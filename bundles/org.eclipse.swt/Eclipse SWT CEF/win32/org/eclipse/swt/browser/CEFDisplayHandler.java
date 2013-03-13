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

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.internal.cef3.CEF3Object;
import org.eclipse.swt.internal.cef3.CEFFrame;
import org.eclipse.swt.widgets.Display;

public class CEFDisplayHandler {
	CEF3Object object;
	CEF host;
	int refCount = 1;

public CEFDisplayHandler(CEF host) {
	this.host = host;
	object = new CEF3Object (new int[] {0, 0, 0, 4, 3, 2, 2, 2, 4}) {
		public long /*int*/ method0(long /*int*/[] args) {return add_ref();}
		public long /*int*/ method1(long /*int*/[] args) {return CEFDisplayHandler.this.release();}
		public long /*int*/ method2(long /*int*/[] args) {return get_refct();}
		public long /*int*/ method3(long /*int*/[] args) {return on_loading_state_change(args[0], (int)/*64*/args[1], (int)/*64*/args[2], (int)/*64*/args[3]);}
		public long /*int*/ method4(long /*int*/[] args) {return on_address_change(args[0], args[1], args[2]);}
		public long /*int*/ method5(long /*int*/[] args) {return on_title_change(args[0], args[1]);}
		public long /*int*/ method6(long /*int*/[] args) {return on_tooltip(args[0], args[1]);}
		public long /*int*/ method7(long /*int*/[] args) {return on_status_message(args[0], args[1]);}
		public long /*int*/ method8(long /*int*/[] args) {return on_console_message(args[0], args[1], args[2], (int)/*64*/args[3]);}
	};
}

long /*int*/ getAddress () {
	return object.getAddress ();
}

/* cef_base_t */

synchronized int add_ref() {
	refCount++;
	return refCount;
}

synchronized int get_refct() {
	return refCount;
}

synchronized int release() {
	if (--refCount == 0) {
		if (Device.DEBUG) System.out.println("release: CEFDisplayHandler");
		if (object != null) {
			object.dispose ();
		}
		object = null;
	}
	return refCount;
}

/* cef_display_handler_t */

long /*int*/ on_loading_state_change(long /*int*/ browser, int isLoading, int canGoBack, int canGoForward) {
	if (Device.DEBUG) System.out.println("on_loading_state_change (TODO)");
	return 0;
}

long /*int*/ on_address_change(long /*int*/ browser, long /*int*/ frame, long /*int*/ url) {
	if (Device.DEBUG) System.out.println("on_address_change (impl)");
	
	CEFFrame cefFrame = new CEFFrame(frame);
	final String location = CEF.getUrl(cefFrame);
	final boolean top = cefFrame.is_main() == 1;
	
	Display.getDefault().asyncExec(new Runnable() {
		public void run() {
			host.onLocationChange(location, top);
		}
	});
	return 0;
}

long /*int*/ on_title_change(long /*int*/ browser, long /*int*/ title) {
	if (Device.DEBUG) System.out.println("on_title_change (impl)");
	
	final String titleString = CEF.ExtractCEFString(title);
	
	Display.getDefault().asyncExec(new Runnable() {
		public void run() {
			host.onTitleChange(titleString);
		}
	});

	return 0;
}

long /*int*/ on_tooltip(long /*int*/ browser, long /*int*/ text) {
	if (Device.DEBUG) System.out.println("on_tooltip");
	return 0;
}

long /*int*/ on_status_message(long /*int*/ browser, long /*int*/ value) {
	if (Device.DEBUG) System.out.println("on_status_message (impl)");
	
	final String statusString = CEF.ExtractCEFString(value);
	
	Display.getDefault().asyncExec(new Runnable() {
		public void run() {
			host.onStatusMessage(statusString);
		}
	});
	
	return 0;
}

long /*int*/ on_console_message(long /*int*/ browser, long /*int*/ message, long /*int*/ source, int line) {
	if (Device.DEBUG) System.out.println("on_console_message");
	return 0;
}

}
