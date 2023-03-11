package app.model;

import java.util.*;

import app.Funcoes;

/**
 * Classe responsável pelo DAO do Jogador. Aqui foi implementado todas as
 * funções do CRUD.
 */

public class JogadorDAOImpl implements JogadorDAO {

	private static Scanner scan = new Scanner(System.in);
	private static Map<String, Jogador> mapJogadores = new HashMap<String, Jogador>(); // Lista para armazenar os
																						// objetos Jogador
	private static List<String> nomesJogadores = new ArrayList<String>();

	// ------------------------------------------------------------------------

	/**
	 * Função responsável por checar se a lista de nomes está vazia e se contém o
	 * nome passado pelo parametro.
	 * 
	 * @param nome
	 * @return vericação de execução.
	 */
	public static boolean checarNome(String nome) {

		if (nomesJogadores.isEmpty()) {
			return false;
		} else {
			if (nomesJogadores.contains(nome)) {
				return true;
			} else {
				return false;
			}
		}
	}

	// ------------------------------------------------------------------------

	/**
	 * Função para verificar se o ID informado pelo usuário está na Map de Joadpres
	 * 
	 * @param ID do jogador
	 * @return resultado da verificação
	 */

	public static boolean checarID(String id) {

		if (mapJogadores.isEmpty()) {
			return false;
		} else {
			if (mapJogadores.containsKey(id)) {
				return true;
			} else {
				return false;
			}
		}
	}

	// ------------------------------------------------------------------------

	/**
	 * Função para retornar o Map de jogadores, pois é um atributo privado do DAO
	 * 
	 * @return Map dos jogadores
	 */

	public static Map<String, Jogador> getMap() {
		return mapJogadores;
	}

	/**
	 * Função para retornar a lista de nomes dos jogadores, pois é um atributo
	 * privado do DAO
	 * 
	 * @return lista do nome dos jogadores
	 */

	public static List<String> getLista() {
		return nomesJogadores;
	}
	// ------------------------------------------------------------------------

	/**
	 * Função para inserir um jogador novo (CRUD)
	 * 
	 * @param objeto jogador
	 */
	@Override
	public boolean inserir(Jogador jogador) {
		mapJogadores.put(jogador.getCodJog(), jogador);
		nomesJogadores.add(jogador.getNome());
		return true;
	}

	/**
	 * Função para editar algum dado de um jogador já cadastrado (CRUD). Para
	 * editar, primeiro verifica se o ID recebido está na lista e depois ele edita
	 * conforme a escolha do usuário.
	 * 
	 * @param ID do jogador e opção do que o usuário quer editar
	 * @return verificação de falha ou sucesso da função (boolean)
	 */

	@Override
	public boolean editar(String idJogador, String novoNome, String novaPosicao, Selecao novaSelecao) {

		if (mapJogadores.containsKey(idJogador)) {

			if (!(novoNome.isBlank())) { // EDITANDO O NOME
				// tirando o nome antigo da segunda lista de jogadores
				int index = nomesJogadores.indexOf(mapJogadores.get(idJogador).getNome());
				nomesJogadores.remove(index);

				mapJogadores.get(idJogador).setNome(novoNome);// mudando o nome do objeto
				nomesJogadores.add(novoNome); // adicionando o novo nome na segunda lista

			} else if (novaPosicao != null) { // EDITANDO A POSIÇÃO
				mapJogadores.get(idJogador).setPosicao(novaPosicao);

			} else if (novaSelecao != null) { // EDITANDO A SELEÇÃO

				Jogador jogador = mapJogadores.get(idJogador);
				Selecao antigaSelecao = jogador.getSelecao();

				// colocando o jogador na nova seleção
				jogador.setSelecao(novaSelecao);
				novaSelecao.setJogadores(jogador);

				// tirando o jogador da antiga seleção
				antigaSelecao.getJogadores().remove(jogador);
			}
			return true;
		}
		return true;
	}

	/**
	 * Função para excluir um jogador cadastrado (CRUD). Esse método retorna o
	 * objeto excluído, ou NULL caso esse ID não exista no Map
	 * 
	 * @param ID do jogador
	 * @return objeto Jogador que foi excluído
	 */
	@Override
	public Jogador excluir(String idJogador) {

		Jogador obj = mapJogadores.remove(idJogador);

		for (String atual : nomesJogadores) {
			if (atual.equals(obj.getNome()) == true) {
				nomesJogadores.remove(nomesJogadores.indexOf(atual));
				return obj;
			}

		}
		return null;

	}

	/**
	 * Função para listar todos os jogadores e seus atributos (CRUD)
	 */

	@Override
	public void listar() {

	}

	/**
	 * Funçao para atualizar a lista de jogadores da seleção de um jogador que foi
	 * excluido
	 * 
	 * @param Objeto Seleçao
	 */

	public static void attListaJogadores(Selecao obj) {

		for (Jogador atual : obj.getJogadores()) {
			mapJogadores.remove(atual.getCodJog());
			int indx = nomesJogadores.indexOf(atual.getNome());
			nomesJogadores.remove(indx);
		}

	}

}
