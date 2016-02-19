package dev.entitys.creatures;

import java.awt.Graphics;

import dev.util.imports.Assets;
import dev.util.imports.ImageCatalog;

public class Bola extends Creature{
	public static final int BOLA_DEFAULT_WIDTH = 20,
			BOLA_DEFAULT_HEIGHT = 20;

	public Bola(double x, double y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Assets.getImage(ImageCatalog.bola_1), (int)x, (int)y, null);
	}

	@Override
	public void update() {
		Move();
	}


	@Override
	protected void getMovements() {
		xMove = xSpeed;
		yMove = ySpeed;
	}


}
