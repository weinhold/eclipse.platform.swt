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
public interface AccessibleScrollListener extends SWTEventListener {
	/**
	 * Makes an object visible on the screen.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] type - scrollType
	 * 		Defines where the object should be placed on the screen.
	 * </ul>
	 */
	public void scrollTo(AccessibleScrollEvent e);

	/**
	 * Moves the top left of an object to a specified location.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] x
	 * 		Defines the x coordinate.
	 * <li>[in] y
	 * 		Defines the y coordinate.
	 * </ul>
	 */
	public void scrollToPoint(AccessibleScrollEvent e);
}
