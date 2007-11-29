package org.eclipse.swt.effects;


public class CompositeEffect extends Effect {
	void createHandle() {
	}
	
	public void addEffect(Effect effect) {
		checkEffect();
	}
	
	public void removeEffect(Effect effect) {
		checkEffect();
	}
}
