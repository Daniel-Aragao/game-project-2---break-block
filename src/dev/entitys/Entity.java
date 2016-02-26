package dev.entitys;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Entity {

	protected double x;
	protected double y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean solid;

	public Entity(double x, double y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		bounds = new Rectangle((int)x,(int) y, width, height);

		this.solid = true;
	}

	public abstract void draw(Graphics g);

	public abstract void update();

	public abstract void colided(Entity sponsor);

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Rectangle getBounds() {
		bounds = new Rectangle((int)x,(int) y, width, height);
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public boolean contains(Point a){
		return getBounds().contains(a);
	}
	public boolean contains(int x, int y){
		return getBounds().contains(x, y);
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}
}
