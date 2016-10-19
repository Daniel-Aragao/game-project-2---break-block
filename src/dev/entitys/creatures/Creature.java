package dev.entitys.creatures;

import java.util.ArrayList;

import dev.entitys.Entity;
import dev.needs.CreatureNeeds;

public abstract class Creature extends Entity{

	public static final int DEFAULT_HEALTH = 3;
	public static final double  DEFAULT_X_SPEED = 3.0,
							 	DEFAULT_Y_SPEED = 3.0;
	public static final int DEFAULT_CREATURE_WIDTH = 64,
							DEFAULT_CREATURE_HEIGHT = 20;

	protected int health;
	protected double xSpeed, ySpeed;
	protected double xMove, yMove;
	protected ArrayList<Entity> elementos;

	public Creature(CreatureNeeds creatureNeeds) {
		super(creatureNeeds.x, creatureNeeds.y, creatureNeeds.width, creatureNeeds.height);
		health = DEFAULT_HEALTH;

		xSpeed = DEFAULT_X_SPEED;
		ySpeed = DEFAULT_Y_SPEED;

		this.elementos = creatureNeeds.elementos;

		this.xMove = 0;
		this.yMove = 0;
	}
	protected final void Move(){
		this.xMove = 0;
		this.yMove = 0;

		getMovements();
		moveBump();// colisão

		x += xMove;
		y += yMove;

	}

	protected void moveBump() {
		if(xMove > 0){ 			// Right
			int proximoX = (int) ((x + width) + xMove);
			if( colide(proximoX, (int)y,this)
					|| colide(proximoX, (int)(y + height),this)){
				bumpRight();
			}


		}else if(xMove < 0){ 	// Left
			int proximoX =  (int)(x + xMove);
			if(colide(proximoX,(int)y,this)
					|| colide(proximoX,(int)(y+height),this)){
				bumpLeft();
			}

		}
		if(yMove < 0){			// Up
			int proximoY = (int) ((y) + yMove);
			if(colide((int)x, proximoY,this)
					||colide((int)(x+width), proximoY,this)){
				bumpUp();
			}

		}else if( yMove > 0){	// Down
			int proximoY = (int) ((y + height) + yMove);
			if(colide((int)x, proximoY,this)
					||colide((int)(x+width), proximoY,this)){
				bumpDown();
			}
		}

	}

	private boolean colide(int x, int y, Entity sponsor){
		for(Entity e : elementos){
			 if(e.getBounds().contains(x,y) && e.isSolid()){
				 e.colided(sponsor);
				 return true;
			 }
		}

		return false;
	}
	protected abstract void bumpRight();
	protected abstract void bumpLeft();
	protected abstract void bumpUp();
	protected abstract void bumpDown();

	protected abstract void getMovements();


	public void invertXSpeed(){
		this.xSpeed = -xSpeed;
	}
	public void invertYSpeed(){
		this.ySpeed = -ySpeed;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public double getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	public double getySpeed() {
		return ySpeed;
	}

	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

	public double getxMove() {
		return xMove;
	}

	public void setxMove(double xMove) {
		this.xMove = xMove;
	}

	public double getyMove() {
		return yMove;
	}

	public void setyMove(double yMove) {
		this.yMove = yMove;
	}

}
