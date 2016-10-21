package dev.listeners;

import java.awt.Container;

import dev.states.EStates;

public interface IStateListener {
	void StateChanged(EStates state);
	void SetContentPane(Container c);
}
