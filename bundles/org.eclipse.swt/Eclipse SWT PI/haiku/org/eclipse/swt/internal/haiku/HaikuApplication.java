/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others. All rights reserved.
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
package org.eclipse.swt.internal.haiku;


import org.eclipse.swt.SWT;


public class HaikuApplication {

	private static HaikuApplication Application;

	private Thread thread;
	private long /*int*/ handle;
	private int referenceCount;

	static {
		// make sure the native library is loaded
		OS.loadLibrary();
	}

	private HaikuApplication() {
		referenceCount = 1;

		// create the application object
		handle = create();
		if (handle == 0) SWT.error(SWT.ERROR_NO_HANDLES);

		// create a new thread for the application message loop
		thread = new Thread() {
			public void run() {
				HaikuApplication.this.run();
			}
		};
		thread.start();
	}

	public long /*int*/ getHandle() {
		return handle;
	}

	public static synchronized HaikuApplication getInstance() {
		if (Application == null) {
			Application = new HaikuApplication();
		}
		return Application;
	}

	public void releaseReference() {
		synchronized(HaikuApplication.class) {
			if (--referenceCount == 0) {
				delete();
				Application = null;
			}
		}
	}

	private native long /*int*/ create();

	private native void delete();

	private native void run();
}
