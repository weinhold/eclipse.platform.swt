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

public JNIType[] getParameterTypes() {
	Class[] paramTypes = method.getParameterTypes();
	ReflectType[] result = new ReflectType[paramTypes.length];
	for (int i = 0; i < paramTypes.length; i++) {
		result[i] = new ReflectType(paramTypes[i]);
	}
	return result;
}

public JNIParameter[] getParameters() {
	Class[] paramTypes = method.getParameterTypes();
	ReflectParameter[] result = new ReflectParameter[paramTypes.length];
	for (int i = 0; i < paramTypes.length; i++) {
		result[i] = new ReflectParameter(this, i);
	}
	return result;
}

public JNIType getReturnType() {
	return new ReflectType(method.getReturnType());
}

public String getAccessor() {
	return (String)getParam("accessor");
}

public String getExclude() {
	return (String)getParam("exclude");
}

public String getMetaData() {
	String className = getDeclaringClass().getSimpleName();
	String key = className + "_" + JNIGenerator.getFunctionName(this);
	MetaData metaData = declaringClass.metaData;
	String value = metaData.getMetaData(key, null);
	if (value == null) {
		key = className + "_" + method.getName();
		value = metaData.getMetaData(key, null);
	}
	/*
	* Support for 64 bit port.
	*/
	if (value == null) {
		JNIType[] paramTypes = getParameterTypes();
		if (convertTo32Bit(paramTypes, true)) {
			key = className + "_" + JNIGenerator.getFunctionName(this, paramTypes);
			value = metaData.getMetaData(key, null);
		}
		if (value == null) {
			paramTypes = getParameterTypes();
			if (convertTo32Bit(paramTypes, false)) {
				key = className + "_" + JNIGenerator.getFunctionName(this, paramTypes);
				value = metaData.getMetaData(key, null);
			}
		}
	}
	/*
	* Support for lock.
	*/
	if (value == null && method.getName().startsWith("_")) {
		key = className + "_" + JNIGenerator.getFunctionName(this).substring(2);
		value = metaData.getMetaData(key, null);
		if (value == null) {
			key = className + "_" + method.getName().substring(1);
			value = metaData.getMetaData(key, null);
		}
	}
	if (value == null) value = "";	
	return value;
}

public void setAccessor(String str) { 
	setParam("accessor", str);
}

public void setExclude(String str) { 
	setParam("exclude", str);
}

public void setMetaData(String value) {
	String key;
	String className = declaringClass.getSimpleName();
	if (JNIGenerator.isNativeUnique(this)) {
		key = className + "_" + method.getName ();
	} else {
		key = className + "_" + JNIGenerator.getFunctionName(this);
	}
	declaringClass.metaData.setMetaData(key, value);
}

public String toString() {
	return method.toString();
}

}
