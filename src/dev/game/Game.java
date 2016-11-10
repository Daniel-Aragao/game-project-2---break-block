package dev.game;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JOptionPane;

import dev.frames.MainFrame;
import dev.inputs.Keyboard;
import dev.listeners.IStateListener;
import dev.needs.GameStateNeeds;
import dev.states.CriarMapaState;
import dev.states.EStates;
import dev.states.GameState;
import dev.states.LoginState;
import dev.states.MenuState;
import dev.states.RankingState;
import dev.states.State;
import dev.states.StateControl;
import dev.util.fpsControl.FpsControl;
import dev.util.fpsControl.IFpsInformer;

public class Game implements Runnable {

	private MainFrame mainFrame;

	private GameState gameState;
	private GameStateNeeds gameStateNeeds;
	private MenuState menuState;
	private LoginState loginState;
	private RankingState rankingState;
	private CriarMapaState criarMapaState;

	private IStateListener stateListener;

	private Thread gameThread;

	private boolean gameLoop;

	private Graphics g;
	private BufferStrategy bs;
	private Keyboard keyboard;
	public static String nomePlayer;

	public Game() {
		gameLoop = false;
		nomePlayer = "player";
	}

	private void init() {
		mainFrame = new MainFrame();
		keyboard = new Keyboard();

		mainFrame.getFrame().addKeyListener(keyboard);

		// gameStateNeeds = new GameStateNeeds(keyboard, getStateListener(),
		// mainFrame.getCanvasPanel());
		gameStateNeeds = new GameStateNeeds(keyboard, getStateListener(), mainFrame.getCanvas());

//		gameState = new GameState(this.gameStateNeeds);
		menuState = new MenuState(getStateListener());
		loginState = new LoginState(getStateListener());
		rankingState = new RankingState(getStateListener());
		criarMapaState = new CriarMapaState(getStateListener());

//		 getStateListener().StateChanged(EStates.Menu);
//		getStateListener().StateChanged(EStates.Login);
//		 getStateListener().StateChanged(EStates.NovoJogo);
		// getStateListener().StateChanged(EStates.Ranking);
		getStateListener().StateChanged(EStates.CriacaoMapa);

		mainFrame.getFrame().setVisible(true);
	}

	public void start() {
		if (gameLoop)
			return;

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
				mainFrame.getFrame().setTitle(MainFrame.MAIN_FRAME_TITLE + " - FPS: " + frames);

			}
		});

		while (gameLoop) {

			FpsControl.loopStart();
			if (FpsControl.isUpdateTime()) {
				update();
				if (StateControl.getState() != null && StateControl.getState().getState() == EStates.Game) {
					draw();
				}
			}
			FpsControl.loopEnd();
		}

		end();

	}

	private void update() {
		keyboard.update();

		if (StateControl.getState() != null) {
			StateControl.getState().update();
		}

	}

	private void draw() {
		bs = mainFrame.getCanvas().getBufferStrategy();
		if (bs == null) {
			mainFrame.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		// clear
		g.clearRect(0, 0, MainFrame.MAIN_FRAME_DIMENSION.width, MainFrame.MAIN_FRAME_DIMENSION.height);

		// draw
		if (StateControl.getState() != null) {
			StateControl.getState().Draw(g);
		}

		// end drawing
		bs.show();
		g.dispose();

	}

	public void end() {
		// if(gameLoop) return ;

		gameLoop = false;

		try {
			gameThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public IStateListener getStateListener() {
		if (stateListener != null) {
			return stateListener;
		}
		stateListener = new IStateListener() {

			@Override
			public void StateChanged(EStates newState) {
				State lastState = StateControl.getState();

				switch (newState) {
				case FimJogo:
					gameState = null;
				case Menu:
					StateControl.setState(menuState);
					break;
				case NovoJogo:
					if(gameState != null){
						int r = JOptionPane.showConfirmDialog(null, "Há um jogo em andamento, "
								+ "tem certeza que deseja sobrescreve-lo?");

						if (r == 1 || r == 2){
							return;
						}
					}
					gameState = new GameState(gameStateNeeds);
					StateControl.setState(gameState);
					break;
				case Login:
					StateControl.setState(loginState);
					break;
				case Continue:
					if (gameState == null){
						JOptionPane.showMessageDialog(null, "Não há jogo para iniciar");
					}else{
						StateControl.setState(gameState);						
					}
					break;
				case Ranking:
					StateControl.setState(rankingState);
					break;
				case CriacaoMapa:
					StateControl.setState(criarMapaState);
					break;
				default:
					System.out.println(newState);
				}
				if (lastState == null) {
					SetContentPane(StateControl.getState().getPanel(), null);
				} else {
					SetContentPane(StateControl.getState().getPanel(), lastState.getPanel());
				}
			}

			@Override
			public void SetContentPane(Component c, Component b) {
				mainFrame.setContentPane(c, b);

			}
		};
		return stateListener;
	}

}
