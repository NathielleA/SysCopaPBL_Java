package app.model;

/**
 * Interface do DAO do Seleção. Com as funções responsáveis pelo CRUD.
 */

public interface SelecaoDAO {

	public boolean inserir(Selecao selecao);

	public boolean editar(String nomeSelecao, String novoNome, String novoGrupo);

	public Selecao excluir(String nomeSelecao);

	public void listar();
}
