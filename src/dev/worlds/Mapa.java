package dev.worlds;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import dev.entitys.Entity;
import dev.entitys.Wall.Wall;
import dev.entitys.blocos.Bloco;
import dev.entitys.blocos.BlocoMassive;
import dev.entitys.blocos.BlocoWeak;
import dev.entitys.creatures.Bola;
import dev.entitys.creatures.Player;
import dev.frames.MainFrame;
import dev.listeners.IBlocoHittedListener;
import dev.listeners.IWallColisionEffects;
import dev.needs.CreatureNeeds;
import dev.needs.MapNeeds;
import dev.needs.PlayerNeeds;
import dev.states.EStates;
import dev.states.MenuState;
import dev.states.StateControl;
import dev.util.GameOverTools;
import dev.util.imports.Assets;
import dev.util.imports.ImageCatalog;

public class Mapa {
	public static final int CELULA_WIDTH = 40, CELULA_HEIGHT = 20;

	public static final int MAPA_WIDTH = MainFrame.MAIN_FRAME_DIMENSION.width / CELULA_WIDTH,
			MAPA_HEIGHT = /*30;*/MainFrame.MAIN_FRAME_DIMENSION.height / CELULA_HEIGHT;
	public static final int HUD_Y = MainFrame.MAIN_FRAME_DIMENSION.height - Bloco.BLOCO_HEIGHT / 2 + 5;
	public static final int HUD_INITIAL_X = 40;
	public static final int SCORE_FOR_LIFE = 500;

	private ArrayList<Entity> elementos;
	private ArrayList<Entity> elementosRemovidos;
	private ArrayList<Entity> elementosAdd;

	private int width, height;

	private int blocosCounter;
	private int fase;

	private Player player;
	private Bola bola;
	private MapNeeds needs;
	private CreatureNeeds bolaNeeds;

	private boolean pause;

	private GameOverTools gameOverTools;

	public Mapa(MapNeeds needs) {
		this.needs = needs;
		this.width = needs.getWidth();
		this.height = needs.getHeight();

		blocosCounter = 0;
		fase = 0;
		pause = false;

		elementos = new ArrayList<Entity>();
		elementosRemovidos = new ArrayList<Entity>();
		elementosAdd = new ArrayList<Entity>();

		setMaping(needs.getMaping());
		setBounds();

		gameOverTools = new GameOverTools();
	}

	private void setBounds() {
		Wall northWall = new Wall(0, -20, MainFrame.MAIN_FRAME_DIMENSION.width, 20);
		elementos.add(northWall);

		Wall southWall = new Wall(0, MainFrame.MAIN_FRAME_DIMENSION.height, MainFrame.MAIN_FRAME_DIMENSION.width, 20);
		elementos.add(southWall);
		southWall.setSolid(false);

		Wall southkillerWall = new Wall(0, MainFrame.MAIN_FRAME_DIMENSION.height + CELULA_HEIGHT,
				MainFrame.MAIN_FRAME_DIMENSION.width, 20);
		southkillerWall.setWallColisionEffects(new WallColisionEffects());
		elementos.add(southkillerWall);

		Wall eastWall = new Wall(MainFrame.MAIN_FRAME_DIMENSION.width, 0, 20, MainFrame.MAIN_FRAME_DIMENSION.height);
		elementos.add(eastWall);

		Wall westWall = new Wall(-20, 0, 20, MainFrame.MAIN_FRAME_DIMENSION.height);
		elementos.add(westWall);
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
						PlayerNeeds playerNeeds = new PlayerNeeds(needs.getKeyboard(), j * Bloco.BLOCO_WIDTH,
								i * Bloco.BLOCO_HEIGHT, Player.PLAYER_DEFAULT_WIDTH, Player.PLAYER_DEFAULT_HEIGHT,
								this.elementos);
						this.player = new Player(playerNeeds);

						elementos.add(player);
					}

				} else if (valor == -1) {
					if (bola == null) {
						bolaNeeds = new CreatureNeeds(j * Bloco.BLOCO_WIDTH, i * Bloco.BLOCO_HEIGHT,
								Bola.BOLA_DEFAULT_WIDTH, Bola.BOLA_DEFAULT_HEIGHT, elementos);
						this.bola = new Bola(bolaNeeds);
						elementos.add(bola);
					} else {
						bola.setMoveable(false);
					}

				}
			}
		}
	}

	public void draw(Graphics g) {
		for (Entity e : this.elementos) {
			e.draw(g);
		}

		g.setColor(Color.black);
		g.drawString("Blocos restantes: " + this.blocosCounter, HUD_INITIAL_X, HUD_Y);
		g.drawString("Fase: " + (fase + 1), MainFrame.MAIN_FRAME_DIMENSION.width / 2, HUD_Y);
		g.drawString("Scores: " + player.getScores(), MainFrame.MAIN_FRAME_DIMENSION.width * 2 / 3, HUD_Y);

		if (!gameOverTools.isGameOver()) {
			if (!pause) {
				removalFromRemoveList();
				addElements();
			}
		} else {
			g.drawImage(Assets.getImage(ImageCatalog.game_over),
					MainFrame.MAIN_FRAME_DIMENSION.width / 2 - Assets.getImage(ImageCatalog.game_over).getWidth() / 2,
					MainFrame.MAIN_FRAME_DIMENSION.height / 2 - Assets.getImage(ImageCatalog.game_over).getHeight() / 2,
					null);
		}
	}

	public void update() {
		if (!gameOverTools.isGameOver()) {
			if (!pause) {
				for (Entity e : this.elementos) {
					e.update();
				}
				if (!bola.isMoveable()) {
					bola.setX(player.getX() + Bloco.BLOCO_WIDTH);
					bola.setY(player.getY() - Bola.BOLA_DEFAULT_HEIGHT);
				}
				if (blocosCounter == 0) {
					changeMap();
				}
			}
		}else{
			if(gameOverTools.isFinished()){
				StateControl.getState().changeToState(EStates.FimJogo);
			}
		}
	}

	public void changeMap() {
		if (fase < Assets.getNMapas() - 1) {
//			setMaping(Assets.loadMap(MapCatalog.getItem(fase + 1)));
			setMaping(Assets.loadMap(fase + 1));
			fase++;
		} else {
			GameOver();
		}
	}

	public void removeEntity(Entity entity) {
		if (this.elementos.contains(entity)) {
			this.elementosRemovidos.add(entity);
		}
	}

	private void removalFromRemoveList() {
		for (Entity e : this.elementosRemovidos) {
			elementos.remove(e);
		}
		elementosRemovidos = new ArrayList<Entity>();
	}

	public void addInElementList(Entity entity) {
		if (!elementos.contains(entity)) {
			elementosAdd.add(entity);
		}
	}

	private void addElements() {
		for (Entity e : elementosAdd) {
			elementos.add(e);
		}
		elementosAdd = new ArrayList<Entity>();
	}

	private void GameOver() {
		gameOverTools.setGameOver(true);
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public void pause() {
		this.pause = !this.pause;
	}

	public void moveBall() {
		if (bola != null) {
			if (!bola.isMoveable()) {
				bola.setMoveable(true);
			}
		}

	}
	
	public long getScores(){
		return player.getScores();
	}

	class BlocoHittedHandler implements IBlocoHittedListener {

		@Override
		public void hitAction(Bloco bloco) {
			bloco.setValor(bloco.getValor() - 1);

			player.setScores(player.getScores() + 10);
			scoresToLife();

			if (bloco.getValor() == 0) {
				blocosCounter--;
				removeEntity(bloco);
			}
		}

		public void scoresToLife() {
			long scores = player.getScores() - player.getLastScores();
			int lifes = (int) (scores / SCORE_FOR_LIFE);

			if (lifes > 0) {
				if (player.getLifes() < Player.MAX_LIFES) {
					player.setLifes(player.getLifes() + lifes);

				}
				player.setLastScores(player.getScores());
			}
		}

	}

	class WallColisionEffects implements IWallColisionEffects {

		@Override
		public void ballColision(Bola b) {
			removeEntity(b);
			player.setLifes(player.getLifes() - 1);

			if (player.getLifes() < 0) {
				//System.out.println("Game Over");
				GameOver();
			} else {
				bola = new Bola(bolaNeeds);
				addInElementList(bola);
			}

		}
	}
}
