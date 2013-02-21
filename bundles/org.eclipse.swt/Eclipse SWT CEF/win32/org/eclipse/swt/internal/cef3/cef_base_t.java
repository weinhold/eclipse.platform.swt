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


public class cef_base_t {
	public long /*int*/ size;

	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_base_t* self)) */
	public long /*int*/ add_ref;

	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_base_t* self)) */
	public long /*int*/ release;

	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_base_t* self)) */
	public long /*int*/ get_refct;

	public static final int sizeof = CEF3.cef_base_t_sizeof ();
}
