package org.eclipse.swt.internal.cef3;

public class cef_load_handler_t extends cef_base_t {
	  ///
	  // Called when the browser begins loading a frame. The |frame| value will
	  // never be NULL -- call the is_main() function to check if this frame is the
	  // main frame. Multiple frames may be loading at the same time. Sub-frames may
	  // start or continue loading after the main frame load has ended. This
	  // function may not be called for a particular frame if the load request for
	  // that frame fails.
	  ///
//	/** @field cast=(void (CEF_CALLBACK *on_load_start)(struct _cef_load_handler_t* self,struct _cef_browser_t* browser, struct _cef_frame_t* frame);
	public long /*int*/ on_load_start;
	
	  ///
	  // Called when the browser is done loading a frame. The |frame| value will
	  // never be NULL -- call the is_main() function to check if this frame is the
	  // main frame. Multiple frames may be loading at the same time. Sub-frames may
	  // start or continue loading after the main frame load has ended. This
	  // function will always be called for all frames irrespective of whether the
	  // request completes successfully.
	  ///
//	/** @field cast=(void (CEF_CALLBACK *on_load_end)(struct _cef_load_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, int httpStatusCode);
	public long /*int*/ on_load_end;
	
	  ///
	  // Called when the browser fails to load a resource. |errorCode| is the error
	  // code number, |errorText| is the error text and and |failedUrl| is the URL
	  // that failed to load. See net\base\net_error_list.h for complete
	  // descriptions of the error codes.
	  ///
//	/** @field cast=(void (CEF_CALLBACK *on_load_error)(struct _cef_load_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, enum cef_errorcode_t errorCode, const cef_string_t* errorText, const cef_string_t* failedUrl);
	public long /*int*/ on_load_error;
	
	  ///
	  // Called when the render process terminates unexpectedly. |status| indicates
	  // how the process terminated.
	  ///
//	/** @field cast=(void (CEF_CALLBACK *on_render_process_terminated)(struct _cef_load_handler_t* self, struct _cef_browser_t* browser, enum cef_termination_status_t status);
	public long /*int*/ on_render_process_terminated;
	
	  ///
	  // Called when a plugin has crashed. |plugin_path| is the path of the plugin
	  // that crashed.
	  ///
//	/** @field cast=(void (CEF_CALLBACK *on_plugin_crashed)(struct _cef_load_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* plugin_path);
	public long /*int*/ on_plugin_crashed;
	
	public static final int sizeof = CEF3.cef_load_handler_t_sizeof();
}
