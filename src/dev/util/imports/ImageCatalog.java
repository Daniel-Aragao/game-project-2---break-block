package dev.util.imports;

public enum ImageCatalog {
	bloco_1(0),
	bloco_2(1),
	bloco_3(2),
	bola_1(3);

	private final int valor;

	private ImageCatalog(int valor){
		this.valor = valor;
	}

	public int getValor(){
		return this.valor;
	}
	public static ImageCatalog getItem(int valor){
		return ImageCatalog.values()[valor];
	}
}
