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


public class cef_download_item_callback_t extends cef_base_t {

	/** @field cast=(void (CEF_CALLBACK *)(struct _cef_download_item_callback_t* self)) */
	public long /*int*/ cancel;

	public static final int sizeof = CEF3.cef_download_item_callback_t_sizeof ();
}
