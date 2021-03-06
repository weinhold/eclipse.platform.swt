/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others.
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

/**
 * Automated Test Suite for class org.eclipse.swt.layout.RowLayout
 *
 * @see org.eclipse.swt.layout.RowLayout
 */
public class Test_org_eclipse_swt_layout_RowLayout extends Test_org_eclipse_swt_widgets_Layout {

public Test_org_eclipse_swt_layout_RowLayout(String name) {
	super(name);
}

public static void main(String[] args) {
	TestRunner.run(suite());
}

@Override
public void test_Constructor() {
	warnUnimpl("Test test_Constructor not written");
}

public void test_ConstructorI() {
	warnUnimpl("Test test_ConstructorI not written");
}

public static Test suite() {
	TestSuite suite = new TestSuite();
	java.util.Vector<String> methodNames = methodNames();
	java.util.Enumeration<String> e = methodNames.elements();
	while (e.hasMoreElements()) {
		suite.addTest(new Test_org_eclipse_swt_layout_RowLayout(e.nextElement()));
	}
	return suite;
}
public static java.util.Vector<String> methodNames() {
	java.util.Vector<String> methodNames = new java.util.Vector<String>();
	methodNames.addElement("test_Constructor");
	methodNames.addElement("test_ConstructorI");
	methodNames.addAll(Test_org_eclipse_swt_widgets_Layout.methodNames()); // add superclass method names
	return methodNames;
}
@Override
protected void runTest() throws Throwable {
	if (getName().equals("test_Constructor")) test_Constructor();
	else if (getName().equals("test_ConstructorI")) test_ConstructorI();
	else super.runTest();
}
}
