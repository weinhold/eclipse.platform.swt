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


public class CEFListValue extends CEFBase {
	cef_list_value_t struct;

public CEFListValue(long /*int*/ address) {
	super(address);
	struct = new cef_list_value_t();
	CEF3.memmove(struct, address, cef_list_value_t.sizeof);
}

public long /*int*/ get_string(int index) {
	return CEF3.callFuncPtr(struct.get_string, address, index);
}

public int set_bool(int index, int value) {
	return CEF3.callFuncInt(struct.set_bool, address, index, value);
}

public int set_string(int index, cef_string_t value) {
	return CEF3.callFuncInt(struct.set_string, address, index, value);
}

}
