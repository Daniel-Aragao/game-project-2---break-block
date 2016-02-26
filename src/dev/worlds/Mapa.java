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
import dev.util.GameOverTools;

public class Mapa {
	public static final int CELULA_WIDTH = 40,
			CELULA_HEIGHT = 20;

	public static final int MAPA_WIDTH = MainFrame.MAIN_FRAME_DIMENSION.width / CELULA_WIDTH,
			MAPA_HEIGHT = MainFrame.MAIN_FRAME_DIMENSION.height / CELULA_HEIGHT;

	private ArrayList<Entity> elementos;
	private ArrayList<Entity> elementosRemovidos;

	private int width, height;

	private int blocosCounter;

	private Player player;
	private Bola bola;
	MapNeeds needs;

	private GameOverTools gameOverTools;

	public Mapa(MapNeeds needs) {
		this.needs = needs;
		this.width = needs.getWidth();
		this.height = needs.getHeight();

		blocosCounter = 0;

		elementos = new ArrayList<Entity>();
		elementosRemovidos = new ArrayList<Entity>();

		setMaping(needs.getMaping());
		setBounds();

		gameOverTools = new GameOverTools();
	}

	private void setBounds() {
		Wall northWall = new Wall(0, -20, MainFrame.MAIN_FRAME_DIMENSION.width, 20);
		elementos.add(northWall);

		Wall southWall = new Wall(0,
				MainFrame.MAIN_FRAME_DIMENSION.height,
				MainFrame.MAIN_FRAME_DIMENSION.width, 20);
		elementos.add(southWall);
		southWall.setSolid(false);

		Wall southkillerWall = new Wall(0,
				MainFrame.MAIN_FRAME_DIMENSION.height+CELULA_HEIGHT,
				MainFrame.MAIN_FRAME_DIMENSION.width, 20);
		southkillerWall.setWallColisionEffects(new WallColisionEffects());
		elementos.add(southkillerWall);

		Wall eastWall = new Wall(MainFrame.MAIN_FRAME_DIMENSION.width, 0, 20,
				MainFrame.MAIN_FRAME_DIMENSION.height);
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
						PlayerNeeds playerNeeds =  new PlayerNeeds(needs.getKeyboard(),j * Bloco.BLOCO_WIDTH, i * Bloco.BLOCO_HEIGHT,
								Player.PLAYER_DEFAULT_WIDTH, Player.PLAYER_DEFAULT_HEIGHT, this.elementos);
						this.player = new Player(playerNeeds);

						elementos.add(player);
					}


				} else if (valor == -1) {
					if (bola == null) {
						CreatureNeeds cretureNeeds = new CreatureNeeds(j * Bloco.BLOCO_WIDTH, i * Bloco.BLOCO_HEIGHT, Bola.BOLA_DEFAULT_WIDTH,
								Bola.BOLA_DEFAULT_HEIGHT, elementos);
						this.bola = new Bola(cretureNeeds);
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
		if(!gameOverTools.isGameOver()){
			for (Entity e : this.elementos){
				e.update();
			}
			removalFromRemoveList();
			if(blocosCounter <= 0){
				System.out.println("Próxima fase");
			}
		}
	}

	class BlocoHittedHandler implements IBlocoHittedListener {

		@Override
		public void hitAction(Bloco bloco) {
			bloco.setValor(bloco.getValor() - 1);
			if (bloco.getValor() <= 0) {
				blocosCounter--;
				removeEntity(bloco);
			}
		}

	}

	public void removeEntity(Entity entity){
		if(this.elementos.contains(entity)){
			this.elementosRemovidos.add(entity);
		}
	}
	private void removalFromRemoveList(){
		for(Entity e : this.elementosRemovidos){
			elementos.remove(e);
		}
	}
	public void addElement(Entity entity){
		throw new RuntimeException("Não implementado");
	}

	class WallColisionEffects implements IWallColisionEffects {



		@Override
		public void ballColision(Bola bola) {
			removeEntity(bola);
			player.setLifes(player.getLifes());
			System.out.println("bola colided");
			if(player.getLifes() < 0){
				System.out.println("Game Over");
				gameOverTools.setGameOver(true);
			}

		}

	}

}
