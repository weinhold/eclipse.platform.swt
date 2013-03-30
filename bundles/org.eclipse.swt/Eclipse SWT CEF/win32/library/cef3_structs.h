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

#ifndef NO_cef_browser_host_t
void cachecef_browser_host_tFields(JNIEnv *env, jobject lpObject);
cef_browser_host_t *getcef_browser_host_tFields(JNIEnv *env, jobject lpObject, cef_browser_host_t *lpStruct);
void setcef_browser_host_tFields(JNIEnv *env, jobject lpObject, cef_browser_host_t *lpStruct);
#define cef_browser_host_t_sizeof() sizeof(cef_browser_host_t)
#else
#define cachecef_browser_host_tFields(a,b)
#define getcef_browser_host_tFields(a,b,c) NULL
#define setcef_browser_host_tFields(a,b,c)
#define cef_browser_host_t_sizeof() 0
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

#ifndef NO_cef_jsdialog_callback_t
void cachecef_jsdialog_callback_tFields(JNIEnv *env, jobject lpObject);
cef_jsdialog_callback_t *getcef_jsdialog_callback_tFields(JNIEnv *env, jobject lpObject, cef_jsdialog_callback_t *lpStruct);
void setcef_jsdialog_callback_tFields(JNIEnv *env, jobject lpObject, cef_jsdialog_callback_t *lpStruct);
#define cef_jsdialog_callback_t_sizeof() sizeof(cef_jsdialog_callback_t)
#else
#define cachecef_jsdialog_callback_tFields(a,b)
#define getcef_jsdialog_callback_tFields(a,b,c) NULL
#define setcef_jsdialog_callback_tFields(a,b,c)
#define cef_jsdialog_callback_t_sizeof() 0
#endif

#ifndef NO_cef_list_value_t
void cachecef_list_value_tFields(JNIEnv *env, jobject lpObject);
cef_list_value_t *getcef_list_value_tFields(JNIEnv *env, jobject lpObject, cef_list_value_t *lpStruct);
void setcef_list_value_tFields(JNIEnv *env, jobject lpObject, cef_list_value_t *lpStruct);
#define cef_list_value_t_sizeof() sizeof(cef_list_value_t)
#else
#define cachecef_list_value_tFields(a,b)
#define getcef_list_value_tFields(a,b,c) NULL
#define setcef_list_value_tFields(a,b,c)
#define cef_list_value_t_sizeof() 0
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

#ifndef NO_cef_process_message_t
void cachecef_process_message_tFields(JNIEnv *env, jobject lpObject);
cef_process_message_t *getcef_process_message_tFields(JNIEnv *env, jobject lpObject, cef_process_message_t *lpStruct);
void setcef_process_message_tFields(JNIEnv *env, jobject lpObject, cef_process_message_t *lpStruct);
#define cef_process_message_t_sizeof() sizeof(cef_process_message_t)
#else
#define cachecef_process_message_tFields(a,b)
#define getcef_process_message_tFields(a,b,c) NULL
#define setcef_process_message_tFields(a,b,c)
#define cef_process_message_t_sizeof() 0
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

