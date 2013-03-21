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


public class CEFProcessMessage extends CEFBase {
	cef_process_message_t struct;

public CEFProcessMessage(long /*int*/ address) {
	super(address);
	struct = new cef_process_message_t();
	CEF3.memmove(struct, address, cef_process_message_t.sizeof);
}

public long /*int*/ get_name() {
	return CEF3.callFuncPtr(struct.get_name, address);
}

public long /*int*/ get_argument_list() {
	return CEF3.callFuncPtr(struct.get_argument_list, address);
}

}
