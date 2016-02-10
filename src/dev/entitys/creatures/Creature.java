package dev.entitys.creatures;

import dev.entitys.Entity;
import dev.entitys.blocos.Bloco;
import dev.worlds.Celula;

public abstract class Creature extends Entity{

	public static final int DEFAULT_HEALTH = 3;
	public static final double  DEFAULT_X_SPEED = 3.0,
							 	DEFAULT_Y_SPEED = 3.0;
	public static final int DEFAULT_CREATURE_WIDTH = 64,
							DEFAULT_CREATURE_HEIGHT = 20;

	protected int health;
	protected double xSpeed, ySpeed;
	protected double xMove, yMove;
	protected Celula[][] celulas;

	public Creature(double x, double y, int width, int height, Celula[][] celulas) {
		super(x, y, width, height);
		health = DEFAULT_HEALTH;

		xSpeed = DEFAULT_X_SPEED;
		ySpeed = DEFAULT_Y_SPEED;

		this.xMove = 0;
		this.yMove = 0;

		this.celulas = celulas;
	}
	public void Move(){
		// remover existência da criatura na celula de início do movimento
		leaveCells();

		// implementação padrão das criaturas
		//moveActs();

		// adicionar existência da criatura na celula de fim do movimento
		enterCells();
	}

	private void moveActs() {
		if(xRightMovable()){
			if(this.getxSpeed() > 0 ){ // moving right
				int tx = (int)( this.x + this.width + this.getxMove())/Celula.CELULA_WIDTH;

//				verificar colisão com cada um dos elementos de uma célula específica, modificar este
//				conjunto de métodos, as quatro direções devem ser chamadas como da forma original
//				do tutorial, porém dentro de cada atitude tomada a cerca da colisão deve-se chamar
//				um dos métodos específicos pra cada objeto ( bola, criatura), ou seja, métodos
//				que mudam o comportamento de criatura para criatura pós colisão. dica: chamar
//				o método aqui e na classe sobescrever o método de atitude ( se possível tornar a atitude
//						um método abstrato)
			}
		}
		if(xLeftMovable()){
			if(this.getxSpeed() < 0){ // moving left
			}
		}
		if(yUpMovable()){
			if(this.getySpeed() < 0){ // moving up

			}
		}
		if(yDownMovable()){
			if(this.getySpeed() > 0){ // moving down

			}
		}
	}
	private boolean xRightMovable() {

		return true;
	}

	private boolean xLeftMovable() {

		return true;
	}

	private boolean yUpMovable() {

		return true;
	}

	private boolean yDownMovable() {

		return true;
	}

	private void leaveCells(){
		leaveCell((int)x,(int) y);
		leaveCell((int)x + width,(int) y);
		leaveCell((int)x + width, (int)y + height);
		leaveCell((int)x, (int)y + height);
	}
	private void enterCells(){
		enterCell((int)x,(int) y);
		enterCell((int)x + width,(int) y);
		enterCell((int)x + width, (int)y + height);
		enterCell((int)x, (int)y + height);
	}

	private void leaveCell(int x, int y){

		int j = (int) (x / Bloco.BLOCO_WIDTH);
		int i = (int) (y / Bloco.BLOCO_HEIGHT);

		celulas[i][j].removeElement(this);
	}
	private void enterCell(int x, int y){

		int j = (int) (x / Bloco.BLOCO_WIDTH);
		int i = (int) (y / Bloco.BLOCO_HEIGHT);

		celulas[i][j].addElement(this);
	}

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
