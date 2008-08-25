/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
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

public class MetaDataGenerator extends JNIGenerator {

public void generateCopyright() {
	generateMetaData("swt_properties_copyright");
}

public void generate(JNIClass clazz) {
	output(toC(clazz.getName()));
	output("=");
	output(((ReflectClass)clazz).flatten());
	outputln();
	JNIField[] fields = clazz.getDeclaredFields();
	generate(fields);
	JNIMethod[] methods = clazz.getDeclaredMethods();
	generate(methods);
	outputln();
}

public void generate(JNIField[] fields) {
	for (int i = 0; i < fields.length; i++) {
		JNIField field = fields[i];
		int mods = field.getModifiers();
		if ((mods & Modifier.PUBLIC) == 0) continue;
		if ((mods & Modifier.FINAL) != 0) continue;
		if ((mods & Modifier.STATIC) != 0) continue;
		generate(field);
		outputln();
	}
}

public void generate(JNIField field) {
	output(field.getDeclaringClass().getSimpleName());
	output("_");
	output(field.getName());
	output("=");
	FieldData data = getMetaData().getMetaData(field);
	if (data != null) output(data.toString());
}

public void generate(JNIMethod[] methods) {
	sort(methods);
	for (int i = 0; i < methods.length; i++) {
		JNIMethod method = methods[i];
		if ((method.getModifiers() & Modifier.NATIVE) == 0) continue;
		generate(method);
		outputln();
		if (progress != null) progress.step();
	}
}

public void generate(JNIMethod method) {
	StringBuffer buffer = new StringBuffer();
	buffer.append(method.getDeclaringClass().getSimpleName());
	buffer.append("_");
	if (isNativeUnique(method)) {
		buffer.append(method.getName());
	} else {
		buffer.append(getFunctionName(method));
	}
	String key = buffer.toString();
	output(key);
	output("=");
	MethodData methodData = getMetaData().getMetaData(method);
	if (methodData != null) output(methodData.toString());
	outputln();
	int length = method.getParameterTypes().length;
	for (int i = 0; i < length; i++) {
		output(key);
		output("_");
		output(i + "=");
		ParameterData paramData = getMetaData().getMetaData(method, i);
		if (paramData != null) output(paramData.toString());
		outputln();		
	}
}

public String getExtension() {
	return ".properties";
}

public String getOutputName() {
	return getMainClass().getName();
}

protected boolean getGenerate(JNIClass clazz) {
	return true;
}

}
