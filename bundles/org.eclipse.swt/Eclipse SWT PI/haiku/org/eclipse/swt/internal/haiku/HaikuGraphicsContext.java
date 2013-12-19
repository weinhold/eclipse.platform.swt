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


public class HaikuGraphicsContext {
	public static native void delete(long handle);

	public static native void drawRectangle(long handle, int x, int y, int width, int height);

	public static native void drawString(long handle, int x, int y, String string, boolean isTransparent);

	public static native HaikuColor getForegroundColor(long handle);

	public static native void setForegroundColor(long handle, byte red, byte green, byte blue);
}
