package dev.frames.criarMapas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import dev.entitys.blocos.Bloco;
import dev.frames.JImagePanel;
import dev.listeners.ICriarMapaClicked;

public class Celula {
	private Bloco bloco;
	private JImagePanel imagePanel;
	private ICriarMapaClicked clickEvt;

	public Celula(ICriarMapaClicked clickEvt) {
		iniciarPanel();
		this.clickEvt = clickEvt;
		iniciarclick();
	}

	public Celula(ICriarMapaClicked clickEvt, Bloco bloco) {
		iniciarPanel();
		this.bloco = bloco;
		this.clickEvt = clickEvt;
		iniciarclick();

		imagePanel.setImage(bloco.getImage());
	}
	public Bloco getBloco(){
		return bloco;
	}

	private void iniciarPanel() {
		imagePanel = new JImagePanel(null, Bloco.BLOCO_WIDTH, Bloco.BLOCO_HEIGHT);
	}

	public JPanel getPanel() {
		return imagePanel;
	}

	private void iniciarclick() {
		Celula self = this;
		imagePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent args) {
				Bloco b = clickEvt.click(self);
				doClick(b);
			}
		});

	}

	public void doClick(Bloco b) {
		if (clickEvt != null) {
			Bloco oldbloco = this.bloco;
			setBloco(b);
			if (oldbloco != null && b != null && oldbloco.getValor() == b.getValor()) {
				clean();
			}
		}
	}

	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
		if (bloco == null) {
			imagePanel.setImage(null);
		} else {
			imagePanel.setImage(bloco.getImage());
		}
		imagePanel.repaint();
	}

	public void clean() {
		setBloco(null);
	}
}
