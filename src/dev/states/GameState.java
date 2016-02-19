package dev.states;

import java.awt.Graphics;

import dev.inputs.Keyboard;
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

	public GameState(GameStateNeeds gameStateNeeds){
		this.keyboard = gameStateNeeds.getKeyboard();

		this.mapNeed = new MapNeeds(keyboard, Mapa.MAPA_WIDTH, Mapa.MAPA_HEIGHT, Assets.loadMap(MapCatalog.primeiro));

		mapa = new Mapa(mapNeed);
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
}
