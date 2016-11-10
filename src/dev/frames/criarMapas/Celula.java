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
	private void iniciarPanel(){
		imagePanel = new JImagePanel(null, Bloco.BLOCO_WIDTH, Bloco.BLOCO_HEIGHT);		
	}
	
	public JPanel getPanel(){
		return imagePanel;
	}
	
	private void iniciarclick(){
		imagePanel.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent args){
				clickEvt.click(bloco);
			}			
		});
	}
	
	public void setBloco(Bloco bloco){
		this.bloco = bloco;
		imagePanel.setImage(bloco.getImage());
	}
}
