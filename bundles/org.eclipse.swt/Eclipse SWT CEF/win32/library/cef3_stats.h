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
	callFuncInt_FUNC,
	callFuncPtr_FUNC,
#ifndef JNI64
	callFuncVoid__II_FUNC,
#else
	callFuncVoid__JJ_FUNC,
#endif
#ifndef JNI64
	callFuncVoid__III_FUNC,
#else
	callFuncVoid__JJJ_FUNC,
#endif
	cef_1base_1t_1sizeof_FUNC,
	cef_1browser_1host_1create_1browser_FUNC,
	cef_1browser_1settings_1t_1sizeof_FUNC,
	cef_1browser_1t_1sizeof_FUNC,
	cef_1execute_1process_FUNC,
	cef_1frame_1t_1sizeof_FUNC,
	cef_1initialize_FUNC,
	cef_1settings_1t_1sizeof_FUNC,
	cef_1string_1clear_FUNC,
	cef_1string_1set_FUNC,
	cef_1string_1t_1sizeof_FUNC,
	cef_1string_1userfree_1alloc_FUNC,
	cef_1string_1userfree_1free_FUNC,
#ifndef JNI64
	memmove__Lorg_eclipse_swt_internal_cef3_cef_1base_1t_2II_FUNC,
#else
	memmove__Lorg_eclipse_swt_internal_cef3_cef_1base_1t_2JJ_FUNC,
#endif
#ifndef JNI64
	memmove__Lorg_eclipse_swt_internal_cef3_cef_1browser_1t_2II_FUNC,
#else
	memmove__Lorg_eclipse_swt_internal_cef3_cef_1browser_1t_2JJ_FUNC,
#endif
#ifndef JNI64
	memmove__Lorg_eclipse_swt_internal_cef3_cef_1frame_1t_2II_FUNC,
#else
	memmove__Lorg_eclipse_swt_internal_cef3_cef_1frame_1t_2JJ_FUNC,
#endif
#ifndef JNI64
	memmove__Lorg_eclipse_swt_internal_cef3_cef_1string_1t_2II_FUNC,
#else
	memmove__Lorg_eclipse_swt_internal_cef3_cef_1string_1t_2JJ_FUNC,
#endif
} CEF3_FUNCS;
