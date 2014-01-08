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

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class QtControl {
	public static Rectangle getBounds(long /*int*/ handle) {
		int[] bounds = new int[4];
		setAndGetBounds(handle, bounds, new boolean[2]);
		return new Rectangle(bounds[0], bounds[1], bounds[2], bounds[3]);
	}

	public static void setBounds(long /*int*/ handle, int x, int y, int width, int height, boolean move, boolean resize) {
		setAndGetBounds(handle, new int[]{x, y, width, height}, new boolean[]{move, resize}); 
	}

	public static native Point getPreferredSize(long /*int*/ handle, int wHint, int hHint);

	public static native void relayout(long /*int*/ handle);

	/*
	 * frame is an int[4] (x, y, width, height), specifying the new position
	 * and size. It is also an out parameter, returing the new position and
	 * size.
	 * moveResize is a boolean[2] (move, resize), specifying whether the view
	 * shall be moved and/or resized. It is also an out parameter, indicating
	 * whether the view has been moved and/or resized.
	 */
	public static native void setAndGetBounds(long /*int*/ handle, int[] frame, boolean moveResize[]);

	public static native void setEnabled(long /*int*/ handle, boolean enabled);

	public static native void setStyle(long /*int*/ handle, int style);

	public static native void setVisible(long /*int*/ handle, boolean visible);
}
