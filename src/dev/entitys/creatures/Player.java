package dev.entitys.creatures;

import java.awt.Graphics;

import dev.needs.PlayerNeeds;
import dev.util.imports.Assets;
import dev.util.imports.ImageCatalog;

public class Player extends Creature{
	public static final int PLAYER_DEFAULT_WIDTH = 120,
			PLAYER_DEFAULT_HEIGHT = 20;


	private long scores;
	private int lifes;
	private PlayerNeeds playerNeeds;

	public Player(PlayerNeeds playerNeeds) {
		super(playerNeeds);
		this.playerNeeds = playerNeeds;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Assets.getImage(ImageCatalog.palheta_1), (int)x, (int)y, null);

	}

	@Override
	public void update() {
		Move();

	}


	public long getScores() {
		return scores;
	}

	public void setScores(long scores) {
		this.scores = scores;
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

	private void getInput() {

		if(playerNeeds.keyboard.isLeft()){
			xMove = -(int)this.xSpeed;
		}
		if(playerNeeds.keyboard.isRight()){
			xMove = (int)this.xSpeed;
		}
	}

	@Override
	protected void moveBump(){
		int proximoX = (int) ((x + width) + xMove);
		if( colide(proximoX, (int)y)
				|| colide(proximoX, (int)(y + height))){
			xMove = -1;
			System.out.println("colisão right");
		}else if(xMove < 0){ 	// Down

		}

	}


	@Override
	protected void getMovements() {
		getInput();
	}

}
