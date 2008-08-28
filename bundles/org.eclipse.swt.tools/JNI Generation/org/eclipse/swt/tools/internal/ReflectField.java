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

import java.io.File;
import java.lang.reflect.Field;
import java.util.Iterator;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class ReflectField extends ReflectItem implements JNIField {
	Field field;
	ReflectType type, type64;
	ReflectClass declaringClass;
	
public ReflectField(final ReflectClass declaringClass, final Field field) {
	this.declaringClass = declaringClass;
	this.field = field;
	final Class clazz = field.getType();
	boolean changes = canChange64(clazz);
	if (changes && new File(declaringClass.sourcePath).exists()) {
		ASTNode ast = declaringClass.getDOM();
		final Class[] result = new Class[1];
		ast.accept(new ASTVisitor() {
			public boolean visit(MethodDeclaration node) {
				return false;
			}
			public boolean visit(FieldDeclaration node) {
				for (Iterator iterator = node.fragments().iterator(); iterator.hasNext();) {
					VariableDeclarationFragment decl = (VariableDeclarationFragment) iterator.next();
					if (decl.getName().getIdentifier().equals(field.getName())) {
						String s = declaringClass.source.substring(node.getStartPosition(), node.getStartPosition() + node.getLength());
						if (clazz == int.class && s.indexOf("int /*long*/") != -1) result[0] = long.class;
						else if (clazz == long.class && s.indexOf("long /*int*/") != -1) result[0] = int.class;
						else if (clazz == float.class && s.indexOf("float /*double*/") != -1) result[0] = double.class;
						else if (clazz == double.class && s.indexOf("double /*float*/") != -1) result[0] = float.class;
						else if (clazz == int[].class && (s.indexOf("int /*long*/") != -1 || s.indexOf("int[] /*long[]*/") != -1)) result[0] = long[].class;
						else if (clazz == long[].class && (s.indexOf("long /*int*/") != -1|| s.indexOf("long[] /*int[]*/") != -1)) result[0] = int[].class;
						else if (clazz == float[].class && (s.indexOf("float /*double*/") != -1|| s.indexOf("float[] /*double[]*/") != -1)) result[0] = double[].class;
						else if (clazz == double[].class && (s.indexOf("double /*float*/") != -1|| s.indexOf("double[] /*float[]*/") != -1)) result[0] = float[].class;
						else result[0] = clazz;
						return false;
					}
				}
				return super.visit(node);
			}
		});
		Class temp = clazz;
		if (temp.isArray()) temp = temp.getComponentType();
		if (temp == long.class || temp == double.class) {
			type = new ReflectType(result[0]);
			type64 = new ReflectType(clazz);
		} else {
			type = new ReflectType(clazz);
			type64 = new ReflectType(result[0]);			
		}
	} else {
		type = type64 = new ReflectType(clazz);
	}
	
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
	return type;
}

public JNIType getType64() {
	return type64;
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
