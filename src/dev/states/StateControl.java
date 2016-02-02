package dev.states;

public class StateControl {
	private static State currentState = null;

	public StateControl(State state){
		StateControl.currentState = state;
	}

	public static void setState(State state){
		StateControl.currentState = state;
	}

	public static State getState(){
		return StateControl.currentState;
	}
}
