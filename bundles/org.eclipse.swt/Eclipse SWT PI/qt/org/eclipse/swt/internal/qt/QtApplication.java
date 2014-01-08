/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others. All rights reserved.
 * The contents of this file are made available under the terms
 * of the GNU Lesser General Public License (LGPL) Version 2.1 that
 * accompanies this distribution (lgpl-v21.txt).  The LGPL is also
 * available at http://www.gnu.org/licenses/lgpl.html.  If the version
 * of the LGPL at http://www.gnu.org is different to the version of
 * the LGPL accompanying this distribution and there is any conflict
 * between the two license versions, the terms of the LGPL accompanying
 * this distribution shall govern.
 * 
 * Contributors:
 *     Ingo Weinhold
 *******************************************************************************/
package org.eclipse.swt.internal.qt;


import org.eclipse.swt.SWT;


public class QtApplication {

	private static QtApplication Application;

	private Thread thread;
	private long /*int*/ handle;
	private int referenceCount;

	static {
		// make sure the native library is loaded
		OS.loadLibrary();
	}

	private QtApplication() {
		referenceCount = 1;

		// create the application object
		handle = create();
		if (handle == 0) SWT.error(SWT.ERROR_NO_HANDLES);
	}

	public long /*int*/ getHandle() {
		return handle;
	}

	public static synchronized QtApplication getInstance() {
		if (Application == null) {
			Application = new QtApplication();
		}
		return Application;
	}

	public void releaseReference() {
		synchronized(QtApplication.class) {
			if (--referenceCount == 0) {
				delete();
				Application = null;
			}
		}
	}

	private native long /*int*/ create();

	private native void delete();
}
