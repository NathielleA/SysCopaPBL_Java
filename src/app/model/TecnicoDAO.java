package app.model;

/**
 * Interface do DAO do Técnico. Com as funções responsáveis pelo CRUD.
 */

public interface TecnicoDAO {

	public boolean inserir(Tecnico tecnico);

	public boolean editar(String nomeTecnico, String novoNome, Selecao novaSelecao);

	public Tecnico excluir(String nomeTecnico);

	public void listar();
}
