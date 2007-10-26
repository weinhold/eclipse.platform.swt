package org.eclipse.swt.effects;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.wpf.*;

public class CompositeEffect extends Effect {
	void createHandle() {
		handle = OS.gcnew_BitmapEffectGroup();
		if (handle == 0) SWT.error(SWT.ERROR_NO_HANDLES);
	}
	
	public void addEffect(Effect effect) {
		checkEffect();
		int children = OS.BitmapEffectGroup_Children(handle);
		OS.IList_Add(children, effect.handle);
		OS.GCHandle_Free(children);
	}
	
	public void removeEffect(Effect effect) {
		checkEffect();
		int children = OS.BitmapEffectGroup_Children(handle);
		OS.IList_Remove(children, effect.handle);
		OS.GCHandle_Free(children);
	}
}
