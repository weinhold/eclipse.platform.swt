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

import java.lang.reflect.Method;

public class ReflectMethod extends ReflectItem implements JNIMethod {
	Method method;
	ReflectClass declaringClass;
	
public ReflectMethod(ReflectClass declaringClass, Method method) {
	this.method = method;
	this.declaringClass = declaringClass;
}

public String getMetaData() {
	//TODO
	return "";
}

public int hashCode() {
	return method.hashCode();
}

public boolean equals(Object obj) {
	if (!(obj instanceof ReflectMethod)) return false;
	return ((ReflectMethod)obj).method.equals(method);
}

public JNIClass getDeclaringClass() {
	return declaringClass;
}

public int getModifiers() {
	return method.getModifiers();
}

public String getName() {
	return method.getName();
}

public JNIClass[] getParameterTypes() {
	Class[] paramTypes = method.getParameterTypes();
	ReflectClass[] result = new ReflectClass[paramTypes.length];
	for (int i = 0; i < paramTypes.length; i++) {
		result[i] = new ReflectClass(paramTypes[i]);
	}
	return result;
}

public JNIClass getReturnType() {
	return new ReflectClass(method.getReturnType());
}

public String toString() {
	return method.toString();
}

}
