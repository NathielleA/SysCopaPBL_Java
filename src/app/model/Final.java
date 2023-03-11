package app.model;

import java.util.*;

public class Final {

	public static Map<Integer, Partida> preMapFinal = new HashMap<Integer, Partida>();
	public static Map<Integer, Partida> mapFinal = new HashMap<Integer, Partida>();

	public static void gerarPreFinal() {

		Selecao timeGanhador1 = SemiFinal.getPartidasSemi().get(1).getGanhador();
		Selecao timeGanhador2 = SemiFinal.getPartidasSemi().get(2).getGanhador();
		Selecao timePerdedor1 = SemiFinal.getPartidasSemi().get(1).getPerdedor();
		Selecao timePerdedor2 = SemiFinal.getPartidasSemi().get(2).getPerdedor();

		Partida partidaFinal = new Partida(timeGanhador1.getNome(), timeGanhador2.getNome());
		Partida terceiroLugar = new Partida(timePerdedor1.getNome(), timePerdedor2.getNome());
		preMapFinal.put(1, partidaFinal);
		preMapFinal.put(2, terceiroLugar);

	}

	public static void inserir(Partida partida, Integer numPartida) {

		mapFinal.put(numPartida, partida);
		preMapFinal.remove(numPartida);

	}

	public static void excluir(Partida partida, Integer numPartida) {
		// chamando a função que retira os dados da partida dos jogadores

		// retirando os dados do objeto excluído
		partida.setData(null);
		partida.setGolsTime1(null);
		partida.setGolsTime2(null);
		partida.setHorario(null);
		partida.setLocal(null);

		mapFinal.remove(numPartida);

		// retornando a partida para a lista de partidas geradas para ser cadastrada
		// novamente
		preMapFinal.put(numPartida, partida);

	}
	
	public static Map<Integer, Partida> getPreMapFinal() {
		return preMapFinal;
	}

	public static Map<Integer, Partida> getMapFinal() {
		return mapFinal;
	}

}
