package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.*;
import app.model.*;
import app.*;

/**
 * Classe responsável pelas demais funções.
 */

public class Funcoes {

	static Scanner read = new Scanner(System.in);

	/**
	 * Essa função tem o objetivo de realizar todas as leituras de dados informados
	 * pelo usuário que são números inteiros. Ele ler o dado informado em String e
	 * depois converte para inteiro, caso o usuário digite algo sem ser um número, o
	 * try-catch trata esse erro
	 */

	public static Integer verificaInteiro(String dadoLeitura) {
		Integer dadoLeituraInt = null;
		boolean finish = true;

		while (finish) {
			try {
				dadoLeituraInt = Integer.parseInt(dadoLeitura);

				// Caso não dê erro, agora verifica se a entrada é um numero positivo
				if (dadoLeituraInt < 0) {
					return null;
				} else {
					finish = false;
				}

			} catch (NumberFormatException erro) {
				return null;
			}
		}
		return dadoLeituraInt;
	}

	public static Integer leituraInt() {

		String dadoLeitura;
		Integer dadoLeituraInt = null;
		boolean finish = true;

		while (finish) {
			try {
				dadoLeitura = read.nextLine();
				dadoLeituraInt = Integer.parseInt(dadoLeitura);

				// Caso não dê erro, agora verifica se a entrada é um numero positivo
				if (dadoLeituraInt < 0) {
					System.err.println("Apenas numeros positivos!");
				} else {
					finish = false;
				}

			} catch (NumberFormatException erro) {
				System.err.println("Digite apenas numeros!");
			}
		}
		return dadoLeituraInt;
	}

	/**
	 * Função que verifica se os nomes informados pelo usuário possuem números
	 * 
	 * @param nome
	 * @return boolean
	 */
	public static boolean verificaNomes(String nome) {

		// percorre cada caracter do nome e verifica se há algum número nele
		for (int i = 0; i < nome.length(); i++) {
			if (Character.isDigit(nome.charAt(i)) == true) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Essa função serve para verificar se o usuário pode ir para a fase de grupos
	 * (fase 1)
	 * 
	 * @param grupos
	 * @param selecoes
	 * @return
	 */

	public static int verificaçãoFase1() {
		int totalSelecao = 32;
		if (FaseGrupos.getMapGrupos().isEmpty()) {
			return 1;

		} else if (SelecaoDAOImpl.getLista1().size() < totalSelecao) {
			return 2;

		} else if (Main.getSelecaoDAO().verificaTotal() == false) {
			return 3;

		} else if (TecnicoDAOImpl.verificaTotal() == false) {
			return 4;
		}

		return 5;
	}

	/**
	 * Essa função serve para verificar os dados de entrada tipo String para
	 * horários
	 * 
	 * @param hora
	 * @return boolean
	 */
	public static boolean verificaHorario(String hora) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
		dateFormat.setLenient(false);

		try {
			dateFormat.parse(hora);
			return false;
		} catch (ParseException e) {
			return true;
		}
	}

	/**
	 * Função responsavel para exibir jogadores com seus IDs.
	 * 
	 * @param time
	 * @param SelecaoDAO
	 */
	public static void exibirJogadores(String time, SelecaoDAOImpl SelecaoDAO) {
		for (Selecao selAtual : SelecaoDAO.getLista1()) {
			if (selAtual.getNome() == time) {
				System.out.println("Jogadores do " + selAtual.getNome() + ": \n");
				for (Jogador jogAtual : selAtual.getJogadores()) {
					System.out.println("- " + jogAtual.getNome() + " ID: " + jogAtual.getCodJog() + "\n");
				}
			}
		}
	}

	/**
	 * Função responsável por inserir o objeto PartidaJogador em jogadores.
	 * 
	 * @param partidaAtual
	 * @param SelecaoDAO
	 */
	public static void inserirPartidaJog(Partida partidaAtual, SelecaoDAOImpl SelecaoDAO) {
		for (Selecao atual : SelecaoDAOImpl.getLista1()) {
			if (partidaAtual.getTime1().equals(atual.getNome())) {
				for (Jogador jogAtual : atual.getJogadores()) {
					PartidaJogador jogPartida = new PartidaJogador(partidaAtual.getCodPart(), jogAtual.getCodJog());
					jogAtual.getPartidaJogador().add(jogPartida);
				}
			}
		}
		for (Selecao atual : SelecaoDAOImpl.getLista1()) {
			if (partidaAtual.getTime2().equals(atual.getNome())) {
				for (Jogador jogAtual : atual.getJogadores()) {
					PartidaJogador jogPartida = new PartidaJogador(partidaAtual.getCodPart(), jogAtual.getCodJog());
					jogAtual.getPartidaJogador().add(jogPartida);
				}
			}
		}
	}

	/**
	 * Checa se o ID existe na lista
	 * 
	 * @param ID
	 * @param nomeSelecao
	 * @param SelecaoDAO
	 * @return jogAtual
	 */
	public static Jogador checarID(String ID, String nomeSelecao, SelecaoDAOImpl SelecaoDAO) {
		for (Selecao selAtual : SelecaoDAO.getLista1()) {
			if (nomeSelecao.equals(selAtual.getNome())) {
				for (Jogador jogAtual : selAtual.getJogadores()) {
					if (jogAtual.getCodJog().equals(ID)) {
						return jogAtual;
					} else {
						continue;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Responsavel por cadastrar gols da partida e e atulizar gols dos jogadores
	 * 
	 * @param golsTime
	 * @param partidaEscolhida
	 * @param SelecaoDAO
	 * @param time
	 */
	public static void cadastrarGolsPartida(Jogador jogador, Integer golsJog, Partida partida, Selecao time) {
		for (PartidaJogador atualPart : jogador.getPartidaJogador()) {
			if (atualPart.getCodPartida().equals(partida.getCodPart())) {
				atualPart.inserirGols(golsJog);
				Integer golGeral = jogador.getGols() + golsJog;
				jogador.setGols(golGeral);
			}
		}
	}

	/**
	 * Cadastra cartão vermelho nos jogadores por partida
	 * 
	 * @param cartoesDoTime
	 * @param partidaEscolhida
	 * @param SelecaoDAO
	 * @param time
	 */
	public static void cadastrarCartaoVermelho(Jogador jogador, Integer cartVJog, Partida partida, Selecao time) {
		for (PartidaJogador atualPart : jogador.getPartidaJogador()) {
			if (atualPart.getCodPartida().equals(partida.getCodPart())) {
				atualPart.inserirCartV(cartVJog);
				Integer cartaoGeral = jogador.getCartVermelho() + cartVJog;
				jogador.setCartVermelho(cartaoGeral);

			}
		}

	}

	/**
	 * Cadastra cartão Amarelo nos jogadores por partida.
	 * 
	 * @param cartoesDoTime
	 * @param partidaEscolhida
	 * @param SelecaoDAO
	 * @param time
	 */
	public static void cadastrarCartaoAmarelo(Jogador jogador, Integer cartAJog, Partida partida, Selecao time) {
		for (PartidaJogador atualPart : jogador.getPartidaJogador()) {
			if (atualPart.getCodPartida().equals(partida.getCodPart())) {
				atualPart.inserirCartA(cartAJog);
				Integer cartaoGeral = jogador.getCartAmarelo() + cartAJog;
				jogador.setCartAmarelo(cartaoGeral);

			}

		}
	}

	/**
	 * Função responsavel para atualizar dados gerais de gols e cartoes dos
	 * jogadores.
	 * 
	 * @param jogadorDao
	 */

	public static void atualizarDadosJog(JogadorDAOImpl jogadorDao) {
		jogadorDao.getMap().forEach((id, jogador) -> {
			int geralGols = 0;
			int geralCartaoA = 0;
			int geralCartaoV = 0;
			for (PartidaJogador partAtual : jogador.getPartidaJogador()) {
				if (jogador.getPartidaJogador().isEmpty()) {
					System.out.println("Nao existe partida cadastrada, nao foi possivel atualizar dados");
					jogador.setGols(0);
					jogador.setCartAmarelo(0);
					jogador.setCartVermelho(0);
				} else {
					geralGols += partAtual.getGols();
					geralCartaoA += partAtual.getCartoesA();
					geralCartaoV += partAtual.getCartoesV();
				}
				jogador.setGols(geralGols);
				jogador.setCartAmarelo(geralCartaoA);
				jogador.setCartVermelho(geralCartaoV);
			}
		});
	}

	/**
	 * Função responsável pela pesquisar os nomes em comum por categoria.
	 * 
	 * @param pesquisa
	 * @param categoria
	 * @param jogadorDao
	 * @param tecnicoDao
	 * @param arbitroDao
	 */
	public static void pesquisarCategoria(String pesquisa, int categoria, JogadorDAOImpl jogadorDao,
			TecnicoDAOImpl tecnicoDao, ArbitroDAOImpl arbitroDao) {
		switch (categoria) {
		case 1:
			System.out.println("Categoria Arbitro:\n");
			for (String atualArbitro : arbitroDao.getLista2()) {
				if (atualArbitro.contains(pesquisa)) {
					System.out.println("- " + atualArbitro + "\n");
				}
			}
			break;
		case 2:
			System.out.println("Categoria Jogador:\n");
			for (String atualJogador : jogadorDao.getLista()) {
				if (atualJogador.contains(pesquisa)) {
					System.out.println("- " + atualJogador + "\n");
				}
			}
			break;
		case 3:
			System.out.println("Categoria Tecnico:\n");
			for (String atualTecnico : tecnicoDao.getLista2()) {
				if (atualTecnico.contains(pesquisa)) {
					System.out.println("- " + atualTecnico + "\n");
				}
			}
			break;
		default:
			System.out.println("Categoria invalida!");

		}

	}

	public static void attDadosJog(Partida partida) {
		
		try {
			PartidaJogador atualTemp = null;
			String idTemp = "";

		int tempGols = 0, tempCartoesA = 0, tempCartoesV = 0;
		for (Map.Entry<String, Jogador> entry : JogadorDAOImpl.getMap().entrySet()) {		

			for (PartidaJogador atual : entry.getValue().getPartidaJogador()) {
				if (partida.getCodPart().equals(atual.getCodPartida())) {
					tempGols = atual.getGols();
					tempCartoesA = atual.getCartoesA();
					tempCartoesV = atual.getCartoesV();
					atualTemp = atual;
					idTemp = atual.getCodJogador();

				}

			}
			if (idTemp.equals(entry.getValue().getCodJog())) {
				entry.getValue().getPartidaJogador().remove(atualTemp);
				entry.getValue().setGols(entry.getValue().getGols() - tempGols);
				entry.getValue().setCartAmarelo(entry.getValue().getCartAmarelo() - tempCartoesA);
				entry.getValue().setCartVermelho(entry.getValue().getCartVermelho() - tempCartoesV);
			}
			
		}
		} catch(Exception e) {
		     e.printStackTrace();
		     e.getCause();
		}

	}

}