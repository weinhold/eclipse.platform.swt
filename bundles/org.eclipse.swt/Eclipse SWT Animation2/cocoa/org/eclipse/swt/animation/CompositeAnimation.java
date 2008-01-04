package org.eclipse.swt.animation;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;

public class CompositeAnimation extends Animation {
	int childCount = 0;
	Animation[] animations = new Animation[4];	

	public void addAnimation(Animation animation) {
		if (animations.length == childCount) {
			Animation[] temp = new Animation[childCount + 4];
			System.arraycopy(animations, 0, temp, 0, childCount);
			animations = temp;
		}
		animations[childCount] = animation;
		childCount++;
	}

	public void childFinished(Animation child) {
	}
	
	public Animation getAnimation(int i) {
		checkAnimation();
		if (0>i || i>=childCount) SWT.error(SWT.ERROR_INVALID_ARGUMENT);
		return animations[i];
	}
	
	boolean isFinished() {
		for (int i = 0; i < childCount; i++) {
			Animation a = animations[i];
			if (!a.isFinished()) return false;
		}
		return true;
	}
	
	boolean isRunning() {
		for (int i = 0; i < childCount; i++) {
			Animation a = animations[i];
			if (a.isRunning()) return true;
		}
		return false;
	}
		
	void release() {
		super.release();
		for (int i = 0; i < childCount; i++) {
			Animation a = animations[i];
			a.release();
		}
	}
	
	void setWidget(Widget widget) {
		this.widget = widget;
		for (int i = 0; i < childCount; i++) {
			Animation a = animations[i];
			a.setWidget(widget);
		}
	}

	public void start(Widget widget) {
		this.widget = widget;
		for (int i = 0; i < childCount; i++) {
			Animation a = animations[i];
			a.start(widget);
		}
	}

}
