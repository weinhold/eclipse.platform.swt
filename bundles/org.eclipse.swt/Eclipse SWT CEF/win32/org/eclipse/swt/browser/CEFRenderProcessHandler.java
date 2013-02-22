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

public class CEFRenderProcessHandler {
	CEF3Object object;
	int refCount = 1;

public CEFRenderProcessHandler() {
	object = new CEF3Object (new int[] {0, 0, 0, 0, 0, 1, 1, 3, 3, 3, 3}) {
		public long /*int*/ method0(long /*int*/[] args) {return add_ref();}
		public long /*int*/ method1(long /*int*/[] args) {return CEFRenderProcessHandler.this.release();}
		public long /*int*/ method2(long /*int*/[] args) {return get_refct();}
		public long /*int*/ method3(long /*int*/[] args) {return on_render_thread_created();}
		public long /*int*/ method4(long /*int*/[] args) {return on_web_kit_initialized();}
		public long /*int*/ method5(long /*int*/[] args) {return on_browser_created(args[0]);}
		public long /*int*/ method6(long /*int*/[] args) {return on_browser_destroyed(args[0]);}
		public long /*int*/ method7(long /*int*/[] args) {return on_context_created(args[0], args[1], args[2]);}
		public long /*int*/ method8(long /*int*/[] args) {return on_context_released(args[0], args[1], args[2]);}
		public long /*int*/ method9(long /*int*/[] args) {return on_focused_node_changed(args[0], args[1], args[2]);}
		public long /*int*/ method10(long /*int*/[] args) {return on_process_message_received(args[0], (int)/*64*/args[1], args[2]);}
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
		if (Device.DEBUG) System.out.println("release: CEFRenderProcessHandler");
		if (object != null) {
			object.dispose ();
		}
		object = null;
	}
	return refCount;
}

/* cef_render_process_handler_t */

long /*int*/ on_render_thread_created() {
	if (Device.DEBUG) System.out.println("on_render_thread_created");
	return 0;
}

long /*int*/ on_web_kit_initialized() {
	if (Device.DEBUG) System.out.println("on_web_kit_initialized");
	return 0;
}

long /*int*/ on_browser_created(long /*int*/ browser) {
	/* possibly helpful */
	if (Device.DEBUG) System.out.println("on_browser_created");
	return 0;
}

long /*int*/ on_browser_destroyed(long /*int*/ browser) {
	/* possibly helpful */
	if (Device.DEBUG) System.out.println("on_browser_destroyed");
	return 0;
}

long /*int*/ on_context_created(long /*int*/ browser, long /*int*/ frame, long /*int*/ context) {
	if (Device.DEBUG) System.out.println("on_context_created (TODO)");
	return 0;
}

long /*int*/ on_context_released(long /*int*/ browser, long /*int*/ frame, long /*int*/ context) {
	if (Device.DEBUG) System.out.println("on_context_released");
	return 0;
}

long /*int*/ on_focused_node_changed(long /*int*/ browser, long /*int*/ frame, long /*int*/ node) {
	if (Device.DEBUG) System.out.println("on_focused_node_changed");
	return 0;
}

long /*int*/ on_process_message_received(long /*int*/ browser, int source_process, long /*int*/ message) {
	if (Device.DEBUG) System.out.println("on_process_message_received");
	return 0;
}

}
