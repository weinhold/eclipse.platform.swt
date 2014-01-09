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
package org.eclipse.swt.internal.qt;


public class QtToolBar {
	public static native void addItem(long /*int*/ handle, long /*int*/ itemHandle, int index);

	public static native long /*int*/ create(long /*int*/ displayHandle, boolean horizontal);

	public static native int getItemCount(long /*int*/ handle);

	public static native long[] /*int[]*/ getItems(long /*int*/ handle);

	public static native int removeItem(long /*int*/ handle, long /*int*/ itemHandle);
}
