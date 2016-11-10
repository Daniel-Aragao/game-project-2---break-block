package dev.needs;

import java.util.ArrayList;

import dev.entitys.Entity;
import dev.inputs.Keyboard;

public class PlayerNeeds extends CreatureNeeds{

	public Keyboard keyboard;

	public PlayerNeeds(Keyboard keyboard, double x, double y, int width, 
			int height, ArrayList<Entity> elementos) {
		super(x, y, width, height, elementos);
		this.keyboard = keyboard;
	}
}
