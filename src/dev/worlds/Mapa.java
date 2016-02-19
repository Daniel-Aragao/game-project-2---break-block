package dev.worlds;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import dev.entitys.Entity;
import dev.entitys.blocos.Bloco;
import dev.entitys.blocos.BlocoMassive;
import dev.entitys.blocos.BlocoWeak;
import dev.entitys.creatures.Bola;
import dev.entitys.creatures.Player;
import dev.frames.MainFrame;
import dev.listeners.IBlocoHittedListener;
import dev.needs.MapNeeds;
import dev.needs.PlayerNeeds;

public class Mapa {
	public static final int CELULA_WIDTH = 40,
			CELULA_HEIGHT = 20;

	public static final int MAPA_WIDTH = MainFrame.MAIN_FRAME_DIMENSION.width / CELULA_WIDTH,
			MAPA_HEIGHT = MainFrame.MAIN_FRAME_DIMENSION.height / CELULA_HEIGHT;

	private ArrayList<Entity> elementos;

	private int width, height;

	private int blocosCounter;

	private Player player;
	private Bola bola;
	MapNeeds needs;

	public Mapa(MapNeeds needs) {
		this.needs = needs;
		this.width = needs.getWidth();
		this.height = needs.getHeight();

		blocosCounter = 0;

		elementos = new ArrayList<Entity>();

		setMaping(needs.getMaping());


	}

	private void setMaping(int[][] maping) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				int valor = maping[i][j];

				if (valor > 0) {
					Bloco bloco = null;

					if (valor == 10) {
						bloco = new BlocoMassive(j * Bloco.BLOCO_WIDTH, i * Bloco.BLOCO_HEIGHT, valor);
					} else {
						bloco = new BlocoWeak(j * Bloco.BLOCO_WIDTH, i * Bloco.BLOCO_HEIGHT, valor);
						blocosCounter++;
						bloco.setBlocoHittedhandler(new BlocoHittedHandler());

					}

					elementos.add(bloco);

				} else if (valor == -2) {
					if (player == null) {
						PlayerNeeds playerNeeds =  new PlayerNeeds(needs.getKeyboard(),j * Bloco.BLOCO_WIDTH, i * Bloco.BLOCO_HEIGHT,
								Player.PLAYER_DEFAULT_WIDTH, Player.PLAYER_DEFAULT_HEIGHT);
						this.player = new Player(playerNeeds);

						elementos.add(player);
					}


				} else if (valor == -1) {
					if (bola == null) {
						this.bola = new Bola(j * Bloco.BLOCO_WIDTH, i * Bloco.BLOCO_HEIGHT, Bola.BOLA_DEFAULT_WIDTH,
								Bola.BOLA_DEFAULT_HEIGHT);
						elementos.add(bola);
					}

				}
			}
		}
	}

	public void draw(Graphics g) {
		for (Entity e : this.elementos){
			e.draw(g);
		}

		g.setColor(Color.black);
		g.drawString("Blocos restantes: " + this.blocosCounter, 40,
				MainFrame.MAIN_FRAME_DIMENSION.height - Bloco.BLOCO_HEIGHT / 2 + 5);
	}

	public void update() {
		for (Entity e : this.elementos){
			e.update();
		}
	}

	class BlocoHittedHandler implements IBlocoHittedListener {

		@Override
		public void hitAction(Bloco bloco) {
//			bloco.setValor(bloco.getValor() - 1);
//			if (bloco.getValor() <= 0) {
//				blocosCounter--;
//				for (int i = 0; i < celulas.length; i++) {
//					for (int j = 0; j < celulas[0].length; j++) {
//						celulas[i][j].removeElement(bloco);
//					}
//				}
//			}
		}

	}
}
