package org.eclipse.swt.examples.accessibility;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.accessibility.*;

public class AccessibleValueExample {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());
		
		Text text = new Text(shell, SWT.BORDER);
		text.setText("Tab over to the scale");
		
		final Scale scale = new Scale(shell, SWT.NONE);
		scale.setSelection(5);
		
		Accessible accessibleScale = scale.getAccessible();
		accessibleScale.addAccessibleListener(new AccessibleAdapter() {
			public void getName(AccessibleEvent e) {
				e.result = "This is the scale's new accessible name";
			}
		});
//		accessibleScale.addAccessibleControlListener(new AccessibleControlAdapter() {
//			public void getValue(AccessibleControlEvent e) {
//				e.result = Integer.toString(scale.getSelection());
//			}
//		});
		accessibleScale.addAccessibleValueListener(new AccessibleValueAdapter() {
			public void setCurrentValue(AccessibleValueEvent e) {
				scale.setSelection(e.value);
			}
			public void getMinimumValue(AccessibleValueEvent e) {
				e.value = scale.getMinimum();
			}
			public void getMaximumValue(AccessibleValueEvent e) {
				e.value = scale.getMaximum();
			}
			public void getCurrentValue(AccessibleValueEvent e) {
				e.value = scale.getSelection(); // seems like a duplicate of the string value?
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