package dev.needs;

import java.awt.Canvas;

import javax.swing.JPanel;

import dev.inputs.Keyboard;
import dev.listeners.IStateListener;

public class GameStateNeeds {
	private Keyboard keyboard;
	private IStateListener stateListener;
	private Canvas canvas;
	
	public GameStateNeeds (Keyboard keyboard, IStateListener stateListener, Canvas canvas){
		this.keyboard = keyboard;
		this.setStateListener(stateListener);
		this.canvas = canvas;
	}
	public Canvas getCanvas(){
		return canvas;
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
