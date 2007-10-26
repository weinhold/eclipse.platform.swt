package org.eclipse.swt.animation;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.wpf.*;
import org.eclipse.swt.widgets.*;

public class Animation {
	public static final int FOREVER = -1;
	
	static int count = 0;
	
	int handle, jniRef;
	long beginTime, duration;
	boolean disposed;
	Widget widget;
	boolean autoReverse;
	int repeatCount = 1;
	
	void addChildren() {
	}
	
	void create () {
		handle = OS.gcnew_Storyboard();
		if (handle == 0) SWT.error(SWT.ERROR_NO_HANDLES);
		jniRef = OS.NewGlobalRef(this);
		if (jniRef == 0) SWT.error(SWT.ERROR_NO_HANDLES);
		OS.Timeline_AutoReverse(handle, autoReverse);
		int repeatBehavior;
		if (repeatCount == Animation.FOREVER) {
			repeatBehavior = OS.RepeatBehavior_Forever();
		} else {
			repeatBehavior = OS.gcnew_RepeatBehavior(repeatCount);
		}
		if (repeatBehavior == 0) SWT.error(SWT.ERROR_NO_HANDLES);
		OS.Timeline_RepeatBehavior(handle, repeatBehavior);
		OS.GCHandle_Free(repeatBehavior);
	}
	
	int createDotNetString (String string) {
		if (string == null) return 0;
		int length = string.length();
		char[] buffer = new char[length + 1];
		string.getChars(0, length, buffer, 0);
		int ptr = OS.gcnew_String(buffer);
		if (ptr == 0) SWT.error(SWT.ERROR_NO_HANDLES);
		return ptr;
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
	
	public void setDuration(long duration) {
		checkAnimation();
		this.duration = duration;
	}
	
	public void start(Widget widget) {
		checkAnimation();
		setWidget(widget);
		create();
		addChildren();
		updateDuration(0);
		OS.Storyboard_Begin(handle, widget.handle, true);
	}
	
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
	
	long updateDuration(long delay) {
		return delay+beginTime+duration;
	}
	
	void setWidget(Widget widget) {
		this.widget = widget;
	}
	
	public void stop() {
		checkAnimation();
		if (widget != null) OS.Storyboard_Stop(handle, widget.handle);
	}
	
	public void pause() {
		checkAnimation();
		if (widget != null) OS.Storyboard_Pause(handle, widget.handle);
	}
	
	void release() {
		if (handle != 0) OS.GCHandle_Free(handle);
		handle = 0;
		if (jniRef != 0) OS.DeleteGlobalRef(jniRef);
		jniRef = 0;
	}

	public void resume() {
		checkAnimation();
		if (widget != null) OS.Storyboard_Resume(handle, widget.handle);
	}
}
