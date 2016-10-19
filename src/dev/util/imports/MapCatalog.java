package dev.util.imports;

public enum MapCatalog {

	primeiro(0),
	segundo(1),
	terceiro(2),
	quarto(3),
	quinto(4),
	sexto(5),
	setimo(6),
	oitavo(7),
	nono(8),
	decimo(9);


	private final int valor;

	private MapCatalog(int valor){
		this.valor = valor;
	}

	public int getValor(){
		return this.valor;
	}
	public static MapCatalog getItem(int valor){
		return MapCatalog.values()[valor];
	}
}
