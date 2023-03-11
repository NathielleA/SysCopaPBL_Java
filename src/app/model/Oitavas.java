package app.model;

import java.util.*;

import app.*;

public class Oitavas {

	public static Map<String, Map<Selecao, Integer>> selecoesOrdenadas = FaseGrupos.gerarMapPontuacao();
	public static List<List<Selecao>> selecoesClassificadas = new ArrayList<List<Selecao>>();
	public static Map<Integer, Partida> prePartidas = new HashMap<Integer, Partida>();
	public static Map<Integer, Partida> mapPartidasOitavas = new HashMap<Integer, Partida>();

	public static List<List<Selecao>> gerarFase2() {

		selecoesClassificadas.add(FaseGrupos.organizaSelecoesGrupo("A"));
		selecoesClassificadas.add(FaseGrupos.organizaSelecoesGrupo("B"));
		selecoesClassificadas.add(FaseGrupos.organizaSelecoesGrupo("C"));
		selecoesClassificadas.add(FaseGrupos.organizaSelecoesGrupo("D"));
		selecoesClassificadas.add(FaseGrupos.organizaSelecoesGrupo("E"));
		selecoesClassificadas.add(FaseGrupos.organizaSelecoesGrupo("F"));
		selecoesClassificadas.add(FaseGrupos.organizaSelecoesGrupo("G"));
		selecoesClassificadas.add(FaseGrupos.organizaSelecoesGrupo("H"));
		return selecoesClassificadas;

	}

	public static void gerarPartidasOitavas() {

		List<List<Selecao>> seleCampeans = gerarFase2();
		String time1, time2, time3, time4;
		int cont = 0, i = 1;

		for (int j = 0; j<seleCampeans.size(); j+=2) {

			if (cont > 6) {
				break;

			} else {
				time1 = seleCampeans.get(j).get(0).getNome();
				time2 = seleCampeans.get(j).get(1).getNome();
				time3 = seleCampeans.get(cont + 1).get(0).getNome();
				time4 = seleCampeans.get(cont + 1).get(1).getNome();

				Partida partOitavas = new Partida(time1, time4);
				Partida partOitavas2 = new Partida(time2, time3);
				prePartidas.put(i, partOitavas);
				prePartidas.put(i + 1, partOitavas2);
				cont += 2;
				i += 2;
			}

		}

	}

	public static void inserir(Partida partida, Integer numPartida) {

		mapPartidasOitavas.put(numPartida, partida);
		prePartidas.remove(numPartida);

	}

	public static void excluir(Partida partida, Integer numPartida) {

		// retirando os dados do objeto excluído
		partida.setData(null);
		partida.setGolsTime1(null);
		partida.setGolsTime2(null);
		partida.setHorario(null);
		partida.setLocal(null);

		mapPartidasOitavas.remove(numPartida);

		// retornando a partida para a lista de partidas geradas para ser cadastrada
		// novamente
		prePartidas.put(numPartida, partida);

	}
	
	public static Map<Integer, Partida> getPrePartidas(){
		return prePartidas;
	}

	public static Map<Integer, Partida> getPartidasOitavas() {
		return mapPartidasOitavas;
	}
	
}
