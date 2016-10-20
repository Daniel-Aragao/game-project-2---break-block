package dev.needs;

import dev.inputs.Keyboard;
import dev.listeners.IStateListener;

public class GameStateNeeds {
	private Keyboard keyboard;
	private IStateListener stateListener;
	
	public GameStateNeeds (Keyboard keyboard, IStateListener stateListener){
		this.keyboard = keyboard;
		this.setStateListener(stateListener);
	}

	public Keyboard getKeyboard() {
		return keyboard;
	}

	public IStateListener getStateListener() {
		return stateListener;
	}

	public void setStateListener(IStateListener stateListener) {
		this.stateListener = stateListener;
	}

}
