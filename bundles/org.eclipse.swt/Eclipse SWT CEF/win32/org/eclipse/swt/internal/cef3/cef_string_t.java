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


public class cef_string_t {
	/** @field cast=(void *) */
	public long /*int*/ str;
	public long /*int*/ length;
	/** @field cast=(void (__cdecl *)(void *)) */
	public long /*int*/ dtor;
	
	public static final int sizeof = CEF3.cef_string_t_sizeof ();
}
