package org.eclipse.swt.animation;

import org.eclipse.swt.internal.wpf.*;

public class IntegerAnimation extends Animation {
	int beginFrame, endFrame;	
	
	public IntegerAnimation(int style) {
		super(checkStyle(style));
	}
	
	void createHandle() {
		int animation = OS.gcnew_Int32AnimationUsingKeyFrames();
		beginFrame = OS.gcnew_DiscreteInt32KeyFrame();
		if ((style & Animation.LINEAR) != 0) {
			endFrame = OS.gcnew_LinearInt32KeyFrame();
		}
		if ((style & Animation.SPLINE) != 0) {
			endFrame = OS.gcnew_SplineInt32KeyFrame();
		}
		if ((style & Animation.DISCRETE) != 0) {
			endFrame = OS.gcnew_DiscreteInt32KeyFrame();
		}
		int frames = OS.Int32AnimationUsingKeyFrames_KeyFrames(animation);
		OS.IList_Add(frames, beginFrame);
		OS.IList_Add(frames, endFrame);
		OS.GCHandle_Free(frames);
		addChild(animation);
		OS.GCHandle_Free(animation);
	}
	
	public int getCurrentValue() {
		int property = OS.SWTAnimator_IntValueProperty();
		int value = OS.DependencyObject_GetValueInt(animatorHandle, property);
		OS.GCHandle_Free(property);
		return value;
	}
	
	void OnPropertyChanged(int object, int args) {
		int oldValue = OS.DependencyPropertyChangedEventArgs_OldValueInt(args);
		int newValue = OS.DependencyPropertyChangedEventArgs_NewValueInt(args);
		//TODO reflection
	}
	
	public void setFrom(int value) {
		checkAnimation();
		OS.Int32KeyFrame_Value(beginFrame, value);
	}
	
	public void setSpline(double x1, double y1, double x2, double y2) {
		checkAnimation();
		if ((style & SPLINE) == 0) return;
		int keySpline = OS.gcnew_KeySpline(x1, y1, x2, y2);
		OS.SplineInt32KeyFrame_KeySpline(endFrame, keySpline);
		OS.GCHandle_Free(keySpline);
	}
	
	void setDefaultProperty() {
		int property = OS.SWTAnimator_IntValueProperty();
		int propertyPath = OS.gcnew_PropertyPath(property);
		OS.Storyboard_SetTargetProperty(handle, propertyPath);
		OS.GCHandle_Free(property);
		OS.GCHandle_Free(propertyPath);
	}
	
	public void setTo (int value) {
		checkAnimation();
		OS.Int32KeyFrame_Value(endFrame, value);
	}
	
	long updateDuration(long delay) {
		int timeSpan = OS.TimeSpan_FromMilliseconds(delay + beginTime);
		int keyTime = OS.KeyTime_FromTimeSpan(timeSpan);
		OS.GCHandle_Free(timeSpan);
		OS.Int32KeyFrame_KeyTime(beginFrame, keyTime);
		OS.GCHandle_Free(keyTime);
		timeSpan = OS.TimeSpan_FromMilliseconds(delay + beginTime + duration);
		keyTime = OS.KeyTime_FromTimeSpan(timeSpan);
		OS.GCHandle_Free(timeSpan);
		OS.Int32KeyFrame_KeyTime(endFrame, keyTime);
		OS.GCHandle_Free(keyTime);
		return delay+beginTime+duration;
	}
}
