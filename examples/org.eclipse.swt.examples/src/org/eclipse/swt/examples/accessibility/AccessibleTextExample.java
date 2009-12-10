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
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.accessibility.*;

/**
 * This example shows how to use an AccessibleTextListener to provide
 * additional information to an AT.
 */
public class AccessibleTextExample {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());
		shell.setText("Accessible Text");
		
		final Text text = new Text(shell, SWT.MULTI | SWT.BORDER);
		text.setText("This is a\nmulti\nline\ntext.\nThere is only one selection.\nThere is only one font.\nThere are no hyperlinks.");
		text.setSelection(45, 48);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Button button = new Button(shell, SWT.NONE);
		button.setText("  OK  ");
		button.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false));
		
		Accessible accessibleText = text.getAccessible();
		accessibleText.addAccessibleListener(new AccessibleAdapter() {
			public void getName(AccessibleEvent e) {
				e.result = "This is the text's new accessible name";
			}
		});
		accessibleText.addAccessibleTextExtendedListener(new AccessibleTextExtendedListener() {
			public void addSelection(AccessibleTextExtendedEvent e) {
				System.out.print("addSelection " + e);
				text.setSelection(e.start, e.end);
				System.out.println(" returning " + e);
			}
			public void getCaretOffset(AccessibleTextEvent e) {
				System.out.print("getCaretOffset " + e);
				e.offset = text.getCaretPosition();
				System.out.println(" returning " + e);
			}
			public void getCharacterCount(AccessibleTextExtendedEvent e) {
				System.out.print("getCharacterCount " + e);
				e.count = text.getCharCount();
				System.out.println(" returning " + e);
			}
			public void getHyperlink(AccessibleTextExtendedEvent e) {
				System.out.print("getHyperlink " + e);
				System.out.println(" returning " + e);
			}
			public void getHyperlinkCount(AccessibleTextExtendedEvent e) {
				System.out.print("getHyperlinkCount " + e);
				e.count = 0;
				System.out.println(" returning " + e);
			}
			public void getHyperlinkIndex(AccessibleTextExtendedEvent e) {
				System.out.print("getHyperlinkIndex " + e);
				e.index = -1;
				System.out.println(" returning " + e);
			}
			public void getOffsetAtPoint(AccessibleTextExtendedEvent e) {
				System.out.print("getOffsetAtPoint " + e);
				//TODO: maybe can emulate this with getTopIndex, getTopPixel, getLineHeight, getOrientation, etc.
				System.out.println(" returning " + e);
			}
			public void getRanges(AccessibleTextExtendedEvent e) {
				System.out.print("getRanges " + e);
				//TODO: maybe can emulate this with getTopIndex, getTopPixel, getLineHeight, getOrientation, etc.
				System.out.println(" returning " + e);
			}
			public void getSelection(AccessibleTextExtendedEvent e) {
				System.out.print("getSelection " + e);
				if (e.index == 0) {
					Point sel = text.getSelection();
					e.ranges = new int [] {sel.x, sel.y};
				}
				System.out.println(" returning " + e);
			}
			public void getSelectionCount(AccessibleTextExtendedEvent e) {
				e.count = text.getSelectionCount();
			}
			public void getSelectionRange(AccessibleTextEvent e) {
				System.out.print("getSelectionRange " + e);
				Point sel = text.getSelection();
				e.offset = sel.x;
				e.length = sel.y - sel.x;
				System.out.println(" returning " + e);
			}
			public void getText(AccessibleTextExtendedEvent e) {
				System.out.print("getText " + e);
				// TODO: need to implement this
				e.result = text.getText(e.start, e.end);
				System.out.println(" returning " + e);
			}
			public void getTextBounds(AccessibleTextExtendedEvent e) {
				System.out.print("getTextBounds " + e);
				// TODO: Can probably emulate this
				System.out.println(" returning " + e);
			}
			public void removeSelection(AccessibleTextExtendedEvent e) {
				System.out.print("removeSelection " + e);
				if (e.index == 0) text.setSelection(text.getCaretPosition());
				System.out.println(" returning " + e);
			}
			public void scrollText(AccessibleTextExtendedEvent e) {
				System.out.print("scrollText " + e);
				// TODO: try to implement this for other scroll types
				if (e.type == ACC.SCROLL_TYPE_TOP_LEFT) {
					text.setTopIndex(e.start);
				}
				System.out.println(" returning " + e);
			}
			public void setCaretOffset(AccessibleTextExtendedEvent e) {
				System.out.print("setCaretOffset " + e);
				text.setSelection(e.offset);
				System.out.println(" returning " + e);
			}
			public void setSelection(AccessibleTextExtendedEvent e) {
				System.out.print("setSelection " + e);
				if (e.index == 0) {
					text.setSelection(e.start, e.end);
				}
				System.out.println(" returning " + e);
			}
			public void getVisibleRanges(AccessibleTextExtendedEvent e) {
				// TODO 
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