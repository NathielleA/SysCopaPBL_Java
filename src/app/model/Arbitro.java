package app.model;

/**
 * Classe que representa o Árbitro no sistema. Árbitro possiu apenas nome como
 * parametro. Get e o set do nome.
 */

public class Arbitro {

	private String nome;

	/**
	 * Construtor
	 * 
	 * @param nome
	 */

	public Arbitro(String nome) {

		this.nome = nome;
	}

	/**
	 * Get nome
	 * 
	 * @return nome
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Set nome
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
}
