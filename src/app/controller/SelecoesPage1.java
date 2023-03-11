package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class SelecoesPage1 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnEditExcSel;

    @FXML
    private Button btnInserirSel;

    @FXML
    private Button btnListarSel;

    @FXML
    private Button btnReturn;

    @FXML
    void btnEditExcSelAction(ActionEvent event) throws Exception {	
    	Parent fxmlEditarExcluirSel = FXMLLoader.load(getClass().getResource("/app/view/Selecoes_EditarExcluirPage.fxml"));
    	Main.trocarTelas(fxmlEditarExcluirSel);
    	//Main.trocarTelas("Selecoes_EditarExcluirPage");

    }

    @FXML
    void btnInserirSelAction(ActionEvent event) throws Exception {
    	Parent fxmlInserirSel = FXMLLoader.load(getClass().getResource("/app/view/Selecoes_InserirPage.fxml"));
    	Main.trocarTelas(fxmlInserirSel);
    	//Main.trocarTelas("Selecoes_InserirPage");
    }

    @FXML
    void btnListarSelAction(ActionEvent event) throws Exception {
    	Parent fxmlListarSel = FXMLLoader.load(getClass().getResource("/app/view/Selecoes_ListarPage.fxml"));
    	Main.trocarTelas(fxmlListarSel);
    	//Main.trocarTelas("Selecoes_ListarPage");
    }

    @FXML
    void btnReturnAction(ActionEvent event) throws Exception {
    	Parent fxmlArb = FXMLLoader.load(getClass().getResource("/app/view/MainWindow.fxml"));
    	Main.trocarTelas(fxmlArb);
    	//Main.trocarTelas("MainWindow");
    }

    @FXML
    void initialize() {
        assert btnEditExcSel != null : "fx:id=\"btnEditarSel\" was not injected: check your FXML file 'SelecoesPage1.fxml'.";
        assert btnInserirSel != null : "fx:id=\"btnInserirSel\" was not injected: check your FXML file 'SelecoesPage1.fxml'.";
        assert btnListarSel != null : "fx:id=\"btnListarSel\" was not injected: check your FXML file 'SelecoesPage1.fxml'.";
        assert btnReturn != null : "fx:id=\"btnReturn\" was not injected: check your FXML file 'SelecoesPage1.fxml'.";

    }

}
