package dev.entitys.blocos;

import dev.entitys.Entity;

public class BlocoMassive extends Bloco{

	public BlocoMassive(double x, double y, int valor) {
		super(x, y, valor, true, false);
	}

	@Override
	public void colided(Entity sponsor) {
		// TODO Auto-generated method stub

	}

}
