package app.controller;

import app.Main;
import app.model.Final;
import app.model.Oitavas;
import app.model.Quartas;
import app.model.SemiFinal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EliminatoriasPage {

    @FXML
    private Button btnFinal;

    @FXML
    private Button btnListar;

    @FXML
    private Button btnOitavas;

    @FXML
    private Button btnQuartas;

    @FXML
    private Button btnSemis;

    @FXML
    private Label labelMessage;

    @FXML
    void btnFinalAction(ActionEvent event) throws Exception {	
    	Parent fxmlFinal = FXMLLoader.load(getClass().getResource("/app/view/PartidaFinalPage.fxml"));
    	Main.trocarTelas(fxmlFinal);
    }

    @FXML
    void btnListarAction(ActionEvent event) throws Exception {
    	Parent fxmlListEli = FXMLLoader.load(getClass().getResource("/app/view/ListagemEliminatorias.fxml"));
    	Main.trocarTelas(fxmlListEli);
    }

    @FXML
    void btnOitavasAction(ActionEvent event) throws Exception {
    	Parent fxmlOitavas = FXMLLoader.load(getClass().getResource("/app/view/PartidasOitavasPage.fxml"));
    	Main.trocarTelas(fxmlOitavas);
    }

    @FXML
    void btnQuartasAction(ActionEvent event) throws Exception { 	
    	Parent fxmlQuartas = FXMLLoader.load(getClass().getResource("/app/view/PartidasQuartasPage.fxml"));
		Main.trocarTelas(fxmlQuartas);
    }

    @FXML
    void btnSemisAction(ActionEvent event) throws Exception {
    	Parent fxmlSemis = FXMLLoader.load(getClass().getResource("/app/view/PartidasSemifinalPage.fxml"));
		Main.trocarTelas(fxmlSemis);
    }
    
    @FXML
    void initialize() {
    	//verificação se os botões podem ser ativados
    	
    	if (Oitavas.getPrePartidas().isEmpty()) { 		
    		if (Main.getPartGerenciar().isQuartasGeradas() == false) {
    			Quartas.gerarPreQuartas();
    			Main.getPartGerenciar().setQuartasGeradas(true);
    		} 		
    		this.btnQuartas.setDisable(false);		
    		
    	} else {
    		this.btnQuartas.setDisable(true);
    	}
    	
    	if (Oitavas.getPrePartidas().isEmpty() && Quartas.getPrePartidasQuartas().isEmpty()) {
    		if (Main.getPartGerenciar().isSemisGeradas() == false) {
    			SemiFinal.gerarPreSemi();
    			Main.getPartGerenciar().setSemisGeradas(true);
    		}    		
    		this.btnSemis.setDisable(false);
    		
    	} else {
    		this.btnSemis.setDisable(true);
    	}
    	
    	if (Oitavas.getPrePartidas().isEmpty() && Quartas.getPrePartidasQuartas().isEmpty() && SemiFinal.prePartidasSemi.isEmpty()) {
    		if (Main.getPartGerenciar().isFinalGeradas() == false) {
    			Final.gerarPreFinal();
    			Main.getPartGerenciar().setFinalGeradas(true);
    		}   		
    		this.btnFinal.setDisable(false);
    	} else {
    		this.btnFinal.setDisable(true);
    	}
    }

}
