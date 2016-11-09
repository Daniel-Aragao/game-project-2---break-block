package dev.needs;

import java.awt.Canvas;

import javax.swing.JPanel;

import dev.inputs.Keyboard;
import dev.listeners.IStateListener;

public class GameStateNeeds {
	private Keyboard keyboard;
	private IStateListener stateListener;
//	private JPanel canvasPanel;
	private Canvas canvas;
	
//	public GameStateNeeds (Keyboard keyboard, IStateListener stateListener, JPanel canvas){
	public GameStateNeeds (Keyboard keyboard, IStateListener stateListener, Canvas canvas){
		this.keyboard = keyboard;
		this.setStateListener(stateListener);
//		this.canvasPanel = canvas;
		this.canvas = canvas;
	}
	public Canvas getCanvas(){
		return canvas;
	}	
	
//	public JPanel getCanvasPanel(){
//		return this.canvasPanel;
//	}

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
