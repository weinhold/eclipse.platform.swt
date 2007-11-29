package org.eclipse.swt.effects;

import org.eclipse.swt.graphics.*;

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
	}

	public boolean isDisposed() {
		return handle == 0;
	}
}
