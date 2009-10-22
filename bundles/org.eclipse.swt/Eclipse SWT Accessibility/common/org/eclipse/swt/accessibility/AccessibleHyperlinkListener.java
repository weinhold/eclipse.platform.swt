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

import org.eclipse.swt.internal.SWTEventListener;

/**
 * Classes which implement this interface provide methods
 * that handle AccessibleHyperlink events.
 * <p>
 * After creating an instance of a class that implements
 * this interface it can be added to an accessible using the
 * <code>addAccessibleHyperlinkListener</code> method and removed using
 * the <code>removeAccessibleHyperlinkListener</code> method.
 * </p>
 *
 * @see AccessibleHyperlinkAdapter
 * @see AccessibleHyperlinkEvent
 *
 * @since 3.6
 */
public interface AccessibleHyperlinkListener extends SWTEventListener {
	/**
	 * Returns an object that represents the link accessible - anchor, as appropriate
	 * for the link at the specified index.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] index
	 * 		A 0 based index identifies the accessible - anchor when, as in the case of an image map,
	 * 		there is more than one link represented by this object.  The valid maximal
	 * 		index is indicated by AccessibleAction::nActions.
	 * <li>[out] accessible - anchor
	 * 		This is an implementation dependent value.  For example, for a text link this
	 * 		method could return the substring of the containing string where the substring
	 * 		is overridden with link behavior, and for an image link this method could return
	 * 		an IUnknown VARIANT for AccessibleImage.  See the section about
	 * 		"VARIANTs" for additional information.
	 * </ul>
	 */
	public void getAnchor(AccessibleHyperlinkEvent e);

	/**
	 * Returns an object representing the target of the link, as appropriate
	 * for the link at the specified index.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] index
	 * 		A 0 based index identifies the anchor when, as in the case of an image map,
	 * 		there is more than one link represented by this object.  The valid maximal
	 * 		index is indicated by AccessibleAction::nActions.
	 * <li>[out] accessible - anchorTarget
	 * 		This is an implementation dependent value.  For example this method could
	 * 		return a String VARIANT of the URI.  Alternatively this method could return an
	 * 		IUnknown VARIANT of a COM interface representing a target object to be
	 * 		activated when the link is activated.  See the section about
	 * 		"VARIANTs" for additional information.
	 * </ul>
	 */
	public void getAnchorTarget(AccessibleHyperlinkEvent e);

	/**
	 * Returns the 0 based character offset at which the textual representation of the hyperlink starts.
	 * 
	 * The returned value is related to the AccessibleExtendedText interface of the object that
	 * owns this hyperlink.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] index
	 * </ul>
	 */
	public void getStartIndex(AccessibleHyperlinkEvent e);

	/**
	 * Returns the 0 based character offset at which the textual representation of the hyperlink ends.
	 * 
	 * The returned value is related to the AccessibleExtendedText interface of the object that
	 * owns this hyperlink. The character at the index is not part of the hypertext.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] index
	 * </ul>
	 */
	public void getEndIndex(AccessibleHyperlinkEvent e);
}
