package org.eclipse.swt.examples.accessibility;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.accessibility.*;
import org.eclipse.swt.events.*;

public class SimpleRelationsExample {
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
		accCanvas.addAccessibleRelation(ACC.RELATION_LABEL_FOR, accList);
		accList.addAccessibleRelation(ACC.RELATION_LABELLED_BY, accCanvas);
		
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}