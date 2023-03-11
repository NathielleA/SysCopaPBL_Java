package app;

import app.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	// declarando os DAO's como atributo para melhor acesso de toda a aplicação
	private static ArbitroDAOImpl ArbitroDAO = new ArbitroDAOImpl();
	private static JogadorDAOImpl JogadorDAO = new JogadorDAOImpl();
	private static SelecaoDAOImpl SelecaoDAO = new SelecaoDAOImpl();
	private static TecnicoDAOImpl TecnicoDAO = new TecnicoDAOImpl();

	private static FaseGrupos GruposCRUD = new FaseGrupos();
	private static PartidaGerenciar PartGerenciar = new PartidaGerenciar();

	private static Stage stage;

	private static Scene MainScene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			stage = primaryStage;
			Parent fxmlMainW = FXMLLoader.load(getClass().getResource("/app/view/MainWindow.fxml"));
			MainScene = new Scene(fxmlMainW);

			primaryStage.setScene(MainScene);
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	public static ArbitroDAOImpl getArbitroDAO() {
		return ArbitroDAO;
	}

	public static JogadorDAOImpl getJogadorDAO() {
		return JogadorDAO;
	}

	public static SelecaoDAOImpl getSelecaoDAO() {
		return SelecaoDAO;
	}

	public static TecnicoDAOImpl getTecnicoDAO() {
		return TecnicoDAO;
	}

	public static FaseGrupos getGruposCRUD() {
		return GruposCRUD;
	}

	public static PartidaGerenciar getPartGerenciar() {
		return PartGerenciar;
	}

	public static void trocarTelas(Parent parent) {
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
