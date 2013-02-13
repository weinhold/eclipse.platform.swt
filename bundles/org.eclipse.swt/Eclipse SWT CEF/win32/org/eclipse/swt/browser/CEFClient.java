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

public class CEFClient {
	CEF3Object object;
	int refCount = 1;

public CEFClient() {
	object = new CEF3Object (new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3}) {
		public long /*int*/ method0(long /*int*/[] args) {return add_ref();}
		public long /*int*/ method1(long /*int*/[] args) {return CEFClient.this.release();}
		public long /*int*/ method2(long /*int*/[] args) {return get_refct();}
		public long /*int*/ method3(long /*int*/[] args) {return get_context_menu_handler();}
		public long /*int*/ method4(long /*int*/[] args) {return get_dialog_handler();}
		public long /*int*/ method5(long /*int*/[] args) {return get_display_handler();}
		public long /*int*/ method6(long /*int*/[] args) {return get_download_handler();}
		public long /*int*/ method7(long /*int*/[] args) {return get_focus_handler();}
		public long /*int*/ method8(long /*int*/[] args) {return get_geolocation_handler();}
		public long /*int*/ method9(long /*int*/[] args) {return get_jsdialog_handler();}
		public long /*int*/ method10(long /*int*/[] args) {return get_keyboard_handler();}
		public long /*int*/ method11(long /*int*/[] args) {return get_life_span_handler();}
		public long /*int*/ method12(long /*int*/[] args) {return get_load_handler();}
		public long /*int*/ method13(long /*int*/[] args) {return get_request_handler();}
		public long /*int*/ method14(long /*int*/[] args) {return on_process_message_received(args[0], args[1], args[2]);}
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
		if (object != null) {
			object.dispose ();
		}
		object = null;
	}
	return refCount;
}

/* cef_t */

long /*int*/ get_context_menu_handler() {
	if (Device.DEBUG) System.out.println("**get_context_menu_handler");
	return 0;
}
long /*int*/ get_dialog_handler() {
	if (Device.DEBUG) System.out.println("**get_dialog_handler");
	return 0;
}
long /*int*/ get_display_handler() {
	if (Device.DEBUG) System.out.println("get_display_handler");
	return 0/*displayHandler*/;
}
long /*int*/ get_download_handler() {
	if (Device.DEBUG) System.out.println("**get_download_handler");
	return 0;
}
long /*int*/ get_focus_handler() {
	if (Device.DEBUG) System.out.println("get_focus_handler");
	return 0/*focusHandler*/;
}
long /*int*/ get_geolocation_handler() {
	if (Device.DEBUG) System.out.println("**get_geolocation_handler");
	return 0;
}
long /*int*/ get_jsdialog_handler() {
	if (Device.DEBUG) System.out.println("**get_jsdialog_handler");
	return 0;
}
long /*int*/ get_keyboard_handler() {
	if (Device.DEBUG) System.out.println("**get_keyboard_handler");
	return 0;
}
long /*int*/ get_life_span_handler() {
	if (Device.DEBUG) System.out.println("get_life_span_handler");
	return 0/*lifeSpanHandler*/;
}
long /*int*/ get_load_handler() {
	if (Device.DEBUG) System.out.println("get_load_handler");
	return 0/*loadHandler*/;
}
long /*int*/ get_request_handler() {
	if (Device.DEBUG) System.out.println("get_request_handler");
	return 0/*requestHandler*/;
}
long /*int*/ on_process_message_received(long /*int*/ arg0, long /*int*/ arg1, long /*int*/ arg2) {
	if (Device.DEBUG) System.out.println("on_process_message_received");
	return 0;
}

}
