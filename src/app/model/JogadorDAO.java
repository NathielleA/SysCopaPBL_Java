package app.model;

/**
 * Interface do DAO do Jogador. Com as funções responsáveis pelo CRUD.
 */

public interface JogadorDAO {

	public boolean inserir(Jogador jogador);

	public boolean editar(String idJogador, String novoNome, String novaPosicao, Selecao novaSelecao);

	public Jogador excluir(String idJogador);

	public void listar();
}
