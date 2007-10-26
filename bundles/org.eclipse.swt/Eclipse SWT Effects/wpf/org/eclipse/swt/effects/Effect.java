package org.eclipse.swt.effects;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.wpf.*;

public class Effect extends Resource {
	public int handle;
	
	public Effect() {
		createHandle();
	}

	void checkEffect() {
	}
	
	void createHandle() {
	}

	public void dispose() {
		releaseHandle();
	}
	
	void releaseHandle() {
		if (handle != 0) OS.GCHandle_Free(handle);
		handle = 0;
	}

	public boolean isDisposed() {
		return handle == 0;
	}
}
