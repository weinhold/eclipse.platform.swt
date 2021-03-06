/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.tests.junit;

import junit.framework.*;
import junit.textui.*;

import org.eclipse.swt.*;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.widgets.*;

/**
 * Automated Test Suite for class org.eclipse.swt.browser.LocationAdapter
 *
 * @see org.eclipse.swt.browser.LocationAdapter
 */
public class Test_org_eclipse_swt_browser_LocationAdapter extends SwtTestCase {

public Test_org_eclipse_swt_browser_LocationAdapter(String name) {
	super(name);
}

public static void main(String[] args) {
	TestRunner.run(suite());
}

public void test_Constructor() {
	new LocationAdapter() {};
}

public void test_changedLorg_eclipse_swt_browser_LocationEvent() {
	Display display = Display.getCurrent();
	Shell shell = new Shell(display);
	Browser browser = new Browser(shell, SWT.NONE);
	LocationAdapter adapter = new LocationAdapter() {	
	};
	browser.addLocationListener(adapter);
	shell.close();
}

public void test_changingLorg_eclipse_swt_browser_LocationEvent() {
	// tested in test_changedLorg_eclipse_swt_browser_LocationEvent
}


public static Test suite() {
	TestSuite suite = new TestSuite();
	java.util.Vector<String> methodNames = methodNames();
	java.util.Enumeration<String> e = methodNames.elements();
	while (e.hasMoreElements()) {
		suite.addTest(new Test_org_eclipse_swt_browser_LocationAdapter(e.nextElement()));
	}
	return suite;
}

public static java.util.Vector<String> methodNames() {
	java.util.Vector<String> methodNames = new java.util.Vector<String>();
	methodNames.addElement("test_Constructor");
	methodNames.addElement("test_changedLorg_eclipse_swt_browser_LocationEvent");
	methodNames.addElement("test_changingLorg_eclipse_swt_browser_LocationEvent");
	return methodNames;
}

@Override
protected void runTest() throws Throwable {
	if (getName().equals("test_Constructor")) test_Constructor();
	else if (getName().equals("test_changedLorg_eclipse_swt_browser_LocationEvent")) test_changedLorg_eclipse_swt_browser_LocationEvent();
	else if (getName().equals("test_changingLorg_eclipse_swt_browser_LocationEvent")) test_changingLorg_eclipse_swt_browser_LocationEvent();
}
}
