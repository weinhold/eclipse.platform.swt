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
	Hashtable lineBackgrounds;
	Hashtable lineStyles;
	Hashtable bidiSegments;	
	
PrintRenderer(
		Device device, GC gc, StyledTextContent logicalContent, 
		Hashtable lineBackgrounds, Hashtable lineStyles, Hashtable bidiSegments,
		Font regularFont, boolean isBidi, int tabLength, int lineEndSpaceWidth, 
		int leftMargin, Rectangle clientArea) {
	super(device, regularFont, isBidi, lineEndSpaceWidth, leftMargin);
	this.lineBackgrounds = lineBackgrounds;
	this.lineStyles = lineStyles;
	this.bidiSegments = bidiSegments;	
	this.clientArea = clientArea;	
	this.gc = gc;
	calculateLineHeight();
	setTabLength(tabLength);
	content = new WrappedContent(this, logicalContent);
	// wrapLines requires tab width to be known	
	content.wrapLines();
}
	
protected void disposeGC(GC gc) {
	// we didn't create the GC so don't dispose it
}
/** 
 * Do not print the selection.
 * <p>
 * @see AbstractRenderer#drawLineSelectionBackground
 */
protected void drawLineSelectionBackground(String line, int lineOffset, StyleRange[] styles, int paintY, GC gc, FontData currentFont, StyledTextBidi bidi) {
	// do nothing
}
/**
 */
protected Rectangle getClientArea() {
	return clientArea;
}
protected GC getGC() {
	return gc;
}
protected int getHorizontalPixel() {
	return 0;
}
/**
 * @see AbstractRenderer#getBidiSegments
 */
protected int[] getBidiSegments(int lineOffset, String lineText) {	
	return (int []) bidiSegments.get(new Integer(lineOffset));
}
/**
 */
protected StyledTextContent getContent() {
	return content;
}
protected int getLastCaretDirection() {
	return SWT.RIGHT;
}
/**
 * @see AbstractRenderer#getLineBackgroundData
 */
protected StyledTextEvent getLineBackgroundData(int lineOffset, String line) {
	return (StyledTextEvent) lineBackgrounds.get(new Integer(lineOffset));
}
/**
 * @see AbstractRenderer#getLineStyleData
 */
protected StyledTextEvent getLineStyleData(int lineOffset, String line) {
	return (StyledTextEvent) lineStyles.get(new Integer(lineOffset));
}
protected Point getSelection() {
	return new Point(0, 0);
}
/**
 * Do not print the selection.
 * <p>
 * @see AbstractRenderer#getSelectionLineStyles
 */
protected StyleRange[] getSelectionLineStyles(StyleRange[] styles) {
	return styles;
}
protected boolean isFullLineSelection() {
	return false;
}
}
