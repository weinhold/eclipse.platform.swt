package org.eclipse.swt.internal.cef3;

import org.eclipse.swt.browser.CEF;

public class CEFBeforeDownloadCallback extends CEFBase {
	cef_before_download_callback_t struct;
	
	public CEFBeforeDownloadCallback(long /*int*/ address) {
		super(address);
		struct = new cef_before_download_callback_t();
		CEF3.memmove(struct, address, cef_before_download_callback_t.sizeof);
	}

	public void cont() {
		CEF3.callFuncVoid(struct.cont, address,CEF.CEFSTRING_EMPTY,1);
	}
}
