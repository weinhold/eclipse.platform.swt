/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others.
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
	"cef_1app_1t_1sizeof",
	"cef_1browser_1host_1create_1browser",
	"cef_1browser_1host_1create_1browser_1sync",
	"cef_1browser_1process_1handler_1t_1sizeof",
	"cef_1browser_1settings_1t_1sizeof",
	"cef_1client_1t_1sizeof",
	"cef_1command_1line_1t_1sizeof",
	"cef_1context_1menu_1handler_1t_1sizeof",
	"cef_1display_1handler_1t_1sizeof",
	"cef_1execute_1process",
	"cef_1focus_1handler_1t_1sizeof",
	"cef_1initialize",
	"cef_1life_1span_1handler_1t_1sizeof",
	"cef_1load_1handler_1t_1sizeof",
	"cef_1proxy_1handler_1t_1sizeof",
	"cef_1request_1handler_1t_1sizeof",
	"cef_1resource_1bundle_1handler_1t_1sizeof",
	"cef_1settings_1t_1sizeof",
	"cef_1string_1clear",
	"cef_1string_1set",
	"cef_1string_1t_1sizeof",
	"cef_1string_1userfree_1alloc",
	"cef_1string_1userfree_1free",
#ifndef JNI64
	"memmove__ILorg_eclipse_swt_internal_cef3_cef_1app_1t_2I",
#else
	"memmove__JLorg_eclipse_swt_internal_cef3_cef_1app_1t_2J",
#endif
#ifndef JNI64
	"memmove__ILorg_eclipse_swt_internal_cef3_cef_1browser_1process_1handler_1t_2I",
#else
	"memmove__JLorg_eclipse_swt_internal_cef3_cef_1browser_1process_1handler_1t_2J",
#endif
#ifndef JNI64
	"memmove__ILorg_eclipse_swt_internal_cef3_cef_1client_1t_2I",
#else
	"memmove__JLorg_eclipse_swt_internal_cef3_cef_1client_1t_2J",
#endif
#ifndef JNI64
	"memmove__ILorg_eclipse_swt_internal_cef3_cef_1display_1handler_1t_2I",
#else
	"memmove__JLorg_eclipse_swt_internal_cef3_cef_1display_1handler_1t_2J",
#endif
#ifndef JNI64
	"memmove__ILorg_eclipse_swt_internal_cef3_cef_1focus_1handler_1t_2I",
#else
	"memmove__JLorg_eclipse_swt_internal_cef3_cef_1focus_1handler_1t_2J",
#endif
#ifndef JNI64
	"memmove__ILorg_eclipse_swt_internal_cef3_cef_1life_1span_1handler_1t_2I",
#else
	"memmove__JLorg_eclipse_swt_internal_cef3_cef_1life_1span_1handler_1t_2J",
#endif
#ifndef JNI64
	"memmove__ILorg_eclipse_swt_internal_cef3_cef_1load_1handler_1t_2I",
#else
	"memmove__JLorg_eclipse_swt_internal_cef3_cef_1load_1handler_1t_2J",
#endif
#ifndef JNI64
	"memmove__ILorg_eclipse_swt_internal_cef3_cef_1proxy_1handler_1t_2I",
#else
	"memmove__JLorg_eclipse_swt_internal_cef3_cef_1proxy_1handler_1t_2J",
#endif
#ifndef JNI64
	"memmove__ILorg_eclipse_swt_internal_cef3_cef_1request_1handler_1t_2I",
#else
	"memmove__JLorg_eclipse_swt_internal_cef3_cef_1request_1handler_1t_2J",
#endif
#ifndef JNI64
	"memmove__ILorg_eclipse_swt_internal_cef3_cef_1resource_1bundle_1handler_1t_2I",
#else
	"memmove__JLorg_eclipse_swt_internal_cef3_cef_1resource_1bundle_1handler_1t_2J",
#endif
#ifndef JNI64
	"memmove__Lorg_eclipse_swt_internal_cef3_cef_1command_1line_1t_2II",
#else
	"memmove__Lorg_eclipse_swt_internal_cef3_cef_1command_1line_1t_2JJ",
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
