package org.eclipse.swt.effects;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.wpf.*;


//WPF additionally has EdgeProfile and Relief
//Flex additionally has highlightColor, highlightAlpha, shadowColor, shadowAlpha, blurX, blurY, quality, type and knockout
public class BevelEffect extends Effect {
	void createHandle() {
		handle = OS.gcnew_BevelBitmapEffect();
		if (handle == 0) SWT.error(SWT.ERROR_NO_HANDLES);
	}
	
	//WPF: LightAngle
	//Flex: angle
	public void setAngle(double angle) {
		checkEffect();
		OS.BevelBitmapEffect_LightAngle(handle, angle);
	}
	
	//WPF: Smoothness
	//Flex: Strength (value 0-255)
	public void setSmoothness(double smoothness) {
		checkEffect();
		OS.BevelBitmapEffect_Smoothness(handle, smoothness);
	}
	
	//WPF: BevelWidth
	//Flex: distance
	public void setWidth(double width) {
		checkEffect();
		OS.BevelBitmapEffect_BevelWidth(handle, width);
	}
}
