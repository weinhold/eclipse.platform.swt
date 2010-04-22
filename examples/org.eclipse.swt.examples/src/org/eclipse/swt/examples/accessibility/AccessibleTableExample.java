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

import java.util.*;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

/**
 * This example shows how to use AccessibleTableListener and
 * AccessibleTableCellListener to provide information to an AT.
 * See CTable, CTableColumn, and CTableItem for details.
 */
public class AccessibleTableExample {
	static ResourceBundle resourceBundle = ResourceBundle.getBundle("examples_accessibility"); //$NON-NLS-1$
	static String getResourceString(String key) {
		try {
			return resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			return key;
		} catch (NullPointerException e) {
			return "!" + key + "!"; //$NON-NLS-1$ //$NON-NLS-2$
		}			
	}
	static final String [] itemText = new String [] {
			AccessibleTableExample.getResourceString("color1"),
			AccessibleTableExample.getResourceString("color2"),
			AccessibleTableExample.getResourceString("color3"),
			AccessibleTableExample.getResourceString("color4"),
			AccessibleTableExample.getResourceString("color5"),};

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(2, true));
		shell.setText("Accessible Table Example");
		
		Group group = new Group(shell, SWT.NONE);
		group.setText("Tables With Accessible Cell Children");
		group.setLayout(new GridLayout());

		new Label(group, SWT.NONE).setText("CTable with column headers");
		
		CTable table1 = new CTable(group, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		table1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		table1.setHeaderVisible(true);
		table1.setLinesVisible(true);
		for (int col = 0; col < 3; col++) {
			CTableColumn column = new CTableColumn(table1, SWT.NONE);
			column.setText("Col " + col);
			column.setWidth(50);
		}
		for (int row = 0; row < 4; row++) {
			CTableItem item = new CTableItem(table1, SWT.NONE);
			item.setText(new String [] {"C0R" + row, "C1R" + row, "C2R" + row});
		}
		
		new Label(group, SWT.NONE).setText("CTable used as a list");
		
		CTable table2 = new CTable(group, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		table2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		table2.setLinesVisible(true);
		for (int row = 0; row < itemText.length; row++) {
			CTableItem item = new CTableItem(table2, SWT.NONE);
			item.setText(itemText[row]);
		}
		
		////-------------------------------------------
		
		group = new Group(shell, SWT.NONE);
		group.setText("Tables With Accessible Row Children");
		group.setLayout(new GridLayout());

		new Label(group, SWT.NONE).setText("CTable2 with column headers");
		
		CTable2 table2_1 = new CTable2(group, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		table2_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		table2_1.setHeaderVisible(true);
		table2_1.setLinesVisible(true);
		for (int col = 0; col < 3; col++) {
			CTableColumn2 column = new CTableColumn2(table2_1, SWT.NONE);
			column.setText("Col " + col);
			column.setWidth(50);
		}
		for (int row = 0; row < 4; row++) {
			CTableItem2 item = new CTableItem2(table2_1, SWT.NONE);
			item.setText(new String [] {"C0R" + row, "C1R" + row, "C2R" + row});
		}
		
		new Label(group, SWT.NONE).setText("CTable2 used as a list");
		
		CTable2 table2_2 = new CTable2(group, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		table2_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		table2_2.setLinesVisible(true);
		for (int row = 0; row < itemText.length; row++) {
			CTableItem2 item = new CTableItem2(table2_2, SWT.NONE);
			item.setText(itemText[row]);
		}
		
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}