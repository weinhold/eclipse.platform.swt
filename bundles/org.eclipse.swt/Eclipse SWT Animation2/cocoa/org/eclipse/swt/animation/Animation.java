package org.eclipse.swt.animation;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;

public class Animation {
	public static final int FOREVER = -1;
	
	long beginTime, duration;
	boolean disposed;
	Widget widget;
	boolean autoReverse;
	int repeatCount = 1;
	
	void addChildren() {
	}
	
	void create () {
	}
	
	void checkAnimation() {
		if (disposed) SWT.error(SWT.ERROR_WIDGET_DISPOSED);
		if (widget != null && widget.isDisposed()) SWT.error(SWT.ERROR_WIDGET_DISPOSED);
	}
	
	public void dispose() {
		if (disposed) return;
		disposed = true;
		release();
	}
	
	public boolean getAutoReverse() {
		checkAnimation();
		return autoReverse;
	}
	
	public long getDuration() {
		return duration;
	}
	
	void release() {
	}

	//FIXME: don't know how to do this
	public void resume() {
		checkAnimation();
	}
	
	public void setDuration(long duration) {
		checkAnimation();
		this.duration = duration;
	}
	
	//FIXME: Implementing this on Cocoa might
	// be difficult. Might be better to remove.
	public void setAutoReverse(boolean autoReverse) {
		checkAnimation();
		this.autoReverse = autoReverse;
	}
	
	public void setBeginTime(long beginTime) {
		checkAnimation();
		this.beginTime = beginTime;
	}

	public void setRepeatCount(int repeatCount) {
		checkAnimation();
		if (repeatCount == 0) SWT.error(SWT.ERROR_INVALID_ARGUMENT);
		this.repeatCount = repeatCount;
	}
	
	void setWidget(Widget widget) {
		this.widget = widget;
	}
	
	public void start(Widget widget) {
		checkAnimation();
		setWidget(widget);
		create();
//		addChildren();
		updateDuration(0);
	}
	
	//FIXME: don't know how to do this
	public void stop() {
		checkAnimation();
	}
	//FIXME: don't know how to do this	
	public void pause() {
		checkAnimation();
	}
	
	long updateDuration(long delay) {
		return delay+beginTime+duration;
	}
}
