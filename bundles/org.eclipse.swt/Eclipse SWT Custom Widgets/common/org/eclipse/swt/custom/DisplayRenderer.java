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
 * @see AbstractRenderer#drawLineSelectionBackground
 */
protected void drawLineSelectionBackground(String line, int lineOffset, StyleRange[] styles, int paintY, GC gc, FontData currentFont, StyledTextBidi bidi) {
	StyledText parent = getParent();
	Point selection = parent.internalGetSelection();
	LineCache lineCache = parent.internalGetLineCache();
	StyledTextContent content = getContent();
	int lineLength = line.length();
	int paintX;
	int selectionBackgroundWidth = -1;
	int selectionStart = Math.max(0, selection.x - lineOffset);
	int selectionEnd = selection.y - lineOffset;
	int selectionLength = selectionEnd - selectionStart;
	int horizontalScrollOffset = parent.internalGetHorizontalPixel();
	int leftMargin = getLeftMargin();
	int lineEndSpaceWidth = getLineEndSpaceWidth();
	int lineHeight = getLineHeight();
	boolean wordWrap = parent.internalGetWordWrap();
	
	if (selectionEnd == selectionStart || selectionEnd < 0 || selectionStart > lineLength) {
		return;
	}
	if (bidi != null) {
		paintX = bidiTextWidth(line, 0, selectionStart, 0, bidi);
	}
	else {
		paintX = textWidth(line, lineOffset, 0, selectionStart, filterLineStyles(styles), 0, gc, currentFont);
	}
	// selection extends past end of line?
	if (selectionEnd > lineLength) {
		if ((parent.getStyle() & SWT.FULL_SELECTION) != 0) {
			// use the greater of the client area width and the content 
			// width. fixes 1G8IYRD
			selectionBackgroundWidth = Math.max(getClientArea().width, lineCache.getWidth());
		}
		else {
			selectionLength = lineLength - selectionStart;
		}
	}
	gc.setBackground(getSelectionBackground());
	gc.setForeground(getSelectionForeground());
	if (selectionBackgroundWidth == -1) {
		boolean isWrappedLine = false;

		if (wordWrap) {
			int lineEnd = lineOffset + lineLength;
			int lineIndex = content.getLineAtOffset(lineEnd);

			// is the start offset of the next line the same as the end 
			// offset of this line?			
			if (lineIndex < content.getLineCount() - 1 &&
				content.getOffsetAtLine(lineIndex + 1) == lineEnd) {
				isWrappedLine = true;
			}
		}
		if (bidi != null) {
			selectionBackgroundWidth = bidiTextWidth(line, selectionStart, selectionLength, paintX, bidi);
		}
		else {
			selectionBackgroundWidth = textWidth(line, lineOffset, selectionStart, selectionLength, styles, paintX, gc, currentFont);
		}
		if (selectionBackgroundWidth < 0) {
			// width can be negative when in R2L bidi segment
			paintX += selectionBackgroundWidth;
			selectionBackgroundWidth *= -1;
		}
		if (selectionEnd > lineLength && isWrappedLine == false) {
			selectionEnd = selectionStart + selectionLength;
			// if the selection extends past this line, render an additional 
			// whitespace background at the end of the line to represent the 
			// selected line break
			if (bidi != null && selectionEnd > 0 && bidi.isRightToLeft(selectionEnd - 1)) {
				int lineEndX = bidi.getTextWidth();
				gc.fillRectangle(lineEndX - horizontalScrollOffset + leftMargin, paintY, lineEndSpaceWidth, lineHeight);
			}
			else {
				selectionBackgroundWidth += lineEndSpaceWidth;
			}
		}
	}	
	// handle empty line case
	if (bidi != null && paintX == 0) {
		paintX = StyledText.XINSET;	
	}
	// fill the background first since expanded tabs are not 
	// drawn as spaces. tabs just move the draw position. 
	gc.fillRectangle(paintX - horizontalScrollOffset + leftMargin, paintY, selectionBackgroundWidth, lineHeight);
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
/**
 * Returns the background color to be used for rendering selected text.
 * <p>
 *
 * @return background color to be used for rendering selected text
 */
private Color getSelectionBackground() {
	return getDevice().getSystemColor(SWT.COLOR_LIST_SELECTION);
}
/**
 * Returns the foreground color to be used for rendering selected text.
 * <p>
 *
 * @return foreground color to be used for rendering selected text
 */
private Color getSelectionForeground() {
	return getDevice().getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT);
}
/**
 * @see AbstractRenderer#getSelectionLineStyles
 */
/*
Pseudo code for getSelectionLineStyles
	for each style {
		if (style ends before selection start) {
			add style to list
		}
		else
		if (style overlaps selection start (i.e., starts before selection start, ends after selection start) {
			change style end
			create new selection style with same font style starting at selection start ending at style end
			add selection style
			// does style extend beyond selection?
			if (selection style end > selection end) {
				selection style end = selection end
				// preserve rest (unselected part) of old style
				style start = selection end
				style length = old style end - selection end
				add style
			}
		}
		else
		if (style starts within selection) {
			if (no selection style created) {
				create selection style with regular font style, starting at selection start, ending at style start
				add selection style				
				if (style start == selection start) {
					set selection style font to style font
				}
			}
			// gap between current selection style end and new style start?
			if (style start > selection styke end && selection style font style != NORMAL) {
				create selection style with regular font style, starting at selection style end, ending at style start
				add selection style
			}
			if (selection style font != style font) {
				selection style end = style start
				add selection style
				create selection style with style font style, starting at style start, ending at style end
			}
			else {
				selection style end = style end
			}
			// does style extend beyond selection?			
			if (selection style end > selection end) {
				selection style end = selection end
				// preserve rest (unselected part) of old style
				style start = selection end
				style length = old style end - selection end
				style start = selection end
				add style
			}
		}
		else {
			if (no selection style created) {
				create selection style with regular font style, starting at selection start, ending at selection end
				add selection style
			}
			else
			if (selection style end < selection end) {
				if (selection style font style != NORMAL) {
					create selection style with regular font style, starting at selection style end, ending at selection end					
					add selection style
				}
				else {
					selection style end = selection end
				}
			}
			add style
		}									
	}
	if (no selection style created) {
		create selection style with regular font style, starting at selection start, ending at selection end
		add selection style to list
	}
	else
	if (selection style end < selection end) {
		if (selection style font style != NORMAL) {
			create selection style with regular font style, starting at selection style end, ending at selection end					
			add selection style
		}
		else {
			selection style end = selection end
		}
	}
*/
protected StyleRange[] getSelectionLineStyles(StyleRange[] styles) {
	Point selection = getParent().internalGetSelection();	
	int selectionStart = selection.x;
	int selectionEnd = selection.y;
	Vector newStyles = new Vector(styles.length);	
	StyleRange selectionStyle = null;
	Color foreground = getSelectionForeground();
	Color background = getSelectionBackground();

	// potential optimization: ignore styles if there is no bold style and the entire line is selected
	for (int i = 0; i < styles.length; i++) {
		StyleRange style = styles[i];
		int styleEnd = style.start + style.length;
		
		if (styleEnd <= selectionStart) {
			newStyles.addElement(style);
		}
		else // style overlaps selection start? (i.e., starts before selection start, ends after selection start
		if (style.start < selectionStart && styleEnd > selectionStart) {
			StyleRange newStyle = (StyleRange) style.clone();
			newStyle.length -= styleEnd - selectionStart;
			newStyles.addElement(newStyle);
			// create new selection style with same font style starting at selection start ending at style end
			selectionStyle = new StyleRange(selectionStart, styleEnd - selectionStart, foreground, background, newStyle.fontStyle);
			newStyles.addElement(selectionStyle);
			// if style extends beyond selection a new style is returned for the unselected part of the style
			newStyle = setSelectionStyleEnd(selectionStyle, style);
			if (newStyle != null) {
				newStyles.addElement(newStyle);					
			}				
		}
		else // style starts within selection?
		if (style.start >= selectionStart && style.start < selectionEnd) {
			StyleRange newStyle;
			int selectionStyleEnd;
			// no selection style created yet?
			if (selectionStyle == null) {
				// create selection style with regular font style, starting at selection start, ending at style start
				selectionStyle = new StyleRange(selectionStart, style.start - selectionStart, foreground, background);
				newStyles.addElement(selectionStyle);
				if (style.start == selectionStart) {
					selectionStyle.fontStyle = style.fontStyle;
				}
			}
			selectionStyleEnd = selectionStyle.start + selectionStyle.length;
			// gap between current selection style end and style start?
			if (style.start > selectionStyleEnd && selectionStyle.fontStyle != SWT.NORMAL) {
				// create selection style with regular font style, starting at selection style end, ending at style start
				selectionStyle = new StyleRange(selectionStyleEnd, style.start - selectionStyleEnd, foreground, background);
				newStyles.addElement(selectionStyle);
			}
			if (selectionStyle.fontStyle != style.fontStyle) {
				// selection style end = style start
				selectionStyle.length = style.start - selectionStyle.start;
				// create selection style with style font style, starting at style start, ending at style end
				selectionStyle = new StyleRange(style.start, style.length, foreground, background, style.fontStyle);
				newStyles.addElement(selectionStyle);
			}
			else {
				// selection style end = style end
				selectionStyle.length = styleEnd - selectionStyle.start;
			}
			// if style extends beyond selection a new style is returned for the unselected part of the style
			newStyle = setSelectionStyleEnd(selectionStyle, style);
			if (newStyle != null) {
				newStyles.addElement(newStyle);					
			}				
		}
		else {
			// no selection style created yet?
			if (selectionStyle == null) {
				// create selection style with regular font style, starting at selection start, ending at selection end
				selectionStyle = new StyleRange(selectionStart, selectionEnd - selectionStart, foreground, background);
				newStyles.addElement(selectionStyle);
			}
			else // does the current selection style end before the selection end?
			if (selectionStyle.start + selectionStyle.length < selectionEnd) {
				if (selectionStyle.fontStyle != SWT.NORMAL) {
					int selectionStyleEnd = selectionStyle.start + selectionStyle.length;
					// create selection style with regular font style, starting at selection style end, ending at selection end
					selectionStyle = new StyleRange(selectionStyleEnd, selectionEnd - selectionStyleEnd, foreground, background);
					newStyles.addElement(selectionStyle);
				}
				else {
					selectionStyle.length = selectionEnd - selectionStyle.start;
				}
			}
			newStyles.addElement(style);
		}
	}
	if (selectionStyle == null) {
		// create selection style with regular font style, starting at selection start, ending at selection end
		selectionStyle = new StyleRange(selectionStart, selectionEnd - selectionStart, foreground, background);
		newStyles.addElement(selectionStyle);
	}
	else // does the current selection style end before the selection end?
	if (selectionStyle.start + selectionStyle.length < selectionEnd) {
		if (selectionStyle.fontStyle != SWT.NORMAL) {
			int selectionStyleEnd = selectionStyle.start + selectionStyle.length;
			// create selection style with regular font style, starting at selection style end, ending at selection end
			selectionStyle = new StyleRange(selectionStyleEnd, selectionEnd - selectionStyleEnd, foreground, background);
			newStyles.addElement(selectionStyle);
		}
		else {
			selectionStyle.length = selectionEnd - selectionStyle.start;
		}
	}
	styles = new StyleRange[newStyles.size()];
	newStyles.copyInto(styles);
	return styles;
}
/**
 * Ensures that the selection style ends at the selection end.
 * <code>selectionStyle</code> is assumed to be created based on the style 
 * range of <code>style</code>. If <code>selectionStyle</code> does extend
 * beyond the selection range a new style is returned to preserve the style
 * passed in with <code>style</code>.
 * <p>
 * @param selectionStyle the selection style based on the style range in 
 * 	<code>style</code>
 * @param style the existing style that is to be merged with the selection
 * @return a new style that preserves the style passed in with <code>style</code>
 * 	if the selection does not fully extend over the existing style range.
 *  null otherwise.
 */
private StyleRange setSelectionStyleEnd(StyleRange selectionStyle, StyleRange style) {
	int selectionEnd = getParent().internalGetSelection().y;
	StyleRange newStyle = null;
	
	// does style extend beyond selection?				
	if (selectionStyle.start + selectionStyle.length > selectionEnd) {
		int styleEnd = style.start + style.length;	
		selectionStyle.length = selectionEnd - selectionStyle.start;
		// preserve rest (unselected part) of old style					
		newStyle = (StyleRange) style.clone();
		newStyle.start = selectionEnd;
		newStyle.length = styleEnd - selectionEnd;
	}
	return newStyle;				
}
}
