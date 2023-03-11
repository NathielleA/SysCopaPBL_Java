package app.model;

import java.util.*;

/**
 * Classe responsável pelo DAO do Técnico. Aqui foi implementado todas as
 * funções do CRUD.
 */

public class TecnicoDAOImpl implements TecnicoDAO {

	private static ArrayList<Tecnico> listaTecnicos = new ArrayList<>();
	private static List<String> nomesTecnico = new ArrayList<String>();

	/**
	 * Função responsável por checar se a lista de nomes está vazia e se contém o
	 * nome passado pelo parametro.
	 * 
	 * @param nome
	 * @return vericação de execução.
	 */

	public static boolean checarNome(String nome) {
		if (nomesTecnico.isEmpty()) {
			return false;
		} else {
			if (nomesTecnico.contains(nome)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static boolean verificaTotal() {
		int totalTecnicos = 32;
		if (listaTecnicos.size() == totalTecnicos) {
			return true;
		} else {
			return false;
		}
	}

	// ------------------------------------------------------------------------

	/**
	 * get da Lista de objetos técnicos cadastrados.
	 * 
	 * @return lista de objetos.
	 */

	public static ArrayList<Tecnico> getLista1() {
		return listaTecnicos;
	}

	/**
	 * Get da Lista de nomes de técnicos cadastrados.
	 * 
	 * @return lista de nomes.
	 */

	public static List<String> getLista2() {
		return nomesTecnico;
	}
	// ------------------------------------------------------------------------

	/**
	 * Função inserir, responsável por adcionar o novo objeto técnico na lista. E
	 * também, adcionar o novo nome do técnico na lista de nomes cadastrados.
	 * 
	 * @param técnico
	 * @return verificação de sucesso ou falha do processo(boolean)
	 */

	@Override
	public boolean inserir(Tecnico tecnico) {
		listaTecnicos.add(tecnico);
		nomesTecnico.add(tecnico.getNome());
		return true;
	}

	/**
	 * Função editar, responsável por modificar os atributos do objeto técnico salvo
	 * na lista. E também, adcionar o novo nome do técnico na lista de nomes
	 * cadastrados.
	 * 
	 * @param nomeTecnico(antigo) e novoNome (novo).
	 * @return verificação de sucesso ou falha do processo(boolean).
	 */

	@Override
	public boolean editar(String nomeTecnico, String novoNome, Selecao novaSelecao) {

		/**
		 * O 'for' percorre a lista de tecnicos procurando o objeto que possui o nome
		 * dado pelo usuário, quando encontrado, troca o nome antigo pelo novo. Caso não
		 * encontre um objeto com o nome dado, o método retorna false
		 */

		for (Tecnico tecnico : listaTecnicos) {
			if (tecnico.getNome().equals(nomeTecnico)) {

				if (!(novoNome.isBlank())) {
					tecnico.setNome(novoNome);

					// tirando o nome antigo da segunda lista e adicionando o novo
					int index = nomesTecnico.indexOf(nomeTecnico);
					nomesTecnico.remove(index);
					nomesTecnico.add(novoNome);

				} else if (novaSelecao != null) {
					tecnico.getSelecao().setTecnico(null); // removendo o tecnico de sua seleção antiga
					tecnico.setSelecao(novaSelecao); // colocando a nova seleção no técnico
					novaSelecao.setTecnico(tecnico); // colocando o técnico na nova seleção
				}

				return true;
			}
		}
		return false;
	}

	/**
	 * Função excluir, responsável por excluir o objeto Técnico da lista. E também,
	 * excluir o nome do técnico da lista de nomes cadastrados.
	 * 
	 * @param nome do técnico.
	 * @return Objeto técnico recém excluido .
	 */

	@Override
	public Tecnico excluir(String nomeTecnico) {

		/*
		 * O método excluir funciona de uma maneira parecida com o método editar, mas
		 * ele exclui o objeto da lista Tecnico ao encontrá-lo. E retorna o nome do
		 * Técnico que foi excluído. Caso não encontre, retorna NULL.
		 */

		for (Tecnico tecnico : listaTecnicos) {

			if (tecnico.getNome().equals(nomeTecnico)) {

				listaTecnicos.remove(tecnico);

				// removendo da lista de nomes (segunda lista)
				int index = nomesTecnico.indexOf(nomeTecnico);
				nomesTecnico.remove(index);

				tecnico.getSelecao().setTecnico(null); // removando o técnico de sua seleção

				return tecnico;
			}

		}
		return null;
	}

	/**
	 * Função listar, responsável por imprimir o nome de todos os objetos técnico
	 * cadastrados.
	 */

	@Override
	public void listar() {

	}

}
