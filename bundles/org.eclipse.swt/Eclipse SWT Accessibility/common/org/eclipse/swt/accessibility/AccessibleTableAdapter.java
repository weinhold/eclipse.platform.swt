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
 * methods in the <code>AccessibleTableListener</code> interface.
 * <p>
 * Classes that wish to deal with <code>AccessibleTable</code> events can
 * extend this class and override only the methods that they are
 * interested in.
 * </p>
 *
 * @see AccessibleTableListener
 * @see AccessibleTableEvent
 *
 * @since 3.6
 */
public class AccessibleTableAdapter implements AccessibleTableListener {
	/**
	 * Returns the accessible object at the specified row and column in
	 * the table.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] row - the 0 based row index for which to retrieve the accessible
	 * <li>[in] column - the 0 based column index for which to retrieve the accessible
	 * <li>[out] accessible - if both row and column index are valid then the corresponding
	 * 		accessible cell object is returned.
	 * </ul>
	 */
	public void getCellAt(AccessibleTableEvent e) {};

	/**
	 * Returns the caption for the table.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] accessible
	 * 		If the table has a caption then a reference to it is returned, else a NULL
	 * 		pointer is returned.
	 * </ul>
	 */
	public void getCaption(AccessibleTableEvent e) {};

	/**
	 * Returns the string - description text of the specified column in the table.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] column
	 * 		The 0 based index of the column for which to retrieve the string - description.
	 * <li>[out] string - description
	 * 		Returns the string - description text of the specified column in the table if such a
	 * 		string - description exists.  Otherwise a NULL pointer is returned.
	 * </ul>
	 */
	public void getColumnDescription(AccessibleTableEvent e) {};

	/**
	 * Returns the total number of columns in table
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count - columnCount
	 * 		Number of columns in table (including columns outside the current viewport)
	 * </ul>
	 */
	public void getColumnCount(AccessibleTableEvent e) {};

	/**
	 * Returns the total number of rows in table
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count - rowCount
	 * 		Number of rows in table (including rows outside the current viewport)
	 * </ul>
	 */
	public void getRowCount(AccessibleTableEvent e) {};

	/**
	 * Returns the total number of selected cells
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count - cellCount
	 * 		Number of cells currently selected
	 * </ul>
	 */
	public void getSelectedCellCount(AccessibleTableEvent e) {};

	/**
	 * Returns the total number of selected columns
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count - columnCount
	 * 		Number of columns currently selected
	 * </ul>
	 */
	public void getSelectedColumnCount(AccessibleTableEvent e) {};

	/**
	 * Returns the total number of selected rows
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] count - rowCount
	 * 		Number of rows currently selected
	 * </ul>
	 */
	public void getSelectedRowCount(AccessibleTableEvent e) {};

	/**
	 * Returns the string - description text of the specified row in the table.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] row
	 * 		The 0 based index of the row for which to retrieve the string - description.
	 * <li>[out] string - description
	 * 		Returns the string - description text of the specified row in the table if such a
	 * 		string - description exists.  Otherwise a NULL pointer is returned.
	 * </ul>
	 */
	public void getRowDescription(AccessibleTableEvent e) {};

	/**
	 * Returns a list of accessibles currently selected.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] accessibles - cells
	 * 		Pointer to an array of references to selected accessibles.  The array is
	 * 		allocated by the server.  
	 * <li>[out] count - selectedCellCount
	 * 		The number of accessibles returned; the size of the returned array.
	 * </ul>
	 */
	public void getSelectedCells(AccessibleTableEvent e) {};

	/**
	 * Returns a list of column indexes currently selected (0 based).
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] selected - selectedColumns
	 * 		A pointer to an array of column indexes of selected columns (each index is
	 * 		0 based).  The array is allocated by the server. 
	 * <li>[out] count - columnCount
	 * 		The number of column indexes returned; the size of the returned array.
	 * </ul>
	 */
	public void getSelectedColumns(AccessibleTableEvent e) {};

	/**
	 * Returns a list of row indexes currently selected (0 based).
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] selected - selectedRows
	 * 		An array of row indexes of selected rows (each index is 0 based), allocated
	 * 		by the server. 
	 * <li>[out] count - rowCount
	 * 		The number of row indexes returned; the size of the returned array.
	 * </ul>
	 */
	public void getSelectedRows(AccessibleTableEvent e) {};

	/**
	 * Returns the summary description of the table.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] accessible
	 * 		Returns a reference to an implementation dependent accessible object
	 * 		representing the table's summary or a NULL pointer if the table
	 * 		does not support a summary.
	 * </ul>
	 */
	public void getSummary(AccessibleTableEvent e) {};

	/**
	 * Returns a boolean value indicating whether the specified column is
	 * completely selected.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] column
	 * 		0 based index of the column for which to determine whether it is selected.
	 * <li>[out] isSelected
	 * 		Returns TRUE if the specified column is selected completely and FALSE otherwise.
	 * </ul>
	 */
	public void isColumnSelected(AccessibleTableEvent e) {};

	/**
	 * Returns a boolean value indicating whether the specified row is completely
	 * selected.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] row
	 * 		0 based index of the row for which to determine whether it is selected.
	 * <li>[out] isSelected
	 * 		Returns TRUE if the specified row is selected completely and FALSE otherwise.
	 * </ul>
	 */
	public void isRowSelected(AccessibleTableEvent e) {};

	/**
	 * Selects a row and unselects all previously selected rows.
	 * 
	 * The behavior should mimic that of the application, but for those applications
	 * which do not have a means in the GUI to select a full row of cells the behavior
	 * should be as follows:  First any selected rows in the table are unselected.  Then
	 * the entire row of cells for the specified row is selected.  If any of the
	 * cells in the selected row span additional rows, the cells in those rows
	 * are also selected.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] row
	 * 		0 based index of the row to be selected.
	 * </ul>
	 */
	public void selectRow(AccessibleTableEvent e) {};

	/**
	 * Selects a column and unselects all previously selected columns.
	 * 
	 * The behavior should mimic that of the application, but for those applications
	 * which do not have a means in the GUI to select a full column of cells the behavior
	 * should be as follows:  First any selected columns in the table are unselected.  Then
	 * the entire column of cells for the specified column is selected.  If any of the
	 * cells in the selected column span additional columns, the cells in those columns
	 * are also selected.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] column
	 * 		0 based index of the column to be selected.
	 * </ul>
	 */
	public void selectColumn(AccessibleTableEvent e) {};

	/**
	 * Unselects one row, leaving other selected rows selected (if any).
	 * 
	 * The behavior should mimic that of the application, but for those applications
	 * which do not have a means in the GUI to unselect a full row of cells the
	 * behavior should be as follows:  The entire row of cells for the specified
	 * row is unselected.  If any of the cells in the selected row span additional
	 * rows, the cells in those rows are also unselected.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] row
	 * 		0 based index of the row to be unselected.
	 * </ul>
	 */
	public void unselectRow(AccessibleTableEvent e) {};

	/**
	 * Unselects one column, leaving other selected columns selected (if any).
	 * 
	 * The behavior should mimic that of the application, but for those applications
	 * which do not have a means in the GUI to unselect a full column of cells the
	 * behavior should be as follows:  The entire column of cells for the specified
	 * column is unselected.  If any of the cells in the selected column span additional
	 * columns, the cells in those columns are also unselected.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[in] column
	 * 		0 based index of the column to be unselected.
	 * </ul>
	 */
	public void unselectColumn(AccessibleTableEvent e) {};

	/**
	 * Returns the type and row/column indices describing how a table changed.
	 * 
	 * Provided for use by the IA2_EVENT_TABLE_MODEL_CHANGED event handler.
	 * 
	 * This data is only guaranteed to be valid while the thread notifying the event
	 * continues. Once the handler has returned, the validity of the data depends on
	 * how the server manages the life cycle of its objects. Also, note that the server
	 * may have different life cycle management strategies for controls depending on
	 * whether or not a control manages its children. Lists, trees, and tables can have
	 * a large number of children and thus it's possible that the child objects for those
	 * controls would only be created as needed. Servers should document their life cycle
	 * strategy as this will be of interest to assistive technology or script engines
	 * accessing data out of process or from other threads. Servers only need to save the
	 * most recent row and column values associated with the change and a scope of the
	 * entire application is adequate.
	 * 
	 * @param e an event object containing the following fields:<ul>
	 * <li>[out] modelChange
	 * 		A struct of (type(insert, delete, update), firstRow, lastRow, firstColumn, lastColumn).
	 * </ul>
	 */
	public void getModelChange(AccessibleTableEvent e) {};
}
