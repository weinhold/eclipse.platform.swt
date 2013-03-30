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


public class cef_jsdialog_callback_t extends cef_base_t {

	/** @field cast="(void (CEF_CALLBACK *)(struct _cef_jsdialog_callback_t* self, int success, const cef_string_t* user_input))" */
	public long /*int*/ cont;
	
	public static final int sizeof = CEF3.cef_jsdialog_callback_t_sizeof();
}
