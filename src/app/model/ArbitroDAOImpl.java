package app.model;

import java.util.*;

/**
 * Classe responsável pelo DAO do Árbitro. Aqui foi implementado todas as
 * funções do CRUD.
 */
public class ArbitroDAOImpl implements ArbitroDAO {

	private static ArrayList<Arbitro> listaArbitro = new ArrayList<>(); // Lista para o armazenamento dos Arbitros
																		// cadastrados PRECISA DO PUBLIC AQUI?
	private static List<String> nomesArbitros = new ArrayList<String>();

	/**
	 * Função responsável por checar se a lista de nomes está vazia e se contém o
	 * nome passado pelo parametro.
	 * 
	 * @param nome
	 * @return vericação de execução.
	 */
	public static boolean checarNome(String nome) {
		if (nomesArbitros.isEmpty()) {
			return false;
		} else {
			if (nomesArbitros.contains(nome)) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * get da Lista de objetos árbitros cadastrados.
	 * 
	 * @return lista de objetos.
	 */
	// ------------------------------------------------------------------------
	public static ArrayList<Arbitro> getLista1() {
		return listaArbitro;
	}

	/**
	 * Get da Lista de nomes de árbitros cadastrados.
	 * 
	 * @return lista de nomes.
	 */
	public static List<String> getLista2() {
		return nomesArbitros;
	}
	// ------------------------------------------------------------------------

	/**
	 * Função inserir, responsável por adcionar o novo objeto Árbitro na lista. E
	 * também, adcionar o novo nome do árbitro na lista de nomes cadastrados.
	 * 
	 * @param arbitro
	 * @return verificação de sucesso ou falha do processo(boolean)
	 */
	@Override
	public boolean inserir(Arbitro arbitro) {
		listaArbitro.add(arbitro);
		nomesArbitros.add(arbitro.getNome());
		return true;
	}

	/**
	 * Função editar, responsável por modificar os atributos do objeto Árbitro salvo
	 * na lista. E também, adcionar o novo nome da lista de nomes cadastrados.
	 * 
	 * @param nome1(antigo) e nome2 (novo).
	 * @return verificação de sucesso ou falha do processo(boolean).
	 */
	@Override
	public boolean editar(String nome1, String nome2) {
		for (Arbitro atual : listaArbitro) {
			if (atual.getNome().equals(nome1) == true) {
				atual.setNome(nome2);

				// tirando o nome antigo da segunda lista e adicionando o novo
				int index = nomesArbitros.indexOf(nome1);
				nomesArbitros.remove(index);
				nomesArbitros.add(nome2);

				return true;
			}
		}
		return false;
	}

	/**
	 * Função excluir, responsável por excluir o objeto Árbitro da lista. E também,
	 * excluir o nome do árbitro da lista de nomes cadastrados.
	 * 
	 * @param nome do árbitro.
	 * @return Objeto Árbitro recém excluido .
	 */

	@Override
	public Arbitro excluir(String nomeArbitro) {

		for (int i = 0; i < listaArbitro.size(); i++) {

			if (listaArbitro.get(i).getNome().equals(nomeArbitro) == true) {

				Arbitro objArbitro = listaArbitro.get(i); // puxa da lista o objeto que será excluído para o retorno
				listaArbitro.remove(i);

				/* removendo da lista de nomes (segunda lista) */
				int index = nomesArbitros.indexOf(nomeArbitro);
				nomesArbitros.remove(index);

				return objArbitro;
			}
		}

		return null;
	}

	/**
	 * Função listar, responsável por imprimir o nome de todos os objetos Árbitros
	 * cadastrados.
	 */

	@Override
	public void listar() {

		}

}
