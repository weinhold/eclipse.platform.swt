package org.eclipse.swt.internal.cef3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class CEFDownload {
	Label statusLabel;
	Button cancel;
	Shell shell;
	boolean cancelled = false;
	long receivedBytes = 0;

	public CEFDownload(String filename, String url, Browser browser) {
		shell = new Shell();
		String msg = Compatibility.getMessage("SWT_FileDownload"); //$NON-NLS-1$
		shell.setText (msg);
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginHeight = 15;
		gridLayout.marginWidth = 15;
		gridLayout.verticalSpacing = 20;
		shell.setLayout(gridLayout);

		msg = Compatibility.getMessage("SWT_Download_Location", new Object[] {filename, url}); //$NON-NLS-1$
		Label nameLabel = new Label(shell, SWT.WRAP);
		nameLabel.setText (msg);
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

		cancel = new Button(shell, SWT.PUSH);
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
	
	private void close() {
		cancel = null;
		statusLabel = null;
		shell.dispose();
		shell = null;
	}
	
	public synchronized boolean updateStatus(long receivedBytes, long totalbytes, int completed, int inProgress, int cancelled, long /*int*/ pCallback){
		if (receivedBytes >= this.receivedBytes) {
			this.receivedBytes = receivedBytes;
			if (this.cancelled || cancelled == 1) {
				this.cancelled = true;
				CEFDownloadItemCallback callback = new CEFDownloadItemCallback(pCallback);
				close();
				callback.cancel();
				callback.release();
				return true;
			}
			// If I understand correctly it is possible for a download to complete before you cancel it.
			// Thus we should only consider the download complete if it is in progress.
			if (inProgress == 1 && completed == 1) {
				close();
				new CEFBase(pCallback).release();
				return true;
			}
			
			String message = Compatibility.getMessage ("SWT_Download_Status", new Object[] {new Long(receivedBytes / 1024L), new Long(totalbytes / 1024L)}); //$NON-NLS-1$
			statusLabel.setText (message);
			new CEFBase(pCallback).release();
		}
		return false;
	}
}
