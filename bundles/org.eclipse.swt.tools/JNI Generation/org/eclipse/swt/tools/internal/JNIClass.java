/*******************************************************************************
 * Copyright (c) 2004, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.tools.internal;

public interface JNIClass extends JNIItem {
	
public static String[] FLAGS = {FLAG_NO_GEN, FLAG_CPP};

public String getName();

public JNIClass getSuperclass();

public JNIField[] getDeclaredFields();

public JNIMethod[] getDeclaredMethods();

public boolean isPrimitive();

public boolean isArray();

public JNIClass getComponentType();

public boolean isType(String type);

public int getByteCount();

public String getTypeSignature();

public String getTypeSignature1();

public String getTypeSignature2();

public String getTypeSignature3();

public String getTypeSignature4();

public String getTypeSignature4(boolean struct);

public String getSimpleName();

public String getExclude();

public void setExclude(String str);
}
