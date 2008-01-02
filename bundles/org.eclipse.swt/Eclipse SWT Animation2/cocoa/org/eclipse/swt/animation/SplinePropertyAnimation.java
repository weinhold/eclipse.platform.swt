package org.eclipse.swt.animation;

import org.eclipse.swt.internal.cocoa.*;

public class SplinePropertyAnimation extends PropertyAnimation {
	double x1, y1, x2, y2;
	
	public SplinePropertyAnimation() {
		x1 = y1 = 0;
		x2 = y2 = 1;
	}
	
	public void setSpline(double x1, double y1, double x2, double y2) {
		checkAnimation();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	void setTimingFunction() {
		int func = OS.objc_msgSend(OS.class_CAMediaTimingFunction, OS.sel_functionWithControlPoints_1_1_1_1, (float)x1, (float)y1, (float)x2, (float)y2);
		for (int i = 0; i < animations.length; i++) {
			OS.objc_msgSend(animations[i].id, OS.sel_setTimingFunction_1, func);
		}
	}
}
