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
	Hashtable printerColors = new Hashtable();
	
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
protected void dispose() {
	if (printerColors != null) {
		Iterator colors = printerColors.values().iterator();
		
		while (colors.hasNext()) {
			Color color = (Color) colors.next();
			color.dispose();
		}
		printerColors = null;
	}
	super.dispose();
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
private Color getPrinterColor(Color color) {
	Color printerColor = null;
	
	if (color != null) {
		printerColor = (Color) printerColors.get(color);		
		if (printerColor == null) {
			printerColor = new Color(getDevice(), color.getRGB());
			printerColors.put(color, printerColor);
		}
	}
	return printerColor;
}
/**
 */
protected StyledTextContent getContent() {
	return content;
}
/**
 * @see AbstractRenderer#getLineBackgroundData
 */
protected StyledTextEvent getLineBackgroundData(int lineOffset, String line) {
	StyledTextEvent event = super.getLineBackgroundData(lineOffset, line);
	
	event.lineBackground = getPrinterColor(event.lineBackground);
	return event;
}
/**
 * @see AbstractRenderer#getLineStyleData
 */
protected StyledTextEvent getLineStyleData(int lineOffset, String line) {
	StyledTextEvent event = super.getLineStyleData(lineOffset, line);
	
	for (int i = 0; i < event.styles.length; i++) {
		StyleRange style = event.styles[i];
		Color printerBackground = getPrinterColor(style.background);
		Color printerForeground = getPrinterColor(style.foreground);
		
		if (printerBackground != style.background || 
			printerForeground != style.foreground) {
			style = (StyleRange) style.clone();
			style.background = printerBackground;
			style.foreground = printerForeground;
			event.styles[i] = style;
		}
	}
	return event;
}
/**
 * Do not print the selection.
 * <p>
 * @see AbstractRenderer#getSelectionLineStyles
 */
protected StyleRange[] getSelectionLineStyles(StyleRange[] styles) {
	return styles;
}
}
