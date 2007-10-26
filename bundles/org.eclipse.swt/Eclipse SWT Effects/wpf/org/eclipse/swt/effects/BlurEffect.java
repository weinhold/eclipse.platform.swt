package org.eclipse.swt.effects;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.wpf.*;

public class BlurEffect extends Effect {
	void createHandle() {
		handle = OS.gcnew_BlurBitmapEffect();
		if (handle == 0) SWT.error(SWT.ERROR_NO_HANDLES);
	}
	
	public void setRadius(double radius) {
		checkEffect();
		OS.BlurBitmapEffect_Radius(handle, radius);
	}
}
