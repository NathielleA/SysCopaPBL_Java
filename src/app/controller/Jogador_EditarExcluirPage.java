package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.Main;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Jogador_EditarExcluirPage extends Jogador_InserirPage {
	
	private static JogadorDAOImpl JogadorDAO = new JogadorDAOImpl();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnDelJog;

	@FXML
	private Button btnEditJog;

	@FXML
	private Button btnReturn;
	
	@FXML
    private ChoiceBox<String> choicePosicao;

	@FXML
	private ComboBox<String> choiceSelJog;

	private List<String> nomesSelecoes = new ArrayList<>();

	private ObservableList<String> obsSelecoes;

	@FXML
	private Label labelMessage;

	@FXML
	private TextField nomeJog;

	@FXML
	private TableView<Jogador> tabelaJogadores;

	private ObservableList<Jogador> dadosJogadores;

	@FXML
	void btnDelJogAction(ActionEvent event) {
		
		labelMessage.setTextFill(Color.RED);
		try {
			int jogIndex = this.tabelaJogadores.getSelectionModel().getSelectedIndex();
			
			if (jogIndex >= 0) {
				Jogador selectedJog = this.dadosJogadores.get(jogIndex);
				
				JogadorDAO.excluir(selectedJog.getCodJog());
				
			} else { //caso o usuário não tenha selecionado nada, um erro é exibido
				this.labelMessage.setText("Selecione um Jogador na tabela!");
				return;
			}
			
		} catch(Exception e) {
			this.labelMessage.setText("Não foi possível excluir o Jogador!");
			return;
		}
		
		labelMessage.setTextFill(Color.GREEN);
    	labelMessage.setText("Jogador Excluído com sucesso!");
    	this.attJogadores();
	}

	@FXML
	void btnEditJogAction(ActionEvent event) {
		labelMessage.setTextFill(Color.RED);
		try {
			int jogIndex = this.tabelaJogadores.getSelectionModel().getSelectedIndex();
			Jogador selectedJog;
			
			String novoNomeJog = this.nomeJog.getText();
    		String novaPosicaoJog = this.choicePosicao.getValue();
    		Selecao novaSelecaoJog = SelecaoDAOImpl.verificaSelecao(this.choiceSelJog.getValue());
    		
    		if (jogIndex >= 0) {
				selectedJog = this.dadosJogadores.get(jogIndex);
				
			}else { //caso o usuário não tenha selecionado nada, um erro é exibido
				this.labelMessage.setText("Selecione um Jogador na tabela!");
				return;
			}

    		//verificações
    		if (novoNomeJog.isBlank() && (novaPosicaoJog == null) && (novaSelecaoJog == null)) {
        		labelMessage.setText("Espaços em branco!");
        		return;
    		} else if (JogadorDAOImpl.checarNome(novoNomeJog)) {
				labelMessage.setText("Esse Jogador já está cadastrado!");
				return;
			} else if (novaSelecaoJog != null && novaSelecaoJog.getJogadores().size() == 26) {
				labelMessage.setText("Essa Selecao ja alcancou seu limite de jogadores! (MÁXIMO DE 26 POR SELEÇÃO)");
				return;
			}

    		JogadorDAO.editar(selectedJog.getCodJog(), novoNomeJog, novaPosicaoJog, novaSelecaoJog);
    		
    		labelMessage.setTextFill(Color.GREEN);
        	labelMessage.setText("Jogador Editado com sucesso!");
			
		} catch(Exception e) {
			this.labelMessage.setText("Não foi possível editar o Jogador!");
			System.out.println(e.getMessage());
			return;
		}
		
    	this.attJogadores();
    	
    	//limpando os espaços
    	this.nomeJog.clear();
		this.choicePosicao.setValue(null);
		this.choiceSelJog.setValue(null);
		
	}

	@FXML
	void btnReturnAction(ActionEvent event) throws Exception {
		labelMessage.setText("");
		Parent fxmlJogador1 = FXMLLoader.load(getClass().getResource("/app/view/JogadorPage1.fxml"));
    	Main.trocarTelas(fxmlJogador1);
		//Main.trocarTelas("JogadorPage1");
	}

	@FXML
	void choiceBoxSelecoes(MouseEvent event) {
		nomesSelecoes = SelecaoDAOImpl.getLista2();
		obsSelecoes = FXCollections.observableArrayList(nomesSelecoes);
		choiceSelJog.setItems(obsSelecoes);

		// Avisando ao usuário que não é possível cadastrar um jogador pois não há
		// seleções cadastradas
		if (nomesSelecoes.isEmpty()) {
			labelMessage.setText("Não será possível editar um jogador, pois ainda não há Seleções cadastradas!");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@FXML
	void initialize() {
		assert btnDelJog != null
				: "fx:id=\"btnDelJog\" was not injected: check your FXML file 'Jogador_EditarExcluirPage.fxml'.";
		assert btnEditJog != null
				: "fx:id=\"btnEditJog\" was not injected: check your FXML file 'Jogador_EditarExcluirPage.fxml'.";
		assert btnReturn != null
				: "fx:id=\"btnReturn\" was not injected: check your FXML file 'Jogador_EditarExcluirPage.fxml'.";
		assert choiceSelJog != null
				: "fx:id=\"choiceSelJog\" was not injected: check your FXML file 'Jogador_EditarExcluirPage.fxml'.";
		assert labelMessage != null
				: "fx:id=\"labelMessage\" was not injected: check your FXML file 'Jogador_EditarExcluirPage.fxml'.";
		assert nomeJog != null
				: "fx:id=\"nomeJog\" was not injected: check your FXML file 'Jogador_EditarExcluirPage.fxml'.";
		assert tabelaJogadores != null
				: "fx:id=\"tabelaJogadores\" was not injected: check your FXML file 'Jogador_EditarExcluirPage.fxml'.";

		carregarPosicoes(); //carregando posições na choice box

		// Adicionando as colunas na tabela
		TableColumn nomeCol = new TableColumn("Nome");
		TableColumn posicaoCol = new TableColumn("Posição");
		TableColumn selecaoCol = new TableColumn("Seleção");
		
		
		nomeCol.setCellValueFactory(new PropertyValueFactory<Jogador, String>("nome"));
		posicaoCol.setCellValueFactory(new PropertyValueFactory<Jogador, String>("posicao"));
		selecaoCol.setCellValueFactory(new PropertyValueFactory<Jogador, String>("selecao"));
		
		this.tabelaJogadores.getColumns().addAll(nomeCol, posicaoCol, selecaoCol);
		this.dadosJogadores = FXCollections.observableArrayList(JogadorDAOImpl.getMap().values());
		this.tabelaJogadores.setItems(dadosJogadores);

	}
	
	@FXML
    void attJogadores() {
		this.dadosJogadores = FXCollections.observableArrayList(JogadorDAOImpl.getMap().values());
		this.tabelaJogadores.setItems(dadosJogadores);
    }

}
