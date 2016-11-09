package dev.states;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
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

	public RankingState(IStateListener StateListener) {
		super(StateListener, EStates.Ranking);

		this.rankingRepository = new RankingRepository();

		List<Ranking> rankings = this.rankingRepository.getAll();
		//List<Ranking> rankings = new ArrayList<Ranking>();
		this.organizarRankings(rankings);

		this.table = new JTable(this.bodyTable, this.headerTable);
		
		this.panel = new JPanel();
		this.panel.setLayout(new BorderLayout());
		this.panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.titulo = new JLabel("Ranking");
		
		this.panel.add(this.titulo);
		this.panel.add(this.table);
		
	}

	public void organizarRankings(List<Ranking> rankings) {
		bodyTable = new String[1][this.headerTable.length];

		for (int i = 0; i < rankings.size(); i++) {
			Ranking ranking = rankings.get(i);
			bodyTable[i][0] = i + "";
			bodyTable[i][1] = ranking.getNomeJogador();
			bodyTable[i][2] = ranking.getPontos() + "";
		}
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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

}
