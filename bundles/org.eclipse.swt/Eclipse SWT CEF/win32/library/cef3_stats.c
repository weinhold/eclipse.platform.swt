/*******************************************************************************
 * Copyright (c) 2000, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *******************************************************************************/

#include "swt.h"
#include "cef3_stats.h"

#ifdef NATIVE_STATS

char * CEF3_nativeFunctionNames[] = {
	"callFuncInt",
	"callFuncPtr",
#ifndef JNI64
	"callFuncVoid__II",
#else
	"callFuncVoid__JJ",
#endif
#ifndef JNI64
	"callFuncVoid__III",
#else
	"callFuncVoid__JJJ",
#endif
#ifndef JNI64
	"callFuncVoid__IIII",
#else
	"callFuncVoid__JJJJ",
#endif
#ifndef JNI64
	"callFuncVoid__IIIII",
#else
	"callFuncVoid__JJJJI",
#endif
	"cef_1base_1t_1sizeof",
	"cef_1browser_1host_1create_1browser",
	"cef_1browser_1host_1t_1sizeof",
	"cef_1browser_1settings_1t_1sizeof",
	"cef_1browser_1t_1sizeof",
	"cef_1build_1revision",
	"cef_1execute_1process",
	"cef_1frame_1t_1sizeof",
	"cef_1initialize",
	"cef_1settings_1t_1sizeof",
	"cef_1string_1clear",
	"cef_1string_1set",
	"cef_1string_1t_1sizeof",
	"cef_1string_1userfree_1alloc",
	"cef_1string_1userfree_1free",
#ifndef JNI64
	"memmove__Lorg_eclipse_swt_internal_cef3_cef_1base_1t_2II",
#else
	"memmove__Lorg_eclipse_swt_internal_cef3_cef_1base_1t_2JJ",
#endif
#ifndef JNI64
	"memmove__Lorg_eclipse_swt_internal_cef3_cef_1browser_1host_1t_2II",
#else
	"memmove__Lorg_eclipse_swt_internal_cef3_cef_1browser_1host_1t_2JJ",
#endif
#ifndef JNI64
	"memmove__Lorg_eclipse_swt_internal_cef3_cef_1browser_1t_2II",
#else
	"memmove__Lorg_eclipse_swt_internal_cef3_cef_1browser_1t_2JJ",
#endif
#ifndef JNI64
	"memmove__Lorg_eclipse_swt_internal_cef3_cef_1frame_1t_2II",
#else
	"memmove__Lorg_eclipse_swt_internal_cef3_cef_1frame_1t_2JJ",
#endif
#ifndef JNI64
	"memmove__Lorg_eclipse_swt_internal_cef3_cef_1string_1t_2II",
#else
	"memmove__Lorg_eclipse_swt_internal_cef3_cef_1string_1t_2JJ",
#endif
};
#define NATIVE_FUNCTION_COUNT sizeof(CEF3_nativeFunctionNames) / sizeof(char*)
int CEF3_nativeFunctionCount = NATIVE_FUNCTION_COUNT;
int CEF3_nativeFunctionCallCount[NATIVE_FUNCTION_COUNT];

#define STATS_NATIVE(func) Java_org_eclipse_swt_tools_internal_NativeStats_##func

JNIEXPORT jint JNICALL STATS_NATIVE(CEF3_1GetFunctionCount)
	(JNIEnv *env, jclass that)
{
	return CEF3_nativeFunctionCount;
}

JNIEXPORT jstring JNICALL STATS_NATIVE(CEF3_1GetFunctionName)
	(JNIEnv *env, jclass that, jint index)
{
	return (*env)->NewStringUTF(env, CEF3_nativeFunctionNames[index]);
}

JNIEXPORT jint JNICALL STATS_NATIVE(CEF3_1GetFunctionCallCount)
	(JNIEnv *env, jclass that, jint index)
{
	return CEF3_nativeFunctionCallCount[index];
}

#endif
