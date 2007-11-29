package org.eclipse.swt.animation;


public class LinearPropertyAnimation extends PropertyAnimation {
	int animation;
	double accelerationRatio, decelerationRatio;

	void create() {
		super.create();
//		updateAccelRatios();
	}
	
	public double getAccelerationRatio() {
		checkAnimation();
		return accelerationRatio;
	}
	
	public double getDecelerationRatio() {
		checkAnimation();
		return decelerationRatio;
	}
	
	public void setAccelerationRatio(double ratio) {
		checkAnimation();
		accelerationRatio = ratio;
	}

	public void setDecelerationRatio(double ratio) {
		checkAnimation();
		decelerationRatio = ratio;
	}
}

