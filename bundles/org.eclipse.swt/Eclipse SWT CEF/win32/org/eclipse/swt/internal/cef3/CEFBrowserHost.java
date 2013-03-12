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
package org.eclipse.swt.internal.cef3;


public class CEFBrowserHost extends CEFBase {
	cef_browser_host_t struct;

public CEFBrowserHost(long /*int*/ address) {
	super(address);
	struct = new cef_browser_host_t();
	CEF3.memmove(struct, address, cef_browser_host_t.sizeof);
}

public long /*int*/ get_window_handle() {
	return CEF3.callFuncPtr(struct.get_window_handle, address);
}

}
