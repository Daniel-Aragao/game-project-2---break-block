package dev.entitys;

public class Jogador {

	private String nome;
	private String senha;
	
	public Jogador(String usuario, String senha) {
		this.nome = usuario;
		this.senha = senha;
	}
	
	public String getUsuario() {
		return nome;
	}
	public void setUsuario(String usuario) {
		this.nome = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
