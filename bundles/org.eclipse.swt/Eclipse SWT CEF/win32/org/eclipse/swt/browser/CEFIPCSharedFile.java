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


import org.eclipse.swt.internal.cef3.*;
import org.eclipse.swt.internal.win32.*;

class CEFIPCSharedFile {
	int hSharedFile;
	CEFBrowser cefBrowser;

	static final int SHARED_MEMORY_MAXSIZE = 0xFFFE;
	static final int SLEEP_INTERVAL = 50;

public CEFIPCSharedFile(CEFBrowser cefBrowser, String args) {
	super();
	hSharedFile = OS.OpenFileMapping(OS.FILE_MAP_ALL_ACCESS, 0, new TCHAR(0, args, true));
	if (hSharedFile != 0) {
		this.cefBrowser = cefBrowser;
		cefBrowser.add_ref();
	}
}

void dispose() {
	if (hSharedFile != 0) {
		OS.CloseHandle(hSharedFile);
		hSharedFile = 0;
		cefBrowser.release();
		cefBrowser = null;
	}
}

boolean sendMessage(CEFProcessMessage cefMessage, int timeout, String[] response) {
	long /*int*/ pMemory = 0;

	if (response != null) {
		/* clear the ipc file for the anticipated response */
		if (hSharedFile == 0) return false; /* response will not be receivable */
		pMemory = OS.MapViewOfFile(hSharedFile, OS.FILE_MAP_WRITE, 0, 0, SHARED_MEMORY_MAXSIZE);
		if (pMemory == 0) return false; /* response will not be receivable */
		OS.CopyMemory(pMemory, new byte[SHARED_MEMORY_MAXSIZE], SHARED_MEMORY_MAXSIZE);
	}

	/* send the message, and wait for the response if required */
	
	boolean result = false;
	cefMessage.add_ref();
	if (cefBrowser.send_process_message(CEF3.PID_RENDERER, cefMessage.getAddress()) != 0) {
		if (response == null) {
			return true; /* done, no response expected, and no pMemory to unmap */
		}
		char[] buffer = new char[SHARED_MEMORY_MAXSIZE / 2];
		int total = 0;
		while (total <= timeout) {
			OS.CopyMemory(buffer, pMemory, SHARED_MEMORY_MAXSIZE);
			if (buffer[0] != 0) break;
			OS.Sleep(SLEEP_INTERVAL); // TODO
			total += SLEEP_INTERVAL;
		}
		if (total <= timeout) {
			int index = -1;
			while (buffer[++index] != '\0' && index < buffer.length); /* find end of string */
			response[0] = new String(buffer, 0, index);
			result = true;
		}
	}

	if (pMemory != 0) {
		OS.UnmapViewOfFile(pMemory);
	}
	return result;
}

boolean sendResponse(String responseString) {
	if (hSharedFile == 0) return false;

	/* ensure that the response string + \0 will fit in the response file */
	byte[] bytes = (responseString + '\0').getBytes();
	int length = Math.min(bytes.length, SHARED_MEMORY_MAXSIZE);
	long /*int*/ pMemory = OS.MapViewOfFile(hSharedFile, OS.FILE_MAP_WRITE, 0, 0, length);
	if (pMemory == 0) return false;
	OS.CopyMemory(pMemory, bytes, length);
	OS.UnmapViewOfFile(pMemory);
	return true;
}

}
