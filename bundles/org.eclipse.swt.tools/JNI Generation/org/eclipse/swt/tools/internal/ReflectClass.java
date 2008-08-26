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

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectClass extends ReflectItem implements JNIClass {
	Class clazz;
	MetaData metaData;

public ReflectClass(Class clazz) {
	this.clazz = clazz;
}

public ReflectClass(Class clazz, MetaData data) {
	this.clazz = clazz;
	this.metaData = data;
}

public int hashCode() {
	return clazz.hashCode();
}

public boolean equals(Object obj) {
	if (!(obj instanceof ReflectClass)) return false;
	return ((ReflectClass)obj).clazz.equals(clazz);
}

public JNIClass getComponentType() {
	return new ReflectClass(clazz.getComponentType(), metaData);
}

public JNIField[] getDeclaredFields() {
	Field[] fields = clazz.getDeclaredFields();
	JNIField[] result = new JNIField[fields.length];
	for (int i = 0; i < fields.length; i++) {
		result[i] = new ReflectField(this, fields[i]);
	}
	return result;
}

public JNIMethod[] getDeclaredMethods() {
	Method[] methods = clazz.getDeclaredMethods();
	JNIMethod[] result = new JNIMethod[methods.length];
	for (int i = 0; i < methods.length; i++) {
		result[i] = new ReflectMethod(this, methods[i]);
	}
	return result;
}

public String getName() {
	return clazz.getName();
}

public JNIClass getSuperclass() {
	return new ReflectClass(clazz.getSuperclass(), metaData);
}

public boolean isArray() {
	return clazz.isArray();
}

public boolean isPrimitive() {
	return clazz.isPrimitive();
}

public boolean isType(String type) {
	return clazz.getName().equals(type);
}

public String getSimpleName() {
	String name = clazz.getName();
	int index = name.lastIndexOf('.') + 1;
	return name.substring(index, name.length());
}

public String getExclude() {
	return (String)getParam("exclude");
}

public String getMetaData() {
	String key = JNIGenerator.toC(clazz.getName());
	return metaData.getMetaData(key, "");
}

public void setExclude(String str) { 
	setParam("exclude", str);
}

public void setMetaData(String value) {
	String key = JNIGenerator.toC(clazz.getName());
	metaData.setMetaData(key, value);
}

public String toString() {
	return clazz.toString();
}

}
