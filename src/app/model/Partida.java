package app.model;

/**
 * Classe que representa as partidas do Sistema...
 */

public class Partida {

	// ATRIBUTOS
	private String codPart;
	private String data;
	private String horario;
	private String local;
	private String time1;
	private String time2;
	private Integer golsTime1;
	private Integer golsTime2;
	private String grupo;
	private boolean cadastro = false;
	private Selecao ganhador;
	private Selecao perdedor;

	// CONSTRUTOR
	public Partida(String time1, String time2) {
		super();
		this.time1 = time1;
		this.time2 = time2;

	}

	// GETTERS E SETTERS

	public String getCodPart() {
		return codPart;
	}

	public void setCodPart(String codPart) {
		this.codPart = codPart;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public Integer getGolsTime1() {
		return golsTime1;
	}

	public void setGolsTime1(Integer golsTime1) {
		this.golsTime1 = golsTime1;
	}

	public Integer getGolsTime2() {
		return golsTime2;
	}

	public void setGolsTime2(Integer golsTime2) {
		this.golsTime2 = golsTime2;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public boolean isCadastro() {
		return cadastro;
	}

	public void setCadastro(boolean cadastro) {
		this.cadastro = cadastro;
	}

	public Selecao getGanhador() {
		return ganhador;
	}

	public void setGanhador(Selecao ganhador) {
		this.ganhador = ganhador;
	}

	public Selecao getPerdedor() {
		return perdedor;
	}

	public void setPerdedor(Selecao perdedor) {
		this.perdedor = perdedor;
	}

}
