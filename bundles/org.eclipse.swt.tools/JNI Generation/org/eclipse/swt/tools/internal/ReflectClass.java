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

public int hashCode() {
	return clazz.hashCode();
}

public boolean equals(Object obj) {
	if (!(obj instanceof ReflectClass)) return false;
	return ((ReflectClass)obj).clazz.equals(clazz);
}

public String getMetaData() {
	String key = JNIGenerator.toC(clazz.getName());
	return metaData.getMetaData(key, "");
}

public JNIClass getComponentType() {
	return new ReflectClass(clazz.getComponentType());
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
	return new ReflectClass(clazz.getSuperclass());
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

public int getByteCount() {
	if (clazz == Integer.TYPE) return 4;
	if (clazz == Boolean.TYPE) return 4;
	if (clazz == Long.TYPE) return 8;
	if (clazz == Short.TYPE) return 2;
	if (clazz == Character.TYPE) return 2;
	if (clazz == Byte.TYPE) return 1;
	if (clazz == Float.TYPE) return 4;
	if (clazz == Double.TYPE) return 8;
	return 4;
}

public String getTypeSignature() {
	if (clazz == Void.TYPE) return "V";
	if (clazz == Integer.TYPE) return "I";
	if (clazz == Boolean.TYPE) return "Z";
	if (clazz == Long.TYPE) return "J";
	if (clazz == Short.TYPE) return "S";
	if (clazz == Character.TYPE) return "C";
	if (clazz == Byte.TYPE) return "B";
	if (clazz == Float.TYPE) return "F";
	if (clazz == Double.TYPE) return "D";
	if (clazz == String.class) return "Ljava/lang/String;";
	if (clazz.isArray()) {
		return "[" + getComponentType().getTypeSignature();
	}
	return "L" + clazz.getName().replace('.', '/') + ";";
}

public String getTypeSignature1() {
	if (clazz == Void.TYPE) return "Void";
	if (clazz == Integer.TYPE) return "Int";
	if (clazz == Boolean.TYPE) return "Boolean";
	if (clazz == Long.TYPE) return "Long";
	if (clazz == Short.TYPE) return "Short";
	if (clazz == Character.TYPE) return "Char";
	if (clazz == Byte.TYPE) return "Byte";
	if (clazz == Float.TYPE) return "Float";
	if (clazz == Double.TYPE) return "Double";
	if (clazz == String.class) return "String";
	return "Object";
}

public String getTypeSignature2() {
	if (clazz == Void.TYPE) return "void";
	if (clazz == Integer.TYPE) return "jint";
	if (clazz == Boolean.TYPE) return "jboolean";
	if (clazz == Long.TYPE) return "jlong";
	if (clazz == Short.TYPE) return "jshort";
	if (clazz == Character.TYPE) return "jchar";
	if (clazz == Byte.TYPE) return "jbyte";
	if (clazz == Float.TYPE) return "jfloat";
	if (clazz == Double.TYPE) return "jdouble";
	if (clazz == String.class) return "jstring";
	if (clazz == Class.class) return "jclass";
	if (clazz.isArray()) {
		return getComponentType().getTypeSignature2() + "Array";
	}
	return "jobject";
}

public String getTypeSignature3() {
	if (clazz == Void.TYPE) return "void";
	if (clazz == Integer.TYPE) return "int";
	if (clazz == Boolean.TYPE) return "boolean";
	if (clazz == Long.TYPE) return "long";
	if (clazz == Short.TYPE) return "short";
	if (clazz == Character.TYPE) return "char";
	if (clazz == Byte.TYPE) return "byte";
	if (clazz == Float.TYPE) return "float";
	if (clazz == Double.TYPE) return "double";
	if (clazz == String.class) return "String";
	if (clazz.isArray()) {
		return getComponentType().getTypeSignature3() + "[]";
	}
	return clazz.getName();
}

public String getTypeSignature4() {
	return getTypeSignature4(false);
}

public String getTypeSignature4(boolean struct) {
	if (clazz == Void.TYPE) return "void";
	if (clazz == Integer.TYPE) return "jint";
	if (clazz == Boolean.TYPE) return "jboolean";
	if (clazz == Long.TYPE) return "jlong";
	if (clazz == Short.TYPE) return "jshort";
	if (clazz == Character.TYPE) return "jchar";
	if (clazz == Byte.TYPE) return "jbyte";
	if (clazz == Float.TYPE) return "jfloat";
	if (clazz == Double.TYPE) return "jdouble";
	if (clazz == String.class) return "jstring";
	if (clazz.isArray()) {
		String sig = getComponentType().getTypeSignature4();
		return struct ? sig : sig + " *";
	}
	String sig = getSimpleName(); 
	return struct ? sig : sig + " *";
}

public String getSimpleName() {
	String name = clazz.getName();
	int index = name.lastIndexOf('.') + 1;
	return name.substring(index, name.length());
}

public String getExclude() {
	return (String)getParam("exclude");
}

public void setExclude(String str) { 
	setParam("exclude", str);
}

public String toString() {
	return clazz.toString();
}

}
