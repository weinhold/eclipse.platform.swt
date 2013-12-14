/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others. All rights reserved.
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
package org.eclipse.swt.internal.haiku;

public class HaikuUtils {
	public static void notImplemented() {
		StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
		String caller = stackTrace.length >= 2 ? stackTrace[1].toString() : "???";
		System.err.println("XXX Haiku: missing implementation: " + caller);
	}

	public static void partiallyImplemented() {
		StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
		String caller = stackTrace.length >= 2 ? stackTrace[1].toString() : "???";
		System.err.println("XXX Haiku: partial implementation: " + caller);
	}

	public static void missingFeature(String featureName) {
		StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
		String caller = stackTrace.length >= 2 ? stackTrace[1].toString() : "???";
		System.err.println("XXX Haiku: missing feature \"" + featureName + "\": " + caller);
	}
}
