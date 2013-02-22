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
	lpStruct->size = (*env)->GetIntLongField(env, lpObject, cef_base_tFc.size);
	lpStruct->add_ref = (int (CEF_CALLBACK *)(struct _cef_base_t* self))(*env)->GetIntLongField(env, lpObject, cef_base_tFc.add_ref);
	lpStruct->release = (int (CEF_CALLBACK *)(struct _cef_base_t* self))(*env)->GetIntLongField(env, lpObject, cef_base_tFc.release);
	lpStruct->get_refct = (int (CEF_CALLBACK *)(struct _cef_base_t* self))(*env)->GetIntLongField(env, lpObject, cef_base_tFc.get_refct);
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

#ifndef NO_cef_browser_t
typedef struct cef_browser_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID get_host, can_go_back, go_back, can_go_forward, go_forward, is_loading, reload, reload_ignore_cache, stop_load, get_identifier, is_popup, has_document, get_main_frame, get_focused_frame, get_frame_byident, get_frame, get_frame_count, get_frame_identifiers, get_frame_names, send_process_message;
} cef_browser_t_FID_CACHE;

cef_browser_t_FID_CACHE cef_browser_tFc;

void cachecef_browser_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_browser_tFc.cached) return;
	cachecef_base_tFields(env, lpObject);
	cef_browser_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_browser_tFc.get_host = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "get_host", I_J);
	cef_browser_tFc.can_go_back = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "can_go_back", I_J);
	cef_browser_tFc.go_back = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "go_back", I_J);
	cef_browser_tFc.can_go_forward = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "can_go_forward", I_J);
	cef_browser_tFc.go_forward = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "go_forward", I_J);
	cef_browser_tFc.is_loading = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "is_loading", I_J);
	cef_browser_tFc.reload = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "reload", I_J);
	cef_browser_tFc.reload_ignore_cache = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "reload_ignore_cache", I_J);
	cef_browser_tFc.stop_load = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "stop_load", I_J);
	cef_browser_tFc.get_identifier = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "get_identifier", I_J);
	cef_browser_tFc.is_popup = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "is_popup", I_J);
	cef_browser_tFc.has_document = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "has_document", I_J);
	cef_browser_tFc.get_main_frame = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "get_main_frame", I_J);
	cef_browser_tFc.get_focused_frame = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "get_focused_frame", I_J);
	cef_browser_tFc.get_frame_byident = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "get_frame_byident", I_J);
	cef_browser_tFc.get_frame = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "get_frame", I_J);
	cef_browser_tFc.get_frame_count = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "get_frame_count", I_J);
	cef_browser_tFc.get_frame_identifiers = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "get_frame_identifiers", I_J);
	cef_browser_tFc.get_frame_names = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "get_frame_names", I_J);
	cef_browser_tFc.send_process_message = (*env)->GetFieldID(env, cef_browser_tFc.clazz, "send_process_message", I_J);
	cef_browser_tFc.cached = 1;
}

cef_browser_t *getcef_browser_tFields(JNIEnv *env, jobject lpObject, cef_browser_t *lpStruct)
{
	if (!cef_browser_tFc.cached) cachecef_browser_tFields(env, lpObject);
	getcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	lpStruct->get_host = (struct _cef_browser_host_t* (CEF_CALLBACK *)(struct _cef_browser_t* self))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.get_host);
	lpStruct->can_go_back = (int (CEF_CALLBACK *)(struct _cef_browser_t* self))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.can_go_back);
	lpStruct->go_back = (void (CEF_CALLBACK *)(struct _cef_browser_t* self))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.go_back);
	lpStruct->can_go_forward = (int (CEF_CALLBACK *)(struct _cef_browser_t* self))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.can_go_forward);
	lpStruct->go_forward = (void (CEF_CALLBACK *)(struct _cef_browser_t* self))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.go_forward);
	lpStruct->is_loading = (int (CEF_CALLBACK *)(struct _cef_browser_t* self))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.is_loading);
	lpStruct->reload = (void (CEF_CALLBACK *)(struct _cef_browser_t* self))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.reload);
	lpStruct->reload_ignore_cache = (void (CEF_CALLBACK *)(struct _cef_browser_t* self))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.reload_ignore_cache);
	lpStruct->stop_load = (void (CEF_CALLBACK *)(struct _cef_browser_t* self))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.stop_load);
	lpStruct->get_identifier = (int (CEF_CALLBACK *)(struct _cef_browser_t* self))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.get_identifier);
	lpStruct->is_popup = (int (CEF_CALLBACK *)(struct _cef_browser_t* self))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.is_popup);
	lpStruct->has_document = (int (CEF_CALLBACK *)(struct _cef_browser_t* self))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.has_document);
	lpStruct->get_main_frame = (struct _cef_frame_t* (CEF_CALLBACK *)(struct _cef_browser_t* self))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.get_main_frame);
	lpStruct->get_focused_frame = (struct _cef_frame_t* (CEF_CALLBACK *)(struct _cef_browser_t* self))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.get_focused_frame);
	lpStruct->get_frame_byident = (struct _cef_frame_t* (CEF_CALLBACK *)(struct _cef_browser_t* self, int64 identifier))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.get_frame_byident);
	lpStruct->get_frame = (struct _cef_frame_t* (CEF_CALLBACK *)(struct _cef_browser_t* self, const cef_string_t* name))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.get_frame);
	lpStruct->get_frame_count = (size_t (CEF_CALLBACK *)(struct _cef_browser_t* self))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.get_frame_count);
	lpStruct->get_frame_identifiers = (void (CEF_CALLBACK *)(struct _cef_browser_t* self, size_t* identifiersCount, int64* identifiers))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.get_frame_identifiers);
	lpStruct->get_frame_names = (void (CEF_CALLBACK *)(struct _cef_browser_t* self, cef_string_list_t names))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.get_frame_names);
	lpStruct->send_process_message = (int (CEF_CALLBACK *)(struct _cef_browser_t* self, enum cef_process_id_t target_process, struct _cef_process_message_t* message))(*env)->GetIntLongField(env, lpObject, cef_browser_tFc.send_process_message);
	return lpStruct;
}

void setcef_browser_tFields(JNIEnv *env, jobject lpObject, cef_browser_t *lpStruct)
{
	if (!cef_browser_tFc.cached) cachecef_browser_tFields(env, lpObject);
	setcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.get_host, (jintLong)lpStruct->get_host);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.can_go_back, (jintLong)lpStruct->can_go_back);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.go_back, (jintLong)lpStruct->go_back);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.can_go_forward, (jintLong)lpStruct->can_go_forward);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.go_forward, (jintLong)lpStruct->go_forward);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.is_loading, (jintLong)lpStruct->is_loading);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.reload, (jintLong)lpStruct->reload);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.reload_ignore_cache, (jintLong)lpStruct->reload_ignore_cache);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.stop_load, (jintLong)lpStruct->stop_load);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.get_identifier, (jintLong)lpStruct->get_identifier);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.is_popup, (jintLong)lpStruct->is_popup);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.has_document, (jintLong)lpStruct->has_document);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.get_main_frame, (jintLong)lpStruct->get_main_frame);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.get_focused_frame, (jintLong)lpStruct->get_focused_frame);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.get_frame_byident, (jintLong)lpStruct->get_frame_byident);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.get_frame, (jintLong)lpStruct->get_frame);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.get_frame_count, (jintLong)lpStruct->get_frame_count);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.get_frame_identifiers, (jintLong)lpStruct->get_frame_identifiers);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.get_frame_names, (jintLong)lpStruct->get_frame_names);
	(*env)->SetIntLongField(env, lpObject, cef_browser_tFc.send_process_message, (jintLong)lpStruct->send_process_message);
}
#endif

#ifndef NO_cef_frame_t
typedef struct cef_frame_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID is_valid, undo, redo, cut, copy, paste, del, select_all, view_source, get_source, get_text, load_request, load_url, load_string, execute_java_script, is_main, is_focused, get_name, get_identifier, get_parent, get_url, get_browser, get_v8context, visit_dom;
} cef_frame_t_FID_CACHE;

cef_frame_t_FID_CACHE cef_frame_tFc;

void cachecef_frame_tFields(JNIEnv *env, jobject lpObject)
{
	if (cef_frame_tFc.cached) return;
	cachecef_base_tFields(env, lpObject);
	cef_frame_tFc.clazz = (*env)->GetObjectClass(env, lpObject);
	cef_frame_tFc.is_valid = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "is_valid", I_J);
	cef_frame_tFc.undo = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "undo", I_J);
	cef_frame_tFc.redo = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "redo", I_J);
	cef_frame_tFc.cut = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "cut", I_J);
	cef_frame_tFc.copy = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "copy", I_J);
	cef_frame_tFc.paste = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "paste", I_J);
	cef_frame_tFc.del = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "del", I_J);
	cef_frame_tFc.select_all = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "select_all", I_J);
	cef_frame_tFc.view_source = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "view_source", I_J);
	cef_frame_tFc.get_source = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "get_source", I_J);
	cef_frame_tFc.get_text = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "get_text", I_J);
	cef_frame_tFc.load_request = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "load_request", I_J);
	cef_frame_tFc.load_url = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "load_url", I_J);
	cef_frame_tFc.load_string = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "load_string", I_J);
	cef_frame_tFc.execute_java_script = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "execute_java_script", I_J);
	cef_frame_tFc.is_main = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "is_main", I_J);
	cef_frame_tFc.is_focused = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "is_focused", I_J);
	cef_frame_tFc.get_name = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "get_name", I_J);
	cef_frame_tFc.get_identifier = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "get_identifier", I_J);
	cef_frame_tFc.get_parent = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "get_parent", I_J);
	cef_frame_tFc.get_url = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "get_url", I_J);
	cef_frame_tFc.get_browser = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "get_browser", I_J);
	cef_frame_tFc.get_v8context = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "get_v8context", I_J);
	cef_frame_tFc.visit_dom = (*env)->GetFieldID(env, cef_frame_tFc.clazz, "visit_dom", I_J);
	cef_frame_tFc.cached = 1;
}

cef_frame_t *getcef_frame_tFields(JNIEnv *env, jobject lpObject, cef_frame_t *lpStruct)
{
	if (!cef_frame_tFc.cached) cachecef_frame_tFields(env, lpObject);
	getcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	lpStruct->is_valid = (int (CEF_CALLBACK *)(struct _cef_frame_t* self))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.is_valid);
	lpStruct->undo = (void (CEF_CALLBACK *)(struct _cef_frame_t* self))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.undo);
	lpStruct->redo = (void (CEF_CALLBACK *)(struct _cef_frame_t* self))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.redo);
	lpStruct->cut = (void (CEF_CALLBACK *)(struct _cef_frame_t* self))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.cut);
	lpStruct->copy = (void (CEF_CALLBACK *)(struct _cef_frame_t* self))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.copy);
	lpStruct->paste = (void (CEF_CALLBACK *)(struct _cef_frame_t* self))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.paste);
	lpStruct->del = (void (CEF_CALLBACK *)(struct _cef_frame_t* self))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.del);
	lpStruct->select_all = (void (CEF_CALLBACK *)(struct _cef_frame_t* self))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.select_all);
	lpStruct->view_source = (void (CEF_CALLBACK *)(struct _cef_frame_t* self))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.view_source);
	lpStruct->get_source = (void (CEF_CALLBACK *)(struct _cef_frame_t* self, struct _cef_string_visitor_t* visitor))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.get_source);
	lpStruct->get_text = (void (CEF_CALLBACK *)(struct _cef_frame_t* self, struct _cef_string_visitor_t* visitor))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.get_text);
	lpStruct->load_request = (void (CEF_CALLBACK *)(struct _cef_frame_t* self, struct _cef_request_t* request))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.load_request);
	lpStruct->load_url = (void (CEF_CALLBACK *)(struct _cef_frame_t* self, const cef_string_t* url))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.load_url);
	lpStruct->load_string = (void (CEF_CALLBACK *)(struct _cef_frame_t* self, const cef_string_t* string_val, const cef_string_t* url))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.load_string);
	lpStruct->execute_java_script = (void (CEF_CALLBACK *)(struct _cef_frame_t* self, const cef_string_t* code, const cef_string_t* script_url, int start_line))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.execute_java_script);
	lpStruct->is_main = (int (CEF_CALLBACK *)(struct _cef_frame_t* self))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.is_main);
	lpStruct->is_focused = (int (CEF_CALLBACK *)(struct _cef_frame_t* self))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.is_focused);
	lpStruct->get_name = (cef_string_userfree_t (CEF_CALLBACK *)(struct _cef_frame_t* self))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.get_name);
	lpStruct->get_identifier = (int64 (CEF_CALLBACK *)(struct _cef_frame_t* self))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.get_identifier);
	lpStruct->get_parent = (struct _cef_frame_t* (CEF_CALLBACK *)(struct _cef_frame_t* self))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.get_parent);
	lpStruct->get_url = (cef_string_userfree_t (CEF_CALLBACK *)(struct _cef_frame_t* self))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.get_url);
	lpStruct->get_browser = (struct _cef_browser_t* (CEF_CALLBACK *)(struct _cef_frame_t* self))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.get_browser);
	lpStruct->get_v8context = (struct _cef_v8context_t* (CEF_CALLBACK *)(struct _cef_frame_t* self))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.get_v8context);
	lpStruct->visit_dom = (void (CEF_CALLBACK *)(struct _cef_frame_t* self, struct _cef_domvisitor_t* visitor))(*env)->GetIntLongField(env, lpObject, cef_frame_tFc.visit_dom);
	return lpStruct;
}

void setcef_frame_tFields(JNIEnv *env, jobject lpObject, cef_frame_t *lpStruct)
{
	if (!cef_frame_tFc.cached) cachecef_frame_tFields(env, lpObject);
	setcef_base_tFields(env, lpObject, (cef_base_t *)lpStruct);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.is_valid, (jintLong)lpStruct->is_valid);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.undo, (jintLong)lpStruct->undo);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.redo, (jintLong)lpStruct->redo);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.cut, (jintLong)lpStruct->cut);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.copy, (jintLong)lpStruct->copy);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.paste, (jintLong)lpStruct->paste);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.del, (jintLong)lpStruct->del);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.select_all, (jintLong)lpStruct->select_all);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.view_source, (jintLong)lpStruct->view_source);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.get_source, (jintLong)lpStruct->get_source);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.get_text, (jintLong)lpStruct->get_text);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.load_request, (jintLong)lpStruct->load_request);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.load_url, (jintLong)lpStruct->load_url);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.load_string, (jintLong)lpStruct->load_string);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.execute_java_script, (jintLong)lpStruct->execute_java_script);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.is_main, (jintLong)lpStruct->is_main);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.is_focused, (jintLong)lpStruct->is_focused);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.get_name, (jintLong)lpStruct->get_name);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.get_identifier, (jintLong)lpStruct->get_identifier);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.get_parent, (jintLong)lpStruct->get_parent);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.get_url, (jintLong)lpStruct->get_url);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.get_browser, (jintLong)lpStruct->get_browser);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.get_v8context, (jintLong)lpStruct->get_v8context);
	(*env)->SetIntLongField(env, lpObject, cef_frame_tFc.visit_dom, (jintLong)lpStruct->visit_dom);
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

#ifndef NO_cef_settings_t
typedef struct cef_settings_t_FID_CACHE {
	int cached;
	jclass clazz;
	jfieldID size, single_process, browser_subprocess_path, multi_threaded_message_loop, command_line_args_disabled, cache_path, user_agent, product_version, locale, log_file, log_severity, javascript_flags, auto_detect_proxy_settings_enabled, resources_dir_path, locales_dir_path, pack_loading_disabled, remote_debugging_port;
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
	cef_settings_tFc.javascript_flags = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "javascript_flags", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_settings_tFc.auto_detect_proxy_settings_enabled = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "auto_detect_proxy_settings_enabled", "I");
	cef_settings_tFc.resources_dir_path = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "resources_dir_path", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_settings_tFc.locales_dir_path = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "locales_dir_path", "Lorg/eclipse/swt/internal/cef3/cef_string_t;");
	cef_settings_tFc.pack_loading_disabled = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "pack_loading_disabled", "I");
	cef_settings_tFc.remote_debugging_port = (*env)->GetFieldID(env, cef_settings_tFc.clazz, "remote_debugging_port", "I");
	cef_settings_tFc.cached = 1;
}

cef_settings_t *getcef_settings_tFields(JNIEnv *env, jobject lpObject, cef_settings_t *lpStruct)
{
	if (!cef_settings_tFc.cached) cachecef_settings_tFields(env, lpObject);
	lpStruct->size = (*env)->GetIntLongField(env, lpObject, cef_settings_tFc.size);
	lpStruct->single_process = (*env)->GetIntField(env, lpObject, cef_settings_tFc.single_process);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.browser_subprocess_path);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->browser_subprocess_path);
	}
	lpStruct->multi_threaded_message_loop = (*env)->GetIntField(env, lpObject, cef_settings_tFc.multi_threaded_message_loop);
	lpStruct->command_line_args_disabled = (*env)->GetIntField(env, lpObject, cef_settings_tFc.command_line_args_disabled);
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
	lpStruct->log_severity = (*env)->GetIntField(env, lpObject, cef_settings_tFc.log_severity);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.javascript_flags);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->javascript_flags);
	}
	lpStruct->auto_detect_proxy_settings_enabled = (*env)->GetIntField(env, lpObject, cef_settings_tFc.auto_detect_proxy_settings_enabled);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.resources_dir_path);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->resources_dir_path);
	}
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_settings_tFc.locales_dir_path);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->locales_dir_path);
	}
	lpStruct->pack_loading_disabled = (*env)->GetIntField(env, lpObject, cef_settings_tFc.pack_loading_disabled);
	lpStruct->remote_debugging_port = (*env)->GetIntField(env, lpObject, cef_settings_tFc.remote_debugging_port);
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
	lpStruct->length = (*env)->GetIntLongField(env, lpObject, cef_string_tFc.length);
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
	lpStruct->ex_style = (*env)->GetIntField(env, lpObject, cef_window_info_tFc.ex_style);
	{
	jobject lpObject1 = (*env)->GetObjectField(env, lpObject, cef_window_info_tFc.window_name);
	if (lpObject1 != NULL) getcef_string_tFields(env, lpObject1, &lpStruct->window_name);
	}
	lpStruct->style = (*env)->GetIntField(env, lpObject, cef_window_info_tFc.style);
	lpStruct->x = (*env)->GetIntField(env, lpObject, cef_window_info_tFc.x);
	lpStruct->y = (*env)->GetIntField(env, lpObject, cef_window_info_tFc.y);
	lpStruct->width = (*env)->GetIntField(env, lpObject, cef_window_info_tFc.width);
	lpStruct->height = (*env)->GetIntField(env, lpObject, cef_window_info_tFc.height);
	lpStruct->parent_window = (cef_window_handle_t)(*env)->GetIntLongField(env, lpObject, cef_window_info_tFc.parent_window);
	lpStruct->menu = (HMENU)(*env)->GetIntLongField(env, lpObject, cef_window_info_tFc.menu);
	lpStruct->transparent_painting = (*env)->GetIntField(env, lpObject, cef_window_info_tFc.transparent_painting);
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

