package org.eclipse.swt.examples.accessibility;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.accessibility.*;

public class SimpleRelationsExample2 {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(2, false));
		shell.setText("Accessible Relations");

		Label nameLabel = new Label(shell, SWT.NONE);
		nameLabel.setText("Name:");
		Text nameText = new Text(shell, SWT.BORDER);
		nameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Accessible accNameLabel = nameLabel.getAccessible();
		Accessible accNameText = nameText.getAccessible();
		accNameLabel.addAccessibleRelation(ACC.RELATION_LABEL_FOR, accNameText);
		accNameText.addAccessibleRelation(ACC.RELATION_LABELLED_BY, accNameLabel);

		Group addressGroup = new Group(shell, SWT.NONE);
		addressGroup.setText("Address");
		addressGroup.setLayout(new GridLayout(2, false));
		addressGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		Label streetLabel = new Label(addressGroup, SWT.NONE);
		streetLabel.setText("Street:");
		Text streetText = new Text(addressGroup, SWT.BORDER);
		streetText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Accessible accStreetLabel = streetLabel.getAccessible();
		Accessible accStreetText = streetText.getAccessible();
		accStreetLabel.addAccessibleRelation(ACC.RELATION_LABEL_FOR, accStreetText);
		accStreetText.addAccessibleRelation(ACC.RELATION_LABELLED_BY, accStreetLabel);
		
		Label cityLabel = new Label(addressGroup, SWT.NONE);
		cityLabel.setText("City:");
		Text cityText = new Text(addressGroup, SWT.BORDER);
		cityText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Accessible accCityLabel = cityLabel.getAccessible();
		Accessible accCityText = cityText.getAccessible();
		accCityLabel.addAccessibleRelation(ACC.RELATION_LABEL_FOR, accCityText);
		accCityText.addAccessibleRelation(ACC.RELATION_LABELLED_BY, accCityLabel);
		
		Accessible accAddressGroup = addressGroup.getAccessible();
		accStreetText.addAccessibleRelation(ACC.RELATION_MEMBER_OF, accAddressGroup);
		accStreetText.addAccessibleRelation(ACC.RELATION_LABELLED_BY, accAddressGroup);
		accCityText.addAccessibleRelation(ACC.RELATION_MEMBER_OF, accAddressGroup);
		accCityText.addAccessibleRelation(ACC.RELATION_LABELLED_BY, accAddressGroup);
		
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}