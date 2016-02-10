package dev.util.imports;

public enum MapCatalog {

	primeiro(0),
	segundo(1);


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
