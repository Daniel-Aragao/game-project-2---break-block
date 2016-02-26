package dev.entitys.blocos;

import dev.entitys.Entity;

public class BlocoWeak extends Bloco{

	public BlocoWeak(double x, double y, int valor) {
		super(x, y, valor, true, true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void colided(Entity sponsor) {
		if(this.getBlocoHittedhandler()!= null){
			this.getBlocoHittedhandler().hitAction(this);
		}

	}

}
