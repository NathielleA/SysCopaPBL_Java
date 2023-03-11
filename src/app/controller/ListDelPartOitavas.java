package app.controller;

import app.Main;
import app.model.Oitavas;
import app.model.Partida;
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

public class ListDelPartOitavas {

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
		
		int partidaIndex = this.tabelaPartidas.getSelectionModel().getSelectedIndex();
		
		if (partidaIndex >= 0) {
			Partida selectedPart = this.dadosPartidas.get(partidaIndex);
			
			Integer numPart = null;
			
			for (Integer num : Oitavas.getPartidasOitavas().keySet()) {
				if (Oitavas.getPartidasOitavas().get(num).equals(selectedPart)) {
					numPart = num;
				}
			}
			
			Oitavas.excluir(selectedPart, numPart);
			
		} else { //caso o usuário não tenha selecionado nada, um erro é exibido
			this.labelMessage.setText("Selecione uma partida na tabela!");
			return;
		}
		
		labelMessage.setTextFill(Color.GREEN);
		labelMessage.setText("Partida excluída com sucesso!");

		this.attTabela();
    }
    
    public void attTabela() {
    	//exibindo os dados na tabela
     	this.dadosPartidas = FXCollections.observableArrayList(Oitavas.getPartidasOitavas().values());
		this.tabelaPartidas.setItems(dadosPartidas);

		labelTotalPart.setText("Total: " + dadosPartidas.size());
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
    void initialize() {
    	// Adicionando as colunas na tabela
     	TableColumn time1Col = new TableColumn("Time 1");
     	TableColumn gols1Col = new TableColumn("Gols");
     	TableColumn time2Col = new TableColumn("Time 2");
     	TableColumn gols2Col = new TableColumn("Gols");
     	TableColumn ganhadorCol = new TableColumn("Ganhador");
     	TableColumn dataCol = new TableColumn("Data");
     	TableColumn horarioCol = new TableColumn("Horário");
     	TableColumn localCol = new TableColumn("Local");
     	

     	time1Col.setCellValueFactory(new PropertyValueFactory<Partida, String>("time1"));
     	gols1Col.setCellValueFactory(new PropertyValueFactory<Partida, Integer>("golsTime1"));
     	time2Col.setCellValueFactory(new PropertyValueFactory<Partida, String>("time2"));
     	gols2Col.setCellValueFactory(new PropertyValueFactory<Partida, Integer>("golsTime2"));
     	ganhadorCol.setCellValueFactory(new PropertyValueFactory<Partida, Integer>("ganhador"));
     	dataCol.setCellValueFactory(new PropertyValueFactory<Partida, String>("data"));
     	horarioCol.setCellValueFactory(new PropertyValueFactory<Partida, String>("horario"));
     	localCol.setCellValueFactory(new PropertyValueFactory<Partida, String>("local"));

     	this.tabelaPartidas.getColumns().addAll(time1Col, gols1Col, time2Col, gols2Col, ganhadorCol, dataCol, horarioCol, localCol);
     	this.attTabela();
    }

    @FXML
    void btnReturnAction(ActionEvent event) throws Exception {
    	Parent fxmlOitavas = FXMLLoader.load(getClass().getResource("/app/view/PartidasOitavasPage.fxml"));
    	Main.trocarTelas(fxmlOitavas);
    }

}
