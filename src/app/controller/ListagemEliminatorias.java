package app.controller;

import java.util.Map;

import app.Main;
import app.model.Final;
import app.model.Oitavas;
import app.model.Partida;
import app.model.Quartas;
import app.model.SemiFinal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ListagemEliminatorias {

    @FXML
    private Button btnReturn;

    @FXML
    private Label final_time1;

    @FXML
    private Label final_time2;

    @FXML
    private Label oitava1_time1;

    @FXML
    private Label oitava1_time2;

    @FXML
    private Label oitava2_time1;

    @FXML
    private Label oitava2_time2;

    @FXML
    private Label oitava3_time1;

    @FXML
    private Label oitava3_time2;

    @FXML
    private Label oitava4_time1;

    @FXML
    private Label oitava4_time2;

    @FXML
    private Label oitava5_time1;

    @FXML
    private Label oitava5_time2;

    @FXML
    private Label oitava6_time1;

    @FXML
    private Label oitava6_time2;

    @FXML
    private Label oitava7_time1;

    @FXML
    private Label oitava7_time2;

    @FXML
    private Label oitava8_time1;

    @FXML
    private Label oitava8_time2;

    @FXML
    private Label quarta1_time1;

    @FXML
    private Label quarta1_time2;

    @FXML
    private Label quarta2_time1;

    @FXML
    private Label quarta2_time2;

    @FXML
    private Label quarta3_time1;

    @FXML
    private Label quarta3_time2;

    @FXML
    private Label quarta4_time1;
    
    @FXML
    private Label quarta4_time2;

    @FXML
    private Label semifinal1_time1;

    @FXML
    private Label semifinal1_time2;

    @FXML
    private Label semifinal2_time1;

    @FXML
    private Label semifinal2_time2;

    @FXML
    private Label terceiroLugar_time1;

    @FXML
    private Label terceiroLugar_time2;

    @FXML
    void btnReturnAction(ActionEvent event) throws Exception {
    	Parent fxmlEliminatorias = FXMLLoader.load(getClass().getResource("/app/view/EliminatoriasPage.fxml"));
		Main.trocarTelas(fxmlEliminatorias);
    }
    
    public void labelOitavas() {
    	Map<Integer, Partida> partidasOitavas = Oitavas.getPartidasOitavas();
    	
    	if (partidasOitavas.get(1) != null) {
    		this.oitava1_time1.setText(partidasOitavas.get(1).getTime1());
    		this.oitava1_time2.setText(partidasOitavas.get(1).getTime2());
    	}
    	
    	
    	if (partidasOitavas.get(2) != null) {
    		this.oitava2_time1.setText(partidasOitavas.get(2).getTime1());
    		this.oitava2_time2.setText(partidasOitavas.get(2).getTime2());
    	}
    	
    	
    	if (partidasOitavas.get(3) != null) {
    		this.oitava3_time1.setText(partidasOitavas.get(3).getTime1());
    		this.oitava3_time2.setText(partidasOitavas.get(3).getTime2());
    	}
    	
    	
    	if (partidasOitavas.get(4) != null) {
    		this.oitava4_time1.setText(partidasOitavas.get(4).getTime1());
    		this.oitava4_time2.setText(partidasOitavas.get(4).getTime2());
    	}
    	
    	
    	if (partidasOitavas.get(5) != null) {
    		this.oitava5_time1.setText(partidasOitavas.get(5).getTime1());
    		this.oitava5_time2.setText(partidasOitavas.get(5).getTime2());
    	}
    	
    	
    	if (partidasOitavas.get(6) != null) {
    		this.oitava6_time1.setText(partidasOitavas.get(6).getTime1());
    		this.oitava6_time2.setText(partidasOitavas.get(6).getTime2());
    	}
    	
    	
    	if (partidasOitavas.get(7) != null) {
    		this.oitava7_time1.setText(partidasOitavas.get(7).getTime1());    	
    		this.oitava7_time2.setText(partidasOitavas.get(7).getTime2());
    	}
    	
    	
    	if (partidasOitavas.get(8) != null) {
    		this.oitava8_time1.setText(partidasOitavas.get(8).getTime1());
    		this.oitava8_time2.setText(partidasOitavas.get(8).getTime2());
    	}
    	
    }
    
    public void labelQuartas(){
    	Map<Integer, Partida> partidasQuartas = Quartas.getPartidasQuartas();
    	
    	if (partidasQuartas.get(1) != null) {
    		this.quarta1_time1.setText(partidasQuartas.get(1).getTime1());
    		this.quarta1_time2.setText(partidasQuartas.get(1).getTime2());
    	}
    	
    	if (partidasQuartas.get(2) != null) {
    		this.quarta2_time1.setText(partidasQuartas.get(2).getTime1());
    		this.quarta2_time2.setText(partidasQuartas.get(2).getTime2());
    	}
    	
    	if (partidasQuartas.get(3) != null) {
    		this.quarta3_time1.setText(partidasQuartas.get(3).getTime1());
    		this.quarta3_time2.setText(partidasQuartas.get(3).getTime2());
    	}
    	
    	if (partidasQuartas.get(4) != null) {
    		this.quarta4_time1.setText(partidasQuartas.get(4).getTime1());
    		this.quarta4_time2.setText(partidasQuartas.get(4).getTime2());
    	}
	
    }
    
    public void labelSemifinal() {
    	Map<Integer, Partida> partidasSemi = SemiFinal.getPartidasSemi();
    	
    	if (partidasSemi.get(1) != null) {
    		this.semifinal1_time1.setText(partidasSemi.get(1).getTime1());
    		this.semifinal1_time2.setText(partidasSemi.get(1).getTime2());
    	}    	
    	
    	if (partidasSemi.get(2) != null) {
    		this.semifinal2_time1.setText(partidasSemi.get(2).getTime1());
    		this.semifinal2_time2.setText(partidasSemi.get(2).getTime2());
    	}
    	
    }
    
    public void labelFinalTerc() {
    	Map<Integer, Partida> partidasFinalTerceiraPre = Final.getPreMapFinal();
    	Map<Integer, Partida> partidasFinalTerceira = Final.getMapFinal();
    	
    	if (partidasFinalTerceira.get(1) != null) {
    		this.final_time1.setText(partidasFinalTerceira.get(1).getTime1());
    		this.final_time2.setText(partidasFinalTerceira.get(1).getTime2());
    	}
    	
    	
    	if (partidasFinalTerceira.get(2) != null) {
    		this.terceiroLugar_time1.setText(partidasFinalTerceira.get(2).getTime1());
    		this.terceiroLugar_time2.setText(partidasFinalTerceira.get(2).getTime2());
    	}
    	
    	if (partidasFinalTerceiraPre.get(1) != null) {
    		this.final_time1.setText(partidasFinalTerceiraPre.get(1).getTime1());
    		this.final_time2.setText(partidasFinalTerceiraPre.get(1).getTime2());
    	}
    	
    	
    	if (partidasFinalTerceiraPre.get(2) != null) {
    		this.terceiroLugar_time1.setText(partidasFinalTerceiraPre.get(2).getTime1());
    		this.terceiroLugar_time2.setText(partidasFinalTerceiraPre.get(2).getTime2());
    	}

    }
    
    @FXML
    void initialize() {
    	labelOitavas();
    	labelQuartas();
    	labelSemifinal();
    	labelFinalTerc();
    }

}
