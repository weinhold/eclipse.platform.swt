package org.eclipse.swt.internal.cef3;

public class cef_display_handler_t extends cef_base_t {
	// Called when the loading state has changed.
	//	/** @field cast=(void (CEF_CALLBACK *on_loading_state_change)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, int isLoading, int canGoBack, int canGoForward))
	public long /*int*/ on_loading_state_change;
	
	// Called when a frame's address has changed.
	//	/** @field cast=(void (CEF_CALLBACK *on_address_change)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, const cef_string_t* url))
	public long /*int*/ on_address_change;
	
	// Called when the page title changes.
	//	/** @field cast=(void (CEF_CALLBACK *on_title_change)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* title))
	public long /*int*/ on_title_change;
	
	// Called when the browser is about to display a tooltip. |text| contains the
	// text that will be displayed in the tooltip. To handle the display of the
	// tooltip yourself return true (1). Otherwise, you can optionally modify
	// |text| and then return false (0) to allow the browser to display the
	// tooltip.
	//	/** @field cast=(int (CEF_CALLBACK *on_tooltip)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, cef_string_t* text))
	public long /*int*/ on_tooltip;
	
	// Called when the browser receives a status message. |text| contains the text
	// that will be displayed in the status message and |type| indicates the
	// status message type.
	//	/** @field cast=(void (CEF_CALLBACK *on_status_message)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* value))
	public long /*int*/ on_status_message;
	
	// Called to display a console message. Return true (1) to stop the message
	// from being output to the console.
	//	/** @field cast=(int (CEF_CALLBACK *on_console_message)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* message, const cef_string_t* source, int line))
	public long /*int*/ on_console_message;
	
	public static final int sizeof = CEF3.cef_display_handler_t_sizeof ();
}
