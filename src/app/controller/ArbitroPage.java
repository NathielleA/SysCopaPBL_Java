package app.controller;

import app.Main;
import app.model.Arbitro;
import app.model.ArbitroDAOImpl;
import app.model.Jogador;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class ArbitroPage {

    @FXML
    private Button btnAddArb;

    @FXML
    private Button btnDelArb;

    @FXML
    private Button btnEditArb;

    @FXML
    private Button btnReturn;

    @FXML
    private Label labelMessage;

    @FXML
    private TextField nomeArb;

    @FXML
    private TableView<Arbitro> tabelaArbitros;
    
    private ObservableList<Arbitro> dadosArbitros;

    @FXML
    void btnAddArbAction(ActionEvent event) {
    	try {
    		labelMessage.setTextFill(Color.RED);
    		String nomeArbitro = this.nomeArb.getText();
    		
    		//verificações
    		if (nomeArbitro.isBlank()) {
        		labelMessage.setText("Espaços em branco!");
        		return;
    		} else if (ArbitroDAOImpl.checarNome(nomeArbitro)) {
				labelMessage.setText("Esse Árbitro já está cadastrado!");
				return;
			}
    		
    		Arbitro novoArbitro = new Arbitro(nomeArbitro);
    		
    		Main.getArbitroDAO().inserir(novoArbitro);
    		
    		labelMessage.setTextFill(Color.GREEN);
        	labelMessage.setText("Árbitro Inserido com Sucesso!"); 

		} catch (Exception e) {
			this.labelMessage.setText("Não foi possível cadastrar o Árbitro!");
		}
    	
    	this.nomeArb.clear();
    	this.attArbitros();
    }

    @FXML
    void btnDelArbAction(ActionEvent event) {
    	try {
			labelMessage.setTextFill(Color.RED);
			int arbIndex = this.tabelaArbitros.getSelectionModel().getSelectedIndex();
			
			if (arbIndex >= 0) {
				Arbitro selectedArb = this.dadosArbitros.get(arbIndex);
				
				Main.getArbitroDAO().excluir(selectedArb.getNome());
				
			} else { //caso o usuário não tenha selecionado nada, um erro é exibido
				this.labelMessage.setText("Selecione um Árbitro na tabela!");
				return;
			}
			
			labelMessage.setTextFill(Color.GREEN);
	    	labelMessage.setText("Árbitro Excluído com sucesso!");
			
		} catch(Exception e) {
			this.labelMessage.setText("Não foi possível excluir o Árbitro!");
			return;
		}
		
    	this.attArbitros();
    }

    @FXML
    void btnEditArbAction(ActionEvent event) {
    	try {
			labelMessage.setTextFill(Color.RED);
			int arbIndex = this.tabelaArbitros.getSelectionModel().getSelectedIndex();
			Arbitro selectedArb;
			
			String novoNomeArb = this.nomeArb.getText();
			
			if (arbIndex >= 0) {
				selectedArb = this.dadosArbitros.get(arbIndex);
				
			} else { //caso o usuário não tenha selecionado nada, um erro é exibido
				this.labelMessage.setText("Selecione um Árbitro na tabela!");
				return;
			}
			
			//verificações
    		if (novoNomeArb.isBlank()) {
        		labelMessage.setText("Espaços em branco!");
        		return;
    		} else if (ArbitroDAOImpl.checarNome(novoNomeArb)) {
				labelMessage.setText("Esse Árbitro já está cadastrado!");
				return;
			}
			
    		Main.getArbitroDAO().editar(selectedArb.getNome(), novoNomeArb);
			labelMessage.setTextFill(Color.GREEN);
	    	labelMessage.setText("Árbitro Editado com sucesso!");
			
		} catch(Exception e) {
			this.labelMessage.setText("Não foi possível editar o Árbitro!");
			return;
		}
		
		this.nomeArb.clear();
    	this.attArbitros();
    }

    @FXML
    void btnReturnAction(ActionEvent event) throws Exception {
    	Parent fxmlMainW = FXMLLoader.load(getClass().getResource("/app/view/MainWindow.fxml"));
    	Main.trocarTelas(fxmlMainW);
    }

    @FXML
    void attArbitros() {
    	this.dadosArbitros = FXCollections.observableArrayList(ArbitroDAOImpl.getLista1());
		this.tabelaArbitros.setItems(dadosArbitros);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @FXML
    void initialize() {
        
        // Adicionando as colunas na tabela
     	TableColumn nomeCol = new TableColumn("Nome");

     	nomeCol.setCellValueFactory(new PropertyValueFactory<Jogador, String>("nome"));

     	this.tabelaArbitros.getColumns().addAll(nomeCol);
     	attArbitros();
    }

}
