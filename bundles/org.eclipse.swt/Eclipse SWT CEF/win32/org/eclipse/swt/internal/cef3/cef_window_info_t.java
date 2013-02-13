package org.eclipse.swt.internal.cef3;

public class cef_window_info_t {
	// Standard parameters required by CreateWindowEx()
	/** @field cast=(DWORD) */
	public int ex_style;
	/** @field cast=(cef_string_t) */
	public cef_string_t window_name;
	/** @field cast=(DWORD) */
	public int style;
	/** @field cast=(int) */
	public int x;
	/** @field cast=(int) */
	public int y;
	/** @field cast=(int) */
	public int width;
	/** @field cast=(int) */
	public int height;
	/** @field cast=(cef_window_handle_t) */
	public long /*int*/ parent_window;
	/** @field cast=(HMENU) */
	public long /*int*/ menu;

	// Set to true to enable transparent painting.
	/** @field cast=(BOOL) */
	public int transparent_painting;

	// Handle for the new browser window.
	/** @field cast=(cef_window_handle_t) */ // on win32 it's an HWND
	public long /*int*/ window;
}
