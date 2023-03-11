package app.model;

import java.util.*;
import app.model.*;

public class SemiFinal {

	public static Map<Integer, Partida> prePartidasSemi = new HashMap<Integer, Partida>();
	public static Map<Integer, Partida> mapPartidasSemi = new HashMap<Integer, Partida>();

	public static void gerarPreSemi() {

		for (Map.Entry<Integer, Partida> entry : Quartas.getPartidasQuartas().entrySet()) {

			Integer numeroDaPartida = entry.getKey();

			switch (numeroDaPartida) {

			case 1:
				for (Map.Entry<Integer, Partida> entry2 : Quartas.getPartidasQuartas().entrySet()) {
					if (entry2.getKey().equals(2)) {
						Partida novaPartidaQuartas = new Partida(entry.getValue().getGanhador().getNome(),
								entry2.getValue().getGanhador().getNome());
						prePartidasSemi.put(1, novaPartidaQuartas);
					}
				}
				break;

			case 3:
				for (Map.Entry<Integer, Partida> entry2 : Quartas.getPartidasQuartas().entrySet()) {
					if (entry2.getKey().equals(4)) {
						Partida novaPartidaQuartas = new Partida(entry.getValue().getGanhador().getNome(),
								entry2.getValue().getGanhador().getNome());
						prePartidasSemi.put(2, novaPartidaQuartas);
					}
				}
				break;

			}

		}

	}

	public static void inserir(Partida partida, Integer numPartida) {

		mapPartidasSemi.put(numPartida, partida);
		prePartidasSemi.remove(numPartida);

	}

	public static void excluir(Partida partida, Integer numPartida) {
		// chamando a função que retira os dados da partida dos jogadores

		// retirando os dados do objeto excluído
		partida.setData(null);
		partida.setGolsTime1(null);
		partida.setGolsTime2(null);
		partida.setHorario(null);
		partida.setLocal(null);

		mapPartidasSemi.remove(numPartida);

		// retornando a partida para a lista de partidas geradas para ser cadastrada
		// novamente
		prePartidasSemi.put(numPartida, partida);

	}
	
	public static Map<Integer, Partida> getPrePartidasSemi() {
		return prePartidasSemi;
	}

	public static Map<Integer, Partida> getPartidasSemi() {
		return mapPartidasSemi;
	}

}
