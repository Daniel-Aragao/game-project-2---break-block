package dev.states;

import java.awt.Graphics;

import dev.frames.MainFrame;
import dev.util.imports.Assets;
import dev.util.imports.ImageCatalog;
import dev.util.imports.MapCatalog;
import dev.worlds.Mapa;

public class GameState extends State{

	// matriz mapa 20x30, cada bloco ocupará 2 casas na horizontal (blocos 40x20)(pixels / célula 40x20)
	private Mapa mapa;

//	private Player;
//	private Bola;

	public GameState(){
		mapa = new Mapa(20, 30);
		mapa.setMaping(Assets.loadMap(MapCatalog.primeiro));
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Draw(Graphics g) {
//		g.fillRect(0, 0, MainFrame.MAIN_FRAME_DIMENSION.width, MainFrame.MAIN_FRAME_DIMENSION.height);
		g.drawImage(Assets.getImage(ImageCatalog.background_1), 0, 0, null);
		mapa.draw(g);

	}
}
