package dev.needs;

import dev.inputs.Keyboard;

public class MapNeeds {
	private Keyboard keyboard;
	private int width, height;
	private int[][] maping;

	public MapNeeds (Keyboard keyboard, int width, int height, int[][] maping){
		this.keyboard = keyboard;
		this.width = width;
		this.height = height;
		this.maping = maping;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[][] getMaping() {
		return maping;
	}

	public Keyboard getKeyboard() {
		return keyboard;
	}

}
