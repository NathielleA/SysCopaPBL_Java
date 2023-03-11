package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import app.model.Oitavas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class popUpEliminatorias {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSim;

    @FXML
    void btnSimAction(ActionEvent event) throws Exception {
    	Oitavas.gerarPartidasOitavas();
    	Parent fxmlEliminatorias = FXMLLoader.load(getClass().getResource("/app/view/EliminatoriasPage.fxml"));
    	Main.trocarTelas(fxmlEliminatorias);
    	
    	FaseGruposPage.fecharPopUp2();
    }

    @FXML
    void initialize() {
        assert btnSim != null : "fx:id=\"btnSim\" was not injected: check your FXML file 'popUpEliminatorias.fxml'.";

    }

}
