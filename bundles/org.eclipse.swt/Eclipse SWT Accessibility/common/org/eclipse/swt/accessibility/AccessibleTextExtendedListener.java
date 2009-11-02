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
 * Classes which implement this interface provide methods
 * that handle AccessibleTextExtended events.
 * <p>
 * After creating an instance of a class that implements
 * this interface it can be added to an accessible using the
 * <code>addAccessibleTextExtendedListener</code> method and removed using
 * the <code>removeAccessibleTextExtendedListener</code> method.
 * </p>
 *
 * @see AccessibleTextExtendedAdapter
 * @see AccessibleTextExtendedEvent
 *
 * @since 3.6
 */
public interface AccessibleTextExtendedListener extends AccessibleTextListener {
	/**
	 * Adds a text selection
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] start - the starting offset (0 based)
	 * <li>[in] end - the offset of the first character after the new selection (0 based)
	 * </ul>
	 */
	public void addSelection(AccessibleTextExtendedEvent e);

	/**
	 * Returns the bounding box of the specified position.
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
	 * <li>[in] offset - the offset of the character for which to return its bounding box.
	 * 		The valid range is 0..getCharacterCount.
	 * <li>[out] x - the X coordinate of the top left corner of the bounding box of the referenced character
	 * <li>[out] y - the Y coordinate of the top left corner of the bounding box of the referenced character
	 * <li>[out] width - the width of the bounding box of the referenced character
	 * <li>[out] height - the height of the bounding box of the referenced character
	 * </ul>
	 */
	public void getCharacterExtents(AccessibleTextExtendedEvent e);

	/**
	 * Returns the number of active non-contiguous selections.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count - the number of active non-contiguous selections
	 * </ul>
	 */
	public void getSelectionCount(AccessibleTextExtendedEvent e);

	/**
	 * Returns the offset of the character under the specified point.
	 * <p>
	 * The same functionality could be achieved by using the bounding
	 * boxes for each character as returned by getCharacterExtents.
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
	public void getOffsetAtPoint(AccessibleTextExtendedEvent e);

	/**
	 * Returns the character offsets of the specified text selection.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] index - the 0 based index of the selection
	 * <li>[out] start - the 0 based offset of first selected character
	 * <li>[out] end - the 0 based offset of one past the last selected character
	 * </ul>
	 */
	public void getSelection(AccessibleTextExtendedEvent e);

	/**
	 * Returns the substring between the two specified offsets.
	 * <p>
	 * The substring starts with the character at start (inclusive) and goes up to the
	 * character at end (exclusive), if start is less than end. If end is less than start,
	 * the result is the same as a call with the two arguments being exchanged. If both
	 * offsets have the same value, an empty string is returned.
	 * </p><p>
	 * The whole text can be requested by passing the offsets zero and getCharacterCount,
	 * or by calling AccessibleControlListener.getValue.
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
	public void getText(AccessibleTextExtendedEvent e);

	/**
	 * Returns a string - text portion before the given position.
	 * 
	 * Returns the substring of the specified string - text type that is located before the
	 * given character and does not include it. The result of this method should be
	 * same as a result for AccessibleTextExtended.textAtOffset with a suitably decreased
	 * index value.
	 * 
	 * For example, if string - text type is TEXT_BOUNDARY_WORD, then the complete
	 * word that is closest to and located before offset is returned.
	 * 
	 * If the index is valid, but no suitable word (or other boundary type) is found, a
	 * NULL pointer is returned.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] offset
	 * 		Index of the character for which to return the string - text part before it.  The index
	 * 		character will not be part of the returned string. The valid range is 0..getCharacterCount.
	 * <li>[in] type - the boundary type of the text portion to return. One of
	 * <li>[out] start - startOffset
	 * 		0 based offset of first character.
	 * <li>[out] end - endOffset
	 * 		0 based offset of one past the last character.
	 * <li>[out] string - text
	 * 		Returns the requested string - text portion.  This portion may be empty or invalid when
	 * 		no appropriate string - text portion is found or string - text type is invalid.
	 * </ul>
	 */
	public void getTextBeforeOffset(AccessibleTextExtendedEvent e);

	/**
	 * Returns a string - text portion after the given position.
	 * 
	 * Returns the substring of the specified string - text type that is located after the
	 * given character and does not include it. The result of this method should be
	 * same as a result for AccessibleTextExtended::string - textAtOffset with a suitably increased
	 * index value.
	 * 
	 * For example, if string - text type is TEXT_BOUNDARY_WORD, then the complete
	 * word that is closest to and located after offset is returned.
	 * 
	 * If the index is valid, but no suitable word (or other string - text type) is found, a
	 * NULL pointer is returned.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] offset
	 * 		Index of the character for which to return the string - text part before it.  The index
	 * 		character will not be part of the returned string. The valid range is 0..getCharacterCount.
	 * <li>[in] type - boundaryType
	 * 		The type of the string - text portion to return.  See ::IA2TextBoundaryType for the complete
	 * 		list.
	 * <li>[out] start - startOffset
	 * 		0 based offset of first character.
	 * <li>[out] end - endOffset
	 * 		0 based offset of one past the last character.
	 * <li>[out] string - text
	 * 		Returns the requested string - text portion.  This portion may be empty or invalid when
	 * 		no appropriate string - text portion is found or string - text type is invalid.
	 * </ul>
	 */
	public void getTextAfterOffset(AccessibleTextExtendedEvent e);

	/**
	 * Returns the substring of the specified text type that spans the specified offset.
	 * <p>
	 * If the index is valid, but no suitable word (or other text type) is found, return null.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] offset
	 * 		Index of the character for which to return the string - text part before it.  The index
	 * 		character will not be part of the returned string. The valid range is 0..getCharacterCount.
	 * <li>[in] type - boundaryType
	 * 		The type of the string - text portion to return.  See ::IA2TextBoundaryType for the complete
	 * 		list.
	 * <li>[out] start - the 0 based offset of first character
	 * <li>[out] end - the 0 based offset of one past the last character
	 * <li>[out] string - the requested substring, or null if no appropriate string is found
	 * 		or if the text type is invalid.
	 * </ul>
	 */
	public void getTextAtOffset(AccessibleTextExtendedEvent e);

	/**
	 * Deselects a range of text.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] index - selectionIndex
	 * 		Index of selection to remove (0 based).
	 * </ul>
	 */
	public void removeSelection(AccessibleTextExtendedEvent e);

	/**
	 * Sets the position of the caret.
	 * 
	 * The caret position/index - offset is that of the character logically following it,
	 * e.g. to the right of it in a left to right language.
	 * 
	 * Setting the caret position may or may not alter the current selection.  A
	 * change of the selection is notified to the accessibility event listeners with
	 * an EVENT_TEXT_SELECTION_CHANGED event.
	 * 
	 * When the new caret position differs from the old one (which, of course, is the
	 * standard case) this is notified to the accessibility event listeners with an
	 * EVENT_TEXT_CARET_MOVED event.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] index - offset
	 * 		The new index of the caret.  This caret is actually placed to the left side of
	 * 		the character with that index.  An index of 0 places the caret so that the next
	 * 		insertion goes before the first character.  An index of AccessibleTextExtended::nCharacters
	 * 		leads to insertion after the last character.
	 * </ul>
	 */
	public void setCaretOffset(AccessibleTextExtendedEvent e);

	/**
	 * Changes the bounds of an existing selection.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] index - selectionIndex
	 * 		Index of selection to change (0 based)
	 * <li>[in] start - startOffset
	 * 		New starting offset (0 based)
	 * <li>[in] end - endOffset
	 * 		New ending offset (0 based) - the offset of the character just past the last character of the selection.
	 * </ul>
	 */
	public void setSelection(AccessibleTextExtendedEvent e);

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
	public void getCharacterCount(AccessibleTextExtendedEvent e);

	/**
	 * Makes a specific part of a substring visible on screen.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] start - the 0 based offset of the first character of the substring
	 * <li>[in] end - the 0 based offset of the character just past the last character of the substring
	 * <li>[in] type - a scroll type indicating where the object should be placed on the screen
	 * </ul>
	 */
	public void scrollSubstringTo(AccessibleTextExtendedEvent e);

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
	public void scrollSubstringToPoint(AccessibleTextExtendedEvent e);

	/**
	 * Returns any inserted text.
	 * <p>
	 * Provided for use by the EVENT_TEXT_INSERTED and EVENT_TEXT_UPDATED
	 * event handlers.
	 * </p><p>
	 * This data is only guaranteed to be valid while the thread notifying the event
	 * continues. Once the handler has returned, the validity of the data depends on
	 * how the server manages the life cycle of its objects. Also, note that the server
	 * may have different life cycle management strategies for controls depending on
	 * whether or not a control manages its children. Lists, trees, and tables can have
	 * a large number of children and thus it's possible that the child objects for those
	 * controls would only be created as needed. Servers should document their life cycle
	 * strategy as this will be of interest to assistive technology or script engines
	 * accessing data out of process or from other threads. Servers only need to save the
	 * last inserted block of text and a scope of the entire application is adequate.
	 * </p>
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] string - the text that was just inserted
	 * <li>[out] start - the start index of the text that was just inserted
	 * <li>[out] end - the end index of the text that was just inserted
	 * </ul>
	 */
	public void getNewText(AccessibleTextExtendedEvent e);

	/**
	 * Returns any removed text.
	 * <p>
	 * Provided for use by the IA2_EVENT_TEXT_REMOVED/UPDATED event handlers.
	 * </p><p>
	 * This data is only guaranteed to be valid while the thread notifying the event
	 * continues. Once the handler has returned, the validity of the data depends on
	 * how the server manages the life cycle of its objects. Also, note that the server
	 * may have different life cycle management strategies for controls depending on
	 * whether or not a control manages its children. Lists, trees, and tables can have
	 * a large number of children and thus it's possible that the child objects for those
	 * controls would only be created as needed. Servers should document their life cycle
	 * strategy as this will be of interest to assistive technology or script engines
	 * accessing data out of process or from other threads. Servers only need to save the
	 * last removed block of text and a scope of the entire application is adequate.
	 * </p>
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] string - the text that was just removed
	 * <li>[out] start - the start index of the text that was just removed
	 * <li>[out] end - the end index of the text that was just removed
	 * </ul>
	 */
	public void getOldText(AccessibleTextExtendedEvent e);

	/**
	 * Returns the number of links and link groups contained within this hypertext
	 * paragraph.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count - the number of links and link groups within this hypertext paragraph,
	 * 		or 0 if there are none
	 * </ul>
	 */
	public void getHyperlinkCount(AccessibleTextExtendedEvent e);

	/**
	 * Returns the specified hyperlink.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] index - the 0 based index of the hyperlink to return
	 * <li>[out] accessible - the specified hyperlink object, or null if the index is invalid
	 * </ul>
	 */
	public void getHyperlink(AccessibleTextExtendedEvent e);

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
	public void getHyperlinkIndex(AccessibleTextExtendedEvent e);
}
