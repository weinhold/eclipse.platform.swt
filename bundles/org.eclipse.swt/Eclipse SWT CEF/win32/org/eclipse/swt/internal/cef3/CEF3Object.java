/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.internal.cef3;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;

public class CEF3Object extends OSObject {
	static {
		FailCode = 0;
		NotImplementedCode = 0;
	}

public CEF3Object (int[] argCounts) {
	super (argCounts);
}

protected long /*int*/ create (int[] argCounts, int os_offset) {
	/* a +1 offset is added to callbackAddresses references to account for the "size" field that must be at position 0 */
	long /*int*/[] callbackAddresses = new long /*int*/[argCounts.length + os_offset + 1];
	synchronized (Callbacks) {
		for (int i = 0, length = argCounts.length; i < length; i++) {
			if ((Callbacks[i + os_offset][argCounts[i]]) == null) {
				Callbacks[i + os_offset][argCounts[i]] = new Callback(getClass(), "callback"+i, argCounts[i] + 1, true, FailCode); //$NON-NLS-1$
			}
			callbackAddresses[i + os_offset + 1] = Callbacks[i + os_offset][argCounts[i]].getAddress();
			if (callbackAddresses[i + os_offset + 1] == 0) SWT.error(SWT.ERROR_NO_MORE_CALLBACKS);
		}
	}

	int size = C.PTR_SIZEOF * (argCounts.length + os_offset + 1);
	callbackAddresses[0] = size;	/* set the "size" field value */
	long /*int*/ result = C.malloc (size);
	C.memmove (result, callbackAddresses, size);
	return result;
}

protected void release (long /*int*/ handle) {
	C.free (handle);
}

}
