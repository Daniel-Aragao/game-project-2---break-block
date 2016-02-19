package dev.entitys.creatures;

import dev.entitys.Entity;

public abstract class Creature extends Entity{

	public static final int DEFAULT_HEALTH = 3;
	public static final double  DEFAULT_X_SPEED = 6.0,
							 	DEFAULT_Y_SPEED = -3.0;
	public static final int DEFAULT_CREATURE_WIDTH = 64,
							DEFAULT_CREATURE_HEIGHT = 20;

	protected int health;
	protected double xSpeed, ySpeed;
	protected double xMove, yMove;

	public Creature(double x, double y, int width, int height) {
		super(x, y, width, height);
		health = DEFAULT_HEALTH;

		xSpeed = DEFAULT_X_SPEED;
		ySpeed = DEFAULT_Y_SPEED;

		this.xMove = 0;
		this.yMove = 0;
	}
	protected final void Move(){
		this.xMove = 0;
		this.yMove = 0;

		getMovements();
		MoveBump();

		x += xMove;
		y += yMove;

	}

	private void MoveBump() {
		// TODO Auto-generated method stub

	}
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
