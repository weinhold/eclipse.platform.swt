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
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

public class ReflectMethod extends ReflectItem implements JNIMethod {
	Method method;
	ReflectType returnType, returnType64;
	ReflectType[] paramTypes, paramTypes64;
	ReflectClass declaringClass;
	
public ReflectMethod(ReflectClass declaringClass, final Method method) {
	this.method = method;
	this.declaringClass = declaringClass;	
	final Class returnType = method.getReturnType();
	final Class[] paramTypes = method.getParameterTypes();
	boolean changes = false;
	if ((method.getModifiers() & Modifier.NATIVE) != 0) {
		changes = canChange64(returnType);
		if (!changes) {
			for (int i = 0; i < paramTypes.length && !changes; i++) {
				changes |= canChange64(paramTypes[i]);
			}
		}
	}
	if (changes) {
		final String name = method.getName();
		ASTNode ast = declaringClass.getDOM();
		final MethodDeclaration[] decl = new MethodDeclaration[1];
		ast.accept(new ASTVisitor() {
			public boolean visit(MethodDeclaration node) {
				if (node.getName().getIdentifier().equals(name)) {
					if (decl[0] != null) return false;
					if (!returnType.getSimpleName().equals(node.getReturnType2().toString())) return false;
					List parameters = node.parameters();
					if (parameters.size() != paramTypes.length) return false;
					for (int i = 0; i < paramTypes.length; i++) {
						if (!paramTypes[i].getSimpleName().equals(((SingleVariableDeclaration)parameters.get(i)).getType().toString())) {
							return false;
						}
					}
					decl[0] = node;
				}
				return false;
			}
		});
		ReflectType[] params = new ReflectType[paramTypes.length];
		ReflectType[] params64 = new ReflectType[paramTypes.length];
		for (int i = 0; i < paramTypes.length; i++) {
			params[i] = new ReflectType(paramTypes[i]);
			if (canChange64(paramTypes[i])) {
				Class clazz = paramTypes[i];
				SingleVariableDeclaration node = (SingleVariableDeclaration)decl[0].parameters().get(i);
				String s = declaringClass.source.substring(node.getStartPosition(), node.getStartPosition() + node.getLength());
				if (clazz == int.class && s.indexOf("int /*long*/") != -1) params64[0] = new ReflectType(long.class);
				else if (clazz == long.class && s.indexOf("long /*int*/") != -1) params64[0] = new ReflectType(int.class);
				else if (clazz == float.class && s.indexOf("float /*double*/") != -1) params64[0] = new ReflectType(double.class);
				else if (clazz == double.class && s.indexOf("double /*float*/") != -1) params64[0] = new ReflectType(float.class);
				else if (clazz == int[].class && (s.indexOf("int /*long*/") != -1 || s.indexOf("int[] /*long[]*/") != -1)) params64[0] = new ReflectType(long[].class);
				else if (clazz == long[].class && (s.indexOf("long /*int*/") != -1|| s.indexOf("long[] /*int[]*/") != -1)) params64[0] = new ReflectType(int[].class);
				else if (clazz == float[].class && (s.indexOf("float /*double*/") != -1|| s.indexOf("float[] /*double[]*/") != -1)) params64[0] = new ReflectType(double[].class);
				else if (clazz == double[].class && (s.indexOf("double /*float*/") != -1|| s.indexOf("double[] /*float[]*/") != -1)) params64[0] = new ReflectType(float[].class);
			} else {
				params64[i] = params[i];				
			}
		}
		this.paramTypes = params;
		this.paramTypes64 = params64;
		this.returnType = new ReflectType(returnType);
		if (canChange64(returnType)) {
			//TODO
		} else {
			this.returnType64 = this.returnType;
		}
	} else {
		this.returnType = this.returnType64 = new ReflectType(returnType);
		ReflectType[] params = new ReflectType[paramTypes.length];
		for (int i = 0; i < params.length; i++) {
			params[i] = new ReflectType(paramTypes[i]);
		}
		this.paramTypes = this.paramTypes64 = params;
	}
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
	return paramTypes;
}

public JNIType[] getParameterTypes64() {
	return paramTypes64;
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
