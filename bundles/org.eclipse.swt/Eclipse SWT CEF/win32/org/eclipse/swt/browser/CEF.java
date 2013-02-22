/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.browser;


import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.cef3.*;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.*;

public class CEF extends WebBrowser {
	boolean ignoreDispose;
	CEFClient client;
	CEFBrowser cefBrowser;

	static boolean LibraryLoaded;
	static CEFApp App;
	
	public static cef_string_t STRING_EMPTY;

	static {
		try {
			// TODO modify the OS PATH to include the CEF3 install dir read from a Java property
			Library.loadLibrary("swt-cef3"); // $NON-NLS-1$

			/* initialize STRING_EMPTY */
			long /*int*/ string = CEF3.cef_string_userfree_alloc();
			CEF3.cef_string_clear(string);
			STRING_EMPTY = new cef_string_t();
			CEF3.memmove(STRING_EMPTY, string, cef_string_t.sizeof);

			/* initialize CEF3 */
			cef_main_args_t args = new cef_main_args_t();
			args.instance = OS.GetModuleHandle(null);
			App = new CEFApp();
			App.add_ref();
			int exitCode = CEF3.cef_execute_process(args, App.getAddress());
			if (exitCode < 0) {
				cef_settings_t settings = new cef_settings_t();
				settings.size = cef_settings_t.sizeof;
				settings.multi_threaded_message_loop = 1;
				settings.release_dcheck_enabled = 1; // TODO remove this before release to master
				// TODO extract cef3_subprocess.exe from the jar appropriately, point at its extracted location
			//	long /*int*/ subprocessPath = createCEFString("C:\\cef3-1180\\chromium\\src\\cef\\binary_distrib\\cef_binary_3.1180.None_windows\\Release\\cef3_subprocess.exe");
				long /*int*/ subprocessPath = createCEFString("C:\\cef3bin-1180\\cef_binary_3.1180.823_windows\\Release\\cef3_subprocess.exe");
				settings.browser_subprocess_path = new cef_string_t();
				CEF3.memmove(settings.browser_subprocess_path, subprocessPath, cef_string_t.sizeof);

				App.add_ref();
				int rc = CEF3.cef_initialize(args, settings, App.getAddress());
				if (rc != 0) {
					LibraryLoaded = true;
				}
			}
		} catch (Throwable e) {
			/* failed to load the swt-cef3 library, so no CEF3 support */
		}

		if (LibraryLoaded) {
			Display.getCurrent().addListener(SWT.Dispose,  new Listener() {
				public void handleEvent(Event event) {
					App.release();
					App = null;
				}
			});

			NativeClearSessions = new Runnable() {
				public void run() {
					if (!LibraryLoaded) return;
					// TODO do it
				}
			};

			NativeGetCookie = new Runnable() {
				public void run() {
					if (!LibraryLoaded) return;
					// TODO use CookieUrl and CookieName, set value of CookieValue
				}
			};

			NativeSetCookie = new Runnable() {
				public void run() {
					if (!LibraryLoaded) return;
					// TODO set CookieValue on CookieUrl, set CookieResult to true if successful
				}
			};

			if (NativePendingCookies != null) {
				SetPendingCookies(NativePendingCookies);
				NativePendingCookies = null;
			}
		} else {
			if (App != null) {
				App.release();
				App = null;
			}
		}
	}

static boolean IsInstalled() {
	if (!LibraryLoaded) return false;
	// TODO if CEF3 has API to get its version then verify that it is supported, for now assume that it is
	return true;
}

static long /*int*/ createCEFString(String string) {
	char[] chars = new char[string.length()];
	string.getChars(0, string.length(), chars, 0);
	long /*int*/ result = CEF3.cef_string_userfree_alloc();
	CEF3.cef_string_set(chars, chars.length, result, 1);
	return result;
}

public void create(Composite parent, int style) {
	cef_window_info_t windowInfo = new cef_window_info_t();
	int extStyle = OS.WS_EX_NOINHERITLAYOUT;
	if ((style & SWT.BORDER) != 0) extStyle |= OS.WS_EX_CLIENTEDGE;
	if ((style & SWT.RIGHT_TO_LEFT) != 0) extStyle |= OS.WS_EX_LAYOUTRTL;
	windowInfo.ex_style = extStyle; 
	windowInfo.style = OS.WS_CHILD | OS.WS_VISIBLE | OS.WS_CLIPSIBLINGS;
	windowInfo.x = windowInfo.width = OS.CW_USEDEFAULT;
	windowInfo.ex_style = 0; 
	windowInfo.window_name = STRING_EMPTY;
	windowInfo.parent_window = browser.handle;

	cef_browser_settings_t browserSettings = new cef_browser_settings_t();
	browserSettings.size = cef_browser_settings_t.sizeof;

	//long /*int*/ url = createCEFString("http://www.google.com/");
	long /*int*/ url = createCEFString("about:blank");
	client = new CEFClient(this);
	client.add_ref();
	int rc = CEF3.cef_browser_host_create_browser(windowInfo, client.getAddress(), url, browserSettings);
	if (rc == 0) {
		return;
	}

	Listener listener = new Listener() {
		public void handleEvent(Event event) {
			switch (event.type) {
				case SWT.Dispose: {
					/* make this handler run after other dispose listeners */
					if (ignoreDispose) {
						ignoreDispose = false;
						break;
					}
					ignoreDispose = true;
					browser.notifyListeners(event.type, event);
					event.type = SWT.NONE;
					onDispose(event);
					break;
				}
			}
		}
	};
	browser.addListener(SWT.Dispose, listener);
	browser.addListener(SWT.KeyDown, listener);
}

public boolean back() {
	// TODO
	return false;
}

public boolean close() {
	// TODO
	return false;
}

void browserCreated(long /*int*/ handle) {
	cefBrowser = new CEFBrowser(handle);
	cefBrowser.add_ref(); // TODO release() when disposed
}

public boolean execute(String script) {
	// TODO
	return false;
}

public boolean forward() {
	// TODO
	return false;
}

public String getBrowserType() {
	// TODO should this return "CEF3"?
	return "webkit"; //$NON-NLS-1$
}

public String getText() {
	// TODO
	return null;
}

public String getUrl() {
	// TODO
	return null;
}

public boolean isBackEnabled() {
	// TODO
	return false;
}

public boolean isForwardEnabled() {
	// TODO
	return false;
}

void onDispose(Event e) {
	if (client != null) {
		client.release();
	}
	client = null;
	if (cefBrowser != null) {
		cefBrowser.release();
	}
	cefBrowser = null;
	// TODO more clean up
}

public void refresh() {
	// TODO
}

public boolean setText(String html, boolean trusted) {
	// TODO
	return false;
}

public boolean setUrl(String url, String postData, String[] headers) {
	// TODO postData, headers...
	long /*int*/ result = cefBrowser.get_main_frame();
	if (result == 0) {
		return false;
	}

	CEFFrame frame = new CEFFrame(result);
	long /*int*/ urlPtr = createCEFString(url);
	frame.load_url(urlPtr);
	return true;
}

public void stop() {
	// TODO
}

}
