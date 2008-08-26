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

public class ReflectField extends ReflectItem implements JNIField {
	Field field;
	ReflectClass declaringClass;
	
public ReflectField(ReflectClass declaringClass, Field field) {
	this.declaringClass = declaringClass;
	this.field = field;
}

public int hashCode() {
	return field.hashCode();
}

public boolean equals(Object obj) {
	if (!(obj instanceof ReflectField)) return false;
	return ((ReflectField)obj).field.equals(field);
}

public JNIClass getDeclaringClass() {
	return declaringClass;
}

public int getModifiers() {
	return field.getModifiers();
}

public String getName() {
	return field.getName();
}

public JNIType getType() {
	return new ReflectType(field.getType());
}

public String getAccessor() {
	return (String)getParam("accessor");
}

public String getCast() {
	String cast = ((String)getParam("cast")).trim();
	if (cast.length() > 0) {
		if (!cast.startsWith("(")) cast = "(" + cast;
		if (!cast.endsWith(")")) cast = cast + ")";
	}
	return cast;
}

public String getExclude() {
	return (String)getParam("exclude");
}

public String getMetaData() {
	String className = getDeclaringClass().getSimpleName();
	String key = className + "_" + field.getName();
	return declaringClass.metaData.getMetaData(key, "");
}

public void setAccessor(String str) { 
	setParam("accessor", str);
}

public void setCast(String str) {
	setParam("cast", str);
}

public void setExclude(String str) { 
	setParam("exclude", str);
}

public void setMetaData(String value) {
	String className = declaringClass.getSimpleName();
	String key = className + "_" + field.getName();
	declaringClass.metaData.setMetaData(key, value);
}

public String toString() {
	return field.toString();
}

}
