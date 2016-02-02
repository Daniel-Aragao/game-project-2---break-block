package dev.states;

import java.awt.Graphics;

public abstract class State {

	public abstract void Draw(Graphics g);

	public abstract void update();
}
