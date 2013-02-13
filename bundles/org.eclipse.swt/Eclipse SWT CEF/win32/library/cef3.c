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
#include "cef3_structs.h"
#include "cef3_stats.h"

#ifndef CEF3_NATIVE
#define CEF3_NATIVE(func) Java_org_eclipse_swt_internal_cef3_CEF3_##func
#endif

#ifndef NO_cef_1app_1t_1sizeof
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1app_1t_1sizeof)
	(JNIEnv *env, jclass that)
{
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1app_1t_1sizeof_FUNC);
	rc = (jint)cef_app_t_sizeof();
	CEF3_NATIVE_EXIT(env, that, cef_1app_1t_1sizeof_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1browser_1host_1create_1browser
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1browser_1host_1create_1browser)
	(JNIEnv *env, jclass that, jobject arg0, jintLong arg1, jintLong arg2, jobject arg3)
{
	cef_window_info_t _arg0, *lparg0=NULL;
	cef_browser_settings_t _arg3, *lparg3=NULL;
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1browser_1host_1create_1browser_FUNC);
	if (arg0) if ((lparg0 = getcef_window_info_tFields(env, arg0, &_arg0)) == NULL) goto fail;
	if (arg3) if ((lparg3 = getcef_browser_settings_tFields(env, arg3, &_arg3)) == NULL) goto fail;
	rc = (jint)cef_browser_host_create_browser((const cef_window_info_t*)lparg0, (struct _cef_client_t*)arg1, (const cef_string_t*)arg2, (const struct _cef_browser_settings_t*)lparg3);
fail:
	if (arg3 && lparg3) setcef_browser_settings_tFields(env, arg3, lparg3);
	if (arg0 && lparg0) setcef_window_info_tFields(env, arg0, lparg0);
	CEF3_NATIVE_EXIT(env, that, cef_1browser_1host_1create_1browser_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1browser_1host_1create_1browser_1sync
JNIEXPORT jintLong JNICALL CEF3_NATIVE(cef_1browser_1host_1create_1browser_1sync)
	(JNIEnv *env, jclass that, jobject arg0, jobject arg1, jobject arg2, jintLong arg3)
{
	cef_window_info_t _arg0, *lparg0=NULL;
	cef_client_t _arg1, *lparg1=NULL;
	cef_string_t _arg2, *lparg2=NULL;
	jintLong rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1browser_1host_1create_1browser_1sync_FUNC);
	if (arg0) if ((lparg0 = getcef_window_info_tFields(env, arg0, &_arg0)) == NULL) goto fail;
	if (arg1) if ((lparg1 = getcef_client_tFields(env, arg1, &_arg1)) == NULL) goto fail;
	if (arg2) if ((lparg2 = getcef_string_tFields(env, arg2, &_arg2)) == NULL) goto fail;
	rc = (jintLong)cef_browser_host_create_browser_sync((const cef_window_info_t*)lparg0, (struct _cef_client_t*)lparg1, (const cef_string_t*)lparg2, (const struct _cef_browser_settings_t*)arg3);
fail:
	if (arg2 && lparg2) setcef_string_tFields(env, arg2, lparg2);
	if (arg1 && lparg1) setcef_client_tFields(env, arg1, lparg1);
	if (arg0 && lparg0) setcef_window_info_tFields(env, arg0, lparg0);
	CEF3_NATIVE_EXIT(env, that, cef_1browser_1host_1create_1browser_1sync_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1browser_1process_1handler_1t_1sizeof
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1browser_1process_1handler_1t_1sizeof)
	(JNIEnv *env, jclass that)
{
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1browser_1process_1handler_1t_1sizeof_FUNC);
	rc = (jint)cef_browser_process_handler_t_sizeof();
	CEF3_NATIVE_EXIT(env, that, cef_1browser_1process_1handler_1t_1sizeof_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1browser_1settings_1t_1sizeof
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1browser_1settings_1t_1sizeof)
	(JNIEnv *env, jclass that)
{
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1browser_1settings_1t_1sizeof_FUNC);
	rc = (jint)cef_browser_settings_t_sizeof();
	CEF3_NATIVE_EXIT(env, that, cef_1browser_1settings_1t_1sizeof_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1client_1t_1sizeof
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1client_1t_1sizeof)
	(JNIEnv *env, jclass that)
{
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1client_1t_1sizeof_FUNC);
	rc = (jint)cef_client_t_sizeof();
	CEF3_NATIVE_EXIT(env, that, cef_1client_1t_1sizeof_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1command_1line_1t_1sizeof
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1command_1line_1t_1sizeof)
	(JNIEnv *env, jclass that)
{
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1command_1line_1t_1sizeof_FUNC);
	rc = (jint)cef_command_line_t_sizeof();
	CEF3_NATIVE_EXIT(env, that, cef_1command_1line_1t_1sizeof_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1context_1menu_1handler_1t_1sizeof
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1context_1menu_1handler_1t_1sizeof)
	(JNIEnv *env, jclass that)
{
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1context_1menu_1handler_1t_1sizeof_FUNC);
	rc = (jint)cef_context_menu_handler_t_sizeof();
	CEF3_NATIVE_EXIT(env, that, cef_1context_1menu_1handler_1t_1sizeof_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1display_1handler_1t_1sizeof
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1display_1handler_1t_1sizeof)
	(JNIEnv *env, jclass that)
{
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1display_1handler_1t_1sizeof_FUNC);
	rc = (jint)cef_display_handler_t_sizeof();
	CEF3_NATIVE_EXIT(env, that, cef_1display_1handler_1t_1sizeof_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1execute_1process
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1execute_1process)
	(JNIEnv *env, jclass that, jobject arg0, jintLong arg1)
{
	cef_main_args_t _arg0, *lparg0=NULL;
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1execute_1process_FUNC);
	if (arg0) if ((lparg0 = getcef_main_args_tFields(env, arg0, &_arg0)) == NULL) goto fail;
	rc = (jint)cef_execute_process((const struct _cef_main_args_t*)lparg0, (struct _cef_app_t*)arg1);
fail:
	if (arg0 && lparg0) setcef_main_args_tFields(env, arg0, lparg0);
	CEF3_NATIVE_EXIT(env, that, cef_1execute_1process_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1focus_1handler_1t_1sizeof
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1focus_1handler_1t_1sizeof)
	(JNIEnv *env, jclass that)
{
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1focus_1handler_1t_1sizeof_FUNC);
	rc = (jint)cef_focus_handler_t_sizeof();
	CEF3_NATIVE_EXIT(env, that, cef_1focus_1handler_1t_1sizeof_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1initialize
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1initialize)
	(JNIEnv *env, jclass that, jobject arg0, jobject arg1, jintLong arg2)
{
	cef_main_args_t _arg0, *lparg0=NULL;
	cef_settings_t _arg1, *lparg1=NULL;
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1initialize_FUNC);
	if (arg0) if ((lparg0 = getcef_main_args_tFields(env, arg0, &_arg0)) == NULL) goto fail;
	if (arg1) if ((lparg1 = getcef_settings_tFields(env, arg1, &_arg1)) == NULL) goto fail;
	rc = (jint)cef_initialize((const struct _cef_main_args_t*)lparg0, (const struct _cef_settings_t*)lparg1, (struct _cef_app_t*)arg2);
fail:
	if (arg1 && lparg1) setcef_settings_tFields(env, arg1, lparg1);
	if (arg0 && lparg0) setcef_main_args_tFields(env, arg0, lparg0);
	CEF3_NATIVE_EXIT(env, that, cef_1initialize_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1life_1span_1handler_1t_1sizeof
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1life_1span_1handler_1t_1sizeof)
	(JNIEnv *env, jclass that)
{
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1life_1span_1handler_1t_1sizeof_FUNC);
	rc = (jint)cef_life_span_handler_t_sizeof();
	CEF3_NATIVE_EXIT(env, that, cef_1life_1span_1handler_1t_1sizeof_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1load_1handler_1t_1sizeof
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1load_1handler_1t_1sizeof)
	(JNIEnv *env, jclass that)
{
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1load_1handler_1t_1sizeof_FUNC);
	rc = (jint)cef_load_handler_t_sizeof();
	CEF3_NATIVE_EXIT(env, that, cef_1load_1handler_1t_1sizeof_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1proxy_1handler_1t_1sizeof
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1proxy_1handler_1t_1sizeof)
	(JNIEnv *env, jclass that)
{
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1proxy_1handler_1t_1sizeof_FUNC);
	rc = (jint)cef_proxy_handler_t_sizeof();
	CEF3_NATIVE_EXIT(env, that, cef_1proxy_1handler_1t_1sizeof_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1request_1handler_1t_1sizeof
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1request_1handler_1t_1sizeof)
	(JNIEnv *env, jclass that)
{
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1request_1handler_1t_1sizeof_FUNC);
	rc = (jint)cef_request_handler_t_sizeof();
	CEF3_NATIVE_EXIT(env, that, cef_1request_1handler_1t_1sizeof_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1resource_1bundle_1handler_1t_1sizeof
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1resource_1bundle_1handler_1t_1sizeof)
	(JNIEnv *env, jclass that)
{
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1resource_1bundle_1handler_1t_1sizeof_FUNC);
	rc = (jint)cef_resource_bundle_handler_t_sizeof();
	CEF3_NATIVE_EXIT(env, that, cef_1resource_1bundle_1handler_1t_1sizeof_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1settings_1t_1sizeof
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1settings_1t_1sizeof)
	(JNIEnv *env, jclass that)
{
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1settings_1t_1sizeof_FUNC);
	rc = (jint)cef_settings_t_sizeof();
	CEF3_NATIVE_EXIT(env, that, cef_1settings_1t_1sizeof_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1string_1clear
JNIEXPORT void JNICALL CEF3_NATIVE(cef_1string_1clear)
	(JNIEnv *env, jclass that, jintLong arg0)
{
	CEF3_NATIVE_ENTER(env, that, cef_1string_1clear_FUNC);
	cef_string_clear((cef_string_t*)arg0);
	CEF3_NATIVE_EXIT(env, that, cef_1string_1clear_FUNC);
}
#endif

#ifndef NO_cef_1string_1set
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1string_1set)
	(JNIEnv *env, jclass that, jcharArray arg0, jintLong arg1, jintLong arg2, jint arg3)
{
	jchar *lparg0=NULL;
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1string_1set_FUNC);
	if (arg0) if ((lparg0 = (*env)->GetCharArrayElements(env, arg0, NULL)) == NULL) goto fail;
	rc = (jint)cef_string_set((void*)lparg0, (size_t)arg1, (cef_string_t*)arg2, arg3);
fail:
	if (arg0 && lparg0) (*env)->ReleaseCharArrayElements(env, arg0, lparg0, 0);
	CEF3_NATIVE_EXIT(env, that, cef_1string_1set_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1string_1t_1sizeof
JNIEXPORT jint JNICALL CEF3_NATIVE(cef_1string_1t_1sizeof)
	(JNIEnv *env, jclass that)
{
	jint rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1string_1t_1sizeof_FUNC);
	rc = (jint)cef_string_t_sizeof();
	CEF3_NATIVE_EXIT(env, that, cef_1string_1t_1sizeof_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1string_1userfree_1alloc
JNIEXPORT jintLong JNICALL CEF3_NATIVE(cef_1string_1userfree_1alloc)
	(JNIEnv *env, jclass that)
{
	jintLong rc = 0;
	CEF3_NATIVE_ENTER(env, that, cef_1string_1userfree_1alloc_FUNC);
	rc = (jintLong)cef_string_userfree_alloc();
	CEF3_NATIVE_EXIT(env, that, cef_1string_1userfree_1alloc_FUNC);
	return rc;
}
#endif

#ifndef NO_cef_1string_1userfree_1free
JNIEXPORT void JNICALL CEF3_NATIVE(cef_1string_1userfree_1free)
	(JNIEnv *env, jclass that, jintLong arg0)
{
	CEF3_NATIVE_ENTER(env, that, cef_1string_1userfree_1free_FUNC);
	cef_string_userfree_free((cef_string_userfree_t)arg0);
	CEF3_NATIVE_EXIT(env, that, cef_1string_1userfree_1free_FUNC);
}
#endif

#if (!defined(NO_memmove__ILorg_eclipse_swt_internal_cef3_cef_1app_1t_2I) && !defined(JNI64)) || (!defined(NO_memmove__JLorg_eclipse_swt_internal_cef3_cef_1app_1t_2J) && defined(JNI64))
#ifndef JNI64
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__ILorg_eclipse_swt_internal_cef3_cef_1app_1t_2I)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#else
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__JLorg_eclipse_swt_internal_cef3_cef_1app_1t_2J)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#endif
{
	cef_app_t _arg1, *lparg1=NULL;
#ifndef JNI64
	CEF3_NATIVE_ENTER(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1app_1t_2I_FUNC);
#else
	CEF3_NATIVE_ENTER(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1app_1t_2J_FUNC);
#endif
	if (arg1) if ((lparg1 = getcef_app_tFields(env, arg1, &_arg1)) == NULL) goto fail;
	memmove((void *)arg0, (const void *)lparg1, (size_t)arg2);
fail:
#ifndef JNI64
	CEF3_NATIVE_EXIT(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1app_1t_2I_FUNC);
#else
	CEF3_NATIVE_EXIT(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1app_1t_2J_FUNC);
#endif
}
#endif

#if (!defined(NO_memmove__ILorg_eclipse_swt_internal_cef3_cef_1browser_1process_1handler_1t_2I) && !defined(JNI64)) || (!defined(NO_memmove__JLorg_eclipse_swt_internal_cef3_cef_1browser_1process_1handler_1t_2J) && defined(JNI64))
#ifndef JNI64
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__ILorg_eclipse_swt_internal_cef3_cef_1browser_1process_1handler_1t_2I)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#else
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__JLorg_eclipse_swt_internal_cef3_cef_1browser_1process_1handler_1t_2J)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#endif
{
	cef_browser_process_handler_t _arg1, *lparg1=NULL;
#ifndef JNI64
	CEF3_NATIVE_ENTER(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1browser_1process_1handler_1t_2I_FUNC);
#else
	CEF3_NATIVE_ENTER(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1browser_1process_1handler_1t_2J_FUNC);
#endif
	if (arg1) if ((lparg1 = getcef_browser_process_handler_tFields(env, arg1, &_arg1)) == NULL) goto fail;
	memmove((void *)arg0, (const void *)lparg1, (size_t)arg2);
fail:
#ifndef JNI64
	CEF3_NATIVE_EXIT(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1browser_1process_1handler_1t_2I_FUNC);
#else
	CEF3_NATIVE_EXIT(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1browser_1process_1handler_1t_2J_FUNC);
#endif
}
#endif

#if (!defined(NO_memmove__ILorg_eclipse_swt_internal_cef3_cef_1client_1t_2I) && !defined(JNI64)) || (!defined(NO_memmove__JLorg_eclipse_swt_internal_cef3_cef_1client_1t_2J) && defined(JNI64))
#ifndef JNI64
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__ILorg_eclipse_swt_internal_cef3_cef_1client_1t_2I)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#else
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__JLorg_eclipse_swt_internal_cef3_cef_1client_1t_2J)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#endif
{
	cef_client_t _arg1, *lparg1=NULL;
#ifndef JNI64
	CEF3_NATIVE_ENTER(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1client_1t_2I_FUNC);
#else
	CEF3_NATIVE_ENTER(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1client_1t_2J_FUNC);
#endif
	if (arg1) if ((lparg1 = getcef_client_tFields(env, arg1, &_arg1)) == NULL) goto fail;
	memmove((void *)arg0, (const void *)lparg1, (size_t)arg2);
fail:
#ifndef JNI64
	CEF3_NATIVE_EXIT(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1client_1t_2I_FUNC);
#else
	CEF3_NATIVE_EXIT(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1client_1t_2J_FUNC);
#endif
}
#endif

#if (!defined(NO_memmove__ILorg_eclipse_swt_internal_cef3_cef_1display_1handler_1t_2I) && !defined(JNI64)) || (!defined(NO_memmove__JLorg_eclipse_swt_internal_cef3_cef_1display_1handler_1t_2J) && defined(JNI64))
#ifndef JNI64
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__ILorg_eclipse_swt_internal_cef3_cef_1display_1handler_1t_2I)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#else
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__JLorg_eclipse_swt_internal_cef3_cef_1display_1handler_1t_2J)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#endif
{
	cef_display_handler_t _arg1, *lparg1=NULL;
#ifndef JNI64
	CEF3_NATIVE_ENTER(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1display_1handler_1t_2I_FUNC);
#else
	CEF3_NATIVE_ENTER(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1display_1handler_1t_2J_FUNC);
#endif
	if (arg1) if ((lparg1 = getcef_display_handler_tFields(env, arg1, &_arg1)) == NULL) goto fail;
	memmove((void *)arg0, (const void *)lparg1, (size_t)arg2);
fail:
#ifndef JNI64
	CEF3_NATIVE_EXIT(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1display_1handler_1t_2I_FUNC);
#else
	CEF3_NATIVE_EXIT(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1display_1handler_1t_2J_FUNC);
#endif
}
#endif

#if (!defined(NO_memmove__ILorg_eclipse_swt_internal_cef3_cef_1focus_1handler_1t_2I) && !defined(JNI64)) || (!defined(NO_memmove__JLorg_eclipse_swt_internal_cef3_cef_1focus_1handler_1t_2J) && defined(JNI64))
#ifndef JNI64
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__ILorg_eclipse_swt_internal_cef3_cef_1focus_1handler_1t_2I)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#else
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__JLorg_eclipse_swt_internal_cef3_cef_1focus_1handler_1t_2J)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#endif
{
	cef_focus_handler_t _arg1, *lparg1=NULL;
#ifndef JNI64
	CEF3_NATIVE_ENTER(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1focus_1handler_1t_2I_FUNC);
#else
	CEF3_NATIVE_ENTER(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1focus_1handler_1t_2J_FUNC);
#endif
	if (arg1) if ((lparg1 = getcef_focus_handler_tFields(env, arg1, &_arg1)) == NULL) goto fail;
	memmove((void *)arg0, (const void *)lparg1, (size_t)arg2);
fail:
#ifndef JNI64
	CEF3_NATIVE_EXIT(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1focus_1handler_1t_2I_FUNC);
#else
	CEF3_NATIVE_EXIT(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1focus_1handler_1t_2J_FUNC);
#endif
}
#endif

#if (!defined(NO_memmove__ILorg_eclipse_swt_internal_cef3_cef_1life_1span_1handler_1t_2I) && !defined(JNI64)) || (!defined(NO_memmove__JLorg_eclipse_swt_internal_cef3_cef_1life_1span_1handler_1t_2J) && defined(JNI64))
#ifndef JNI64
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__ILorg_eclipse_swt_internal_cef3_cef_1life_1span_1handler_1t_2I)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#else
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__JLorg_eclipse_swt_internal_cef3_cef_1life_1span_1handler_1t_2J)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#endif
{
	cef_life_span_handler_t _arg1, *lparg1=NULL;
#ifndef JNI64
	CEF3_NATIVE_ENTER(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1life_1span_1handler_1t_2I_FUNC);
#else
	CEF3_NATIVE_ENTER(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1life_1span_1handler_1t_2J_FUNC);
#endif
	if (arg1) if ((lparg1 = getcef_life_span_handler_tFields(env, arg1, &_arg1)) == NULL) goto fail;
	memmove((void *)arg0, (const void *)lparg1, (size_t)arg2);
fail:
#ifndef JNI64
	CEF3_NATIVE_EXIT(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1life_1span_1handler_1t_2I_FUNC);
#else
	CEF3_NATIVE_EXIT(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1life_1span_1handler_1t_2J_FUNC);
#endif
}
#endif

#if (!defined(NO_memmove__ILorg_eclipse_swt_internal_cef3_cef_1load_1handler_1t_2I) && !defined(JNI64)) || (!defined(NO_memmove__JLorg_eclipse_swt_internal_cef3_cef_1load_1handler_1t_2J) && defined(JNI64))
#ifndef JNI64
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__ILorg_eclipse_swt_internal_cef3_cef_1load_1handler_1t_2I)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#else
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__JLorg_eclipse_swt_internal_cef3_cef_1load_1handler_1t_2J)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#endif
{
	cef_load_handler_t _arg1, *lparg1=NULL;
#ifndef JNI64
	CEF3_NATIVE_ENTER(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1load_1handler_1t_2I_FUNC);
#else
	CEF3_NATIVE_ENTER(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1load_1handler_1t_2J_FUNC);
#endif
	if (arg1) if ((lparg1 = getcef_load_handler_tFields(env, arg1, &_arg1)) == NULL) goto fail;
	memmove((void *)arg0, (const void *)lparg1, (size_t)arg2);
fail:
#ifndef JNI64
	CEF3_NATIVE_EXIT(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1load_1handler_1t_2I_FUNC);
#else
	CEF3_NATIVE_EXIT(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1load_1handler_1t_2J_FUNC);
#endif
}
#endif

#if (!defined(NO_memmove__ILorg_eclipse_swt_internal_cef3_cef_1proxy_1handler_1t_2I) && !defined(JNI64)) || (!defined(NO_memmove__JLorg_eclipse_swt_internal_cef3_cef_1proxy_1handler_1t_2J) && defined(JNI64))
#ifndef JNI64
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__ILorg_eclipse_swt_internal_cef3_cef_1proxy_1handler_1t_2I)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#else
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__JLorg_eclipse_swt_internal_cef3_cef_1proxy_1handler_1t_2J)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#endif
{
	cef_proxy_handler_t _arg1, *lparg1=NULL;
#ifndef JNI64
	CEF3_NATIVE_ENTER(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1proxy_1handler_1t_2I_FUNC);
#else
	CEF3_NATIVE_ENTER(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1proxy_1handler_1t_2J_FUNC);
#endif
	if (arg1) if ((lparg1 = getcef_proxy_handler_tFields(env, arg1, &_arg1)) == NULL) goto fail;
	memmove((void *)arg0, (const void *)lparg1, (size_t)arg2);
fail:
#ifndef JNI64
	CEF3_NATIVE_EXIT(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1proxy_1handler_1t_2I_FUNC);
#else
	CEF3_NATIVE_EXIT(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1proxy_1handler_1t_2J_FUNC);
#endif
}
#endif

#if (!defined(NO_memmove__ILorg_eclipse_swt_internal_cef3_cef_1request_1handler_1t_2I) && !defined(JNI64)) || (!defined(NO_memmove__JLorg_eclipse_swt_internal_cef3_cef_1request_1handler_1t_2J) && defined(JNI64))
#ifndef JNI64
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__ILorg_eclipse_swt_internal_cef3_cef_1request_1handler_1t_2I)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#else
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__JLorg_eclipse_swt_internal_cef3_cef_1request_1handler_1t_2J)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#endif
{
	cef_request_handler_t _arg1, *lparg1=NULL;
#ifndef JNI64
	CEF3_NATIVE_ENTER(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1request_1handler_1t_2I_FUNC);
#else
	CEF3_NATIVE_ENTER(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1request_1handler_1t_2J_FUNC);
#endif
	if (arg1) if ((lparg1 = getcef_request_handler_tFields(env, arg1, &_arg1)) == NULL) goto fail;
	memmove((void *)arg0, (const void *)lparg1, (size_t)arg2);
fail:
#ifndef JNI64
	CEF3_NATIVE_EXIT(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1request_1handler_1t_2I_FUNC);
#else
	CEF3_NATIVE_EXIT(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1request_1handler_1t_2J_FUNC);
#endif
}
#endif

#if (!defined(NO_memmove__ILorg_eclipse_swt_internal_cef3_cef_1resource_1bundle_1handler_1t_2I) && !defined(JNI64)) || (!defined(NO_memmove__JLorg_eclipse_swt_internal_cef3_cef_1resource_1bundle_1handler_1t_2J) && defined(JNI64))
#ifndef JNI64
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__ILorg_eclipse_swt_internal_cef3_cef_1resource_1bundle_1handler_1t_2I)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#else
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__JLorg_eclipse_swt_internal_cef3_cef_1resource_1bundle_1handler_1t_2J)(JNIEnv *env, jclass that, jintLong arg0, jobject arg1, jintLong arg2)
#endif
{
	cef_resource_bundle_handler_t _arg1, *lparg1=NULL;
#ifndef JNI64
	CEF3_NATIVE_ENTER(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1resource_1bundle_1handler_1t_2I_FUNC);
#else
	CEF3_NATIVE_ENTER(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1resource_1bundle_1handler_1t_2J_FUNC);
#endif
	if (arg1) if ((lparg1 = getcef_resource_bundle_handler_tFields(env, arg1, &_arg1)) == NULL) goto fail;
	memmove((void *)arg0, (const void *)lparg1, (size_t)arg2);
fail:
#ifndef JNI64
	CEF3_NATIVE_EXIT(env, that, memmove__ILorg_eclipse_swt_internal_cef3_cef_1resource_1bundle_1handler_1t_2I_FUNC);
#else
	CEF3_NATIVE_EXIT(env, that, memmove__JLorg_eclipse_swt_internal_cef3_cef_1resource_1bundle_1handler_1t_2J_FUNC);
#endif
}
#endif

#if (!defined(NO_memmove__Lorg_eclipse_swt_internal_cef3_cef_1command_1line_1t_2II) && !defined(JNI64)) || (!defined(NO_memmove__Lorg_eclipse_swt_internal_cef3_cef_1command_1line_1t_2JJ) && defined(JNI64))
#ifndef JNI64
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__Lorg_eclipse_swt_internal_cef3_cef_1command_1line_1t_2II)(JNIEnv *env, jclass that, jobject arg0, jintLong arg1, jintLong arg2)
#else
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__Lorg_eclipse_swt_internal_cef3_cef_1command_1line_1t_2JJ)(JNIEnv *env, jclass that, jobject arg0, jintLong arg1, jintLong arg2)
#endif
{
	cef_command_line_t _arg0, *lparg0=NULL;
#ifndef JNI64
	CEF3_NATIVE_ENTER(env, that, memmove__Lorg_eclipse_swt_internal_cef3_cef_1command_1line_1t_2II_FUNC);
#else
	CEF3_NATIVE_ENTER(env, that, memmove__Lorg_eclipse_swt_internal_cef3_cef_1command_1line_1t_2JJ_FUNC);
#endif
	if (arg0) if ((lparg0 = &_arg0) == NULL) goto fail;
	memmove((void *)lparg0, (const void *)arg1, (size_t)arg2);
fail:
	if (arg0 && lparg0) setcef_command_line_tFields(env, arg0, lparg0);
#ifndef JNI64
	CEF3_NATIVE_EXIT(env, that, memmove__Lorg_eclipse_swt_internal_cef3_cef_1command_1line_1t_2II_FUNC);
#else
	CEF3_NATIVE_EXIT(env, that, memmove__Lorg_eclipse_swt_internal_cef3_cef_1command_1line_1t_2JJ_FUNC);
#endif
}
#endif

#if (!defined(NO_memmove__Lorg_eclipse_swt_internal_cef3_cef_1string_1t_2II) && !defined(JNI64)) || (!defined(NO_memmove__Lorg_eclipse_swt_internal_cef3_cef_1string_1t_2JJ) && defined(JNI64))
#ifndef JNI64
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__Lorg_eclipse_swt_internal_cef3_cef_1string_1t_2II)(JNIEnv *env, jclass that, jobject arg0, jintLong arg1, jintLong arg2)
#else
JNIEXPORT void JNICALL CEF3_NATIVE(memmove__Lorg_eclipse_swt_internal_cef3_cef_1string_1t_2JJ)(JNIEnv *env, jclass that, jobject arg0, jintLong arg1, jintLong arg2)
#endif
{
	cef_string_t _arg0, *lparg0=NULL;
#ifndef JNI64
	CEF3_NATIVE_ENTER(env, that, memmove__Lorg_eclipse_swt_internal_cef3_cef_1string_1t_2II_FUNC);
#else
	CEF3_NATIVE_ENTER(env, that, memmove__Lorg_eclipse_swt_internal_cef3_cef_1string_1t_2JJ_FUNC);
#endif
	if (arg0) if ((lparg0 = &_arg0) == NULL) goto fail;
	memmove((void *)lparg0, (const void *)arg1, (size_t)arg2);
fail:
	if (arg0 && lparg0) setcef_string_tFields(env, arg0, lparg0);
#ifndef JNI64
	CEF3_NATIVE_EXIT(env, that, memmove__Lorg_eclipse_swt_internal_cef3_cef_1string_1t_2II_FUNC);
#else
	CEF3_NATIVE_EXIT(env, that, memmove__Lorg_eclipse_swt_internal_cef3_cef_1string_1t_2JJ_FUNC);
#endif
}
#endif

