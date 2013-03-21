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


public class cef_process_message_t extends cef_base_t {

	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_process_message_t* self)) */
	public long /*int*/ is_valid;

	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_process_message_t* self)) */
	public long /*int*/ is_read_only;

	/** @field cast=(struct _cef_process_message_t* (CEF_CALLBACK *)(struct _cef_process_message_t* self)) */
	public long /*int*/ copy;

	/** @field cast=(cef_string_userfree_t (CEF_CALLBACK *)(struct _cef_process_message_t* self)) */
	public long /*int*/ get_name;

	/** @field cast=(struct _cef_list_value_t* (CEF_CALLBACK *)(struct _cef_process_message_t* self)) */
	public long /*int*/ get_argument_list;

	public static final int sizeof = CEF3.cef_process_message_t_sizeof ();
}
