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

import java.lang.reflect.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NativesGenerator extends JNIGenerator {

boolean enterExitMacro;
String currentSource;

public NativesGenerator() {
	enterExitMacro = true;
}

public void generateCopyright() {
	generateMetaData("swt_copyright");
}

public void generateIncludes() {
	String outputName = getOutputName();
	outputln("#include \"swt.h\"");
	output("#include \"");
	output(outputName);
	outputln("_structs.h\"");
	output("#include \"");
	output(outputName);
	outputln("_stats.h\"");
	outputln();
}

public void generate(Class clazz, String methodName) {
	Method[] methods = clazz.getDeclaredMethods();
	int count = 0;
	for (int i = 0; i < methods.length; i++) {
		if (methods[i].getName().startsWith(methodName)) count++;
	}
	Method[] result = new Method[count];
	count = 0;
	for (int i = 0; i < methods.length; i++) {
		if (methods[i].getName().startsWith(methodName)) result[count++] = methods[i];
	}
	generate(result);
}

public void generate(Class clazz) {
	Method[] methods = clazz.getDeclaredMethods();
	int i = 0;
	for (; i < methods.length; i++) {
		Method method = methods[i];
		if ((method.getModifiers() & Modifier.NATIVE) != 0) break;
	}
	if (i == methods.length) return;
	currentSource = loadSource(clazz);
	sort(methods);
	generateNativeMacro(clazz);
	generateExcludes(methods);
	generate(methods);
}

public void generate(Method[] methods) {
	sort(methods);	
	for (int i = 0; i < methods.length; i++) {
		Method method = methods[i];
		if ((method.getModifiers() & Modifier.NATIVE) == 0) continue;
		generate(method);
		if (progress != null) progress.step();
	}
}

boolean canChangeFor64(Class clazz) {
	if (clazz == int.class) return true;
	if (clazz == int[].class) return true;
	if (clazz == float.class) return true;
	if (clazz == float[].class) return true;
	return false;
}

String asRegex(String str) {
	int dot = str.lastIndexOf('.');
	if (dot != -1) str = str.substring(dot + 1);
	StringBuffer buffer = new StringBuffer();
	for (int i = 0; i < str.length(); i++) {
		char c = str.charAt(i);
		switch (c) {
			case '[':
				buffer.append(".*\\[");
				break;
			case ']':
				buffer.append("\\s*\\]");
				break;
			default: buffer.append(c);
		}
	}
	return buffer.toString();
}

boolean get64MethodTypes(Method method, Class[] paramTypes64, Class[] returnType64) {
	boolean canChange = false;
	Class returnType = method.getReturnType();
	if (!(canChange |= canChangeFor64(returnType))) returnType64[0] = returnType;
	Class[] paramTypes = method.getParameterTypes();
	for (int i = 0; i < paramTypes.length; i++) {
		if (!(canChange |= canChangeFor64(paramTypes[i]))) paramTypes64[i] = paramTypes[i];
	}
	if (!canChange) return false;
	boolean result = false;
	Matcher matcher = Pattern.compile("public\\s*static\\s*final\\s*native\\s*").matcher(currentSource);
	int start = 0;
	String name = method.getName();
	while (matcher.find(start)) {
		start = matcher.end();
		int semicolon = currentSource.indexOf(';', matcher.end());
		String mthDecl = currentSource.substring(matcher.end(), semicolon);
		int startParen = mthDecl.indexOf('(');
		String returnAndMethod = mthDecl.substring(0, startParen);
		if (returnAndMethod.indexOf(name) == -1) continue;
		int endParen = mthDecl.indexOf(')', startParen + 1);
		String params = mthDecl.substring(startParen + 1, endParen).trim();
		String[] paramsDecl = params.length() == 0 ? new String[0] : params.split(",");
		if (paramsDecl.length != paramTypes.length) continue;
		boolean found = true;
		for (int i = 0; i < paramsDecl.length; i++) {
			String paramDecl = paramsDecl[i].trim();
			int index = 0;
			//TODO not working for arrays
			while (!Character.isWhitespace(paramDecl.charAt(index))) index++;
			String paramTypeRegex = asRegex(getTypeSignature3(paramTypes[i]));
			if (!paramDecl.substring(0, index).matches(paramTypeRegex)) {
				found = false;
				break;
			}
		}
		if (found) {
			for (int i = 0; i < paramsDecl.length; i++) {
				if (paramTypes64[i] == null) {
					if (paramsDecl[i].indexOf("/*") != -1) {
						if (paramTypes[i] == int.class) paramTypes64[i] = long.class;
						else if (paramTypes[i] == int[].class) paramTypes64[i] = long[].class;
						else if (paramTypes[i] == float.class) paramTypes64[i] = double.class;
						else if (paramTypes[i] == float[].class) paramTypes64[i] = double[].class;
						result = true;
					} else {
						paramTypes64[i] = paramTypes[i];
					}
				}
			}
			if (returnAndMethod.indexOf("/*") != -1) {
				if (returnType == int.class) returnType64[0] = long.class;
				else if (returnType == int[].class) returnType64[0] = long[].class;
				else if (returnType == float.class) returnType64[0] = double.class;
				else if (returnType == float[].class) returnType64[0] = double[].class;
			} else {
				returnType64[0] = returnType;
			}
			System.out.println(method + " " + mthDecl);
			break;
		}
	}		
	return result;

}

public void generate(Method method) {
	MethodData methodData = getMetaData().getMetaData(method);
	if (methodData.getFlag(FLAG_NO_GEN)) return;
	Class returnType = method.getReturnType();
	if (!(returnType == Void.TYPE || returnType.isPrimitive() || isSystemClass(returnType) || returnType == String.class)) {
		output("Warning: bad return type. :");
		outputln(method.toString());
		return;
	}
	Class[] paramTypes = method.getParameterTypes();
	Class[] paramTypes64 = new Class[paramTypes.length];
	Class[] returnType64 = new Class[1];
	get64MethodTypes(method, paramTypes64, returnType64);
	String function = getFunctionName(method), function64 = getFunctionName(method, paramTypes64);
	generateSourceStart(function, function64);
	boolean sameFunction = function.equals(function64);
	if (!sameFunction) {
		outputln("#ifndef SWT_PTR_SIZE_64");
	}
	if (isCPP) {
		output("extern \"C\" ");
		generateFunctionPrototype(method, function, paramTypes, returnType, paramTypes64, returnType64[0], true);
		outputln(";");
	}
	generateFunctionPrototype(method, function, paramTypes, returnType, paramTypes64, returnType64[0], !sameFunction);
	if (!function.equals(function64)) {
		outputln();
		outputln("#else");
		if (isCPP) {
			output("extern \"C\" ");
			generateFunctionPrototype(method, function64, paramTypes, returnType, paramTypes64, returnType64[0], true);
			outputln(";");
		}
		generateFunctionPrototype(method, function64, paramTypes, returnType, paramTypes64, returnType64[0], !sameFunction);
		outputln();
		outputln("#endif");
	}
	generateFunctionBody(method, methodData, function, paramTypes, returnType, function64, paramTypes64, returnType64[0]);
	generateSourceEnd(function, function64);
	outputln();
}

public void setEnterExitMacro(boolean enterExitMacro) {
	this.enterExitMacro = enterExitMacro;
}

void generateExcludes(Method[] methods) {
	HashSet excludes = new HashSet();
	for (int i = 0; i < methods.length; i++) {
		Method method = methods[i];
		if ((method.getModifiers() & Modifier.NATIVE) == 0) continue;
		MethodData methodData = getMetaData().getMetaData(method);
		String exclude = methodData.getExclude();
		if (exclude.length() != 0) {
			excludes.add(exclude);
		}
	}
	for (Iterator iter = excludes.iterator(); iter.hasNext();) {
		String exclude = (String)iter.next();
		outputln(exclude);
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if ((method.getModifiers() & Modifier.NATIVE) == 0) continue;
			MethodData methodData = getMetaData().getMetaData(method);
			String methodExclude = methodData.getExclude();
			if (exclude.equals(methodExclude)) {
				output("#define NO_");
				outputln(getFunctionName(method));
			}
		}
		outputln("#endif");
		outputln();
	}
}

void generateNativeMacro(Class clazz) {
	output("#define ");
	output(getClassName(clazz));
	output("_NATIVE(func) Java_");
	output(toC(clazz.getName()));
	outputln("_##func");
	outputln();
}

boolean generateGetParameter(Method method, int i, Class paramType, Class paramType64, ParameterData paramData, boolean critical, int indent) {
	if (paramType.isPrimitive() || isSystemClass(paramType)) return false;
	String iStr = String.valueOf(i);
	for (int j = 0; j < indent; j++) output("\t");
	output("if (arg");
	output(iStr);
	output(") if ((lparg");
	output(iStr);
	output(" = ");
	if (paramType.isArray()) {
		Class componentType = paramType.getComponentType();
		if (componentType.isPrimitive()) {
			if (critical) {
				if (isCPP) {
					output("(");
					output(getTypeSignature2(componentType, componentType != paramType64.getComponentType()));
					output("*)");
					output("env->GetPrimitiveArrayCritical(arg");
				} else {
					output("(*env)->GetPrimitiveArrayCritical(env, arg");
				}
				output(iStr);
				output(", NULL)");
			} else {
				if (isCPP) {
					output("env->Get");
				} else {
					output("(*env)->Get");
				}
				output(getTypeSignature1(componentType, componentType != paramType64.getComponentType()));
				if (isCPP) {
					output("ArrayElements(arg");
				} else {
					output("ArrayElements(env, arg");
				}
				output(iStr);
				output(", NULL)");
			}
		} else {
			throw new Error("not done");
		}
	} else if (paramType == String.class) {
		if (paramData.getFlag(FLAG_UNICODE)) {
			if (isCPP) {
				output("env->GetStringChars(arg");
			} else {
				output("(*env)->GetStringChars(env, arg");
			}
			output(iStr);
			output(", NULL)");
		} else {
			if (isCPP) {
				output("env->GetStringUTFChars(arg");
			} else {
				output("(*env)->GetStringUTFChars(env, arg");
			}
			output(iStr);
			output(", NULL)");
		}
	} else {
		if (paramData.getFlag(FLAG_NO_IN)) {
			output("&_arg");
			output(iStr);
		} else {
			output("get");
			output(getClassName(paramType));
			output("Fields(env, arg");
			output(iStr);
			output(", &_arg");
			output(iStr);
			output(")");
		}
	}	
	outputln(") == NULL) goto fail;");
	return true;
}

void generateSetParameter(int i, Class paramType, Class paramType64, ParameterData paramData, boolean critical) {
	if (paramType.isPrimitive() || isSystemClass(paramType)) return;
	String iStr = String.valueOf(i);
	if (paramType.isArray()) {
		output("\tif (arg");
		output(iStr);
		output(" && lparg");
		output(iStr);
		output(") ");
		Class componentType = paramType.getComponentType();
		if (componentType.isPrimitive()) {
			if (critical) {
				if (isCPP) {
					output("env->ReleasePrimitiveArrayCritical(arg");
				} else {
					output("(*env)->ReleasePrimitiveArrayCritical(env, arg");
				}
				output(iStr);
			} else {
				if (isCPP) {
					output("env->Release");
				} else {
					output("(*env)->Release");
				}
				output(getTypeSignature1(componentType, componentType != paramType64.getComponentType()));
				if (isCPP) {
					output("ArrayElements(arg");
				} else {
					output("ArrayElements(env, arg");
				}
				output(iStr);
			}
			output(", lparg");
			output(iStr);
			output(", ");
			if (paramData.getFlag(FLAG_NO_OUT)) {
				output("JNI_ABORT");
			} else {				
				output("0");
			}
			output(");");
		} else {
			throw new Error("not done");
		}
		outputln();
	} else if (paramType == String.class) {
		output("\tif (arg");
		output(iStr);
		output(" && lparg");
		output(iStr);
		output(") ");
		if (paramData.getFlag(FLAG_UNICODE)) {
			if (isCPP) {
				output("env->ReleaseStringChars(arg");
			} else {
				output("(*env)->ReleaseStringChars(env, arg");
			}
		} else {
			if (isCPP) {
				output("env->ReleaseStringUTFChars(arg");
			} else {
				output("(*env)->ReleaseStringUTFChars(env, arg");
			}
		}
		output(iStr);
		output(", lparg");
		output(iStr);
		outputln(");");
	} else {
		if (!paramData.getFlag(FLAG_NO_OUT)) {
			output("\tif (arg");
			output(iStr);
			output(" && lparg");
			output(iStr);
			output(") ");
			output("set");
			output(getClassName(paramType));
			output("Fields(env, arg");
			output(iStr);
			output(", lparg");
			output(iStr);
			outputln(");");
		}
	}
}

void generateExitMacro(Method method, String function, String function64) {
	if (!enterExitMacro) return;
	if (!function.equals(function64)) {
		outputln("#ifndef SWT_PTR_SIZE_64");
	}
	output("\t");
	output(getClassName(method.getDeclaringClass()));
	output("_NATIVE_EXIT(env, that, ");
	output(function);
	outputln("_FUNC);");
	if (!function.equals(function64)) {
		outputln("#else");
		output("\t");
		output(getClassName(method.getDeclaringClass()));
		output("_NATIVE_EXIT(env, that, ");
		output(function64);
		outputln("_FUNC);");
		outputln("#endif");
	}
}

void generateEnterMacro(Method method, String function, String function64) {
	if (!enterExitMacro) return;
	if (!function.equals(function64)) {
		outputln("#ifndef SWT_PTR_SIZE_64");
	}
	output("\t");
	output(getClassName(method.getDeclaringClass()));
	output("_NATIVE_ENTER(env, that, ");
	output(function);
	outputln("_FUNC);");
	if (!function.equals(function64)) {
		outputln("#else");
		output("\t");
		output(getClassName(method.getDeclaringClass()));
		output("_NATIVE_ENTER(env, that, ");
		output(function64);
		outputln("_FUNC);");
		outputln("#endif");
	}
}

boolean generateLocalVars(Method method, Class[] paramTypes, Class returnType, Class[] paramTypes64, Class returnType64) {
	boolean needsReturn = enterExitMacro;
	for (int i = 0; i < paramTypes.length; i++) {
		Class paramType = paramTypes[i];
		if (paramType.isPrimitive() || isSystemClass(paramType)) continue;
		ParameterData paramData = getMetaData().getMetaData(method, i);
		output("\t");
		if (paramType.isArray()) {
			Class componentType = paramType.getComponentType();
			if (componentType.isPrimitive()) {
				output(getTypeSignature2(componentType, componentType != paramTypes64[i].getComponentType()));
				output(" *lparg" + i);
				output("=NULL;");
			} else {
				throw new Error("not done");
			}
		} else if (paramType == String.class) {
			if (paramData.getFlag(FLAG_UNICODE)) {
				output("const jchar *lparg" + i);				
			} else {
				output("const char *lparg" + i);
			}
			output("= NULL;");
		} else {
			ClassData classData = metaData.getMetaData(paramType);
			if (classData.getFlag(Flags.FLAG_STRUCT)) {
				output("struct ");
			}
			output(getClassName(paramType));
			output(" _arg" + i);
			if (paramData.getFlag(FLAG_INIT)) output("={0}");
			output(", *lparg" + i);
			output("=NULL;");
		}
		outputln();
		needsReturn = true;
	}
	if (needsReturn) {
		if (returnType != Void.TYPE) {
			output("\t");
			output(getTypeSignature2(returnType, returnType != returnType64));
			outputln(" rc = 0;");
		}
	}
	return needsReturn;
}

boolean generateGetters(Method method, Class[] paramTypes, Class[] paramTypes64) {
	boolean genFailTag = false;
	int criticalCount = 0;
	for (int i = 0; i < paramTypes.length; i++) {
		Class paramType = paramTypes[i];
		ParameterData paramData = getMetaData().getMetaData(method, i);
		if (!isCritical(paramType, paramData)) {
			genFailTag |= generateGetParameter(method, i, paramType, paramTypes64[i], paramData, false, 1);
		} else {
			criticalCount++;
		}
	}
	if (criticalCount != 0) {
		outputln("#ifdef JNI_VERSION_1_2");
		outputln("\tif (IS_JNI_1_2) {");
		for (int i = 0; i < paramTypes.length; i++) {
			Class paramType = paramTypes[i];
			ParameterData paramData = getMetaData().getMetaData(method, i);
			if (isCritical(paramType, paramData)) {
				genFailTag |= generateGetParameter(method, i, paramType, paramTypes64[i], paramData, true, 2);
			}
		}
		outputln("\t} else");
		outputln("#endif");
		outputln("\t{");
		for (int i = 0; i < paramTypes.length; i++) {
			Class paramType = paramTypes[i];
			ParameterData paramData = getMetaData().getMetaData(method, i);
			if (isCritical(paramType, paramData)) {
				genFailTag |= generateGetParameter(method, i, paramType, paramTypes64[i], paramData, false, 2);
			}
		}
		outputln("\t}");
	}
	return genFailTag;
}

void generateSetters(Method method, Class[] paramTypes, Class[] paramTypes64) {
	int criticalCount = 0;
	for (int i = paramTypes.length - 1; i >= 0; i--) {
		Class paramType = paramTypes[i];
		ParameterData paramData = getMetaData().getMetaData(method, i);
		if (isCritical(paramType, paramData)) {
			criticalCount++;
		}
	}
	if (criticalCount != 0) {
		outputln("#ifdef JNI_VERSION_1_2");
		outputln("\tif (IS_JNI_1_2) {");
		for (int i = paramTypes.length - 1; i >= 0; i--) {
			Class paramType = paramTypes[i];
			ParameterData paramData = getMetaData().getMetaData(method, i);
			if (isCritical(paramType, paramData)) {
				output("\t");
				generateSetParameter(i, paramType, paramTypes64[i], paramData, true);
			}
		}
		outputln("\t} else");
		outputln("#endif");
		outputln("\t{");
		for (int i = paramTypes.length - 1; i >= 0; i--) {
			Class paramType = paramTypes[i];
			ParameterData paramData = getMetaData().getMetaData(method, i);
			if (isCritical(paramType, paramData)) {
				output("\t");
				generateSetParameter(i, paramType, paramTypes64[i], paramData, false);
			}
		}
		outputln("\t}");
	}
	for (int i = paramTypes.length - 1; i >= 0; i--) {
		Class paramType = paramTypes[i];
		ParameterData paramData = getMetaData().getMetaData(method, i);
		if (!isCritical(paramType, paramData)) {
			generateSetParameter(i, paramType, paramTypes64[i], paramData, false);
		}
	}
}

void generateDynamicFunctionCall(Method method, MethodData methodData, Class[] paramTypes, Class returnType, Class[] paramTypes64, Class returnType64, boolean needsReturn) {
	outputln("/*");
	generateFunctionCall(method, methodData, paramTypes, returnType, paramTypes64, returnType64, needsReturn);
	outputln("*/");
	outputln("\t{");

	String name = method.getName();
	if (name.startsWith("_")) name = name.substring(1);
	if (getPlatform().equals("win32")) {
		outputln("\t\tstatic int initialized = 0;");
		outputln("\t\tstatic HMODULE hm = NULL;");
		outputln("\t\tstatic FARPROC fp = NULL;");
		if (returnType != Void.TYPE) {
			if (needsReturn) {
				outputln("\t\trc = 0;");
			}
		}
		outputln("\t\tif (!initialized) {");
		output("\t\t\tif (!hm) hm = LoadLibrary(");
		output(name);
		outputln("_LIB);");
		output("\t\t\tif (hm) fp = GetProcAddress(hm, \"");
		output(name);
		outputln("\");");
		outputln("\t\t\tinitialized = 1;");
		outputln("\t\t}");
		outputln("\t\tif (fp) {");
		output("\t\t");
		generateFunctionCallLeftSide(method, methodData, returnType, returnType64, needsReturn);
		output("fp");
		generateFunctionCallRightSide(method, methodData, paramTypes, paramTypes64, 0);
		output(";");
		outputln();
		outputln("\t\t}");
	} else if (getPlatform().equals("carbon") || getPlatform().equals("cocoa")) {
		outputln("\t\tstatic int initialized = 0;");
		outputln("\t\tstatic CFBundleRef bundle = NULL;");
		output("\t\ttypedef ");
		output(getTypeSignature2(returnType, returnType != returnType64));
		output(" (*FPTR)(");
		for (int i = 0; i < paramTypes.length; i++) {
			if (i != 0) output(", ");
			Class paramType = paramTypes[i];
			ParameterData paramData = getMetaData().getMetaData(method, i);
			String cast = paramData.getCast();
			if (cast.length() > 2) {
				output(cast.substring(1, cast.length() - 1));
			} else {
				output(getTypeSignature4(paramType, paramData.getFlag(FLAG_STRUCT), paramType != paramTypes64[i]));
			}
		}
		outputln(");");
		outputln("\t\tstatic FPTR fptr;");
		if (returnType != Void.TYPE) {
			if (needsReturn) {
				outputln("\t\trc = 0;");
			}
		}
		outputln("\t\tif (!initialized) {");
		output("\t\t\tif (!bundle) bundle = CFBundleGetBundleWithIdentifier(CFSTR(");
		output(name);
		outputln("_LIB));");
		output("\t\t\tif (bundle) fptr = (FPTR)CFBundleGetFunctionPointerForName(bundle, CFSTR(\"");
		output(name);
		outputln("\"));");
		outputln("\t\t\tinitialized = 1;");
		outputln("\t\t}");
		outputln("\t\tif (fptr) {");
		output("\t\t");
		generateFunctionCallLeftSide(method, methodData, returnType, returnType64, needsReturn);
		output("(*fptr)");
		generateFunctionCallRightSide(method, methodData, paramTypes, paramTypes64, 0);
		output(";");
		outputln();
		outputln("\t\t}");
	} else {
		outputln("\t\tstatic int initialized = 0;");
		outputln("\t\tstatic void *handle = NULL;");
		output("\t\ttypedef ");
		output(getTypeSignature2(returnType, returnType != returnType64));
		output(" (*FPTR)(");
		for (int i = 0; i < paramTypes.length; i++) {
			if (i != 0) output(", ");
			Class paramType = paramTypes[i];
			ParameterData paramData = getMetaData().getMetaData(method, i);
			String cast = paramData.getCast();
			if (cast.length() > 2) {
				output(cast.substring(1, cast.length() - 1));
			} else {
				output(getTypeSignature4(paramType, paramData.getFlag(FLAG_STRUCT), paramType != paramTypes64[i]));
			}
		}
		outputln(");");
		outputln("\t\tstatic FPTR fptr;");
		if (returnType != Void.TYPE) {
			if (needsReturn) {
				outputln("\t\trc = 0;");
			}
		}
		outputln("\t\tif (!initialized) {");
		output("\t\t\tif (!handle) handle = dlopen(");
		output(name);
		outputln("_LIB, RTLD_LAZY);");
		output("\t\t\tif (handle) fptr = (FPTR)dlsym(handle, \"");
		output(name);
		outputln("\");");
		outputln("\t\t\tinitialized = 1;");
		outputln("\t\t}");
		outputln("\t\tif (fptr) {");
		output("\t\t");
		generateFunctionCallLeftSide(method, methodData, returnType, returnType64, needsReturn);
		output("(*fptr)");
		generateFunctionCallRightSide(method, methodData, paramTypes, paramTypes64, 0);
		output(";");
		outputln();
		outputln("\t\t}");
	}

	outputln("\t}");
}

void generateFunctionCallLeftSide(Method method, MethodData methodData, Class returnType, Class returnType64, boolean needsReturn) {
	output("\t");
	if (returnType != Void.TYPE) {
		if (needsReturn) {
			output("rc = ");
		} else {
			output("return ");
		}
		output("(");
		output(getTypeSignature2(returnType, returnType != returnType64));
		output(")");
	}
	if (methodData.getFlag(FLAG_ADDRESS)) {
		output("&");
	}	
	if (methodData.getFlag(FLAG_JNI)) {
		output(isCPP ? "env->" : "(*env)->");
	}
}

void generateFunctionCallRightSide(Method method, MethodData methodData, Class[] paramTypes, Class[] paramTypes64, int paramStart) {
	if (!methodData.getFlag(FLAG_CONST)) {
		output("(");
		if (methodData.getFlag(FLAG_JNI)) {
			if (!isCPP) output("env, ");
		}
		for (int i = paramStart; i < paramTypes.length; i++) {
			Class paramType = paramTypes[i];
			ParameterData paramData = getMetaData().getMetaData(method, i);
			if (i != paramStart) output(", ");
			if (paramData.getFlag(FLAG_STRUCT)) output("*");
			output(paramData.getCast());
			if (paramData.getFlag(FLAG_GCOBJECT)) output("TO_OBJECT(");
			if (i == paramTypes.length - 1 && paramData.getFlag(FLAG_SENTINEL)) {
				output("NULL");
			} else {
				if (!paramType.isPrimitive() && !isSystemClass(paramType)) output("lp");
				output("arg" + i);
			}
			if (paramData.getFlag(FLAG_GCOBJECT)) output(")");
		}
		output(")");
	}
}

void generateFunctionCall(Method method, MethodData methodData, Class[] paramTypes, Class returnType, Class[] paramTypes64, Class returnType64, boolean needsReturn) {
	String name = method.getName();
	boolean objc_struct = false;
	if (name.equals("objc_msgSend_stret")) {
		objc_struct = true;
		outputln("\tif (sizeof(_arg0) > STRUCT_SIZE_LIMIT) {");
		output("\t");
	}
	String copy = (String)methodData.getParam("copy");
	boolean makeCopy = copy.length() != 0 && isCPP && returnType != Void.TYPE;
	if (makeCopy) {
		output("\t");
		output(copy);
		output(" temp = ");
	} else {
		generateFunctionCallLeftSide(method, methodData, returnType, returnType64, needsReturn);
	}
	int paramStart = 0;
	if (name.startsWith("_")) name = name.substring(1);
	if (name.equalsIgnoreCase("call")) {
		output("(");
		ParameterData paramData = getMetaData().getMetaData(method, 0);
		String cast = paramData.getCast(); 
		if (cast.length() != 0 && !cast.equals("()")) {
			output(cast);
		} else {
			output("(");
			output(getTypeSignature2(returnType, returnType != returnType64));
			output(" (*)())");
		}
		output("arg0)");
		paramStart = 1;
	} else if (name.startsWith("VtblCall")) {
		output("((");
		output(getTypeSignature2(returnType, returnType != returnType64));
		output(" (STDMETHODCALLTYPE *)(");
		for (int i = 1; i < paramTypes.length; i++) {
			if (i != 1) output(", ");
			Class paramType = paramTypes[i];
			output(getTypeSignature4(paramType, false, paramType != paramTypes64[i]));
		}
		output("))(*(");
		output(getTypeSignature4(paramTypes[1], false, paramTypes[1] != paramTypes64[1]));
		output(" **)arg1)[arg0])");
		paramStart = 1;
	} else if (methodData.getFlag(FLAG_CPP) || methodData.getFlag(FLAG_SETTER) || methodData.getFlag(FLAG_GETTER) || methodData.getFlag(FLAG_ADDER)) {
		if (methodData.getFlag(FLAG_GCOBJECT)) {
			output("TO_HANDLE(");
		}
		output("(");
		ParameterData paramData = getMetaData().getMetaData(method, 0);
		if (paramData.getFlag(FLAG_STRUCT)) output("*");
		String cast = paramData.getCast(); 
		if (cast.length() != 0 && !cast.equals("()")) {
			output(cast);
		}
		if (paramData.getFlag(FLAG_GCOBJECT)) {
			output("TO_OBJECT(");
		}
		output("arg0");
		if (paramData.getFlag(FLAG_GCOBJECT)) {
			output(")");
		}
		output(")->");
		String accessor = methodData.getAccessor();
		if (accessor.length() != 0) {
			output(accessor);
		} else {
			int index = -1;
			if ((index = name.indexOf('_')) != -1) {
				output(name.substring(index + 1, name.length()));
			} else {
				output(name);
			}
		}
		paramStart = 1;
	} else if (methodData.getFlag(FLAG_GCNEW)) {
		output("TO_HANDLE(gcnew ");
		String accessor = methodData.getAccessor();
		if (accessor.length() != 0) {
			output(accessor);
		} else {
			int index = -1;
			if ((index = name.indexOf('_')) != -1) {
				output(name.substring(index + 1));
			} else {
				output(name);
			}
		}
	} else if (methodData.getFlag(FLAG_NEW)) {
		output("new ");
		String accessor = methodData.getAccessor();
		if (accessor.length() != 0) {
			output(accessor);
		} else {
			int index = -1;
			if ((index = name.indexOf('_')) != -1) {
				output(name.substring(0, index));
			} else {
				output(name);
			}
		}
	} else if (methodData.getFlag(FLAG_DELETE)) {
		output("delete ");
		ParameterData paramData = getMetaData().getMetaData(method, 0);
		String cast = paramData.getCast(); 
		if (cast.length() != 0 && !cast.equals("()")) {
			output(cast);
		} else {
			output("(");
			output(name.substring(0, name.indexOf("_")));
			output(" *)");
		}
		outputln("arg0;");
		return;
	} else {
		if (methodData.getFlag(FLAG_GCOBJECT)) {
			output("TO_HANDLE(");				
		}
		if (methodData.getFlag(Flags.FLAG_CAST)) {
			output("((");
			output(getTypeSignature2(returnType, returnType != returnType64));
			output(" (*)(");
			for (int i = 0; i < paramTypes.length; i++) {
				if (i != 0) output(", ");
				Class paramType = paramTypes[i];
				ParameterData paramData = getMetaData().getMetaData(method, i);
				String cast = paramData.getCast();
				if (cast != null && cast.length() != 0) {
					if (cast.startsWith("(")) cast = cast.substring(1);
					if (cast.endsWith(")")) cast = cast.substring(0, cast.length() - 1);
					output(cast);
				} else {
					output(getTypeSignature4(paramType, paramData.getFlag(FLAG_STRUCT), paramType != paramTypes64[i]));
				}
			}
			output("))");
		}
		String accessor = methodData.getAccessor();
		if (accessor.length() != 0) {
			output(accessor);
		} else {
			output(name);
		}
		if (methodData.getFlag(Flags.FLAG_CAST)) {
			output(")");
		}
	}
	if ((methodData.getFlag(FLAG_SETTER) && paramTypes.length == 3) || (methodData.getFlag(FLAG_GETTER) && paramTypes.length == 2)) {
		output("[arg1]");
		paramStart++;
	}
	if (methodData.getFlag(FLAG_SETTER)) output(" = ");
	if (methodData.getFlag(FLAG_ADDER)) output(" += ");
	if (!methodData.getFlag(FLAG_GETTER)) {
		generateFunctionCallRightSide(method, methodData, paramTypes, paramTypes64, paramStart);
	}
	if (methodData.getFlag(FLAG_GCNEW) || methodData.getFlag(FLAG_GCOBJECT)) {
		output(")");
	}
	output(";");
	outputln();
	if (makeCopy) {
		outputln("\t{");
		output("\t\t");
		output(copy);
		output("* copy = new ");
		output(copy);
		outputln("();");
		outputln("\t\t*copy = temp;");
		output("\t\trc = ");
		output("(");
		output(getTypeSignature2(returnType, returnType != returnType64));
		output(")");
		outputln("copy;");
		outputln("\t}");
	}
	if (objc_struct) {
		outputln("\t} else {");
		output("\t\t*lparg0 = (*(");
		output(getTypeSignature4(paramTypes[0], true, paramTypes[0] != paramTypes64[0]));
		output(" (*)(");
		for (int i = 1; i < paramTypes.length; i++) {
			if (i != 1) output(", ");
			Class paramType = paramTypes[i];
			ParameterData paramData = getMetaData().getMetaData(method, i);
			String cast = paramData.getCast();
			if (cast != null && cast.length() != 0) {
				if (cast.startsWith("(")) cast = cast.substring(1);
				if (cast.endsWith(")")) cast = cast.substring(0, cast.length() - 1);
				output(cast);
			} else {
				output(getTypeSignature4(paramType, paramData.getFlag(FLAG_STRUCT), paramType != paramTypes64[i]));
			}
		}
		output("))objc_msgSend)");
		generateFunctionCallRightSide(method, methodData, paramTypes, paramTypes64, 1);
		outputln(";");
		outputln("\t}");
	}
}

void generateReturn(Method method, Class returnType, Class returnType64, boolean needsReturn) {
	if (needsReturn && returnType != Void.TYPE) {
		outputln("\treturn rc;");
	}
}

void generateMemmove(Method method, String function, Class[] paramTypes, String function64, Class[] paramTypes64) {
	generateEnterMacro(method, function, function64);
	output("\t");
	boolean get = paramTypes[0].isPrimitive();
	String className = getClassName(paramTypes[get ? 1 : 0]);
	output(get ? "if (arg1) get" : "if (arg0) set");
	output(className);
	output(get ? "Fields(env, arg1, (" : "Fields(env, arg0, (");
	output(className);
	output(get ? " *)arg0)" : " *)arg1)");
	outputln(";");
	generateExitMacro(method, function, function64);	
}

void generateFunctionBody(Method method, MethodData methodData, String function, Class[] paramTypes, Class returnType, String function64, Class[] paramTypes64, Class returnType64) {
	outputln("{");
	
	/* Custom GTK memmoves. */
	String name = method.getName();
	if (name.startsWith("_")) name = name.substring(1);
	boolean isMemove = (name.equals("memmove") || name.equals("MoveMemory")) && paramTypes.length == 2 && returnType == Void.TYPE;
	if (isMemove) {
		generateMemmove(method, function, paramTypes, function64, paramTypes64);
	} else {
		boolean needsReturn = generateLocalVars(method, paramTypes, returnType, paramTypes64, returnType64);
		generateEnterMacro(method, function, function64);
		boolean genFailTag = generateGetters(method, paramTypes, paramTypes64);
		if (methodData.getFlag(FLAG_DYNAMIC)) {
			generateDynamicFunctionCall(method, methodData, paramTypes, returnType, paramTypes64, returnType64, needsReturn);
		} else {
			generateFunctionCall(method, methodData, paramTypes, returnType, paramTypes64, returnType64, needsReturn);
		}
		if (genFailTag) outputln("fail:");
		generateSetters(method, paramTypes, paramTypes64);
		generateExitMacro(method, function, function64);
		generateReturn(method, returnType, returnType64, needsReturn);
	}
	
	outputln("}");
}

void generateFunctionPrototype(Method method, String function, Class[] paramTypes, Class returnType, Class[] paramTypes64, Class returnType64, boolean singleLine) {
	output("JNIEXPORT ");
	output(getTypeSignature2(returnType, returnType != returnType64));
	output(" JNICALL ");
	output(getClassName(method.getDeclaringClass()));
	output("_NATIVE(");
	output(function);
	if (singleLine) {
		output(")");
		output("(JNIEnv *env, ");
	} else {
		outputln(")");
		output("\t(JNIEnv *env, ");
	}
	if ((method.getModifiers() & Modifier.STATIC) != 0) {
		output("jclass");
	} else {
		output("jobject");
	}
	output(" that");
	for (int i = 0; i < paramTypes.length; i++) {
		Class paramType = paramTypes[i];
		output(", ");
		output(getTypeSignature2(paramType, paramType != paramTypes64[i]));
		output(" arg" + i);
	}
	output(")");
	if (!singleLine) outputln();
}

void generateSourceStart(String function, String function64) {
	if (function64 == null || function.equals(function64)) {
		output("#ifndef NO_");
		outputln(function);
	} else {
		output("#if (!defined(NO_");
		output(function);
		output(") && !defined(SWT_PTR_SIZE_64)) || (!defined(");
		output(function64);
		outputln(") && defined(SWT_PTR_SIZE_64))");
	}
}

void generateSourceEnd(String function, String function64) {
	outputln("#endif");
}

boolean isCritical(Class paramType, ParameterData paramData) {
	return paramType.isArray() && paramType.getComponentType().isPrimitive() && paramData.getFlag(FLAG_CRITICAL);
}

boolean isSystemClass(Class type) {
	return type == Object.class || type == Class.class;
}

public static void main(String[] args) {
//	args = new String[]{"org.eclipse.swt.internal.win32.OS"};
	if (args.length < 1) {
		System.out.println("Usage: java NativesGenerator <className1> <className2>");
		return;
	}
	try {
		NativesGenerator gen = new NativesGenerator();
		for (int i = 0; i < args.length; i++) {
			String clazzName = args[i];
			Class clazz = Class.forName(clazzName);
			gen.generate(clazz);
//			gen.generate(clazz, "CommandBar_Destroy");
		}
	} catch (Exception e) {
		System.out.println("Problem");
		e.printStackTrace(System.out);
	}
}

}
