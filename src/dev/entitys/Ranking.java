package dev.entitys;

public class Ranking {

	private String nomeJogador;
	private int pontos;
	
	public Ranking(String nomeJogador, int pontos) {
		this.nomeJogador = nomeJogador;
		this.pontos = pontos;
	}

	public String getNomeJogador() {
		return nomeJogador;
	}

	public void setNomeJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

}
