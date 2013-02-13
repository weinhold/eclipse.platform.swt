package org.eclipse.swt.internal.cef3;

import org.eclipse.swt.internal.C;

public class CEF3 extends C {

/**
 * @param windowInfo cast=(const cef_window_info_t*)
 * @param client cast=(struct _cef_client_t*)
 * @param url cast=(const cef_string_t*)
 * @param settings cast=(const struct _cef_browser_settings_t*)
 */
public static final native int cef_browser_host_create_browser(cef_window_info_t windowInfo, /*cef_client_t*/ long /*int*/ client, /*cef_string_t*/ long /*int*/ url, cef_browser_settings_t settings);

/**
 * @param windowInfo cast=(const cef_window_info_t*)
 * @param client cast=(struct _cef_client_t*)
 * @param url cast=(const cef_string_t*)
 * @param settings cast=(const struct _cef_browser_settings_t*)
 */
public static final native long /*int*/ cef_browser_host_create_browser_sync(cef_window_info_t windowInfo, cef_client_t client, cef_string_t url, long /*int*/ settings);

///
// This function should be called from the application entry point function to
// execute a secondary process. It can be used to run secondary processes from
// the browser client executable (default behavior) or from a separate
// executable specified by the CefSettings.browser_subprocess_path value. If
// called for the browser process (identified by no "type" command-line value)
// it will return immediately with a value of -1. If called for a recognized
// secondary process it will block until the process should exit and then return
// the process exit code. The |application| parameter may be NULL.
///
//CEF_EXPORT int cef_execute_process(const struct _cef_main_args_t* args,
//	struct _cef_app_t* application);
/**
 * @param args cast=(const struct _cef_main_args_t*)
 * @param application cast=(struct _cef_app_t*)
 */
public static final native int cef_execute_process(cef_main_args_t args, long /*int*/ application);

///
// This function should be called on the main application thread to initialize
// the CEF browser process. The |application| parameter may be NULL. A return
// value of true (1) indicates that it succeeded and false (0) indicates that it
// failed.
///
//CEF_EXPORT int cef_initialize(const struct _cef_main_args_t* args,
//    const struct _cef_settings_t* settings, struct _cef_app_t* application);
/**
 * @param args cast=(const struct _cef_main_args_t*)
 * @param settings cast=(const struct _cef_settings_t*)
 * @param application cast=(struct _cef_app_t*)
 */
public static final native int cef_initialize(cef_main_args_t args, cef_settings_t settings, /*cef_app_t*/ long /*int*/ application);

/* strings */
/** @param str cast=(cef_string_t*) */
public static final native void cef_string_clear(long /*int*/ str);
/**
 * @param src cast=(void*)
 * @param src_len cast=(size_t)
 * @param output cast=(cef_string_t*)
 */
public static final native int cef_string_set(char[] src, long /*int*/ src_len, /*cef_string_t*/ long /*int*/ output, int copy);
public static final native long /*int*/ cef_string_userfree_alloc();
/** @param str cast=(cef_string_userfree_t) */
public static final native void cef_string_userfree_free(long /*int*/ str);

/**
 * @param dest cast=(void *)
 * @param src cast=(const void *),flags=no_out critical
 * @param size cast=(size_t)
 */
public static final native void memmove (long /*int*/ dest, cef_app_t src, long /*int*/ size);
/**
 * @param dest cast=(void *)
 * @param src cast=(const void *),flags=no_out critical
 * @param size cast=(size_t)
 */
public static final native void memmove (long /*int*/ dest, cef_browser_process_handler_t src, long /*int*/ size);
/**
 * @param dest cast=(void *)
 * @param src cast=(const void *),flags=no_out critical
 * @param size cast=(size_t)
 */
public static final native void memmove (long /*int*/ dest, cef_client_t src, long /*int*/ size);
/**
 * @param dest cast=(void *)
 * @param src cast=(const void *),flags=no_out critical
 * @param size cast=(size_t)
 */
public static final native void memmove (long /*int*/ dest, cef_display_handler_t src, long /*int*/ size);
/**
 * @param dest cast=(void *)
 * @param src cast=(const void *),flags=no_out critical
 * @param size cast=(size_t)
 */
public static final native void memmove (long /*int*/ dest, cef_focus_handler_t src, long /*int*/ size);
/**
 * @param dest cast=(void *)
 * @param src cast=(const void *),flags=no_out critical
 * @param size cast=(size_t)
 */
public static final native void memmove (long /*int*/ dest, cef_life_span_handler_t src, long /*int*/ size);
/**
 * @param dest cast=(void *)
 * @param src cast=(const void *),flags=no_out critical
 * @param size cast=(size_t)
 */
public static final native void memmove (long /*int*/ dest, cef_load_handler_t src, long /*int*/ size);
/**
 * @param dest cast=(void *)
 * @param src cast=(const void *),flags=no_out critical
 * @param size cast=(size_t)
 */
public static final native void memmove (long /*int*/ dest, cef_proxy_handler_t src, long /*int*/ size);
/**
 * @param dest cast=(void *)
 * @param src cast=(const void *),flags=no_out critical
 * @param size cast=(size_t)
 */
public static final native void memmove (long /*int*/ dest, cef_request_handler_t src, long /*int*/ size);
/**
 * @param dest cast=(void *)
 * @param src cast=(const void *),flags=no_out critical
 * @param size cast=(size_t)
 */
public static final native void memmove (long /*int*/ dest, cef_resource_bundle_handler_t src, long /*int*/ size);

/**
 * @param dest cast=(void *),flags=no_in critical
 * @param src cast=(const void *)
 * @param size cast=(size_t)
 */
public static final native void memmove (cef_command_line_t dest, long /*int*/ src, long /*int*/ size);
/**
 * @param dest cast=(void *),flags=no_in critical
 * @param src cast=(const void *)
 * @param size cast=(size_t)
 */
public static final native void memmove (cef_string_t dest, long /*int*/ src, long /*int*/ size);

/* sizeof */
public static final native int cef_app_t_sizeof();
public static final native int cef_browser_process_handler_t_sizeof();
public static final native int cef_browser_settings_t_sizeof();
public static final native int cef_client_t_sizeof();
public static final native int cef_command_line_t_sizeof();
public static final native int cef_context_menu_handler_t_sizeof();
public static final native int cef_display_handler_t_sizeof();
public static final native int cef_focus_handler_t_sizeof();
public static final native int cef_life_span_handler_t_sizeof();
public static final native int cef_load_handler_t_sizeof();
public static final native int cef_proxy_handler_t_sizeof();
public static final native int cef_request_handler_t_sizeof();
public static final native int cef_resource_bundle_handler_t_sizeof();
public static final native int cef_settings_t_sizeof();
public static final native int cef_string_t_sizeof();
}
