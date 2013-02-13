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

#include "cef3.h"

#ifndef NO_cef_app_t
void cachecef_app_tFields(JNIEnv *env, jobject lpObject);
cef_app_t *getcef_app_tFields(JNIEnv *env, jobject lpObject, cef_app_t *lpStruct);
void setcef_app_tFields(JNIEnv *env, jobject lpObject, cef_app_t *lpStruct);
#define cef_app_t_sizeof() sizeof(cef_app_t)
#else
#define cachecef_app_tFields(a,b)
#define getcef_app_tFields(a,b,c) NULL
#define setcef_app_tFields(a,b,c)
#define cef_app_t_sizeof() 0
#endif

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

#ifndef NO_cef_browser_process_handler_t
void cachecef_browser_process_handler_tFields(JNIEnv *env, jobject lpObject);
cef_browser_process_handler_t *getcef_browser_process_handler_tFields(JNIEnv *env, jobject lpObject, cef_browser_process_handler_t *lpStruct);
void setcef_browser_process_handler_tFields(JNIEnv *env, jobject lpObject, cef_browser_process_handler_t *lpStruct);
#define cef_browser_process_handler_t_sizeof() sizeof(cef_browser_process_handler_t)
#else
#define cachecef_browser_process_handler_tFields(a,b)
#define getcef_browser_process_handler_tFields(a,b,c) NULL
#define setcef_browser_process_handler_tFields(a,b,c)
#define cef_browser_process_handler_t_sizeof() 0
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

#ifndef NO_cef_client_t
void cachecef_client_tFields(JNIEnv *env, jobject lpObject);
cef_client_t *getcef_client_tFields(JNIEnv *env, jobject lpObject, cef_client_t *lpStruct);
void setcef_client_tFields(JNIEnv *env, jobject lpObject, cef_client_t *lpStruct);
#define cef_client_t_sizeof() sizeof(cef_client_t)
#else
#define cachecef_client_tFields(a,b)
#define getcef_client_tFields(a,b,c) NULL
#define setcef_client_tFields(a,b,c)
#define cef_client_t_sizeof() 0
#endif

#ifndef NO_cef_command_line_t
void cachecef_command_line_tFields(JNIEnv *env, jobject lpObject);
cef_command_line_t *getcef_command_line_tFields(JNIEnv *env, jobject lpObject, cef_command_line_t *lpStruct);
void setcef_command_line_tFields(JNIEnv *env, jobject lpObject, cef_command_line_t *lpStruct);
#define cef_command_line_t_sizeof() sizeof(cef_command_line_t)
#else
#define cachecef_command_line_tFields(a,b)
#define getcef_command_line_tFields(a,b,c) NULL
#define setcef_command_line_tFields(a,b,c)
#define cef_command_line_t_sizeof() 0
#endif

#ifndef NO_cef_context_menu_handler_t
void cachecef_context_menu_handler_tFields(JNIEnv *env, jobject lpObject);
cef_context_menu_handler_t *getcef_context_menu_handler_tFields(JNIEnv *env, jobject lpObject, cef_context_menu_handler_t *lpStruct);
void setcef_context_menu_handler_tFields(JNIEnv *env, jobject lpObject, cef_context_menu_handler_t *lpStruct);
#define cef_context_menu_handler_t_sizeof() sizeof(cef_context_menu_handler_t)
#else
#define cachecef_context_menu_handler_tFields(a,b)
#define getcef_context_menu_handler_tFields(a,b,c) NULL
#define setcef_context_menu_handler_tFields(a,b,c)
#define cef_context_menu_handler_t_sizeof() 0
#endif

#ifndef NO_cef_display_handler_t
void cachecef_display_handler_tFields(JNIEnv *env, jobject lpObject);
cef_display_handler_t *getcef_display_handler_tFields(JNIEnv *env, jobject lpObject, cef_display_handler_t *lpStruct);
void setcef_display_handler_tFields(JNIEnv *env, jobject lpObject, cef_display_handler_t *lpStruct);
#define cef_display_handler_t_sizeof() sizeof(cef_display_handler_t)
#else
#define cachecef_display_handler_tFields(a,b)
#define getcef_display_handler_tFields(a,b,c) NULL
#define setcef_display_handler_tFields(a,b,c)
#define cef_display_handler_t_sizeof() 0
#endif

#ifndef NO_cef_focus_handler_t
void cachecef_focus_handler_tFields(JNIEnv *env, jobject lpObject);
cef_focus_handler_t *getcef_focus_handler_tFields(JNIEnv *env, jobject lpObject, cef_focus_handler_t *lpStruct);
void setcef_focus_handler_tFields(JNIEnv *env, jobject lpObject, cef_focus_handler_t *lpStruct);
#define cef_focus_handler_t_sizeof() sizeof(cef_focus_handler_t)
#else
#define cachecef_focus_handler_tFields(a,b)
#define getcef_focus_handler_tFields(a,b,c) NULL
#define setcef_focus_handler_tFields(a,b,c)
#define cef_focus_handler_t_sizeof() 0
#endif

#ifndef NO_cef_life_span_handler_t
void cachecef_life_span_handler_tFields(JNIEnv *env, jobject lpObject);
cef_life_span_handler_t *getcef_life_span_handler_tFields(JNIEnv *env, jobject lpObject, cef_life_span_handler_t *lpStruct);
void setcef_life_span_handler_tFields(JNIEnv *env, jobject lpObject, cef_life_span_handler_t *lpStruct);
#define cef_life_span_handler_t_sizeof() sizeof(cef_life_span_handler_t)
#else
#define cachecef_life_span_handler_tFields(a,b)
#define getcef_life_span_handler_tFields(a,b,c) NULL
#define setcef_life_span_handler_tFields(a,b,c)
#define cef_life_span_handler_t_sizeof() 0
#endif

#ifndef NO_cef_load_handler_t
void cachecef_load_handler_tFields(JNIEnv *env, jobject lpObject);
cef_load_handler_t *getcef_load_handler_tFields(JNIEnv *env, jobject lpObject, cef_load_handler_t *lpStruct);
void setcef_load_handler_tFields(JNIEnv *env, jobject lpObject, cef_load_handler_t *lpStruct);
#define cef_load_handler_t_sizeof() sizeof(cef_load_handler_t)
#else
#define cachecef_load_handler_tFields(a,b)
#define getcef_load_handler_tFields(a,b,c) NULL
#define setcef_load_handler_tFields(a,b,c)
#define cef_load_handler_t_sizeof() 0
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

#ifndef NO_cef_proxy_handler_t
void cachecef_proxy_handler_tFields(JNIEnv *env, jobject lpObject);
cef_proxy_handler_t *getcef_proxy_handler_tFields(JNIEnv *env, jobject lpObject, cef_proxy_handler_t *lpStruct);
void setcef_proxy_handler_tFields(JNIEnv *env, jobject lpObject, cef_proxy_handler_t *lpStruct);
#define cef_proxy_handler_t_sizeof() sizeof(cef_proxy_handler_t)
#else
#define cachecef_proxy_handler_tFields(a,b)
#define getcef_proxy_handler_tFields(a,b,c) NULL
#define setcef_proxy_handler_tFields(a,b,c)
#define cef_proxy_handler_t_sizeof() 0
#endif

#ifndef NO_cef_request_handler_t
void cachecef_request_handler_tFields(JNIEnv *env, jobject lpObject);
cef_request_handler_t *getcef_request_handler_tFields(JNIEnv *env, jobject lpObject, cef_request_handler_t *lpStruct);
void setcef_request_handler_tFields(JNIEnv *env, jobject lpObject, cef_request_handler_t *lpStruct);
#define cef_request_handler_t_sizeof() sizeof(cef_request_handler_t)
#else
#define cachecef_request_handler_tFields(a,b)
#define getcef_request_handler_tFields(a,b,c) NULL
#define setcef_request_handler_tFields(a,b,c)
#define cef_request_handler_t_sizeof() 0
#endif

#ifndef NO_cef_resource_bundle_handler_t
void cachecef_resource_bundle_handler_tFields(JNIEnv *env, jobject lpObject);
cef_resource_bundle_handler_t *getcef_resource_bundle_handler_tFields(JNIEnv *env, jobject lpObject, cef_resource_bundle_handler_t *lpStruct);
void setcef_resource_bundle_handler_tFields(JNIEnv *env, jobject lpObject, cef_resource_bundle_handler_t *lpStruct);
#define cef_resource_bundle_handler_t_sizeof() sizeof(cef_resource_bundle_handler_t)
#else
#define cachecef_resource_bundle_handler_tFields(a,b)
#define getcef_resource_bundle_handler_tFields(a,b,c) NULL
#define setcef_resource_bundle_handler_tFields(a,b,c)
#define cef_resource_bundle_handler_t_sizeof() 0
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

