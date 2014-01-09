/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others. All rights reserved.
 * The contents of this file are made available under the terms
 * of the GNU Lesser General Public License (LGPL) Version 2.1 that
 * accompanies this distribution (lgpl-v21.txt).  The LGPL is also
 * available at http://www.gnu.org/licenses/lgpl.html.  If the version
 * of the LGPL at http://www.gnu.org is different to the version of
 * the LGPL accompanying this distribution and there is any conflict
 * between the two license versions, the terms of the LGPL accompanying
 * this distribution shall govern.
 * 
 * Contributors:
 *     Ingo Weinhold
 *******************************************************************************/
package org.eclipse.swt.internal.qt;

public class QtUtils {
	private static boolean debug = Boolean.parseBoolean(System.getProperty("org.eclipse.swt.qt.debug")); //$NON-NLS-1$

	public static void notImplemented() {
		if (debug) {
			StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
			String caller = stackTrace.length >= 2 ? stackTrace[1].toString() : "???";
			System.err.println("XXX Qt: missing implementation: " + caller);
		}
	}

	public static void partiallyImplemented() {
		if (debug) {
			StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
			String caller = stackTrace.length >= 2 ? stackTrace[1].toString() : "???";
			System.err.println("XXX Qt: partial implementation: " + caller);
		}
	}

	public static void missingFeature(String featureName) {
		if (debug) {
			StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
			String caller = stackTrace.length >= 2 ? stackTrace[1].toString() : "???";
			System.err.println("XXX Qt: missing feature \"" + featureName + "\": " + caller);
		}
	}
}
