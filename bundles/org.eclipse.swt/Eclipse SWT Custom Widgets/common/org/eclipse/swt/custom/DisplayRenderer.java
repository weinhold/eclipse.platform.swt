/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package org.eclipse.swt.custom;

import java.util.*;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.StyledText.*;
import org.eclipse.swt.graphics.*;

/**
 * Created by StyledText to handle a single invalidate (i.e., Paint event).
 */
class DisplayRenderer extends AbstractRenderer {
	
DisplayRenderer(Device device, Font regularFont, StyledText parent, boolean isBidi, int tabLength, int lineEndSpaceWidth, int leftMargin) {
	super(device, regularFont, parent, isBidi, lineEndSpaceWidth, leftMargin);
	calculateLineHeight();
	setTabLength(tabLength);
}
/**
 */
protected Rectangle getClientArea() {
	return getParent().getClientArea();
}
/**
 */
protected StyledTextContent getContent() {
	return getParent().internalGetContent();
}

}
