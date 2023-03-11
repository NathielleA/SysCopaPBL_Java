package app.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.Main;
import app.model.Final;
import app.model.Partida;
import app.model.Selecao;
import app.model.SelecaoDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PartidaFinalPage {

    @FXML
    private Button btnCadastrarFinal;

    @FXML
    private Button btnExcFinal;

    @FXML
    private Button btnReturn;

    @FXML
    private ComboBox<String> choiceGanhadorFinal;
    
    private List<String> timesPartidaFinal = new ArrayList<>();

	private ObservableList<String> obsTimesFinal;

    @FXML
    private ComboBox<String> choiceGanhadorTerc;
    
    private List<String> timesPartidaTerc = new ArrayList<>();

	private ObservableList<String> obsTimesTerc;

    @FXML
    private ComboBox<Integer> comboGolsFinal1;

    @FXML
    private ComboBox<Integer> comboGolsFinal2;

    @FXML
    private ComboBox<Integer> comboGolsTerc1;

    @FXML
    private ComboBox<Integer> comboGolsTerc2;
    
    private List<Integer> numInteiros = new ArrayList<>();

	private ObservableList<Integer> obsNum;

    @FXML
    private DatePicker datePickerPart3;

    @FXML
    private DatePicker datePickerPartF;

    @FXML
    private HBox box1Gols;

    @FXML
    private HBox box2Gols;

    @FXML
    private TextField horaPartida3;

    @FXML
    private TextField horaPartidaF;

    @FXML
    private Label labelFinalT1;

    @FXML
    private Label labelFinalT2;

    @FXML
    private Label labelTercT1;

    @FXML
    private Label labelTercT2;

    @FXML
    private TextField localPartida3;

    @FXML
    private TextField localPartidaF;
    
    @FXML
    private Label labelMessage;
    
    private static Stage stage;

    @FXML
    void btnCadastrarFinalAction(ActionEvent event) throws Exception {
    	try {
			// tirando os valores informados
			LocalDate getDate = this.datePickerPartF.getValue();
			String horario = this.horaPartidaF.getText();
			String local = this.localPartidaF.getText();

			Integer golsTime1 = this.comboGolsFinal1.getValue();
			Integer golsTime2 = this.comboGolsFinal2.getValue();

			// verificação dos dados informados
			if (verificaEspacos()) {
				return;
			}

			// colocando a data no formato brasileiro
			String data = getDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

			// encontrando o objeto seleção dos times da partida informados
			Selecao time1 = SelecaoDAOImpl.verificaSelecao(Final.getPreMapFinal().get(1).getTime1());
			Selecao time2 = SelecaoDAOImpl.verificaSelecao(Final.getPreMapFinal().get(1).getTime2());

			
			//ganhador da partida
			Selecao ganhador = null;
			if ((golsTime1.equals(golsTime2))) {
				String ganTemp = choiceGanhadorFinal.getValue();
				ganhador = SelecaoDAOImpl.verificaSelecao(ganTemp);
			} else if (golsTime1 > golsTime2) {
				ganhador = time1;
			} else if (golsTime1 < golsTime2) {
				ganhador = time2;
			}
			
			Partida partidaFinal = Final.getPreMapFinal().get(1);

			// setando as informações
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
			Date dataP = new Date();
			String codPart = sdf.format(dataP);
			
			partidaFinal.setCodPart(codPart);
			partidaFinal.setData(data);
			partidaFinal.setHorario(horario);
			partidaFinal.setLocal(local);
			partidaFinal.setGolsTime1(golsTime1);
			partidaFinal.setGolsTime2(golsTime2);

			partidaFinal.setGanhador(ganhador);

			if (ganhador.equals(time1)) {
				partidaFinal.setPerdedor(time2);

			} else if (ganhador.equals(time2)) {
				partidaFinal.setPerdedor(time1);
			}
			
			Final.inserir(partidaFinal, 1);
			
			this.cadastroTerceiroLugar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	//abrindo PopUp da Finalização e dos Vencedores
    	Parent fxmlPopUpVencedores = FXMLLoader.load(getClass().getResource("/app/view/FinalPage.fxml"));
		Scene scene = new Scene(fxmlPopUpVencedores);

		stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();

    }
    
    public void cadastroTerceiroLugar() {
    	try {
			// tirando os valores informados
			LocalDate getDate = this.datePickerPart3.getValue();
			String horario = this.horaPartida3.getText();
			String local = this.localPartida3.getText();

			Integer golsTime1 = this.comboGolsTerc1.getValue();
			Integer golsTime2 = this.comboGolsTerc2.getValue();

			// colocando a data no formato brasileiro
			String data = getDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

			// encontrando o objeto seleção dos times da partida informados
			Selecao time1 = SelecaoDAOImpl.verificaSelecao(Final.getPreMapFinal().get(2).getTime1());
			Selecao time2 = SelecaoDAOImpl.verificaSelecao(Final.getPreMapFinal().get(2).getTime2());
			
			//ganhador da partida
			Selecao ganhador = null;
			if ((golsTime1.equals(golsTime2))) {
				String ganTemp = choiceGanhadorTerc.getValue();
				ganhador = SelecaoDAOImpl.verificaSelecao(ganTemp);
			} else if (golsTime1 > golsTime2) {
				ganhador = time1;
			} else if (golsTime1 < golsTime2) {
				ganhador = time2;
			}	
			
			Partida partidaTerceiro = Final.getPreMapFinal().get(2);

			// setando as informações
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
			Date dataP = new Date();
			String codPart = sdf.format(dataP);
			
			partidaTerceiro.setCodPart(codPart);
			partidaTerceiro.setData(data);
			partidaTerceiro.setHorario(horario);
			partidaTerceiro.setLocal(local);
			partidaTerceiro.setGolsTime1(golsTime1);
			partidaTerceiro.setGolsTime2(golsTime2);

			partidaTerceiro.setGanhador(ganhador);

			if (ganhador.equals(time1)) {
				partidaTerceiro.setPerdedor(time2);

			} else if (ganhador.equals(time2)) {
				partidaTerceiro.setPerdedor(time1);
			}
			
			Final.inserir(partidaTerceiro, 2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

    @FXML
    void btnExcFinalAction(ActionEvent event) {
    	clearAll();
    	Final.excluir(Final.getMapFinal().get(1), 1);
    	Final.excluir(Final.getMapFinal().get(2), 2);
    }

    @FXML
    void btnReturnAction(ActionEvent event) throws Exception {
    	Parent fxmlEliminatorias = FXMLLoader.load(getClass().getResource("/app/view/EliminatoriasPage.fxml"));
		Main.trocarTelas(fxmlEliminatorias);
    }
    
    @FXML
    void HBoxMove1(MouseEvent event) {
		Integer golsTime1 = this.comboGolsFinal1.getValue();
		Integer golsTime2 = this.comboGolsFinal2.getValue();
		
		if (golsTime1 == null || golsTime2 == null) {
			return;
		} else if (golsTime1.equals(golsTime2)) {
			this.choiceGanhadorFinal.setDisable(false);
		} else if (!(golsTime1.equals(golsTime2))) {
			this.choiceGanhadorFinal.setDisable(true);
		}
    }
    
    @FXML
    void HBoxMove2(MouseEvent event) {
		Integer golsTime1 = this.comboGolsTerc1.getValue();
		Integer golsTime2 = this.comboGolsTerc2.getValue();
		
		if (golsTime1 == null || golsTime2 == null) {
			return;
		} else if (golsTime1.equals(golsTime2)) {
			this.choiceGanhadorTerc.setDisable(false);
		} else if (!(golsTime1.equals(golsTime2))) {
			this.choiceGanhadorTerc.setDisable(true);
		}
    }
    
    public void carregarCombosInteger() {
		for (Integer i = 0; i <= 10; i++) {
			this.numInteiros.add(i);
		}

		obsNum = FXCollections.observableArrayList(numInteiros);

		this.comboGolsFinal1.setItems(obsNum);
		this.comboGolsFinal2.setItems(obsNum);
		this.comboGolsTerc1.setItems(obsNum);
		this.comboGolsTerc2.setItems(obsNum);
	}
    
    public boolean verificaEspacos() {

		if (this.datePickerPartF.getValue() == null || this.datePickerPart3.getValue() == null || this.horaPartidaF.getText().isBlank() ||
				this.horaPartida3.getText().isBlank() || this.localPartidaF.getText().isBlank() || this.localPartida3.getText().isBlank() || 
				this.comboGolsFinal1.getValue() == null || this.comboGolsFinal2 == null || this.comboGolsTerc1.getValue() == null || 
				this.comboGolsTerc2.getValue() == null || 
				(this.choiceGanhadorFinal.getValue() == null && this.choiceGanhadorFinal.isDisable() == false) || 
				(this.choiceGanhadorTerc.getValue() == null && this.choiceGanhadorTerc.isDisable() == false)) {

			labelMessage.setTextFill(Color.RED);
			labelMessage.setText("Há espaços em branco!");
			return true;
		}
		return false;   
    }
    
    @FXML
    void initialize() {
    	carregarCombosInteger();
    	
    	//preenchendo os labels com os nomes dos times
    	this.labelFinalT1.setText(Final.getPreMapFinal().get(1).getTime1());
    	this.labelFinalT2.setText(Final.getPreMapFinal().get(1).getTime2());
    	
    	this.labelTercT1.setText(Final.getPreMapFinal().get(2).getTime1());
    	this.labelTercT2.setText(Final.getPreMapFinal().get(2).getTime2());
    	
    	//adicionando os times nas choices de ganhador
    	this.timesPartidaFinal.add(Final.getPreMapFinal().get(1).getTime1());
		this.timesPartidaFinal.add(Final.getPreMapFinal().get(1).getTime2());

		obsTimesFinal = FXCollections.observableArrayList(timesPartidaFinal);
		choiceGanhadorFinal.setItems(obsTimesFinal);
		
		this.timesPartidaTerc.add(Final.getPreMapFinal().get(2).getTime1());
		this.timesPartidaTerc.add(Final.getPreMapFinal().get(2).getTime2());

		obsTimesTerc = FXCollections.observableArrayList(timesPartidaTerc);
		choiceGanhadorTerc.setItems(obsTimesTerc);
    }
    
    public void clearAll() {
		this.choiceGanhadorFinal.setValue(null);
		this.comboGolsFinal1.setValue(null);
		this.comboGolsFinal2.setValue(null);
		this.datePickerPartF.setValue(null);
		this.horaPartidaF.setText("");
		this.localPartidaF.setText("");
		this.labelMessage.setText(null);
		
		this.choiceGanhadorTerc.setValue(null);
		this.comboGolsTerc1.setValue(null);
		this.comboGolsTerc2.setValue(null);
		this.datePickerPart3.setValue(null);
		this.horaPartida3.setText("");
		this.localPartida3.setText("");
		this.labelMessage.setText(null);
	
	}

}
