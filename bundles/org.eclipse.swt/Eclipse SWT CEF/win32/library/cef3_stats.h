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

#ifdef NATIVE_STATS
extern int CEF3_nativeFunctionCount;
extern int CEF3_nativeFunctionCallCount[];
extern char* CEF3_nativeFunctionNames[];
#define CEF3_NATIVE_ENTER(env, that, func) CEF3_nativeFunctionCallCount[func]++;
#define CEF3_NATIVE_EXIT(env, that, func) 
#else
#ifndef CEF3_NATIVE_ENTER
#define CEF3_NATIVE_ENTER(env, that, func) 
#endif
#ifndef CEF3_NATIVE_EXIT
#define CEF3_NATIVE_EXIT(env, that, func) 
#endif
#endif

typedef enum {
	cef_1app_1t_1sizeof_FUNC,
	cef_1browser_1host_1create_1browser_FUNC,
	cef_1browser_1host_1create_1browser_1sync_FUNC,
	cef_1browser_1process_1handler_1t_1sizeof_FUNC,
	cef_1browser_1settings_1t_1sizeof_FUNC,
	cef_1client_1t_1sizeof_FUNC,
	cef_1command_1line_1t_1sizeof_FUNC,
	cef_1context_1menu_1handler_1t_1sizeof_FUNC,
	cef_1display_1handler_1t_1sizeof_FUNC,
	cef_1execute_1process_FUNC,
	cef_1focus_1handler_1t_1sizeof_FUNC,
	cef_1initialize_FUNC,
	cef_1life_1span_1handler_1t_1sizeof_FUNC,
	cef_1load_1handler_1t_1sizeof_FUNC,
	cef_1proxy_1handler_1t_1sizeof_FUNC,
	cef_1request_1handler_1t_1sizeof_FUNC,
	cef_1resource_1bundle_1handler_1t_1sizeof_FUNC,
	cef_1settings_1t_1sizeof_FUNC,
	cef_1string_1clear_FUNC,
	cef_1string_1set_FUNC,
	cef_1string_1t_1sizeof_FUNC,
	cef_1string_1userfree_1alloc_FUNC,
	cef_1string_1userfree_1free_FUNC,
#ifndef JNI64
	memmove__ILorg_eclipse_swt_internal_cef3_cef_1app_1t_2I_FUNC,
#else
	memmove__JLorg_eclipse_swt_internal_cef3_cef_1app_1t_2J_FUNC,
#endif
#ifndef JNI64
	memmove__ILorg_eclipse_swt_internal_cef3_cef_1browser_1process_1handler_1t_2I_FUNC,
#else
	memmove__JLorg_eclipse_swt_internal_cef3_cef_1browser_1process_1handler_1t_2J_FUNC,
#endif
#ifndef JNI64
	memmove__ILorg_eclipse_swt_internal_cef3_cef_1client_1t_2I_FUNC,
#else
	memmove__JLorg_eclipse_swt_internal_cef3_cef_1client_1t_2J_FUNC,
#endif
#ifndef JNI64
	memmove__ILorg_eclipse_swt_internal_cef3_cef_1display_1handler_1t_2I_FUNC,
#else
	memmove__JLorg_eclipse_swt_internal_cef3_cef_1display_1handler_1t_2J_FUNC,
#endif
#ifndef JNI64
	memmove__ILorg_eclipse_swt_internal_cef3_cef_1focus_1handler_1t_2I_FUNC,
#else
	memmove__JLorg_eclipse_swt_internal_cef3_cef_1focus_1handler_1t_2J_FUNC,
#endif
#ifndef JNI64
	memmove__ILorg_eclipse_swt_internal_cef3_cef_1life_1span_1handler_1t_2I_FUNC,
#else
	memmove__JLorg_eclipse_swt_internal_cef3_cef_1life_1span_1handler_1t_2J_FUNC,
#endif
#ifndef JNI64
	memmove__ILorg_eclipse_swt_internal_cef3_cef_1load_1handler_1t_2I_FUNC,
#else
	memmove__JLorg_eclipse_swt_internal_cef3_cef_1load_1handler_1t_2J_FUNC,
#endif
#ifndef JNI64
	memmove__ILorg_eclipse_swt_internal_cef3_cef_1proxy_1handler_1t_2I_FUNC,
#else
	memmove__JLorg_eclipse_swt_internal_cef3_cef_1proxy_1handler_1t_2J_FUNC,
#endif
#ifndef JNI64
	memmove__ILorg_eclipse_swt_internal_cef3_cef_1request_1handler_1t_2I_FUNC,
#else
	memmove__JLorg_eclipse_swt_internal_cef3_cef_1request_1handler_1t_2J_FUNC,
#endif
#ifndef JNI64
	memmove__ILorg_eclipse_swt_internal_cef3_cef_1resource_1bundle_1handler_1t_2I_FUNC,
#else
	memmove__JLorg_eclipse_swt_internal_cef3_cef_1resource_1bundle_1handler_1t_2J_FUNC,
#endif
#ifndef JNI64
	memmove__Lorg_eclipse_swt_internal_cef3_cef_1command_1line_1t_2II_FUNC,
#else
	memmove__Lorg_eclipse_swt_internal_cef3_cef_1command_1line_1t_2JJ_FUNC,
#endif
#ifndef JNI64
	memmove__Lorg_eclipse_swt_internal_cef3_cef_1string_1t_2II_FUNC,
#else
	memmove__Lorg_eclipse_swt_internal_cef3_cef_1string_1t_2JJ_FUNC,
#endif
} CEF3_FUNCS;
