package dev.states;

import java.awt.Component;
import java.awt.Graphics;

import dev.listeners.IStateListener;

public abstract class State {
	protected IStateListener StateListener;
	protected EStates state;
	
	public State(IStateListener StateListener, EStates state){
		this.StateListener = StateListener;
		this.state = state;
	}
	
	public EStates getState(){
		return state;
	}
	
	public abstract Component getPanel();
	
	public abstract void changeToState(EStates State);
	
	public abstract void Draw(Graphics g);

	public abstract void update();
}
