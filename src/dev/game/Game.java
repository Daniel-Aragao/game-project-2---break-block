package dev.game;

import dev.frames.MainFrame;

public class Game implements Runnable{

	private MainFrame mainFrame;

	private Thread gameThread;

	private boolean gameLoop;

	public Game(){
		gameLoop = false;
	}

	private void init(){
		mainFrame = new MainFrame();

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
			draw();
			update();
		}

		end();

	}

	private void update() {
		// TODO Auto-generated method stub

	}

	private void draw() {
		// TODO Auto-generated method stub

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
