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

import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

public class ASTMethod extends ReflectItem implements JNIMethod {
	String name, qualifiedName;
	int modifiers;
	ASTClass declaringClass;
	ASTType[] paramTypes, paramTypes64;
	ASTType returnType, returnType64;
	Boolean unique;
	
public ASTMethod(ASTClass declaringClass, String source, String packageName, MethodDeclaration method) {
	this.declaringClass = declaringClass;
	
	name = method.getName().getIdentifier();
	modifiers = method.getModifiers();
	
	returnType = new ASTType(packageName, method.getReturnType2(), method.getExtraDimensions());
	returnType64 = returnType;
	if (GEN64) {
		String s = source.substring(method.getReturnType2().getStartPosition(), method.getName().getStartPosition());
		if (returnType.isType("int") && s.indexOf("int /*long*/") != -1) returnType64 = new ASTType("J");
		else if (returnType.isType("float") && s.indexOf("float /*double*/") != -1) returnType64 = new ASTType("D");
		else if (returnType.isType("[I") && (s.indexOf("int /*long*/") != -1 || s.indexOf("int[] /*long[]*/") != -1)) returnType64 = new ASTType("[J");
		else if (returnType.isType("[F") && (s.indexOf("float /*double*/") != -1|| s.indexOf("float[] /*double[]*/") != -1)) returnType64 = new ASTType("[D");
		else if (returnType.isType("long") && s.indexOf("long /*int*/") != -1) returnType = new ASTType("I");
		else if (returnType.isType("double") && s.indexOf("double /*float*/") != -1) returnType = new ASTType("F");
		else if (returnType.isType("[J") && (s.indexOf("long /*int*/") != -1|| s.indexOf("long[] /*int[]*/") != -1)) returnType = new ASTType("[I");
		else if (returnType.isType("[D") && (s.indexOf("double /*float*/") != -1|| s.indexOf("double[] /*float[]*/") != -1)) returnType = new ASTType("[F");
	}
	
	List parameters = method.parameters();
	paramTypes = new ASTType[parameters.size()];
	paramTypes64 = new ASTType[parameters.size()];
	int i = 0;
	for (Iterator iterator = parameters.iterator(); iterator.hasNext(); i++) {
		SingleVariableDeclaration param = (SingleVariableDeclaration) iterator.next();
		paramTypes[i] = new ASTType(packageName, param.getType(), param.getExtraDimensions());
		paramTypes64[i] = paramTypes[i];
		if (GEN64) {
			String s = source.substring(param.getStartPosition(), param.getStartPosition() + param.getLength());
			if (paramTypes[i].isType("int") && s.indexOf("int /*long*/") != -1) paramTypes64[i] = new ASTType("J");
			else if (paramTypes[i].isType("float") && s.indexOf("float /*double*/") != -1) paramTypes64[i] = new ASTType("D");
			else if (paramTypes[i].isType("[I") && (s.indexOf("int /*long*/") != -1 || s.indexOf("int[] /*long[]*/") != -1)) paramTypes64[i] = new ASTType("[J");
			else if (paramTypes[i].isType("[F") && (s.indexOf("float /*double*/") != -1|| s.indexOf("float[] /*double[]*/") != -1)) paramTypes64[i] = new ASTType("[D");
			else if (paramTypes[i].isType("long") && s.indexOf("long /*int*/") != -1) paramTypes[i] = new ASTType("I");
			else if (paramTypes[i].isType("double") && s.indexOf("double /*float*/") != -1) paramTypes[i] = new ASTType("F");
			else if (paramTypes[i].isType("[J") && (s.indexOf("long /*int*/") != -1|| s.indexOf("long[] /*int[]*/") != -1)) paramTypes[i] = new ASTType("[I");
			else if (paramTypes[i].isType("[D") && (s.indexOf("double /*float*/") != -1|| s.indexOf("double[] /*float[]*/") != -1)) paramTypes[i] = new ASTType("[F");
		}
	}
}

public JNIClass getDeclaringClass() {
	return declaringClass;
}

public int getModifiers() {
	return modifiers;
}

public String getName() {
	return name;
}

public boolean isNativeUnique() {
	if (unique != null) return unique.booleanValue();
	boolean result = true;
	String name = getName();
	JNIMethod[] methods = declaringClass.getDeclaredMethods();
	for (int i = 0; i < methods.length; i++) {
		JNIMethod mth = methods[i];
		if ((mth.getModifiers() & Modifier.NATIVE) != 0 &&
			this != mth && !this.equals(mth) &&
			name.equals(mth.getName()))
			{
				result = false;
				break;
			}
	}
	unique = new Boolean(result);
	return result;
}

public JNIType[] getParameterTypes() {
	return paramTypes;
}

public JNIType[] getParameterTypes64() {
	return paramTypes64;
}

public JNIParameter[] getParameters() {
	JNIType[] paramTypes = getParameterTypes();
	ASTParameter[] result = new ASTParameter[paramTypes.length];
	for (int i = 0; i < paramTypes.length; i++) {
		result[i] = new ASTParameter(this, i);
	}
	return result;
}

public JNIType getReturnType() {
	return returnType;
}

public JNIType getReturnType64() {
	return returnType64;
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
		key = className + "_" + getName();
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
	if (value == null && getName().startsWith("_")) {
		key = className + "_" + JNIGenerator.getFunctionName(this).substring(2);
		value = metaData.getMetaData(key, null);
		if (value == null) {
			key = className + "_" + getName().substring(1);
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
	if (isNativeUnique()) {
		key = className + "_" + getName ();
	} else {
		key = className + "_" + JNIGenerator.getFunctionName(this);
	}
	declaringClass.metaData.setMetaData(key, value);
}

public String toString() {
	return getName();
}

}
