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

public String getMetaData() {
	String className = field.getDeclaringClass().getSimpleName();
	String key = className + "_" + field.getName();
	return declaringClass.metaData.getMetaData(key, "");
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

public JNIClass getType() {
	return new ReflectClass(field.getType());
}

public String toString() {
	return field.toString();
}

}
