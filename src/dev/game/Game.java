package dev.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.frames.MainFrame;
import dev.states.GameState;
import dev.states.State;
import dev.states.StateControl;
import dev.util.fpsControl.FpsControl;

public class Game implements Runnable{

	private MainFrame mainFrame;
	private GameState gameState;

	private Thread gameThread;

	private boolean gameLoop;

	private Graphics g;
	private BufferStrategy bs;

	public Game(){
		gameLoop = false;
	}

	private void init(){
		mainFrame = new MainFrame();

		gameState = new GameState();
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
