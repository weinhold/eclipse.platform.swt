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

	void addChildren() {
//		int children = OS.TimelineGroup_Children(handle);
//		OS.IList_Clear(children);
//		for (int i = 0; i < childCount; i++) {
//			Animation animation = animations[i];
//			OS.IList_Add(children, animation.handle);
//			animation.addChildren();
//		}
//		OS.GCHandle_Free(children);
	}
	
	void create () {
		if (handle == 0) super.create();
		for (int i = 0; i < childCount; i++) {
			animations[i].create();
		}
	}
		
	public Animation getAnimation(int i) {
		checkAnimation();
		if (0>i || i>=childCount) SWT.error(SWT.ERROR_INVALID_ARGUMENT);
		return animations[i];
	}
	
	public long getDuration() {
		long duration = 0;
		for (int i = 0; i < childCount; i++) {
			Animation a = animations[i];
			duration += a.getDuration();
		}
		return duration;
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
}
