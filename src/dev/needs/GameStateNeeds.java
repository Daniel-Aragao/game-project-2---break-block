package dev.needs;

import dev.inputs.Keyboard;

public class GameStateNeeds {
	private Keyboard keyboard;

	public GameStateNeeds (Keyboard keyboard){
		this.keyboard = keyboard;
	}

	public Keyboard getKeyboard() {
		return keyboard;
	}

}
