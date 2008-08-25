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

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Iterator;

public class NativesGenerator extends JNIGenerator {

boolean enterExitMacro;

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

public void generate(JNIClass clazz, String methodName) {
	JNIMethod[] methods = clazz.getDeclaredMethods();
	int count = 0;
	for (int i = 0; i < methods.length; i++) {
		if (methods[i].getName().startsWith(methodName)) count++;
	}
	JNIMethod[] result = new JNIMethod[count];
	count = 0;
	for (int i = 0; i < methods.length; i++) {
		if (methods[i].getName().startsWith(methodName)) result[count++] = methods[i];
	}
	generate(result);
}

public void generate(JNIClass clazz) {
	JNIMethod[] methods = clazz.getDeclaredMethods();
	int i = 0;
	for (; i < methods.length; i++) {
		JNIMethod method = methods[i];
		if ((method.getModifiers() & Modifier.NATIVE) != 0) break;
	}
	if (i == methods.length) return;
	sort(methods);
	generateNativeMacro(clazz);
	generateExcludes(methods);
	generate(methods);
}

public void generate(JNIMethod[] methods) {
	sort(methods);	
	for (int i = 0; i < methods.length; i++) {
		JNIMethod method = methods[i];
		if ((method.getModifiers() & Modifier.NATIVE) == 0) continue;
		generate(method);
		if (progress != null) progress.step();
	}
}

public void generate(JNIMethod method) {
	MethodData methodData = getMetaData().getMetaData(method);
	if (methodData.getFlag(FLAG_NO_GEN)) return;
	JNIClass returnType = method.getReturnType();
	JNIClass[] paramTypes = method.getParameterTypes();
	String function = getFunctionName(method);
	
	if (!(returnType.isType("void") || returnType.isPrimitive() || isSystemClass(returnType) || returnType.isType("java.lang.String"))) {
		output("Warning: bad return type. :");
		outputln(method.toString());
		return;
	}
	
	generateSourceStart(function);
	if (isCPP) {
		output("extern \"C\" ");
		generateFunctionPrototype(method, function, paramTypes, returnType, true);
		outputln(";");
	}
	generateFunctionPrototype(method, function, paramTypes, returnType, false);
	generateFunctionBody(method, methodData, function, paramTypes, returnType);
	generateSourceEnd(function);
	outputln();
}

public void setEnterExitMacro(boolean enterExitMacro) {
	this.enterExitMacro = enterExitMacro;
}

void generateExcludes(JNIMethod[] methods) {
	HashSet excludes = new HashSet();
	for (int i = 0; i < methods.length; i++) {
		JNIMethod method = methods[i];
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
			JNIMethod method = methods[i];
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

void generateNativeMacro(JNIClass clazz) {
	output("#define ");
	output(clazz.getSimpleName());
	output("_NATIVE(func) Java_");
	output(toC(clazz.getName()));
	outputln("_##func");
	outputln();
}

boolean generateGetParameter(JNIMethod method, int i, JNIClass paramType, ParameterData paramData, boolean critical, int indent) {
	if (paramType.isPrimitive() || isSystemClass(paramType)) return false;
	String iStr = String.valueOf(i);
	for (int j = 0; j < indent; j++) output("\t");
	output("if (arg");
	output(iStr);
	output(") if ((lparg");
	output(iStr);
	output(" = ");
	if (paramType.isArray()) {
		JNIClass componentType = paramType.getComponentType();
		if (componentType.isPrimitive()) {
			if (critical) {
				if (isCPP) {
					output("(");
					output(componentType.getTypeSignature2());
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
				output(componentType.getTypeSignature1());
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
	} else if (paramType.isType("java.lang.String")) {
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
			output(paramType.getSimpleName());
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

void generateSetParameter(int i, JNIClass paramType, ParameterData paramData, boolean critical) {
	if (paramType.isPrimitive() || isSystemClass(paramType)) return;
	String iStr = String.valueOf(i);
	if (paramType.isArray()) {
		output("\tif (arg");
		output(iStr);
		output(" && lparg");
		output(iStr);
		output(") ");
		JNIClass componentType = paramType.getComponentType();
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
				output(componentType.getTypeSignature1());
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
	} else if (paramType.isType("java.lang.String")) {
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
			output(paramType.getSimpleName());
			output("Fields(env, arg");
			output(iStr);
			output(", lparg");
			output(iStr);
			outputln(");");
		}
	}
}

void generateExitMacro(JNIMethod method, String function) {
	if (!enterExitMacro) return;
	output("\t");
	output(method.getDeclaringClass().getSimpleName());
	output("_NATIVE_EXIT(env, that, ");
	output(function);
	outputln("_FUNC);");
}

void generateEnterMacro(JNIMethod method, String function) {
	if (!enterExitMacro) return;
	output("\t");
	output(method.getDeclaringClass().getSimpleName());
	output("_NATIVE_ENTER(env, that, ");
	output(function);
	outputln("_FUNC);");
}

boolean generateLocalVars(JNIMethod method, JNIClass[] paramTypes, JNIClass returnType) {
	boolean needsReturn = enterExitMacro;
	for (int i = 0; i < paramTypes.length; i++) {
		JNIClass paramType = paramTypes[i];
		if (paramType.isPrimitive() || isSystemClass(paramType)) continue;
		ParameterData paramData = getMetaData().getMetaData(method, i);
		output("\t");
		if (paramType.isArray()) {
			JNIClass componentType = paramType.getComponentType();
			if (componentType.isPrimitive()) {
				output(componentType.getTypeSignature2());
				output(" *lparg" + i);
				output("=NULL;");
			} else {
				throw new Error("not done");
			}
		} else if (paramType.isType("java.lang.String")) {
			if (paramData.getFlag(FLAG_UNICODE)) {
				output("const jchar *lparg" + i);				
			} else {
				output("const char *lparg" + i);
			}
			output("= NULL;");
		} else {
			if (paramType.getFlag(Flags.FLAG_STRUCT)) {
				output("struct ");
			}
			output(paramType.getSimpleName());
			output(" _arg" + i);
			if (paramData.getFlag(FLAG_INIT)) output("={0}");
			output(", *lparg" + i);
			output("=NULL;");
		}
		outputln();
		needsReturn = true;
	}
	if (needsReturn) {
		if (!returnType.isType("void")) {
			output("\t");
			output(returnType.getTypeSignature2());
			outputln(" rc = 0;");
		}
	}
	return needsReturn;
}

boolean generateGetters(JNIMethod method, JNIClass[] paramTypes) {
	boolean genFailTag = false;
	int criticalCount = 0;
	for (int i = 0; i < paramTypes.length; i++) {
		JNIClass paramType = paramTypes[i];
		ParameterData paramData = getMetaData().getMetaData(method, i);
		if (!isCritical(paramType, paramData)) {
			genFailTag |= generateGetParameter(method, i, paramType, paramData, false, 1);
		} else {
			criticalCount++;
		}
	}
	if (criticalCount != 0) {
		outputln("#ifdef JNI_VERSION_1_2");
		outputln("\tif (IS_JNI_1_2) {");
		for (int i = 0; i < paramTypes.length; i++) {
			JNIClass paramType = paramTypes[i];
			ParameterData paramData = getMetaData().getMetaData(method, i);
			if (isCritical(paramType, paramData)) {
				genFailTag |= generateGetParameter(method, i, paramType, paramData, true, 2);
			}
		}
		outputln("\t} else");
		outputln("#endif");
		outputln("\t{");
		for (int i = 0; i < paramTypes.length; i++) {
			JNIClass paramType = paramTypes[i];
			ParameterData paramData = getMetaData().getMetaData(method, i);
			if (isCritical(paramType, paramData)) {
				genFailTag |= generateGetParameter(method, i, paramType, paramData, false, 2);
			}
		}
		outputln("\t}");
	}
	return genFailTag;
}

void generateSetters(JNIMethod method, JNIClass[] paramTypes) {
	int criticalCount = 0;
	for (int i = paramTypes.length - 1; i >= 0; i--) {
		JNIClass paramType = paramTypes[i];
		ParameterData paramData = getMetaData().getMetaData(method, i);
		if (isCritical(paramType, paramData)) {
			criticalCount++;
		}
	}
	if (criticalCount != 0) {
		outputln("#ifdef JNI_VERSION_1_2");
		outputln("\tif (IS_JNI_1_2) {");
		for (int i = paramTypes.length - 1; i >= 0; i--) {
			JNIClass paramType = paramTypes[i];
			ParameterData paramData = getMetaData().getMetaData(method, i);
			if (isCritical(paramType, paramData)) {
				output("\t");
				generateSetParameter(i, paramType, paramData, true);
			}
		}
		outputln("\t} else");
		outputln("#endif");
		outputln("\t{");
		for (int i = paramTypes.length - 1; i >= 0; i--) {
			JNIClass paramType = paramTypes[i];
			ParameterData paramData = getMetaData().getMetaData(method, i);
			if (isCritical(paramType, paramData)) {
				output("\t");
				generateSetParameter(i, paramType, paramData, false);
			}
		}
		outputln("\t}");
	}
	for (int i = paramTypes.length - 1; i >= 0; i--) {
		JNIClass paramType = paramTypes[i];
		ParameterData paramData = getMetaData().getMetaData(method, i);
		if (!isCritical(paramType, paramData)) {
			generateSetParameter(i, paramType, paramData, false);
		}
	}
}

void generateDynamicFunctionCall(JNIMethod method, MethodData methodData, JNIClass[] paramTypes, JNIClass returnType, boolean needsReturn) {
	outputln("/*");
	generateFunctionCall(method, methodData, paramTypes, returnType, needsReturn);
	outputln("*/");
	outputln("\t{");

	String name = method.getName();
	if (name.startsWith("_")) name = name.substring(1);
	if (getPlatform().equals("win32")) {
		outputln("\t\tstatic int initialized = 0;");
		outputln("\t\tstatic HMODULE hm = NULL;");
		outputln("\t\tstatic FARPROC fp = NULL;");
		if (!returnType.isType("void")) {
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
		generateFunctionCallLeftSide(method, methodData, returnType, needsReturn);
		output("fp");
		generateFunctionCallRightSide(method, methodData, paramTypes, 0);
		output(";");
		outputln();
		outputln("\t\t}");
	} else if (getPlatform().equals("carbon") || getPlatform().equals("cocoa")) {
		outputln("\t\tstatic int initialized = 0;");
		outputln("\t\tstatic CFBundleRef bundle = NULL;");
		output("\t\ttypedef ");
		output(returnType.getTypeSignature2());
		output(" (*FPTR)(");
		for (int i = 0; i < paramTypes.length; i++) {
			if (i != 0) output(", ");
			JNIClass paramType = paramTypes[i];
			ParameterData paramData = getMetaData().getMetaData(method, i);
			String cast = paramData.getCast();
			if (cast.length() > 2) {
				output(cast.substring(1, cast.length() - 1));
			} else {
				output(paramType.getTypeSignature4(paramData.getFlag(FLAG_STRUCT)));
			}
		}
		outputln(");");
		outputln("\t\tstatic FPTR fptr;");
		if (!returnType.isType("void")) {
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
		generateFunctionCallLeftSide(method, methodData, returnType, needsReturn);
		output("(*fptr)");
		generateFunctionCallRightSide(method, methodData, paramTypes, 0);
		output(";");
		outputln();
		outputln("\t\t}");
	} else {
		outputln("\t\tstatic int initialized = 0;");
		outputln("\t\tstatic void *handle = NULL;");
		output("\t\ttypedef ");
		output(returnType.getTypeSignature2());
		output(" (*FPTR)(");
		for (int i = 0; i < paramTypes.length; i++) {
			if (i != 0) output(", ");
			JNIClass paramType = paramTypes[i];
			ParameterData paramData = getMetaData().getMetaData(method, i);
			String cast = paramData.getCast();
			if (cast.length() > 2) {
				output(cast.substring(1, cast.length() - 1));
			} else {
				output(paramType.getTypeSignature4(paramData.getFlag(FLAG_STRUCT)));
			}
		}
		outputln(");");
		outputln("\t\tstatic FPTR fptr;");
		if (!returnType.isType("void")) {
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
		generateFunctionCallLeftSide(method, methodData, returnType, needsReturn);
		output("(*fptr)");
		generateFunctionCallRightSide(method, methodData, paramTypes, 0);
		output(";");
		outputln();
		outputln("\t\t}");
	}

	outputln("\t}");
}

void generateFunctionCallLeftSide(JNIMethod method, MethodData methodData, JNIClass returnType, boolean needsReturn) {
	output("\t");
	if (!returnType.isType("void")) {
		if (needsReturn) {
			output("rc = ");
		} else {
			output("return ");
		}
		output("(");
		output(returnType.getTypeSignature2());
		output(")");
	}
	if (methodData.getFlag(FLAG_ADDRESS)) {
		output("&");
	}	
	if (methodData.getFlag(FLAG_JNI)) {
		output(isCPP ? "env->" : "(*env)->");
	}
}

void generateFunctionCallRightSide(JNIMethod method, MethodData methodData, JNIClass[] paramTypes, int paramStart) {
	if (!methodData.getFlag(FLAG_CONST)) {
		output("(");
		if (methodData.getFlag(FLAG_JNI)) {
			if (!isCPP) output("env, ");
		}
		for (int i = paramStart; i < paramTypes.length; i++) {
			JNIClass paramType = paramTypes[i];
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

void generateFunctionCall(JNIMethod method, MethodData methodData, JNIClass[] paramTypes, JNIClass returnType, boolean needsReturn) {
	String name = method.getName();
	boolean objc_struct = false;
	if (name.equals("objc_msgSend_stret")) {
		objc_struct = true;
		outputln("\tif (sizeof(_arg0) > STRUCT_SIZE_LIMIT) {");
		output("\t");
	}
	String copy = (String)methodData.getParam("copy");
	boolean makeCopy = copy.length() != 0 && isCPP && !returnType.isType("void");
	if (makeCopy) {
		output("\t");
		output(copy);
		output(" temp = ");
	} else {
		generateFunctionCallLeftSide(method, methodData, returnType, needsReturn);
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
			output(returnType.getTypeSignature2());
			output(" (*)())");
		}
		output("arg0)");
		paramStart = 1;
	} else if (name.startsWith("VtblCall")) {
		output("((");
		output(returnType.getTypeSignature2());
		output(" (STDMETHODCALLTYPE *)(");
		for (int i = 1; i < paramTypes.length; i++) {
			if (i != 1) output(", ");
			JNIClass paramType = paramTypes[i];
			output(paramType.getTypeSignature4());
		}
		output("))(*(");
		output(paramTypes[1].getTypeSignature4());
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
			output(returnType.getTypeSignature2());
			output(" (*)(");
			for (int i = 0; i < paramTypes.length; i++) {
				if (i != 0) output(", ");
				JNIClass paramType = paramTypes[i];
				ParameterData paramData = getMetaData().getMetaData(method, i);
				String cast = paramData.getCast();
				if (cast != null && cast.length() != 0) {
					if (cast.startsWith("(")) cast = cast.substring(1);
					if (cast.endsWith(")")) cast = cast.substring(0, cast.length() - 1);
					output(cast);
				} else {
					output(paramType.getTypeSignature4(paramData.getFlag(FLAG_STRUCT)));
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
		generateFunctionCallRightSide(method, methodData, paramTypes, paramStart);
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
		output(returnType.getTypeSignature2());
		output(")");
		outputln("copy;");
		outputln("\t}");
	}
	if (objc_struct) {
		outputln("\t} else {");
		output("\t\t*lparg0 = (*(");
		output(paramTypes[0].getTypeSignature4(true));
		output(" (*)(");
		for (int i = 1; i < paramTypes.length; i++) {
			if (i != 1) output(", ");
			JNIClass paramType = paramTypes[i];
			ParameterData paramData = getMetaData().getMetaData(method, i);
			String cast = paramData.getCast();
			if (cast != null && cast.length() != 0) {
				if (cast.startsWith("(")) cast = cast.substring(1);
				if (cast.endsWith(")")) cast = cast.substring(0, cast.length() - 1);
				output(cast);
			} else {
				output(paramType.getTypeSignature4(paramData.getFlag(FLAG_STRUCT)));
			}
		}
		output("))objc_msgSend)");
		generateFunctionCallRightSide(method, methodData, paramTypes, 1);
		outputln(";");
		outputln("\t}");
	}
}

void generateReturn(JNIMethod method, JNIClass returnType, boolean needsReturn) {
	if (needsReturn && !returnType.isType("void")) {
		outputln("\treturn rc;");
	}
}

void generateMemmove(JNIMethod method, String function, JNIClass[] paramTypes) {
	generateEnterMacro(method, function);
	output("\t");
	boolean get = paramTypes[0].isPrimitive();
	String className = paramTypes[get ? 1 : 0].getSimpleName();
	output(get ? "if (arg1) get" : "if (arg0) set");
	output(className);
	output(get ? "Fields(env, arg1, (" : "Fields(env, arg0, (");
	output(className);
	output(get ? " *)arg0)" : " *)arg1)");
	outputln(";");
	generateExitMacro(method, function);	
}

void generateFunctionBody(JNIMethod method, MethodData methodData, String function, JNIClass[] paramTypes, JNIClass returnType) {
	outputln("{");
	
	/* Custom GTK memmoves. */
	String name = method.getName();
	if (name.startsWith("_")) name = name.substring(1);
	boolean isMemove = (name.equals("memmove") || name.equals("MoveMemory")) && paramTypes.length == 2 && returnType.isType("void");
	if (isMemove) {
		generateMemmove(method, function, paramTypes);
	} else {
		boolean needsReturn = generateLocalVars(method, paramTypes, returnType);
		generateEnterMacro(method, function);
		boolean genFailTag = generateGetters(method, paramTypes);
		if (methodData.getFlag(FLAG_DYNAMIC)) {
			generateDynamicFunctionCall(method, methodData, paramTypes, returnType, needsReturn);
		} else {
			generateFunctionCall(method, methodData, paramTypes, returnType, needsReturn);
		}
		if (genFailTag) outputln("fail:");
		generateSetters(method, paramTypes);
		generateExitMacro(method, function);
		generateReturn(method, returnType, needsReturn);
	}
	
	outputln("}");
}

void generateFunctionPrototype(JNIMethod method, String function, JNIClass[] paramTypes, JNIClass returnType, boolean singleLine) {
	output("JNIEXPORT ");
	output(returnType.getTypeSignature2());
	output(" JNICALL ");
	output(method.getDeclaringClass().getSimpleName());
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
		JNIClass paramType = paramTypes[i];
		output(", ");
		output(paramType.getTypeSignature2());
		output(" arg" + i);
	}
	output(")");
	if (!singleLine) outputln();
}

void generateSourceStart(String function) {
	output("#ifndef NO_");
	outputln(function);
}

void generateSourceEnd(String function) {
	outputln("#endif");
}

boolean isCritical(JNIClass paramType, ParameterData paramData) {
	return paramType.isArray() && paramType.getComponentType().isPrimitive() && paramData.getFlag(FLAG_CRITICAL);
}

boolean isSystemClass(JNIClass type) {
	return type.isType("java.lang.Object") || type.isType("java.lang.Class") ;
}

}
