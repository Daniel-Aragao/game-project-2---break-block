package dev.entitys.creatures;

import java.awt.Graphics;

import dev.entitys.Entity;
import dev.needs.CreatureNeeds;
import dev.util.imports.Assets;
import dev.util.imports.ImageCatalog;

public class Bola extends Creature{
	public static final int BOLA_DEFAULT_WIDTH = 20,
			BOLA_DEFAULT_HEIGHT = 20;

	public Bola(CreatureNeeds creatureNeeds) {
		super(creatureNeeds);
//		xSpeed = -xSpeed;
		ySpeed = -ySpeed;
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
	protected void bumpRight() {
		xSpeed = - xSpeed;
		xMove = 0;

	}

	@Override
	protected void bumpLeft() {
		xSpeed = - xSpeed;
		xMove = 0;

	}

	@Override
	protected void bumpUp() {
		ySpeed = -ySpeed;
		yMove=0;
	}

	@Override
	protected void bumpDown() {
		ySpeed = -ySpeed;
		yMove=0;
	}

	@Override
	protected void getMovements() {
		xMove = xSpeed;
		yMove = ySpeed;
	}

	@Override
	public void colided(Entity sponsor) {
		// TODO Auto-generated method stub

	}


}
