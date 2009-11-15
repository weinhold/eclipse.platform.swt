package org.eclipse.swt.examples.accessibility;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.accessibility.*;

public class AccessibleTextExample {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());
		shell.setText("Accessible Text");
		
		Text text = new Text(shell, SWT.MULTI | SWT.BORDER);
		text.setText("This is a multi\nline\ntext.");
		
		Accessible accessibleText = text.getAccessible();
		accessibleText.addAccessibleListener(new AccessibleAdapter() {
			public void getName(AccessibleEvent e) {
				e.result = "This is the text's new accessible name";
			}
		});
		accessibleText.addAccessibleTextListener(new AccessibleTextExtendedListener() {
			public void addSelection(AccessibleTextExtendedEvent e) {
				System.out.println("addSelection " + e);
			}
			public void getCaretOffset(AccessibleTextEvent e) {
				System.out.println("getCaretOffset " + e);
			}
			public void getCharacterCount(AccessibleTextExtendedEvent e) {
				System.out.println("getCharacterCount " + e);
			}
			public void getHyperlink(AccessibleTextExtendedEvent e) {
				System.out.println("getHyperlink " + e);
			}
			public void getHyperlinkCount(AccessibleTextExtendedEvent e) {
				System.out.println("getHyperlinkCount " + e);
			}
			public void getHyperlinkIndex(AccessibleTextExtendedEvent e) {
				System.out.println("getHyperlinkIndex " + e);
			}
			public void getOffsetAtPoint(AccessibleTextExtendedEvent e) {
				System.out.println("getOffsetAtPoint " + e);
			}
			public void getSelection(AccessibleTextExtendedEvent e) {
				System.out.println("getSelection " + e);
			}
			public void getSelectionCount(AccessibleTextExtendedEvent e) {
				System.out.println("getSelectionCount " + e);
			}
			public void getSelectionRange(AccessibleTextEvent e) {
				System.out.println("getSelectionRange " + e);
			}
			public void getText(AccessibleTextExtendedEvent e) {
				System.out.println("getText " + e);
			}
			public void getTextBounds(AccessibleTextExtendedEvent e) {
				System.out.println("getTextBounds " + e);
			}
			public void getTextRange(AccessibleTextExtendedEvent e) {
				System.out.println("getTextRange " + e);
			}
			public void removeSelection(AccessibleTextExtendedEvent e) {
				System.out.println("removeSelection " + e);
			}
			public void scrollTextTo(AccessibleTextExtendedEvent e) {
				System.out.println("scrollTextTo " + e);
			}
			public void scrollTextToPoint(AccessibleTextExtendedEvent e) {
				System.out.println("scrollTextToPoint " + e);
			}
			public void setCaretOffset(AccessibleTextExtendedEvent e) {
				System.out.println("setCaretOffset " + e);
			}
			public void setSelection(AccessibleTextExtendedEvent e) {
				System.out.println("setSelection " + e);
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