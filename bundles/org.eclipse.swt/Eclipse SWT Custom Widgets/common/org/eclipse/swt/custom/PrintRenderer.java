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
	StyledTextContent logicalContent;
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
	this.logicalContent = logicalContent;
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
private int getLogicalLineOffset(int visualLineOffset) {
	int logicalLineIndex = logicalContent.getLineAtOffset(visualLineOffset);
	
	return logicalContent.getOffsetAtLine(logicalLineIndex);
}
/**
 * @see AbstractRenderer#getBidiSegments
 */
protected int[] getBidiSegments(int lineOffset, String lineText) {
	int lineLength = lineText.length();
	int logicalLineOffset = getLogicalLineOffset(lineOffset);
	int[] segments = (int []) bidiSegments.get(new Integer(logicalLineOffset));
	
	if (segments == null) {
		segments = new int[] {0, lineLength};
	}
	else {
		// cached bidi segments are for logical lines.
		// make sure that returned segments match requested line since
		// line wrapping may require either entire or part of logical 
		// line bidi segments
		int logicalLineIndex = logicalContent.getLineAtOffset(lineOffset);
		int logicalLineLength = logicalContent.getLine(logicalLineIndex).length();
		
		if (lineOffset != logicalLineOffset || lineLength != logicalLineLength) {
			int lineOffsetDelta = lineOffset - logicalLineOffset;
			int newSegmentCount = 0;
			int[] newSegments = new int[segments.length];
			
			for (int i = 0; i < segments.length; i++) {
				newSegments[i] = Math.max(0, segments[i] - lineOffsetDelta);
				if (newSegments[i] > lineLength) {
					newSegments[i] = lineLength;
					newSegmentCount++;
					break;
				}
				if (i == 0 || newSegments[i] > 0) {
					newSegmentCount++;
				}
			}
			segments = new int[newSegmentCount];
			for (int i = 0, newIndex = 0; i < newSegments.length && newIndex < newSegmentCount; i++) {
				if (i == 0 || newSegments[i] > 0) {
					segments[newIndex++] = newSegments[i];
				}
			}
		}
	}
	return segments;
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
	int logicalLineOffset = getLogicalLineOffset(lineOffset);
	
	return (StyledTextEvent) lineBackgrounds.get(new Integer(logicalLineOffset));
}
/**
 * @see AbstractRenderer#getLineStyleData
 */
protected StyledTextEvent getLineStyleData(int lineOffset, String line) {
	int logicalLineOffset = getLogicalLineOffset(lineOffset);
	StyledTextEvent logicalLineEvent = (StyledTextEvent) lineStyles.get(new Integer(logicalLineOffset));
	
	if (logicalLineEvent != null) {
		logicalLineEvent = getLineStyleData(logicalLineEvent, lineOffset, line);
	}
	return logicalLineEvent;
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
protected boolean getWordWrap() {
	return true;
}
protected boolean isFullLineSelection() {
	return false;
}
}
