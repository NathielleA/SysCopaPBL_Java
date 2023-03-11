package app.model;

import java.util.*;

/**
 * Classe que representa o Seleção no sistema. Seleção possiu nome, lista de
 * jogadores e tecnico como parametro. Get e o set do dos atributos.
 */

public class Selecao {

	// ATRIBUTOS
	private String nome;
	private List<Jogador> jogadores;
	private Tecnico tecnico = null;
	private String grupo;
	private Integer pontuacaoFaseG = 0;
	private Integer totalGols = 0;
	private Integer totalCartao = 0;

	// CONSTRUTOR
	public Selecao(String nome, List<Jogador> jogadores, String grupo) {
		super();
		this.nome = nome;
		this.jogadores = jogadores;
		this.grupo = grupo;
	}

	// METODOS

	public String toString() {
		return this.nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Jogador> getJogadores() {
		return jogadores;// AQUI PRECISARIA LISTAR CADA UM DA LISTA
	}

	public void setJogadores(Jogador jogador) {
		this.jogadores.add(jogador); // adiciona o objeto passado como parâmetro na lista
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public Integer getPontuacaoFaseG() {
		return pontuacaoFaseG;
	}

	public void setPontuacaoFaseG(Integer pontuacaoFaseG) {
		this.pontuacaoFaseG = pontuacaoFaseG;
	}

	public Integer getTotalGols() {
		return totalGols;
	}

	public void setTotalGols(Integer totalGols) {
		this.totalGols = totalGols;
	}

	public Integer getTotalCartao() {
		return totalCartao;
	}

	public void setTotalCartao(Integer totalCartao) {
		this.totalCartao = totalCartao;
	}

}
