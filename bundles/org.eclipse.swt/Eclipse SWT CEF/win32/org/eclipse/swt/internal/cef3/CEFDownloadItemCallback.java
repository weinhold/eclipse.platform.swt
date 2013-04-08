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


public class CEFDownloadItemCallback extends CEFBase {
	cef_download_item_callback_t struct;

	public CEFDownloadItemCallback(long /*int*/ address) {
		super(address);
		struct = new cef_download_item_callback_t();
		CEF3.memmove(struct, address, cef_download_item_callback_t.sizeof);
	}

	public void cancel() {
		CEF3.callFuncVoid(struct.cancel, address);
	}
}
