package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class JogadorPage1 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnEditExcJog;

    @FXML
    private Button btnInserirJog;

    @FXML
    private Button btnListarJog;

    @FXML
    private Button btnReturnJog;

    @FXML
    void btnEditExcJogAction(ActionEvent event) throws Exception {
    	Parent fxmlEditarExcluirJog = FXMLLoader.load(getClass().getResource("/app/view/Jogador_EditarExcluirPage.fxml"));
    	Main.trocarTelas(fxmlEditarExcluirJog);
    	//Main.trocarTelas("Jogador_EditarExcluirPage");
    }

    @FXML
    void btnInserirJogAction(ActionEvent event) throws Exception {
    	Parent fxmlInserirJog = FXMLLoader.load(getClass().getResource("/app/view/Jogador_InserirPage.fxml"));
    	Main.trocarTelas(fxmlInserirJog);
    	//Main.trocarTelas("Jogador_InserirPage");
    }

    @FXML
    void btnListarJogAction(ActionEvent event) throws Exception {
    	Parent fxmlListarJog = FXMLLoader.load(getClass().getResource("/app/view/Jogador_ListarPage.fxml"));
    	Main.trocarTelas(fxmlListarJog);
    	//Main.trocarTelas("Jogador_ListarPage");
    }

    @FXML
    void btnReturnJogAction(ActionEvent event) throws Exception {
    	Parent fxmlArb = FXMLLoader.load(getClass().getResource("/app/view/MainWindow.fxml"));
    	Main.trocarTelas(fxmlArb);
    	//Main.trocarTelas("MainWindow");
    }

    @FXML
    void initialize() {
        assert btnEditExcJog != null : "fx:id=\"btnEditarJog\" was not injected: check your FXML file 'JogadorPage1.fxml'.";
        assert btnInserirJog != null : "fx:id=\"btnInserirJog\" was not injected: check your FXML file 'JogadorPage1.fxml'.";
        assert btnListarJog != null : "fx:id=\"btnListarJog\" was not injected: check your FXML file 'JogadorPage1.fxml'.";
        assert btnReturnJog != null : "fx:id=\"btnReturnJog\" was not injected: check your FXML file 'JogadorPage1.fxml'.";

    }

}
