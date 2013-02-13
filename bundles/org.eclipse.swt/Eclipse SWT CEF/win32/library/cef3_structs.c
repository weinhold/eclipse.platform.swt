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
#include "cef3_structs.h"

#ifndef NO_cef_app_t
typedef struct cef_app_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID on_before_command_line_processing, on_register_custom_schemes, get_resource_bundle_handler, get_browser_process_handler, get_render_process_handler;
} cef_app_t_FID_CACHE;

cef_app_t_FID_CACHE cef_app_tFc;

void cachecef_app_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_app_tFc.cached) return;
	cachecef_base_tFields(env, lpObject);
	cef_app_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_app_tFc.on_before_command_line_processing = (*env)->GetFieldID(env, cef_app_tFc.clazz, "on_before_command_line_processing", I_J);
	cef_app_tFc.on_register_custom_schemes = (*env)->GetFieldID(env, cef_app_tFc.clazz, "on_register_custom_schemes", I_J);
	cef_app_tFc.get_resource_bundle_handler = (*env)->GetFieldID(env, cef_app_tFc.clazz, "get_resource_bundle_handler", I_J);
	cef_app_tFc.get_browser_process_handler = (*env)->GetFieldID(env, cef_app_tFc.clazz, "get_browser_process_handler", I_J);
	cef_app_tFc.get_render_process_handler = (*env)->GetFieldID(env, cef_app_tFc.clazz, "get_render_process_handler", I_J);
	cef_app_tFc.cached = 1;
}

cef_app_t *getcef_app_tFields(JNIEnv *env, jobject lpObject, cef_app_t *lpStruct)
{
	if (!cef_app_tFc.cached) cachecef_app_tFields(env, lpObject);
	getcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	lpStruct->on_before_command_line_processing = (*env)->GetIntLongField(env, lpObject, cef_app_tFc.on_before_command_line_processing);
	lpStruct->on_register_custom_schemes = (*env)->GetIntLongField(env, lpObject, cef_app_tFc.on_register_custom_schemes);
	lpStruct->get_resource_bundle_handler = (*env)->GetIntLongField(env, lpObject, cef_app_tFc.get_resource_bundle_handler);
	lpStruct->get_browser_process_handler = (*env)->GetIntLongField(env, lpObject, cef_app_tFc.get_browser_process_handler);
	lpStruct->get_render_process_handler = (*env)->GetIntLongField(env, lpObject, cef_app_tFc.get_render_process_handler);
	return lpStruct;
}

void setcef_app_tFields(JNIEnv *env, jobject lpObject, cef_app_t *lpStruct)
{
	if (!cef_app_tFc.cached) cachecef_app_tFields(env, lpObject);
	setcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	(*env)->SetIntLongField(env, lpObject, cef_app_tFc.on_before_command_line_processing, (jintLong)lpStruct->on_before_command_line_processing);
	(*env)->SetIntLongField(env, lpObject, cef_app_tFc.on_register_custom_schemes, (jintLong)lpStruct->on_register_custom_schemes);
	(*env)->SetIntLongField(env, lpObject, cef_app_tFc.get_resource_bundle_handler, (jintLong)lpStruct->get_resource_bundle_handler);
	(*env)->SetIntLongField(env, lpObject, cef_app_tFc.get_browser_process_handler, (jintLong)lpStruct->get_browser_process_handler);
	(*env)->SetIntLongField(env, lpObject, cef_app_tFc.get_render_process_handler, (jintLong)lpStruct->get_render_process_handler);
}
#endif

#ifndef NO_cef_base_t
typedef struct cef_base_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID size, add_ref, release, get_refct;
} cef_base_t_FID_CACHE;

cef_base_t_FID_CACHE cef_base_tFc;

void cachecef_base_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_base_tFc.cached) return;
	cef_base_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_base_tFc.size = (*env)->GetFieldID(env, cef_base_tFc.clazz, "size", I_J);
	cef_base_tFc.add_ref = (*env)->GetFieldID(env, cef_base_tFc.clazz, "add_ref", I_J);
	cef_base_tFc.release = (*env)->GetFieldID(env, cef_base_tFc.clazz, "release", I_J);
	cef_base_tFc.get_refct = (*env)->GetFieldID(env, cef_base_tFc.clazz, "get_refct", I_J);
	cef_base_tFc.cached = 1;
}

cef_base_t *getcef_base_tFields(JNIEnv *env, jobject lpObject, cef_base_t *lpStruct)
{
	if (!cef_base_tFc.cached) cachecef_base_tFields(env, lpObject);
	lpStruct->size = (size_t)(*env)->GetIntLongField(env, lpObject, cef_base_tFc.size);
	lpStruct->add_ref = (*env)->GetIntLongField(env, lpObject, cef_base_tFc.add_ref);
	lpStruct->release = (*env)->GetIntLongField(env, lpObject, cef_base_tFc.release);
	lpStruct->get_refct = (*env)->GetIntLongField(env, lpObject, cef_base_tFc.get_refct);
	return lpStruct;
}

void setcef_base_tFields(JNIEnv *env, jobject lpObject, cef_base_t *lpStruct)
{
	if (!cef_base_tFc.cached) cachecef_base_tFields(env, lpObject);
	(*env)->SetIntLongField(env, lpObject, cef_base_tFc.size, (jintLong)lpStruct->size);
	(*env)->SetIntLongField(env, lpObject, cef_base_tFc.add_ref, (jintLong)lpStruct->add_ref);
	(*env)->SetIntLongField(env, lpObject, cef_base_tFc.release, (jintLong)lpStruct->release);
	(*env)->SetIntLongField(env, lpObject, cef_base_tFc.get_refct, (jintLong)lpStruct->get_refct);
}
#endif

#ifndef NO_cef_browser_process_handler_t
typedef struct cef_browser_process_handler_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID get_proxy_handler, on_context_initialized, on_before_child_process_launch, on_render_process_thread_created;
} cef_browser_process_handler_t_FID_CACHE;

cef_browser_process_handler_t_FID_CACHE cef_browser_process_handler_tFc;

void cachecef_browser_process_handler_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_browser_process_handler_tFc.cached) return;
	cachecef_base_tFields(env, lpObject);
	cef_browser_process_handler_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_browser_process_handler_tFc.get_proxy_handler = (*env)->GetFieldID(env, cef_browser_process_handler_tFc.clazz, "get_proxy_handler", I_J);
	cef_browser_process_handler_tFc.on_context_initialized = (*env)->GetFieldID(env, cef_browser_process_handler_tFc.clazz, "on_context_initialized", I_J);
	cef_browser_process_handler_tFc.on_before_child_process_launch = (*env)->GetFieldID(env, cef_browser_process_handler_tFc.clazz, "on_before_child_process_launch", I_J);
	cef_browser_process_handler_tFc.on_render_process_thread_created = (*env)->GetFieldID(env, cef_browser_process_handler_tFc.clazz, "on_render_process_thread_created", I_J);
	cef_browser_process_handler_tFc.cached = 1;
}

cef_browser_process_handler_t *getcef_browser_process_handler_tFields(JNIEnv *env, jobject lpObject, cef_browser_process_handler_t *lpStruct)
{
	if (!cef_browser_process_handler_tFc.cached) cachecef_browser_process_handler_tFields(env, lpObject);
	getcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	lpStruct->get_proxy_handler = (*env)->GetIntLongField(env, lpObject, cef_browser_process_handler_tFc.get_proxy_handler);
	lpStruct->on_context_initialized = (*env)->GetIntLongField(env, lpObject, cef_browser_process_handler_tFc.on_context_initialized);
	lpStruct->on_before_child_process_launch = (*env)->GetIntLongField(env, lpObject, cef_browser_process_handler_tFc.on_before_child_process_launch);
	lpStruct->on_render_process_thread_created = (*env)->GetIntLongField(env, lpObject, cef_browser_process_handler_tFc.on_render_process_thread_created);
	return lpStruct;
}

void setcef_browser_process_handler_tFields(JNIEnv *env, jobject lpObject, cef_browser_process_handler_t *lpStruct)
{
	if (!cef_browser_process_handler_tFc.cached) cachecef_browser_process_handler_tFields(env, lpObject);
	setcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	(*env)->SetIntLongField(env, lpObject, cef_browser_process_handler_tFc.get_proxy_handler, (jintLong)lpStruct->get_proxy_handler);
	(*env)->SetIntLongField(env, lpObject, cef_browser_process_handler_tFc.on_context_initialized, (jintLong)lpStruct->on_context_initialized);
	(*env)->SetIntLongField(env, lpObject, cef_browser_process_handler_tFc.on_before_child_process_launch, (jintLong)lpStruct->on_before_child_process_launch);
	(*env)->SetIntLongField(env, lpObject, cef_browser_process_handler_tFc.on_render_process_thread_created, (jintLong)lpStruct->on_render_process_thread_created);
}
#endif

#ifndef NO_cef_browser_settings_t
typedef struct cef_browser_settings_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID size, standard_font_family, fixed_font_family, serif_font_family, sans_serif_font_family, cursive_font_family, fantasy_font_family, default_font_size, default_fixed_font_size, minimum_font_size, minimum_logical_font_size, remote_fonts_disabled, default_encoding, encoding_detector_enabled, javascript_disabled, javascript_open_windows_disallowed, javascript_close_windows_disallowed, javascript_access_clipboard_disallowed, dom_paste_disabled, caret_browsing_enabled, java_disabled, plugins_disabled, universal_access_from_file_urls_allowed, file_access_from_file_urls_allowed, web_security_disabled, xss_auditor_enabled, image_load_disabled, shrink_standalone_images_to_fit, site_specific_quirks_disabled, text_area_resize_disabled, page_cache_disabled, tab_to_links_disabled, hyperlink_auditing_disabled, user_style_sheet_enabled, user_style_sheet_location, author_and_user_styles_disabled, local_storage_disabled, databases_disabled, application_cache_disabled, webgl_disabled, accelerated_compositing_disabled, accelerated_layers_disabled, accelerated_video_disabled, accelerated_2d_canvas_disabled, accelerated_painting_enabled, accelerated_filters_enabled, accelerated_plugins_disabled, developer_tools_disabled, fullscreen_enabled;
} cef_browser_settings_t_FID_CACHE;

cef_browser_settings_t_FID_CACHE cef_browser_settings_tFc;

void cachecef_browser_settings_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_browser_settings_tFc.cached) return;
	cef_browser_settings_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_browser_settings_tFc.size = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "size", I_J);
	cef_browser_settings_tFc.standard_font_family = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "standard_font_family", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_browser_settings_tFc.fixed_font_family = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "fixed_font_family", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_browser_settings_tFc.serif_font_family = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "serif_font_family", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_browser_settings_tFc.sans_serif_font_family = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "sans_serif_font_family", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_browser_settings_tFc.cursive_font_family = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "cursive_font_family", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_browser_settings_tFc.fantasy_font_family = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "fantasy_font_family", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_browser_settings_tFc.default_font_size = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "default_font_size", "I");
	cef_browser_settings_tFc.default_fixed_font_size = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "default_fixed_font_size", "I");
	cef_browser_settings_tFc.minimum_font_size = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "minimum_font_size", "I");
	cef_browser_settings_tFc.minimum_logical_font_size = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "minimum_logical_font_size", "I");
	cef_browser_settings_tFc.remote_fonts_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "remote_fonts_disabled", "I");
	cef_browser_settings_tFc.default_encoding = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "default_encoding", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_browser_settings_tFc.encoding_detector_enabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "encoding_detector_enabled", "I");
	cef_browser_settings_tFc.javascript_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "javascript_disabled", "I");
	cef_browser_settings_tFc.javascript_open_windows_disallowed = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "javascript_open_windows_disallowed", "I");
	cef_browser_settings_tFc.javascript_close_windows_disallowed = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "javascript_close_windows_disallowed", "I");
	cef_browser_settings_tFc.javascript_access_clipboard_disallowed = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "javascript_access_clipboard_disallowed", "I");
	cef_browser_settings_tFc.dom_paste_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "dom_paste_disabled", "I");
	cef_browser_settings_tFc.caret_browsing_enabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "caret_browsing_enabled", "I");
	cef_browser_settings_tFc.java_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "java_disabled", "I");
	cef_browser_settings_tFc.plugins_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "plugins_disabled", "I");
	cef_browser_settings_tFc.universal_access_from_file_urls_allowed = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "universal_access_from_file_urls_allowed", "I");
	cef_browser_settings_tFc.file_access_from_file_urls_allowed = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "file_access_from_file_urls_allowed", "I");
	cef_browser_settings_tFc.web_security_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "web_security_disabled", "I");
	cef_browser_settings_tFc.xss_auditor_enabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "xss_auditor_enabled", "I");
	cef_browser_settings_tFc.image_load_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "image_load_disabled", "I");
	cef_browser_settings_tFc.shrink_standalone_images_to_fit = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "shrink_standalone_images_to_fit", "I");
	cef_browser_settings_tFc.site_specific_quirks_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "site_specific_quirks_disabled", "I");
	cef_browser_settings_tFc.text_area_resize_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "text_area_resize_disabled", "I");
	cef_browser_settings_tFc.page_cache_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "page_cache_disabled", "I");
	cef_browser_settings_tFc.tab_to_links_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "tab_to_links_disabled", "I");
	cef_browser_settings_tFc.hyperlink_auditing_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "hyperlink_auditing_disabled", "I");
	cef_browser_settings_tFc.user_style_sheet_enabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "user_style_sheet_enabled", "I");
	cef_browser_settings_tFc.user_style_sheet_location = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "user_style_sheet_location", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_browser_settings_tFc.author_and_user_styles_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "author_and_user_styles_disabled", "I");
	cef_browser_settings_tFc.local_storage_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "local_storage_disabled", "I");
	cef_browser_settings_tFc.databases_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "databases_disabled", "I");
	cef_browser_settings_tFc.application_cache_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "application_cache_disabled", "I");
	cef_browser_settings_tFc.webgl_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "webgl_disabled", "I");
	cef_browser_settings_tFc.accelerated_compositing_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "accelerated_compositing_disabled", "I");
	cef_browser_settings_tFc.accelerated_layers_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "accelerated_layers_disabled", "I");
	cef_browser_settings_tFc.accelerated_video_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "accelerated_video_disabled", "I");
	cef_browser_settings_tFc.accelerated_2d_canvas_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "accelerated_2d_canvas_disabled", "I");
	cef_browser_settings_tFc.accelerated_painting_enabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "accelerated_painting_enabled", "I");
	cef_browser_settings_tFc.accelerated_filters_enabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "accelerated_filters_enabled", "I");
	cef_browser_settings_tFc.accelerated_plugins_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "accelerated_plugins_disabled", "I");
	cef_browser_settings_tFc.developer_tools_disabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "developer_tools_disabled", "I");
	cef_browser_settings_tFc.fullscreen_enabled = (*env)->GetFieldID(env, cef_browser_settings_tFc.clazz, "fullscreen_enabled", "I");
	cef_browser_settings_tFc.cached = 1;
}

cef_browser_settings_t *getcef_browser_settings_tFields(JNIEnv *env, jobject lpObject, cef_browser_settings_t *lpStruct)
{
	if (!cef_browser_settings_tFc.cached) cachecef_browser_settings_tFields(env, lpObject);
	lpStruct->size = (*env)->GetIntLongField(env, lpObject, cef_browser_settings_tFc.size);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_browser_settings_tFc.standard_font_family);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->standard_font_family);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_browser_settings_tFc.fixed_font_family);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->fixed_font_family);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_browser_settings_tFc.serif_font_family);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->serif_font_family);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_browser_settings_tFc.sans_serif_font_family);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->sans_serif_font_family);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_browser_settings_tFc.cursive_font_family);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->cursive_font_family);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_browser_settings_tFc.fantasy_font_family);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->fantasy_font_family);
	}
	lpStruct->default_font_size = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.default_font_size);
	lpStruct->default_fixed_font_size = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.default_fixed_font_size);
	lpStruct->minimum_font_size = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.minimum_font_size);
	lpStruct->minimum_logical_font_size = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.minimum_logical_font_size);
	lpStruct->remote_fonts_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.remote_fonts_disabled);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_browser_settings_tFc.default_encoding);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->default_encoding);
	}
	lpStruct->encoding_detector_enabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.encoding_detector_enabled);
	lpStruct->javascript_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.javascript_disabled);
	lpStruct->javascript_open_windows_disallowed = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.javascript_open_windows_disallowed);
	lpStruct->javascript_close_windows_disallowed = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.javascript_close_windows_disallowed);
	lpStruct->javascript_access_clipboard_disallowed = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.javascript_access_clipboard_disallowed);
	lpStruct->dom_paste_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.dom_paste_disabled);
	lpStruct->caret_browsing_enabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.caret_browsing_enabled);
	lpStruct->java_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.java_disabled);
	lpStruct->plugins_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.plugins_disabled);
	lpStruct->universal_access_from_file_urls_allowed = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.universal_access_from_file_urls_allowed);
	lpStruct->file_access_from_file_urls_allowed = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.file_access_from_file_urls_allowed);
	lpStruct->web_security_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.web_security_disabled);
	lpStruct->xss_auditor_enabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.xss_auditor_enabled);
	lpStruct->image_load_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.image_load_disabled);
	lpStruct->shrink_standalone_images_to_fit = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.shrink_standalone_images_to_fit);
	lpStruct->site_specific_quirks_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.site_specific_quirks_disabled);
	lpStruct->text_area_resize_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.text_area_resize_disabled);
	lpStruct->page_cache_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.page_cache_disabled);
	lpStruct->tab_to_links_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.tab_to_links_disabled);
	lpStruct->hyperlink_auditing_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.hyperlink_auditing_disabled);
	lpStruct->user_style_sheet_enabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.user_style_sheet_enabled);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_browser_settings_tFc.user_style_sheet_location);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->user_style_sheet_location);
	}
	lpStruct->author_and_user_styles_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.author_and_user_styles_disabled);
	lpStruct->local_storage_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.local_storage_disabled);
	lpStruct->databases_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.databases_disabled);
	lpStruct->application_cache_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.application_cache_disabled);
	lpStruct->webgl_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.webgl_disabled);
	lpStruct->accelerated_compositing_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.accelerated_compositing_disabled);
	lpStruct->accelerated_layers_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.accelerated_layers_disabled);
	lpStruct->accelerated_video_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.accelerated_video_disabled);
	lpStruct->accelerated_2d_canvas_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.accelerated_2d_canvas_disabled);
	lpStruct->accelerated_painting_enabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.accelerated_painting_enabled);
	lpStruct->accelerated_filters_enabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.accelerated_filters_enabled);
	lpStruct->accelerated_plugins_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.accelerated_plugins_disabled);
	lpStruct->developer_tools_disabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.developer_tools_disabled);
	lpStruct->fullscreen_enabled = (*env)->GetIntField(env, lpObject, cef_browser_settings_tFc.fullscreen_enabled);
	return lpStruct;
}

void setcef_browser_settings_tFields(JNIEnv *env, jobject lpObject, cef_browser_settings_t *lpStruct)
{
	if (!cef_browser_settings_tFc.cached) cachecef_browser_settings_tFields(env, lpObject);
	(*env)->SetIntLongField(env, lpObject, cef_browser_settings_tFc.size, (jintLong)lpStruct->size);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_browser_settings_tFc.standard_font_family);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->standard_font_family);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_browser_settings_tFc.fixed_font_family);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->fixed_font_family);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_browser_settings_tFc.serif_font_family);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->serif_font_family);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_browser_settings_tFc.sans_serif_font_family);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->sans_serif_font_family);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_browser_settings_tFc.cursive_font_family);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->cursive_font_family);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_browser_settings_tFc.fantasy_font_family);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->fantasy_font_family);
	}
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.default_font_size, (jint)lpStruct->default_font_size);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.default_fixed_font_size, (jint)lpStruct->default_fixed_font_size);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.minimum_font_size, (jint)lpStruct->minimum_font_size);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.minimum_logical_font_size, (jint)lpStruct->minimum_logical_font_size);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.remote_fonts_disabled, (jint)lpStruct->remote_fonts_disabled);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_browser_settings_tFc.default_encoding);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->default_encoding);
	}
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.encoding_detector_enabled, (jint)lpStruct->encoding_detector_enabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.javascript_disabled, (jint)lpStruct->javascript_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.javascript_open_windows_disallowed, (jint)lpStruct->javascript_open_windows_disallowed);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.javascript_close_windows_disallowed, (jint)lpStruct->javascript_close_windows_disallowed);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.javascript_access_clipboard_disallowed, (jint)lpStruct->javascript_access_clipboard_disallowed);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.dom_paste_disabled, (jint)lpStruct->dom_paste_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.caret_browsing_enabled, (jint)lpStruct->caret_browsing_enabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.java_disabled, (jint)lpStruct->java_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.plugins_disabled, (jint)lpStruct->plugins_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.universal_access_from_file_urls_allowed, (jint)lpStruct->universal_access_from_file_urls_allowed);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.file_access_from_file_urls_allowed, (jint)lpStruct->file_access_from_file_urls_allowed);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.web_security_disabled, (jint)lpStruct->web_security_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.xss_auditor_enabled, (jint)lpStruct->xss_auditor_enabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.image_load_disabled, (jint)lpStruct->image_load_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.shrink_standalone_images_to_fit, (jint)lpStruct->shrink_standalone_images_to_fit);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.site_specific_quirks_disabled, (jint)lpStruct->site_specific_quirks_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.text_area_resize_disabled, (jint)lpStruct->text_area_resize_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.page_cache_disabled, (jint)lpStruct->page_cache_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.tab_to_links_disabled, (jint)lpStruct->tab_to_links_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.hyperlink_auditing_disabled, (jint)lpStruct->hyperlink_auditing_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.user_style_sheet_enabled, (jint)lpStruct->user_style_sheet_enabled);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_browser_settings_tFc.user_style_sheet_location);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->user_style_sheet_location);
	}
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.author_and_user_styles_disabled, (jint)lpStruct->author_and_user_styles_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.local_storage_disabled, (jint)lpStruct->local_storage_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.databases_disabled, (jint)lpStruct->databases_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.application_cache_disabled, (jint)lpStruct->application_cache_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.webgl_disabled, (jint)lpStruct->webgl_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.accelerated_compositing_disabled, (jint)lpStruct->accelerated_compositing_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.accelerated_layers_disabled, (jint)lpStruct->accelerated_layers_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.accelerated_video_disabled, (jint)lpStruct->accelerated_video_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.accelerated_2d_canvas_disabled, (jint)lpStruct->accelerated_2d_canvas_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.accelerated_painting_enabled, (jint)lpStruct->accelerated_painting_enabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.accelerated_filters_enabled, (jint)lpStruct->accelerated_filters_enabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.accelerated_plugins_disabled, (jint)lpStruct->accelerated_plugins_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.developer_tools_disabled, (jint)lpStruct->developer_tools_disabled);
	(*env)->SetIntField(env, lpObject, cef_browser_settings_tFc.fullscreen_enabled, (jint)lpStruct->fullscreen_enabled);
}
#endif

#ifndef NO_cef_client_t
typedef struct cef_client_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID get_context_menu_handler, get_dialog_handler, get_display_handler, get_download_handler, get_focus_handler, get_geolocation_handler, get_jsdialog_handler, get_keyboard_handler, get_life_span_handler, get_load_handler, get_request_handler, on_process_message_received;
} cef_client_t_FID_CACHE;

cef_client_t_FID_CACHE cef_client_tFc;

void cachecef_client_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_client_tFc.cached) return;
	cachecef_base_tFields(env, lpObject);
	cef_client_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_client_tFc.get_context_menu_handler = (*env)->GetFieldID(env, cef_client_tFc.clazz, "get_context_menu_handler", I_J);
	cef_client_tFc.get_dialog_handler = (*env)->GetFieldID(env, cef_client_tFc.clazz, "get_dialog_handler", I_J);
	cef_client_tFc.get_display_handler = (*env)->GetFieldID(env, cef_client_tFc.clazz, "get_display_handler", I_J);
	cef_client_tFc.get_download_handler = (*env)->GetFieldID(env, cef_client_tFc.clazz, "get_download_handler", I_J);
	cef_client_tFc.get_focus_handler = (*env)->GetFieldID(env, cef_client_tFc.clazz, "get_focus_handler", I_J);
	cef_client_tFc.get_geolocation_handler = (*env)->GetFieldID(env, cef_client_tFc.clazz, "get_geolocation_handler", I_J);
	cef_client_tFc.get_jsdialog_handler = (*env)->GetFieldID(env, cef_client_tFc.clazz, "get_jsdialog_handler", I_J);
	cef_client_tFc.get_keyboard_handler = (*env)->GetFieldID(env, cef_client_tFc.clazz, "get_keyboard_handler", I_J);
	cef_client_tFc.get_life_span_handler = (*env)->GetFieldID(env, cef_client_tFc.clazz, "get_life_span_handler", I_J);
	cef_client_tFc.get_load_handler = (*env)->GetFieldID(env, cef_client_tFc.clazz, "get_load_handler", I_J);
	cef_client_tFc.get_request_handler = (*env)->GetFieldID(env, cef_client_tFc.clazz, "get_request_handler", I_J);
	cef_client_tFc.on_process_message_received = (*env)->GetFieldID(env, cef_client_tFc.clazz, "on_process_message_received", I_J);
	cef_client_tFc.cached = 1;
}

cef_client_t *getcef_client_tFields(JNIEnv *env, jobject lpObject, cef_client_t *lpStruct)
{
	if (!cef_client_tFc.cached) cachecef_client_tFields(env, lpObject);
	getcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	lpStruct->get_context_menu_handler = (*env)->GetIntLongField(env, lpObject, cef_client_tFc.get_context_menu_handler);
	lpStruct->get_dialog_handler = (*env)->GetIntLongField(env, lpObject, cef_client_tFc.get_dialog_handler);
	lpStruct->get_display_handler = (*env)->GetIntLongField(env, lpObject, cef_client_tFc.get_display_handler);
	lpStruct->get_download_handler = (*env)->GetIntLongField(env, lpObject, cef_client_tFc.get_download_handler);
	lpStruct->get_focus_handler = (*env)->GetIntLongField(env, lpObject, cef_client_tFc.get_focus_handler);
	lpStruct->get_geolocation_handler = (*env)->GetIntLongField(env, lpObject, cef_client_tFc.get_geolocation_handler);
	lpStruct->get_jsdialog_handler = (*env)->GetIntLongField(env, lpObject, cef_client_tFc.get_jsdialog_handler);
	lpStruct->get_keyboard_handler = (*env)->GetIntLongField(env, lpObject, cef_client_tFc.get_keyboard_handler);
	lpStruct->get_life_span_handler = (*env)->GetIntLongField(env, lpObject, cef_client_tFc.get_life_span_handler);
	lpStruct->get_load_handler = (*env)->GetIntLongField(env, lpObject, cef_client_tFc.get_load_handler);
	lpStruct->get_request_handler = (*env)->GetIntLongField(env, lpObject, cef_client_tFc.get_request_handler);
	lpStruct->on_process_message_received = (*env)->GetIntLongField(env, lpObject, cef_client_tFc.on_process_message_received);
	return lpStruct;
}

void setcef_client_tFields(JNIEnv *env, jobject lpObject, cef_client_t *lpStruct)
{
	if (!cef_client_tFc.cached) cachecef_client_tFields(env, lpObject);
	setcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	(*env)->SetIntLongField(env, lpObject, cef_client_tFc.get_context_menu_handler, (jintLong)lpStruct->get_context_menu_handler);
	(*env)->SetIntLongField(env, lpObject, cef_client_tFc.get_dialog_handler, (jintLong)lpStruct->get_dialog_handler);
	(*env)->SetIntLongField(env, lpObject, cef_client_tFc.get_display_handler, (jintLong)lpStruct->get_display_handler);
	(*env)->SetIntLongField(env, lpObject, cef_client_tFc.get_download_handler, (jintLong)lpStruct->get_download_handler);
	(*env)->SetIntLongField(env, lpObject, cef_client_tFc.get_focus_handler, (jintLong)lpStruct->get_focus_handler);
	(*env)->SetIntLongField(env, lpObject, cef_client_tFc.get_geolocation_handler, (jintLong)lpStruct->get_geolocation_handler);
	(*env)->SetIntLongField(env, lpObject, cef_client_tFc.get_jsdialog_handler, (jintLong)lpStruct->get_jsdialog_handler);
	(*env)->SetIntLongField(env, lpObject, cef_client_tFc.get_keyboard_handler, (jintLong)lpStruct->get_keyboard_handler);
	(*env)->SetIntLongField(env, lpObject, cef_client_tFc.get_life_span_handler, (jintLong)lpStruct->get_life_span_handler);
	(*env)->SetIntLongField(env, lpObject, cef_client_tFc.get_load_handler, (jintLong)lpStruct->get_load_handler);
	(*env)->SetIntLongField(env, lpObject, cef_client_tFc.get_request_handler, (jintLong)lpStruct->get_request_handler);
	(*env)->SetIntLongField(env, lpObject, cef_client_tFc.on_process_message_received, (jintLong)lpStruct->on_process_message_received);
}
#endif

#ifndef NO_cef_command_line_t
typedef struct cef_command_line_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID is_valid, is_read_only, copy, init_from_argv, init_from_string, reset, get_command_line_string, get_program, set_program, has_switch, get_switch_value, get_switches, append_switch, append_switch_with_value, has_arguments, get_arguments, append_argument, prepend_wrapper;
} cef_command_line_t_FID_CACHE;

cef_command_line_t_FID_CACHE cef_command_line_tFc;

void cachecef_command_line_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_command_line_tFc.cached) return;
	cachecef_base_tFields(env, lpObject);
	cef_command_line_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_command_line_tFc.is_valid = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "is_valid", I_J);
	cef_command_line_tFc.is_read_only = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "is_read_only", I_J);
	cef_command_line_tFc.copy = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "copy", I_J);
	cef_command_line_tFc.init_from_argv = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "init_from_argv", I_J);
	cef_command_line_tFc.init_from_string = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "init_from_string", I_J);
	cef_command_line_tFc.reset = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "reset", I_J);
	cef_command_line_tFc.get_command_line_string = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "get_command_line_string", I_J);
	cef_command_line_tFc.get_program = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "get_program", I_J);
	cef_command_line_tFc.set_program = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "set_program", I_J);
	cef_command_line_tFc.has_switch = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "has_switch", I_J);
	cef_command_line_tFc.get_switch_value = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "get_switch_value", I_J);
	cef_command_line_tFc.get_switches = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "get_switches", I_J);
	cef_command_line_tFc.append_switch = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "append_switch", I_J);
	cef_command_line_tFc.append_switch_with_value = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "append_switch_with_value", I_J);
	cef_command_line_tFc.has_arguments = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "has_arguments", I_J);
	cef_command_line_tFc.get_arguments = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "get_arguments", I_J);
	cef_command_line_tFc.append_argument = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "append_argument", I_J);
	cef_command_line_tFc.prepend_wrapper = (*env)->GetFieldID(env, cef_command_line_tFc.clazz, "prepend_wrapper", I_J);
	cef_command_line_tFc.cached = 1;
}

cef_command_line_t *getcef_command_line_tFields(JNIEnv *env, jobject lpObject, cef_command_line_t *lpStruct)
{
	if (!cef_command_line_tFc.cached) cachecef_command_line_tFields(env, lpObject);
	getcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	lpStruct->is_valid = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.is_valid);
	lpStruct->is_read_only = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.is_read_only);
	lpStruct->copy = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.copy);
	lpStruct->init_from_argv = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.init_from_argv);
	lpStruct->init_from_string = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.init_from_string);
	lpStruct->reset = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.reset);
	lpStruct->get_command_line_string = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.get_command_line_string);
	lpStruct->get_program = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.get_program);
	lpStruct->set_program = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.set_program);
	lpStruct->has_switch = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.has_switch);
	lpStruct->get_switch_value = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.get_switch_value);
	lpStruct->get_switches = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.get_switches);
	lpStruct->append_switch = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.append_switch);
	lpStruct->append_switch_with_value = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.append_switch_with_value);
	lpStruct->has_arguments = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.has_arguments);
	lpStruct->get_arguments = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.get_arguments);
	lpStruct->append_argument = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.append_argument);
	lpStruct->prepend_wrapper = (*env)->GetIntLongField(env, lpObject, cef_command_line_tFc.prepend_wrapper);
	return lpStruct;
}

void setcef_command_line_tFields(JNIEnv *env, jobject lpObject, cef_command_line_t *lpStruct)
{
	if (!cef_command_line_tFc.cached) cachecef_command_line_tFields(env, lpObject);
	setcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.is_valid, (jintLong)lpStruct->is_valid);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.is_read_only, (jintLong)lpStruct->is_read_only);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.copy, (jintLong)lpStruct->copy);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.init_from_argv, (jintLong)lpStruct->init_from_argv);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.init_from_string, (jintLong)lpStruct->init_from_string);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.reset, (jintLong)lpStruct->reset);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.get_command_line_string, (jintLong)lpStruct->get_command_line_string);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.get_program, (jintLong)lpStruct->get_program);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.set_program, (jintLong)lpStruct->set_program);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.has_switch, (jintLong)lpStruct->has_switch);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.get_switch_value, (jintLong)lpStruct->get_switch_value);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.get_switches, (jintLong)lpStruct->get_switches);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.append_switch, (jintLong)lpStruct->append_switch);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.append_switch_with_value, (jintLong)lpStruct->append_switch_with_value);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.has_arguments, (jintLong)lpStruct->has_arguments);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.get_arguments, (jintLong)lpStruct->get_arguments);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.append_argument, (jintLong)lpStruct->append_argument);
	(*env)->SetIntLongField(env, lpObject, cef_command_line_tFc.prepend_wrapper, (jintLong)lpStruct->prepend_wrapper);
}
#endif

#ifndef NO_cef_context_menu_handler_t
typedef struct cef_context_menu_handler_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID on_before_context_menu, on_context_menu_command, on_context_menu_dismissed;
} cef_context_menu_handler_t_FID_CACHE;

cef_context_menu_handler_t_FID_CACHE cef_context_menu_handler_tFc;

void cachecef_context_menu_handler_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_context_menu_handler_tFc.cached) return;
	cachecef_base_tFields(env, lpObject);
	cef_context_menu_handler_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_context_menu_handler_tFc.on_before_context_menu = (*env)->GetFieldID(env, cef_context_menu_handler_tFc.clazz, "on_before_context_menu", I_J);
	cef_context_menu_handler_tFc.on_context_menu_command = (*env)->GetFieldID(env, cef_context_menu_handler_tFc.clazz, "on_context_menu_command", I_J);
	cef_context_menu_handler_tFc.on_context_menu_dismissed = (*env)->GetFieldID(env, cef_context_menu_handler_tFc.clazz, "on_context_menu_dismissed", I_J);
	cef_context_menu_handler_tFc.cached = 1;
}

cef_context_menu_handler_t *getcef_context_menu_handler_tFields(JNIEnv *env, jobject lpObject, cef_context_menu_handler_t *lpStruct)
{
	if (!cef_context_menu_handler_tFc.cached) cachecef_context_menu_handler_tFields(env, lpObject);
	getcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	lpStruct->on_before_context_menu = (*env)->GetIntLongField(env, lpObject, cef_context_menu_handler_tFc.on_before_context_menu);
	lpStruct->on_context_menu_command = (*env)->GetIntLongField(env, lpObject, cef_context_menu_handler_tFc.on_context_menu_command);
	lpStruct->on_context_menu_dismissed = (*env)->GetIntLongField(env, lpObject, cef_context_menu_handler_tFc.on_context_menu_dismissed);
	return lpStruct;
}

void setcef_context_menu_handler_tFields(JNIEnv *env, jobject lpObject, cef_context_menu_handler_t *lpStruct)
{
	if (!cef_context_menu_handler_tFc.cached) cachecef_context_menu_handler_tFields(env, lpObject);
	setcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	(*env)->SetIntLongField(env, lpObject, cef_context_menu_handler_tFc.on_before_context_menu, (jintLong)lpStruct->on_before_context_menu);
	(*env)->SetIntLongField(env, lpObject, cef_context_menu_handler_tFc.on_context_menu_command, (jintLong)lpStruct->on_context_menu_command);
	(*env)->SetIntLongField(env, lpObject, cef_context_menu_handler_tFc.on_context_menu_dismissed, (jintLong)lpStruct->on_context_menu_dismissed);
}
#endif

#ifndef NO_cef_display_handler_t
typedef struct cef_display_handler_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID on_loading_state_change, on_address_change, on_title_change, on_tooltip, on_status_message, on_console_message;
} cef_display_handler_t_FID_CACHE;

cef_display_handler_t_FID_CACHE cef_display_handler_tFc;

void cachecef_display_handler_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_display_handler_tFc.cached) return;
	cachecef_base_tFields(env, lpObject);
	cef_display_handler_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_display_handler_tFc.on_loading_state_change = (*env)->GetFieldID(env, cef_display_handler_tFc.clazz, "on_loading_state_change", I_J);
	cef_display_handler_tFc.on_address_change = (*env)->GetFieldID(env, cef_display_handler_tFc.clazz, "on_address_change", I_J);
	cef_display_handler_tFc.on_title_change = (*env)->GetFieldID(env, cef_display_handler_tFc.clazz, "on_title_change", I_J);
	cef_display_handler_tFc.on_tooltip = (*env)->GetFieldID(env, cef_display_handler_tFc.clazz, "on_tooltip", I_J);
	cef_display_handler_tFc.on_status_message = (*env)->GetFieldID(env, cef_display_handler_tFc.clazz, "on_status_message", I_J);
	cef_display_handler_tFc.on_console_message = (*env)->GetFieldID(env, cef_display_handler_tFc.clazz, "on_console_message", I_J);
	cef_display_handler_tFc.cached = 1;
}

cef_display_handler_t *getcef_display_handler_tFields(JNIEnv *env, jobject lpObject, cef_display_handler_t *lpStruct)
{
	if (!cef_display_handler_tFc.cached) cachecef_display_handler_tFields(env, lpObject);
	getcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	lpStruct->on_loading_state_change = (*env)->GetIntLongField(env, lpObject, cef_display_handler_tFc.on_loading_state_change);
	lpStruct->on_address_change = (*env)->GetIntLongField(env, lpObject, cef_display_handler_tFc.on_address_change);
	lpStruct->on_title_change = (*env)->GetIntLongField(env, lpObject, cef_display_handler_tFc.on_title_change);
	lpStruct->on_tooltip = (*env)->GetIntLongField(env, lpObject, cef_display_handler_tFc.on_tooltip);
	lpStruct->on_status_message = (*env)->GetIntLongField(env, lpObject, cef_display_handler_tFc.on_status_message);
	lpStruct->on_console_message = (*env)->GetIntLongField(env, lpObject, cef_display_handler_tFc.on_console_message);
	return lpStruct;
}

void setcef_display_handler_tFields(JNIEnv *env, jobject lpObject, cef_display_handler_t *lpStruct)
{
	if (!cef_display_handler_tFc.cached) cachecef_display_handler_tFields(env, lpObject);
	setcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	(*env)->SetIntLongField(env, lpObject, cef_display_handler_tFc.on_loading_state_change, (jintLong)lpStruct->on_loading_state_change);
	(*env)->SetIntLongField(env, lpObject, cef_display_handler_tFc.on_address_change, (jintLong)lpStruct->on_address_change);
	(*env)->SetIntLongField(env, lpObject, cef_display_handler_tFc.on_title_change, (jintLong)lpStruct->on_title_change);
	(*env)->SetIntLongField(env, lpObject, cef_display_handler_tFc.on_tooltip, (jintLong)lpStruct->on_tooltip);
	(*env)->SetIntLongField(env, lpObject, cef_display_handler_tFc.on_status_message, (jintLong)lpStruct->on_status_message);
	(*env)->SetIntLongField(env, lpObject, cef_display_handler_tFc.on_console_message, (jintLong)lpStruct->on_console_message);
}
#endif

#ifndef NO_cef_focus_handler_t
typedef struct cef_focus_handler_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID on_take_focus, on_set_focus, on_got_focus;
} cef_focus_handler_t_FID_CACHE;

cef_focus_handler_t_FID_CACHE cef_focus_handler_tFc;

void cachecef_focus_handler_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_focus_handler_tFc.cached) return;
	cachecef_base_tFields(env, lpObject);
	cef_focus_handler_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_focus_handler_tFc.on_take_focus = (*env)->GetFieldID(env, cef_focus_handler_tFc.clazz, "on_take_focus", I_J);
	cef_focus_handler_tFc.on_set_focus = (*env)->GetFieldID(env, cef_focus_handler_tFc.clazz, "on_set_focus", I_J);
	cef_focus_handler_tFc.on_got_focus = (*env)->GetFieldID(env, cef_focus_handler_tFc.clazz, "on_got_focus", I_J);
	cef_focus_handler_tFc.cached = 1;
}

cef_focus_handler_t *getcef_focus_handler_tFields(JNIEnv *env, jobject lpObject, cef_focus_handler_t *lpStruct)
{
	if (!cef_focus_handler_tFc.cached) cachecef_focus_handler_tFields(env, lpObject);
	getcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	lpStruct->on_take_focus = (*env)->GetIntLongField(env, lpObject, cef_focus_handler_tFc.on_take_focus);
	lpStruct->on_set_focus = (*env)->GetIntLongField(env, lpObject, cef_focus_handler_tFc.on_set_focus);
	lpStruct->on_got_focus = (*env)->GetIntLongField(env, lpObject, cef_focus_handler_tFc.on_got_focus);
	return lpStruct;
}

void setcef_focus_handler_tFields(JNIEnv *env, jobject lpObject, cef_focus_handler_t *lpStruct)
{
	if (!cef_focus_handler_tFc.cached) cachecef_focus_handler_tFields(env, lpObject);
	setcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	(*env)->SetIntLongField(env, lpObject, cef_focus_handler_tFc.on_take_focus, (jintLong)lpStruct->on_take_focus);
	(*env)->SetIntLongField(env, lpObject, cef_focus_handler_tFc.on_set_focus, (jintLong)lpStruct->on_set_focus);
	(*env)->SetIntLongField(env, lpObject, cef_focus_handler_tFc.on_got_focus, (jintLong)lpStruct->on_got_focus);
}
#endif

#ifndef NO_cef_life_span_handler_t
typedef struct cef_life_span_handler_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID on_before_popup, on_after_created, run_modal, do_close, on_before_close;
} cef_life_span_handler_t_FID_CACHE;

cef_life_span_handler_t_FID_CACHE cef_life_span_handler_tFc;

void cachecef_life_span_handler_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_life_span_handler_tFc.cached) return;
	cachecef_base_tFields(env, lpObject);
	cef_life_span_handler_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_life_span_handler_tFc.on_before_popup = (*env)->GetFieldID(env, cef_life_span_handler_tFc.clazz, "on_before_popup", I_J);
	cef_life_span_handler_tFc.on_after_created = (*env)->GetFieldID(env, cef_life_span_handler_tFc.clazz, "on_after_created", I_J);
	cef_life_span_handler_tFc.run_modal = (*env)->GetFieldID(env, cef_life_span_handler_tFc.clazz, "run_modal", I_J);
	cef_life_span_handler_tFc.do_close = (*env)->GetFieldID(env, cef_life_span_handler_tFc.clazz, "do_close", I_J);
	cef_life_span_handler_tFc.on_before_close = (*env)->GetFieldID(env, cef_life_span_handler_tFc.clazz, "on_before_close", I_J);
	cef_life_span_handler_tFc.cached = 1;
}

cef_life_span_handler_t *getcef_life_span_handler_tFields(JNIEnv *env, jobject lpObject, cef_life_span_handler_t *lpStruct)
{
	if (!cef_life_span_handler_tFc.cached) cachecef_life_span_handler_tFields(env, lpObject);
	getcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	lpStruct->on_before_popup = (*env)->GetIntLongField(env, lpObject, cef_life_span_handler_tFc.on_before_popup);
	lpStruct->on_after_created = (*env)->GetIntLongField(env, lpObject, cef_life_span_handler_tFc.on_after_created);
	lpStruct->run_modal = (*env)->GetIntLongField(env, lpObject, cef_life_span_handler_tFc.run_modal);
	lpStruct->do_close = (*env)->GetIntLongField(env, lpObject, cef_life_span_handler_tFc.do_close);
	lpStruct->on_before_close = (*env)->GetIntLongField(env, lpObject, cef_life_span_handler_tFc.on_before_close);
	return lpStruct;
}

void setcef_life_span_handler_tFields(JNIEnv *env, jobject lpObject, cef_life_span_handler_t *lpStruct)
{
	if (!cef_life_span_handler_tFc.cached) cachecef_life_span_handler_tFields(env, lpObject);
	setcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	(*env)->SetIntLongField(env, lpObject, cef_life_span_handler_tFc.on_before_popup, (jintLong)lpStruct->on_before_popup);
	(*env)->SetIntLongField(env, lpObject, cef_life_span_handler_tFc.on_after_created, (jintLong)lpStruct->on_after_created);
	(*env)->SetIntLongField(env, lpObject, cef_life_span_handler_tFc.run_modal, (jintLong)lpStruct->run_modal);
	(*env)->SetIntLongField(env, lpObject, cef_life_span_handler_tFc.do_close, (jintLong)lpStruct->do_close);
	(*env)->SetIntLongField(env, lpObject, cef_life_span_handler_tFc.on_before_close, (jintLong)lpStruct->on_before_close);
}
#endif

#ifndef NO_cef_load_handler_t
typedef struct cef_load_handler_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID on_load_start, on_load_end, on_load_error, on_render_process_terminated, on_plugin_crashed;
} cef_load_handler_t_FID_CACHE;

cef_load_handler_t_FID_CACHE cef_load_handler_tFc;

void cachecef_load_handler_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_load_handler_tFc.cached) return;
	cachecef_base_tFields(env, lpObject);
	cef_load_handler_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_load_handler_tFc.on_load_start = (*env)->GetFieldID(env, cef_load_handler_tFc.clazz, "on_load_start", I_J);
	cef_load_handler_tFc.on_load_end = (*env)->GetFieldID(env, cef_load_handler_tFc.clazz, "on_load_end", I_J);
	cef_load_handler_tFc.on_load_error = (*env)->GetFieldID(env, cef_load_handler_tFc.clazz, "on_load_error", I_J);
	cef_load_handler_tFc.on_render_process_terminated = (*env)->GetFieldID(env, cef_load_handler_tFc.clazz, "on_render_process_terminated", I_J);
	cef_load_handler_tFc.on_plugin_crashed = (*env)->GetFieldID(env, cef_load_handler_tFc.clazz, "on_plugin_crashed", I_J);
	cef_load_handler_tFc.cached = 1;
}

cef_load_handler_t *getcef_load_handler_tFields(JNIEnv *env, jobject lpObject, cef_load_handler_t *lpStruct)
{
	if (!cef_load_handler_tFc.cached) cachecef_load_handler_tFields(env, lpObject);
	getcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	lpStruct->on_load_start = (*env)->GetIntLongField(env, lpObject, cef_load_handler_tFc.on_load_start);
	lpStruct->on_load_end = (*env)->GetIntLongField(env, lpObject, cef_load_handler_tFc.on_load_end);
	lpStruct->on_load_error = (*env)->GetIntLongField(env, lpObject, cef_load_handler_tFc.on_load_error);
	lpStruct->on_render_process_terminated = (*env)->GetIntLongField(env, lpObject, cef_load_handler_tFc.on_render_process_terminated);
	lpStruct->on_plugin_crashed = (*env)->GetIntLongField(env, lpObject, cef_load_handler_tFc.on_plugin_crashed);
	return lpStruct;
}

void setcef_load_handler_tFields(JNIEnv *env, jobject lpObject, cef_load_handler_t *lpStruct)
{
	if (!cef_load_handler_tFc.cached) cachecef_load_handler_tFields(env, lpObject);
	setcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	(*env)->SetIntLongField(env, lpObject, cef_load_handler_tFc.on_load_start, (jintLong)lpStruct->on_load_start);
	(*env)->SetIntLongField(env, lpObject, cef_load_handler_tFc.on_load_end, (jintLong)lpStruct->on_load_end);
	(*env)->SetIntLongField(env, lpObject, cef_load_handler_tFc.on_load_error, (jintLong)lpStruct->on_load_error);
	(*env)->SetIntLongField(env, lpObject, cef_load_handler_tFc.on_render_process_terminated, (jintLong)lpStruct->on_render_process_terminated);
	(*env)->SetIntLongField(env, lpObject, cef_load_handler_tFc.on_plugin_crashed, (jintLong)lpStruct->on_plugin_crashed);
}
#endif

#ifndef NO_cef_main_args_t
typedef struct cef_main_args_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID instance;
} cef_main_args_t_FID_CACHE;

cef_main_args_t_FID_CACHE cef_main_args_tFc;

void cachecef_main_args_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_main_args_tFc.cached) return;
	cef_main_args_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_main_args_tFc.instance = (*env)->GetFieldID(env, cef_main_args_tFc.clazz, "instance", I_J);
	cef_main_args_tFc.cached = 1;
}

cef_main_args_t *getcef_main_args_tFields(JNIEnv *env, jobject lpObject, cef_main_args_t *lpStruct)
{
	if (!cef_main_args_tFc.cached) cachecef_main_args_tFields(env, lpObject);
	lpStruct->instance = (HINSTANCE)(*env)->GetIntLongField(env, lpObject, cef_main_args_tFc.instance);
	return lpStruct;
}

void setcef_main_args_tFields(JNIEnv *env, jobject lpObject, cef_main_args_t *lpStruct)
{
	if (!cef_main_args_tFc.cached) cachecef_main_args_tFields(env, lpObject);
	(*env)->SetIntLongField(env, lpObject, cef_main_args_tFc.instance, (jintLong)lpStruct->instance);
}
#endif

#ifndef NO_cef_proxy_handler_t
typedef struct cef_proxy_handler_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID get_proxy_for_url;
} cef_proxy_handler_t_FID_CACHE;

cef_proxy_handler_t_FID_CACHE cef_proxy_handler_tFc;

void cachecef_proxy_handler_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_proxy_handler_tFc.cached) return;
	cachecef_base_tFields(env, lpObject);
	cef_proxy_handler_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_proxy_handler_tFc.get_proxy_for_url = (*env)->GetFieldID(env, cef_proxy_handler_tFc.clazz, "get_proxy_for_url", I_J);
	cef_proxy_handler_tFc.cached = 1;
}

cef_proxy_handler_t *getcef_proxy_handler_tFields(JNIEnv *env, jobject lpObject, cef_proxy_handler_t *lpStruct)
{
	if (!cef_proxy_handler_tFc.cached) cachecef_proxy_handler_tFields(env, lpObject);
	getcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	lpStruct->get_proxy_for_url = (*env)->GetIntLongField(env, lpObject, cef_proxy_handler_tFc.get_proxy_for_url);
	return lpStruct;
}

void setcef_proxy_handler_tFields(JNIEnv *env, jobject lpObject, cef_proxy_handler_t *lpStruct)
{
	if (!cef_proxy_handler_tFc.cached) cachecef_proxy_handler_tFields(env, lpObject);
	setcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	(*env)->SetIntLongField(env, lpObject, cef_proxy_handler_tFc.get_proxy_for_url, (jintLong)lpStruct->get_proxy_for_url);
}
#endif

#ifndef NO_cef_request_handler_t
typedef struct cef_request_handler_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID on_before_resource_load, get_resource_handler, on_resource_redirect, get_auth_credentials, on_quota_request, get_cookie_manager, on_protocol_execution, on_before_plugin_load;
} cef_request_handler_t_FID_CACHE;

cef_request_handler_t_FID_CACHE cef_request_handler_tFc;

void cachecef_request_handler_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_request_handler_tFc.cached) return;
	cachecef_base_tFields(env, lpObject);
	cef_request_handler_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_request_handler_tFc.on_before_resource_load = (*env)->GetFieldID(env, cef_request_handler_tFc.clazz, "on_before_resource_load", I_J);
	cef_request_handler_tFc.get_resource_handler = (*env)->GetFieldID(env, cef_request_handler_tFc.clazz, "get_resource_handler", I_J);
	cef_request_handler_tFc.on_resource_redirect = (*env)->GetFieldID(env, cef_request_handler_tFc.clazz, "on_resource_redirect", I_J);
	cef_request_handler_tFc.get_auth_credentials = (*env)->GetFieldID(env, cef_request_handler_tFc.clazz, "get_auth_credentials", I_J);
	cef_request_handler_tFc.on_quota_request = (*env)->GetFieldID(env, cef_request_handler_tFc.clazz, "on_quota_request", I_J);
	cef_request_handler_tFc.get_cookie_manager = (*env)->GetFieldID(env, cef_request_handler_tFc.clazz, "get_cookie_manager", I_J);
	cef_request_handler_tFc.on_protocol_execution = (*env)->GetFieldID(env, cef_request_handler_tFc.clazz, "on_protocol_execution", I_J);
	cef_request_handler_tFc.on_before_plugin_load = (*env)->GetFieldID(env, cef_request_handler_tFc.clazz, "on_before_plugin_load", I_J);
	cef_request_handler_tFc.cached = 1;
}

cef_request_handler_t *getcef_request_handler_tFields(JNIEnv *env, jobject lpObject, cef_request_handler_t *lpStruct)
{
	if (!cef_request_handler_tFc.cached) cachecef_request_handler_tFields(env, lpObject);
	getcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	lpStruct->on_before_resource_load = (*env)->GetIntLongField(env, lpObject, cef_request_handler_tFc.on_before_resource_load);
	lpStruct->get_resource_handler = (*env)->GetIntLongField(env, lpObject, cef_request_handler_tFc.get_resource_handler);
	lpStruct->on_resource_redirect = (*env)->GetIntLongField(env, lpObject, cef_request_handler_tFc.on_resource_redirect);
	lpStruct->get_auth_credentials = (*env)->GetIntLongField(env, lpObject, cef_request_handler_tFc.get_auth_credentials);
	lpStruct->on_quota_request = (*env)->GetIntLongField(env, lpObject, cef_request_handler_tFc.on_quota_request);
	lpStruct->get_cookie_manager = (*env)->GetIntLongField(env, lpObject, cef_request_handler_tFc.get_cookie_manager);
	lpStruct->on_protocol_execution = (*env)->GetIntLongField(env, lpObject, cef_request_handler_tFc.on_protocol_execution);
	lpStruct->on_before_plugin_load = (*env)->GetIntLongField(env, lpObject, cef_request_handler_tFc.on_before_plugin_load);
	return lpStruct;
}

void setcef_request_handler_tFields(JNIEnv *env, jobject lpObject, cef_request_handler_t *lpStruct)
{
	if (!cef_request_handler_tFc.cached) cachecef_request_handler_tFields(env, lpObject);
	setcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	(*env)->SetIntLongField(env, lpObject, cef_request_handler_tFc.on_before_resource_load, (jintLong)lpStruct->on_before_resource_load);
	(*env)->SetIntLongField(env, lpObject, cef_request_handler_tFc.get_resource_handler, (jintLong)lpStruct->get_resource_handler);
	(*env)->SetIntLongField(env, lpObject, cef_request_handler_tFc.on_resource_redirect, (jintLong)lpStruct->on_resource_redirect);
	(*env)->SetIntLongField(env, lpObject, cef_request_handler_tFc.get_auth_credentials, (jintLong)lpStruct->get_auth_credentials);
	(*env)->SetIntLongField(env, lpObject, cef_request_handler_tFc.on_quota_request, (jintLong)lpStruct->on_quota_request);
	(*env)->SetIntLongField(env, lpObject, cef_request_handler_tFc.get_cookie_manager, (jintLong)lpStruct->get_cookie_manager);
	(*env)->SetIntLongField(env, lpObject, cef_request_handler_tFc.on_protocol_execution, (jintLong)lpStruct->on_protocol_execution);
	(*env)->SetIntLongField(env, lpObject, cef_request_handler_tFc.on_before_plugin_load, (jintLong)lpStruct->on_before_plugin_load);
}
#endif

#ifndef NO_cef_resource_bundle_handler_t
typedef struct cef_resource_bundle_handler_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID get_localized_string, get_data_resource;
} cef_resource_bundle_handler_t_FID_CACHE;

cef_resource_bundle_handler_t_FID_CACHE cef_resource_bundle_handler_tFc;

void cachecef_resource_bundle_handler_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_resource_bundle_handler_tFc.cached) return;
	cachecef_base_tFields(env, lpObject);
	cef_resource_bundle_handler_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_resource_bundle_handler_tFc.get_localized_string = (*env)->GetFieldID(env, cef_resource_bundle_handler_tFc.clazz, "get_localized_string", I_J);
	cef_resource_bundle_handler_tFc.get_data_resource = (*env)->GetFieldID(env, cef_resource_bundle_handler_tFc.clazz, "get_data_resource", I_J);
	cef_resource_bundle_handler_tFc.cached = 1;
}

cef_resource_bundle_handler_t *getcef_resource_bundle_handler_tFields(JNIEnv *env, jobject lpObject, cef_resource_bundle_handler_t *lpStruct)
{
	if (!cef_resource_bundle_handler_tFc.cached) cachecef_resource_bundle_handler_tFields(env, lpObject);
	getcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	lpStruct->get_localized_string = (*env)->GetIntLongField(env, lpObject, cef_resource_bundle_handler_tFc.get_localized_string);
	lpStruct->get_data_resource = (*env)->GetIntLongField(env, lpObject, cef_resource_bundle_handler_tFc.get_data_resource);
	return lpStruct;
}

void setcef_resource_bundle_handler_tFields(JNIEnv *env, jobject lpObject, cef_resource_bundle_handler_t *lpStruct)
{
	if (!cef_resource_bundle_handler_tFc.cached) cachecef_resource_bundle_handler_tFields(env, lpObject);
	setcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	(*env)->SetIntLongField(env, lpObject, cef_resource_bundle_handler_tFc.get_localized_string, (jintLong)lpStruct->get_localized_string);
	(*env)->SetIntLongField(env, lpObject, cef_resource_bundle_handler_tFc.get_data_resource, (jintLong)lpStruct->get_data_resource);
}
#endif

#ifndef NO_cef_settings_t
typedef struct cef_settings_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID size, single_process, browser_subprocess_path, multi_threaded_message_loop, command_line_args_disabled, cache_path, user_agent, product_version, locale, log_file, log_severity, release_dcheck_enabled, javascript_flags, auto_detect_proxy_settings_enabled, resources_dir_path, locales_dir_path, pack_loading_disabled, remote_debugging_port, uncaught_exception_stack_size, context_safety_implementation;
} cef_settings_t_FID_CACHE;

cef_settings_t_FID_CACHE cef_settings_tFc;

void cachecef_settings_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_settings_tFc.cached) return;
	cef_settings_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_settings_tFc.size = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "size", I_J);
	cef_settings_tFc.single_process = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "single_process", "I");
	cef_settings_tFc.browser_subprocess_path = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "browser_subprocess_path", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_settings_tFc.multi_threaded_message_loop = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "multi_threaded_message_loop", "I");
	cef_settings_tFc.command_line_args_disabled = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "command_line_args_disabled", "I");
	cef_settings_tFc.cache_path = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "cache_path", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_settings_tFc.user_agent = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "user_agent", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_settings_tFc.product_version = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "product_version", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_settings_tFc.locale = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "locale", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_settings_tFc.log_file = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "log_file", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_settings_tFc.log_severity = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "log_severity", "I");
	cef_settings_tFc.release_dcheck_enabled = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "release_dcheck_enabled", "I");
	cef_settings_tFc.javascript_flags = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "javascript_flags", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_settings_tFc.auto_detect_proxy_settings_enabled = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "auto_detect_proxy_settings_enabled", "I");
	cef_settings_tFc.resources_dir_path = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "resources_dir_path", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_settings_tFc.locales_dir_path = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "locales_dir_path", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_settings_tFc.pack_loading_disabled = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "pack_loading_disabled", "I");
	cef_settings_tFc.remote_debugging_port = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "remote_debugging_port", "I");
	cef_settings_tFc.uncaught_exception_stack_size = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "uncaught_exception_stack_size", "I");
	cef_settings_tFc.context_safety_implementation = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "context_safety_implementation", "I");
	cef_settings_tFc.cached = 1;
}

cef_settings_t *getcef_settings_tFields(JNIEnv *env, jobject lpObject, cef_settings_t *lpStruct)
{
	if (!cef_settings_tFc.cached) cachecef_settings_tFields(env, lpObject);
	lpStruct->size = (size_t)(*env)->GetIntLongField(env, lpObject, cef_settings_tFc.size);
	lpStruct->single_process = (bool)(*env)->GetIntField(env, lpObject, cef_settings_tFc.single_process);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.browser_subprocess_path);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->browser_subprocess_path);
	}
	lpStruct->multi_threaded_message_loop = (bool)(*env)->GetIntField(env, lpObject, cef_settings_tFc.multi_threaded_message_loop);
	lpStruct->command_line_args_disabled = (bool)(*env)->GetIntField(env, lpObject, cef_settings_tFc.command_line_args_disabled);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.cache_path);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->cache_path);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.user_agent);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->user_agent);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.product_version);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->product_version);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.locale);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->locale);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.log_file);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->log_file);
	}
	lpStruct->log_severity = (int)(*env)->GetIntField(env, lpObject, cef_settings_tFc.log_severity);
	lpStruct->release_dcheck_enabled = (bool)(*env)->GetIntField(env, lpObject, cef_settings_tFc.release_dcheck_enabled);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.javascript_flags);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->javascript_flags);
	}
	lpStruct->auto_detect_proxy_settings_enabled = (bool)(*env)->GetIntField(env, lpObject, cef_settings_tFc.auto_detect_proxy_settings_enabled);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.resources_dir_path);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->resources_dir_path);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.locales_dir_path);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->locales_dir_path);
	}
	lpStruct->pack_loading_disabled = (bool)(*env)->GetIntField(env, lpObject, cef_settings_tFc.pack_loading_disabled);
	lpStruct->remote_debugging_port = (int)(*env)->GetIntField(env, lpObject, cef_settings_tFc.remote_debugging_port);
	lpStruct->uncaught_exception_stack_size = (*env)->GetIntField(env, lpObject, cef_settings_tFc.uncaught_exception_stack_size);
	lpStruct->context_safety_implementation = (*env)->GetIntField(env, lpObject, cef_settings_tFc.context_safety_implementation);
	return lpStruct;
}

void setcef_settings_tFields(JNIEnv *env, jobject lpObject, cef_settings_t *lpStruct)
{
	if (!cef_settings_tFc.cached) cachecef_settings_tFields(env, lpObject);
	(*env)->SetIntLongField(env, lpObject, cef_settings_tFc.size, (jintLong)lpStruct->size);
	(*env)->SetIntField(env, lpObject, cef_settings_tFc.single_process, (jint)lpStruct->single_process);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.browser_subprocess_path);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->browser_subprocess_path);
	}
	(*env)->SetIntField(env, lpObject, cef_settings_tFc.multi_threaded_message_loop, (jint)lpStruct->multi_threaded_message_loop);
	(*env)->SetIntField(env, lpObject, cef_settings_tFc.command_line_args_disabled, (jint)lpStruct->command_line_args_disabled);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.cache_path);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->cache_path);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.user_agent);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->user_agent);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.product_version);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->product_version);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.locale);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->locale);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.log_file);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->log_file);
	}
	(*env)->SetIntField(env, lpObject, cef_settings_tFc.log_severity, (jint)lpStruct->log_severity);
	(*env)->SetIntField(env, lpObject, cef_settings_tFc.release_dcheck_enabled, (jint)lpStruct->release_dcheck_enabled);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.javascript_flags);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->javascript_flags);
	}
	(*env)->SetIntField(env, lpObject, cef_settings_tFc.auto_detect_proxy_settings_enabled, (jint)lpStruct->auto_detect_proxy_settings_enabled);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.resources_dir_path);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->resources_dir_path);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.locales_dir_path);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->locales_dir_path);
	}
	(*env)->SetIntField(env, lpObject, cef_settings_tFc.pack_loading_disabled, (jint)lpStruct->pack_loading_disabled);
	(*env)->SetIntField(env, lpObject, cef_settings_tFc.remote_debugging_port, (jint)lpStruct->remote_debugging_port);
	(*env)->SetIntField(env, lpObject, cef_settings_tFc.uncaught_exception_stack_size, (jint)lpStruct->uncaught_exception_stack_size);
	(*env)->SetIntField(env, lpObject, cef_settings_tFc.context_safety_implementation, (jint)lpStruct->context_safety_implementation);
}
#endif

#ifndef NO_cef_string_t
typedef struct cef_string_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID str, length, dtor;
} cef_string_t_FID_CACHE;

cef_string_t_FID_CACHE cef_string_tFc;

void cachecef_string_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_string_tFc.cached) return;
	cef_string_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_string_tFc.str = (*env)->GetFieldID(env, cef_string_tFc.clazz, "str", I_J);
	cef_string_tFc.length = (*env)->GetFieldID(env, cef_string_tFc.clazz, "length", I_J);
	cef_string_tFc.dtor = (*env)->GetFieldID(env, cef_string_tFc.clazz, "dtor", I_J);
	cef_string_tFc.cached = 1;
}

cef_string_t *getcef_string_tFields(JNIEnv *env, jobject lpObject, cef_string_t *lpStruct)
{
	if (!cef_string_tFc.cached) cachecef_string_tFields(env, lpObject);
	lpStruct->str = (void *)(*env)->GetIntLongField(env, lpObject, cef_string_tFc.str);
	lpStruct->length = (size_t)(*env)->GetIntLongField(env, lpObject, cef_string_tFc.length);
	lpStruct->dtor = (void (__cdecl *)(void *))(*env)->GetIntLongField(env, lpObject, cef_string_tFc.dtor);
	return lpStruct;
}

void setcef_string_tFields(JNIEnv *env, jobject lpObject, cef_string_t *lpStruct)
{
	if (!cef_string_tFc.cached) cachecef_string_tFields(env, lpObject);
	(*env)->SetIntLongField(env, lpObject, cef_string_tFc.str, (jintLong)lpStruct->str);
	(*env)->SetIntLongField(env, lpObject, cef_string_tFc.length, (jintLong)lpStruct->length);
	(*env)->SetIntLongField(env, lpObject, cef_string_tFc.dtor, (jintLong)lpStruct->dtor);
}
#endif

#ifndef NO_cef_window_info_t
typedef struct cef_window_info_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID ex_style, window_name, style, x, y, width, height, parent_window, menu, transparent_painting, window;
} cef_window_info_t_FID_CACHE;

cef_window_info_t_FID_CACHE cef_window_info_tFc;

void cachecef_window_info_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_window_info_tFc.cached) return;
	cef_window_info_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_window_info_tFc.ex_style = (*env)->GetFieldID(env, cef_window_info_tFc.clazz, "ex_style", "I");
	cef_window_info_tFc.window_name = (*env)->GetFieldID(env, cef_window_info_tFc.clazz, "window_name", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_window_info_tFc.style = (*env)->GetFieldID(env, cef_window_info_tFc.clazz, "style", "I");
	cef_window_info_tFc.x = (*env)->GetFieldID(env, cef_window_info_tFc.clazz, "x", "I");
	cef_window_info_tFc.y = (*env)->GetFieldID(env, cef_window_info_tFc.clazz, "y", "I");
	cef_window_info_tFc.width = (*env)->GetFieldID(env, cef_window_info_tFc.clazz, "width", "I");
	cef_window_info_tFc.height = (*env)->GetFieldID(env, cef_window_info_tFc.clazz, "height", "I");
	cef_window_info_tFc.parent_window = (*env)->GetFieldID(env, cef_window_info_tFc.clazz, "parent_window", I_J);
	cef_window_info_tFc.menu = (*env)->GetFieldID(env, cef_window_info_tFc.clazz, "menu", I_J);
	cef_window_info_tFc.transparent_painting = (*env)->GetFieldID(env, cef_window_info_tFc.clazz, "transparent_painting", "I");
	cef_window_info_tFc.window = (*env)->GetFieldID(env, cef_window_info_tFc.clazz, "window", I_J);
	cef_window_info_tFc.cached = 1;
}

cef_window_info_t *getcef_window_info_tFields(JNIEnv *env, jobject lpObject, cef_window_info_t *lpStruct)
{
	if (!cef_window_info_tFc.cached) cachecef_window_info_tFields(env, lpObject);
	lpStruct->ex_style = (DWORD)(*env)->GetIntField(env, lpObject, cef_window_info_tFc.ex_style);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_window_info_tFc.window_name);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->window_name);
	}
	lpStruct->style = (DWORD)(*env)->GetIntField(env, lpObject, cef_window_info_tFc.style);
	lpStruct->x = (int)(*env)->GetIntField(env, lpObject, cef_window_info_tFc.x);
	lpStruct->y = (int)(*env)->GetIntField(env, lpObject, cef_window_info_tFc.y);
	lpStruct->width = (int)(*env)->GetIntField(env, lpObject, cef_window_info_tFc.width);
	lpStruct->height = (int)(*env)->GetIntField(env, lpObject, cef_window_info_tFc.height);
	lpStruct->parent_window = (cef_window_handle_t)(*env)->GetIntLongField(env, lpObject, cef_window_info_tFc.parent_window);
	lpStruct->menu = (HMENU)(*env)->GetIntLongField(env, lpObject, cef_window_info_tFc.menu);
	lpStruct->transparent_painting = (BOOL)(*env)->GetIntField(env, lpObject, cef_window_info_tFc.transparent_painting);
	lpStruct->window = (cef_window_handle_t)(*env)->GetIntLongField(env, lpObject, cef_window_info_tFc.window);
	return lpStruct;
}

void setcef_window_info_tFields(JNIEnv *env, jobject lpObject, cef_window_info_t *lpStruct)
{
	if (!cef_window_info_tFc.cached) cachecef_window_info_tFields(env, lpObject);
	(*env)->SetIntField(env, lpObject, cef_window_info_tFc.ex_style, (jint)lpStruct->ex_style);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_window_info_tFc.window_name);
	if (lpObject1 != NULL) setcef_string_tFields(env, lpObject1, &lpStruct->window_name);
	}
	(*env)->SetIntField(env, lpObject, cef_window_info_tFc.style, (jint)lpStruct->style);
	(*env)->SetIntField(env, lpObject, cef_window_info_tFc.x, (jint)lpStruct->x);
	(*env)->SetIntField(env, lpObject, cef_window_info_tFc.y, (jint)lpStruct->y);
	(*env)->SetIntField(env, lpObject, cef_window_info_tFc.width, (jint)lpStruct->width);
	(*env)->SetIntField(env, lpObject, cef_window_info_tFc.height, (jint)lpStruct->height);
	(*env)->SetIntLongField(env, lpObject, cef_window_info_tFc.parent_window, (jintLong)lpStruct->parent_window);
	(*env)->SetIntLongField(env, lpObject, cef_window_info_tFc.menu, (jintLong)lpStruct->menu);
	(*env)->SetIntField(env, lpObject, cef_window_info_tFc.transparent_painting, (jint)lpStruct->transparent_painting);
	(*env)->SetIntLongField(env, lpObject, cef_window_info_tFc.window, (jintLong)lpStruct->window);
}
#endif

