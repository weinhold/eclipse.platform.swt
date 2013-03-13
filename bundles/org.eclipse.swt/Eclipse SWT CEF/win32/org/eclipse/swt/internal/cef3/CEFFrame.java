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


public class CEFFrame extends CEFBase {
	cef_frame_t struct;

public CEFFrame(long /*int*/ address) {
	super(address);
	struct = new cef_frame_t();
	CEF3.memmove(struct, address, cef_frame_t.sizeof);
}

public void load_url(long /*int*/ url) {
	CEF3.callFuncVoid(struct.load_url, address, url);
}

public long /*int*/ get_url() {
	return CEF3.callFuncPtr(struct.get_url, address);
}

public boolean is_main() {
	return CEF3.callFuncInt(struct.is_main, address) != 0;
}

public void execute_java_script(long /*int*/ code, long /*int*/ script_url, int start_line) {
	CEF3.callFuncVoid(struct.execute_java_script, address, code, script_url, start_line);
}

}
