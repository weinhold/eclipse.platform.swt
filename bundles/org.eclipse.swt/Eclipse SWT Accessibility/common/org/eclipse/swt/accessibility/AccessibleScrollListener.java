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
 * that handle AccessibleScroll events.
 * <p>
 * After creating an instance of a class that implements
 * this interface it can be added to an accessible using the
 * <code>addAccessibleScrollListener</code> method and removed using
 * the <code>removeAccessibleScrollListener</code> method.
 * </p>
 *
 * @see AccessibleScrollAdapter
 * @see AccessibleScrollEvent
 *
 * @since 3.6
 */
// TODO: Before exposing this api, need to look at UIA IScrollProvider - it has more powerful scrolling capability
public interface AccessibleScrollListener extends SWTEventListener {
	/**
	 * Scrolls a specific part of an object according to the scroll type.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] type - a scroll type indicating where the object should be placed
	 * 		on the screen. One of:<ul>
	 * 		<li>SCROLL_TYPE_TOP_LEFT
	 * 		<li>SCROLL_TYPE_BOTTOM_RIGHT
	 * 		<li>SCROLL_TYPE_TOP_EDGE
	 * 		<li>SCROLL_TYPE_BOTTOM_EDGE
	 * 		<li>SCROLL_TYPE_LEFT_EDGE
	 * 		<li>SCROLL_TYPE_RIGHT_EDGE
	 * 		<li>SCROLL_TYPE_ANYWHERE
	 * 		<li>SCROLL_TYPE_POINT
	 * 		</ul>
	 * </ul>
	 * <li>[optional in] x - for SCROLL_TYPE_POINT, the x coordinate of the destination point in display coordinates
	 * <li>[optional in] y - for SCROLL_TYPE_POINT, the y coordinate of the destination point in display coordinates
	 * </ul>
	 */
	public void scroll(AccessibleScrollEvent e);
}
