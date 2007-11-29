package org.eclipse.swt.animation;


public class SequenceAnimation extends CompositeAnimation {
	long updateDuration(long delay) {
		for (int i = 0; i < childCount; i++) {
			Animation a = animations[i];
			delay += a.updateDuration(delay);
		}
		return delay;
	}
}
