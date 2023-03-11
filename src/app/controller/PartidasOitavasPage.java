package app.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.Main;
import app.model.Oitavas;
import app.model.Partida;
import app.model.Selecao;
import app.model.SelecaoDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class PartidasOitavasPage {

	private SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
	
	@FXML
	private HBox boxGols;

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
	private ChoiceBox<String> choiceGruposOitavas;

	private List<String> grupos = new ArrayList<>();

	private ObservableList<String> obsGrupos;

	@FXML
	private ComboBox<String> choicePartida;

	private List<String> partidasGrupo = new ArrayList<>();

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
			String grupo = this.choiceGruposOitavas.getValue();
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
			
			// buscando a partida na lista de partidas geradas das Oitavas
			Partida partidaEscolhida = null;
			for (Partida partAtual : Oitavas.getPrePartidas().values()) {
				if (partAtual.getTime1().equals(time1.getNome()) && partAtual.getTime2().equals(time2.getNome())) {
					partidaEscolhida = partAtual;
					
				}
			}

			// setando as informações
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
			for (Integer num : Oitavas.getPrePartidas().keySet()) {
				if (Oitavas.getPrePartidas().get(num).equals(partidaEscolhida)) {
					numPart = num;
				}
			}
			
			Oitavas.inserir(partidaEscolhida, numPart);

			labelMessage.setTextFill(Color.GREEN);
			labelMessage.setText("Partida Oitavas cadastrada com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
		}
		clearAll();
		
		if(Oitavas.getPartidasOitavas().size() == 8) {
			labelMessage.setText("Você já cadastrou todas as partidas das Oitavas de final!");
		}
	}

	@FXML
	void btnListDelPartAction(ActionEvent event) throws Exception {
		Parent fxmlDelListOitavas = FXMLLoader.load(getClass().getResource("/app/view/ListDelPartOitavas.fxml"));
    	Main.trocarTelas(fxmlDelListOitavas);
	}

	@FXML
	void btnReturnAction(ActionEvent event) throws Exception {
		Parent fxmlEliminatorias = FXMLLoader.load(getClass().getResource("/app/view/EliminatoriasPage.fxml"));
		Main.trocarTelas(fxmlEliminatorias);
	}

	@FXML
	void exibirPartidasDosGrupos(MouseEvent event) {
		carregarPartidas();
	}

	public void carregarGrupos() {
		grupos.add("A e B");// partidas 1 e 2
		grupos.add("C e D");// partidas 3 e 4
		grupos.add("E e F");// partidas 5 e 6
		grupos.add("G e H");// partidas 7 e 8

		obsGrupos = FXCollections.observableArrayList(grupos);
		choiceGruposOitavas.setItems(obsGrupos);
	}

	public void carregarPartidas() {
		// buscando a lista onde está armazenado as partidas geradas das oitavas de
		// final
		String grupo = this.choiceGruposOitavas.getValue();
		partidasGrupo.clear();

		if (grupo != null) {

			switch(grupo) {
			case "A e B":
				
				if (Oitavas.getPrePartidas().get(1) != null) {
					partidasGrupo.add(Oitavas.getPrePartidas().get(1).getTime1() + " X " + Oitavas.getPrePartidas().get(1).getTime2());
				}
				if (Oitavas.getPrePartidas().get(2) != null) {
					partidasGrupo.add(Oitavas.getPrePartidas().get(2).getTime1() + " X " + Oitavas.getPrePartidas().get(2).getTime2());
				}			
				break;
				
			case "C e D":
				if (Oitavas.getPrePartidas().get(3) != null) {
					partidasGrupo.add(Oitavas.getPrePartidas().get(3).getTime1() + " X " + Oitavas.getPrePartidas().get(3).getTime2());
				}
				
				if(Oitavas.getPrePartidas().get(4) != null) {
					partidasGrupo.add(Oitavas.getPrePartidas().get(4).getTime1() + " X " + Oitavas.getPrePartidas().get(4).getTime2());
				}				
				break;
				
			case "E e F":
				if (Oitavas.getPrePartidas().get(5) != null) {
					partidasGrupo.add(Oitavas.getPrePartidas().get(5).getTime1() + " X " + Oitavas.getPrePartidas().get(5).getTime2());
				}
				if (Oitavas.getPrePartidas().get(6) != null) {
					partidasGrupo.add(Oitavas.getPrePartidas().get(6).getTime1() + " X " + Oitavas.getPrePartidas().get(6).getTime2());
				}
	
				break;
				
			case "G e H":
				if (Oitavas.getPrePartidas().get(7) != null) {
					partidasGrupo.add(Oitavas.getPrePartidas().get(7).getTime1() + " X " + Oitavas.getPrePartidas().get(7).getTime2());
				}
				if (Oitavas.getPrePartidas().get(8) != null) {
					partidasGrupo.add(Oitavas.getPrePartidas().get(8).getTime1() + " X " + Oitavas.getPrePartidas().get(8).getTime2());
				}
				
				break;
			}

			obsPartidas = FXCollections.observableArrayList(partidasGrupo);
			choicePartida.setItems(obsPartidas);
		}
	}

	public boolean verificaEspacos() {

		if (this.choiceGruposOitavas.getValue() == null || this.choicePartida.getValue() == null
				|| this.datePickerPart.getValue() == null || this.horaPartida.getText().isBlank()
				|| this.localPartida.getText().isBlank() || (this.choiceGanhador.getValue() == null && this.choiceGanhador.isDisable() == false)) {

			labelMessage.setText("Há espaços em branco!");
			return true;
		}
		return false;
	}

	public void carregarCombosInteger() {
		for (Integer i = 0; i <= 10; i++) {
			this.numInteiros.add(i);
		}

		obsNum = FXCollections.observableArrayList(numInteiros);

		this.comboGolsTime1.setItems(obsNum);
		this.comboGolsTime2.setItems(obsNum);
	}
	
	public void clearAll() {
		this.choiceGruposOitavas.setValue(null);
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

	@FXML
	void initialize() {
		carregarGrupos();
		carregarCombosInteger();
		this.choiceGanhador.setDisable(true);
	}

}
