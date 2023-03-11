package app.controller;

import app.Main;
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
import javafx.scene.input.MouseEvent;

public class Selecoes_ListarPage {

	@FXML
	private Button btnReturn;

	@FXML
	private TableView<Selecao> tabelaSelecoes;

	private ObservableList<Selecao> dadosSelecoes;

	@FXML
	void btnReturnAction(ActionEvent event) throws Exception {
		Parent fxmlSelecoes1 = FXMLLoader.load(getClass().getResource("/app/view/SelecoesPage1.fxml"));
    	Main.trocarTelas(fxmlSelecoes1);
		//Main.trocarTelas("SelecoesPage1");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@FXML
	void initialize() {
		// Adicionando as colunas na tabela
		TableColumn nomeCol = new TableColumn("Seleção");
		TableColumn tecnicoCol = new TableColumn("Técnico");
		TableColumn grupoCol = new TableColumn("Grupo");
		TableColumn pontuacaoCol = new TableColumn("Pontuação");

		nomeCol.setCellValueFactory(new PropertyValueFactory<Selecao, String>("nome"));
		tecnicoCol.setCellValueFactory(new PropertyValueFactory<Selecao, String>("tecnico"));
		grupoCol.setCellValueFactory(new PropertyValueFactory<Selecao, String>("grupo"));
		pontuacaoCol.setCellValueFactory(new PropertyValueFactory<Selecao, Double>("pontuacaoFaseG"));

		this.tabelaSelecoes.getColumns().addAll(nomeCol, tecnicoCol, grupoCol, pontuacaoCol);
		this.dadosSelecoes = FXCollections.observableArrayList(SelecaoDAOImpl.getLista1());
		this.tabelaSelecoes.setItems(dadosSelecoes);
	}


}
