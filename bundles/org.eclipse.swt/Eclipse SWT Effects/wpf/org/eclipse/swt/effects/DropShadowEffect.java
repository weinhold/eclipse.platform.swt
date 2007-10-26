package org.eclipse.swt.effects;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.wpf.*;

public class DropShadowEffect extends Effect {

	void createHandle() {
		handle = OS.gcnew_DropShadowBitmapEffect();
		if (handle == 0) SWT.error(SWT.ERROR_NO_HANDLES);
	}
	
	public void setAngle(double angle) {
		checkEffect();
		OS.DropShadowBitmapEffect_Direction(handle, angle);
	}

	public void setAlpha(double alpha) {
		checkEffect();
		OS.DropShadowBitmapEffect_Opacity(handle, alpha);
	}
	
	public void setColor(Color color) {
		checkEffect();
		OS.DropShadowBitmapEffect_Color(handle, color.handle);
	}
	
	public void setDistance(double distance) {
		checkEffect();
		OS.DropShadowBitmapEffect_ShadowDepth(handle, distance);
	}
	
	public void setSoftness(double softness) {
		checkEffect();
		OS.DropShadowBitmapEffect_Softness(handle, softness);
	}
}
