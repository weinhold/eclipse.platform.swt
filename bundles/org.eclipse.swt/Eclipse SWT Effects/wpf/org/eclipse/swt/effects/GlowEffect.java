package org.eclipse.swt.effects;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.wpf.*;

public class GlowEffect extends Effect {

	void createHandle() {
		handle = OS.gcnew_OuterGlowBitmapEffect();
		if (handle == 0) SWT.error(SWT.ERROR_NO_HANDLES);
	}
	
	public void setColor(Color color) {
		checkEffect();
		OS.OuterGlowBitmapEffect_GlowColor(handle, color.handle);
	}

	public void setSize(double size) {
		checkEffect();
		OS.OuterGlowBitmapEffect_GlowSize(handle, size);
	}
	
	public void setAlpha(double alpha) {
		checkEffect();
		OS.OuterGlowBitmapEffect_Opacity(handle, alpha);
	}
}
