package app.model;

import java.util.*;
import app.*;

public class Quartas {

	public static Map<Integer, Partida> prePartidasQuartas = new HashMap<Integer, Partida>();
	public static Map<Integer, Partida> mapPartidasQuartas = new HashMap<Integer, Partida>();

	public static void gerarPreQuartas() {
		
		for (Map.Entry<Integer, Partida> entry : Oitavas.getPartidasOitavas().entrySet()) {

			Integer numeroDaPartida = entry.getKey();

			switch (numeroDaPartida) {

			case 1:
				for (Map.Entry<Integer, Partida> entry2 : Oitavas.getPartidasOitavas().entrySet()) {
					if (entry2.getKey().equals(3)) {
						Partida novaPartidaQuartas = new Partida(entry.getValue().getGanhador().getNome(),
								entry2.getValue().getGanhador().getNome());
						prePartidasQuartas.put(1, novaPartidaQuartas);
					}
				}
				break;

			case 5:
				for (Map.Entry<Integer, Partida> entry2 : Oitavas.getPartidasOitavas().entrySet()) {
					if (entry2.getKey().equals(7)) {
						Partida novaPartidaQuartas = new Partida(entry.getValue().getGanhador().getNome(),
								entry2.getValue().getGanhador().getNome());
						prePartidasQuartas.put(2, novaPartidaQuartas);
					}
				}
				break;

			case 2:
				for (Map.Entry<Integer, Partida> entry2 : Oitavas.getPartidasOitavas().entrySet()) {
					if (entry2.getKey().equals(4)) {
						Partida novaPartidaQuartas = new Partida(entry.getValue().getGanhador().getNome(),
								entry2.getValue().getGanhador().getNome());
						prePartidasQuartas.put(3, novaPartidaQuartas);
					}
				}
				break;

			case 6:
				for (Map.Entry<Integer, Partida> entry2 : Oitavas.getPartidasOitavas().entrySet()) {
					if (entry2.getKey().equals(8)) {
						Partida novaPartidaQuartas = new Partida(entry.getValue().getGanhador().getNome(),
								entry2.getValue().getGanhador().getNome());
						prePartidasQuartas.put(4, novaPartidaQuartas);
					}
				}
				break;

			}

		}

	}

	public static void inserir(Partida partida, Integer numPartida) {

		mapPartidasQuartas.put(numPartida, partida);
		prePartidasQuartas.remove(numPartida);

	}

	public static void excluir(Partida partida, Integer numPartida) {
		// chamando a função que retira os dados da partida dos jogadores

		// retirando os dados do objeto excluído
		partida.setData(null);
		partida.setGolsTime1(null);
		partida.setGolsTime2(null);
		partida.setHorario(null);
		partida.setLocal(null);

		mapPartidasQuartas.remove(numPartida);

		// retornando a partida para a lista de partidas geradas para ser cadastrada
		// novamente
		prePartidasQuartas.put(numPartida, partida);

	}
	
	public static Map<Integer, Partida> getPrePartidasQuartas() {
		return prePartidasQuartas;
	}

	public static Map<Integer, Partida> getPartidasQuartas() {
		return mapPartidasQuartas;
	}
}
