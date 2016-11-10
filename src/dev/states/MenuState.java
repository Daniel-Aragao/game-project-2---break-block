package dev.states;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dev.frames.MainFrame;
import dev.listeners.IStateListener;

public class MenuState extends State {
	private JButton continuar;
	private JButton jogar;
	private JButton selecionarFase;
	private JButton ranking;
	private JButton sair;
	private JButton criarFases;
	private JPanel painel;
	private JLabel label;

	public MenuState(IStateListener StateListener) {
		super(StateListener, EStates.Menu);
		painel = new JPanel();
		painel.setLayout(new BorderLayout());
//		painel.setSize(MainFrame.MAIN_FRAME_DIMENSION);
//		painel.setPreferredSize(MainFrame.MAIN_FRAME_DIMENSION);
		painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel painelCenter = new JPanel();
		painelCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		BoxLayout layoutCenter = new BoxLayout(painelCenter,BoxLayout.Y_AXIS);
		painelCenter.setLayout(layoutCenter);
		
		// painelCenter.setBackground(Color.BLACK);

		JPanel painelSouth = new JPanel();
		painelSouth.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		painelSouth.setLayout(new FlowLayout(FlowLayout.LEFT));

		jogar = new JButton("Novo Jogo");
		continuar = new JButton("Continuar");
		ranking = new JButton("Ranking");
		sair = new JButton("Sair");
		criarFases = new JButton("Criar fases");
		selecionarFase = new JButton("Selecionar fase");
		
		selecionarFase.setAlignmentX(Component.CENTER_ALIGNMENT);
		jogar.setAlignmentX(Component.CENTER_ALIGNMENT);
		continuar.setAlignmentX(Component.CENTER_ALIGNMENT);
		ranking.setAlignmentX(Component.CENTER_ALIGNMENT);
		sair.setAlignmentX(Component.CENTER_ALIGNMENT);
		criarFases.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		label = new JLabel("Break Block");
		
		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		jogar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				changeToState(EStates.NovoJogo);

			}
		});
		continuar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				changeToState(EStates.Continue);

			}
		});
		ranking.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				changeToState(EStates.Ranking);

			}
		});
		sair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);				
			}
		});
		criarFases.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				changeToState(EStates.CriacaoMapa);

			}
		});
		selecionarFase.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changeToState(EStates.SelecaoFase);				
				
			}
		});

		painelCenter.add(label);
		painelCenter.add(Box.createRigidArea(new Dimension(10, 10)));
		painelCenter.add(continuar);
		painelCenter.add(Box.createRigidArea(new Dimension(10, 10)));
		painelCenter.add(jogar);
		painelCenter.add(Box.createRigidArea(new Dimension(10, 10)));
		painelCenter.add(selecionarFase);
		painelCenter.add(Box.createRigidArea(new Dimension(10, 10)));
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
		// salvar jogo
		StateListener.StateChanged(State);

	}

	public Container getPanel() {
		return painel;
	}

}
