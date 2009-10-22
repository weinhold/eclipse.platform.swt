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
 * that handle AccessibleTableCell events.
 * <p>
 * After creating an instance of a class that implements
 * this interface it can be added to an accessible using the
 * <code>addAccessibleTableCellListener</code> method and removed using
 * the <code>removeAccessibleTableCellListener</code> method.
 * </p>
 *
 * @see AccessibleTableCellAdapter
 * @see AccessibleTableCellEvent
 *
 * @since 3.6
 */
public interface AccessibleTableCellListener extends SWTEventListener {
	/**
	 * Returns the number of columns occupied by this cell accessible.
	 * 
	 * The result is greater than 1 if the specified cell spans multiple columns.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count - columnsSpannCount
	 * 		Returns the 1 based column extent of the specified cell.
	 * </ul>
	 */
	public void getColumnExtent(AccessibleTableCellEvent e);

	/**
	 * Returns the column headers as an array of cell accessibles.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] accessibles - cellAccessibles
	 * 		Pointer to an array of references to cell accessibles.  The array is allocated
	 * 		by the server.  
	 * <li>[out] count - columnHeaderCellCount
	 * 		The number of accessibles returned; the size of the returned array.
	 * </ul>
	 */
	public void getColumnHeaderCells(AccessibleTableCellEvent e);

	/**
	 * Translates this cell accessible into the corresponding column index.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] index - columnIndex
	 * 		Returns the 0 based column index of the cell of the specified cell or the index of
	 * 		the first column if the cell spans multiple columns.
	 * </ul>
	 */
	public void getColumnIndex(AccessibleTableCellEvent e);

	/**
	 * Returns the number of rows occupied by this cell accessible.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count - rowsSpannCount
	 * 		Returns the row extent of the specified cell.
	 * </ul>
	 */
	public void getRowExtent(AccessibleTableCellEvent e);

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
	public void getRowHeaderCells(AccessibleTableCellEvent e);

	/**
	 * Translates this cell accessible into the corresponding row index.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] index - rowIndex
	 * 		Returns the 0 based row index of the specified cell or the index of
	 * 		the first row if the cell spans multiple rows.
	 * </ul>
	 */
	public void getRowIndex(AccessibleTableCellEvent e);

	/**
	 * Returns a boolean value indicating whether this cell is selected.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] isSelected
	 * 		Returns TRUE if the specified cell is selected and FALSE otherwise.
	 * </ul>
	 */
	public void getIsSelected(AccessibleTableCellEvent e);

	/**
	 * Gets the row and column indexes and extents of this cell accessible
	 * and whether or not it is selected.
	 * 
	 * This is a convenience function.  It is not mandatory to implement it.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] row
	 * 		0 based row index.
	 * <li>[out] column
	 * 		0 based column index.
	 * <li>[out] rowExtents
	 * 		Number of cells spanned by this cell in this row.
	 * <li>[out] columnExtents
	 * 		Number of cells spanned by this cell in this column.
	 * <li>[out] isSelected
	 * 		Indicates if the specified cell is selected.
	 * </ul>
	 */
	public void getRowColumnExtents(AccessibleTableCellEvent e);

	/**
	 * Returns a reference to the accessbile of the containing accessible - table.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] accessible - table
	 * 		Returns a reference to the IUnknown of the containing accessible - table.
	 * </ul>
	 */
	public void getTable(AccessibleTableCellEvent e);
}
