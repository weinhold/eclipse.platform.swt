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
