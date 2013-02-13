/*******************************************************************************
 * Copyright (c) 2003, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.internal.mozilla;

import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.*;

public class XPCOMObject extends OSObject {
	
public XPCOMObject (int[] argCounts) {
	super (argCounts);
}
	
protected long /*int*/ create (int[] argCounts, int os_offset) {
	long /*int*/[] callbackAddresses = new long /*int*/[argCounts.length + os_offset];
	synchronized (Callbacks) {
		for (int i = 0, length = argCounts.length; i < length; i++) {
			if ((Callbacks[i + os_offset][argCounts[i]]) == null) {
				Callbacks[i + os_offset][argCounts[i]] = new Callback (getClass (), "callback"+i, argCounts[i] + 1, true, XPCOM.NS_ERROR_FAILURE); //$NON-NLS-1$
			}
			callbackAddresses[i + os_offset] = Callbacks[i + os_offset][argCounts[i]].getAddress ();
			if (callbackAddresses[i + os_offset] == 0) SWT.error (SWT.ERROR_NO_MORE_CALLBACKS);
		}
	}

	long /*int*/ pVtable = C.malloc (C.PTR_SIZEOF * (argCounts.length + os_offset));
	XPCOM.memmove (pVtable, callbackAddresses, C.PTR_SIZEOF * (argCounts.length + os_offset));
	long /*int*/ result = C.malloc (C.PTR_SIZEOF);
	XPCOM.memmove (result, new long /*int*/[] {pVtable}, C.PTR_SIZEOF);
	return result;
}

protected void release (long /*int*/ handle) {
	long /*int*/[] pVtable = new long /*int*/[1];
	XPCOM.memmove (pVtable, handle, C.PTR_SIZEOF);
	C.free (pVtable[0]);
	C.free (handle);	
}

}
