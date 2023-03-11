package app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.Main;
import app.model.FaseGrupos;
import app.model.Partida;
import app.model.PartidaGerenciar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class classificacaoGruposPage {

	@FXML
	private Button btnReturn;

	@FXML
	private ChoiceBox<String> choiceGrupoPart;

	private List<String> grupos = new ArrayList<>();

	private ObservableList<String> obsGrupos;

	@FXML
	private Label golsTime1_1;

	@FXML
	private Label golsTime1_2;

	@FXML
	private Label golsTime1_3;

	@FXML
	private Label golsTime1_4;

	@FXML
	private Label golsTime1_5;

	@FXML
	private Label golsTime1_6;

	@FXML
	private Label golsTime2_1;

	@FXML
	private Label golsTime2_2;

	@FXML
	private Label golsTime2_3;

	@FXML
	private Label golsTime2_4;

	@FXML
	private Label golsTime2_5;

	@FXML
	private Label golsTime2_6;

	@FXML
	private Label label3Lugar;

	@FXML
	private Label label4Lugar;

	@FXML
	private Label labelPrimeiroLugar;

	@FXML
	private Label labelSegundoLugar;

	@FXML
	private Label time1_1;

	@FXML
	private Label time1_2;

	@FXML
	private Label time1_3;

	@FXML
	private Label time1_4;

	@FXML
	private Label time1_5;

	@FXML
	private Label time1_6;

	@FXML
	private Label time2_1;

	@FXML
	private Label time2_2;

	@FXML
	private Label time2_3;

	@FXML
	private Label time2_4;

	@FXML
	private Label time2_5;

	@FXML
	private Label time2_6;

	public void exibirInformacoes(MouseEvent event) {
		try {
			String grupo = this.choiceGrupoPart.getValue();

			if (grupo != null) {

				List<Partida> partidas = FaseGrupos.getPartidasGeradas().get(grupo);
				
				for(Partida partida : PartidaGerenciar.getMapPartidas().values()) {
					if(partida.getGrupo().equals(grupo)) {
						
					}
				}

				// exibindo informações nos labels
				time1_1.setText(partidas.get(0).getTime1());
				time2_1.setText(partidas.get(0).getTime2());
				if (PartidaGerenciar.getMapPartidas().containsValue(partidas.get(0))) {
					golsTime1_1.setText("" + partidas.get(0).getGolsTime1());
					golsTime2_1.setText("" + partidas.get(0).getGolsTime1());
				}

				time1_2.setText(partidas.get(1).getTime1());
				time2_2.setText(partidas.get(1).getTime2());
				if (PartidaGerenciar.getMapPartidas().containsValue(partidas.get(1))) {
					golsTime1_2.setText("" + partidas.get(1).getGolsTime1());
					golsTime2_2.setText("" + partidas.get(1).getGolsTime1());
				}

				time1_3.setText(partidas.get(2).getTime1());
				time2_3.setText(partidas.get(2).getTime2());
				if (PartidaGerenciar.getMapPartidas().containsValue(partidas.get(2))) {
					golsTime1_3.setText("" + partidas.get(2).getGolsTime1());
					golsTime2_3.setText("" + partidas.get(2).getGolsTime1());
				}

				time1_4.setText(partidas.get(3).getTime1());
				time2_4.setText(partidas.get(3).getTime2());
				if (PartidaGerenciar.getMapPartidas().containsValue(partidas.get(3))) {
					golsTime1_4.setText("" + partidas.get(3).getGolsTime1());
					golsTime2_4.setText("" + partidas.get(3).getGolsTime1());
				}

				time1_5.setText(partidas.get(4).getTime1());
				time2_5.setText(partidas.get(4).getTime2());
				if (PartidaGerenciar.getMapPartidas().containsValue(partidas.get(4))) {
					golsTime1_5.setText("" + partidas.get(4).getGolsTime1());
					golsTime2_5.setText("" + partidas.get(4).getGolsTime1());
				}

				time1_6.setText(partidas.get(5).getTime1());
				time2_6.setText(partidas.get(5).getTime2());
				if (PartidaGerenciar.getMapPartidas().containsValue(partidas.get(5))) {
					golsTime1_6.setText("" + partidas.get(5).getGolsTime1());
					golsTime2_6.setText("" + partidas.get(5).getGolsTime1());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage() + e.getMessage() + e.getCause());
		}
	}

	@FXML
	void btnReturnAction(ActionEvent event) throws Exception {
		Parent fxmlFaseGrupos = FXMLLoader.load(getClass().getResource("/app/view/FaseGruposPage.fxml"));
		Main.trocarTelas(fxmlFaseGrupos);
	}

	@FXML
	void exibirTotal(MouseEvent event) {

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
		choiceGrupoPart.setItems(obsGrupos);
	}

	@FXML
	void initialize() {
		carregarGrupos();
	}

}
