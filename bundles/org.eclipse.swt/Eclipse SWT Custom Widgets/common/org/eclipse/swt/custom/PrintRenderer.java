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
class PrintRenderer extends AbstractRenderer {
	WrappedContent content;
	Rectangle clientArea;
	GC gc;
	
PrintRenderer(Device device, GC gc, Font regularFont, StyledText parent, boolean isBidi, int tabLength, int lineEndSpaceWidth, int leftMargin, Rectangle clientArea) {
	super(device, regularFont, parent, isBidi, lineEndSpaceWidth, leftMargin);
	this.clientArea = clientArea;	
	this.gc = gc;
	calculateLineHeight();
	setTabLength(tabLength);
	content = new WrappedContent(this, parent.internalGetLogicalContent());
	// wrapLines requires tab width to be known	
	content.wrapLines();
}
/**
 * Factor out in separate DisplayRenderer/PrinterRenderer?
 */
protected Rectangle getClientArea() {
	return clientArea;
}
protected void disposeGC(GC gc) {
	// we didn't create the GC so don't dispose it
}
protected GC getGC() {
	return gc;
}
/**
 * Factor out in separate DisplayRenderer/PrinterRenderer?
 */
protected StyledTextContent getContent() {
	return content;
}
}
