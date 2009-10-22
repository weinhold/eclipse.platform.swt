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
 * methods in the <code>AccessibleValueListener</code> interface.
 * <p>
 * Classes that wish to deal with <code>AccessibleValue</code> events can
 * extend this class and override only the methods that they are
 * interested in.
 * </p>
 *
 * @see AccessibleValueListener
 * @see AccessibleValueEvent
 *
 * @since 3.6
 */
public class AccessibleValueAdapter implements AccessibleValueListener {
	/**
	 * Returns the value of this object as a number.
	 * 
	 * The exact return type is implementation dependent.  Typical types are long and
	 * double.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] value - currentValue
	 * 		Returns the current value represented by this object.  See the section about
	 * 		"VARIANTs" for additional information.
	 * </ul>
	 */
	public void getCurrentValue(AccessibleValueEvent e) {};

	/**
	 * Sets the value of this object to the given number.
	 * 
	 * The argument is clipped to the valid interval whose upper and lower
	 * bounds are returned by the methods AccessibleValue::maximumValue and
	 * AccessibleValue::minimumValue, i.e. if it is 	lower than the minimum
	 * value the new value will be the minimum and if it is greater than the
	 * maximum then the new value will be the maximum.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] value
	 * 		The new value represented by this object.  The set of admissible types for
	 * 		this argument is implementation dependent.
	 * </ul>
	 */
	public void setCurrentValue(AccessibleValueEvent e) {};

	/**
	 * Returns the maximal value that can be represented by this object.
	 * 
	 * The type of the returned value is implementation dependent.  It does not have
	 * to be the same type as that returned by method AccessibleValue::currentValue.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] value - maximumValue
	 * 		Returns the maximal value in an implementation dependent type. If this object
	 * 		has no upper bound then an empty object is returned.  See the section about
	 * 		"VARIANTs" for additional information.
	 * </ul>
	 */
	public void getMaximumValue(AccessibleValueEvent e) {};

	/**
	 * Returns the minimal value that can be represented by this object.
	 * 
	 * The type of the returned value is implementation dependent.  It does not have
	 * to be the same type as that returned by method AccessibleValue::currentValue.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] value - minimumValue
	 * 		Returns the minimal value in an implementation dependent type. If this object
	 * 		has no lower bound then an empty object is returned.  See the section about
	 * 		"VARIANTs" for additional information.
	 * </ul>
	 */
	public void getMinimumValue(AccessibleValueEvent e) {};
}
