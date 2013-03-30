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

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.internal.cef3.*;

public class CEFJSDialogHandler {
	private static final int JSDIALOGTYPE_ALERT = 0;
	private static final int JSDIALOGTYPE_CONFIRM = 1;
	private static final int JSDIALOGTYPE_PROMPT = 2;
	CEF3Object object;
	CEF host;
	int refCount = 1;
	
public CEFJSDialogHandler(CEF host) {
	this.host = host;
	object = new CEF3Object (new int[] {0, 0, 0, 8, 4, 1}) {
		public long /*int*/ method0(long /*int*/[] args) {return add_ref();}
		public long /*int*/ method1(long /*int*/[] args) {return CEFJSDialogHandler.this.release();}
		public long /*int*/ method2(long /*int*/[] args) {return get_refct();}
		public long /*int*/ method3(long /*int*/[] args) {return on_jsdialog(args[0], args[1], args[2], (int)/*64*/ args[3], args[4], args[5], args[6],(int)/*64*/args[7]);}
		public long /*int*/ method4(long /*int*/[] args) {return on_before_unload_dialog(args[0], args[1],(int)/*64*/args[2], args[3]);}
		public long /*int*/ method5(long /*int*/[] args) {return on_reset_dialog_state(args[0]);}
	};
}

long /*int*/ getAddress () {
	return object.getAddress ();
}

/* cef_base_t */

synchronized int add_ref() {
	refCount++;
	return refCount;
}

synchronized int get_refct() {
	return refCount;
}

synchronized int release() {
	if (--refCount == 0) {
		if (Device.DEBUG) System.out.println("release: CEFJSDialogHandler");
		if (object != null) {
			object.dispose ();
		}
		object = null;
	}
	return refCount;
}

long /*int*/ on_jsdialog(long /*int*/ browser, long /*int*/ originUrl, long /*int*/ acceptLang, int dialogType, long /*int*/ strMessageText, long /*int*/ strDefaultPromptText, long /*int*/ pCallback, int suppressMessage) {
	if (Device.DEBUG) System.out.println("on_jsdialog (impl)");
	final String message = CEF.ExtractCEFString(strMessageText);
	final CEFJSDialogCallback callback = new CEFJSDialogCallback(pCallback);
	if (dialogType == JSDIALOGTYPE_ALERT) {
		host.browser.getDisplay().asyncExec(new Runnable() {
			public void run() {
				if (host.browser.isDisposed()) return;
				host.showAlertMessage("Javascript", message, callback); //$NON-NLS-1$
			}
		});
	}
	else if (dialogType == JSDIALOGTYPE_CONFIRM) {
		host.browser.getDisplay().asyncExec(new Runnable() {
			public void run() {
				if (host.browser.isDisposed()) return;
				host.showConfirmPanel("Javascript", message, callback); //$NON-NLS-1$
			}
		});
	}
	else if (dialogType == JSDIALOGTYPE_PROMPT) {
		final String defaultPromptText = CEF.ExtractCEFString(strDefaultPromptText);
		host.browser.getDisplay().asyncExec(new Runnable() {
			public void run() {
				if (host.browser.isDisposed()) return;
				host.showTextPrompter("Javascript",message, defaultPromptText, callback); //$NON-NLS-1$
			}
		});
	}
	else {
		new CEFBase(browser).release();
		return 0;
	}
	new CEFBase(browser).release();
	return 1;
}


long /*int*/ on_before_unload_dialog(long /*int*/ browser, long /*int*/ strMessageText, int isReload, long /*int*/ pCallback) {
	if (Device.DEBUG) System.out.println("on_before_unload_dialog (impl)");
	final String message = CEF.ExtractCEFString(strMessageText);
	final CEFJSDialogCallback callback = new CEFJSDialogCallback(pCallback);
	host.browser.getDisplay().asyncExec(new Runnable() {
		public void run() {
			if (host.browser.isDisposed()) return;
			host.showBeforeUnloadConfirmPanel(message, callback);
		}
	});
	new CEFBase(browser).release();
	return 1;
}

long /*int*/ on_reset_dialog_state(long /*int*/ browser) {
	if (Device.DEBUG) System.out.println("on_reset_dialog_state (TODO)");
	new CEFBase(browser).release();
	return 0;
}

}
