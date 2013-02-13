package org.eclipse.swt.internal.cef3;

public class cef_life_span_handler_t extends cef_base_t {
	// Called before a new popup window is created. The |parentBrowser| parameter
	// will point to the parent browser window. The |popupFeatures| parameter will
	// contain information about the style of popup window requested. Return false
	// (0) to have the framework create the new popup window based on the
	// parameters in |windowInfo|. Return true (1) to cancel creation of the popup
	// window. By default, a newly created popup window will have the same client
	// and settings as the parent window. To change the client for the new window
	// modify the object that |client| points to. To change the settings for the
	// new window modify the |settings| structure.
//	/** @field cast=(int (CEF_CALLBACK *on_before_popup)(struct _cef_life_span_handler_t* self, struct _cef_browser_t* parentBrowser, const struct _cef_popup_features_t* popupFeatures, struct _cef_window_info_t* windowInfo, const cef_string_t* url, struct _cef_client_t** client, struct _cef_browser_settings_t* settings);
	public long /*int*/ on_before_popup;
	
	// Called after a new window is created.
//	/** @field cast=(void (CEF_CALLBACK *on_after_created)(struct _cef_life_span_handler_t* self, struct _cef_browser_t* browser);
	public long /*int*/ on_after_created;
	
	// Called when a modal window is about to display and the modal loop should
	// begin running. Return false (0) to use the default modal loop
	// implementation or true (1) to use a custom implementation.
//	/** @field cast=(int (CEF_CALLBACK *run_modal)(struct _cef_life_span_handler_t* self, struct _cef_browser_t* browser);
	public long /*int*/ run_modal;
	
	// Called when a window has recieved a request to close. Return false (0) to
	// proceed with the window close or true (1) to cancel the window close. If
	// this is a modal window and a custom modal loop implementation was provided
	// in run_modal() this callback should be used to restore the opener window to
	// a usable state.
//	/** @field cast=(int (CEF_CALLBACK *do_close)(struct _cef_life_span_handler_t* self, struct _cef_browser_t* browser);
	public long /*int*/ do_close;
	
	// Called just before a window is closed. If this is a modal window and a
	// custom modal loop implementation was provided in run_modal() this callback
	// should be used to exit the custom modal loop.
//	/** @field cast=(void (CEF_CALLBACK *on_before_close)(struct _cef_life_span_handler_t* self, struct _cef_browser_t* browser);
	public long /*int*/ on_before_close;
	
	public static final int sizeof = CEF3.cef_life_span_handler_t_sizeof();
}
