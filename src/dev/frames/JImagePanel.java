package dev.frames;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class JImagePanel extends JPanel{
	/**
	 * Painel com imagem de fundo
	 */
	private static final long serialVersionUID = 1L;
	private Image imagem;
	private int width;
	private int height;
	
	
	public JImagePanel(Image imagem, int width, int height) {
		this.imagem = imagem;
		this.width = width;
		this.height = height;
		
//		this.setFocusable(false);
		
		Dimension d = new Dimension(width, height);
		this.setSize(d);
		this.setPreferredSize(d);
	}
	
	public void setImage(Image image){
		this.imagem = image;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics gr = g.create();

		gr.clearRect(0, 0, width, height);
		
		if(imagem != null)
			gr.drawImage(this.imagem, 0, 0, width, height, null);
		
		gr.dispose();
	}
}
