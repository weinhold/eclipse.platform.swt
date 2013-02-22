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


public class CEFBase {
	cef_base_t struct;
	long /*int*/ address;

public CEFBase(long /*int*/ address) {
	super();
	this.address = address;
	struct = new cef_base_t();
	CEF3.memmove(struct, address, cef_base_t.sizeof);
}

public long /*int*/ getAddress() {
	return this.address;
}

public int add_ref() {
	return CEF3.callFuncInt(struct.add_ref, address);
}

public int release() {
	return CEF3.callFuncInt(struct.release, address);
}

public int get_refct() {
	return CEF3.callFuncInt(struct.get_refct, address);
}
	
}
