package app.controller;

import app.Main;
import app.model.Arbitro;
import app.model.ArbitroDAOImpl;
import app.model.Jogador;
import app.model.JogadorDAOImpl;
import app.model.Selecao;
import app.model.SelecaoDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListagensPage {
	
	@FXML
    private Button btnArbList;

    @FXML
    private Button btnJogList;

    @FXML
    private Button btnReturn;

    @FXML
    private Button btnSelList;
    
    @FXML
    private TableView<Arbitro> tabelaListagensArb;

    @FXML
    private TableView<Jogador> tabelaListagensJog;

    @FXML
    private TableView<Selecao> tabelaListagensSel;
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@FXML
    void btnArbListAction(ActionEvent event) {
    	
    	this.tabelaListagensArb.setItems(null);
		this.tabelaListagensArb.getColumns().clear();
    	
    	TableColumn nomeCol = new TableColumn("Nome");

     	nomeCol.setCellValueFactory(new PropertyValueFactory<Jogador, String>("nome"));
     	this.tabelaListagensArb.getColumns().addAll(nomeCol);
     	
     	ObservableList<Arbitro> dadosArbs = FXCollections.observableArrayList(ArbitroDAOImpl.getLista1());
 		this.tabelaListagensArb.setItems(dadosArbs);
		this.tabelaListagensArb.toFront();
		this.tabelaListagensArb.setDisable(false);
		
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@FXML
    void btnJogListAction(ActionEvent event) throws Exception {
    	
    	this.tabelaListagensJog.setItems(null);
		this.tabelaListagensJog.getColumns().clear();
    	
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

 		this.tabelaListagensJog.getColumns().addAll(IDCol, nomeCol, selecaoCol, posicaoCol, cartACol, cartVCol, golsCol);
 		
 		ObservableList<Jogador> dadosJogs = FXCollections.observableArrayList(JogadorDAOImpl.getMap().values());
 		this.tabelaListagensJog.setItems(dadosJogs);
		this.tabelaListagensJog.toFront();
		this.tabelaListagensJog.setDisable(false);
    }

    @FXML
    void btnReturnAction(ActionEvent event) throws Exception {
    	Parent fxmlFaseGrupos = FXMLLoader.load(getClass().getResource("/app/view/FaseGruposPage.fxml"));
    	Main.trocarTelas(fxmlFaseGrupos);

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@FXML
    void btnSelListAction(ActionEvent event) throws Exception {
    	this.tabelaListagensSel.setItems(null);
		this.tabelaListagensSel.getColumns().clear();
    	
    	TableColumn nomeCol = new TableColumn("Seleção");
		TableColumn tecnicoCol = new TableColumn("Técnico");
		TableColumn grupoCol = new TableColumn("Grupo");
		TableColumn pontuacaoCol = new TableColumn("Pontuação");

		nomeCol.setCellValueFactory(new PropertyValueFactory<Selecao, String>("nome"));
		tecnicoCol.setCellValueFactory(new PropertyValueFactory<Selecao, String>("tecnico"));
		grupoCol.setCellValueFactory(new PropertyValueFactory<Selecao, String>("grupo"));
		pontuacaoCol.setCellValueFactory(new PropertyValueFactory<Selecao, Double>("pontuacaoFaseG"));

		ObservableList<Selecao> dadosSelecoes = FXCollections.observableArrayList(SelecaoDAOImpl.getLista1());

		this.tabelaListagensSel.getColumns().addAll(nomeCol, tecnicoCol, grupoCol, pontuacaoCol);
		this.tabelaListagensSel.setItems(dadosSelecoes);
		this.tabelaListagensSel.toFront();
		this.tabelaListagensSel.setDisable(false);

    }
    
    @FXML
    void initialize() {
    	tabelaListagensArb.setDisable(true);
    	tabelaListagensJog.setDisable(true);
    	tabelaListagensSel.setDisable(true);
    }


}
