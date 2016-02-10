package dev.worlds;

import java.awt.Graphics;

import dev.entitys.blocos.Bloco;
import dev.frames.MainFrame;

public class Mapa {
	public static final int MAPA_WIDTH = MainFrame.MAIN_FRAME_DIMENSION.width / Celula.CELULA_WIDTH,
							MAPA_HEIGHT = MainFrame.MAIN_FRAME_DIMENSION.height / Celula.CELULA_HEIGHT;

	private Celula[][] celulas;

	private int width, height;



	public Mapa(int width, int height){
		this.width = MAPA_WIDTH;
		this.height = MAPA_HEIGHT;

		celulas = new Celula[height][width];
	}

	public Celula getCelula(int i, int j){
		return celulas[i][j];
	}

	public void setMaping(int[][] maping) {
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				celulas[i][j] = new Celula(i, j);

				int valor = maping[i][j];
				if (valor > 0){
					Bloco bloco = new Bloco(j*Bloco.BLOCO_WIDTH, i*Bloco.BLOCO_HEIGHT, valor);
					celulas[i][j].addElement(bloco);
				}else if( valor == -2){

				}else if( valor == -1){

				}
			}
		}
	}

	public void draw(Graphics g){
		for(int i = 0; i < celulas.length; i++){
			for(int j = 0; j < celulas[0].length; j++){
				if(!celulas[i][j].isEmpty()){
					celulas[i][j].draw(g);
				}
			}
		}
	}
	public void update(){
		for(int i = 0; i < celulas.length; i++){
			for(int j = 0; j < celulas[0].length; j++){
				if(!celulas[i][j].isEmpty()){
					celulas[i][j].update();
				}
			}
		}
	}
}
