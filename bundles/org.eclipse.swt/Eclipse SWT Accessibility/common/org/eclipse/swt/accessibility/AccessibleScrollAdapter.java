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
 * methods in the <code>AccessibleScrollListener</code> interface.
 * <p>
 * Classes that wish to deal with <code>AccessibleScroll</code> events can
 * extend this class and override only the methods that they are
 * interested in.
 * </p>
 *
 * @see AccessibleScrollListener
 * @see AccessibleScrollEvent
 *
 * @since 3.6
 */
public class AccessibleScrollAdapter implements AccessibleScrollListener {
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
	public void scroll(AccessibleScrollEvent e) {};
}
