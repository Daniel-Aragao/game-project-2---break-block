package dev.util.imports;

public enum ImageCatalog {
	bloco_1(0),
	bloco_2(1),
	bloco_3(2),
	bloco_4(3),
	bloco_5(4),
	bloco_6(5),
	bloco_7(6),
	bloco_8(7),
	bloco_9(8),
	bloco_10(9),
	bola_1(10),
	background_1(11);

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
