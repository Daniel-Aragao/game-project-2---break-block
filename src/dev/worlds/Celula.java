package dev.worlds;

import java.awt.Graphics;
import java.util.ArrayList;

import dev.entitys.Entity;

public class Celula {
	public static final int CELULA_WIDTH = 40,
							CELULA_HEIGHT = 20;

	private ArrayList<Entity> elementos;
	private int i, j;

	public Celula(int i, int j){
		elementos = new ArrayList<Entity>();
		this.i = i;
		this.j = j;
	}

	public boolean isEmpty(){
		return elementos.isEmpty();
	}
	public void addElement(Entity elemento){
		elementos.add(elemento);
	}

	public void removeElement(Entity elemento){
		elementos.remove(elemento);
	}

	public void draw(Graphics g){
		int sx, sy;
		for(Entity e : elementos){

			sx = (int)e.getX()/Celula.CELULA_WIDTH;
			sy = (int)e.getY()/Celula.CELULA_HEIGHT;

			if(sx == j && sy == i){
				e.draw(g);

			}
		}
	}
	public void update(){
		int sx, sy;
		for(Entity e : elementos){

			sx = (int)e.getX()/Celula.CELULA_WIDTH;
			sy = (int)e.getY()/Celula.CELULA_HEIGHT;

			if(sx == i && sy == j){
				e.update();
			}
		}
	}
}
