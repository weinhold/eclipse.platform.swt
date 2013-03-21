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


public class cef_list_value_t extends cef_base_t {

	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_list_value_t* self)) */
	public long /*int*/ is_valid;

	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_list_value_t* self)) */
	public long /*int*/ is_owned;

	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_list_value_t* self)) */
	public long /*int*/ is_read_only;

	/** @field cast=(struct _cef_list_value_t* (CEF_CALLBACK *)(struct _cef_list_value_t* self)) */
	public long /*int*/ copy;

	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_list_value_t* self, size_t size))" */
	public long /*int*/ set_size;

	/** @field cast=(size_t (CEF_CALLBACK *)(struct _cef_list_value_t* self)) */
	public long /*int*/ get_size;

	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_list_value_t* self)) */
	public long /*int*/ clear;

	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_list_value_t* self, int index))" */
	public long /*int*/ remove;

	/** @field cast="(enum cef_value_type_t (CEF_CALLBACK *)(struct _cef_list_value_t* self, int index))" */
	public long /*int*/ get_type;

	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_list_value_t* self, int index))" */
	public long /*int*/ get_bool;

	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_list_value_t* self, int index))" */
	public long /*int*/ get_int;

	/** @field cast="(double (CEF_CALLBACK *)(struct _cef_list_value_t* self, int index))" */
	public long /*int*/ get_double;

	/** @field cast="(cef_string_userfree_t (CEF_CALLBACK *)(struct _cef_list_value_t* self, int index))" */
	public long /*int*/ get_string;

	/** @field cast="(struct _cef_binary_value_t* (CEF_CALLBACK *)(struct _cef_list_value_t* self, int index))" */
	public long /*int*/ get_binary;

	/** @field cast="(struct _cef_dictionary_value_t* (CEF_CALLBACK *)(struct _cef_list_value_t* self, int index))" */
	public long /*int*/ get_dictionary;

	/** @field cast="(struct _cef_list_value_t* (CEF_CALLBACK *)(struct _cef_list_value_t* self, int index))" */
	public long /*int*/ get_list;

	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_list_value_t* self, int index))" */
	public long /*int*/ set_null;

	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_list_value_t* self, int index, int value))" */
	public long /*int*/ set_bool;

	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_list_value_t* self, int index, int value))" */
	public long /*int*/ set_int;

	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_list_value_t* self, int index, double value))" */
	public long /*int*/ set_double;

	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_list_value_t* self, int index, const cef_string_t* value))" */
	public long /*int*/ set_string;

	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_list_value_t* self, int index, struct _cef_binary_value_t* value))" */
	public long /*int*/ set_binary;

	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_list_value_t* self, int index, struct _cef_dictionary_value_t* value))" */
	public long /*int*/ set_dictionary;

	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_list_value_t* self, int index, struct _cef_list_value_t* value))" */
	public long /*int*/ set_list;

	public static final int sizeof = CEF3.cef_list_value_t_sizeof ();
}
