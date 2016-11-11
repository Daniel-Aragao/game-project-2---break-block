package dev.util.imports;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import dev.util.BackgroundSoundID;
import dev.worlds.Mapa;

public class Assets {
	private static ArrayList<BufferedImage> imagens;
	private static ArrayList<int[][]> mapas;
	private static int alteracoes = 0;

	private static void importImagens(){
		imagens = new ArrayList<BufferedImage>();

		ArrayList<String> paths = new ArrayList<String>();

		paths.add("/imagens/bloco_1.png");
		paths.add("/imagens/bloco_2.png");
		paths.add("/imagens/bloco_3.png");
		paths.add("/imagens/bloco_4.png");
		paths.add("/imagens/bloco_5.png");
		paths.add("/imagens/bloco_6.png");
		paths.add("/imagens/bloco_7.png");
		paths.add("/imagens/bloco_8.png");
		paths.add("/imagens/bloco_9.png");
		paths.add("/imagens/bloco_10.png");
		paths.add("/imagens/bola_3.png");
		paths.add("/imagens/background_1.jpg");
		paths.add("/imagens/palheta_1.png");
		paths.add("/imagens/coracao.png");
		paths.add("/imagens/Game_Over.png");
		paths.add("/imagens/icon.png");
		paths.add("/imagens/Save16.gif");
		paths.add("/imagens/Back24.gif");
		paths.add("/imagens/middle.gif");
//		paths.add("/imagens/menubackground.png");

		for(String path : paths){
			imagens.add(ImageLoader.loadImage(path));
		}

	}

	public static BufferedImage getImage(ImageCatalog catalog){
		if(imagens == null) importImagens();
		return imagens.get(catalog.getValor());
	}
	private static void importMapas(){
		mapas = new ArrayList<int[][]>();

		ArrayList<String> paths = new ArrayList<String>();
		
		paths.add("./res/worlds/world_0.txt");
		paths.add("./res/worlds/world_1.txt");
		paths.add("./res/worlds/world_2.txt");
		paths.add("./res/worlds/world_3.txt");
		paths.add("./res/worlds/world_4.txt");
		paths.add("./res/worlds/world_5.txt");
		paths.add("./res/worlds/world_6.txt");
		paths.add("./res/worlds/world_7.txt");
		paths.add("./res/worlds/world_8.txt");
		paths.add("./res/worlds/world_9.txt");
		
		paths.add("./res/worlds/default_world_0.txt");
		paths.add("./res/worlds/default_world_1.txt");
		paths.add("./res/worlds/default_world_2.txt");
		paths.add("./res/worlds/default_world_3.txt");
		paths.add("./res/worlds/default_world_4.txt");
		paths.add("./res/worlds/default_world_5.txt");
		paths.add("./res/worlds/default_world_6.txt");
		paths.add("./res/worlds/default_world_7.txt");
		paths.add("./res/worlds/default_world_8.txt");
		paths.add("./res/worlds/default_world_9.txt");
		

		FileReader reader = null;
		BufferedReader buffer = null;

		for(String path : paths){
			try {
				reader = new FileReader(path);
				buffer = new BufferedReader(reader);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String linha;
			try {

				int width, height;

				width = Mapa.MAPA_WIDTH;

				height = Mapa.MAPA_HEIGHT;

				int[][] mapa = new int[height][width];

				for(int i = 0; i < height; i++){
					linha = buffer.readLine();

					String[] aux = linha.split(" ");

					for(int j = 0; j < width; j++){
						int a = Integer.parseInt(aux[j]);
						mapa[i][j] = a;
					}

				}

				mapas.add(mapa);
//				for(int i = 0; i < mapa.length; i++){
//					System.out.println();
//					for(int j = 0; j < mapa[0].length; j++){
//						System.out.print(mapa[i][j]);
//					}
//				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}



	}

	public static int[][] loadMap(MapCatalog catalog) {
		if(mapas == null || alteracoes > 0){
			importMapas();
			alteracoes = 0;
		}
		return mapas.get(catalog.getValor());
	}
	public static int[][] loadMap(int fase) {
		if(mapas == null || alteracoes > 0){
			importMapas();
			alteracoes = 0;
		}
		return mapas.get(fase);
	}
	
	public static int getNMapas(){
		return 10;
	}
	
	public static void salvarMapa(int[][]mapa, int nivel){
		
		try {
			FileWriter arquivo= new FileWriter("./res/worlds/world_"+nivel+".txt",false);
			PrintWriter gravararq= new PrintWriter(arquivo,true);
			
			for(int i = 0; i < Mapa.MAPA_HEIGHT; i++){
				String linha = "";
				for(int j = 0; j < Mapa.MAPA_WIDTH; j++){
					linha += mapa[i][j]+" ";
				}
				gravararq.printf(linha+"%n");
			}
			
			arquivo.close();
			alteracoes++;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static AudioInputStream getSound(BackgroundSoundID bg) {
		try {
			if(bg == BackgroundSoundID.menu){
				return AudioSystem.getAudioInputStream(new File("./res/Bgm/Give me your happiness.wav"));				
			}else if(bg == BackgroundSoundID.game){
				return AudioSystem.getAudioInputStream(new File("./res/Bgm/One Step Closer.wav"));				
			} 
			
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
