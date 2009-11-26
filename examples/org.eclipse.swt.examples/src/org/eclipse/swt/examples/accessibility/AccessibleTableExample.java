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

/**
 * This example shows how to use AccessibleTableListener and
 * AccessibleTableCellListener to provide information to an AT.
 * See CTable, CTableColumn, and CTableItem for details.
 */
public class AccessibleTableExample {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());
		shell.setText("Accessible Table");
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("Label for Custom Table");
		
		CTable table = new CTable(shell, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		for (int col = 0; col < 3; col++) {
			CTableColumn column = new CTableColumn(table, SWT.NONE);
			column.setText("Col " + col);
			column.setWidth(50);
		}
		for (int row = 0; row < 3; row++) {
			CTableItem item = new CTableItem(table, SWT.NONE);
			item.setText(new String [] {"C0R" + row, "C1R" + row, "C2R" + row});
		}
		
		final Accessible accessibleTable = table.getAccessible();
		final Accessible accessibleLabel = label.getAccessible();
		accessibleLabel.addRelation(ACC.RELATION_LABEL_FOR, accessibleTable);
		accessibleTable.addRelation(ACC.RELATION_LABELLED_BY, accessibleLabel);
		
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}