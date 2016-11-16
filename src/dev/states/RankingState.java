package dev.states;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import dev.entitys.Ranking;
import dev.listeners.IStateListener;
import dev.repositories.RankingRepository;

public class RankingState extends State {

	private RankingRepository rankingRepository;

	private String[] headerTable = { "Posição", "Nome", "Pontuação" };

	private String[][] bodyTable;

	private JPanel panel;
	private JTable table;
	private JLabel titulo;

	private JButton voltar;

	public RankingState(IStateListener StateListener) {
		super(StateListener, EStates.Ranking);

		this.rankingRepository = new RankingRepository();

		this.panel = new JPanel();
		this.panel.setLayout(new BorderLayout());
		this.panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		this.titulo = new JLabel("Ranking");

		this.voltar = new JButton("Voltar");
		this.voltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changeToState(EStates.Menu);
			}
		});

		this.organizarRankings();
		this.panel.add(this.titulo, BorderLayout.NORTH);
		this.panel.add(this.voltar, BorderLayout.SOUTH);
	}

	public void organizarRankings() {
		List<Ranking> rankings = this.rankingRepository.getAll();

		bodyTable = new String[10][this.headerTable.length];

		for (int i = 0; i < rankings.size(); i++) {
			if (i == 10) {
				break;
			}
			Ranking ranking = rankings.get(i);
			bodyTable[i][0] = (i + 1) + "";
			bodyTable[i][1] = ranking.getJogador().getUsuario();
			bodyTable[i][2] = ranking.getPontos() + "";
		}

		this.table = new JTable(this.bodyTable, this.headerTable);
		this.panel.add(this.table, BorderLayout.CENTER);
		this.panel.repaint();
	}

	@Override
	public Component getPanel() {
		return this.panel;
	}

	@Override
	public void changeToState(EStates State) {
		this.StateListener.StateChanged(State);
	}

	@Override
	public void Draw(Graphics g) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public void update() {

	}

}
