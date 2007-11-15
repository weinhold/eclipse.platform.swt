package org.eclipse.swt.animation;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.wpf.*;

public class LinearPropertyAnimation extends PropertyAnimation {
	int animation;
	
	void createDoubleAnimation() {
		animation = OS.gcnew_DoubleAnimation();
		int children = OS.TimelineGroup_Children(handle);
		OS.IList_Add(children, animation);
		OS.GCHandle_Free(children);
	}

	void createIntegerAnimation() {
		animation = OS.gcnew_Int32Animation();
		int children = OS.TimelineGroup_Children(handle);
		OS.IList_Add(children, animation);
		OS.GCHandle_Free(children);
	}
	
	void release() {
		super.release();
		if (animation != 0) OS.GCHandle_Free(animation);
		animation = 0;
	}
		
	long updateDuration(long delay) {
		//set duration
		int timeSpan = OS.TimeSpan_FromMilliseconds(duration);
		int dur = OS.gcnew_Duration(timeSpan);
		OS.Timeline_Duration(animation, dur);
		OS.GCHandle_Free(dur);
		OS.GCHandle_Free(timeSpan);
		//set begin time
		timeSpan = OS.TimeSpan_FromMilliseconds(delay + beginTime);
		OS.Timeline_BeginTime(animation, timeSpan);
		OS.GCHandle_Free(timeSpan);		
		return delay+beginTime+duration;
	}

	void updateFromToValues() {
		if (paramType == Double.TYPE
				|| paramType == Color.class
				|| paramType == Transform.class) {
			OS.DoubleAnimation_From(animation, from);
			OS.DoubleAnimation_To(animation, to);
		} else if (paramType == Integer.TYPE) {
			OS.Int32Animation_From(animation, (int)from);
			OS.Int32Animation_To(animation, (int)to);
		} else {
			throw new RuntimeException(paramType.getName() + " is not supported yet.");
		}
	}
}

