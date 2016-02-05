package dev.util.imports;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assets {
	private static ArrayList<BufferedImage> imagens;
	private static ArrayList<int[][]> mapas;

	private static void importImagens(){
		imagens = new ArrayList<BufferedImage>();

		ArrayList<String> paths = new ArrayList<String>();

		paths.add("/imagens/bloco_1.jpg");
		paths.add("/imagens/bloco_2.jpg");
		paths.add("/imagens/bloco_3.jpg");
		paths.add("/imagens/bola_1.png");

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

		paths.add("./res/worlds/world_1");

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
				linha = buffer.readLine();
				width = Integer.parseInt(linha);

				linha = buffer.readLine();
				height = Integer.parseInt(linha);

				int[][] mapa = new int[height][width];

				for(int i = 0; i < height; i++){
					linha = buffer.readLine();

					for(int j = 0; j < width; j++){
						int a = Integer.parseInt(linha.charAt(j)+"");
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
		if(mapas == null){
			importMapas();
		}
		return mapas.get(catalog.getValor());
	}

}
