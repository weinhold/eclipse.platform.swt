package org.eclipse.swt.internal.cef3;

import org.eclipse.swt.browser.CEF;

public class cef_settings_t {
	// Size of this structure.
	/** @field cast=(size_t) */
	public long /*int*/ size;

	// Set to true (1) to use a single process for the browser and renderer. This
	// run mode is not officially supported by Chromium and is less stable than
	// the multi-process default.
	/** @field cast=(bool) */
	public int single_process;

	// The path to a separate executable that will be launched for sub-processes.
	// By default the browser process executable is used. See the comments on
	// CefExecuteProcess() for details.
	/** @field cast=(cef_string_t) */
	public cef_string_t browser_subprocess_path = CEF.STRING_EMPTY;

	// Set to true (1) to have the browser process message loop run in a separate
	// thread. If false (0) than the CefDoMessageLoopWork() function must be
	// called from your application message loop.
	/** @field cast=(bool) */
	public int multi_threaded_message_loop;

	// Set to true (1) to disable configuration of browser process features using
	// standard CEF and Chromium command-line arguments. Configuration can still
	// be specified using CEF data structures or via the
	// CefApp::OnBeforeCommandLineProcessing() method.
	/** @field cast=(bool) */
	public int command_line_args_disabled;

	// The location where cache data will be stored on disk. If empty an in-memory
	// cache will be used. HTML5 databases such as localStorage will only persist
	// across sessions if a cache path is specified.
	/** @field cast=(cef_string_t) */
	public cef_string_t cache_path = CEF.STRING_EMPTY;

	// Value that will be returned as the User-Agent HTTP header. If empty the
	// default User-Agent string will be used.
	/** @field cast=(cef_string_t) */
	public cef_string_t user_agent = CEF.STRING_EMPTY;

	// Value that will be inserted as the product portion of the default
	// User-Agent string. If empty the Chromium product version will be used. If
	// |userAgent| is specified this value will be ignored.
	/** @field cast=(cef_string_t) */
	public cef_string_t product_version = CEF.STRING_EMPTY;

	// The locale string that will be passed to WebKit. If empty the default
	// locale of "en-US" will be used. This value is ignored on Linux where locale
	// is determined using environment variable parsing with the precedence order:
	// LANGUAGE, LC_ALL, LC_MESSAGES and LANG.
	/** @field cast=(cef_string_t) */
	public cef_string_t locale = CEF.STRING_EMPTY;

	// The directory and file name to use for the debug log. If empty, the
	// default name of "debug.log" will be used and the file will be written
	// to the application directory.
	/** @field cast=(cef_string_t) */
	public cef_string_t log_file = CEF.STRING_EMPTY;

	// The log severity. Only messages of this severity level or higher will be
	// logged.
	/** @field cast=(int) */ //GWG should be cef_log_severity_t
	public int log_severity;

	  ///
	  // Enable DCHECK in release mode to ease debugging.
	  ///
	/** @field cast=(bool) */
	public int release_dcheck_enabled;
	  
	// Custom flags that will be used when initializing the V8 JavaScript engine.
	// The consequences of using custom flags may not be well tested.
	/** @field cast=(cef_string_t) */
	public cef_string_t javascript_flags = CEF.STRING_EMPTY;

	// Set to true (1) to use the system proxy resolver on Windows when
	// "Automatically detect settings" is checked. This setting is disabled
	// by default for performance reasons.
	/** @field cast=(bool) */
	public int auto_detect_proxy_settings_enabled;

	// The fully qualified path for the resources directory. If this value is
	// empty the cef.pak and/or devtools_resources.pak files must be located in
	// the module directory on Windows/Linux or the app bundle Resources directory
	// on Mac OS X.
	/** @field cast=(cef_string_t) */
	public cef_string_t resources_dir_path = CEF.STRING_EMPTY;

	// The fully qualified path for the locales directory. If this value is empty
	// the locales directory must be located in the module directory. This value
	// is ignored on Mac OS X where pack files are always loaded from the app
	// bundle Resources directory.
	/** @field cast=(cef_string_t) */
	public cef_string_t locales_dir_path = CEF.STRING_EMPTY;

	// Set to true (1) to disable loading of pack files for resources and locales.
	// A resource bundle handler must be provided for the browser and render
	// processes via CefApp::GetResourceBundleHandler() if loading of pack files
	// is disabled.
	/** @field cast=(bool) */
	public int pack_loading_disabled;

	// Set to a value between 1024 and 65535 to enable remote debugging on the
	// specified port. For example, if 8080 is specified the remote debugging URL
	// will be http://localhost:8080. CEF can be remotely debugged from any CEF or
	// Chrome browser window.
	/** @field cast=(int) */
	public int remote_debugging_port;

	  ///
	  // The number of stack trace frames to capture for uncaught exceptions.
	  // Specify a positive value to enable the CefV8ContextHandler::
	  // OnUncaughtException() callback. Specify 0 (default value) and
	  // OnUncaughtException() will not be called.
	  ///
	public int uncaught_exception_stack_size;

	  ///
	  // By default CEF V8 references will be invalidated (the IsValid() method will
	  // return false) after the owning context has been released. This reduces the
	  // need for external record keeping and avoids crashes due to the use of V8
	  // references after the associated context has been released.
	  //
	  // CEF currently offers two context safety implementations with different
	  // performance characteristics. The default implementation (value of 0) uses a
	  // map of hash values and should provide better performance in situations with
	  // a small number contexts. The alternate implementation (value of 1) uses a
	  // hidden value attached to each context and should provide better performance
	  // in situations with a large number of contexts.
	  //
	  // If you need better performance in the creation of V8 references and you
	  // plan to manually track context lifespan you can disable context safety by
	  // specifying a value of -1.
	  ///
	public int context_safety_implementation;
	  
	public static final int sizeof = CEF3.cef_settings_t_sizeof ();
}
