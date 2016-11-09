package dev.listeners;

import java.awt.Component;

import dev.states.EStates;

public interface IStateListener {
	void StateChanged(EStates state);
	void SetContentPane(Component c, Component b);
}
