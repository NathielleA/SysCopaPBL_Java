package app.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.Funcoes;
import app.Main;
import app.model.Jogador;
import app.model.Partida;
import app.model.PartidaGerenciar;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class PartidasPage {

	private SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");

	@FXML
	private Button btnAddPart;

	@FXML
	private Button btnListDelJog;

	@FXML
	private Button btnAddGC;

	@FXML
	private Button btnReturn;
	
	@FXML
    private Button btnFinalizarPart;

	@FXML
	private ComboBox<Integer> cartAJog1;

	@FXML
	private ComboBox<Integer> cartAJog2;

	@FXML
	private ComboBox<Integer> cartVJog1;

	@FXML
	private ComboBox<Integer> cartVJog2;

	@FXML
	private ChoiceBox<String> choiceGrupoPart;

	private List<String> grupos = new ArrayList<>();

	private ObservableList<String> obsGrupos;

	@FXML
	private ComboBox<Jogador> choiceJogador1;

	@FXML
	private ComboBox<Jogador> choiceJogador2;

	private List<Jogador> jogadoresTime1 = new ArrayList<>();

	private ObservableList<Jogador> obsJogadores1;

	private List<Jogador> jogadoresTime2 = new ArrayList<>();

	private ObservableList<Jogador> obsJogadores2;

	@FXML
	private ComboBox<String> choicePartida;

	private List<String> partidasGrupo = new ArrayList<>();

	private ObservableList<String> obsPartidas;

	@FXML
	private DatePicker datePickerPart;

	@FXML
	private ComboBox<Integer> golsJog1;

	@FXML
	private ComboBox<Integer> golsJog2;
	
	@FXML
    private HBox hBoxGolsCart;

	@FXML
	private TextField horaPartida;

	@FXML
	private Label labelExibirTime1;

	@FXML
	private Label labelExibirTime2;

	@FXML
	private Label labelMessage;

	@FXML
	private Label labelTotalGols1;

	@FXML
	private Label labelTotalGols2;

	@FXML
	private TextField localPartida;

	private List<Integer> numInteiros = new ArrayList<>();

	private ObservableList<Integer> obsNum;

	private Integer totalGolsTime1 = 0;
	private Integer totalGolsTime2 = 0;

	@FXML
	void btnAddPartAction(ActionEvent event) {
		try {
			labelMessage.setTextFill(Color.RED);

			// tirando os valores informados
			String grupo = this.choiceGrupoPart.getValue();
			String partidaTimes = this.choicePartida.getValue();
			LocalDate getDate = this.datePickerPart.getValue();
			String horario = this.horaPartida.getText();
			String local = this.localPartida.getText();

			// verificação dos dados informados
			if (verificaEspacos()) {
				return;
			}

			// colocando a data no formato brasileiro
			String data = getDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

			// encontrando o objeto seleção dos times da partida informados
			String[] selecoes = partidaTimes.split(" X ");

			Selecao time1 = SelecaoDAOImpl.verificaSelecao(selecoes[0]);
			Selecao time2 = SelecaoDAOImpl.verificaSelecao(selecoes[1]);

			// procurando o objeto da Partida informada pelo usuário na lista de Partidas
			// geradas
			List<Partida> listaDePartidas = FaseGruposPage.getPartidasGeradas().get(grupo);
			Partida partidaEscolhida = null;
			for (Partida partAtual : listaDePartidas) {
				if (partAtual.getTime1().equals(time1.getNome()) && partAtual.getTime2().equals(time2.getNome())) {
					partidaEscolhida = partAtual;
				}
			}

			// setando as informações
			Date dataP = new Date();
			String codPart = sdf.format(dataP);
			partidaEscolhida.setCodPart(codPart);

			partidaEscolhida.setData(data);
			partidaEscolhida.setHorario(horario);
			partidaEscolhida.setLocal(local);

			//a parte dos gols é inserido após os outros dados, em outra função

			PartidaGerenciar.inserir(partidaEscolhida);
			
			// Inserindo partida em todos jogadores.
			Funcoes.inserirPartidaJog(partidaEscolhida, Main.getSelecaoDAO());
			listaDePartidas.remove(partidaEscolhida);

			labelMessage.setTextFill(Color.GREEN);
			labelMessage.setText("Partida inserida! Adicione gols e cartões:");
			hBoxGolsCart.setDisable(false);
			btnAddGC.setDisable(false);

		} catch (Exception e) {
			this.labelMessage.setText("Erro ao cadastrar partida!");
		}
		
	}

	@FXML
	void btnListDelJogAction(ActionEvent event) throws Exception {
		Parent fxmlPartListExc = FXMLLoader.load(getClass().getResource("/app/view/partidaListarExcluirPage.fxml"));
		Main.trocarTelas(fxmlPartListExc);
	}

	@FXML
	void atualizarLabels(MouseEvent event) {
		String partidaTimes = choicePartida.getValue();
		if (partidaTimes != null) {
			String[] selecoes = partidaTimes.split(" X ");

			Selecao time1 = SelecaoDAOImpl.verificaSelecao(selecoes[0]);
			Selecao time2 = SelecaoDAOImpl.verificaSelecao(selecoes[1]);

			// iniciando a inserção de gols e cartões pelos gols da primeira seleção
			this.labelExibirTime1.setText(time1.getNome().toUpperCase());
			this.labelExibirTime2.setText(time2.getNome().toUpperCase());

			carregarJogadores(time1, time2);

			this.labelTotalGols1.setText("Total de Gols: " + totalGolsTime1);
			this.labelTotalGols2.setText("Total de Gols: " + totalGolsTime2);

		}
	}

	
	@FXML
	void exibirPartidasDoGrupo(MouseEvent event) {
		this.partidasGrupo.clear(); // limpa a lista para o comboBox para não exibir as mesmo itens
		carregarPartidas();
		totalGolsTime1 = 0;
		totalGolsTime2 = 0;
	}
	

	@FXML
	void btnReturnAction(ActionEvent event) throws Exception {
		Parent fxmlFaseGrupos = FXMLLoader.load(getClass().getResource("/app/view/FaseGruposPage.fxml"));
		Main.trocarTelas(fxmlFaseGrupos);
	}

	@FXML
	void initialize() {
		carregarGrupos();
		carregarCombosInteger();
		this.golsJog1.setValue(0);
		this.golsJog2.setValue(0);
		this.cartAJog1.setValue(0);
		this.cartAJog2.setValue(0);
		this.cartVJog1.setValue(0);
		this.cartVJog2.setValue(0);
		hBoxGolsCart.setDisable(true);
		btnAddGC.setDisable(true);
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

	public void carregarPartidas() {
		try {

			String grupo = this.choiceGrupoPart.getValue();

			if (grupo != null) {
				List<Partida> partidas = FaseGruposPage.getPartidasGeradas().get(grupo);

				// pegando os times das partidas geradas do grupo para exibir nas opções do
				// choiche box

				for (int i = 0; i < partidas.size(); i++) {
					partidasGrupo.add(partidas.get(i).getTime1() + " X " + partidas.get(i).getTime2());
				}

				obsPartidas = FXCollections.observableArrayList(partidasGrupo);
				choicePartida.setItems(obsPartidas);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void inserirGols(Partida partida, Jogador jogador, Selecao time, Integer gols) {
		if (gols == 0) {		
			return;
		}
		Funcoes.cadastrarGolsPartida(jogador, gols, partida, time);	
	}

	public void inserirCartões(Partida partida, Jogador jogador, Selecao time, Integer cartoesVerm, Integer cartoesAm) {
		//verificações caso os campos estejam com zero, então não precisará cadastrar
		if (cartoesVerm == 0 && cartoesAm == 0) {		
			return;
		} else if (cartoesVerm != 0) {
			Funcoes.cadastrarCartaoVermelho(jogador, cartoesVerm, partida, time);
		} else if (cartoesAm != 0) {
			Funcoes.cadastrarCartaoAmarelo(jogador, cartoesAm, partida, time);
		}

	}

	public void carregarJogadores(Selecao time1, Selecao time2) {
		try {
			if (!jogadoresTime1.isEmpty()) {
				jogadoresTime1.clear();
				jogadoresTime2.clear();
				obsJogadores1.clear();
				obsJogadores2.clear();
			}
			
			jogadoresTime1.add(null);
			jogadoresTime2.add(null);
			
			jogadoresTime1.addAll(time1.getJogadores());
			jogadoresTime2.addAll(time2.getJogadores());

			obsJogadores1 = FXCollections.observableArrayList(jogadoresTime1);
			choiceJogador1.setItems(obsJogadores1);

			obsJogadores2 = FXCollections.observableArrayList(jogadoresTime2);
			choiceJogador2.setItems(obsJogadores2);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void carregarCombosInteger() {
		for (Integer i = 0; i <= 10; i++) {
			this.numInteiros.add(i);
		}

		obsNum = FXCollections.observableArrayList(numInteiros);

		this.golsJog1.setItems(obsNum);
		this.golsJog2.setItems(obsNum);
		this.cartVJog1.setItems(obsNum);
		this.cartVJog2.setItems(obsNum);
		this.cartAJog1.setItems(obsNum);
		this.cartAJog2.setItems(obsNum);
	}

	public void clearAll() {
		this.choiceGrupoPart.setValue(null);
		this.choicePartida.setValue(null);
		this.datePickerPart.setValue(null);
		this.horaPartida.clear();
		this.localPartida.clear();
		this.choiceJogador1.setValue(null);
		this.choiceJogador2.setValue(null);

		this.labelTotalGols1.setText("");
		this.labelTotalGols2.setText("");
		this.labelExibirTime1.setText("");
		this.labelExibirTime2.setText("");
	}

	@FXML
	void btnAddGCAction(ActionEvent event) {
		
			labelMessage.setTextFill(Color.RED);
			
			String partidaTimes = this.choicePartida.getValue();

			//pegando os dados do jogador 
			Jogador jogadorTime1 = choiceJogador1.getValue();
			Integer golsJog1 = this.golsJog1.getValue();
			Integer cartVJog1 = this.cartVJog1.getValue();
			Integer cartAJog1 = this.cartAJog1.getValue();
			
			Jogador jogadorTime2 = choiceJogador2.getValue();
			Integer golsJog2 = this.golsJog2.getValue();
			Integer cartVJog2 = this.cartVJog2.getValue();
			Integer cartAJog2 = this.cartAJog2.getValue();
			
			//verificações
			if (this.choiceGrupoPart.getValue() == null || this.choicePartida.getValue() == null) {
				labelMessage.setText("Selecione uma partida!");
				return;
			} else if (jogadorTime1 == null && jogadorTime2 == null) {
				labelMessage.setText("Selecione um jogador!");
				return;
			} else if (golsJog1 == 0 && cartVJog1 == 0 && cartAJog1 == 0 && golsJog2 == 0 && cartVJog2 == 0 && cartAJog2 == 0) {
				labelMessage.setText("");
				return;
			}
			
			String[] selecoes = partidaTimes.split(" X ");

			Selecao time1 = SelecaoDAOImpl.verificaSelecao(selecoes[0]);
			Selecao time2 = SelecaoDAOImpl.verificaSelecao(selecoes[1]);

			Partida partida = PartidaGerenciar.retornaPartida(time1, time2);
			
			if (jogadorTime1 != null) {
				inserirGols(partida, jogadorTime1, time1, golsJog1);
				inserirCartões(partida, jogadorTime1, time1, cartVJog1, cartAJog1);
				this.totalGolsTime1 += golsJog1;
				this.labelTotalGols1.setText("Total de Gols: " + totalGolsTime1);
				
				//adicionando os gols na partida
				partida.setGolsTime1(totalGolsTime1);
				
			}
			
			if (jogadorTime2 != null) {
				inserirGols(partida, jogadorTime2, time2, golsJog2);
				inserirCartões(partida, jogadorTime2, time2, cartVJog2, cartAJog2);
				this.totalGolsTime2 += golsJog2;
				this.labelTotalGols2.setText("Total de Gols: " + totalGolsTime2);
				
				//adicionando os gols na partida
				partida.setGolsTime2(totalGolsTime2);
			}

			labelMessage.setTextFill(Color.GREEN);
			this.labelMessage.setText("Gols e Cartões inseridos!");	
			
			this.golsJog1.setValue(0);
			this.golsJog2.setValue(0);
			this.cartAJog1.setValue(0);
			this.cartAJog2.setValue(0);
			this.cartVJog1.setValue(0);
			this.cartVJog2.setValue(0);
			this.choiceJogador1.setValue(null);
			this.choiceJogador2.setValue(null);
	}

	@FXML
	void movimentoBtn(MouseEvent event) {
		this.btnAddGC.setTextFill(Color.GREEN);
	}
	
	public boolean verificaEspacos() {
		
		if (this.choiceGrupoPart.getValue() == null || this.choicePartida.getValue() == null || this.datePickerPart.getValue() == null || 
				this.horaPartida.getText().isBlank() || this.localPartida.getText().isBlank()) {
			labelMessage.setText("Há espaços em branco!");
			return true;
		}
		return false;
	}
	
	@FXML
    void btnFinalizarPartAction(ActionEvent event) {
		if (verificaEspacos()) {
			return;
		} else {
			if (this.hBoxGolsCart.isDisable()) {
				labelMessage.setTextFill(Color.RED);
				labelMessage.setText("É necessário inserir a partida antes!");
				return;
			} else {
				labelMessage.setTextFill(Color.GREEN);
				labelMessage.setText("Partida cadastrada com sucesso!");
				clearAll();
				hBoxGolsCart.setDisable(true);
				btnAddGC.setDisable(true);
			}
	
		}
		
    }

}
