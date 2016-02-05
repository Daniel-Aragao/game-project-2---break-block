package dev.util.colisions;

import dev.entitys.Entity;
import dev.entitys.creatures.Creature;

public class Colision {

	public static boolean checkColision(Creature creature, Entity entity){
//invert creature speeds
		if(checkXColision(creature, entity)){
			creature.setxMove(0);

			return true;
		}
		if(checkYColision(creature, entity)){
			return true;
		}


		return false;
	}
	private static boolean checkXColision(Creature creature, Entity entity){

		return false;
	}

	private static boolean checkYColision(Creature creature, Entity entity){

		return false;
	}
}
