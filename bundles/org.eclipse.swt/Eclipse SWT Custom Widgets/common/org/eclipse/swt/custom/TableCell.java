package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.*;

public class TableCell extends Composite {

	Table table;
	TableItem row;
	int column;
	Listener scrollListener;
	Listener tableListener;
	Listener columnListener;
	Table cellTable;
	TableItem cellItem;

public TableCell (Table table, int style) {
	super(table, style);
	this.table = table;
	
	cellTable = new Table(this, SWT.NONE);
	cellTable.setBackground(getDisplay().getSystemColor(SWT.COLOR_RED));
	cellTable.setFont(table.getFont());
	cellTable.setForeground(table.getForeground());
	//cellTable.setBackground(table.getBackground());
	cellItem = new TableItem(cellTable, SWT.NONE);
	cellTable.setSelection(0);
	
	scrollListener = new Listener() {
		public void handleEvent(Event e) {
			switch (e.type) {
				case SWT.Selection: {
					onScrollSelection(e); 
					break;
				}
			}
		}
	};
	ScrollBar hBar = table.getHorizontalBar ();
	if (hBar != null) hBar.addListener(SWT.Selection, scrollListener);
	ScrollBar vBar = table.getVerticalBar ();
	if (vBar != null) vBar.addListener(SWT.Selection, scrollListener);
	
	tableListener = new Listener() {
		public void handleEvent(Event e) {
			switch (e.type) {
				case SWT.KeyDown:   onKeyDown(e); break;
				case SWT.Selection: onSelection(e); break;
				case SWT.MouseDown: onMouseDown(e); break;
				case SWT.FocusIn:   onFocus(e); break;
			}
		}
	};
	table.addListener(SWT.KeyDown,   tableListener);
	table.addListener(SWT.Selection, tableListener);
	table.addListener(SWT.MouseDown, tableListener);
	table.addListener(SWT.FocusIn,   tableListener);
	
	columnListener = new Listener() {
		public void handleEvent(Event e) {
			resize();
		}
	};
	
	cellTable.addListener(SWT.KeyDown, new Listener() {
		public void handleEvent(Event e) {
			onCellKeyDown(e);
		}
	});
	cellTable.addListener(SWT.Traverse, new Listener() {
		public void handleEvent(Event e) {
			onCellTraverse(e);
		}
	});
	
	addListener(SWT.Resize, new Listener() {
		public void handleEvent(Event e) {
			Point size = getSize();
			cellTable.setBounds(0, -2, size.x, size.y + 2);
		}
	});
}
public void dispose () {
	setColumn(-1);
	row = null;
	
	table.removeListener(SWT.KeyDown, tableListener);
	table.removeListener(SWT.Resize, tableListener);
	table.removeListener(SWT.Selection, tableListener);
	table.removeListener(SWT.MouseUp, tableListener);
	
	ScrollBar hBar = table.getHorizontalBar ();
	if (hBar != null) hBar.removeListener (SWT.Selection, scrollListener);
	ScrollBar vBar = table.getVerticalBar ();
	if (vBar != null) vBar.removeListener (SWT.Selection, scrollListener);
}
public TableColumn getColumn () {
	return table.getColumn(column);
}
public TableItem getRow() {
	return row;
}
private void onCellKeyDown(Event e){
	if (row == null) return;
	int newColumn = column;
	if (e.keyCode == SWT.ARROW_LEFT)  newColumn = Math.max(column - 1, 0);
	if (e.keyCode == SWT.ARROW_RIGHT) newColumn = Math.min(column + 1, table.getColumnCount() - 1);
	if (column == newColumn) return;
	setColumn(newColumn);
	updateCell();
}
private void onCellTraverse(Event e) {
	if (row == null) return;
	
	int index = table.indexOf(row);
	int newIndex = index;
	if (e.detail == SWT.TRAVERSE_ARROW_PREVIOUS && e.keyCode == SWT.ARROW_UP) {
		newIndex = Math.max(index - 1, 0);
	}
	if (e.detail == SWT.TRAVERSE_ARROW_NEXT && e.keyCode == SWT.ARROW_DOWN) {
		newIndex = Math.min(index + 1, table.getItemCount() - 1);
	}
	if (index == newIndex) return;
	row = table.getItem(newIndex);
	table.setSelection(newIndex);
	table.showItem(row);	
	updateCell();
	e.doit = false;
}

private void onFocus(Event e) {
	//cellTable.setFocus();
}
private void onMouseDown(Event e) {
	// find column at coordinates
	Point pt = new Point(e.x, e.y);
	row = table.getItem(pt);
	if (row == null) {
		setColumn(-1);
	} else {
		TableColumn[] columns = table.getColumns();
		int x = table.getClientArea().x;
		int index = -1;
		int i = 0;
		while (i < columns.length && index == -1) {
			int width = columns[i].getWidth();
			if (pt.x >= x && pt.x < x + width) {
				index = i;
			}
			x += width;
			i++;
		}
		setColumn(index);
	}
	updateCell();
}
private void onScrollSelection(Event e) {
	if (cellTable == null || cellTable.isDisposed()) return;
	if (column == -1 || row == null || row.isDisposed()) return;
	
	boolean hadFocus = false;
	if (getVisible ()) {
		hadFocus = cellTable.isFocusControl();
	}
	boolean visible = e.detail != SWT.DRAG;
	if (visible) {
		setBounds (row.getBounds(column));
	}
	setVisible (visible);
	if (visible && hadFocus) cellTable.setFocus ();
}
private void onSelection(Event e) {
	row = table.getSelection()[0];
	if (column == -1) setColumn(0);
	//if (table.isFocusControl()) cellTable.setFocus();
	updateCell();
}
private void onKeyDown(Event e){
	if (row == null) return;
	int oldColumn = column;
	if (e.keyCode == SWT.ARROW_LEFT) setColumn(Math.max(column - 1, 0));
	if (e.keyCode == SWT.ARROW_RIGHT) setColumn(Math.min(column + 1, table.getColumnCount() - 1));
	if (oldColumn == column) return;
	updateCell();
}

private void updateCell() {
	if (row != null && column != -1) {
		cellItem.setText(row.getText(column));
		cellItem.setImage(row.getImage(column));
		cellTable.pack();
		//cellTable.setFocus ();
	}
	resize();
}
private void resize() {
	if (row != null && column != -1) {
		setBounds (row.getBounds(column));
	} else {
		setBounds (-200, -200, 0, 0);
	}
}
private void setColumn(int column) {
	if (column == this.column) return;
	if (this.column != -1) {
		TableColumn tableColumn = table.getColumn(this.column);
		if (tableColumn != null && !tableColumn.isDisposed()) {
			tableColumn.removeListener(SWT.Resize, columnListener);
			tableColumn.removeListener(SWT.Move, columnListener);
		}
		this.column = -1;
	}
	if (column != -1) {
		TableColumn tableColumn = table.getColumn(column);
		if (tableColumn != null && !tableColumn.isDisposed()) {
			tableColumn.addListener(SWT.Resize, columnListener);
			tableColumn.addListener(SWT.Move, columnListener);
			this.column = column;
		}
	}
}
}

