package dev.util;

public enum BackgroundSoundID {
	game(0),
	menu(1);
	
	private final int index;
	

	BackgroundSoundID(int index){
		this.index = index;
	}
	public int getindex(){return this.index;}
}
