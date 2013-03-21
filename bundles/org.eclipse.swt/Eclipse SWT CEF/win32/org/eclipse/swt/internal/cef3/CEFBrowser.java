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


public class CEFBrowser extends CEFBase {
	cef_browser_t struct;

public CEFBrowser(long /*int*/ address) {
	super(address);
	struct = new cef_browser_t();
	CEF3.memmove(struct, address, cef_browser_t.sizeof);
}

public long /*int*/ get_host() {
	return CEF3.callFuncPtr(struct.get_host, address);
}

public int can_go_back() {
	return CEF3.callFuncInt(struct.can_go_back, address);
}

public void go_back() {
	CEF3.callFuncPtr(struct.go_back, address);
}

public int can_go_forward() {
	return CEF3.callFuncInt(struct.can_go_forward, address);
}

public void go_forward() {
	CEF3.callFuncPtr(struct.go_forward, address);
}

public void reload() {
	CEF3.callFuncPtr(struct.reload, address);
}

public void stop_load() {
	CEF3.callFuncPtr(struct.stop_load, address);
}

public long /*int*/ get_main_frame() {
	return CEF3.callFuncPtr(struct.get_main_frame, address);
}

public int send_process_message(int target_process, long /*int*/ message) {
	return CEF3.callFuncInt(struct.send_process_message,  address, target_process, message);
}
}
