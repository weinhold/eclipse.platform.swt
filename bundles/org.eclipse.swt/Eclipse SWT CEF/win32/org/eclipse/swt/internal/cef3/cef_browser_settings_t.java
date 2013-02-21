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

	public cef_string_t standard_font_family = CEF.STRING_EMPTY;
	public cef_string_t fixed_font_family = CEF.STRING_EMPTY;
	public cef_string_t serif_font_family = CEF.STRING_EMPTY;
	public cef_string_t sans_serif_font_family = CEF.STRING_EMPTY;
	public cef_string_t cursive_font_family = CEF.STRING_EMPTY;
	public cef_string_t fantasy_font_family = CEF.STRING_EMPTY;
	public int default_font_size;
	public int default_fixed_font_size;
	public int minimum_font_size;
	public int minimum_logical_font_size;
	public int remote_fonts_disabled;
	public cef_string_t default_encoding = CEF.STRING_EMPTY;
	public int encoding_detector_enabled;
	public int javascript_disabled;
	public int javascript_open_windows_disallowed;
	public int javascript_close_windows_disallowed;
	public int javascript_access_clipboard_disallowed;
	public int dom_paste_disabled;
	public int caret_browsing_enabled;
	public int java_disabled;
	public int plugins_disabled;
	public int universal_access_from_file_urls_allowed;
	public int file_access_from_file_urls_allowed;
	public int web_security_disabled;
	public int xss_auditor_enabled;
	public int image_load_disabled;
	public int shrink_standalone_images_to_fit;
	public int site_specific_quirks_disabled;
	public int text_area_resize_disabled;
	public int page_cache_disabled;
	public int tab_to_links_disabled;
	public int hyperlink_auditing_disabled;
	public int user_style_sheet_enabled;
	public cef_string_t user_style_sheet_location = CEF.STRING_EMPTY;
	public int author_and_user_styles_disabled;
	public int local_storage_disabled;
	public int databases_disabled;
	public int application_cache_disabled;
	public int webgl_disabled;
	public int accelerated_compositing_disabled;
	public int accelerated_layers_disabled;
	public int accelerated_video_disabled;
	public int accelerated_2d_canvas_disabled;
	public int accelerated_painting_enabled;
	public int accelerated_filters_enabled;
	public int accelerated_plugins_disabled;
	public int developer_tools_disabled;
	public int fullscreen_enabled;

	public static final int sizeof = CEF3.cef_browser_settings_t_sizeof ();
}
