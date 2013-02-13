package org.eclipse.swt.internal.cef3;

public class cef_client_t extends cef_base_t {
	// Return the handler for context menus. If no handler is provided the default
	// implementation will be used.
//	/** @field cast=(struct _cef_context_menu_handler_t* (CEF_CALLBACK *get_context_menu_handler)(struct _cef_client_t* self)) */
	public long /*int*/ get_context_menu_handler;

	  ///
	  // Return the handler for dialogs. If no handler is provided the default
	  // implementation will be used.
	  ///
//	/** @field cast=(struct _cef_dialog_handler_t* (CEF_CALLBACK *get_dialog_handler)(struct _cef_client_t* self);
	  public long /*int*/ get_dialog_handler;
	  
	// Return the handler for browser display state events.
//	/** @field cast=(struct _cef_display_handler_t* (CEF_CALLBACK *get_display_handler)(struct _cef_client_t* self)) */
	public long /*int*/ get_display_handler;

	// Return the handler for download events. If no handler is returned downloads
	// will not be allowed.
//	/** @field cast=(struct _cef_download_handler_t* (CEF_CALLBACK *get_download_handler)(struct _cef_client_t* self)) */
	public long /*int*/ get_download_handler;

	// Return the handler for focus events.
//	/** @field cast=(struct _cef_focus_handler_t* (CEF_CALLBACK *get_focus_handler)(struct _cef_client_t* self)) */
	public long /*int*/ get_focus_handler;

	// Return the handler for geolocation permissions requests. If no handler is
	// provided geolocation access will be denied by default.
//	/** @field cast=(struct _cef_geolocation_handler_t* (CEF_CALLBACK *get_geolocation_handler)(struct _cef_client_t* self)) */
	public long /*int*/ get_geolocation_handler;

	// Return the handler for JavaScript dialogs. If no handler is provided the
	// default implementation will be used.
//	/** @field cast=(struct _cef_jsdialog_handler_t* (CEF_CALLBACK *get_jsdialog_handler)(struct _cef_client_t* self)) */
	public long /*int*/ get_jsdialog_handler;

	// Return the handler for keyboard events.
//	/** @field cast=(struct _cef_keyboard_handler_t* (CEF_CALLBACK *get_keyboard_handler)(struct _cef_client_t* self)) */
	public long /*int*/ get_keyboard_handler;

	// Return the handler for browser life span events.
//	/** @field cast=(struct _cef_life_span_handler_t* (CEF_CALLBACK *get_life_span_handler)(struct _cef_client_t* self)) */
	public long /*int*/ get_life_span_handler;

	// Return the handler for browser load status events.
//	/** @field cast=(struct _cef_load_handler_t* (CEF_CALLBACK *get_load_handler)(struct _cef_client_t* self)) */
	public long /*int*/ get_load_handler;

	// Return the handler for browser request events.
//	/** @field cast=(struct _cef_request_handler_t* (CEF_CALLBACK *get_request_handler)(struct _cef_client_t* self)) */
	public long /*int*/ get_request_handler;

	// Called when a new message is received from a different process. Return true
	// (1) if the message was handled or false (0) otherwise. Do not keep a
	// reference to or attempt to access the message outside of this callback.
//	/** @field cast=(int (CEF_CALLBACK *on_process_message_received)(struct _cef_client_t* self, struct _cef_browser_t* browser, enum cef_process_id_t source_process, struct _cef_process_message_t* message)) */
	public long /*int*/ on_process_message_received;
	
	public static final int sizeof = CEF3.cef_client_t_sizeof ();
}
