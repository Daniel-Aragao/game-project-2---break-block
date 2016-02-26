package dev.util;

public class GameOverTools {
	private boolean gameOver;
	private long gameOverTimer;
	private long timeLimit;

	public GameOverTools(){
		setGameOver(false);
		timeLimit = 5000;
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
}
