package app.controller;

import app.Main;
import app.model.Partida;
import app.model.PartidaGerenciar;
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
import javafx.scene.paint.Color;

public class PartidaListarExcluirPage {

    @FXML
    private Button btnDelPart;

    @FXML
    private Button btnReturn;

    @FXML
    private Label labelMessage;

    @FXML
    private Label labelTotalPart;

    @FXML
	private TableView<Partida> tabelaPartidas;
	
	private ObservableList<Partida> dadosPartidas;

	@FXML
	void btnDelPartAction(ActionEvent event) {
		
			labelMessage.setTextFill(Color.RED);
			
			Partida selectedPart = this.tabelaPartidas.getSelectionModel().getSelectedItem();
			
			if (selectedPart != null) {
				
				PartidaGerenciar.excluir(selectedPart);
				dadosPartidas.remove(selectedPart);
				
			} else { //caso o usuário não tenha selecionado nada, um erro é exibido
				this.labelMessage.setText("Selecione uma partida na tabela!");
				return;
			}
			
			labelMessage.setTextFill(Color.GREEN);
			labelMessage.setText("Partida excluída com sucesso!");

			this.attTabela();
	}

    @FXML
    void btnReturnAction(ActionEvent event) throws Exception {
    	Parent fxmlPartidas = FXMLLoader.load(getClass().getResource("/app/view/PartidasPage.fxml"));
    	Main.trocarTelas(fxmlPartidas);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	void initialize() {
		
		// Adicionando as colunas na tabela
     	TableColumn time1Col = new TableColumn("Time 1");
     	TableColumn gols1Col = new TableColumn("Gols");
     	TableColumn time2Col = new TableColumn("Time 2");
     	TableColumn gols2Col = new TableColumn("Gols");
     	TableColumn dataCol = new TableColumn("Data");
     	TableColumn horarioCol = new TableColumn("Horário");
     	TableColumn localCol = new TableColumn("Local");
     	TableColumn grupoCol = new TableColumn("Grupo");
     	

     	time1Col.setCellValueFactory(new PropertyValueFactory<Partida, String>("time1"));
     	gols1Col.setCellValueFactory(new PropertyValueFactory<Partida, Integer>("golsTime1"));
     	time2Col.setCellValueFactory(new PropertyValueFactory<Partida, String>("time2"));
     	gols2Col.setCellValueFactory(new PropertyValueFactory<Partida, Integer>("golsTime2"));
     	dataCol.setCellValueFactory(new PropertyValueFactory<Partida, String>("data"));
     	horarioCol.setCellValueFactory(new PropertyValueFactory<Partida, String>("horario"));
     	localCol.setCellValueFactory(new PropertyValueFactory<Partida, String>("local"));
     	grupoCol.setCellValueFactory(new PropertyValueFactory<Partida, String>("grupo"));

     	this.tabelaPartidas.getColumns().addAll(time1Col, gols1Col, time2Col, gols2Col, dataCol, horarioCol, localCol, grupoCol);
     	this.attTabela();
     	
		
	}
    
    public void attTabela() {
    	//exibindo os dados na tabela
     	this.dadosPartidas = FXCollections.observableArrayList(PartidaGerenciar.getMapPartidas().values());
		this.tabelaPartidas.setItems(dadosPartidas);

		labelTotalPart.setText("Total: " + dadosPartidas.size());
    }

}
