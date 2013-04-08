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


import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.cef3.CEFDownloadItemCallback;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class CEFDownload {
	Label statusLabel;
	Shell shell;
	boolean cancelled = false;
	long receivedBytes = 0;

	public CEFDownload(String filename, String url, Browser browser) {
		shell = new Shell(browser.getDisplay(), SWT.DIALOG_TRIM);
		String msg = Compatibility.getMessage("SWT_FileDownload"); //$NON-NLS-1$
		shell.setText(msg);
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginHeight = 15;
		gridLayout.marginWidth = 15;
		gridLayout.verticalSpacing = 20;
		shell.setLayout(gridLayout);

		msg = Compatibility.getMessage("SWT_Download_Location", new Object[] {filename, url}); //$NON-NLS-1$
		Label nameLabel = new Label(shell, SWT.WRAP);
		nameLabel.setText(msg);
		GridData data = new GridData();
		Monitor monitor = browser.getMonitor();
		int maxWidth = monitor.getBounds().width / 2;
		int width = nameLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
		data.widthHint = Math.min(width, maxWidth);
		data.horizontalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		nameLabel.setLayoutData(data);

		statusLabel = new Label(shell, SWT.NONE);
		statusLabel.setText(Compatibility.getMessage("SWT_Download_Started")); //$NON-NLS-1$
		data = new GridData(GridData.FILL_BOTH);
		statusLabel.setLayoutData (data);

		Button cancel = new Button(shell, SWT.PUSH);
		cancel.setText(Compatibility.getMessage("SWT_Cancel")); //$NON-NLS-1$
		data = new GridData();
		data.horizontalAlignment = GridData.CENTER;
		cancel.setLayoutData(data);
		final Listener cancelListener = new Listener () {
			public void handleEvent(Event event) {
				cancelled = true;
			}
		};
		cancel.addListener(SWT.Selection, cancelListener);
		shell.pack();
		shell.open();
	}

	void close() {
		shell.dispose();
		shell = null;
		statusLabel = null;
	}

	boolean updateStatus(long receivedBytes, long totalbytes, boolean completed, boolean inProgress, boolean cancelled, CEFDownloadItemCallback callback) {
		if (completed) {
			close();
			return true;
		}

		if (shell.isDisposed() || this.cancelled || cancelled) {
			this.cancelled = true;
			close();
			callback.cancel();
			return true;
		}

		if (receivedBytes >= this.receivedBytes) {
			this.receivedBytes = receivedBytes;
			String message = Compatibility.getMessage("SWT_Download_Status", new Object[] {new Long(receivedBytes / 1024L), new Long(totalbytes / 1024L)}); //$NON-NLS-1$
			statusLabel.setText(message);
		}
		return false;
	}
}
