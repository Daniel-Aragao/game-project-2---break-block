package dev.states;

import java.awt.Graphics;

import dev.listeners.IStateListener;

public class PauseState extends State{

	public PauseState(IStateListener StateListener) {
		super(StateListener);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Draw(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeToState(EStates State) {
		throw new RuntimeException("Não implementado");
		
	}

}
