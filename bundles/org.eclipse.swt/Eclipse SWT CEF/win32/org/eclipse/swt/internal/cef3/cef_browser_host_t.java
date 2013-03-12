/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.internal.cef3;


public class cef_browser_host_t extends cef_base_t {

	/** @field cast=(struct _cef_browser_t* (CEF_CALLBACK *)(struct _cef_browser_host_t* self)) */
	public long /*int*/ get_browser;

	/** @field cast=(void (CEF_CALLBACK *)(struct _cef_browser_host_t* self)) */
	public long /*int*/ parent_window_will_close;

	/** @field cast=(void (CEF_CALLBACK *)(struct _cef_browser_host_t* self)) */
	public long /*int*/ close_browser;

	/** @field cast="(void (CEF_CALLBACK *)(struct _cef_browser_host_t* self, int enable))" */
	public long /*int*/ set_focus;

	/** @field cast=(cef_window_handle_t (CEF_CALLBACK *)(struct _cef_browser_host_t* self)) */
	public long /*int*/ get_window_handle;

	/** @field cast=(cef_window_handle_t (CEF_CALLBACK *)(struct _cef_browser_host_t* self)) */
	public long /*int*/ get_opener_window_handle;

	/** @field cast=(struct _cef_client_t* (CEF_CALLBACK *)(struct _cef_browser_host_t* self)) */
	public long /*int*/ get_client;

	/** @field cast="(cef_string_userfree_t (CEF_CALLBACK *)(struct _cef_browser_host_t* self, int http_scheme))" */
	public long /*int*/ get_dev_tools_url;

	/** @field cast=(double (CEF_CALLBACK *)(struct _cef_browser_host_t* self)) */
	public long /*int*/ get_zoom_level;

	/** @field cast="(void (CEF_CALLBACK *)(struct _cef_browser_host_t* self, double zoomLevel))" */
	public long /*int*/ set_zoom_level;

	/** @field cast="(void (CEF_CALLBACK *)(struct _cef_browser_host_t* self, enum cef_file_dialog_mode_t mode, const cef_string_t* title, const cef_string_t* default_file_name, cef_string_list_t accept_types, struct _cef_run_file_dialog_callback_t* callback))" */
	public long /*int*/ run_file_dialog;
	
	public static final int sizeof = CEF3.cef_browser_host_t_sizeof ();
}
