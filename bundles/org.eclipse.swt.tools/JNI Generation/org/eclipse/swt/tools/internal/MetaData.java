/*******************************************************************************
 * Copyright (c) 2004, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.tools.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MetaData {
	
	Properties data;

public MetaData(String mainClass) {
	data = new Properties();
	int index = 0;
	Class clazz = getClass();
	int length = mainClass.length();
	while (index < length) {
		index = mainClass.indexOf('.', index);
		if (index == -1) index = length;
		InputStream is = clazz.getResourceAsStream(mainClass.substring(0, index) + ".properties");
		if (is != null) {
			try {
				data.load(is);
			} catch (IOException e) {
			} finally {
				try {
					is.close();
				} catch (IOException e) {}
			}
			
		}
		index++;
	}
}

public MetaData(Properties data) {
	this.data = data;
}

public FieldData getMetaData(JNIField field) {
	String className = field.getDeclaringClass().getSimpleName();
	String key = className + "_" + field.getName();
	String value = getMetaData(key, "");
	return new FieldData(field, value);
}

boolean convertTo32Bit(JNIClass[] paramTypes, boolean floatingPointTypes) {
	boolean changed = false;
	for (int i = 0; i < paramTypes.length; i++) {
		JNIClass paramType = paramTypes[i];
		if (paramType.isType("long")) {
			paramTypes[i] = new ReflectClass(Integer.TYPE);
			changed = true;
		}
		if (paramType.isType("[J")) {
			paramTypes[i] = new ReflectClass(int[].class);
			changed = true;
		}
		if (floatingPointTypes) {
			if (paramType.isType("double")) {
				paramTypes[i] = new ReflectClass(Float.TYPE);
				changed = true;
			}
			if (paramType.isType("[D")) {
				paramTypes[i] = new ReflectClass(float[].class);
				changed = true;
			}
		}
	}
	return changed;	
}

public MethodData getMetaData(JNIMethod method) {
	String className = method.getDeclaringClass().getSimpleName();
	String key = className + "_" + JNIGenerator.getFunctionName(method);
	String value = getMetaData(key, null);
	if (value == null) {
		key = className + "_" + method.getName();
		value = getMetaData(key, null);
	}
	/*
	* Support for 64 bit port.
	*/
	if (value == null) {
		JNIClass[] paramTypes = method.getParameterTypes();
		if (convertTo32Bit(paramTypes, true)) {
			key = className + "_" + JNIGenerator.getFunctionName(method, paramTypes);
			value = getMetaData(key, null);
		}
		if (value == null) {
			paramTypes = method.getParameterTypes();
			if (convertTo32Bit(paramTypes, false)) {
				key = className + "_" + JNIGenerator.getFunctionName(method, paramTypes);
				value = getMetaData(key, null);
			}
		}
	}
	/*
	* Support for lock.
	*/
	if (value == null && method.getName().startsWith("_")) {
		key = className + "_" + JNIGenerator.getFunctionName(method).substring(2);
		value = getMetaData(key, null);
		if (value == null) {
			key = className + "_" + method.getName().substring(1);
			value = getMetaData(key, null);
		}
	}
	if (value == null) value = "";	
	return new MethodData(method, value);
}

public ParameterData getMetaData(JNIMethod method, int parameter) {
	String className = method.getDeclaringClass().getSimpleName();
	String key = className + "_" + JNIGenerator.getFunctionName(method) + "_" + parameter;
	String value = getMetaData(key, null);
	if (value == null) {
		key = className + "_" + method.getName() + "_" + parameter;
		value = getMetaData(key, null);
	}
	/*
	* Support for 64 bit port.
	*/
	if (value == null) {
		JNIClass[] paramTypes = method.getParameterTypes();
		if (convertTo32Bit(paramTypes, true)) {
			key = className + "_" + JNIGenerator.getFunctionName(method, paramTypes) + "_" + parameter;
			value = getMetaData(key, null);
		}
		if (value == null) {
			paramTypes = method.getParameterTypes();
			if (convertTo32Bit(paramTypes, false)) {
				key = className + "_" + JNIGenerator.getFunctionName(method, paramTypes) + "_" + parameter;
				value = getMetaData(key, null);
			}
		}
	}
	/*
	* Support for lock.
	*/
	if (value == null && method.getName().startsWith("_")) {
		key = className + "_" + JNIGenerator.getFunctionName(method).substring(2) + "_" + parameter;
		value = getMetaData(key, null);
		if (value == null) {
			key = className + "_" + method.getName().substring(1) + "_" + parameter;
			value = getMetaData(key, null);
		}
	}
	if (value == null) value = "";	
	return new ParameterData(method, parameter, value);
}

public String getMetaData(String key, String defaultValue) {
	return data.getProperty(key, defaultValue);
}

public void setMetaData(JNIField field, FieldData value) {
	String className = field.getDeclaringClass().getSimpleName();
	String key = className + "_" + field.getName();
	setMetaData(key, value.toString());
}

public void setMetaData(JNIMethod method, MethodData value) {
	String key;
	String className = method.getDeclaringClass().getSimpleName();
	if (JNIGenerator.isNativeUnique(method)) {
		key = className + "_" + method.getName ();
	} else {
		key = className + "_" + JNIGenerator.getFunctionName(method);
	}
	setMetaData(key, value.toString());
}

public void setMetaData(JNIMethod method, int arg, ParameterData value) {
	String key;
	String className = method.getDeclaringClass().getSimpleName();
	if (JNIGenerator.isNativeUnique(method)) {
		key = className + "_" + method.getName () + "_" + arg;
	} else {
		key = className + "_" + JNIGenerator.getFunctionName(method) + "_" + arg;
	}
	setMetaData(key, value.toString());
}

public void setMetaData(String key, String value) {
	data.setProperty(key, value);
}

}
