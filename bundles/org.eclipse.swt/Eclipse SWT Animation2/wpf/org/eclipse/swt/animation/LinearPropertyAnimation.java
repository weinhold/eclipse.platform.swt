package org.eclipse.swt.animation;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.wpf.*;

public class LinearPropertyAnimation extends PropertyAnimation {
	int animation;
	int xAnim, yAnim, wAnim, hAnim;
	double accelerationRatio, decelerationRatio;
	
	void create() {
		super.create();
		updateAccelRatios();
	}
	void createDoubleAnimation() {
		animation = OS.gcnew_DoubleAnimation();
		int children = OS.TimelineGroup_Children(handle);
		OS.IList_Add(children, animation);
		OS.GCHandle_Free(children);
		setTargetProperty(animation, property);
	}

	void createIntegerAnimation() {
		animation = OS.gcnew_Int32Animation();
		int children = OS.TimelineGroup_Children(handle);
		OS.IList_Add(children, animation);
		OS.GCHandle_Free(children);
		setTargetProperty(animation, property);
	}
	
	void createPointAnimation() {
		int children = OS.TimelineGroup_Children(handle);
		if ("location".equals(property)) {
			xAnim = OS.gcnew_DoubleAnimation();
			OS.IList_Add(children, xAnim);
			setTargetProperty(xAnim, "x");
			yAnim = OS.gcnew_DoubleAnimation();
			OS.IList_Add(children, yAnim);
			setTargetProperty(yAnim, "y");
		}
		if ("size".equals(property)) {
			wAnim = OS.gcnew_DoubleAnimation();
			OS.IList_Add(children, wAnim);
			setTargetProperty(wAnim, "Width");
			hAnim = OS.gcnew_DoubleAnimation();
			OS.IList_Add(children, hAnim);
			setTargetProperty(hAnim, "Height");
		}
		OS.GCHandle_Free(children);
	}
	
	void createRectangleAnimation() {
		int children = OS.TimelineGroup_Children(handle);
		xAnim = OS.gcnew_DoubleAnimation();
		OS.IList_Add(children, xAnim);
		setTargetProperty(xAnim, "x");
		yAnim = OS.gcnew_DoubleAnimation();
		OS.IList_Add(children, yAnim);
		setTargetProperty(yAnim, "y");
		wAnim = OS.gcnew_DoubleAnimation();
		OS.IList_Add(children, wAnim);
		setTargetProperty(wAnim, "Width");
		hAnim = OS.gcnew_DoubleAnimation();
		OS.IList_Add(children, hAnim);
		setTargetProperty(hAnim, "Height");
		OS.GCHandle_Free(children);
	}
	
	public double getAccelerationRatio() {
		checkAnimation();
		return accelerationRatio;
	}
	
	public double getDecelerationRatio() {
		checkAnimation();
		return decelerationRatio;
	}
	
	void release() {
		super.release();
		if (animation != 0) OS.GCHandle_Free(animation);
		animation = 0;
		if (xAnim != 0) OS.GCHandle_Free(xAnim);
		xAnim = 0;
		if (yAnim != 0) OS.GCHandle_Free(yAnim);
		yAnim = 0;
		if (wAnim != 0) OS.GCHandle_Free(wAnim);
		wAnim = 0;
		if (hAnim != 0) OS.GCHandle_Free(hAnim);
		hAnim = 0;
	}

	public void setAccelerationRatio(double ratio) {
		checkAnimation();
		accelerationRatio = ratio;
	}

	public void setDecelerationRatio(double ratio) {
		checkAnimation();
		decelerationRatio = ratio;
	}
		
	void updateAccelRatios() {
		OS.Timeline_AccelerationRatio(handle, accelerationRatio);
		OS.Timeline_DecelerationRatio(handle, decelerationRatio);
	}
	
	long updateDuration(long delay) {
		//set duration
		int timeSpan = OS.TimeSpan_FromMilliseconds(duration);
		int dur = OS.gcnew_Duration(timeSpan);
		if (animation != 0) OS.Timeline_Duration(animation, dur);
		if (xAnim != 0) OS.Timeline_Duration(xAnim, dur);
		if (yAnim != 0) OS.Timeline_Duration(yAnim, dur);
		if (wAnim != 0) OS.Timeline_Duration(wAnim, dur);
		if (hAnim != 0) OS.Timeline_Duration(hAnim, dur);
		OS.GCHandle_Free(dur);
		OS.GCHandle_Free(timeSpan);
		//set begin time
		timeSpan = OS.TimeSpan_FromMilliseconds(delay + beginTime);
		OS.Timeline_BeginTime(handle, timeSpan);
		OS.GCHandle_Free(timeSpan);		
		return delay+beginTime+duration;
	}

	void updateFromToValues() {
		if (paramType == Double.TYPE) {
			double fromValue = 0, toValue;
			if ("alpha".equals(property)) {
				if (from != null) fromValue = ((Number)from).doubleValue() / 255;
				toValue = ((Number)to).doubleValue() / 255;
			} else {
				if (from != null) fromValue = ((Number)from).doubleValue();
				toValue = ((Number)to).doubleValue();
			}
			if (from != null) OS.DoubleAnimation_From(animation, fromValue);
			OS.DoubleAnimation_To(animation, toValue);
		}
		if (paramType == Color.class || paramType == Transform.class) {
			OS.DoubleAnimation_From(animation, 0);
			OS.DoubleAnimation_To(animation, 1);
		} 
		if (paramType == Integer.TYPE) {
			if (from != null) OS.Int32Animation_From(animation, ((Integer)from).intValue());
			OS.Int32Animation_To(animation, ((Integer)to).intValue());
		} 
		if (paramType == Rectangle.class) {
			if (from != null) {
				OS.DoubleAnimation_From(xAnim, ((Rectangle)from).x);
				OS.DoubleAnimation_From(yAnim, ((Rectangle)from).y);
				OS.DoubleAnimation_From(wAnim, ((Rectangle)from).width);
				OS.DoubleAnimation_From(hAnim, ((Rectangle)from).height);
			}
			OS.DoubleAnimation_To(xAnim, ((Rectangle)to).x);			
			OS.DoubleAnimation_To(yAnim, ((Rectangle)to).y);			
			OS.DoubleAnimation_To(wAnim, ((Rectangle)to).width);			
			OS.DoubleAnimation_To(hAnim, ((Rectangle)to).height);			
		}
		if (paramType == Point.class) {
			if ("location".equals(property)) {
				if (from != null) {
					OS.DoubleAnimation_From(xAnim, ((Point)from).x);
					OS.DoubleAnimation_From(yAnim, ((Point)from).y);
				}
				OS.DoubleAnimation_To(xAnim, ((Point)to).x);			
				OS.DoubleAnimation_To(yAnim, ((Point)to).y);
			}
			if ("size".equals(property)) {
				if (from != null) {
					OS.DoubleAnimation_From(wAnim, ((Point)from).x);
					OS.DoubleAnimation_From(hAnim, ((Point)from).y);
				}
				OS.DoubleAnimation_To(wAnim, ((Point)to).x);			
				OS.DoubleAnimation_To(hAnim, ((Point)to).y);
			}
		}
//		else {
//			throw new RuntimeException(paramType.getName() + " is not supported yet.");
//		}
	}
}

