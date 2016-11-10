package dev.entitys;

public class Ranking {

	private int id;
	private int jogadorId;
	private int pontos;
	
	private Jogador jogador;
	
	public Ranking(int jogadorId, int pontos) {
		this.jogadorId = jogadorId;
		this.pontos = pontos;
	}

	public Ranking(int id, int jogadorId, int pontos, Jogador jogador) {
		this.id = id;
		this.jogadorId = jogadorId;
		this.pontos = pontos;
		this.jogador = jogador;
	}
	
	public int getJogadorId() {
		return this.jogadorId;
	}

	public void setJogadorId(int jogadorId) {
		this.jogadorId = jogadorId;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public int getId(){
		return this.id;
	}
	
	public Jogador getJogador(){
		return this.jogador;
	}
	
	public void setJogador(Jogador jogador){
		this.jogador = jogador;
	}
	
}
