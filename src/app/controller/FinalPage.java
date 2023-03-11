package app.controller;

import app.model.Final;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FinalPage {

    @FXML
    private Button btnEncerrar;

    @FXML
    private Label labelCampeao;

    @FXML
    private Label labelSegundo;

    @FXML
    private Label labelTerceiro;

    @FXML
    void btnEncerrarAction(ActionEvent event) {
    	System.exit(0);
    }
    
    @FXML
    void initialize() {
    	this.labelCampeao.setText(Final.getMapFinal().get(1).getGanhador().getNome());
    	this.labelSegundo.setText(Final.getMapFinal().get(1).getPerdedor().getNome());
    	this.labelTerceiro.setText(Final.getMapFinal().get(2).getGanhador().getNome());
    }

}
