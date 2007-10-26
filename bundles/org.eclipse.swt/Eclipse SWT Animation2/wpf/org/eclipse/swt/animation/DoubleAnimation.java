package org.eclipse.swt.animation;

import org.eclipse.swt.internal.wpf.*;

public class DoubleAnimation extends Animation {
	int beginFrame, endFrame;	
	
	public DoubleAnimation(int style) {
		super(checkStyle(style));
	}
	
	void createHandle() {
		int animation = OS.gcnew_DoubleAnimationUsingKeyFrames();
		beginFrame = OS.gcnew_DiscreteDoubleKeyFrame();
		if ((style & Animation.LINEAR) != 0) {
			endFrame = OS.gcnew_LinearDoubleKeyFrame();
		}
		if ((style & Animation.SPLINE) != 0) {
			endFrame = OS.gcnew_SplineDoubleKeyFrame();
		}
		if ((style & Animation.DISCRETE) != 0) {
			endFrame = OS.gcnew_DiscreteDoubleKeyFrame();
		}
		int frames = OS.DoubleAnimationUsingKeyFrames_KeyFrames(animation);
		OS.IList_Add(frames, beginFrame);
		OS.IList_Add(frames, endFrame);
		OS.GCHandle_Free(frames);
		addChild(animation);
		OS.GCHandle_Free(animation);
	}

	public double getCurrentValue() {
		int property = OS.SWTAnimator_DoubleValueProperty();
		double value = OS.DependencyObject_GetValueDouble(animatorHandle, property);
		OS.GCHandle_Free(property);
		return value;
	}
	
	void OnPropertyChanged(int object, int args) {
		double oldValue = OS.DependencyPropertyChangedEventArgs_OldValueDouble(args);
		double newValue = OS.DependencyPropertyChangedEventArgs_NewValueDouble(args);
		//TODO reflection
	}
	
	public void setFrom(double value) {
		checkAnimation();
		OS.DoubleKeyFrame_Value(beginFrame, value);
	}
	
	public void setSpline(double x1, double y1, double x2, double y2) {
		checkAnimation();
		if ((style & SPLINE) == 0) return;
		int keySpline = OS.gcnew_KeySpline(x1, y1, x2, y2);
		OS.SplineDoubleKeyFrame_KeySpline(endFrame, keySpline);
		OS.GCHandle_Free(keySpline);
	}
	
	void setDefaultProperty() {
		int property = OS.SWTAnimator_DoubleValueProperty();
		int propertyPath = OS.gcnew_PropertyPath(property);
		OS.Storyboard_SetTargetProperty(handle, propertyPath);
		OS.GCHandle_Free(property);
		OS.GCHandle_Free(propertyPath);
	}
	
	public void setTo (double value) {
		checkAnimation();
		OS.DoubleKeyFrame_Value(endFrame, value);
	}
	
	long updateDuration(long delay) {
		int timeSpan = OS.TimeSpan_FromMilliseconds(delay + beginTime);
		int keyTime = OS.KeyTime_FromTimeSpan(timeSpan);
		OS.GCHandle_Free(timeSpan);
		OS.DoubleKeyFrame_KeyTime(beginFrame, keyTime);
		OS.GCHandle_Free(keyTime);
		timeSpan = OS.TimeSpan_FromMilliseconds(delay + beginTime + duration);
		keyTime = OS.KeyTime_FromTimeSpan(timeSpan);
		OS.GCHandle_Free(timeSpan);
		OS.DoubleKeyFrame_KeyTime(endFrame, keyTime);
		OS.GCHandle_Free(keyTime);
		return delay+beginTime+duration;
	}
}
