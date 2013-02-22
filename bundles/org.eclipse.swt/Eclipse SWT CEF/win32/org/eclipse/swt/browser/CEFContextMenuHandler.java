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

public class CEFContextMenuHandler {
	CEF3Object object;
	int refCount = 1;

public CEFContextMenuHandler() {
	object = new CEF3Object (new int[] {0, 0, 0, 4, 5, 2}) {
		public long /*int*/ method0(long /*int*/[] args) {return add_ref();}
		public long /*int*/ method1(long /*int*/[] args) {return CEFContextMenuHandler.this.release();}
		public long /*int*/ method2(long /*int*/[] args) {return get_refct();}
		public long /*int*/ method3(long /*int*/[] args) {return on_before_context_menu(args[0], args[1], args[2], args[3]);}
		public long /*int*/ method4(long /*int*/[] args) {return on_context_menu_command(args[0], args[1], args[2], (int)/*64*/args[3], (int)/*64*/args[4]);}
		public long /*int*/ method5(long /*int*/[] args) {return on_context_menu_dismissed(args[0], args[1]);}
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
		if (Device.DEBUG) System.out.println("release: CEFContextMenuHandler");
		if (object != null) {
			object.dispose ();
		}
		object = null;
	}
	return refCount;
}

/* cef_context_menu_handler_t */

long /*int*/ on_before_context_menu(long /*int*/ browser, long /*int*/ frame, long /*int*/ params, long /*int*/ model) {
	if (Device.DEBUG) System.out.println("on_before_context_menu (TODO)");
	return 0;
}

long /*int*/ on_context_menu_command(long /*int*/ browser, long /*int*/ frame, long /*int*/ params, int command_id, int event_flags) {
	if (Device.DEBUG) System.out.println("on_context_menu_command");
	return 0;
}

long /*int*/ on_context_menu_dismissed(long /*int*/ browser, long /*int*/ frame) {
	if (Device.DEBUG) System.out.println("on_before_context_menu");
	return 0;
}

}
