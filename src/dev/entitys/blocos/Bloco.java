package dev.entitys.blocos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.entitys.Entity;
import dev.util.imports.Assets;
import dev.util.imports.ImageCatalog;

public class Bloco extends Entity{

	public static final int BLOCO_WIDTH = 40,
							BLOCO_HEIGHT = 20;

	private int valor;

	public Bloco(double x, double y, int valor) {
		super(x, y, BLOCO_WIDTH, BLOCO_HEIGHT);
		this.valor = valor;
	}

	@Override
	public void draw(Graphics g) {

		if(valor >= 1)
		g.drawImage(Assets.getImage(ImageCatalog.getItem(valor-1)), (int)x, (int)y, null);

	}
	@Override
	public void update() {


	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
