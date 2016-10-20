package dev.states;

import java.awt.Graphics;

import javax.swing.JButton;

import dev.listeners.IStateListener;

public class MenuState extends State{
	
	public MenuState(IStateListener StateListener) {
		super(StateListener);
		// TODO Auto-generated constructor stub
	}

	private JButton jogar;
	private JButton escolherFase;
//	private

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
