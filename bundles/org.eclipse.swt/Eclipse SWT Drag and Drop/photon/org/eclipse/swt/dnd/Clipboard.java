package org.eclipse.swt.dnd;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.photon.*;
import org.eclipse.swt.widgets.*;

/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved
 */

/*
 *
 * IMPORTANT: This class is <em>not</em> intended to be subclassed.
 *
 */ 
public class Clipboard {
	
	private Display display;
	private final int MAX_RETRIES = 10;


public Clipboard(Display display) {	
	checkSubclass ();
	if (display == null) {
		display = Display.getCurrent();
		if (display == null) {
			display =  Display.getDefault();
		}
	}
	if (display.getThread() != Thread.currentThread()) {
		SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
	}
	this.display = display;
}
protected void checkSubclass () {
	String name = getClass().getName ();
	String validName = Clipboard.class.getName();
	if (!validName.equals(name)) {
		DND.error (SWT.ERROR_INVALID_SUBCLASS);
	}
}
public void dispose () {
	display = null;
}
public Object getContents(Transfer transfer) {
	if (display.isDisposed() ) return null;
	int ig = OS.PhInputGroup(0);
	int cbdata = OS.PhClipboardPasteStart((short)ig);
	Object result = null;
	try {
		String[] types = transfer.getTypeNames();
		for (int i = 0; i < types.length; i++) {
			byte[] type = new byte[8];
			byte[] temp = types[i].getBytes();
			System.arraycopy(temp, 0, type, 0, Math.min(type.length, temp.length));
			int pClipHeader = OS.PhClipboardPasteType(cbdata, type);
			if (pClipHeader != 0) {
				PhClipHeader clipHeader = new PhClipHeader();
				OS.memmove(clipHeader, pClipHeader, PhClipHeader.sizeof);
				byte[] buffer = new byte[clipHeader.length];
				OS.memmove(buffer, clipHeader.data, buffer.length);
				OS.free(clipHeader.data);
				OS.free(pClipHeader);
				result = buffer;
				break;
			}
		}
	} finally {
		OS.PhClipboardPasteFinish(cbdata);
	}
	return result;
}
public void setContents(Object[] data, Transfer[] transferAgents){

	if (data == null) {
		DND.error(SWT.ERROR_NOT_IMPLEMENTED);
	}
	if (transferAgents == null || data.length != transferAgents.length) {
		DND.error(SWT.ERROR_INVALID_ARGUMENT);
	}
	
	// copy data directly over to System clipboard (not deferred)
	PhClipHeader[] clip = new PhClipHeader[0];
	for (int i = 0; i < transferAgents.length; i++) {
		String[] names = transferAgents[i].getTypeNames();
		PhClipHeader[] tempClip = new PhClipHeader[names.length];
		for (int j = 0; j < names.length; j++) {
			tempClip[j] = new PhClipHeader();
			byte[] type = new byte[8];
			byte[] name = names[j].getBytes();
			System.arraycopy(name, 0, type, 0, Math.min(type.length, name.length));
			tempClip[j].type_0 = type[0];
			tempClip[j].type_1 = type[1];
			tempClip[j].type_2 = type[2];
			tempClip[j].type_3 = type[3];
			tempClip[j].type_4 = type[4];
			tempClip[j].type_5 = type[5];
			tempClip[j].type_6 = type[6];
			tempClip[j].type_7 = type[7];
			TransferData transferData = new TransferData();
			transferAgents[i].javaToNative(data[i], transferData);
			tempClip[j].data = transferData.pData;
			tempClip[j].length = (short)transferData.length;
		}
		PhClipHeader[] newClip = new PhClipHeader[clip.length + tempClip.length];
		System.arraycopy(clip, 0, newClip, 0, clip.length);
		System.arraycopy(tempClip, 0, newClip, clip.length, tempClip.length);
		clip = newClip;
	}
	
	int pClip = OS.malloc(clip.length * PhClipHeader.sizeof);
	int status = -1;
	try {
		int offset = 0;
		for (int i = 0; i < clip.length; i++) {
			OS.memmove(clip[i], pClip + offset, PhClipHeader.sizeof);
			offset += PhClipHeader.sizeof;
		}
	
		int ig = OS.PhInputGroup(0);
		status = OS.PhClipboardCopy((short)ig, clip.length, pClip);
	} finally {
		OS.free(pClip);
	}
	
	for (int i = 0; i < clip.length; i++) {
		int pData = clip[i].data;
		if (pData != 0)	OS.free(pData);
	}
	
	if (status != 0) 
		DND.error(DND.ERROR_CANNOT_SET_CLIPBOARD);
}
/*
 * Note: getAvailableTypeNames is a tool for writing a Transfer sub-class only.  It should
 * NOT be used within an application because it provides platform specfic 
 * information.
 */
public String[] getAvailableTypeNames() {
	int[] count = new int[1];
	int[] max_length = new int[1];
//	int xDisplay = OS.XtDisplay (shellHandle);
//	if (xDisplay == 0)
//		DND.error(SWT.ERROR_UNSPECIFIED);
//	int xWindow = OS.XtWindow (shellHandle);
//	if (xWindow == 0)
//		DND.error(SWT.ERROR_UNSPECIFIED);
//	if (OS.XmClipboardInquireCount(xDisplay, xWindow, count, max_length) != OS.XmClipboardSuccess)
//		DND.error(SWT.ERROR_UNSPECIFIED);
	String[] types = new String[count[0]];
//	for (int i = 0; i < count[0]; i++) {
//		byte[] buffer = new byte[max_length[0]];
//		int[] copied_length = new int[1];
//		int rc = OS.XmClipboardInquireFormat(xDisplay, xWindow, i + 1, buffer, buffer.length, copied_length);
//		if (rc == OS.XmClipboardNoData){
//			types[i] = "";
//			continue;
//		}
//		if (rc != OS.XmClipboardSuccess)
//			DND.error(SWT.ERROR_UNSPECIFIED);
//		byte[] buffer2 = new byte[copied_length[0]];
//		System.arraycopy(buffer, 0, buffer2, 0, copied_length[0]);
//		types[i] = new String(buffer2);
//	}
	return types;
}
}
