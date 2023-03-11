package app.controller;

import java.util.List;
import java.util.Map;

import app.Main;
import app.model.FaseGrupos;
import app.model.Partida;
import app.model.PartidaGerenciar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FaseGruposPage {
	
	private static Map<String, List<Partida>> PartidasGeradas = FaseGrupos.gerarPartidas();;

	@FXML
    private Button btnClassifica;
	
	@FXML
    private Button btnEliminatorias;

    @FXML
    private Button btnList;

    @FXML
    private Button btnPartidas;
    
    @FXML
    private Label labelMessage;
    
    private static Stage stage;
    
    @FXML
    void btnClassificaAction(ActionEvent event) throws Exception {
    	Parent fxmlClassificacao = FXMLLoader.load(getClass().getResource("/app/view/classificacaoGruposPage.fxml"));
    	Main.trocarTelas(fxmlClassificacao);
    }

    @FXML
    void btnEliminatoriasAction(ActionEvent event) throws Exception {
    	Integer totalGeral, totalPartidas, faltam;
    	
    	totalGeral = 48; //pois são 48 partidas para completar a fase de grupos (8 grupos X 6 partidas = 48)
    	totalPartidas = PartidaGerenciar.getMapPartidas().size();
    	faltam = totalGeral - totalPartidas;
    	
    	//chamar AQUI NO IF função de verificação PartidaGerenciar - todasPartidasCadastradas, OU DEIXAR ASSIM QUE TBM FUNCIONA
    	if (faltam != 0) {
    		labelMessage.setText("Não é possível ir para as Fases Eliminatórias, pois ainda faltam cadastrar " + faltam + " partidas!");
  
    	} else {
    		//abrir popUp para o usuário confirmar			
			Parent fxmlPopUpEl = FXMLLoader.load(getClass().getResource("/app/view/popUpEliminatorias.fxml"));
			Scene scene = new Scene(fxmlPopUpEl);
			
			stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
    	}

    }

    @FXML
    void btnListAction(ActionEvent event) throws Exception {
    	Parent fxmlListagens = FXMLLoader.load(getClass().getResource("/app/view/ListagensPage.fxml"));
    	Main.trocarTelas(fxmlListagens);
    }

    @FXML
    void btnPartidasAction(ActionEvent event) throws Exception {
    	Parent fxmlPartidas = FXMLLoader.load(getClass().getResource("/app/view/PartidasPage.fxml"));
    	Main.trocarTelas(fxmlPartidas);
    }

	public static Map<String, List<Partida>> getPartidasGeradas() {
		return PartidasGeradas;
	}
	
	public static void fecharPopUp2() {
    	stage.close();
    }

}
