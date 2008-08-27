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
import java.util.Iterator;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

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

boolean canChange(Class clazz) {
	return clazz == Integer.TYPE ||
		clazz == Long.TYPE ||
		clazz == Float.TYPE ||
		clazz == Double.TYPE ||
		clazz == int[].class ||
		clazz == long[].class ||
		clazz == float[].class ||
		clazz == double[].class;
}

public JNIType getType() {
	final Class clazz = field.getType();
	if (canChange(clazz)) {
		String originalSource = JNIGenerator.loadFile(declaringClass.sourcePath);
		final String source = originalSource;
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(source.toCharArray());
		ASTNode ast = parser.createAST(null);
		final Class[] result = new Class[1];
		ast.accept(new ASTVisitor() {
			public boolean visit(org.eclipse.jdt.core.dom.FieldDeclaration node) {
				for (Iterator iterator = node.fragments().iterator(); iterator.hasNext();) {
					VariableDeclarationFragment decl = (VariableDeclarationFragment) iterator.next();
					if (decl.getName().getIdentifier().equals(field.getName())) {
						String s = source.substring(node.getStartPosition(), node.getStartPosition() + node.getLength());
						if (clazz == int.class && s.indexOf("int /*long*/") != -1) result[0] = long.class;
						else if (clazz == long.class && s.indexOf("long /*int*/") != -1) result[0] = int.class;
						else if (clazz == float.class && s.indexOf("float /*double*/") != -1) result[0] = double.class;
						else if (clazz == double.class && s.indexOf("double /*float*/") != -1) result[0] = float.class;
						else if (clazz == int[].class && (s.indexOf("int /*long*/") != -1 || s.indexOf("int[] /*long[]*/") != -1)) result[0] = long[].class;
						else if (clazz == long[].class && (s.indexOf("long /*int*/") != -1|| s.indexOf("long[] /*int[]*/") != -1)) result[0] = int[].class;
						else if (clazz == float[].class && (s.indexOf("float /*double*/") != -1|| s.indexOf("float[] /*double[]*/") != -1)) result[0] = double[].class;
						else if (clazz == double[].class && (s.indexOf("double /*float*/") != -1|| s.indexOf("double[] /*float[]*/") != -1)) result[0] = float[].class;
						return false;
					}
				}
				return super.visit(node);
			}
		});
		if (result[0] != null) return new ReflectType(clazz, result[0]);
	}
	return new ReflectType(clazz);
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
