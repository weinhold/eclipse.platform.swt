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


public class cef_popup_features_t {
	  public int x;
	  public int xSet;
	  public int y;
	  public int ySet;
	  public int width;
	  public int widthSet;
	  public int height;
	  public int heightSet;

	  public int menuBarVisible;
	  public int statusBarVisible;
	  public int toolBarVisible;
	  public int locationBarVisible;
	  public int scrollbarsVisible;
	  public int resizable;

	  public int fullscreen;
	  public int dialog;
	  /** @field cast=(cef_string_list_t) */
	  public int /*long*/ additionalFeatures;

	  public static final int sizeof = CEF3.cef_popup_features_t_sizeof();

}