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

import org.eclipse.swt.internal.cef3.CEF3;
import org.eclipse.swt.internal.cef3.CEFBase;
import org.eclipse.swt.internal.cef3.cef_jsdialog_callback_t;
import org.eclipse.swt.internal.cef3.cef_string_t;

public class CEFJSDialogCallback extends CEFBase {
	cef_jsdialog_callback_t struct;

	public CEFJSDialogCallback(long /*int*/ address) {
		super(address);
		struct = new cef_jsdialog_callback_t();
		CEF3.memmove(struct, address, cef_jsdialog_callback_t.sizeof);
	}
	
	public void cont(int success, cef_string_t userInput) {
		CEF3.callFuncVoid(struct.cont, address, success, userInput);
	}

}
