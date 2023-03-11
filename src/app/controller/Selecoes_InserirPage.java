package app.controller;

import java.util.ArrayList;
import java.util.List;
import app.Main;
import app.model.FaseGrupos;
import app.model.Jogador;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Selecoes_InserirPage {
	
	protected static SelecaoDAOImpl SelecaoDAO = new SelecaoDAOImpl();
	protected static FaseGrupos GruposCRUD = new FaseGrupos();

    @FXML
    private Button btnAddSel;

    @FXML
    private Button btnReturn;
    
    @FXML
    private Label labelMessage;

    @FXML
    private ChoiceBox<String> choiceGrupoSel;

    @FXML
    private TextField nomeSel;
    
    private List<String> grupos = new ArrayList<>();
    
    private ObservableList<String> obsGrupos;

    @FXML
    void btnAddSelAction(ActionEvent event) {
    	try {
    		labelMessage.setTextFill(Color.RED);
    		String nomeSelecao = this.nomeSel.getText();
    		String grupoSel = this.choiceGrupoSel.getValue();
    		List<Jogador> jogadoresList = new ArrayList<>();
    		
    		//verificando se os espaços estão vazios, se o grupo informado já está completo (com 4 seleções) e se o nome é repetido
    		if (nomeSelecao.isBlank() || grupoSel == null) {
        		labelMessage.setText("Espaços em branco!");
        		return;
    		} else if (GruposCRUD.verificaGrupos(grupoSel)) {
				labelMessage.setText("Esse Grupo já está completo!");
				return;
			} else if (SelecaoDAOImpl.checarNome(nomeSelecao)) {
				labelMessage.setText("Essa Selecao já está cadastrada!");
				return;
			}
    		
    		Selecao novaSelecao = new Selecao(nomeSelecao, jogadoresList, grupoSel);
    		
    		SelecaoDAO.inserir(novaSelecao);
    		GruposCRUD.atualizaGrupos(grupoSel, novaSelecao); 
    		
    		labelMessage.setTextFill(Color.GREEN);
        	labelMessage.setText("Seleção Inserida com Sucesso!");

		} catch (Exception e) {
			this.labelMessage.setText("Não foi possível cadastrar a Seleção!");
		}  	
    	
    	this.nomeSel.clear();
		this.choiceGrupoSel.setValue(null);
    }

    @FXML
    void btnReturnAction(ActionEvent event) throws Exception {
    	labelMessage.setText("");//Limpando o label de erro do cadastro da seleção
    	Parent fxmlSelecoes1 = FXMLLoader.load(getClass().getResource("/app/view/SelecoesPage1.fxml"));
    	Main.trocarTelas(fxmlSelecoes1);
    	//Main.trocarTelas("SelecoesPage1");
    }
    
    @FXML
    void initialize() {
    	carregarGrupos();
    }
    
    public void carregarGrupos() {
    	grupos.add("A");
    	grupos.add("B");
    	grupos.add("C");
    	grupos.add("D");
    	grupos.add("E");
    	grupos.add("F");
    	grupos.add("G");
    	grupos.add("H");
    	
    	obsGrupos = FXCollections.observableArrayList(grupos);
    	choiceGrupoSel.setItems(obsGrupos);
    }
}
