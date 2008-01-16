package org.eclipse.swt.animation;

import org.eclipse.swt.widgets.*;


public class SequenceAnimation extends CompositeAnimation {
	
	int current;
	boolean stopped;
	
	public void start(Widget widget) {
		current = 0;
		stopped = false;
		this.widget = widget;
		for (int i = 0; i < childCount; i++) {
			Animation a = animations[i];
			a.setParent(this);
		}
		if (animations != null && childCount > 0) animations[0].start(widget);
	}
	
	public void childFinished(Animation child) {
		if (stopped) return;
		current++;
		if (current < childCount) animations[current].start(widget);
	}
	
	public void stop() {
		stopped = true;
		if (current < childCount) animations[current].stop();
	}
}
