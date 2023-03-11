package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import app.model.Jogador;
import app.model.JogadorDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Jogador_ListarPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnReturn;
    
    @FXML
    private Label labelTotalJog;

    @FXML
	private TableView<Jogador> tabelaJogadores;

	private ObservableList<Jogador> dadosJogadores;

    @FXML
    void btnReturnAction(ActionEvent event) throws Exception {
    	Parent fxmlJogador1 = FXMLLoader.load(getClass().getResource("/app/view/JogadorPage1.fxml"));
    	Main.trocarTelas(fxmlJogador1);
    	//Main.trocarTelas("JogadorPage1");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
    void initialize() {
        assert btnReturn != null : "fx:id=\"btnReturn\" was not injected: check your FXML file 'Jogador_ListarPage.fxml'.";
        assert tabelaJogadores != null : "fx:id=\"tabelaJogadores\" was not injected: check your FXML file 'Jogador_ListarPage.fxml'.";
        
     // Adicionando as colunas na tabela
     		TableColumn IDCol = new TableColumn("ID");
     		TableColumn nomeCol = new TableColumn("Nome");
     		TableColumn selecaoCol = new TableColumn("Seleção");
     		TableColumn posicaoCol = new TableColumn("Posição");
     		TableColumn cartACol = new TableColumn("Cartões Amarelos");
     		TableColumn cartVCol = new TableColumn("Cartões Vermelhos");
     		TableColumn golsCol = new TableColumn("Gols");

     		IDCol.setCellValueFactory(new PropertyValueFactory<Jogador, String>("codJog"));
     		nomeCol.setCellValueFactory(new PropertyValueFactory<Jogador, String>("nome"));
     		selecaoCol.setCellValueFactory(new PropertyValueFactory<Jogador, String>("selecao"));
     		posicaoCol.setCellValueFactory(new PropertyValueFactory<Jogador, String>("posicao"));
     		cartACol.setCellValueFactory(new PropertyValueFactory<Jogador, Double>("cartAmarelo"));
     		cartVCol.setCellValueFactory(new PropertyValueFactory<Jogador, Double>("cartVermelho"));
     		golsCol.setCellValueFactory(new PropertyValueFactory<Jogador, Double>("gols"));

     		this.tabelaJogadores.getColumns().addAll(IDCol, nomeCol, selecaoCol, posicaoCol, cartACol, cartVCol, golsCol);
     		
     		//exibindo jogadores
     		this.dadosJogadores = FXCollections.observableArrayList(JogadorDAOImpl.getMap().values());
    		this.tabelaJogadores.setItems(dadosJogadores);
    		labelTotalJog.setText("Total: " + dadosJogadores.size() + " jogadores");
    }

}
