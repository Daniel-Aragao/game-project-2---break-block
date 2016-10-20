package dev.states;

import java.awt.Graphics;

import dev.listeners.IStateListener;

public abstract class State {
	private IStateListener StateListener;
	
	public State(IStateListener StateListener){
		this.StateListener = StateListener;
	}
	
	public abstract void changeToState(EStates State);
	
	public abstract void Draw(Graphics g);

	public abstract void update();
}
