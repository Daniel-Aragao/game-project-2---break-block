package dev.util.fpsControl;

import java.util.ArrayList;

public class FpsControl {
	private static int fps = 60;
	private static double timePerTick = 1000000000/fps;
	private static double delta = 0;
	private static long now;
	private static long lastTime = System.nanoTime();
	private static long timer = 0;
	private static int ticks = 0;
	private static ArrayList<IFpsInformer> fpsInformerList = new ArrayList<IFpsInformer>();

	public static void loopStart(){
		now = System.nanoTime();
		delta += (now - lastTime) / timePerTick;
		timer += now - lastTime;
		lastTime = now;
	}

	public static boolean isUpdateTime(){
		if(delta >= 1){
			ticks++;
			delta--;
			return true;
		}
		return false;
	}
	public static double getDelta(){
		return delta;
	}

	public static void loopEnd(){
		if(timer >= 1000000000){
			iFpsInformerListReader();
			ticks = 0;
			timer = 0;
		}
	}

	private static void iFpsInformerListReader(){
		for(IFpsInformer informer : fpsInformerList){
			if(informer != null)
				informer.FpsExibition(ticks);
		}
	}

	public static void addIFpsInformer(IFpsInformer informer){
		fpsInformerList.add(informer);
	}
}
