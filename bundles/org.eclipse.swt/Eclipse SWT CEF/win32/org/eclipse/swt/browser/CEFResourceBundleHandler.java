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

public class CEFResourceBundleHandler {
	CEF3Object object;
	int refCount = 1;

public CEFResourceBundleHandler() {
	object = new CEF3Object (new int[] {0, 0, 0, 2, 3}) {
		public long /*int*/ method0(long /*int*/[] args) {return add_ref();}
		public long /*int*/ method1(long /*int*/[] args) {return CEFResourceBundleHandler.this.release();}
		public long /*int*/ method2(long /*int*/[] args) {return get_refct();}
		public long /*int*/ method3(long /*int*/[] args) {return get_localized_string((int)/*64*/args[0], args[1]);}
		public long /*int*/ method4(long /*int*/[] args) {return get_data_resource((int)/*64*/args[0], args[1], args[2]);}
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
		if (Device.DEBUG) System.out.println("release: CEFResourceBundleHandler");
		if (object != null) {
			object.dispose ();
		}
		object = null;
	}
	return refCount;
}

/* cef_resource_bundle_handler_t */

long /*int*/ get_localized_string(int message_id, long /*int*/ string) {
	if (Device.DEBUG) System.out.println("get_localized_string (TODO)");
	return 0;
}

long /*int*/ get_data_resource(int resource_id, long /*int*/ data, long /*int*/ data_size) {
	if (Device.DEBUG) System.out.println("get_data_resource");
	return 0;
}

}
