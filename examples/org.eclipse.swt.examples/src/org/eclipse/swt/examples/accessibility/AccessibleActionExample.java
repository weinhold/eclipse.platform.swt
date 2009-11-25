package org.eclipse.swt.examples.accessibility;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.accessibility.*;

public class AccessibleActionExample {
	public static void main(String[] args) {
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