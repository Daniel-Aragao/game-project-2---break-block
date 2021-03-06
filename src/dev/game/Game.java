package dev.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.frames.MainFrame;
import dev.inputs.Keyboard;
import dev.needs.GameStateNeeds;
import dev.states.GameState;
import dev.states.MenuState;
import dev.states.StateControl;
import dev.util.fpsControl.FpsControl;
import dev.util.fpsControl.IFpsInformer;

public class Game implements Runnable{

	private MainFrame mainFrame;

	private GameState gameState;
	private GameStateNeeds gameStateNeeds;
	private MenuState menuState;

	private Thread gameThread;

	private boolean gameLoop;

	private Graphics g;
	private BufferStrategy bs;
	private Keyboard keyboard;

	public Game(){
		gameLoop = false;
	}

	private void init(){
		mainFrame = new MainFrame();
		keyboard = new Keyboard();

		mainFrame.getFrame().addKeyListener(keyboard);

		gameStateNeeds = new GameStateNeeds(keyboard);

		gameState = new GameState(this.gameStateNeeds);
		menuState = new MenuState();
		StateControl.setState(gameState);
	}

	public void start() {
		if(gameLoop) return;

		gameLoop = true;
		this.gameThread = new Thread(this, "Game Thread");
		this.gameThread.start();
	}

	@Override
	public void run() {
		init();
		FpsControl.addIFpsInformer(new IFpsInformer() {

			@Override
			public void FpsExibition(int frames) {
				mainFrame.getFrame().setTitle(MainFrame.MAIN_FRAME_TITLE+" - FPS: "+frames);

			}
		});

		while(gameLoop){
			FpsControl.loopStart();

			if(FpsControl.isUpdateTime()){
				update();
				draw();
			}

			FpsControl.loopEnd();
		}

		end();

	}

	private void update() {
		keyboard.update();

		if(StateControl.getState() != null){
			StateControl.getState().update();
		}

	}

	private void draw() {
		bs = mainFrame.getCanvas().getBufferStrategy();
		if(bs == null){
			mainFrame.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		//clear
		g.clearRect(0, 0, MainFrame.MAIN_FRAME_DIMENSION.width, MainFrame.MAIN_FRAME_DIMENSION.height);

		//draw
		if(StateControl.getState() != null){
			StateControl.getState().Draw(g);
		}

		//end drawing
		bs.show();
		g.dispose();

	}

	public void end(){
		if(!gameLoop) return ;

		gameLoop = false;

		try {
			gameThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
