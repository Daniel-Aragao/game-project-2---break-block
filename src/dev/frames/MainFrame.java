package dev.frames;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;

import dev.util.imports.Assets;
import dev.util.imports.ImageCatalog;

public class MainFrame {
	public static final String MAIN_FRAME_TITLE = "BreaK BlocK";
	public static final Dimension MAIN_FRAME_DIMENSION = new Dimension(800,600);

	private JFrame frame;
	private Canvas canvas;
//	private JPanel panel;

	private String title;
	private Dimension dimension;

	public MainFrame(){
		this.title = MAIN_FRAME_TITLE;
		this.dimension = MAIN_FRAME_DIMENSION;

		createFrame();
	}

	private void createFrame() {
		frame = new JFrame(title);
		frame.setSize(dimension);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.setIconImage(Assets.getImage(ImageCatalog.icon));
//		frame.setVisible(true);

		canvas = new Canvas();
		canvas.setPreferredSize(dimension);
		canvas.setMaximumSize(dimension);
		canvas.setMinimumSize(dimension);
		canvas.setFocusable(false);
		
//		panel = new JPanel();
//		panel.setLayout(null);
//		panel.setPreferredSize(dimension);
//		panel.setFocusable(false);
//		panel.add(canvas,-1);

//		frame.add(canvas);
//		frame.pack();
	}

	public JFrame getFrame() {
		return frame;
	}

	public Canvas getCanvas() {
		return canvas;
	}
	
//	public JPanel getCanvasPanel(){
//		return panel;
//	}
	
	public void setContentPane(Component contentPane, Component lastPanel){
		if (lastPanel != null){
			frame.remove(lastPanel);			
		}
		frame.add(contentPane);
		frame.revalidate();
		frame.repaint();
		frame.pack();
	}

}
