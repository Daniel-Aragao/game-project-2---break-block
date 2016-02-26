package dev.entitys.Wall;

import java.awt.Graphics;

import dev.entitys.Entity;
import dev.entitys.creatures.Bola;
import dev.listeners.IWallColisionEffects;

public class Wall extends Entity {

	private IWallColisionEffects wallColisionEffects;

	public Wall(double x, double y, int width, int height) {
		super(x, y, width, height);

	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void colided(Entity sponsor) {
		if(sponsor instanceof Bola && getWallColisionEffects() != null){
			getWallColisionEffects().ballColision((Bola) sponsor);
		}

	}

	public void playerColideEffect(){

	}

	public IWallColisionEffects getWallColisionEffects() {
		return wallColisionEffects;
	}

	public void setWallColisionEffects(IWallColisionEffects wallColisionEffects) {
		this.wallColisionEffects = wallColisionEffects;
	}

}
