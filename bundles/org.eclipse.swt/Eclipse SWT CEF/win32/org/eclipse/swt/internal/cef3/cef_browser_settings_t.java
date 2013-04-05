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

import org.eclipse.swt.browser.*;


public class cef_browser_settings_t {
	public long /*int*/ size;

	public cef_string_t standard_font_family = CEF.CEFSTRING_EMPTY;
	public cef_string_t fixed_font_family = CEF.CEFSTRING_EMPTY;
	public cef_string_t serif_font_family = CEF.CEFSTRING_EMPTY;
	public cef_string_t sans_serif_font_family = CEF.CEFSTRING_EMPTY;
	public cef_string_t cursive_font_family = CEF.CEFSTRING_EMPTY;
	public cef_string_t fantasy_font_family = CEF.CEFSTRING_EMPTY;
	public int default_font_size;
	public int default_fixed_font_size;
	public int minimum_font_size;
	public int minimum_logical_font_size;
	public cef_string_t default_encoding = CEF.CEFSTRING_EMPTY;
	public cef_string_t user_style_sheet_location = CEF.CEFSTRING_EMPTY;
	public int remote_fonts;
	public int javascript;
	public int javascript_open_windows;
	public int javascript_close_windows;
	public int javascript_access_clipboard;
	public int javascript_dom_paste;
	public int caret_browsing;
	public int java;
	public int plugins;
	public int universal_access_from_file_urls;
	public int file_access_from_file_urls;
	public int web_security;
	public int image_loading;
	public int image_shrink_standalone_to_fit;
	public int text_area_resize;
	public int page_cache;
	public int tab_to_links;
	public int author_and_user_styles;
	public int local_storage;
	public int databases;
	public int application_cache;
	public int webgl;
	public int accelerated_compositing;
	public int developer_tools;

	public static final int sizeof = CEF3.cef_browser_settings_t_sizeof ();
}
