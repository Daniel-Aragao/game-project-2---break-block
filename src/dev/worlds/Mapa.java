package dev.worlds;

import java.awt.Graphics;

import dev.entitys.blocos.Bloco;

public class Mapa {
	private Celula[][] celulas;

	private int width, height;

	public Mapa(int width, int height){
		this.width = width;
		this.height = height;

		celulas = new Celula[height][width];
	}

	public void setMaping(int[][] maping) {
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				celulas[i][j] = new Celula(i, j);

				int valor = maping[i][j];
				switch(valor){
					case -2:
						break;
					case -1:
						break;
					case 0:
						break;
					case 1:
					case 2:
					case 3:
						Bloco bloco = new Bloco(j*Bloco.BLOCO_WIDTH, i*Bloco.BLOCO_HEIGHT, valor);
						celulas[i][j].addElement(bloco);
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
