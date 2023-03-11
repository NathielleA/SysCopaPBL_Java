package app.controller;


import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;


public class popUpFaseG {

    @FXML
    private Button btnSim;


    @FXML
    void btnSimAction(ActionEvent event) throws Exception {

    	Parent fxmlFaseGrupos = FXMLLoader.load(getClass().getResource("/app/view/FaseGruposPage.fxml"));
    	Main.trocarTelas(fxmlFaseGrupos);
    	
    	MainWindow.fecharPopUp();

    }
}
