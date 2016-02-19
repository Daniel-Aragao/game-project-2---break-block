package dev.needs;

import dev.inputs.Keyboard;

public class PlayerNeeds {

	public double x, y;
	public int width, height;
	public Keyboard keyboard;

	public PlayerNeeds(Keyboard keyboard, double x, double y, int width, int height) {
		this.keyboard = keyboard;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}
