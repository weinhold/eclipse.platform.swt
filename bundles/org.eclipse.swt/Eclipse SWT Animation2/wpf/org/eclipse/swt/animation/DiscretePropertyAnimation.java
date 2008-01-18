package org.eclipse.swt.animation;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.wpf.*;

public class DiscretePropertyAnimation extends PropertyAnimation {
	int beginFrame, endFrame; 
	int bX, bY, bW, bH, eX, eY, eW, eH;

	void createDoubleAnimation() {
		int animation = OS.gcnew_DoubleAnimationUsingKeyFrames();
		beginFrame = OS.gcnew_DiscreteDoubleKeyFrame();
		endFrame = OS.gcnew_DiscreteDoubleKeyFrame();
		int frames = OS.DoubleAnimationUsingKeyFrames_KeyFrames(animation);
		OS.IList_Add(frames, beginFrame);
		OS.IList_Add(frames, endFrame);
		OS.GCHandle_Free(frames);
		int children = OS.TimelineGroup_Children(handle);
		OS.IList_Add(children, animation);
		OS.GCHandle_Free(children);
		OS.GCHandle_Free(animation);
		setTargetProperty(animation, property);
	}

	void createIntegerAnimation() {
		int animation = OS.gcnew_Int32AnimationUsingKeyFrames();
		beginFrame = OS.gcnew_DiscreteInt32KeyFrame();
		endFrame = OS.gcnew_DiscreteInt32KeyFrame();
		int frames = OS.Int32AnimationUsingKeyFrames_KeyFrames(animation);
		OS.IList_Add(frames, beginFrame);
		OS.IList_Add(frames, endFrame);
		OS.GCHandle_Free(frames);
		int children = OS.TimelineGroup_Children(handle);
		OS.IList_Add(children, animation);
		OS.GCHandle_Free(children);
		OS.GCHandle_Free(animation);
		setTargetProperty(animation, property);
	}
	
	void createRectangleAnimation() {
		int children = OS.TimelineGroup_Children(handle);
		int animation = OS.gcnew_DoubleAnimationUsingKeyFrames();
		bX = OS.gcnew_DiscreteDoubleKeyFrame();
		eX = OS.gcnew_DiscreteDoubleKeyFrame();
		int frames = OS.DoubleAnimationUsingKeyFrames_KeyFrames(animation);
		OS.IList_Add(frames, bX);
		OS.IList_Add(frames, eX);
		OS.GCHandle_Free(frames);
		OS.IList_Add(children, animation);
		setTargetProperty(animation, "x");
		OS.GCHandle_Free(animation);
		
		animation = OS.gcnew_DoubleAnimationUsingKeyFrames();
		bY = OS.gcnew_DiscreteDoubleKeyFrame();
		eY = OS.gcnew_DiscreteDoubleKeyFrame();
		frames = OS.DoubleAnimationUsingKeyFrames_KeyFrames(animation);
		OS.IList_Add(frames, bY);
		OS.IList_Add(frames, eY);
		OS.GCHandle_Free(frames);
		OS.IList_Add(children, animation);
		setTargetProperty(animation, "y");
		OS.GCHandle_Free(animation);
		
		animation = OS.gcnew_DoubleAnimationUsingKeyFrames();
		bW = OS.gcnew_DiscreteDoubleKeyFrame();
		eW = OS.gcnew_DiscreteDoubleKeyFrame();
		frames = OS.DoubleAnimationUsingKeyFrames_KeyFrames(animation);
		OS.IList_Add(frames, bW);
		OS.IList_Add(frames, eW);
		OS.GCHandle_Free(frames);
		OS.IList_Add(children, animation);
		setTargetProperty(animation, "Width");
		OS.GCHandle_Free(animation);
		
		animation = OS.gcnew_DoubleAnimationUsingKeyFrames();
		bH = OS.gcnew_DiscreteDoubleKeyFrame();
		eH = OS.gcnew_DiscreteDoubleKeyFrame();
		frames = OS.DoubleAnimationUsingKeyFrames_KeyFrames(animation);
		OS.IList_Add(frames, bH);
		OS.IList_Add(frames, eH);
		OS.GCHandle_Free(frames);
		OS.IList_Add(children, animation);
		setTargetProperty(animation, "Height");
		OS.GCHandle_Free(animation);
		
		OS.GCHandle_Free(children);
	}
	
	void release() {
		super.release();
		if (beginFrame != 0) OS.GCHandle_Free(beginFrame);
		beginFrame = 0;
		if (endFrame != 0) OS.GCHandle_Free(endFrame);
		endFrame = 0;
		if (bX != 0) OS.GCHandle_Free(bX);
		bX = 0;
		if (bY != 0) OS.GCHandle_Free(bY);
		bY = 0;
		if (bW != 0) OS.GCHandle_Free(bW);
		bW = 0;
		if (bH != 0) OS.GCHandle_Free(bH);
		bH = 0;
		if (eX != 0) OS.GCHandle_Free(eX);
		eX = 0;
		if (eY != 0) OS.GCHandle_Free(eY);
		eY = 0;
		if (eW != 0) OS.GCHandle_Free(eW);
		eW = 0;
		if (eH != 0) OS.GCHandle_Free(eH);
		eH = 0;
	}
	
	long updateDuration(long delay) {
		int timeSpan = OS.TimeSpan_FromMilliseconds(delay + beginTime);
		int begin = OS.KeyTime_FromTimeSpan(timeSpan);
		OS.GCHandle_Free(timeSpan);
		timeSpan = OS.TimeSpan_FromMilliseconds(delay + beginTime + duration);
		int end = OS.KeyTime_FromTimeSpan(timeSpan);
		OS.GCHandle_Free(timeSpan);
		if (paramType == Double.TYPE
				|| paramType == Color.class
				|| paramType == Transform.class) {
			OS.DoubleKeyFrame_KeyTime(beginFrame, begin);
			OS.DoubleKeyFrame_KeyTime(endFrame, end);
		} 
		if (paramType == Integer.TYPE) {
			OS.Int32KeyFrame_KeyTime(beginFrame, begin);
			OS.Int32KeyFrame_KeyTime(endFrame, end);
		} 
		if (paramType == Rectangle.class) {
			OS.DoubleKeyFrame_KeyTime(bX, begin);
			OS.DoubleKeyFrame_KeyTime(bY, begin);
			OS.DoubleKeyFrame_KeyTime(bW, begin);
			OS.DoubleKeyFrame_KeyTime(bH, begin);
			OS.DoubleKeyFrame_KeyTime(eX, end);
			OS.DoubleKeyFrame_KeyTime(eY, end);			
			OS.DoubleKeyFrame_KeyTime(eW, end);			
			OS.DoubleKeyFrame_KeyTime(eH, end);
		}
		OS.GCHandle_Free(begin);
		OS.GCHandle_Free(end);
		return delay;
	}
	
	void updateFromToValues() {
		if (paramType == Double.TYPE
				|| paramType == Color.class
				|| paramType == Transform.class) {
			OS.DoubleKeyFrame_Value(beginFrame, ((Double)from).doubleValue());
			OS.DoubleKeyFrame_Value(endFrame, ((Double)to).doubleValue());
		} 
		if (paramType == Integer.TYPE) {
			OS.Int32KeyFrame_Value(beginFrame, ((Integer)from).intValue());
			OS.Int32KeyFrame_Value(endFrame, ((Integer)to).intValue());
		} 
		if (paramType == Rectangle.class) {
			Rectangle fromRect = (Rectangle) from;
			Rectangle toRect = (Rectangle) to;
			OS.DoubleKeyFrame_Value(bX, fromRect.x);
			OS.DoubleKeyFrame_Value(bY, fromRect.y);
			OS.DoubleKeyFrame_Value(bW, fromRect.width);
			OS.DoubleKeyFrame_Value(bH, fromRect.height);
			OS.DoubleKeyFrame_Value(eX, toRect.x);
			OS.DoubleKeyFrame_Value(eY, toRect.y);
			OS.DoubleKeyFrame_Value(eW, toRect.width);
			OS.DoubleKeyFrame_Value(eH, toRect.height);
		} 
	}
}
