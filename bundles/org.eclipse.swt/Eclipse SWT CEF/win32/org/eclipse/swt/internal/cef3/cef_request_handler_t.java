package org.eclipse.swt.internal.cef3;

public class cef_request_handler_t extends cef_base_t {
	  ///
	  // Called on the IO thread before a resource request is loaded. The |request|
	  // object may be modified. To cancel the request return true (1) otherwise
	  // return false (0).
	  ///
//	/** @field cast=(int (CEF_CALLBACK *on_before_resource_load)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request);
	public long /*int*/ on_before_resource_load;
	
	  ///
	  // Called on the IO thread before a resource is loaded. To allow the resource
	  // to load normally return NULL. To specify a handler for the resource return
	  // a cef_resource_handler_t object. The |request| object should not be
	  // modified in this callback.
	  ///
//	/** @field cast=(struct _cef_resource_handler_t* (CEF_CALLBACK *get_resource_handler)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request);
	public long /*int*/ get_resource_handler;
	
	  ///
	  // Called on the IO thread when a resource load is redirected. The |old_url|
	  // parameter will contain the old URL. The |new_url| parameter will contain
	  // the new URL and can be changed if desired.
	  ///
//	/** @field cast=(void (CEF_CALLBACK *on_resource_redirect)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, const cef_string_t* old_url, cef_string_t* new_url);
	public long /*int*/ on_resource_redirect;
	
	  ///
	  // Called on the IO thread when the browser needs credentials from the user.
	  // |isProxy| indicates whether the host is a proxy server. |host| contains the
	  // hostname and |port| contains the port number. Return true (1) to continue
	  // the request and call cef_auth_callback_t::Complete() when the
	  // authentication information is available. Return false (0) to cancel the
	  // request.
	  ///
//	/** @field cast=(int (CEF_CALLBACK *get_auth_credentials)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, int isProxy, const cef_string_t* host, int port, const cef_string_t* realm, const cef_string_t* scheme, struct _cef_auth_callback_t* callback);
	public long /*int*/ get_auth_credentials;
	
	  ///
	  // Called on the IO thread when JavaScript requests a specific storage quota
	  // size via the webkitStorageInfo.requestQuota function. |origin_url| is the
	  // origin of the page making the request. |new_size| is the requested quota
	  // size in bytes. Return true (1) and call cef_quota_callback_t::Complete()
	  // either in this function or at a later time to grant or deny the request.
	  // Return false (0) to cancel the request.
	  ///
//	/** @field cast=(int (CEF_CALLBACK *on_quota_request)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* origin_url, int64 new_size, struct _cef_quota_callback_t* callback);
	public long /*int*/ on_quota_request;
	
	  ///
	  // Called on the IO thread to retrieve the cookie manager. |main_url| is the
	  // URL of the top-level frame. Cookies managers can be unique per browser or
	  // shared across multiple browsers. The global cookie manager will be used if
	  // this function returns NULL.
	  ///
//	/** @field cast=(struct _cef_cookie_manager_t* (CEF_CALLBACK *get_cookie_manager)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* main_url);
	public long /*int*/ get_cookie_manager;
	
	  ///
	  // Called on the UI thread to handle requests for URLs with an unknown
	  // protocol component. Set |allow_os_execution| to true (1) to attempt
	  // execution via the registered OS protocol handler, if any. SECURITY WARNING:
	  // YOU SHOULD USE THIS METHOD TO ENFORCE RESTRICTIONS BASED ON SCHEME, HOST OR
	  // OTHER URL ANALYSIS BEFORE ALLOWING OS EXECUTION.
	  ///
//	/** @field cast=(void (CEF_CALLBACK *on_protocol_execution)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* url, int* allow_os_execution);
	public long /*int*/ on_protocol_execution;
	
	  ///
	  // Called on the browser process IO thread before a plugin is loaded. Return
	  // true (1) to block loading of the plugin.
	  ///
//	/** @field cast=(int (CEF_CALLBACK *on_before_plugin_load)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* url, const cef_string_t* policy_url, struct _cef_web_plugin_info_t* info);
	public long /*int*/ on_before_plugin_load;
	
	public static final int sizeof = CEF3.cef_request_handler_t_sizeof();
}
