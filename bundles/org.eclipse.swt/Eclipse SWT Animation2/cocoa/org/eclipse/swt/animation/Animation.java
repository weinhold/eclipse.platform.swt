package org.eclipse.swt.animation;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.cocoa.*;
import org.eclipse.swt.widgets.*;

public class Animation {
	public static final int FOREVER = -1;
	static Callback callback2, callback3, callback4;

	long beginTime, duration;
	boolean disposed;
	Widget widget;
//	boolean autoReverse;
	int repeatCount = 1;
	SWTCAAnimationDelegate delegate;
	int jniRef;
	CompositeAnimation parent;
	
	static {
		callback2 = new Callback(Animation.class, "animationDelegateProc", 2);
		int proc2 = callback2.getAddress();
		if (proc2 == 0) SWT.error (SWT.ERROR_NO_MORE_CALLBACKS);
		callback3 = new Callback(Animation.class, "animationDelegateProc", 3);
		int proc3 = callback3.getAddress();
		if (proc3 == 0) SWT.error (SWT.ERROR_NO_MORE_CALLBACKS);
		callback4 = new Callback(Animation.class, "animationDelegateProc", 4);
		int proc4 = callback4.getAddress();
		if (proc4 == 0) SWT.error (SWT.ERROR_NO_MORE_CALLBACKS);
		
		String className = "SWTCAAnimationDelegate";
		int cls = OS.objc_allocateClassPair(OS.class_NSObject, className, 0);
		OS.class_addIvar(cls, "tag", OS.PTR_SIZEOF, (byte)(Math.log(OS.PTR_SIZEOF) / Math.log(2)), "i");
		OS.class_addMethod(cls, OS.sel_animationDidStart_1, proc3, "@:@");
		OS.class_addMethod(cls, OS.sel_animationDidStop_1finished_1, proc4, "@:@i");
		OS.class_addMethod(cls, OS.sel_tag, proc2, "@:");
		OS.class_addMethod(cls, OS.sel_setTag_1, proc3, "@:i");
		OS.objc_registerClassPair(cls);
	}
	
	static int animationDelegateProc(int delegate, int sel) {
		if (sel == OS.sel_tag) {
			int[] tag = new int[1];
			OS.object_getInstanceVariable(delegate, "tag", tag);	
			return tag[0];
		}
		return 0;
	}
	
	static int animationDelegateProc(int delegate, int sel, int arg0) {
		if (sel == OS.sel_setTag_1) {
			OS.object_setInstanceVariable(delegate, "tag", arg0);
			return 0;
		}
		int jniRef = OS.objc_msgSend(delegate, OS.sel_tag);
		if (jniRef == 0 || jniRef == -1) return 0;
		Animation animation = (Animation)OS.JNIGetObject(jniRef);
		if (animation == null) return 0;
		if (sel == OS.sel_animationDidStart_1) animation.animationDidStart(arg0);
		return 0;
	}

	static int animationDelegateProc(int delegate, int sel, int arg0, int arg1) {
		int jniRef = OS.objc_msgSend(delegate, OS.sel_tag);
		if (jniRef == 0 || jniRef == -1) return 0;
		Animation animation = (Animation)OS.JNIGetObject(jniRef);
		if (animation == null) return 0;
		if (sel == OS.sel_animationDidStop_1finished_1) animation.animationDidStop(arg0, arg1);
		return 0;
	}

	void animationDidStart(int animation) {
	}
	
	void animationDidStop(int animation, int finished) {
	}
	
	void create () {
		if (delegate == null) {
			jniRef = OS.NewGlobalRef(this);
			if (jniRef == 0) SWT.error (SWT.ERROR_NO_HANDLES);
			delegate = (SWTCAAnimationDelegate) new SWTCAAnimationDelegate();
			delegate.alloc();
			delegate.init();
			delegate.setTag(jniRef);
		}
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
	
	boolean isFinished() {
		return false;
	}
	
	boolean isRunning() {
		return false;
	}
	
//	public boolean getAutoReverse() {
//		checkAnimation();
//		return autoReverse;
//	}
	
	
	void release() {
		parent = null;
		if (jniRef != 0) OS.DeleteGlobalRef(jniRef);
		jniRef = 0;
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

	void setParent(CompositeAnimation parent) {
		this.parent = parent;
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

	public void setDelegate(SWTCAAnimationDelegate delegate) {
	}
	
	//FIXME: don't know how to do this
	//remove API.
//	public void pause() {
//		checkAnimation();
//	}
	
}
