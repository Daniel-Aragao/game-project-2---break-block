package dev.states;

import dev.util.imports.ImageCatalog;

public enum EStates {
	Game(0),
	Menu(1),
	Pause(2),
	Ranking(3),
	CriacaoMapa(4),
	Cadastro(5),
	Login(6),
	SelecaoFase(7);

	private final int valor;

	private EStates(int valor){
		this.valor = valor;
	}

	public int getValor(){
		return this.valor;
	}
	public static ImageCatalog getItem(int valor){
		return ImageCatalog.values()[valor];
	}
}
