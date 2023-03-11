package app.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.Main;
import app.model.Jogador;
import app.model.Selecao;
import app.model.SelecaoDAOImpl;
import app.model.Tecnico;
import app.model.TecnicoDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class TecnicosPage {
	
	private static TecnicoDAOImpl TecnicoDAO = new TecnicoDAOImpl();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAddTec;

    @FXML
    private Button btnDelTec;

    @FXML
    private Button btnEditTec;

    @FXML
    private Button btnReturn1;

    @FXML
	private ComboBox<String> choiceSelTec;
    
    private List<String> nomesSelecoes = new ArrayList<>();
    
    private ObservableList<String> obsSelecoes;

    @FXML
    private Label labelMessage;

    @FXML
    private TextField nomeTec;

    @FXML
	private TableView<Tecnico> tabelaTecnicos;

	private ObservableList<Tecnico> dadosTecnicos;

	@FXML
    void btnAddTecAction(ActionEvent event) {
    	try {
    		labelMessage.setTextFill(Color.RED);
    		String nomeTecnico = this.nomeTec.getText();
    		String nomeSelecao = this.choiceSelTec.getValue();
    		Selecao selecaoTec = SelecaoDAOImpl.verificaTecnico(nomeSelecao);
    		
    		//verificações
    		if (nomeTecnico.isBlank() || nomeSelecao == null) {
        		labelMessage.setText("Espaços em branco!");
        		return;
    		} else if (TecnicoDAOImpl.checarNome(nomeTecnico)) {
				labelMessage.setText("Esse Técnico já está cadastrado!");
				return;
			} else if (selecaoTec == null) {
				labelMessage.setText("Essa Selecao já possui um Técnico!");
				return;
			}
    		
    		Tecnico novoTecnico = new Tecnico(nomeTecnico);
    		
    		TecnicoDAO.inserir(novoTecnico);
    		selecaoTec.setTecnico(novoTecnico);
    		
    		labelMessage.setTextFill(Color.GREEN);
        	labelMessage.setText("Técnico Inserido com Sucesso!"); 

		} catch (Exception e) {
			this.labelMessage.setText("Não foi possível cadastrar o Técnico!");
		}
    	
    	this.nomeTec.clear();
		this.choiceSelTec.setValue(null);
    }

    @FXML
	void btnDelTecAction(ActionEvent event) {
		
		try {
			labelMessage.setTextFill(Color.RED);
			int tecIndex = this.tabelaTecnicos.getSelectionModel().getSelectedIndex();
			
			if (tecIndex >= 0) {
				Tecnico selectedTec = this.dadosTecnicos.get(tecIndex);
				
				TecnicoDAO.excluir(selectedTec.getNome());
				
			} else { //caso o usuário não tenha selecionado nada, um erro é exibido
				this.labelMessage.setText("Selecione um Técnico na tabela!");
				return;
			}
			
			labelMessage.setTextFill(Color.GREEN);
	    	labelMessage.setText("Técnico Excluído com sucesso!");
			
		} catch(Exception e) {
			this.labelMessage.setText("Não foi possível excluir o Técnico!");
			return;
		}
		
    	this.attTecnicos();
	}

    @FXML
	void btnEditTecAction(ActionEvent event) {
		try {
			labelMessage.setTextFill(Color.RED);
			int tecIndex = this.tabelaTecnicos.getSelectionModel().getSelectedIndex();
			Tecnico selectedTec;
			
			String novoNomeTec = this.nomeTec.getText();
    		String nomeSelecao = this.choiceSelTec.getValue();
    		Selecao novaSelecaoTec = SelecaoDAOImpl.verificaTecnico(nomeSelecao);
			
			if (tecIndex >= 0) {
				selectedTec = this.dadosTecnicos.get(tecIndex);
				
			} else { //caso o usuário não tenha selecionado nada, um erro é exibido
				this.labelMessage.setText("Selecione um Técnico na tabela!");
				return;
			}
			
			if (novoNomeTec.isBlank() && nomeSelecao == null) {
        		labelMessage.setText("Espaços em branco!");
        		return;
    		} else if (TecnicoDAOImpl.checarNome(novoNomeTec)) {
				labelMessage.setText("Esse Técnico já está cadastrado!");
				return;
			} else if (nomeSelecao != null && novaSelecaoTec == null) {
				labelMessage.setText("Essa Selecao já possui um Técnico!");
				return;
			}
			
			TecnicoDAO.editar(selectedTec.getNome(), novoNomeTec, novaSelecaoTec);
			labelMessage.setTextFill(Color.GREEN);
	    	labelMessage.setText("Técnico Editado com sucesso!");
			
		} catch(Exception e) {
			this.labelMessage.setText("Não foi possível editar o Técnico!");
			return;
		}
		
		this.nomeTec.clear();
    	this.attTecnicos();
	}

    @FXML
    void btnReturnAction(ActionEvent event) throws Exception {
    	Parent fxmlMainW = FXMLLoader.load(getClass().getResource("/app/view/MainWindow.fxml"));
    	Main.trocarTelas(fxmlMainW);
    }

    @FXML
    void choiceBoxSelecoes(MouseEvent event) {
    	nomesSelecoes = SelecaoDAOImpl.getLista2();
    	obsSelecoes = FXCollections.observableArrayList(nomesSelecoes);
    	choiceSelTec.setItems(obsSelecoes);
    	
    	//Avisando ao usuário que não é possível cadastrar um Técnico pois não há seleções cadastradas
    	if (nomesSelecoes.isEmpty()) {
    		labelMessage.setText("Ainda não há Seleções cadastradas!");
    	}
    }

    @FXML
	void attTecnicos() {
		this.dadosTecnicos = FXCollections.observableArrayList(TecnicoDAOImpl.getLista1());
		this.tabelaTecnicos.setItems(dadosTecnicos);

	}

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @FXML
    void initialize() {
        assert btnAddTec != null : "fx:id=\"btnAddTec\" was not injected: check your FXML file 'TecnicoPage.fxml'.";
        assert btnDelTec != null : "fx:id=\"btnDelTec\" was not injected: check your FXML file 'TecnicoPage.fxml'.";
        assert btnEditTec != null : "fx:id=\"btnEditTec\" was not injected: check your FXML file 'TecnicoPage.fxml'.";
        assert btnReturn1 != null : "fx:id=\"btnReturn1\" was not injected: check your FXML file 'TecnicoPage.fxml'.";
        assert choiceSelTec != null : "fx:id=\"choiceSelTec\" was not injected: check your FXML file 'TecnicoPage.fxml'.";
        assert labelMessage != null : "fx:id=\"labelMessage\" was not injected: check your FXML file 'TecnicoPage.fxml'.";
        assert nomeTec != null : "fx:id=\"nomeTec\" was not injected: check your FXML file 'TecnicoPage.fxml'.";
        assert tabelaTecnicos != null : "fx:id=\"tabelaTecnicos\" was not injected: check your FXML file 'TecnicoPage.fxml'.";
        
        // Adicionando as colunas na tabela
     	TableColumn nomeCol = new TableColumn("Nome");
     	TableColumn selecaoCol = new TableColumn("Seleção");

     	nomeCol.setCellValueFactory(new PropertyValueFactory<Jogador, String>("nome"));
    	selecaoCol.setCellValueFactory(new PropertyValueFactory<Jogador, String>("selecao"));

     	this.tabelaTecnicos.getColumns().addAll(nomeCol, selecaoCol);
     	attTecnicos();
    }

}
