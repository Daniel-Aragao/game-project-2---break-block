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
	protected void moveBump(){
		if(xMove > 0){ 			// Right
			int proximoX = (int) ((x + width) + xMove);
			if( colide(proximoX, (int)y)
					|| colide(proximoX, (int)(y + height))){
				xSpeed = - xSpeed;
				xMove = 0;
				System.out.println("colisão right");
			}


		}else if(xMove < 0){ 	// Left
			int proximoX =  (int)((x) + xMove);

		}
		if(yMove < 0){			// Up
			int proximoY = (int) ((y) + yMove);

		}else if( yMove > 0){	// Down
			int proximoY = (int) ((y + height) + yMove);

		}

	}

	protected boolean colide(int x, int y){
		for(Entity e : elementos){
			 if(e.getBounds().contains(x,y)){
				 return true;
			 }
		}

		return false;
	}

	@Override
	protected void getMovements() {
		xMove = xSpeed;
		yMove = ySpeed;
	}


}
