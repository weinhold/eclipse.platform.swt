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
 * that handle AccessibleTable events.
 * <p>
 * After creating an instance of a class that implements
 * this interface it can be added to an accessible using the
 * <code>addAccessibleTableListener</code> method and removed using
 * the <code>removeAccessibleTableListener</code> method.
 * </p>
 *
 * @see AccessibleTableAdapter
 * @see AccessibleTableEvent
 *
 * @since 3.6
 */
public interface AccessibleTableListener extends SWTEventListener {
	/**
	 * Returns the caption for the table.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] accessible - the caption for the table,
	 * 		or null if the table does not have a caption
	 * </ul>
	 */
	public void getCaption(AccessibleTableEvent e);

	/**
	 * Returns the accessible object at the specified row and column in the table.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] row - the 0 based row index for which to retrieve the accessible cell
	 * <li>[in] column - the 0 based column index for which to retrieve the accessible cell
	 * <li>[out] accessible - the table cell at the specified row and column,
	 * 		or null if the row or column index are not valid
	 * </ul>
	 */
	public void getCellAt(AccessibleTableEvent e);

	/**
	 * Returns the total number of columns in the table.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count - the number of columns in the table
	 * </ul>
	 */
	public void getColumnCount(AccessibleTableEvent e);

	/**
	 * Returns the description text of the specified column in the table.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] column - the 0 based index of the column for which to retrieve the description
	 * <li>[out] string - the description text of the specified column in the table,
	 * 		or null if the column does not have a description
	 * </ul>
	 */
	public void getColumnDescription(AccessibleTableEvent e);

	/**
	 * Returns the total number of rows in the table.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count - the number of rows in the table
	 * </ul>
	 */
	public void getRowCount(AccessibleTableEvent e);

	/**
	 * Returns the description text of the specified row in the table.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] row - the 0 based index of the row for which to retrieve the description
	 * <li>[out] string - the description text of the specified row in the table,
	 * 		or null if the row does not have a description
	 * </ul>
	 */
	public void getRowDescription(AccessibleTableEvent e);

	/**
	 * Returns the number of selected cells.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count -  the number of cells currently selected
	 * </ul>
	 */
	public void getSelectedCellCount(AccessibleTableEvent e);

	/**
	 * Returns the currently selected cells.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] accessibles - array containing the selected accessible table cells
	 * </ul>
	 */
	public void getSelectedCells(AccessibleTableEvent e);

	/**
	 * Returns the number of selected columns.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count - the number of columns currently selected
	 * </ul>
	 */
	public void getSelectedColumnCount(AccessibleTableEvent e);

	/**
	 * Returns the column indexes that are currently selected.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] selected - an array of 0 based column indexes of selected columns
	 * </ul>
	 */
	public void getSelectedColumns(AccessibleTableEvent e);

	/**
	 * Returns the number of selected rows.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count - the number of rows currently selected
	 * </ul>
	 */
	public void getSelectedRowCount(AccessibleTableEvent e);

	/**
	 * Returns the row indexes that are currently selected.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] selected - an array of 0 based row indexes of selected rows
	 * </ul>
	 */
	public void getSelectedRows(AccessibleTableEvent e);

	/**
	 * Returns the summary description of the table.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] accessible - the summary for the table,
	 * 		or null if the table does not have a summary
	 * </ul>
	 */
	public void getSummary(AccessibleTableEvent e);

	/**
	 * Returns a boolean value indicating whether the specified column is
	 * completely selected.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] column - 0 based index of the column for which to determine whether it is selected
	 * <li>[out] isSelected - true if the specified column is selected completely, and false otherwise
	 * </ul>
	 */
	public void isColumnSelected(AccessibleTableEvent e);

	/**
	 * Returns a boolean value indicating whether the specified row is
	 * completely selected.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] row - 0 based index of the row for which to determine whether it is selected
	 * <li>[out] isSelected - true if the specified row is selected completely, and false otherwise
	 * </ul>
	 */
	public void isRowSelected(AccessibleTableEvent e);

	/**
	 * Selects a column and unselects all previously selected columns.
	 * <p>
	 * The behavior should mimic that of the application, but for those applications
	 * that do not have a means in the GUI to select a full column of cells, the behavior
	 * should be as follows: First any selected columns in the table are unselected. Then
	 * the entire column of cells for the specified column is selected. If any of the
	 * cells in the selected column span additional columns, the cells in those columns
	 * are also selected.
	 * </p>
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] column - 0 based index of the column to be selected
	 * </ul>
	 */
	public void selectColumn(AccessibleTableEvent e);

	/**
	 * Selects a row and unselects all previously selected rows.
	 * <p>
	 * The behavior should mimic that of the application, but for those applications
	 * that do not have a means in the GUI to select a full row of cells, the behavior
	 * should be as follows:  First any selected rows in the table are unselected. Then
	 * the entire row of cells for the specified row is selected. If any of the
	 * cells in the selected row span additional rows, the cells in those rows
	 * are also selected.
	 * </p>
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] row - 0 based index of the row to be selected
	 * </ul>
	 */
	public void selectRow(AccessibleTableEvent e);

	/**
	 * Unselects one column, leaving other selected columns selected (if any).
	 * <p>
	 * The behavior should mimic that of the application, but for those applications
	 * that do not have a means in the GUI to unselect a full column of cells the
	 * behavior should be as follows:  The entire column of cells for the specified
	 * column is unselected.  If any of the cells in the selected column span additional
	 * columns, the cells in those columns are also unselected.
	 * </p>
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] column - 0 based index of the column to be unselected.
	 * </ul>
	 */
	public void unselectColumn(AccessibleTableEvent e);

	/**
	 * Unselects one row, leaving other selected rows selected (if any).
	 * <p>
	 * The behavior should mimic that of the application, but for those applications
	 * that do not have a means in the GUI to unselect a full row of cells the
	 * behavior should be as follows:  The entire row of cells for the specified
	 * row is unselected.  If any of the cells in the selected row span additional
	 * rows, the cells in those rows are also unselected.
	 * </p>
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] row - 0 based index of the row to be unselected
	 * </ul>
	 */
	public void unselectRow(AccessibleTableEvent e);
}
