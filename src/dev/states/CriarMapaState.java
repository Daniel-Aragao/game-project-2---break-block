package dev.states;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import dev.listeners.IStateListener;
import dev.worlds.Mapa;

public class CriarMapaState extends State {
	
	private JPanel panel;
	private JPanel gridPanel;

	public CriarMapaState(IStateListener StateListener) {
		super(StateListener, EStates.CriacaoMapa);
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(Mapa.MAPA_WIDTH, Mapa.CELULA_HEIGHT,1,1));
		
		/*20x30*/
		
		panel.add(gridPanel, BorderLayout.CENTER);
	}

	@Override
	public Component getPanel() {
		return this.panel;
	}

	@Override
	public void changeToState(EStates State) {
		throw new RuntimeException("Não implementado");
		
	}

	@Override
	public void Draw(Graphics g) {
		throw new RuntimeException("Não implementado");
		
	}

	@Override
	public void update() {
		
		
	}

}
