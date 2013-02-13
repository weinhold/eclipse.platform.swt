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
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.Callback;
import org.eclipse.swt.internal.cef3.CEF3;
import org.eclipse.swt.internal.cef3.CEF3Object;
import org.eclipse.swt.internal.cef3.cef_browser_process_handler_t;

public class CEFApp {
	CEF3Object object;
	CEFBrowserProcessHandler browserProcessHandler;
	int refCount = 1;

public CEFApp() {
	object = new CEF3Object (new int[] {0, 0, 0, 2, 1, 0, 0, 0}) {
		public long /*int*/ method0(long /*int*/[] args) {return add_ref();}
		public long /*int*/ method1(long /*int*/[] args) {return CEFApp.this.release();}
		public long /*int*/ method2(long /*int*/[] args) {return get_refct();}
		public long /*int*/ method3(long /*int*/[] args) {return on_before_command_line_processing(args[0], args[1]);}
		public long /*int*/ method4(long /*int*/[] args) {return on_register_custom_schemes(args[0]);}
		public long /*int*/ method5(long /*int*/[] args) {return get_resource_bundle_handler();}
		public long /*int*/ method6(long /*int*/[] args) {return get_browser_process_handler();}
		public long /*int*/ method7(long /*int*/[] args) {return get_render_process_handler();}
	};
}

long /*int*/ getAddress () {
	return object.getAddress ();
}

/* cef_base_t */

int add_ref() {
	refCount++;
	return refCount;
}

int get_refct() {
	return refCount;
}

int release() {
	if (--refCount == 0) {
		if (browserProcessHandler != null) {
			browserProcessHandler.release();
			browserProcessHandler = null;
		}
		if (object != null) {
			object.dispose ();
		}
		object = null;
	}
	return refCount;
}

/* cef_app_t */

long /*int*/ get_browser_process_handler() {
	if (Device.DEBUG) System.out.println("get_browser_process_handler");
	if (browserProcessHandler == null) {
		browserProcessHandler = new CEFBrowserProcessHandler();
		browserProcessHandler.add_ref();
	}
	return browserProcessHandler.getAddress();
}
long /*int*/ get_render_process_handler() {
	if (Device.DEBUG) System.out.println("**get_render_process_handler");
	return 0;
}
long /*int*/ get_resource_bundle_handler() {
	if (Device.DEBUG) System.out.println("**get_resource_bundle_handler");
	return 0;
}
long /*int*/ on_before_command_line_processing(long /*int*/ self, long /*int*/ arg1) {
	/* opportunity to view/modify command line before it is used by subprocess */
	if (Device.DEBUG) System.out.println("on_before_command_line_processing");
	return 0;
}
long /*int*/ on_register_custom_schemes(long /*int*/ self) {
	if (Device.DEBUG) System.out.println("on_register_custom_schemes");
	return 0;
}

}
