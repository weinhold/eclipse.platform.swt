package org.eclipse.swt.animation;

import org.eclipse.swt.graphics.*;

public class SplinePropertyAnimation extends PropertyAnimation {
	int beginFrame, endFrame; 
	double x1, y1, x2, y2;

	void createDoubleAnimation() {
//		int animation = OS.gcnew_DoubleAnimationUsingKeyFrames();
//		beginFrame = OS.gcnew_SplineDoubleKeyFrame();
//		endFrame = OS.gcnew_SplineDoubleKeyFrame();
//		int frames = OS.DoubleAnimationUsingKeyFrames_KeyFrames(animation);
//		OS.IList_Add(frames, beginFrame);
//		OS.IList_Add(frames, endFrame);
//		OS.GCHandle_Free(frames);
//		int children = OS.TimelineGroup_Children(handle);
//		OS.IList_Add(children, animation);
//		OS.GCHandle_Free(children);
//		OS.GCHandle_Free(animation);
	}

	void createIntegerAnimation() {
//		int animation = OS.gcnew_Int32AnimationUsingKeyFrames();
//		beginFrame = OS.gcnew_SplineInt32KeyFrame();
//		endFrame = OS.gcnew_SplineInt32KeyFrame();
//		int frames = OS.Int32AnimationUsingKeyFrames_KeyFrames(animation);
//		OS.IList_Add(frames, beginFrame);
//		OS.IList_Add(frames, endFrame);
//		OS.GCHandle_Free(frames);
//		int children = OS.TimelineGroup_Children(handle);
//		OS.IList_Add(children, animation);
//		OS.GCHandle_Free(children);
//		OS.GCHandle_Free(animation);
	}
	
	void release() {
		super.release();
//		if (beginFrame != 0) OS.GCHandle_Free(beginFrame);
//		beginFrame = 0;
//		if (endFrame != 0) OS.GCHandle_Free(endFrame);
//		endFrame = 0;
	}
	
	public void setSpline(double x1, double y1, double x2, double y2) {
		checkAnimation();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	long updateDuration(long delay) {
//		int timeSpan = OS.TimeSpan_FromMilliseconds(delay + beginTime);
//		int begin = OS.KeyTime_FromTimeSpan(timeSpan);
//		OS.GCHandle_Free(timeSpan);
//		timeSpan = OS.TimeSpan_FromMilliseconds(delay + beginTime + duration);
//		int end = OS.KeyTime_FromTimeSpan(timeSpan);
//		OS.GCHandle_Free(timeSpan);
//		if (paramType == Double.TYPE
//				|| paramType == Color.class
//				|| paramType == Transform.class) {
//			OS.DoubleKeyFrame_KeyTime(beginFrame, begin);
//			OS.DoubleKeyFrame_KeyTime(endFrame, end);
//		} else if (paramType == Integer.TYPE) {
//			OS.Int32KeyFrame_KeyTime(beginFrame, begin);
//			OS.Int32KeyFrame_KeyTime(endFrame, end);
//		} else {
//			throw new RuntimeException(paramType.getName() + " is not supported yet.");
//		}
//		OS.GCHandle_Free(begin);
//		OS.GCHandle_Free(end);
		return delay;
	}
	
	void updateFromToValues() {
//		int keySpline = OS.gcnew_KeySpline(x1, y1, x2, y2);
//		if (paramType == Double.TYPE
//				|| paramType == Color.class
//				|| paramType == Transform.class) {
//			OS.DoubleKeyFrame_Value(beginFrame, from);
//			OS.DoubleKeyFrame_Value(endFrame, to);
//			OS.SplineDoubleKeyFrame_KeySpline(endFrame, keySpline);
//		} else if (paramType == Integer.TYPE) {
//			OS.Int32KeyFrame_Value(beginFrame, (int)from);
//			OS.Int32KeyFrame_Value(endFrame, (int)to);
//			OS.SplineInt32KeyFrame_KeySpline(endFrame, keySpline);
//		} else {
//			throw new RuntimeException(paramType.getName() + " is not supported yet.");
//		}
//		OS.GCHandle_Free(keySpline);
	}
}
