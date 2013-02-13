package org.eclipse.swt.internal.cef3;

public class cef_browser_process_handler_t extends cef_base_t {
	  ///
	  // Return the handler for proxy events. If no handler is returned the default
	  // system handler will be used. This function is called on the browser process
	  // IO thread.
	  ///
//	/** @field cast=(struct _cef_proxy_handler_t* (CEF_CALLBACK *get_proxy_handler)(struct _cef_browser_process_handler_t* self);
	public long /*int*/ get_proxy_handler;
	
	  ///
	  // Called on the browser process UI thread immediately after the CEF context
	  // has been initialized.
	  ///
//	/** @field cast=(void (CEF_CALLBACK *on_context_initialized)(struct _cef_browser_process_handler_t* self);
	public long /*int*/ on_context_initialized;
	
	  ///
	  // Called on the browser process IO thread before a child process is launched.
	  // Provides an opportunity to modify the child process command line.
	  ///
//	/** @field cast=(void (CEF_CALLBACK *on_before_child_process_launch)(struct _cef_browser_process_handler_t* self, struct _cef_command_line_t* command_line);
	public long /*int*/ on_before_child_process_launch;
	
	  ///
	  // Called on the browser process IO thread after the main thread has been
	  // created for a new render process. Provides an opportunity to specify extra
	  // information that will be passed to
	  // cef_render_process_handler_t::on_render_thread_created() in the render
	  // process. Do not keep a reference to |extra_info| outside of this function.
	  ///
//	/** @field cast=(void (CEF_CALLBACK *on_render_process_thread_created)(struct _cef_browser_process_handler_t* self, struct _cef_list_value_t* extra_info);
	public long /*int*/ on_render_process_thread_created;
	
	public static final int sizeof = CEF3.cef_browser_process_handler_t_sizeof();
}
