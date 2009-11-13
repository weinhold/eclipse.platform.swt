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
 * methods in the <code>AccessibleTableCellListener</code> interface.
 * <p>
 * Classes that wish to deal with <code>AccessibleTableCell</code> events can
 * extend this class and override only the methods that they are
 * interested in.
 * </p>
 *
 * @see AccessibleTableCellListener
 * @see AccessibleTableCellEvent
 *
 * @since 3.6
 */
public class AccessibleTableCellAdapter implements AccessibleTableCellListener {
	/**
	 * Returns the number of columns occupied by this cell accessible.
	 * <p>
	 * This is 1 if the specified cell is only in one column, or
	 * more than 1 if the specified cell spans multiple columns.
	 * </p>
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count - the 1-based number of columns spanned by the specified cell
	 * </ul>
	 */
	public void getColumnSpan(AccessibleTableCellEvent e) {};

	/**
	 * Returns the column headers as an array of cell accessibles.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] accessibles - an array of cell accessibles.
	 * <li>[out] count - columnHeaderCellCount
	 * 		The number of accessibles returned; the size of the returned array.
	 * </ul>
	 */
	public void getColumnHeaderCells(AccessibleTableCellEvent e) {};

	/**
	 * Translates this cell accessible into the corresponding column index.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] index - columnIndex
	 * 		Returns the 0 based column index of the cell of the specified cell or the index of
	 * 		the first column if the cell spans multiple columns.
	 * </ul>
	 */
	public void getColumnIndex(AccessibleTableCellEvent e) {};

	/**
	 * Returns the number of rows occupied by this cell accessible.
	 * <p>
	 * This is 1 if the specified cell is only in one row, or
	 * more than 1 if the specified cell spans multiple rows.
	 * </p>
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count - the 1-based number of rows spanned by the specified cell
	 * </ul>
	 */
	public void getRowSpan(AccessibleTableCellEvent e) {};

	/**
	 * Returns the row headers as an array of cell accessibles.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] accessibles - cellAccessibles
	 * 		Pointer to an array of references to cell accessibles.  The array is allocated
	 * 		by the server.  
	 * <li>[out] count - rowHeaderCellCount
	 * 		The number of accessibles returned; the size of the returned array.
	 * </ul>
	 */
	public void getRowHeaderCells(AccessibleTableCellEvent e) {};

	/**
	 * Translates this cell accessible into the corresponding row index.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] index - the 0 based row index of the specified cell
	 * or the index of the first row if the cell spans multiple rows
	 * </ul>
	 */
	public void getRowIndex(AccessibleTableCellEvent e) {};

	/**
	 * Returns a boolean value indicating whether this cell is selected.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] isSelected
	 * 		Returns TRUE if the specified cell is selected and FALSE otherwise.
	 * </ul>
	 */
	public void isSelected(AccessibleTableCellEvent e) {};

	/**
	 * Returns a reference to the accessbile of the containing accessible - table.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] accessible - table
	 * 		Returns a reference to the IUnknown of the containing accessible - table.
	 * </ul>
	 */
	public void getTable(AccessibleTableCellEvent e) {};
}
