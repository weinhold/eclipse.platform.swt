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


import org.eclipse.swt.internal.*;

public class OS {
	public static int Qt_AlignLeft;
	public static int Qt_AlignRight;
	public static int Qt_AlignHCenter;

	public static int Qt_AlignTop;
	public static int Qt_AlignBottom;
	public static int Qt_AlignVCenter;

	static {
		Library.loadLibrary("swt-pi");
		init();
	}

	static void loadLibrary() {
	}

	private static native void init();
}
