package dev.states;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dev.listeners.IStateListener;

public class MenuState extends State {
	private JButton continuar;
	private JButton jogar;
	private JButton ranking;
	private JButton sair;
	private JButton criarFases;
	private JPanel painel;
	private JLabel label;

	public MenuState(IStateListener StateListener) {
		super(StateListener, EStates.Menu);
		painel = new JPanel();
		painel.setLayout(new BorderLayout());
		// painel.setSize(MainFrame.MAIN_FRAME_DIMENSION);
		// painel.setPreferredSize(MainFrame.MAIN_FRAME_DIMENSION);

		JPanel painelCenter = new JPanel();
		painelCenter.setLayout(new FlowLayout(FlowLayout.LEFT));
		// painelCenter.setBackground(Color.BLACK);

		JPanel painelSouth = new JPanel();
		painelSouth.setLayout(new FlowLayout(FlowLayout.LEFT));

		jogar = new JButton("Jogar");
		continuar = new JButton("Continue");
		ranking = new JButton("Ranking");
		sair = new JButton("Sair");
		criarFases = new JButton("Criar fases");

		label = new JLabel("Break Block");

		jogar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				throw new RuntimeException("N�o implementado");

			}
		});
		continuar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				throw new RuntimeException("N�o implementado");

			}
		});
		ranking.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				throw new RuntimeException("N�o implementado");

			}
		});
		sair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				

			}
		});
		criarFases.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				throw new RuntimeException("N�o implementado");

			}
		});

		painelCenter.add(label);
		painelCenter.add(continuar);
		painelCenter.add(jogar);
		painelCenter.add(ranking);

		painelSouth.add(sair);
		painelSouth.add(criarFases);

		painel.add(painelCenter, BorderLayout.CENTER);
		painel.add(painelSouth, BorderLayout.PAGE_END);
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
		throw new RuntimeException("N�o implementado");

	}

	public Container getPanel() {
		return painel;
	}

}