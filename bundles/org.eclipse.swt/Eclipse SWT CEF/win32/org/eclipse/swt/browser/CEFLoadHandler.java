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

public class CEFLoadHandler {
	CEF3Object object;
	CEF host;
	int refCount = 1;

public CEFLoadHandler(CEF host) {
	this.host = host;
	object = new CEF3Object (new int[] {0, 0, 0, 2, 3, 5, 2, 2}) {
		public long /*int*/ method0(long /*int*/[] args) {return add_ref();}
		public long /*int*/ method1(long /*int*/[] args) {return CEFLoadHandler.this.release();}
		public long /*int*/ method2(long /*int*/[] args) {return get_refct();}
		public long /*int*/ method3(long /*int*/[] args) {return on_load_start(args[0], args[1]);}
		public long /*int*/ method4(long /*int*/[] args) {return on_load_end(args[0], args[1], (int)/*64*/args[2]);}
		public long /*int*/ method5(long /*int*/[] args) {return on_load_error(args[0], args[1], (int)/*64*/args[2], args[3], args[4]);}
		public long /*int*/ method6(long /*int*/[] args) {return on_render_process_terminated(args[0], (int)/*64*/args[1]);}
		public long /*int*/ method7(long /*int*/[] args) {return on_plugin_crashed(args[0], args[1]);}
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
		if (Device.DEBUG) System.out.println("release: CEFLifeSpanHandler");
		if (object != null) {
			object.dispose ();
		}
		object = null;
	}
	return refCount;
}

/* cef_load_handler_t */

long /*int*/ on_load_start(long /*int*/ browser, long /*int*/ frame) {
	if (Device.DEBUG) System.out.println("on_load_start (TODO)");
	return 0;
}

long /*int*/ on_load_end(long /*int*/ browser, long /*int*/ frame, int httpStatusCode) {
	if (Device.DEBUG) System.out.println("on_load_end (impl)");
	
	CEFFrame cefFrame = new CEFFrame(frame);
	if (cefFrame.is_main() == 1) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				host.onLoadComplete();
			}
		});
	}
	return 0;
}

long /*int*/ on_load_error(long /*int*/ browser, long /*int*/ frame, int errorCode, long /*int*/ errorText, long /*int*/ failedUrl) {
	/* possibly useful if on_load_end() not received for load errors */
	if (Device.DEBUG) System.out.println("on_load_error");
	return 0;
}

long /*int*/ on_render_process_terminated(long /*int*/ browser, int status) {
	/* possibly useful if no other notification received for this */
	if (Device.DEBUG) System.out.println("on_render_process_terminated");
	return 0;
}

long /*int*/ on_plugin_crashed(long /*int*/ browser, long /*int*/ plugin_path) {
	/* possibly useful if no other notification received for this */
	if (Device.DEBUG) System.out.println("on_plugin_crashed");
	return 0;
}

}
