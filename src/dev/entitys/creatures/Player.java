package dev.entitys.creatures;

import java.awt.Graphics;

import dev.entitys.Entity;
import dev.entitys.blocos.Bloco;
import dev.frames.MainFrame;
import dev.needs.PlayerNeeds;
import dev.util.imports.Assets;
import dev.util.imports.ImageCatalog;
import dev.worlds.Mapa;

public class Player extends Creature{
	public static final int PLAYER_DEFAULT_WIDTH = 120,
			PLAYER_DEFAULT_HEIGHT = 20;
	public static final int CORACAO_WIDTH_HEIGHT = 14;
	public static final int LIFES_X = MainFrame.MAIN_FRAME_DIMENSION.width - Bloco.BLOCO_WIDTH - CORACAO_WIDTH_HEIGHT-10;
	public static final int MAX_LIFES = 3;

	private long scores;
	private long lastScores;
	private PlayerNeeds playerNeeds;

	private int lifes;



	public Player(PlayerNeeds playerNeeds) {
		super(playerNeeds);
		this.playerNeeds = playerNeeds;
		lifes = MAX_LIFES;
		scores = 0;
		xSpeed = xSpeed*2.3;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Assets.getImage(ImageCatalog.palheta_1), (int)x, (int)y, null);
		for(int i = 0; i < lifes; i++){
			g.drawImage(Assets.getImage(ImageCatalog.coracao),
					LIFES_X - i*CORACAO_WIDTH_HEIGHT, Mapa.HUD_Y - CORACAO_WIDTH_HEIGHT, null);
		}
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
		if(this.lifes > 3){
			this.lifes = MAX_LIFES;
		}
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
	protected void bumpRight() {
		xMove = 0;

	}

	@Override
	protected void bumpLeft() {
		xMove=0;

	}

	@Override
	protected void bumpUp() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void bumpDown() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void getMovements() {
		getInput();
	}

	@Override
	public void colided(Entity sponsor) {
		scores +=10;

	}

	public long getLastScores() {
		return lastScores;
	}

	public void setLastScores(long lastScores) {
		this.lastScores = lastScores;
	}

}
