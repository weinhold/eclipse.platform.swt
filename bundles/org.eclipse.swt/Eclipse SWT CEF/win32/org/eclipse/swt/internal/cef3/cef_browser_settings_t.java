package org.eclipse.swt.internal.cef3;

import org.eclipse.swt.browser.CEF;

public class cef_browser_settings_t {
	public long /*int*/ size;

	/** @field cast=(cef_string_t) */
	public cef_string_t standard_font_family = CEF.STRING_EMPTY;

	/** @field cast=(cef_string_t) */
	public cef_string_t fixed_font_family = CEF.STRING_EMPTY;

	/** @field cast=(cef_string_t) */
	public cef_string_t serif_font_family = CEF.STRING_EMPTY;
	
	/** @field cast=(cef_string_t) */
	public cef_string_t sans_serif_font_family = CEF.STRING_EMPTY;
	
	/** @field cast=(cef_string_t) */
	public cef_string_t cursive_font_family = CEF.STRING_EMPTY;
	
	/** @field cast=(cef_string_t) */
	public cef_string_t fantasy_font_family = CEF.STRING_EMPTY;
	
	public int default_font_size;
	public int default_fixed_font_size;
	public int minimum_font_size;
	public int minimum_logical_font_size;

	  ///
	  // Set to true (1) to disable loading of fonts from remote sources.
	  ///
	public int remote_fonts_disabled;

	  ///
	  // Default encoding for Web content. If empty "ISO-8859-1" will be used.
	  ///
	/** @field cast=(cef_string_t) */
	public cef_string_t default_encoding = CEF.STRING_EMPTY;

	  ///
	  // Set to true (1) to attempt automatic detection of content encoding.
	  ///
	public int encoding_detector_enabled;

	  ///
	  // Set to true (1) to disable JavaScript.
	  ///
	public int javascript_disabled;

	  ///
	  // Set to true (1) to disallow JavaScript from opening windows.
	  ///
	public int javascript_open_windows_disallowed;

	  ///
	  // Set to true (1) to disallow JavaScript from closing windows.
	  ///
	public int javascript_close_windows_disallowed;

	  ///
	  // Set to true (1) to disallow JavaScript from accessing the clipboard.
	  ///
	public int javascript_access_clipboard_disallowed;

	  ///
	  // Set to true (1) to disable DOM pasting in the editor. DOM pasting also
	  // depends on |javascript_cannot_access_clipboard| being false (0).
	  ///
	public int dom_paste_disabled;

	  ///
	  // Set to true (1) to enable drawing of the caret position.
	  ///
	public int caret_browsing_enabled;

	  ///
	  // Set to true (1) to disable Java.
	  ///
	public int java_disabled;

	  ///
	  // Set to true (1) to disable plugins.
	  ///
	public int plugins_disabled;

	  ///
	  // Set to true (1) to allow access to all URLs from file URLs.
	  ///
	public int universal_access_from_file_urls_allowed;

	  ///
	  // Set to true (1) to allow access to file URLs from other file URLs.
	  ///
	public int file_access_from_file_urls_allowed;

	  ///
	  // Set to true (1) to allow risky security behavior such as cross-site
	  // scripting (XSS). Use with extreme care.
	  ///
	public int web_security_disabled;

	  ///
	  // Set to true (1) to enable console warnings about XSS attempts.
	  ///
	public int xss_auditor_enabled;

	  ///
	  // Set to true (1) to suppress the network load of image URLs.  A cached
	  // image will still be rendered if requested.
	  ///
	public int image_load_disabled;

	  ///
	  // Set to true (1) to shrink standalone images to fit the page.
	  ///
	public int shrink_standalone_images_to_fit;

	  ///
	  // Set to true (1) to disable browser backwards compatibility features.
	  ///
	public int site_specific_quirks_disabled;

	  ///
	  // Set to true (1) to disable resize of text areas.
	  ///
	public int text_area_resize_disabled;

	  ///
	  // Set to true (1) to disable use of the page cache.
	  ///
	public int page_cache_disabled;

	  ///
	  // Set to true (1) to not have the tab key advance focus to links.
	  ///
	public int tab_to_links_disabled;

	  ///
	  // Set to true (1) to disable hyperlink pings (<a ping> and window.sendPing).
	  ///
	public int hyperlink_auditing_disabled;

	  ///
	  // Set to true (1) to enable the user style sheet for all pages.
	  ///
	public int user_style_sheet_enabled;

	  ///
	  // Location of the user style sheet. This must be a data URL of the form
	  // "data:text/css;charset=utf-8;base64,csscontent" where "csscontent" is the
	  // base64 encoded contents of the CSS file.
	  ///
		/** @field cast=(cef_string_t) */
		public cef_string_t user_style_sheet_location = CEF.STRING_EMPTY;

	  ///
	  // Set to true (1) to disable style sheets.
	  ///
		public int author_and_user_styles_disabled;

	  ///
	  // Set to true (1) to disable local storage.
	  ///
		public int local_storage_disabled;

	  ///
	  // Set to true (1) to disable databases.
	  ///
		public int databases_disabled;

	  ///
	  // Set to true (1) to disable application cache.
	  ///
		public int application_cache_disabled;

	  ///
	  // Set to true (1) to disable WebGL.
	  ///
		public int webgl_disabled;

	  ///
	  // Set to true (1) to disable accelerated compositing.
	  ///
		public int accelerated_compositing_disabled;

	  ///
	  // Set to true (1) to disable accelerated layers. This affects features like
	  // 3D CSS transforms.
	  ///
		public int accelerated_layers_disabled;

	  ///
	  // Set to true (1) to disable accelerated video.
	  ///
		public int accelerated_video_disabled;

	  ///
	  // Set to true (1) to disable accelerated 2d canvas.
	  ///
		public int accelerated_2d_canvas_disabled;

	  ///
	  // Set to true (1) to enable accelerated painting.
	  ///
		public int accelerated_painting_enabled;

	  ///
	  // Set to true (1) to enable accelerated filters.
	  ///
		public int accelerated_filters_enabled;

	  ///
	  // Set to true (1) to disable accelerated plugins.
	  ///
		public int accelerated_plugins_disabled;

	  ///
	  // Set to true (1) to disable developer tools (WebKit inspector).
	  ///
		public int developer_tools_disabled;

	  ///
	  // Set to true (1) to enable fullscreen mode.
	  ///
		public int fullscreen_enabled;
		
		public static final int sizeof = CEF3.cef_browser_settings_t_sizeof ();
}
