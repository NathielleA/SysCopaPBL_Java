package app.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.Main;
import app.model.Jogador;
import app.model.JogadorDAOImpl;
import app.model.PartidaJogador;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Jogador_InserirPage {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
	private JogadorDAOImpl JogadorDAO = new JogadorDAOImpl();

    @FXML
    private Button btnAddJog;

    @FXML
    private Button btnReturn;

    @FXML
    private ChoiceBox<String> choicePosicao;
    
    private List<String> posicoes = new ArrayList<>();
    
    private ObservableList<String> obsPosicoes;

    @FXML
    private ComboBox<String> choiceSelJog;
    
    private List<String> nomesSelecoes = new ArrayList<>();
    
    private ObservableList<String> obsSelecoes;

    @FXML
    private Label labelMessage;

    @FXML
    private TextField nomeJog;

    @FXML
    void btnAddJogAction(ActionEvent event) {
    	try {
    		labelMessage.setTextFill(Color.RED);
    		String nomeJogador = this.nomeJog.getText();
    		String posicaoJog = this.choicePosicao.getValue();
    		Selecao selecaoJog = SelecaoDAOImpl.verificaSelecao(this.choiceSelJog.getValue());
    		
    		//verificando se os espaços estão vazios, se esse jogador já está cadastrado ou se já alcançou o total de 26 jogadores por seleção
    		if (nomeJogador.isBlank() || posicaoJog == null || selecaoJog == null) {
        		labelMessage.setText("Espaços em branco!");
        		return;
    		} else if (JogadorDAOImpl.checarNome(nomeJogador)) {
				labelMessage.setText("Esse Jogador já está cadastrado!");
				return;
			} else if (selecaoJog.getJogadores().size() == 26) {
				labelMessage.setText("Essa Selecao já alcancou seu limite de jogadores! (MÁXIMO DE 26 POR SELEÇÃO)");
				return;
			}
    		
    		Date data = new Date();
			Jogador novoJogador = new Jogador(sdf.format(data), nomeJogador, posicaoJog, selecaoJog);
			
			ArrayList<PartidaJogador> listaJogPart = new ArrayList<>();
			novoJogador.setPartidasJogador(listaJogPart);
    		
    		this.JogadorDAO.inserir(novoJogador);
    		selecaoJog.setJogadores(novoJogador);
    		
    		labelMessage.setTextFill(Color.GREEN);
        	labelMessage.setText("Jogador Inserido com Sucesso! NUMERO DO ID: " + novoJogador.getCodJog()); 

		} catch (Exception e) {
			this.labelMessage.setText("Não foi possível cadastrar o Jogador!");
		}
    	
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
    	
    	//Avisando ao usuário que não é possível cadastrar um jogador pois não há seleções cadastradas
    	if (nomesSelecoes.isEmpty()) {
    		labelMessage.setText("Não será possível cadastrar um jogador, pois ainda não há Seleções cadastradas!");
    	}
    }
    
    @FXML
    void initialize() {
    	carregarPosicoes();
   	
    }
    
    public void carregarPosicoes() {
    	posicoes.add("Goleiro");
    	posicoes.add("Zagueiro");
    	posicoes.add("Meia");
    	posicoes.add("Atacante");
    	
    	obsPosicoes = FXCollections.observableArrayList(posicoes);
    	choicePosicao.setItems(obsPosicoes);
    }

}
