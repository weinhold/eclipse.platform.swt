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
import org.eclipse.swt.accessibility.*;

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

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());
		shell.setText("Accessible Table");
		
		Label label1 = new Label(shell, SWT.NONE);
		label1.setText("Custom Table with column headers");
		
		CTable table1 = new CTable(shell, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
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
		
		final Accessible table1Accessible = table1.getAccessible();
		final Accessible label1Accessible = label1.getAccessible();
		label1Accessible.addRelation(ACC.RELATION_LABEL_FOR, table1Accessible);
		table1Accessible.addRelation(ACC.RELATION_LABELLED_BY, label1Accessible);
		table1Accessible.addAccessibleListener(new AccessibleAdapter() {
			public void getName(AccessibleEvent e) {
				if (e.childID == ACC.CHILDID_SELF) {
					e.result = "Custom Table with column headers";
				}
			}
		});
		
		Label label2 = new Label(shell, SWT.NONE);
		label2.setText("Custom Table used as a list");
		
		CTable table2 = new CTable(shell, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		table2.setLinesVisible(true);
		String [] itemText = new String [] {
				AccessibleTableExample.getResourceString("color1"),
				AccessibleTableExample.getResourceString("color2"),
				AccessibleTableExample.getResourceString("color3"),
				AccessibleTableExample.getResourceString("color4"),
				AccessibleTableExample.getResourceString("color5"),};
		for (int row = 0; row < itemText.length; row++) {
			CTableItem item = new CTableItem(table2, SWT.NONE);
			item.setText(itemText[row]);
		}
		
		final Accessible table2Accessible = table2.getAccessible();
		final Accessible label2Accessible = label2.getAccessible();
		label2Accessible.addRelation(ACC.RELATION_LABEL_FOR, table2Accessible);
		table2Accessible.addRelation(ACC.RELATION_LABELLED_BY, label2Accessible);
		table2Accessible.addAccessibleListener(new AccessibleAdapter() {
			public void getName(AccessibleEvent e) {
				if (e.childID == ACC.CHILDID_SELF) {
					e.result = "Custom Table used as a list";
				}
			}
		});
		
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}