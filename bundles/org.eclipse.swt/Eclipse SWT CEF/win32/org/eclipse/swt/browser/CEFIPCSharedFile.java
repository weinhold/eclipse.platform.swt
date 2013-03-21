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
package org.eclipse.swt.browser;


import java.io.UnsupportedEncodingException;
import org.eclipse.swt.internal.win32.*;

public class CEFIPCSharedFile {
	int hSharedFile;
	static final int SHARED_MEMORY_MAXSIZE = 0x7FFFFFFF;

public CEFIPCSharedFile(String args) {
	super();
	hSharedFile = OS.OpenFileMapping(OS.FILE_MAP_ALL_ACCESS, 0, new TCHAR(0, args, true));
}

void dispose() {
	if (hSharedFile != 0) {
		OS.CloseHandle(hSharedFile);
		hSharedFile = 0;
	}
}

boolean sendResponse(String message) {
	return sendResponse(message, SHARED_MEMORY_MAXSIZE);
}

boolean sendResponse(String message, int maxLength) {
	if (hSharedFile == 0) return false;

	byte[] bytes = null;
	try {
		bytes = (message + '\0').getBytes("UTF-8"); //$NON-NLS-1$
	} catch (UnsupportedEncodingException e) {
		bytes = (message + '\0').getBytes();
	}

	maxLength = Math.min(maxLength, SHARED_MEMORY_MAXSIZE);
	if (bytes.length > maxLength) {
		byte[] temp = new byte[maxLength];
		System.arraycopy(bytes, 0, temp, 0, maxLength);
		bytes = temp;
	}

	long /*int*/ pMemory = OS.MapViewOfFile(hSharedFile, OS.FILE_MAP_WRITE, 0, 0, bytes.length);
	if (pMemory == 0) {
		return false;
	}
	OS.CopyMemory(pMemory, bytes, bytes.length);
	OS.UnmapViewOfFile(pMemory);
	return true;
}

}
