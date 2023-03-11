package app.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import app.Main;
import app.model.Oitavas;
import app.model.Partida;
import app.model.Quartas;
import app.model.Selecao;
import app.model.SelecaoDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class PartidasQuartasPage {
	
	 @FXML
	private HBox boxGols;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAddPart;

    @FXML
    private Button btnListDelPart;

    @FXML
    private Button btnReturn;

    @FXML
	private ComboBox<Selecao> choiceGanhador;

	private List<Selecao> timesPartida = new ArrayList<>();

	private ObservableList<Selecao> obsTimes;

	@FXML
	private ComboBox<String> choicePartida;

	private List<String> partidasQuartas = new ArrayList<>();

	private ObservableList<String> obsPartidas;

	@FXML
	private ComboBox<Integer> comboGolsTime1;

	@FXML
	private ComboBox<Integer> comboGolsTime2;

	private List<Integer> numInteiros = new ArrayList<>();

	private ObservableList<Integer> obsNum;

    @FXML
    private DatePicker datePickerPart;

    @FXML
    private TextField horaPartida;

    @FXML
    private Label labelExibirTime1;

    @FXML
    private Label labelExibirTime2;

    @FXML
    private Label labelMessage;

    @FXML
    private TextField localPartida;

    @FXML
    private TableView<Partida> tabelaPartidas;
    
    private ObservableList<Partida> dadosPartidas;

    @FXML
    void atualizarLabels(MouseEvent event) {
    	String partidaTimes = choicePartida.getValue();
		if (partidaTimes != null) {
			String[] selecoes = partidaTimes.split(" X ");

			Selecao time1 = SelecaoDAOImpl.verificaSelecao(selecoes[0]);
			Selecao time2 = SelecaoDAOImpl.verificaSelecao(selecoes[1]);

			this.labelExibirTime1.setText(time1.getNome().toUpperCase());
			this.labelExibirTime2.setText(time2.getNome().toUpperCase());

			// atualizando o choice de ganhador
			this.timesPartida.clear();
			this.timesPartida.add(time1);
			this.timesPartida.add(time2);

			obsTimes = FXCollections.observableArrayList(timesPartida);
			choiceGanhador.setItems(obsTimes);
		}
    }

    @FXML
    void btnAddPartAction(ActionEvent event) {
    	labelMessage.setTextFill(Color.RED);
		
		try {
			// tirando os valores informados
			String partidaTimes = this.choicePartida.getValue();
			LocalDate getDate = this.datePickerPart.getValue();
			String horario = this.horaPartida.getText();
			String local = this.localPartida.getText();

			Integer golsTime1 = this.comboGolsTime1.getValue();
			Integer golsTime2 = this.comboGolsTime2.getValue();

			// verificação dos dados informados
			if (verificaEspacos()) {
				return;
			}

			// colocando a data no formato brasileiro
			String data = getDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

			// encontrando o objeto seleção dos times da partida informados
			String[] selecoes = partidaTimes.split(" X ");

			Selecao time1 = SelecaoDAOImpl.verificaSelecao(selecoes[0]);
			Selecao time2 = SelecaoDAOImpl.verificaSelecao(selecoes[1]);
			
			//ganhador da partida
			Selecao ganhador = null;
			if ((golsTime1.equals(golsTime2))) {
				ganhador = choiceGanhador.getValue();
			} else if (golsTime1 > golsTime2) {
				ganhador = time1;
			} else if (golsTime1 < golsTime2) {
				ganhador = time2;
			}	
			
			// buscando a partida na lista de partidas geradas das Quartas
			Partida partidaEscolhida = null;
			for (Partida partAtual : Quartas.getPrePartidasQuartas().values()) {
				if (partAtual.getTime1().equals(time1.getNome()) && partAtual.getTime2().equals(time2.getNome())) {
					partidaEscolhida = partAtual;
				}
			}

			// setando as informações
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
			Date dataP = new Date();
			String codPart = sdf.format(dataP);
			
			partidaEscolhida.setCodPart(codPart);
			partidaEscolhida.setData(data);
			partidaEscolhida.setHorario(horario);
			partidaEscolhida.setLocal(local);
			partidaEscolhida.setGolsTime1(golsTime1);
			partidaEscolhida.setGolsTime2(golsTime2);

			partidaEscolhida.setGanhador(ganhador);

			if (ganhador.equals(time1)) {
				partidaEscolhida.setPerdedor(time2);

			} else if (ganhador.equals(time2)) {
				partidaEscolhida.setPerdedor(time1);
			}
			Integer numPart = null;
			for (Integer num : Quartas.getPrePartidasQuartas().keySet()) {
				if (Quartas.getPrePartidasQuartas().get(num).equals(partidaEscolhida)) {
					numPart = num;
				}
			}
			
			Quartas.inserir(partidaEscolhida, numPart);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		carregarPartidasQuartas();
		attTabela();
		clearAll();
		labelMessage.setTextFill(Color.GREEN);
		labelMessage.setText("Partida Quartas cadastrada com sucesso!");
		
		if(Quartas.getPartidasQuartas().size() == 4) {
			labelMessage.setText("Você já cadastrou todas as partidas das Quartas!");
		}
    }

    @FXML
    void btnListDelPartAction(ActionEvent event) {
    	labelMessage.setTextFill(Color.RED);
		
		Partida selectedPart = this.tabelaPartidas.getSelectionModel().getSelectedItem();
		
		if (selectedPart != null) {
			
			Integer numPart = null;
			
			for (Integer num : Quartas.getPartidasQuartas().keySet()) {
				if (Quartas.getPartidasQuartas().get(num).equals(selectedPart)) {
					numPart = num;
				}
			}
			
			Quartas.excluir(selectedPart, numPart);
			
		} else { //caso o usuário não tenha selecionado nada, um erro é exibido
			this.labelMessage.setText("Selecione uma partida na tabela!");
			return;
		}
		
		labelMessage.setTextFill(Color.GREEN);
		labelMessage.setText("Partida excluída com sucesso!");
		
		this.carregarPartidasQuartas();
		this.attTabela();
    }

    @FXML
    void btnReturnAction(ActionEvent event) throws Exception {
    	Parent fxmlEliminatorias = FXMLLoader.load(getClass().getResource("/app/view/EliminatoriasPage.fxml"));
		Main.trocarTelas(fxmlEliminatorias);
    }
    
    public void carregarPartidasQuartas() {
    	
    	try {
    		partidasQuartas.clear();
    		
    		Map<Integer, Partida> mapPrePartidasQuartas = Quartas.getPrePartidasQuartas();
    		
    		for (int i = 1; i <= 4; i++) {
    			if (mapPrePartidasQuartas.get(i) !=  null) {
    				this.partidasQuartas.add(mapPrePartidasQuartas.get(i).getTime1() + " X " + mapPrePartidasQuartas.get(i).getTime2());
    			}
    		}
    		
    		obsPartidas = FXCollections.observableArrayList(partidasQuartas);
    		choicePartida.setItems(obsPartidas);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public void carregarCombosInteger() {
		for (Integer i = 0; i <= 10; i++) {
			this.numInteiros.add(i);
		}

		obsNum = FXCollections.observableArrayList(numInteiros);

		this.comboGolsTime1.setItems(obsNum);
		this.comboGolsTime2.setItems(obsNum);
	}
    
    @FXML
    void HBoxMove(MouseEvent event) {
		Integer golsTime1 = this.comboGolsTime1.getValue();
		Integer golsTime2 = this.comboGolsTime2.getValue();
		
		if (golsTime1 == null || golsTime2 == null) {
			return;
		} else if (golsTime1.equals(golsTime2)) {
			this.choiceGanhador.setDisable(false);
		} else if (!(golsTime1.equals(golsTime2))) {
			this.choiceGanhador.setDisable(true);
		}
    }
    
    public boolean verificaEspacos() {

		if (this.choicePartida.getValue() == null || this.datePickerPart.getValue() == null || this.horaPartida.getText().isBlank()
				|| this.localPartida.getText().isBlank() || this.comboGolsTime1.getValue() == null || this.comboGolsTime2 == null
					|| (this.choiceGanhador.getValue() == null && this.choiceGanhador.isDisable() == false)) {

			labelMessage.setText("Há espaços em branco!");
			return true;
		}
		return false;   
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@FXML
    void initialize() {
        assert btnAddPart != null : "fx:id=\"btnAddPart\" was not injected: check your FXML file 'PartidasQuartasPage.fxml'.";
        assert btnListDelPart != null : "fx:id=\"btnListDelPart\" was not injected: check your FXML file 'PartidasQuartasPage.fxml'.";
        assert btnReturn != null : "fx:id=\"btnReturn\" was not injected: check your FXML file 'PartidasQuartasPage.fxml'.";
        assert choiceGanhador != null : "fx:id=\"choiceGanhador\" was not injected: check your FXML file 'PartidasQuartasPage.fxml'.";
        assert choicePartida != null : "fx:id=\"choicePartida\" was not injected: check your FXML file 'PartidasQuartasPage.fxml'.";
        assert comboGolsTime1 != null : "fx:id=\"comboGolsTime1\" was not injected: check your FXML file 'PartidasQuartasPage.fxml'.";
        assert comboGolsTime2 != null : "fx:id=\"comboGolsTime2\" was not injected: check your FXML file 'PartidasQuartasPage.fxml'.";
        assert datePickerPart != null : "fx:id=\"datePickerPart\" was not injected: check your FXML file 'PartidasQuartasPage.fxml'.";
        assert horaPartida != null : "fx:id=\"horaPartida\" was not injected: check your FXML file 'PartidasQuartasPage.fxml'.";
        assert labelExibirTime1 != null : "fx:id=\"labelExibirTime1\" was not injected: check your FXML file 'PartidasQuartasPage.fxml'.";
        assert labelExibirTime2 != null : "fx:id=\"labelExibirTime2\" was not injected: check your FXML file 'PartidasQuartasPage.fxml'.";
        assert labelMessage != null : "fx:id=\"labelMessage\" was not injected: check your FXML file 'PartidasQuartasPage.fxml'.";
        assert localPartida != null : "fx:id=\"localPartida\" was not injected: check your FXML file 'PartidasQuartasPage.fxml'.";
        assert tabelaPartidas != null : "fx:id=\"tabelaPartidas\" was not injected: check your FXML file 'PartidasQuartasPage.fxml'.";
        
        carregarCombosInteger();
        carregarPartidasQuartas();
        this.choiceGanhador.setDisable(true);
        
     // Adicionando as colunas na tabela
     	TableColumn time1Col = new TableColumn("Time 1");
     	TableColumn gols1Col = new TableColumn("Gols");
     	TableColumn time2Col = new TableColumn("Time 2");
     	TableColumn gols2Col = new TableColumn("Gols");
     	TableColumn ganhadorCol = new TableColumn("Ganhador");
     	TableColumn dataCol = new TableColumn("Data");
     	TableColumn horarioCol = new TableColumn("Horário");
     	TableColumn localCol = new TableColumn("Local");
     	

     	time1Col.setCellValueFactory(new PropertyValueFactory<Partida, String>("time1"));
     	gols1Col.setCellValueFactory(new PropertyValueFactory<Partida, Integer>("golsTime1"));
     	time2Col.setCellValueFactory(new PropertyValueFactory<Partida, String>("time2"));
     	gols2Col.setCellValueFactory(new PropertyValueFactory<Partida, Integer>("golsTime2"));
     	ganhadorCol.setCellValueFactory(new PropertyValueFactory<Partida, Integer>("ganhador"));
     	dataCol.setCellValueFactory(new PropertyValueFactory<Partida, String>("data"));
     	horarioCol.setCellValueFactory(new PropertyValueFactory<Partida, String>("horario"));
     	localCol.setCellValueFactory(new PropertyValueFactory<Partida, String>("local"));

     	this.tabelaPartidas.getColumns().addAll(time1Col, gols1Col, time2Col, gols2Col, ganhadorCol, dataCol, horarioCol, localCol);
     	attTabela();
    }
    
    public void attTabela() {
    	//exibindo os dados na tabela
     	this.dadosPartidas = FXCollections.observableArrayList(Quartas.getPartidasQuartas().values());
		this.tabelaPartidas.setItems(dadosPartidas);
    }
    
    public void clearAll() {
		this.choiceGanhador.setValue(null);
		this.choicePartida.setValue(null);
		this.comboGolsTime1.setValue(null);
		this.comboGolsTime2.setValue(null);
		this.datePickerPart.setValue(null);
		this.horaPartida.setText("");
		this.localPartida.setText("");
		this.labelExibirTime1.setText(null);
		this.labelExibirTime2.setText(null);
		this.labelMessage.setText(null);
	}

}
