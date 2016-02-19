package dev.entitys.blocos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.entitys.Entity;
import dev.listeners.IBlocoHittedListener;
import dev.util.imports.Assets;
import dev.util.imports.ImageCatalog;

public abstract class Bloco extends Entity{

	public static final int BLOCO_WIDTH = 40,
							BLOCO_HEIGHT = 20;

	private int valor;
	protected boolean isSolid;
	protected boolean isCorruptible;
	private IBlocoHittedListener blocoHittedhandler;

	public Bloco(double x, double y, int valor, boolean solid, boolean corruption) {
		super(x, y, BLOCO_WIDTH, BLOCO_HEIGHT);
		this.valor = valor;
		isSolid = solid;
		isCorruptible = corruption;
	}

	@Override
	public void draw(Graphics g) {

		if(valor >= 1)
		g.drawImage(Assets.getImage(ImageCatalog.getItem(valor-1)), (int)x, (int)y, null);

	}
	@Override
	public void update() {
// quando hitar chamar o metodo bloco hitAction
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public boolean isSolid() {
		return isSolid;
	}

	public boolean isCorruptible() {
		return isCorruptible;
	}

	public IBlocoHittedListener getBlocoHittedhandler() {
		return blocoHittedhandler;
	}

	public void setBlocoHittedhandler(IBlocoHittedListener blocoHittedhandler) {
		this.blocoHittedhandler = blocoHittedhandler;
	}

}
