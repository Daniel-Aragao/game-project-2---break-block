package dev.needs;

import java.util.ArrayList;

import dev.entitys.Entity;

public class CreatureNeeds {

	public double x, y;
	public int width, height;
	public ArrayList<Entity> elementos;

	public CreatureNeeds(double x, double y, int width, int height, ArrayList<Entity> elementos){
		this.elementos = elementos;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}
