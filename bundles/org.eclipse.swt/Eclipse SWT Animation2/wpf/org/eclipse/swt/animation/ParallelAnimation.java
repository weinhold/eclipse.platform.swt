package org.eclipse.swt.animation;

public class ParallelAnimation extends CompositeAnimation {
	long updateDuration(long delay) {
		for (int i = 0; i < childCount; i++) {
			Animation a = animations[i];
			a.updateDuration(delay);
		}
		return delay+beginTime+duration;
	}
}
