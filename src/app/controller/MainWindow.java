package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.Funcoes;
import app.Main;
import app.DadosArquivo.DadosPreCadastro;
import app.model.FaseGrupos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainWindow {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnArbitros;

    @FXML
    private Button btnFaseG;

    @FXML
    private Button btnJogadores;

    @FXML
    private Button btnPreSet;

    @FXML
    private Button btnSelecoes;

    @FXML
    private Button btnTecnicos;
    
    @FXML
    private Label labelMessage;
    
    private static Stage stage;
    
    private static Integer contadorPreSet = 0;

    @FXML
    void goArbitrosPage1(ActionEvent event) throws Exception {
    	Parent fxmlArbitro = FXMLLoader.load(getClass().getResource("/app/view/ArbitroPage.fxml"));
    	Main.trocarTelas(fxmlArbitro);
    }

    @FXML
    void goFaseGruposPage1(ActionEvent event) throws Exception {
    	
    	//verificação se tudo já está cadastrado
    	int verificacoes = Funcoes.verificaçãoFase1();
    	switch (verificacoes) {
		case 1:
			labelMessage.setText("Ainda não é possível ir para a Fase de Grupos, pois não há Seleções cadastradas!");
			break;
		case 2:
			labelMessage.setText("Ainda não é possível ir para a Fase de Grupos, pois o número de Seleções cadastradas é insuficiente!");
			break;
		case 3:
			labelMessage.setText("Ainda não é possível ir para a Fase de Grupos, pois o número de Jogadores cadastrados é insuficiente!");
			break;
		case 4:
			labelMessage.setText("Ainda não é possível ir para a Fase de Grupos, pois o número de Técnicos cadastrados é insuficiente!");
			break;
		case 5:
			
			//abrir popUp para o usuário confirmar			
			FXMLLoader loader = new FXMLLoader();
			URL xmlURL = getClass().getResource("/app/view/popUpFaseG.fxml");
			loader.setLocation(xmlURL);
			Parent parent = loader.load();
			
			Scene scene = new Scene(parent);
			stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			break;		
		}
    }

    @FXML
    void goJogadoresPage1(ActionEvent event) throws Exception {
    	Parent fxmlJogador1 = FXMLLoader.load(getClass().getResource("/app/view/JogadorPage1.fxml"));
    	Main.trocarTelas(fxmlJogador1);
    	//Main.trocarTelas("JogadorPage1"); 	
    }

    @FXML
    void goSelecoesPage1(ActionEvent event) throws Exception { 
    	Parent fxmlSelecoes1 = FXMLLoader.load(getClass().getResource("/app/view/SelecoesPage1.fxml"));
    	Main.trocarTelas(fxmlSelecoes1);
    	//Main.trocarTelas("SelecoesPage1");
    }

    @FXML
    void goTecnicosPage(ActionEvent event) throws Exception {
    	Parent fxmlTecnico = FXMLLoader.load(getClass().getResource("/app/view/TecnicoPage.fxml"));
    	Main.trocarTelas(fxmlTecnico);
    }

    @FXML
    void preSetAction(ActionEvent event) {
    	contadorPreSet = 1;
    	DadosPreCadastro.LeituraArquivos(Main.getSelecaoDAO(), Main.getJogadorDAO(), Main.getGruposCRUD());
    	DadosPreCadastro.LeituraTecnicoArbitro(Main.getSelecaoDAO(), Main.getTecnicoDAO(), Main.getArbitroDAO(), Main.getGruposCRUD());
    	this.btnPreSet.setDisable(true); //o botão é desabilitado pois não pode ser acionado duas vezes o cadastro automático
    	
    }
    
    public static void fecharPopUp() {
    	stage.close();
    }

    @FXML
    void initialize() {
        assert btnArbitros != null : "fx:id=\"btnArbitros\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert btnFaseG != null : "fx:id=\"btnFaseG\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert btnJogadores != null : "fx:id=\"btnJogadores\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert btnPreSet != null : "fx:id=\"btnPreSet\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert btnSelecoes != null : "fx:id=\"btnSelecoes\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert btnTecnicos != null : "fx:id=\"btnTecnicos\" was not injected: check your FXML file 'MainWindow.fxml'.";
        
        //verificação para o botão não ser clicado mais de uma vez
        if (contadorPreSet == 1) {
        	this.btnPreSet.setDisable(true);
        }
       
    }
    
    @FXML
    void selectedBtn1(MouseEvent event) {
    	this.btnSelecoes.setBorder(null);    	
    }
    
    @FXML
    void selectedBtn2(MouseEvent event) {
    	this.btnJogadores.setBorder(null);
    }

    @FXML
    void selectedBtn3(MouseEvent event) {
    	this.btnTecnicos.setBorder(null);
    }

    @FXML
    void selectedBtn4(MouseEvent event) {
    	this.btnArbitros.setBorder(null);
    }
    
    @FXML
    void selectedBtn5(MouseEvent event) {

    }

    @FXML
    void selectedBtn6(MouseEvent event) {

    }

}
