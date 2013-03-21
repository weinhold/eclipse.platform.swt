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

public class CEFFocusHandler {
	CEF3Object object;
	int refCount = 1;

public CEFFocusHandler() {
	object = new CEF3Object (new int[] {0, 0, 0, 2, 2, 1}) {
		public long /*int*/ method0(long /*int*/[] args) {return add_ref();}
		public long /*int*/ method1(long /*int*/[] args) {return CEFFocusHandler.this.release();}
		public long /*int*/ method2(long /*int*/[] args) {return get_refct();}
		public long /*int*/ method3(long /*int*/[] args) {return on_take_focus(args[0], (int)/*64*/args[1]);}
		public long /*int*/ method4(long /*int*/[] args) {return on_set_focus(args[0], (int)/*64*/args[1]);}
		public long /*int*/ method5(long /*int*/[] args) {return on_got_focus(args[0]);}
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
		if (Device.DEBUG) System.out.println("release: CEFFocusHandler");
		if (object != null) {
			object.dispose ();
		}
		object = null;
	}
	return refCount;
}

/* cef_focus_handler_t */

long /*int*/ on_take_focus(long /*int*/ browser, int next) {
	if (Device.DEBUG) System.out.println("on_take_focus (TODO)");
	new CEFBase(browser).release();
	return 0;
}

long /*int*/ on_set_focus(long /*int*/ browser, int source) {
	if (Device.DEBUG) System.out.println("on_set_focus");
	new CEFBase(browser).release();
	return 0;
}

long /*int*/ on_got_focus(long /*int*/ browser) {
	/* possibly useful */
	if (Device.DEBUG) System.out.println("on_got_focus");
	new CEFBase(browser).release();
	return 0;
}

}
