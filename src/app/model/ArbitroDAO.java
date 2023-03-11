package app.model;

/**
 * Interface do DAO do Árbitro. Com as funções responsáveis pelo CRUD.
 */

public interface ArbitroDAO {

	/**
	 * Função INSERIR do CRUD
	 * 
	 * @param arbitro
	 * @return boolean
	 */
	public boolean inserir(Arbitro arbitro);

	/**
	 * Função EDITAR do CRUD
	 * 
	 * @param nome1 e nome2
	 * @return boolean
	 */

	public boolean editar(String nome1, String nome2);

	/**
	 * Função EXCLUIR do CRUD
	 * 
	 * @param nome do Árbitro
	 * @return boolean
	 */
	public Arbitro excluir(String nomeArbitro);

	/**
	 * Função listar os objetos.
	 */
	public void listar();

}
