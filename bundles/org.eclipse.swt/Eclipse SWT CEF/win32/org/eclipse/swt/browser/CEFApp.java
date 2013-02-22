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

public class CEFApp {
	CEF3Object object;
	CEFBrowserProcessHandler browserProcessHandler;
	CEFRenderProcessHandler renderProcessHandler;
	CEFResourceBundleHandler resourceBundleHandler;
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

synchronized int add_ref() {
	refCount++;
	return refCount;
}

synchronized int get_refct() {
	return refCount;
}

synchronized int release() {
	if (--refCount == 0) {
		if (Device.DEBUG) System.out.println("release: CEFApp");
		if (browserProcessHandler != null) {
			browserProcessHandler.release();
			browserProcessHandler = null;
		}
		if (renderProcessHandler != null) {
			renderProcessHandler.release();
			renderProcessHandler = null;
		}
		if (resourceBundleHandler != null) {
			resourceBundleHandler.release();
			resourceBundleHandler = null;
		}
		if (object != null) {
			object.dispose ();
		}
		object = null;
	}
	return refCount;
}

/* cef_app_t */

synchronized long /*int*/ get_browser_process_handler() {
	if (Device.DEBUG) System.out.println("get_browser_process_handler (impl)");
	if (browserProcessHandler == null) {
		browserProcessHandler = new CEFBrowserProcessHandler();
	}
	browserProcessHandler.add_ref();
	return browserProcessHandler.getAddress();
}

synchronized long /*int*/ get_render_process_handler() {
	if (Device.DEBUG) System.out.println("get_render_process_handler (impl)");
	if (renderProcessHandler == null) {
		renderProcessHandler = new CEFRenderProcessHandler();
	}
	renderProcessHandler.add_ref();
	return renderProcessHandler.getAddress();
}

long /*int*/ get_resource_bundle_handler() {
	if (Device.DEBUG) System.out.println("get_resource_bundle_handler (impl)");
	if (resourceBundleHandler == null) {
		resourceBundleHandler = new CEFResourceBundleHandler();
	}
	resourceBundleHandler.add_ref();
	return resourceBundleHandler.getAddress();
}

long /*int*/ on_before_command_line_processing(long /*int*/ process_type, long /*int*/ command_line) {
	/* 
	 * CEFBrowserProcessHandler.on_before_child_process_launch() appears to be the
	 * appropriate place to do this for our purposes.
	 */
	if (Device.DEBUG) System.out.println("on_before_command_line_processing");
	return 0;
}

long /*int*/ on_register_custom_schemes(long /*int*/ registrar) {
	if (Device.DEBUG) System.out.println("on_register_custom_schemes");
	return 0;
}

}
