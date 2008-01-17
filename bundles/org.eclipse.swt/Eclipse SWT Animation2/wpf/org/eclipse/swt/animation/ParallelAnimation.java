package org.eclipse.swt.animation;

public class ParallelAnimation extends CompositeAnimation {
	long updateDuration(long delay) {
		long duration = 0;
		for (int i = 0; i < childCount; i++) {
			Animation a = animations[i];
			duration = Math.max(a.updateDuration(delay), duration);
		}
		return delay+beginTime+duration;
	}
}
