/*******************************************************************************
 * Copyright (c) 2000, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.printing;


import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.qt.*;
import org.eclipse.swt.printing.PrinterData;

/**
 * Instances of this class are used to print to a printer.
 * Applications create a GC on a printer using <code>new GC(printer)</code>
 * and then draw on the printer GC using the usual graphics calls.
 * <p>
 * A <code>Printer</code> object may be constructed by providing
 * a <code>PrinterData</code> object which identifies the printer.
 * A <code>PrintDialog</code> presents a print dialog to the user
 * and returns an initialized instance of <code>PrinterData</code>.
 * Alternatively, calling <code>new Printer()</code> will construct a
 * printer object for the user's default printer.
 * </p><p>
 * Application code must explicitly invoke the <code>Printer.dispose()</code> 
 * method to release the operating system resources managed by each instance
 * when those instances are no longer required.
 * </p>
 *
 * @see PrinterData
 * @see PrintDialog
 * @see <a href="http://www.eclipse.org/swt/snippets/#printing">Printing snippets</a>
 * @see <a href="http://www.eclipse.org/swt/">Sample code and further information</a>
 */
public final class Printer extends Device {
	
static void qt_init() {
	// TODO: Implement!
	QtUtils.notImplemented();
}

/**
 * Returns an array of <code>PrinterData</code> objects
 * representing all available printers.  If there are no
 * printers, the array will be empty.
 *
 * @return an array of PrinterData objects representing the available printers
 */
public static PrinterData[] getPrinterList() {
	// TODO: Implement!
	QtUtils.notImplemented();
	return null;
}

/**
 * Returns a <code>PrinterData</code> object representing
 * the default printer or <code>null</code> if there is no 
 * default printer.
 *
 * @return the default printer data or null
 * 
 * @since 2.1
 */
public static PrinterData getDefaultPrinterData() {
	// TODO: Implement!
	QtUtils.notImplemented();
	return null;
}

/**
 * Constructs a new printer representing the default printer.
 * <p>
 * Note: You must dispose the printer when it is no longer required. 
 * </p>
 *
 * @exception SWTError <ul>
 *    <li>ERROR_NO_HANDLES - if there is no valid default printer
 * </ul>
 *
 * @see Device#dispose
 */
public Printer() {
	this(null);
}

/**
 * Constructs a new printer given a <code>PrinterData</code>
 * object representing the desired printer. If the argument
 * is null, then the default printer will be used.
 * <p>
 * Note: You must dispose the printer when it is no longer required. 
 * </p>
 *
 * @param data the printer data for the specified printer, or null to use the default printer
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_INVALID_ARGUMENT - if the specified printer data does not represent a valid printer
 * </ul>
 * @exception SWTError <ul>
 *    <li>ERROR_NO_HANDLES - if there are no valid printers
 * </ul>
 *
 * @see Device#dispose
 */
public Printer(PrinterData data) {
	// TODO: Implement!
	super(data);
	QtUtils.notImplemented();
}

/**	 
 * Invokes platform specific functionality to allocate a new GC handle.
 * <p>
 * <b>IMPORTANT:</b> This method is <em>not</em> part of the public
 * API for <code>Printer</code>. It is marked public only so that it
 * can be shared within the packages provided by SWT. It is not
 * available on all platforms, and should never be called from
 * application code.
 * </p>
 *
 * @param data the platform specific GC data 
 * @return the platform specific GC handle
 * 
 * @noreference This method is not intended to be referenced by clients.
 */
public long /*int*/ internal_new_GC(GCData data) {
	// TODO: Implement!
	QtUtils.notImplemented();
	return 0;
}

/**	 
 * Invokes platform specific functionality to dispose a GC handle.
 * <p>
 * <b>IMPORTANT:</b> This method is <em>not</em> part of the public
 * API for <code>Printer</code>. It is marked public only so that it
 * can be shared within the packages provided by SWT. It is not
 * available on all platforms, and should never be called from
 * application code.
 * </p>
 *
 * @param hDC the platform specific GC handle
 * @param data the platform specific GC data 
 * 
 * @noreference This method is not intended to be referenced by clients.
 */
public void internal_dispose_GC(long /*int*/ hDC, GCData data) {
	// TODO: Implement!
	QtUtils.notImplemented();
}

/**	 
 * Releases any internal state prior to destroying this printer.
 * This method is called internally by the dispose
 * mechanism of the <code>Device</code> class.
 */
protected void release () {
	// TODO: Implement!
	QtUtils.notImplemented();
}

/**
 * Starts a print job and returns true if the job started successfully
 * and false otherwise.
 * <p>
 * This must be the first method called to initiate a print job,
 * followed by any number of startPage/endPage calls, followed by
 * endJob. Calling startPage, endPage, or endJob before startJob
 * will result in undefined behavior.
 * </p>
 * 
 * @param jobName the name of the print job to start
 * @return true if the job started successfully and false otherwise.
 *
 * @exception SWTException <ul>
 *    <li>ERROR_DEVICE_DISPOSED - if the receiver has been disposed</li>
 * </ul>
 *
 * @see #startPage
 * @see #endPage
 * @see #endJob
 */
public boolean startJob(String jobName) {
	// TODO: Implement!
	QtUtils.notImplemented();
	return false;
}

/**	 
 * Destroys the printer handle.
 * This method is called internally by the dispose
 * mechanism of the <code>Device</code> class.
 */
protected void destroy () {
	// TODO: Implement!
	QtUtils.notImplemented();
}

/**
 * Ends the current print job.
 *
 * @exception SWTException <ul>
 *    <li>ERROR_DEVICE_DISPOSED - if the receiver has been disposed</li>
 * </ul>
 *
 * @see #startJob
 * @see #startPage
 * @see #endPage
 */
public void endJob() {
	// TODO: Implement!
	QtUtils.notImplemented();
}

/**
 * Cancels a print job in progress. 
 *
 * @exception SWTException <ul>
 *    <li>ERROR_DEVICE_DISPOSED - if the receiver has been disposed</li>
 * </ul>
 */
public void cancelJob() {
	// TODO: Implement!
	QtUtils.notImplemented();
}

/**
 * Starts a page and returns true if the page started successfully
 * and false otherwise.
 * <p>
 * After calling startJob, this method may be called any number of times
 * along with a matching endPage.
 * </p>
 * 
 * @return true if the page started successfully and false otherwise.
 *
 * @exception SWTException <ul>
 *    <li>ERROR_DEVICE_DISPOSED - if the receiver has been disposed</li>
 * </ul>
 *
 * @see #endPage
 * @see #startJob
 * @see #endJob
 */
public boolean startPage() {
	// TODO: Implement!
	QtUtils.notImplemented();
	return false;
}

/**
 * Ends the current page.
 *
 * @exception SWTException <ul>
 *    <li>ERROR_DEVICE_DISPOSED - if the receiver has been disposed</li>
 * </ul>
 *
 * @see #startPage
 * @see #startJob
 * @see #endJob
 */
public void endPage() {
	// TODO: Implement!
	QtUtils.notImplemented();
}

/**
 * Returns a point whose x coordinate is the horizontal
 * dots per inch of the printer, and whose y coordinate
 * is the vertical dots per inch of the printer.
 *
 * @return the horizontal and vertical DPI
 *
 * @exception SWTException <ul>
 *    <li>ERROR_DEVICE_DISPOSED - if the receiver has been disposed</li>
 * </ul>
 */
public Point getDPI() {
	// TODO: Implement!
	QtUtils.notImplemented();
	return null;
}

/**
 * Returns a rectangle describing the receiver's size and location.
 * <p>
 * For a printer, this is the size of the physical page, in pixels.
 * </p>
 *
 * @return the bounding rectangle
 *
 * @exception SWTException <ul>
 *    <li>ERROR_DEVICE_DISPOSED - if the receiver has been disposed</li>
 * </ul>
 *
 * @see #getClientArea
 * @see #computeTrim
 */
public Rectangle getBounds() {
	// TODO: Implement!
	QtUtils.notImplemented();
	return null;
}

/**
 * Returns a rectangle which describes the area of the
 * receiver which is capable of displaying data.
 * <p>
 * For a printer, this is the size of the printable area
 * of the page, in pixels.
 * </p>
 * 
 * @return the client area
 *
 * @exception SWTException <ul>
 *    <li>ERROR_DEVICE_DISPOSED - if the receiver has been disposed</li>
 * </ul>
 *
 * @see #getBounds
 * @see #computeTrim
 */
public Rectangle getClientArea() {
	// TODO: Implement!
	QtUtils.notImplemented();
	return null;
}

/**
 * Given a <em>client area</em> (as described by the arguments),
 * returns a rectangle, relative to the client area's coordinates,
 * that is the client area expanded by the printer's trim (or minimum margins).
 * <p>
 * Most printers have a minimum margin on each edge of the paper where the
 * printer device is unable to print.  This margin is known as the "trim."
 * This method can be used to calculate the printer's minimum margins
 * by passing in a client area of 0, 0, 0, 0 and then using the resulting
 * x and y coordinates (which will be <= 0) to determine the minimum margins
 * for the top and left edges of the paper, and the resulting width and height
 * (offset by the resulting x and y) to determine the minimum margins for the
 * bottom and right edges of the paper, as follows:
 * <ul>
 * 		<li>The left trim width is -x pixels</li>
 * 		<li>The top trim height is -y pixels</li>
 * 		<li>The right trim width is (x + width) pixels</li>
 * 		<li>The bottom trim height is (y + height) pixels</li>
 * </ul>
 * </p>
 * 
 * @param x the x coordinate of the client area
 * @param y the y coordinate of the client area
 * @param width the width of the client area
 * @param height the height of the client area
 * @return a rectangle, relative to the client area's coordinates, that is
 * 		the client area expanded by the printer's trim (or minimum margins)
 *
 * @exception SWTException <ul>
 *    <li>ERROR_DEVICE_DISPOSED - if the receiver has been disposed</li>
 * </ul>
 *
 * @see #getBounds
 * @see #getClientArea
 */
public Rectangle computeTrim(int x, int y, int width, int height) {
	// TODO: Implement!
	QtUtils.notImplemented();
	return null;
}

/**	 
 * Creates the printer handle.
 * This method is called internally by the instance creation
 * mechanism of the <code>Device</code> class.
 * @param deviceData the device data
 */
protected void create(DeviceData deviceData) {
	// TODO: Implement!
	QtUtils.notImplemented();
}

/**
 * Initializes any internal resources needed by the
 * device.
 * <p>
 * This method is called after <code>create</code>.
 * </p><p>
 * If subclasses reimplement this method, they must
 * call the <code>super</code> implementation.
 * </p>
 * 
 * @see #create
 */
protected void init() {
	// TODO: Implement!
	QtUtils.notImplemented();
}

/**
 * Returns a <code>PrinterData</code> object representing the
 * target printer for this print job.
 * 
 * @return a PrinterData object describing the receiver
 */
public PrinterData getPrinterData() {
	// TODO: Implement!
	QtUtils.notImplemented();
	return null;
}

}
