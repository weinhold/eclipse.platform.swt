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


public class cef_before_download_callback_t extends cef_base_t {

	/** @field cast="(void (CEF_CALLBACK *)(struct _cef_before_download_callback_t* self, const cef_string_t* download_path, int show_dialog))" */
	public long /*int*/ cont;

	public static final int sizeof = CEF3.cef_before_download_callback_t_sizeof ();
}
