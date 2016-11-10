package dev.states;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import dev.entitys.blocos.Bloco;
import dev.entitys.blocos.BlocoMassive;
import dev.entitys.blocos.BlocoWeak;
import dev.frames.criarMapas.Celula;
import dev.listeners.ICriarMapaClicked;
import dev.listeners.IStateListener;
import dev.worlds.Mapa;

public class CriarMapaState extends State {
	
	private JPanel panel;
	private JPanel gridPanel;
	
	private ArrayList<Bloco> blocos;

	public CriarMapaState(IStateListener StateListener) {
		super(StateListener, EStates.CriacaoMapa);
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		blocos = getBlocos();
		
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(Mapa.MAPA_HEIGHT, Mapa.MAPA_WIDTH/2,1,1));
		
		/*20x30*/
		
		for(int i = 0; i < Mapa.MAPA_HEIGHT; i++){
			for(int j = 0; j < Mapa.MAPA_WIDTH; j++){
				int k = i%10;
				Celula celula = new Celula(getClickedListener(), blocos.get(k));
				gridPanel.add(celula.getPanel());
			}
		}
		
		
		
		panel.add(gridPanel, BorderLayout.CENTER);
	}
	
	private ICriarMapaClicked getClickedListener(){
		return new ICriarMapaClicked() {
			
			@Override
			public void click(Bloco bloco) {
				System.out.println(bloco.getValor());				
			}
		};
	}

	private ArrayList<Bloco> getBlocos() {
		blocos = new ArrayList<Bloco>();
		for(int i = 1; i < 11; i++){
			Bloco bloco = null;
			if (i == 10) {
				bloco = new BlocoMassive(0, 0, i);
			} else {
				bloco = new BlocoWeak(0, 0, i);
			}
			blocos.add(bloco);
		}
		return blocos;
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
