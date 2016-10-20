package dev.util;

public class GameOverTools {
	private boolean gameOver;
	private long gameOverTimer;
	private long timeLimit;
	private long lastTime;

	public GameOverTools(){
		setGameOver(false);
		timeLimit = 5000;
		gameOverTimer = 0;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public long getGameOverTimer() {
		return gameOverTimer;
	}

	public void setGameOverTimer(long gameOverTimer) {
		this.gameOverTimer = gameOverTimer;
	}

	public long getTimeLimit() {

		return timeLimit;
	}

	public void setTimeLimit(long timeLimit) {
		this.timeLimit = timeLimit;
	}
	
	public boolean isFinished(){
		if(gameOverTimer >= timeLimit){
			return true;
		}
		if(lastTime != 0){
			gameOverTimer += System.currentTimeMillis() - lastTime;			
		}
		lastTime = System.currentTimeMillis();
		return false;
	}
}
