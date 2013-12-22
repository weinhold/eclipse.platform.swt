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


public class HaikuButton {
	public static native long /*int*/ createArrowButton(long /*int*/ displayHandle);

	public static native long /*int*/ createCheckButton(long /*int*/ displayHandle);

	public static native long /*int*/ createPushButton(long /*int*/ displayHandle);

	public static native long /*int*/ createRadioButton(long /*int*/ displayHandle, boolean inRadioGroup);

	public static native long /*int*/ createToggleButton(long /*int*/ displayHandle);

	public static native boolean isSelected(long /*int*/ handle);

	public static native void setAlignmentStyle(long /*int*/ handle, int style);

	public static native void setGrayed(long /*int*/ handle, boolean grayed);

	public static native void setImage(long /*int*/ handle, long /*int*/ imageHandle);

	public static native void setSelected(long /*int*/ handle, boolean selected);

	public static native void setText(long /*int*/ handle, String text);
}
