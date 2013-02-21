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

#include "cef3.h"

#ifndef NO_cef_base_t
void cachecef_base_tFields(JNIEnv *env, jobject lpObject);
cef_base_t *getcef_base_tFields(JNIEnv *env, jobject lpObject, cef_base_t *lpStruct);
void setcef_base_tFields(JNIEnv *env, jobject lpObject, cef_base_t *lpStruct);
#define cef_base_t_sizeof() sizeof(cef_base_t)
#else
#define cachecef_base_tFields(a,b)
#define getcef_base_tFields(a,b,c) NULL
#define setcef_base_tFields(a,b,c)
#define cef_base_t_sizeof() 0
#endif

#ifndef NO_cef_browser_settings_t
void cachecef_browser_settings_tFields(JNIEnv *env, jobject lpObject);
cef_browser_settings_t *getcef_browser_settings_tFields(JNIEnv *env, jobject lpObject, cef_browser_settings_t *lpStruct);
void setcef_browser_settings_tFields(JNIEnv *env, jobject lpObject, cef_browser_settings_t *lpStruct);
#define cef_browser_settings_t_sizeof() sizeof(cef_browser_settings_t)
#else
#define cachecef_browser_settings_tFields(a,b)
#define getcef_browser_settings_tFields(a,b,c) NULL
#define setcef_browser_settings_tFields(a,b,c)
#define cef_browser_settings_t_sizeof() 0
#endif

#ifndef NO_cef_browser_t
void cachecef_browser_tFields(JNIEnv *env, jobject lpObject);
cef_browser_t *getcef_browser_tFields(JNIEnv *env, jobject lpObject, cef_browser_t *lpStruct);
void setcef_browser_tFields(JNIEnv *env, jobject lpObject, cef_browser_t *lpStruct);
#define cef_browser_t_sizeof() sizeof(cef_browser_t)
#else
#define cachecef_browser_tFields(a,b)
#define getcef_browser_tFields(a,b,c) NULL
#define setcef_browser_tFields(a,b,c)
#define cef_browser_t_sizeof() 0
#endif

#ifndef NO_cef_frame_t
void cachecef_frame_tFields(JNIEnv *env, jobject lpObject);
cef_frame_t *getcef_frame_tFields(JNIEnv *env, jobject lpObject, cef_frame_t *lpStruct);
void setcef_frame_tFields(JNIEnv *env, jobject lpObject, cef_frame_t *lpStruct);
#define cef_frame_t_sizeof() sizeof(cef_frame_t)
#else
#define cachecef_frame_tFields(a,b)
#define getcef_frame_tFields(a,b,c) NULL
#define setcef_frame_tFields(a,b,c)
#define cef_frame_t_sizeof() 0
#endif

#ifndef NO_cef_main_args_t
void cachecef_main_args_tFields(JNIEnv *env, jobject lpObject);
cef_main_args_t *getcef_main_args_tFields(JNIEnv *env, jobject lpObject, cef_main_args_t *lpStruct);
void setcef_main_args_tFields(JNIEnv *env, jobject lpObject, cef_main_args_t *lpStruct);
#define cef_main_args_t_sizeof() sizeof(cef_main_args_t)
#else
#define cachecef_main_args_tFields(a,b)
#define getcef_main_args_tFields(a,b,c) NULL
#define setcef_main_args_tFields(a,b,c)
#define cef_main_args_t_sizeof() 0
#endif

#ifndef NO_cef_settings_t
void cachecef_settings_tFields(JNIEnv *env, jobject lpObject);
cef_settings_t *getcef_settings_tFields(JNIEnv *env, jobject lpObject, cef_settings_t *lpStruct);
void setcef_settings_tFields(JNIEnv *env, jobject lpObject, cef_settings_t *lpStruct);
#define cef_settings_t_sizeof() sizeof(cef_settings_t)
#else
#define cachecef_settings_tFields(a,b)
#define getcef_settings_tFields(a,b,c) NULL
#define setcef_settings_tFields(a,b,c)
#define cef_settings_t_sizeof() 0
#endif

#ifndef NO_cef_string_t
void cachecef_string_tFields(JNIEnv *env, jobject lpObject);
cef_string_t *getcef_string_tFields(JNIEnv *env, jobject lpObject, cef_string_t *lpStruct);
void setcef_string_tFields(JNIEnv *env, jobject lpObject, cef_string_t *lpStruct);
#define cef_string_t_sizeof() sizeof(cef_string_t)
#else
#define cachecef_string_tFields(a,b)
#define getcef_string_tFields(a,b,c) NULL
#define setcef_string_tFields(a,b,c)
#define cef_string_t_sizeof() 0
#endif

#ifndef NO_cef_window_info_t
void cachecef_window_info_tFields(JNIEnv *env, jobject lpObject);
cef_window_info_t *getcef_window_info_tFields(JNIEnv *env, jobject lpObject, cef_window_info_t *lpStruct);
void setcef_window_info_tFields(JNIEnv *env, jobject lpObject, cef_window_info_t *lpStruct);
#define cef_window_info_t_sizeof() sizeof(cef_window_info_t)
#else
#define cachecef_window_info_tFields(a,b)
#define getcef_window_info_tFields(a,b,c) NULL
#define setcef_window_info_tFields(a,b,c)
#define cef_window_info_t_sizeof() 0
#endif

