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

public class CEFBrowserProcessHandler {
	CEF3Object object;
	int refCount = 1;

public CEFBrowserProcessHandler() {
	object = new CEF3Object (new int[] {0, 0, 0, 0, 1, 1}) {
		public long /*int*/ method0(long /*int*/[] args) {return add_ref();}
		public long /*int*/ method1(long /*int*/[] args) {return CEFBrowserProcessHandler.this.release();}
		public long /*int*/ method2(long /*int*/[] args) {return get_refct();}
		public long /*int*/ method3(long /*int*/[] args) {return on_context_initialized();}
		public long /*int*/ method4(long /*int*/[] args) {return on_before_child_process_launch(args[0]);}
		public long /*int*/ method5(long /*int*/[] args) {return on_render_process_thread_created(args[0]);}
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
		if (Device.DEBUG) System.out.println("release: CEFBrowserProcessHandler");
		if (object != null) {
			object.dispose ();
		}
		object = null;
	}
	return refCount;
}

/* cef_browser_process_handler_t */

long /*int*/ on_context_initialized() {
	if (Device.DEBUG) System.out.println("on_context_initialized");
	return 0;
}

long /*int*/ on_before_child_process_launch(long /*int*/ command_line) {
	/* opportunity to view/modify command line before it is used by subprocess */
	if (Device.DEBUG) System.out.println("on_before_child_process_launch (TODO)");
	return 0;
}

long /*int*/ on_render_process_thread_created(long /*int*/ extra_info) {
	if (Device.DEBUG) System.out.println("on_render_process_thread_created");
	return 0;
}

}
