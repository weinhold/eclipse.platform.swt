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


public class QtToolItem {
	public static native long /*int*/ createCheckItem(long /*int*/ displayHandle);

	public static native long /*int*/ createDropDownItem(long /*int*/ displayHandle);

	public static native long /*int*/ createPushItem(long /*int*/ displayHandle);

	public static native long /*int*/ createRadioItem(long /*int*/ displayHandle);

	public static native long /*int*/ createSeparatorItem(long /*int*/ displayHandle);

	public static native long /*int*/ getControl(long /*int*/ handle);

	public static native boolean isSelected(long /*int*/ handle);

	public static native void setControl(long /*int*/ handle, long /*int*/ controlHandle);

	public static native void setDisabledImage(long /*int*/ handle, long /*int*/ imageHandle);

	public static native void setHotImage(long /*int*/ handle, long /*int*/ imageHandle);

	public static native void setImage(long /*int*/ handle, long /*int*/ imageHandle);

	public static native void setSelected(long /*int*/ handle, boolean selected);

	public static native void setText(long /*int*/ handle, String text);

	public static native void setToolTipText(long /*int*/ handle, String text);
}
