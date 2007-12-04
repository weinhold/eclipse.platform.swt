package org.eclipse.swt.animation;

import org.eclipse.swt.internal.cocoa.*;


public class LinearPropertyAnimation extends PropertyAnimation {
	int animation;
	double accelerationRatio, decelerationRatio;

	public LinearPropertyAnimation() {
		accelerationRatio = 0;
		decelerationRatio = 1;
	}
	void create() {
		super.create();
	}
	
	public double getAccelerationRatio() {
		checkAnimation();
		return accelerationRatio;
	}
	
	public double getDecelerationRatio() {
		checkAnimation();
		return decelerationRatio;
	}

	//FIXME: 
	//acceleration,deceleration >=0 <=1
	//acceleration + deceleration <= 1
	public void setAccelerationRatio(double ratio) {
		checkAnimation();
		accelerationRatio = ratio;
	}

	public void setDecelerationRatio(double ratio) {
		checkAnimation();
		decelerationRatio = ratio;
	}
	
	void setTimingFunction() {
		int func = OS.objc_msgSend(OS.class_CAMediaTimingFunction, OS.sel_functionWithControlPoints_1_1_1_1, (float)accelerationRatio, 0f, (float)decelerationRatio, 1f);
		for (int i = 0; i < animations.length; i++) {
			OS.object_setInstanceVariable(animations[i].id, "timingFunction", func);
		}
	}
}

