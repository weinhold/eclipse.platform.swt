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
	Image image;
	String text;


public TableCell (Table table, int style) {
	super(table, style);
	this.table = table;
	
	setFont(table.getFont());
	setForeground(table.getForeground());
	setBackground(table.getBackground());
	
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
				case SWT.Selection: onTableSelection(e); break;
				case SWT.MouseDown: onTableMouseDown(e); break;
				case SWT.FocusIn:   onTableFocus(e); break;
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
	
	Listener listener = new Listener() {
		public void handleEvent(Event e) {
			switch (e.type) {
				case SWT.KeyDown:   onKeyDown(e); break;
				case SWT.Traverse:  onTraverse(e); break;
				case SWT.Paint:     paint(e.gc); break;
			}
		}
	};
	addListener(SWT.KeyDown, listener);
	addListener(SWT.Traverse, listener);
	addListener(SWT.Paint, listener);
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
private void onKeyDown(Event e){
	if (row == null) return;
	int newColumn = column;
	if (e.keyCode == SWT.ARROW_LEFT)  newColumn = Math.max(column - 1, 0);
	if (e.keyCode == SWT.ARROW_RIGHT) newColumn = Math.min(column + 1, table.getColumnCount() - 1);
	if (column == newColumn) return;
	setColumn(newColumn);
	updateCell();
}
private void onTraverse(Event e) {
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

private void onTableFocus(Event e) {
	setFocus();
}
private void onTableMouseDown(Event e) {
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
	if (column == -1 || row == null || row.isDisposed()) return;
	
	boolean hadFocus = false;
	if (getVisible ()) {
		hadFocus = isFocusControl();
	}
	boolean visible = e.detail != SWT.DRAG;
	if (visible) {
		setBounds (row.getBounds(column));
	}
	setVisible (visible);
	if (visible && hadFocus) setFocus ();
}
private void onTableSelection(Event e) {
	row = table.getSelection()[0];
	if (column == -1) setColumn(0);
	//if (table.isFocusControl()) cellTable.setFocus();
	updateCell();
}
private void paint(GC gc) {
	Rectangle rect = getClientArea();
	int x = rect.x, y = rect.y;
	int width = rect.width; int height = rect.height;
	gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
	gc.fillRectangle(x, y, width, height);
	gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_BLACK));
	gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
	gc.drawFocus(x, y, width, height);
	
	if (image != null) {
		Rectangle imageRect = image.getBounds();
		x += 2; y += 2;
		width -= 4; height -= 4;
		gc.drawImage(image, 0, 0, imageRect.width, imageRect.height, x, y, height, height);
		x += height; width -= height;
	}
	if (text != null) {
		x += 2;
		gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
		Point extent = gc.textExtent(text);
		gc.drawText(text, x, y + (height - extent.y)/2, true);
	}
}

private void updateCell() {
	if (row != null && column != -1) {
		text = row.getText(column);
		image = row.getImage(column);
	}
	resize();
	GC gc = new GC(this);
	paint(gc);
	gc.dispose();
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

