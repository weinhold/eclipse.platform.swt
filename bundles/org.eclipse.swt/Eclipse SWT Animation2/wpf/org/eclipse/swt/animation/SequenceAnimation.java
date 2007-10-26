package org.eclipse.swt.animation;


public class SequenceAnimation extends CompositeAnimation {
	//sets Begin of children 
	
	public SequenceAnimation(int style) {
		super(style);
	}

	long updateDuration(long delay) {
		for (int i = 0; i < childCount; i++) {
			Animation a = animations[i];
			delay += a.updateDuration(delay);
		}
		return delay;
	}
}
