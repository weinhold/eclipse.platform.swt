/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.internal.cef3;


public class cef_window_info_t {
	public int ex_style;
	public cef_string_t window_name;
	public int style;
	public int x;
	public int y;
	public int width;
	public int height;
	/** @field cast=(cef_window_handle_t) */
	public long /*int*/ parent_window;
	/** @field cast=(HMENU) */
	public long /*int*/ menu;
	public int transparent_painting;
	/** @field cast=(cef_window_handle_t) */
	public long /*int*/ window;		// HWND on win32
}
