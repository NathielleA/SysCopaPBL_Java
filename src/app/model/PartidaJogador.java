package app.model;

// Classe responsável por armazenar dados dos jogadores em diferentes partidas.

public class PartidaJogador {

	private String codPartida;
	private String codJogador;
	private int gols = 0;
	private int cartoesA = 0;
	private int cartoesV = 0;

	public PartidaJogador(String codPartida, String codJogador) {
		super();
		this.codPartida = codPartida;
		this.codJogador = codJogador;
	}

	public String getCodPartida() {
		return codPartida;
	}

	public void setCodPartida(String codPartida) {
		this.codPartida = codPartida;
	}

	public String getCodJogador() {
		return codJogador;
	}

	public void setCodJogador(String codJogador) {
		this.codJogador = codJogador;
	}

	public int getGols() {
		return gols;
	}

	public void setGols(int gols) {
		this.gols = gols;
	}

	public int getCartoesA() {
		return cartoesA;
	}

	public void setCartoesA(int cartoesA) {
		this.cartoesA = cartoesA;
	}

	public int getCartoesV() {
		return cartoesV;
	}

	public void setCartoesV(int cartoesV) {
		this.cartoesV = cartoesV;
	}

	public void inserirGols(int gol) {
		this.gols = gols + gol;
	}

	public void inserirCartA(int cartA) {
		this.cartoesA = cartoesA + cartA;
	}

	public void inserirCartV(int cartV) {
		this.cartoesV = cartoesV + cartV;
	}
}
