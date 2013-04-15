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


public class CEFMenuModel extends CEFBase {
	cef_menu_model_t struct;

public CEFMenuModel(long /*int*/ address) {
	super(address);
	struct = new cef_menu_model_t();
	CEF3.memmove(struct, address, cef_menu_model_t.sizeof);
}

public int clear() {
	return CEF3.callFuncInt(struct.clear, address);
}

}
