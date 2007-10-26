package org.eclipse.swt.animation;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.wpf.*;

public class DiscretePropertyAnimation extends PropertyAnimation {
	int beginFrame, endFrame; 

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
	}
	
	void release() {
		super.release();
		if (beginFrame != 0) OS.GCHandle_Free(beginFrame);
		beginFrame = 0;
		if (endFrame != 0) OS.GCHandle_Free(endFrame);
		endFrame = 0;
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
		} else if (paramType == Integer.TYPE) {
			OS.Int32KeyFrame_KeyTime(beginFrame, begin);
			OS.Int32KeyFrame_KeyTime(endFrame, end);
		} else {
			throw new RuntimeException(paramType.getName() + " is not supported yet.");
		}
		OS.GCHandle_Free(begin);
		OS.GCHandle_Free(end);
		return delay;
	}
	
	void updateFromToValues() {
		if (paramType == Double.TYPE
				|| paramType == Color.class
				|| paramType == Transform.class) {
			OS.DoubleKeyFrame_Value(beginFrame, from);
			OS.DoubleKeyFrame_Value(endFrame, to);
		} else if (paramType == Integer.TYPE) {
			OS.Int32KeyFrame_Value(beginFrame, (int)from);
			OS.Int32KeyFrame_Value(endFrame, (int)to);
		} else {
			throw new RuntimeException(paramType.getName() + " is not supported yet.");
		}
	}
}
