/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.examples.accessibility;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.accessibility.*;

/**
 * This example shows how to use an AccessibleActionListener to provide
 * additional information to an AT.
 */
public class AccessibleActionExample {
	public static void main(String[] args) {
		Display.DEBUG = true;
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());
		shell.setText("Accessible Action");
		
		final Canvas customButton = new Canvas(shell, SWT.NONE);
		customButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		customButton.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				String buttonText = "Hello Button";
				Rectangle clientArea = customButton.getClientArea();
				Point stringExtent = e.gc.stringExtent(buttonText);
				int x = clientArea.x + (clientArea.width - stringExtent.x) / 2;
				int y = clientArea.y + (clientArea.height - stringExtent.y) / 2;
				e.gc.drawString(buttonText, x, y);
			}
		});
		customButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				customButtonAction();
			}
		});

		Accessible accessible = customButton.getAccessible();
		accessible.addAccessibleListener(new AccessibleAdapter() {
			public void getKeyboardShortcut(AccessibleEvent e) {
				e.result = "ALT+P"; 
			}
		});
		accessible.addAccessibleControlListener(new AccessibleControlAdapter() {
			public void getRole(AccessibleControlEvent e) {
				e.detail = ACC.ROLE_PUSHBUTTON;
			}
		});
		accessible.addAccessibleActionListener(new AccessibleActionAdapter() {
			public void getActionCount(AccessibleActionEvent e) {
				e.count = 1;
			}
			public void getName(AccessibleActionEvent e) {
				e.result = "Press";
			}
			public void getDescription(AccessibleActionEvent e) {
				e.result = "Press custom button";
			}
			public void doAction(AccessibleActionEvent e) {
				System.out.println("Performing action " + e.index + " for " + customButton);
				customButtonAction();
				e.result = ACC.OK;
			}
			public void getKeyBinding(AccessibleActionEvent e) {
				e.result = "P;CTRL+P";
			}
		});

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
	
	static void customButtonAction() {
		System.out.println("custom button click");
	}
}