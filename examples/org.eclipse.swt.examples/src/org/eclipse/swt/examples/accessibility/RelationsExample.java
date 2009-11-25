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
import org.eclipse.swt.accessibility.*;
import org.eclipse.swt.events.*;

/**
 * This example shows how to use accessible relations to provide
 * additional information to an AT.
 */
public class RelationsExample {
	static final String canvasLabelText = "Fruit:";
		
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(2, false));
		shell.setText("Accessible Relations");

		Canvas canvas = new Canvas(shell, SWT.NONE);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				e.gc.drawText(canvasLabelText, 10, 8);
			}
		});
		canvas.setLayoutData(new GridData(50, 30));
		
		List list = new List(shell, SWT.BORDER);
		list.setItems(new String[] {"Apples", "Bananas", "Oranges"});
		
		Accessible accCanvas = canvas.getAccessible();
		accCanvas.addAccessibleListener(new AccessibleAdapter() {
			public void getName(AccessibleEvent e) {
				e.result = canvasLabelText;
			}
		});
		Accessible accList = list.getAccessible();
		accCanvas.addRelation(ACC.RELATION_LABEL_FOR, accList);
		accList.addRelation(ACC.RELATION_LABELLED_BY, accCanvas);
		
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}