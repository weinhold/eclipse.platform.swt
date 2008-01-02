package org.eclipse.swt.animation;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.cocoa.*;
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
	
	
	void release() {
	}

	//FIXME: don't know how to do this
	// remove API.
//	public void resume() {
//		checkAnimation();
//	}
	
	//FIXME: Implementing this on Cocoa might
	// be difficult. 
	// remove API.
//	public void setAutoReverse(boolean autoReverse) {
//		checkAnimation();
//		this.autoReverse = autoReverse;
//	}
	
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
	}
	
	//FIXME:  
	//set NSAnimationContext.duration to 0.0001,
	//animate to current value.
	public void stop() {
		checkAnimation();
	}
	
	//FIXME: don't know how to do this
	//remove API.
//	public void pause() {
//		checkAnimation();
//	}
	
}
