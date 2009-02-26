/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.widgets;

import java.text.DateFormatSymbols;
import java.util.Calendar;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.accessibility.*;
import org.eclipse.swt.internal.carbon.LongDateRec;
import org.eclipse.swt.internal.carbon.OS;
import org.eclipse.swt.internal.carbon.Rect;

/**
 * Instances of this class are selectable user interface
 * objects that allow the user to enter and modify date
 * or time values.
 * <p>
 * Note that although this class is a subclass of <code>Composite</code>,
 * it does not make sense to add children to it, or set a layout on it.
 * </p>
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>DATE, TIME, CALENDAR, SHORT, MEDIUM, LONG, DROP_DOWN</dd>
 * <dt><b>Events:</b></dt>
 * <dd>DefaultSelection, Selection</dd>
 * </dl>
 * <p>
 * Note: Only one of the styles DATE, TIME, or CALENDAR may be specified,
 * and only one of the styles SHORT, MEDIUM, or LONG may be specified.
 * The DROP_DOWN style is only valid with the DATE style.
 * </p><p>
 * IMPORTANT: This class is <em>not</em> intended to be subclassed.
 * </p>
 * 
 * @see <a href="http://www.eclipse.org/swt/snippets/#datetime">DateTime snippets</a>
 * @see <a href="http://www.eclipse.org/swt/examples.php">SWT Example: ControlExample</a>
 * @see <a href="http://www.eclipse.org/swt/">Sample code and further information</a>
 *
 * @since 3.3
 */
public class DateTime extends Composite {
	/* DATE and TIME fields */
	LongDateRec dateRec;
	LongDateRec dateAndTime = new LongDateRec ();  // copy of date for a kControlClockTypeHourMinuteSecond or time for a kControlClockTypeMonthDayYear
	
	/* Emulated CALENDAR fields */
	Color fg, bg;
	Calendar calendar;
	DateFormatSymbols formatSymbols;
	Button monthDown, monthUp, yearDown, yearUp;
	
	/* Emulated DROP_DOWN calendar fields for DATE */
	Button down;
	boolean hasFocus;
	int savedYear, savedMonth, savedDay;
	Shell popupShell;
	DateTime popupCalendar, popupOwner;
	Listener popupListener, popupFilter;

	static final int MARGIN_WIDTH = 2;
	static final int MARGIN_HEIGHT = 1;
	static final int MIN_YEAR = 1752; // Gregorian switchover in North America: September 19, 1752
	static final int MAX_YEAR = 9999;

/**
 * Constructs a new instance of this class given its parent
 * and a style value describing its behavior and appearance.
 * <p>
 * The style value is either one of the style constants defined in
 * class <code>SWT</code> which is applicable to instances of this
 * class, or must be built by <em>bitwise OR</em>'ing together 
 * (that is, using the <code>int</code> "|" operator) two or more
 * of those <code>SWT</code> style constants. The class description
 * lists the style constants that are applicable to the class.
 * Style bits are also inherited from superclasses.
 * </p>
 *
 * @param parent a composite control which will be the parent of the new instance (cannot be null)
 * @param style the style of control to construct
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the parent is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the parent</li>
 *    <li>ERROR_INVALID_SUBCLASS - if this class is not an allowed subclass</li>
 * </ul>
 *
 * @see SWT#DATE
 * @see SWT#TIME
 * @see SWT#CALENDAR
 * @see Widget#checkSubclass
 * @see Widget#getStyle
 */
public DateTime (Composite parent, int style) {
	super (parent, checkStyle (style) | ((style & SWT.CALENDAR) != 0 ? SWT.NO_REDRAW_RESIZE : 0));
	if ((this.style & SWT.CALENDAR) != 0) {
		createCalendar();
	} else {
		createText((this.style & SWT.DROP_DOWN) != 0);
	}
	initAccessible ();
}

void createCalendar() {
	calendar = Calendar.getInstance();
	formatSymbols = new DateFormatSymbols();
	Listener listener = new Listener() {
		public void handleEvent(Event event) {
			switch(event.type) {
				case SWT.Paint:		calendarPaint(event); break;
				case SWT.Resize:	calendarResize(event); break;
				case SWT.MouseDown:	calendarMouseDown(event); break;
				case SWT.KeyDown:	calendarKeyDown(event); break;
				case SWT.Traverse:	calendarTraverse(event); break;
			}
		}
	};
	int [] listeners = new int [] {SWT.Paint, SWT.Resize, SWT.MouseDown, SWT.KeyDown, SWT.Traverse};
	for (int i = 0; i < listeners.length; i++) {
		addListener(listeners [i], listener);
	}
	
	yearDown = new Button(this, SWT.ARROW | SWT.LEFT);
	//yearDown.setToolTipText(SWT.getMessage ("SWT_Last_Year")); //$NON-NLS-1$
	monthDown = new Button(this, SWT.ARROW | SWT.LEFT);
	//monthDown.setToolTipText(SWT.getMessage ("SWT_Last_Month")); //$NON-NLS-1$
	monthUp = new Button(this, SWT.ARROW | SWT.RIGHT);
	//monthUp.setToolTipText(SWT.getMessage ("SWT_Next_Month")); //$NON-NLS-1$
	yearUp = new Button(this, SWT.ARROW | SWT.RIGHT);
	//yearUp.setToolTipText(SWT.getMessage ("SWT_Next_Year")); //$NON-NLS-1$
	listener = new Listener() {
		public void handleEvent(Event event) {
			switch(event.type) {
				case SWT.Selection:	calendarSelection(event); break;
				case SWT.KeyDown:	calendarKeyDown(event); break;
				case SWT.Traverse:	calendarTraverse(event); break;
			}
		}
	};
	listeners = new int [] {SWT.Selection, SWT.KeyDown, SWT.Traverse};
	for (int i = 0; i < listeners.length; i++) {
		yearDown.addListener(listeners [i], listener);
		monthDown.addListener(listeners [i], listener);
		monthUp.addListener(listeners [i], listener);
		yearUp.addListener(listeners [i], listener);
	}
}

void createText(boolean dropDown) {
	if ((style & SWT.DATE) != 0 && dropDown) {
		createDropDownButton();
		createPopupShell(-1, -1, -1);
	}
}

void createDropDownButton() {
	//TODO: This doesn't work, because ClockControl doesn't take children
	//TODO: Also, there's no easy way to hide the little arrows
	//TODO: So need to come up with a better plan - either draw, or get in the way of events, or something
	//TODO: (maybe create the ClockControl as a child of another, and clip the arrows, and add a button peer?)
	down = new Button(this, SWT.ARROW  | SWT.DOWN);
	down.addListener(SWT.Selection, new Listener() {
		public void handleEvent(Event event) {
			dropDownCalendar (!isDropped());
		}
	});
	popupListener = new Listener () {
		public void handleEvent (Event event) {
			if (event.widget == popupShell) {
				popupShellEvent (event);
				return;
			}
			if (event.widget == popupCalendar) {
				popupCalendarEvent (event);
				return;
			}
			if (event.widget == DateTime.this) {
				dateTimeEvent (event);
				return;
			}
			if (event.widget == getShell ()) {
				getDisplay().asyncExec(new Runnable() {
					public void run() {
						if (isDisposed()) return;
						popupCalendarFocus (SWT.FocusOut);
					}
				});
			}
		}
	};
	popupFilter = new Listener() {
		public void handleEvent(Event event) {
			Shell shell = ((Control)event.widget).getShell ();
			if (shell == DateTime.this.getShell ()) {
				popupCalendarFocus (SWT.FocusOut);
			}
		}
	};
}

void createPopupShell(int year, int month, int day) {	
	popupShell = new Shell (getShell (), SWT.NO_TRIM | SWT.ON_TOP);
	popupCalendar = new DateTime (popupShell, SWT.CALENDAR);
	popupCalendar.popupOwner = this;
	if (font != null) popupCalendar.setFont (font);
	if (fg != null) popupCalendar.setForeground (fg);
	if (bg != null) popupCalendar.setBackground (bg);

	int [] listeners = {SWT.Close, SWT.Paint, SWT.Deactivate};
	for (int i=0; i < listeners.length; i++) {
		popupShell.addListener (listeners [i], popupListener);
	}
	listeners = new int [] {SWT.MouseUp, SWT.Selection, SWT.Traverse, SWT.KeyDown, SWT.KeyUp, SWT.FocusIn, SWT.Dispose};
	for (int i=0; i < listeners.length; i++) {
		popupCalendar.addListener (listeners [i], popupListener);
	}

	if (year != -1) popupCalendar.setDate(year, month, day);
}

/**
 * Adds the listener to the collection of listeners who will
 * be notified when the control is selected by the user, by sending
 * it one of the messages defined in the <code>SelectionListener</code>
 * interface.
 * <p>
 * <code>widgetSelected</code> is called when the user changes the control's value.
 * <code>widgetDefaultSelected</code> is typically called when ENTER is pressed.
 * </p>
 *
 * @param listener the listener which should be notified
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @see SelectionListener
 * @see #removeSelectionListener
 * @see SelectionEvent
 */
public void addSelectionListener (SelectionListener listener) {
	checkWidget ();
	if (listener == null) error (SWT.ERROR_NULL_ARGUMENT);
	TypedListener typedListener = new TypedListener (listener);
	addListener (SWT.Selection, typedListener);
	addListener (SWT.DefaultSelection, typedListener);
}

void calendarKeyDown(Event event) {
	if (popupOwner != null) {
		/* Escape cancels popupCalendar and reverts date. */
		if (event.character == SWT.ESC) {
			popupOwner.setDate(popupOwner.savedYear, popupOwner.savedMonth, popupOwner.savedDay);
			popupOwner.dropDownCalendar(false);
			return;
		}
		
		/* Return, Alt+Up, and Alt+Down cancel popupCalendar and select date. */
		if (event.keyCode == SWT.CR || (event.stateMask & SWT.ALT) != 0 && (event.keyCode == SWT.ARROW_UP || event.keyCode == SWT.ARROW_DOWN)) {
			popupOwner.dropDownCalendar(false);
			return;
		}
	}
	int newDay = calendar.get(Calendar.DAY_OF_MONTH);
	switch (event.keyCode) {
		case SWT.ARROW_DOWN: newDay += 7; break;
		case SWT.ARROW_UP: newDay -= 7; break;
		case SWT.ARROW_RIGHT: newDay += 1; break;
		case SWT.ARROW_LEFT: newDay -= 1; break;
		case SWT.PAGE_DOWN: calendarSelection((event.stateMask & SWT.ALT) != 0 ? yearUp : monthUp); break;
		case SWT.PAGE_UP: calendarSelection((event.stateMask & SWT.ALT) != 0 ? yearDown : monthDown); break;
	}
	setDay(newDay, true);
	if (event.character == SWT.CR) {
		postEvent (SWT.DefaultSelection);
	}
}

void calendarMouseDown(Event event) {
	setFocus();
	Point cellSize = getCellSize(null);
	int column = event.x / cellSize.x;
	int row = event.y / cellSize.y;	
	int cell = row * 7 + column;
	int newDay = getDate(cell);
	setDay(newDay, true);
}

void calendarPaint(Event event) {
	GC gc = event.gc;
	Rectangle client = getClientArea();
	Point cellSize = getCellSize(gc);
	drawDaysOfWeek(gc, cellSize, client);
	drawDays(gc, cellSize, client);
	drawMonth(gc, cellSize, client);
}

void calendarResize(Event event) {
	yearDown.pack();
	monthDown.pack();
	monthUp.pack();
	yearUp.pack();
	Point cellSize = getCellSize(null);
	Point size = monthDown.getSize();
	int height = Math.max(cellSize.y, size.y);
	int y = cellSize.y * 7 + (height - size.y) / 2;
	yearDown.setLocation(0, y);
	monthDown.setLocation(size.x, y);
	int x = cellSize.x * 7 - size.x;
	monthUp.setLocation(x - size.x, y);
	yearUp.setLocation(x, y);
}

void calendarSelection(Event event) {
	if (event.widget == monthDown) {
		calendar.add(Calendar.MONTH, -1);
	} else if (event.widget == monthUp) {
		calendar.add(Calendar.MONTH, 1);
	} else if (event.widget == yearDown) {
		calendar.add(Calendar.YEAR, -1);
	} else if (event.widget == yearUp) {				
		calendar.add(Calendar.YEAR, 1);
	} else {
		return;
	}
	redraw();
	postEvent(SWT.Selection);
}

void calendarSelection(Button button) {
	Event event = new Event();
	event.widget = button;
	calendarSelection(event);
}

void calendarTraverse(Event event) {
	switch (event.detail) {
		case SWT.TRAVERSE_ESCAPE:
		case SWT.TRAVERSE_PAGE_NEXT:
		case SWT.TRAVERSE_PAGE_PREVIOUS:
		case SWT.TRAVERSE_RETURN:
		case SWT.TRAVERSE_TAB_NEXT:
		case SWT.TRAVERSE_TAB_PREVIOUS:
			event.doit = true;
			break;
	}
}

static int checkStyle (int style) {
	/*
	* Even though it is legal to create this widget
	* with scroll bars, they serve no useful purpose
	* because they do not automatically scroll the
	* widget's client area.  The fix is to clear
	* the SWT style.
	*/
	style &= ~(SWT.H_SCROLL | SWT.V_SCROLL);
	style = checkBits (style, SWT.DATE, SWT.TIME, SWT.CALENDAR, 0, 0, 0);
	style = checkBits (style, SWT.MEDIUM, SWT.SHORT, SWT.LONG, 0, 0, 0);
	if ((style & SWT.DATE) == 0) style &=~ SWT.DROP_DOWN;
	return style;
}

protected void checkSubclass () {
	if (!isValidSubclass ()) error (SWT.ERROR_INVALID_SUBCLASS);
}

public Point computeSize (int wHint, int hHint, boolean changed) {
	checkWidget ();
	int width = 0, height = 0;
	if (wHint == SWT.DEFAULT || hHint == SWT.DEFAULT) {
		if ((style & SWT.CALENDAR) != 0) {
			Point cellSize = getCellSize(null);
			Point buttonSize = monthDown.computeSize(SWT.DEFAULT, SWT.DEFAULT, changed);
			width = cellSize.x * 7;
			height = cellSize.y * 7 + Math.max(cellSize.y, buttonSize.y);
		} else {
			Rect rect = new Rect ();
			OS.GetBestControlRect (handle, rect, null);
			width = rect.right - rect.left;
			height = rect.bottom - rect.top;
		}
	}
	if (width == 0) width = DEFAULT_WIDTH;
	if (height == 0) height = DEFAULT_HEIGHT;
	if (wHint != SWT.DEFAULT) width = wHint;
	if (hHint != SWT.DEFAULT) height = hHint;
	int border = getBorderWidth ();
	width += border * 2; height += border * 2;
	return new Point (width, height);
}

void createHandle () {
	int clockType = -1;
	if ((style & SWT.TIME) != 0) clockType = (style & SWT.SHORT) != 0 ? OS.kControlClockTypeHourMinute : OS.kControlClockTypeHourMinuteSecond;
	if ((style & SWT.DATE) != 0) clockType = (style & SWT.SHORT) != 0 ? OS.kControlClockTypeMonthYear : OS.kControlClockTypeMonthDayYear;
	if (clockType != -1) { /* SWT.DATE and SWT.TIME */
		int clockFlags = OS.kControlClockFlagStandard;
		int [] outControl = new int [1];
		int window = OS.GetControlOwner (parent.handle);
		OS.CreateClockControl(window, null, clockType, clockFlags, outControl);
		if (outControl [0] == 0) error (SWT.ERROR_NO_HANDLES);
		handle = outControl [0];
	} else { /* SWT.CALENDAR */
		super.createHandle();
	}
}

void createWidget() {
	super.createWidget ();
	getDate();
}

void dateTimeEvent (Event event) {
	switch (event.type) {
		case SWT.Dispose:
			if (popupShell != null && !popupShell.isDisposed ()) {
				popupCalendar.removeListener (SWT.Dispose, popupListener);
				popupShell.dispose ();
			}
			Shell shell = getShell ();
			shell.removeListener (SWT.Deactivate, popupListener);
			Display display = getDisplay ();
			display.removeFilter (SWT.FocusIn, popupFilter);
			popupShell = null;  
			popupCalendar = null;  
			down = null;
			break;
		case SWT.FocusIn:
			Control focusControl = getDisplay ().getFocusControl ();
			if (focusControl == down || focusControl == popupCalendar) return;
			if (isDropped()) {
				popupCalendar.setFocus();
			} else {
				setFocus();
			}
			break;
		case SWT.Move:
			dropDownCalendar (false);
			break;
		case SWT.Resize:
			internalLayout (false);
			break;
	}
}

void drawDay(GC gc, Point cellSize, int day) {
	int cell = getCell(day);
	Point location = getCellLocation(cell, cellSize);
	String str = String.valueOf(day);
	Point extent = gc.stringExtent(str);
	int date = calendar.get(Calendar.DAY_OF_MONTH);
	if (day == date) {
		Display display = getDisplay();
		gc.setBackground(display.getSystemColor(SWT.COLOR_LIST_SELECTION));
		gc.setForeground(display.getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT));
		gc.fillRectangle(location.x + 1, location.y + 1, cellSize.x - 2, cellSize.y - 2);
		if (isFocusControl()) {
			gc.drawFocus(location.x, location.y, cellSize.x, cellSize.y);
		}
	}
	gc.drawString(str, location.x + (cellSize.x - extent.x) / 2, location.y + (cellSize.y - extent.y) / 2, true);
	if (day == date) {
		gc.setBackground(getBackground());
		gc.setForeground(getForeground());
	}
}

void drawDays(GC gc, Point cellSize, Rectangle client) {
	gc.setBackground(getBackground());
	gc.setForeground(getForeground());
	gc.fillRectangle(0, cellSize.y, client.width, cellSize.y * 7);
	int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
	int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	for (int day = firstDay; day <= lastDay; day++) {
		drawDay(gc, cellSize, day);
	}
}

void drawDaysOfWeek(GC gc, Point cellSize, Rectangle client) {
	Display display = getDisplay();
	gc.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
	gc.setForeground(display.getSystemColor(SWT.COLOR_WIDGET_FOREGROUND));
	gc.fillRectangle(0, 0, client.width, cellSize.y);
	String[] days = formatSymbols.getShortWeekdays();
	int x = 0, y = 0; 
	for (int i = 1; i < days.length; i++) {
		String day = days[i];
		Point extent = gc.stringExtent(day);
		gc.drawString(day, x + (cellSize.x - extent.x) / 2, y + (cellSize.y - extent.y) / 2, true);
		x += cellSize.x;
	}
	gc.drawLine(0, cellSize.y - 1, client.width, cellSize.y - 1);
}

void drawMonth(GC gc, Point cellSize, Rectangle client) {
	Display display = getDisplay();
	gc.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
	gc.setForeground(display.getSystemColor(SWT.COLOR_WIDGET_FOREGROUND));
	int y = cellSize.y * 7;
	gc.fillRectangle(0, y, client.width, cellSize.y);
	gc.drawLine(0, y - 1, client.width, y - 1);
	String str = formatSymbols.getShortMonths()[calendar.get(Calendar.MONTH)] + ", " + calendar.get(Calendar.YEAR);
	Point extent = gc.stringExtent(str);
	gc.drawString(str, (cellSize.x * 7 - extent.x) / 2, y + (cellSize.y - extent.y) / 2, true);
}

void dropDownCalendar(boolean drop) {
	if (drop == isDropped ()) return;
	if (!drop) {
		popupShell.setVisible (false);
		if (!isDisposed () && isFocusControl()) {
			setFocus();
		}
		return;
	}

	savedYear = getYear ();
	savedMonth = getMonth ();
	savedDay = getDay ();
	if (getShell() != popupShell.getParent ()) {
		int year = popupCalendar.getYear ();
		int month = popupCalendar.getMonth ();
		int day = popupCalendar.getDay ();
		popupCalendar.removeListener (SWT.Dispose, popupListener);
		popupShell.dispose();
		popupShell = null;
		popupCalendar = null;
		createPopupShell (year, month, day);
	}
	
	Point dateBounds = getSize ();
	Point calendarSize = popupCalendar.computeSize (SWT.DEFAULT, SWT.DEFAULT, false);
	popupCalendar.setBounds (1, 1, Math.max (dateBounds.x - 2, calendarSize.x), calendarSize.y);
	
	int year = popupCalendar.getYear ();
	int month = popupCalendar.getMonth ();
	int day = popupCalendar.getDay ();
	popupCalendar.setDate(year, month, day);
	Display display = getDisplay ();
	Rectangle parentRect = display.map (getParent (), null, getBounds ());
	Rectangle displayRect = getMonitor ().getClientArea ();
	int width = Math.max (dateBounds.x, calendarSize.x + 2);
	int height = calendarSize.y + 2;
	int x = parentRect.x;
	int y = parentRect.y + dateBounds.y;
	if (y + height > displayRect.y + displayRect.height) y = parentRect.y - height;
	if (x + width > displayRect.x + displayRect.width) x = displayRect.x + displayRect.width - calendarSize.x;
	popupShell.setBounds (x, y, width, height);
	popupShell.setVisible (true);
	if (isFocusControl()) popupCalendar.setFocus ();
}

Point getCellSize(GC gc) {
	boolean dispose = gc == null; 
	if (gc == null) gc = new GC(this);
	int width = 0, height = 0;
	String[] days = formatSymbols.getShortWeekdays();
	for (int i = 0; i < days.length; i++) {
		Point extent = gc.stringExtent(days[i]);
		width = Math.max(width, extent.x);
		height = Math.max(height, extent.y);
	}
	int firstDay = calendar.getMinimum(Calendar.DAY_OF_MONTH);
	int lastDay = calendar.getMaximum(Calendar.DAY_OF_MONTH);
	for (int day = firstDay; day <= lastDay; day++) {
		Point extent = gc.stringExtent(String.valueOf(day));
		width = Math.max(width, extent.x);
		height = Math.max(height, extent.y);	
	}
	if (dispose) gc.dispose();
	return new Point(width + MARGIN_WIDTH * 2, height + MARGIN_HEIGHT * 2);
}

Point getCellLocation(int cell, Point cellSize) {
	return new Point(cell % 7 * cellSize.x, cell / 7 * cellSize.y);
}

int getCell(int date) {
	int day = calendar.get(Calendar.DAY_OF_MONTH);
	calendar.set(Calendar.DAY_OF_MONTH, 1);
	int result = date + calendar.get(Calendar.DAY_OF_WEEK) + 5;
	calendar.set(Calendar.DAY_OF_MONTH, day);
	return result;
}

void getDate() {
	dateRec = new LongDateRec ();
	OS.GetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, dateRec, null);
}

int getDate(int cell) {
	int day = calendar.get(Calendar.DAY_OF_MONTH);
	calendar.set(Calendar.DAY_OF_MONTH, 1);
	int result = cell - calendar.get(Calendar.DAY_OF_WEEK) - 5;
	calendar.set(Calendar.DAY_OF_MONTH, day);
	return result;
}

public Color getBackground() {
	checkWidget();
	if (bg == null) {
		return getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND);
	}
	return bg;
}

/**
 * Returns the receiver's date, or day of the month.
 * <p>
 * The first day of the month is 1, and the last day depends on the month and year.
 * </p>
 *
 * @return a positive integer beginning with 1
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public int getDay () {
	checkWidget ();
	if ((style & SWT.TIME) != 0) return dateAndTime.day;
	if ((style & SWT.CALENDAR) != 0) {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	getDate();
	return dateRec.day;
}

public Color getForeground() {
	checkWidget();
	if (fg == null) {
		return getDisplay().getSystemColor(SWT.COLOR_LIST_FOREGROUND);
	}
	return fg;
}

/**
 * Returns the receiver's hours.
 * <p>
 * Hours is an integer between 0 and 23.
 * </p>
 *
 * @return an integer between 0 and 23
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public int getHours () {
	checkWidget ();
	if ((style & SWT.DATE) != 0) return dateAndTime.hour;
	if ((style & SWT.CALENDAR) != 0) {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	getDate();
	return dateRec.hour;
}

/**
 * Returns the receiver's minutes.
 * <p>
 * Minutes is an integer between 0 and 59.
 * </p>
 *
 * @return an integer between 0 and 59
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public int getMinutes () {
	checkWidget ();
	if ((style & SWT.DATE) != 0) return dateAndTime.minute;
	if ((style & SWT.CALENDAR) != 0) {
		return calendar.get(Calendar.MINUTE);
	}
	getDate();
	return dateRec.minute;
}

/**
 * Returns the receiver's month.
 * <p>
 * The first month of the year is 0, and the last month is 11.
 * </p>
 *
 * @return an integer between 0 and 11
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public int getMonth () {
	checkWidget ();
	if ((style & SWT.TIME) != 0) return dateAndTime.month - 1;
	if ((style & SWT.CALENDAR) != 0) {
		return calendar.get(Calendar.MONTH);
	}
	getDate();
	return dateRec.month - 1;
}

String getNameText() {
	return (style & SWT.TIME) != 0 ? getHours() + ":" + getMinutes() + ":" + getSeconds()
			: (getMonth() + 1) + "/" + getDay() + "/" + getYear();
}

/**
 * Returns the receiver's seconds.
 * <p>
 * Seconds is an integer between 0 and 59.
 * </p>
 *
 * @return an integer between 0 and 59
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public int getSeconds () {
	checkWidget ();
	if ((style & SWT.DATE) != 0) return dateAndTime.second;
	if ((style & SWT.CALENDAR) != 0) {
		return calendar.get(Calendar.SECOND);
	}
	getDate();
	return dateRec.second;
}

/*
 * Returns a textual representation of the receiver,
 * intended for speaking the text aloud.
 */
String getSpokenText () {
	// TODO: needs more work for locale
	StringBuffer result = new StringBuffer ();
	if ((style & SWT.TIME) != 0) {
		int h = calendar.get(Calendar.HOUR); if (h == 0) h = 12;
		result.append(h);
		int m = calendar.get(Calendar.MINUTE);
		result.append(":" + (m < 10 ? "0" : "") + m);
		if ((style & SWT.SHORT) == 0) {
			int s = calendar.get(Calendar.SECOND);
			result.append(":" + (s < 10 ? "0" : "") + s);
		}
		result.append(" " + formatSymbols.getAmPmStrings()[calendar.get(Calendar.AM_PM)]);
	} else {
		/* SWT.DATE or SWT.CALENDAR */
		if ((style & SWT.SHORT) == 0) {
			result.append(formatSymbols.getWeekdays()[calendar.get(Calendar.DAY_OF_WEEK)] + ", ");
		}
		result.append(formatSymbols.getMonths()[calendar.get(Calendar.MONTH)] + " ");
		if ((style & SWT.SHORT) == 0) {
			result.append(calendar.get(Calendar.DAY_OF_MONTH) + ", ");
		}
		result.append(calendar.get(Calendar.YEAR));
	}
	return result.toString();
}

/**
 * Returns the receiver's year.
 * <p>
 * The first year is 1752 and the last year is 9999.
 * </p>
 *
 * @return an integer between 1752 and 9999
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public int getYear () {
	checkWidget ();
	if ((style & SWT.TIME) != 0) return dateAndTime.year;
	if ((style & SWT.CALENDAR) != 0) {
		return calendar.get(Calendar.YEAR);
	}
	getDate();
	return dateRec.year;
}

void hookEvents () {
	super.hookEvents ();
	if (OS.VERSION >= 0x1040) {
		int clockProc = display.clockProc;
		int [] mask = new int [] {
			OS.kEventClassClockView, OS.kEventClockDateOrTimeChanged,
		};
		int controlTarget = OS.GetControlEventTarget (handle);
		OS.InstallEventHandler (controlTarget, clockProc, mask.length / 2, mask, handle, null);
	}
}

void initAccessible() {
	Accessible accessible = getAccessible ();
	accessible.addAccessibleListener (new AccessibleAdapter () {
		public void getName (AccessibleEvent e) {
			e.result = getSpokenText ();
		}

		public void getHelp(AccessibleEvent e) {
			e.result = getToolTipText ();
		}
	});

	accessible.addAccessibleControlListener (new AccessibleControlAdapter () {
		public void getChildAtPoint (AccessibleControlEvent e) {
			e.childID = ACC.CHILDID_SELF;
		}
		
		public void getLocation (AccessibleControlEvent e) {
			Rectangle rect = display.map (getParent (), null, getBounds ());
			e.x = rect.x;
			e.y = rect.y;
			e.width = rect.width;
			e.height = rect.height;
		}
		
		public void getChildCount (AccessibleControlEvent e) {
			e.detail = 0;
		}
		
		public void getRole (AccessibleControlEvent e) {
			e.detail = ((style & SWT.CALENDAR) != 0) ? ACC.ROLE_LABEL : ACC.ROLE_TEXT;
		}
		
		public void getState (AccessibleControlEvent e) {
			e.detail = ACC.STATE_FOCUSABLE;
			if (hasFocus ()) e.detail |= ACC.STATE_FOCUSED;
		}
		
		public void getSelection (AccessibleControlEvent e) {
			if (hasFocus ()) e.childID = ACC.CHILDID_SELF;
		}
		
		public void getFocus (AccessibleControlEvent e) {
			if (hasFocus ()) e.childID = ACC.CHILDID_SELF;
		}
	});
}

void internalLayout (boolean changed) {
	if (isDropped ()) dropDownCalendar (false);
	Rectangle rect = getClientArea ();
	int width = rect.width;
	int height = rect.height;
	Point arrowSize = down.computeSize (SWT.DEFAULT, height, changed);
	setBounds (0, 0, width - arrowSize.x, height);
	down.setBounds (width - arrowSize.x, 0, arrowSize.x, arrowSize.y);
}

boolean isDropped () {
	return popupShell.getVisible ();
}

public boolean isFocusControl () {
	checkWidget();
	if ((style & SWT.DROP_DOWN) != 0 && (isFocusControl () || down.isFocusControl () || popupShell.isFocusControl ())) {
		return true;
	} 
	return super.isFocusControl ();
}

boolean isValidTime(int fieldName, int value) {
	Calendar calendar = Calendar.getInstance();
	int min = calendar.getActualMinimum(fieldName);
	int max = calendar.getActualMaximum(fieldName);
	return value >= min && value <= max;
}

boolean isValidDate(int year, int month, int day) {
	if (year < MIN_YEAR || year > MAX_YEAR) return false;
	Calendar calendar = Calendar.getInstance();
	calendar.set(year, month, day);
	return calendar.get(Calendar.YEAR) == year
		&& calendar.get(Calendar.MONTH) == month
		&& calendar.get(Calendar.DAY_OF_MONTH) == day;
}

int kEventClockDateOrTimeChanged (int nextHandler, int theEvent, int userData) {
	sendSelectionEvent ();
	return OS.noErr;
}

int kEventControlHit (int nextHandler, int theEvent, int userData) {
	int result = super.kEventControlHit (nextHandler, theEvent, userData);
	if (result == OS.noErr) return result;
	if (OS.VERSION < 0x1040) sendSelectionEvent ();
	return result;
}

int kEventTextInputUnicodeForKeyEvent (int nextHandler, int theEvent, int userData) {
	int result = super.kEventTextInputUnicodeForKeyEvent (nextHandler, theEvent, userData);
	if (result == OS.noErr) return result;
	if (OS.VERSION < 0x1040) sendSelectionEvent ();
	return result;
}

boolean pollTrackEvent() {
	return ((style & SWT.DATE) != 0) || ((style & SWT.TIME) != 0);
}

void popupCalendarEvent (Event event) {
	switch (event.type) {
		case SWT.Dispose:
			if (popupShell != null && !popupShell.isDisposed () && !isDisposed() && getShell () != popupShell.getParent ()) {
				int year = popupCalendar.getYear ();
				int month = popupCalendar.getMonth ();
				int day = popupCalendar.getDay ();
				popupShell = null;
				popupCalendar = null;
				createPopupShell (year, month, day);
			}
			break;
		case SWT.FocusIn: {
			popupCalendarFocus (SWT.FocusIn);
			break;
		}
		case SWT.MouseUp: {
			if (event.button != 1) return;
			dropDownCalendar (false);
			break;
		}
		case SWT.Selection: {
			int year = popupCalendar.getYear ();
			int month = popupCalendar.getMonth ();
			int day = popupCalendar.getDay ();
			setDate(year, month, day);
			Event e = new Event ();
			e.time = event.time;
			e.stateMask = event.stateMask;
			e.doit = event.doit;
			notifyListeners (SWT.Selection, e);
			event.doit = e.doit;
			break;
		}
		case SWT.Traverse: {
			switch (event.detail) {
				case SWT.TRAVERSE_RETURN:
				case SWT.TRAVERSE_ESCAPE:
				case SWT.TRAVERSE_ARROW_PREVIOUS:
				case SWT.TRAVERSE_ARROW_NEXT:
					event.doit = false;
					break;
				case SWT.TRAVERSE_TAB_NEXT:
				case SWT.TRAVERSE_TAB_PREVIOUS:
					event.doit = traverse(event.detail);
					event.detail = SWT.TRAVERSE_NONE;
					if (event.doit) dropDownCalendar (false);
					return;
				case SWT.TRAVERSE_PAGE_NEXT:
				case SWT.TRAVERSE_PAGE_PREVIOUS:
					return;
			}
			Event e = new Event ();
			e.time = event.time;
			e.detail = event.detail;
			e.doit = event.doit;
			e.character = event.character;
			e.keyCode = event.keyCode;
			notifyListeners (SWT.Traverse, e);
			event.doit = e.doit;
			event.detail = e.detail;
			break;
		}
		case SWT.KeyUp: {		
			Event e = new Event ();
			e.time = event.time;
			e.character = event.character;
			e.keyCode = event.keyCode;
			e.stateMask = event.stateMask;
			notifyListeners (SWT.KeyUp, e);
			break;
		}
		case SWT.KeyDown: {
			if (event.character == SWT.ESC) {
				/* Escape key cancels popupCalendar and reverts date */
				popupCalendar.setDate (savedYear, savedMonth, savedDay);
				dropDownCalendar (false);
			}
			if (event.keyCode == SWT.CR || (event.stateMask & SWT.ALT) != 0 && (event.keyCode == SWT.ARROW_UP || event.keyCode == SWT.ARROW_DOWN)) {
				/* Return, Alt+Down, and Alt+Up cancel popupCalendar and select date. */
				dropDownCalendar (false);
			}
			/* At this point the widget may have been disposed.
			 * If so, do not continue. */
			if (isDisposed ()) break;
			Event e = new Event();
			e.time = event.time;
			e.character = event.character;
			e.keyCode = event.keyCode;
			e.stateMask = event.stateMask;
			notifyListeners(SWT.KeyDown, e);
			break;
		}
	}
}

void popupCalendarFocus (int type) {
	if (isDisposed ()) return;
	switch (type) {
		case SWT.FocusIn: {
			if (hasFocus) return;
			hasFocus = true;
			Shell shell = getShell ();
			shell.removeListener (SWT.Deactivate, popupListener);
			shell.addListener (SWT.Deactivate, popupListener);
			Display display = getDisplay ();
			display.removeFilter (SWT.FocusIn, popupFilter);
			display.addFilter (SWT.FocusIn, popupFilter);
			Event e = new Event ();
			notifyListeners (SWT.FocusIn, e);
			break;
		}
		case SWT.FocusOut: {
			if (!hasFocus) return;
			Control focusControl = getDisplay ().getFocusControl ();
			if (focusControl == down || focusControl == popupCalendar || focusControl == this) return;
			hasFocus = false;
			Shell shell = getShell ();
			shell.removeListener(SWT.Deactivate, popupListener);
			Display display = getDisplay ();
			display.removeFilter (SWT.FocusIn, popupFilter);
			Event e = new Event ();
			notifyListeners (SWT.FocusOut, e);
			break;
		}
	}
}

void popupShellEvent(Event event) {
	switch (event.type) {
		case SWT.Paint:
			/* Draw black rectangle around popupCalendar */
			Rectangle bounds = popupCalendar.getBounds();
			Color black = getDisplay().getSystemColor(SWT.COLOR_BLACK);
			event.gc.setForeground(black);
			event.gc.drawRectangle(0, 0, bounds.width + 1, bounds.height + 1);
			break;
		case SWT.Close:
			event.doit = false;
			dropDownCalendar (false);
			break;
		case SWT.Deactivate:
			/*
			 * Feature in Carbon. When the arrow button is pressed the popup control receives
			 * a deactivate event and then the arrow button receives a selection event. 
			 * If we hide the popup in the deactivate event, the selection event will show 
			 * it again. To prevent the popup from showing again, we will let the selection 
			 * event of the arrow button hide the popup.
			 */
			dropDownCalendar(false);
			break;
	}
}

void redraw(int cell, Point cellSize) {
	Point location = getCellLocation(cell, cellSize);
	redraw(location.x, location.y, cellSize.x, cellSize.y, false);	
}

void releaseWidget () {
	super.releaseWidget();
	dateRec = null;
}

/**
 * Removes the listener from the collection of listeners who will
 * be notified when the control is selected by the user.
 *
 * @param listener the listener which should no longer be notified
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @see SelectionListener
 * @see #addSelectionListener
 */
public void removeSelectionListener (SelectionListener listener) {
	checkWidget ();
	if (listener == null) error (SWT.ERROR_NULL_ARGUMENT);
	if (eventTable == null) return;
	eventTable.unhook (SWT.Selection, listener);
	eventTable.unhook (SWT.DefaultSelection, listener);	
}

void sendSelectionEvent () {
	LongDateRec rec = new LongDateRec ();
	OS.GetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, rec, null);
	if (rec.second != dateRec.second ||
		rec.minute != dateRec.minute ||
		rec.hour != dateRec.hour ||
		rec.day != dateRec.day ||
		rec.month != dateRec.month ||
		rec.year != dateRec.year) {
		dateRec = rec;
		postEvent (SWT.Selection);
	}
}

public void setBackground(Color color) {
	checkWidget();
	super.setBackground(color);
	bg = color;
}

public void setForeground(Color color) {
	checkWidget();
	super.setForeground(color);
	fg = color;
}

/**
 * Sets the receiver's year, month, and day in a single operation.
 * <p>
 * This is the recommended way to set the date, because setting the year,
 * month, and day separately may result in invalid intermediate dates.
 * </p>
 *
 * @param year an integer between 1752 and 9999
 * @param month an integer between 0 and 11
 * @param day a positive integer beginning with 1
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @since 3.4
 */
public void setDate (int year, int month, int day) {
	checkWidget ();
	if (!isValidDate(year, month, day)) return;
	if ((style & SWT.CALENDAR) != 0) {
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, month);
		redraw();
		setDay(day, false);
	} else {
		dateRec.year = (short)year;
		dateRec.month = (short)(month + 1);
		dateRec.day = (short)day;
		OS.SetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, dateRec);
		OS.GetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, dateRec, null);
		redraw();
		if ((style & SWT.TIME) != 0) {
			dateAndTime.year = (short)year;
			dateAndTime.month = (short)(month + 1);
			dateAndTime.day = (short)day;
		}
	}
}

/**
 * Sets the receiver's date, or day of the month, to the specified day.
 * <p>
 * The first day of the month is 1, and the last day depends on the month and year.
 * </p>
 *
 * @param day a positive integer beginning with 1
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void setDay (int day) {
	checkWidget ();
	if (!isValidDate(getYear(), getMonth(), day)) return;
	if ((style & SWT.CALENDAR) != 0) {
		setDay(day, false);
	} else {
		dateRec.day = (short)day;
		OS.SetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, dateRec);
		OS.GetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, dateRec, null);
		if ((style & SWT.TIME) != 0) dateAndTime.day = (short)day;
		redraw();
	}
}

void setDay (int newDay, boolean notify) {
	int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
	int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	if (!(firstDay <= newDay && newDay <= lastDay)) return;
	Point cellSize = getCellSize(null);
	redraw(getCell(calendar.get(Calendar.DAY_OF_MONTH)), cellSize);
	calendar.set(Calendar.DAY_OF_MONTH, newDay);
	redraw(getCell(calendar.get(Calendar.DAY_OF_MONTH)), cellSize);
	if (notify) postEvent(SWT.Selection);
	getAccessible().selectionChanged();
}

/**
 * Sets the receiver's hours.
 * <p>
 * Hours is an integer between 0 and 23.
 * </p>
 *
 * @param hours an integer between 0 and 23
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void setHours (int hours) {
	checkWidget ();
	if (!isValidTime(Calendar.HOUR_OF_DAY, hours)) return;
	if ((style & SWT.CALENDAR) != 0) {
		calendar.set(Calendar.HOUR_OF_DAY, hours);
	} else {
		dateRec.hour = (short)hours;
		OS.SetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, dateRec);
		OS.GetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, dateRec, null);
		if ((style & SWT.DATE) != 0) dateAndTime.hour = (short)hours;
	}
	redraw();
}

/**
 * Sets the receiver's minutes.
 * <p>
 * Minutes is an integer between 0 and 59.
 * </p>
 *
 * @param minutes an integer between 0 and 59
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void setMinutes (int minutes) {
	checkWidget ();
	if (!isValidTime(Calendar.MINUTE, minutes)) return;
	if ((style & SWT.CALENDAR) != 0) {
		calendar.set(Calendar.MINUTE, minutes);
	} else {
		dateRec.minute = (short)minutes;
		OS.SetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, dateRec);
		OS.GetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, dateRec, null);
		if ((style & SWT.DATE) != 0) dateAndTime.minute = (short)minutes;
	}
	redraw();
}

/**
 * Sets the receiver's month.
 * <p>
 * The first month of the year is 0, and the last month is 11.
 * </p>
 *
 * @param month an integer between 0 and 11
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void setMonth (int month) {
	checkWidget ();
	if (!isValidDate(getYear(), month, getDay())) return;
	if ((style & SWT.CALENDAR) != 0) {
		calendar.set(Calendar.MONTH, month);
	} else {
		dateRec.month = (short)(month + 1);
		OS.SetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, dateRec);
		OS.GetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, dateRec, null);
		if ((style & SWT.TIME) != 0) dateAndTime.month = (short)(month + 1);
	}
	redraw();
}

/**
 * Sets the receiver's seconds.
 * <p>
 * Seconds is an integer between 0 and 59.
 * </p>
 *
 * @param seconds an integer between 0 and 59
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void setSeconds (int seconds) {
	checkWidget ();
	if (!isValidTime(Calendar.SECOND, seconds)) return;
	if ((style & SWT.CALENDAR) != 0) {
		calendar.set(Calendar.SECOND, seconds);
	} else {
		dateRec.second = (short)seconds;
		OS.SetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, dateRec);
		OS.GetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, dateRec, null);
		if ((style & SWT.DATE) != 0) dateAndTime.second = (short)seconds;
	}
	redraw();
}

/**
 * Sets the receiver's hours, minutes, and seconds in a single operation.
 *
 * @param hours an integer between 0 and 23
 * @param minutes an integer between 0 and 59
 * @param seconds an integer between 0 and 59
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @since 3.4
 */
public void setTime (int hours, int minutes, int seconds) {
	checkWidget ();
	if (!isValidTime(Calendar.HOUR_OF_DAY, hours)) return;
	if (!isValidTime(Calendar.MINUTE, minutes)) return;
	if (!isValidTime(Calendar.SECOND, seconds)) return;
	if ((style & SWT.CALENDAR) != 0) {
		calendar.set(Calendar.HOUR_OF_DAY, hours);
		calendar.set(Calendar.MINUTE, minutes);
		calendar.set(Calendar.SECOND, seconds);
	} else {
		dateRec.hour = (short)hours;
		dateRec.minute = (short)minutes;
		dateRec.second = (short)seconds;
		OS.SetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, dateRec);
		OS.GetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, dateRec, null);
		if ((style & SWT.DATE) != 0) {
			dateAndTime.hour = (short)hours;
			dateAndTime.minute = (short)minutes;
			dateAndTime.second = (short)seconds;
		}
	}
	redraw();
}

/**
 * Sets the receiver's year.
 * <p>
 * The first year is 1752 and the last year is 9999.
 * </p>
 *
 * @param year an integer between 1752 and 9999
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void setYear (int year) {
	checkWidget ();
	if (!isValidDate(year, getMonth(), getDay())) return;
	if ((style & SWT.CALENDAR) != 0) {
		calendar.set(Calendar.YEAR, year);
	} else {
		dateRec.year = (short)year;
		OS.SetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, dateRec);
		OS.GetControlData (handle, (short)OS.kControlEntireControl, OS.kControlClockLongDateTag, LongDateRec.sizeof, dateRec, null);
		if ((style & SWT.TIME) != 0) dateAndTime.year = (short)year;
	}
	redraw();
}
}
