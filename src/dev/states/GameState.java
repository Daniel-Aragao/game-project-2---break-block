package dev.states;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import dev.inputs.Keyboard;
import dev.listeners.IKeyPressedListener;
import dev.needs.GameStateNeeds;
import dev.needs.MapNeeds;
import dev.util.imports.Assets;
import dev.util.imports.ImageCatalog;
import dev.util.imports.MapCatalog;
import dev.worlds.Mapa;

public class GameState extends State{

	// matriz mapa 20x30, cada bloco ocupará 2 casas na horizontal (blocos 40x20)(pixels / célula 40x20)
	private Mapa mapa;
	private Keyboard keyboard;
	private MapNeeds mapNeed;
	private JPanel canvasPanel;

	public GameState(GameStateNeeds gameStateNeeds){
		super(gameStateNeeds.getStateListener(), EStates.Game);
		this.keyboard = gameStateNeeds.getKeyboard();
		keyboard.setKeyPressedListener(new IKeyPressedListener() {

			@Override
			public void keyPressed(KeyEvent keyEvent) {
				if(KeyEvent.VK_P == keyEvent.getKeyCode()){
					mapa.pause();
				}else if(KeyEvent.VK_B == keyEvent.getKeyCode() || KeyEvent.VK_SPACE == keyEvent.getKeyCode()){
					mapa.moveBall();
				}

			}
		});
		this.mapNeed = new MapNeeds(keyboard, Mapa.MAPA_WIDTH, Mapa.MAPA_HEIGHT, Assets.loadMap(MapCatalog.primeiro));

		mapa = new Mapa(mapNeed);
		this.canvasPanel = gameStateNeeds.getCanvasPanel();
	}


	@Override
	public void update() {
		mapa.update();

	}

	@Override
	public void Draw(Graphics g) {
//		g.fillRect(0, 0, MainFrame.MAIN_FRAME_DIMENSION.width, MainFrame.MAIN_FRAME_DIMENSION.height);
		g.drawImage(Assets.getImage(ImageCatalog.background_1), 0, 0, null);
		mapa.draw(g);

	}


	@Override
	public void changeToState(EStates State) {
		// Salvar dados do usuário nesta etapa, ou na própria troca na controladora mais externa classe "Game"
		this.StateListener.StateChanged(State);
		
	}


	@Override
	public Container getPanel() {
		return canvasPanel;
	}
}
