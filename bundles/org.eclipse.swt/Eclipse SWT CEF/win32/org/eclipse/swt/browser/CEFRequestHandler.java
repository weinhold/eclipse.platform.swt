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
import org.eclipse.swt.internal.cef3.*;

public class CEFRequestHandler {
	CEF3Object object;
	int refCount = 1;

public CEFRequestHandler() {
	object = new CEF3Object (new int[] {0, 0, 0, 3, 3, 4, 8, 4, 2, 3, 4}) {
		public long /*int*/ method0(long /*int*/[] args) {return add_ref();}
		public long /*int*/ method1(long /*int*/[] args) {return CEFRequestHandler.this.release();}
		public long /*int*/ method2(long /*int*/[] args) {return get_refct();}
		public long /*int*/ method3(long /*int*/[] args) {return on_before_resource_load(args[0], args[1], args[2]);}
		public long /*int*/ method4(long /*int*/[] args) {return get_resource_handler(args[0], args[1], args[2]);}
		public long /*int*/ method5(long /*int*/[] args) {return on_resource_redirect(args[0], args[1], args[2], args[3]);}
		public long /*int*/ method6(long /*int*/[] args) {return get_auth_credentials(args[0], args[1], (int)/*64*/args[2], args[3], (int)/*64*/args[4], args[5], args[6], args[7]);}
		public long /*int*/ method7(long /*int*/[] args) {return on_quota_request(args[0], args[1], /*int64*/args[2], args[3]);}
		public long /*int*/ method8(long /*int*/[] args) {return get_cookie_manager(args[0], args[1]);}
		public long /*int*/ method9(long /*int*/[] args) {return on_protocol_execution(args[0], args[1], args[2]);}
		public long /*int*/ method10(long /*int*/[] args) {return on_before_plugin_load(args[0], args[1], args[2], args[3]);}
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
		if (Device.DEBUG) System.out.println("release: CEFRequestHandler");
		if (object != null) {
			object.dispose ();
		}
		object = null;
	}
	return refCount;
}

/* cef_request_handler_t */

long /*int*/ on_before_resource_load(long /*int*/ browser, long /*int*/ frame, long /*int*/ request) {
	if (Device.DEBUG) System.out.println("on_before_resource_load");
	new CEFBase(browser).release();
	new CEFBase(frame).release();
	new CEFBase(request).release();
	return 0;
}

long /*int*/ get_resource_handler(long /*int*/ browser, long /*int*/ frame, long /*int*/ request) {
	if (Device.DEBUG) System.out.println("get_resource_handler");
	new CEFBase(browser).release();
	new CEFBase(frame).release();
	new CEFBase(request).release();
	return 0;
}

long /*int*/ on_resource_redirect(long /*int*/ browser, long /*int*/ frame, long /*int*/ old_url, long /*int*/ new_url) {
	if (Device.DEBUG) System.out.println("on_resource_redirect");
	new CEFBase(browser).release();
	new CEFBase(frame).release();
	return 0;
}

long /*int*/ get_auth_credentials(long /*int*/ browser, long /*int*/ frame, int isProxy, long /*int*/ host, int port, long /*int*/ realm, long /*int*/ scheme, long /*int*/ callback) {
	if (Device.DEBUG) System.out.println("get_auth_credentials (TODO)");
	new CEFBase(browser).release();
	new CEFBase(frame).release();
	new CEFBase(callback).release();
	return 0;
}

long /*int*/ on_quota_request(long /*int*/ browser, long /*int*/ origin_url, /*int64*/ long /*int*/ new_size, long /*int*/ callback) {
	if (Device.DEBUG) System.out.println("on_quota_request (note: has int64 param)");
	new CEFBase(browser).release();
	new CEFBase(callback).release();
	return 0;
}

long /*int*/ get_cookie_manager(long /*int*/ browser, long /*int*/ main_url) {
	if (Device.DEBUG) System.out.println("get_cookie_manager");
	new CEFBase(browser).release();
	return 0;
}

long /*int*/ on_protocol_execution(long /*int*/ browser, long /*int*/ url, long /*int*/ allow_os_execution) {
	if (Device.DEBUG) System.out.println("on_protocol_execution");
	new CEFBase(browser).release();
	return 0;
}

long /*int*/ on_before_plugin_load(long /*int*/ browser, long /*int*/ url, long /*int*/ policy_url, long /*int*/ info) {
	if (Device.DEBUG) System.out.println("on_before_plugin_load");
	new CEFBase(browser).release();
	new CEFBase(info).release();
	return 0;
}

}
