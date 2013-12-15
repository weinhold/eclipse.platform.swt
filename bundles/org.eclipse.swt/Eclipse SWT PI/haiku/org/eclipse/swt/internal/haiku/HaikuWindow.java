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
import org.eclipse.swt.graphics.Rectangle;


public class HaikuWindow {
	public static Rectangle getFrame(long handle) {
		int[] bounds = new int[4];
		HaikuView.setAndGetFrame(handle, bounds, new boolean[2]);
		return new Rectangle (bounds[0], bounds[1], bounds[2], bounds[3]);
	}

	public static native long create(long displayHandle);

	public static native long createRootView(long handle);

	public static native void delete(long handle);

	/*
	 * frame is an int[4] (x, y, width, height), specifying the new position
	 * and size. It is also an out parameter, returing the new position and
	 * size.
	 * moveResize is a boolean[2] (move, resize), specifying whether the window
	 * shall be moved and/or resized. It is also an out parameter, indicating
	 * whether the window has been moved and/or resized.
	 */
	public static native void setAndGetFrame(long handle, int[] frame, boolean moveResize[]);

	public static native void setVisible(long handle, boolean visible);
}
