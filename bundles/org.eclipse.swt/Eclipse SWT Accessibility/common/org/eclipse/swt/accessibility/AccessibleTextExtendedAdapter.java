/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.accessibility;

/**
 * This adapter class provides default implementations for the
 * methods in the <code>AccessibleTextExtendedListener</code> interface.
 * <p>
 * Classes that wish to deal with <code>AccessibleTextExtended</code> events can
 * extend this class and override only the methods that they are
 * interested in.
 * </p>
 *
 * @see AccessibleTextExtendedListener
 * @see AccessibleTextExtendedEvent
 *
 * @since 3.6
 */
public class AccessibleTextExtendedAdapter extends AccessibleTextAdapter implements AccessibleTextExtendedListener {
	/**
	 * Adds a text selection
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] start - the starting offset (0 based)
	 * <li>[in] end - the offset of the first character after the new selection (0 based)
	 * </ul>
	 */
	public void addSelection(AccessibleTextExtendedEvent e) {};

	/**
	 * Returns the total number of characters in the text.
	 * <p>
	 * Note that this may be different than the total number of bytes required to store the
	 * text, if the text contains multi-byte characters.
	 * </p>
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count - the total number of characters
	 * </ul>
	 */
	public void getCharacterCount(AccessibleTextExtendedEvent e) {}

	/**
	 * Returns the number of links and link groups contained within this hypertext
	 * paragraph.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count - the number of links and link groups within this hypertext paragraph,
	 * 		or 0 if there are none
	 * </ul>
	 */
	public void getHyperlinkCount(AccessibleTextExtendedEvent e) {}

	/**
	 * Returns the specified hyperlink.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] index - the 0 based index of the hyperlink to return
	 * <li>[out] accessible - the specified hyperlink object, or null if the index is invalid
	 * </ul>
	 */
	public void getHyperlink(AccessibleTextExtendedEvent e) {}

	/**
	 * Returns the index of the hyperlink that is associated with this character offset.
	 * <p>
	 * This is the case when a link spans the given character index.
	 * </p>
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] offset - a 0 based index of the character for which to return the link index
	 * <li>[out] index - the 0 based index of the hyperlink that is associated with this
	 * 		character offset, or -1 if the offset is not in a link
	 * </ul>
	 */
	public void getHyperlinkIndex(AccessibleTextExtendedEvent e) {}

	/**
	 * Returns the offset of the character under the specified point.
	 * <p>
	 * The same functionality could be achieved by using the bounding
	 * boxes for each character as returned by getCharacterBounds.
	 * The method getOffsetAtPoint, however, can be implemented more efficiently.
	 * </p>
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] x - the x value in display coordinates for which to look up the offset of the character that
	 * 		is rendered on the display at that point
	 * <li>[in] y - the position's y value for which to look up the offset of the character that
	 * 		is rendered on the display at that point
	 * <li>[out] offset - the zero-based offset of the character under the given point,
	 * 		or -1 if the point is invalid or there is no character under the point
	 * </ul>
	 */
	public void getOffsetAtPoint(AccessibleTextExtendedEvent e) {}

	/**
	 * Returns the bounding box of the specified text range in display coordinates.
	 * <p>
	 * Note: The virtual character after the last character of the represented text,
	 * i.e. the one at offset getCharacterCount, is a special case. It represents the
	 * current input position and will therefore typically be queried by AT more
	 * often than other positions.  Because it does not represent an existing character
	 * its bounding box is defined in relation to preceding characters.  It should be
	 * roughly equivalent to the bounding box of some character when inserted at the
	 * end of the text.  Its height typically being the maximal height of all the
	 * characters in the text or the height of the preceding character, its width being
	 * at least one pixel so that the bounding box is not degenerate.
	 * </p>
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] start - the offset of the first character of the substring to get the bounding box.
	 * 		The valid range is 0..getCharacterCount.
	 * <li>[in] end - one less than the offset of the last character of the substring to get the bounding box.
	 * 		The valid range is 0..getCharacterCount.
	 * <li>[out] x - the X coordinate of the top left corner of the bounding box of the referenced substring
	 * <li>[out] y - the Y coordinate of the top left corner of the bounding box of the referenced substring
	 * <li>[out] width - the width of the bounding box of the referenced substring
	 * <li>[out] height - the height of the bounding box of the referenced substring
	 * <li>[optional out] rectangles - a set of disjoint bounding rectangles
	 * </ul>
	 */
	public void getRangeBounds(AccessibleTextExtendedEvent e) {};

	/**
	 * Returns the text ranges contained within the given bounding box.
	 * <p>
	 * Partially visible characters are included in the returned ranges.
	 * </p>
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] x - the X coordinate of the top left corner of the bounding box
	 * <li>[in] y - the Y coordinate of the top left corner of the bounding box
	 * <li>[in] width - the width of the bounding box
	 * <li>[in] height - the height of the bounding box
	 * <li>[out] ranges - an array of pairs specifying the start and end offsets of each range
	 * </ul>
	 */
	public void getRanges(AccessibleTextExtendedEvent e) {};

	/**
	 * Returns the character offsets of all active non-contiguous selections.
	 * <p>
	 * Often applications only support one selection, in which case a single
	 * start, end pair is returned if the selection is currently active.
	 * </p>
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] ranges - an array of pairs specifying the start and end offsets of each selection.
	 * 		For each pair:<ul>
	 * 		<li>start is the 0 based offset of first selected character
	 * 		<li>end is the 0 based offset of one past the last selected character
	 * 		</ul>
	 * </ul>
	 */
	public void getSelections(AccessibleTextExtendedEvent e) {};

	/**
	 * Returns the substring between the two specified offsets.
	 * <p>
	 * The substring starts with the character at start (inclusive) and goes up to the
	 * character at end (exclusive), if start is less than end. If end is less than start,
	 * the result is the same as a call with the two arguments being exchanged. If both
	 * offsets have the same value, an empty string is returned.
	 * </p><p>
	 * The whole text can be requested by passing the offsets zero and getCharacterCount,
	 * or by calling AccessibleControlListener.getValue().
	 * </p>
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] start - the offset of the first character in the returned substring.
	 * 		The valid range is 0..getCharacterCount.
	 * <li>[in] end - one less than the offset of the last character in the returned substring.
	 * 		The valid range is 0..getCharacterCount.
	 * <li>[out] string - the substring starting with the character at start offset
	 * 		and up to but not including the character at end offset
	 * </ul>
	 */
	public void getText(AccessibleTextExtendedEvent e) {};

	/**
	 * Returns a substring and its range for the given offset, count and boundary type.
	 * <p>
	 * Returns the substring of the specified boundary type that is located count
	 * positions from the given character offset.
	 * </p><p>
	 * For example, if the boundary type is TEXT_BOUNDARY_WORD, then the complete
	 * word that is closest to and located count positions from offset is returned.
	 * </p><p>
	 * If the index is valid, but no suitable word (or other boundary type) is found,
	 * the returned string is null.
	 * </p>
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] type - the boundary type of the text portion to return. One of:<ul>
	 * 		<li> TEXT_BOUNDARY_CHAR - cluster?
	 * 		<li> TEXT_BOUNDARY_WORD
	 * 		<li> TEXT_BOUNDARY_SENTENCE
	 * 		<li> TEXT_BOUNDARY_PARAGRAPH
	 * 		<li> TEXT_BOUNDARY_LINE
	 * 		<li> TEXT_BOUNDARY_ALL
	 * 		</ul>
	 * <li>[in] offset - the offset of the character for which to return the nearest text part.
	 * 		The valid range is 0..getCharacterCount.
	 * <li>[in,out] count - determines if getting text before (count < 0), after (count > 0) or at (count == 0) the offset
	 * 			TODO: does count need to be 'out' also? we have start and end...
	 * <li>[out] start - the 0 based offset of first character of the returned substring
	 * <li>[out] end - the 0 based offset of one past the last character of the returned substring
	 * <li>[out] string - the requested text portion.  This portion may be empty or null when
	 * 		no appropriate text portion is found, or if the type is invalid.
	 * </ul>
	 */
	public void getTextRange(AccessibleTextExtendedEvent e) {};

	/**
	 * Deselects a range of text.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] index - the index of selection to remove (0 based).
	 * </ul>
	 */
	public void removeSelection(AccessibleTextExtendedEvent e) {};

	/**
	 * Makes a specific part of a substring visible on screen.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] start - the 0 based offset of the first character of the substring
	 * <li>[in] end - the 0 based offset of the character just past the last character of the substring
	 * <li>[in] type - a scroll type indicating where the object should be placed on the screen
	 * </ul>
	 */
	public void scrollTextTo(AccessibleTextExtendedEvent e) {}

	/**
	 * Moves the top left of a substring to a specified location.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] start - the 0 based offset of the first character of the substring
	 * <li>[in] end - the 0 based offset of the character just past the last character of the substring
	 * <li>[in] x - the x coordinate of the destination point in display coordinates
	 * <li>[in] y - the y coordinate of the destination point in display coordinates
	 * </ul>
	 */
	public void scrollTextToPoint(AccessibleTextExtendedEvent e) {}

	/**
	 * Sets the position of the caret.
	 * 
	 * The caret position is that of the character logically following it,
	 * e.g. to the right of it in a left to right language.
	 * 
	 * Setting the caret position may or may not alter the current selection.  A
	 * change of the selection is notified to the accessibility event listeners with
	 * an EVENT_TEXT_SELECTION_CHANGED event.
	 * 
	 * When the new caret position differs from the old one, this is notified to the
	 * accessibility event listeners with an EVENT_TEXT_CARET_MOVED event.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] offset - the new offset of the caret.  This caret is actually placed to the left side
	 * 		of the character with that offset.  An offset of 0 places the caret so that the next
	 * 		insertion goes before the first character.  An offset of getCharacterCount places
	 * 		the caret so that the next insertion goes after the last character.
	 * </ul>
	 */
	public void setCaretOffset(AccessibleTextExtendedEvent e) {};

	/**
	 * Changes the bounds of an existing selection.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] index - the 0 based index of the selection to change
	 * <li>[in] start - the new starting offset (0 based)
	 * <li>[in] end - the new ending offset (0 based).
	 * 		This is the offset of the character just past the last character of the selection.
	 * </ul>
	 */
	public void setSelection(AccessibleTextExtendedEvent e) {};
}
