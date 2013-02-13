package org.eclipse.swt.internal.cef3;

public class cef_app_t extends cef_base_t {
	  ///
	  // Provides an opportunity to view and/or modify command-line arguments before
	  // processing by CEF and Chromium. The |process_type| value will be NULL for
	  // the browser process. Do not keep a reference to the cef_command_line_t
	  // object passed to this function. The CefSettings.command_line_args_disabled
	  // value can be used to start with an NULL command-line object. Any values
	  // specified in CefSettings that equate to command-line arguments will be set
	  // before this function is called. Be cautious when using this function to
	  // modify command-line arguments for non-browser processes as this may result
	  // in undefined behavior including crashes.
	  ///
//	/** @field cast=(void (CEF_CALLBACK *on_before_command_line_processing)(struct _cef_app_t* self, const cef_string_t* process_type, struct _cef_command_line_t* command_line)) */
	public long /*int*/ on_before_command_line_processing;

	  ///
	  // Provides an opportunity to register custom schemes. Do not keep a reference
	  // to the |registrar| object. This function is called on the main thread for
	  // each process and the registered schemes should be the same across all
	  // processes.
	  ///
//	/** @field cast=(void (CEF_CALLBACK *on_register_custom_schemes)(struct _cef_app_t* self, struct _cef_scheme_registrar_t* registrar)) */
	public long /*int*/ on_register_custom_schemes;

	  ///
	  // Return the handler for resource bundle events. If
	  // CefSettings.pack_loading_disabled is true (1) a handler must be returned.
	  // If no handler is returned resources will be loaded from pack files. This
	  // function is called by the browser and render processes on multiple threads.
	  ///
//	/** @field cast=(struct _cef_resource_bundle_handler_t* (CEF_CALLBACK *get_resource_bundle_handler)(struct _cef_app_t* self)) */
	public long /*int*/ get_resource_bundle_handler;

	  ///
	  // Return the handler for functionality specific to the browser process. This
	  // function is called on multiple threads in the browser process.
	  ///
//	/** @field cast=(struct _cef_browser_process_handler_t* (CEF_CALLBACK *get_browser_process_handler)(struct _cef_app_t* self)) */
	public long /*int*/ get_browser_process_handler;

	  ///
	  // Return the handler for functionality specific to the render process. This
	  // function is called on the render process main thread.
	  ///
//	/** @field cast=(struct _cef_render_process_handler_t* (CEF_CALLBACK *get_render_process_handler)(struct _cef_app_t* self)) */
	public long /*int*/ get_render_process_handler;
	
	public static final int sizeof = CEF3.cef_app_t_sizeof();
}
