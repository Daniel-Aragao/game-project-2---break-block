package dev.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dev.listeners.IKeyPressedListener;

public class Keyboard implements KeyListener{
	public static int MOVEMENT_KEYS[] = {KeyEvent.VK_A,KeyEvent.VK_D, KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT};

	private IKeyPressedListener keyPressedListener;
	private boolean left;
	private boolean right;

	private boolean[] keys;

	public Keyboard(){
		keys = new boolean[256];

		setLeft(false);
		setRight(false);
	}

	public void update(){
		left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
	}


	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() >= keys.length) return;
		keys[k.getKeyCode()] = true;

//		if(keyPressedListener != null){
//		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		if(k.getKeyCode() >= keys.length) return;
		keys[k.getKeyCode()] = false;

//		if(keyPressedListener != null){
//		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public boolean isRelevantKey(int key){
		for(int e : MOVEMENT_KEYS){
			if (e == key){
				return true;
			}
		}

		return false;
	}

	public static boolean isLeft(int key){
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
			return true;
		}

		return false;
	}
	public static boolean isRight(int key){
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
			return true;
		}

		return false;
	}

	public IKeyPressedListener getKeyPressedListener() {
		return keyPressedListener;
	}

	public void setKeyPressedListener(IKeyPressedListener keyPressedListener) {
		this.keyPressedListener = keyPressedListener;
	}


	public boolean isRight() {
		return right;
	}


	public void setRight(boolean right) {
		this.right = right;
	}


	public boolean isLeft() {
		return left;
	}


	public void setLeft(boolean left) {
		this.left = left;
	}

}
