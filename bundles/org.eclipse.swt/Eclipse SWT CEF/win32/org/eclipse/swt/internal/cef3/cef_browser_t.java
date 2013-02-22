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


public class cef_browser_t extends cef_base_t {

	/** @field cast=(struct _cef_browser_host_t* (CEF_CALLBACK *)(struct _cef_browser_t* self)) */
	public long /*int*/ get_host;

	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_browser_t* self)) */
	public long /*int*/ can_go_back;
	
	/** @field cast=(void (CEF_CALLBACK *)(struct _cef_browser_t* self)) */
	public long /*int*/ go_back;
	
	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_browser_t* self)) */
	public long /*int*/ can_go_forward;
	
	/** @field cast=(void (CEF_CALLBACK *)(struct _cef_browser_t* self)) */
	public long /*int*/ go_forward;
	
	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_browser_t* self)) */
	public long /*int*/ is_loading;
	
	/** @field cast=(void (CEF_CALLBACK *)(struct _cef_browser_t* self)) */
	public long /*int*/ reload;
	
	/** @field cast=(void (CEF_CALLBACK *)(struct _cef_browser_t* self)) */
	public long /*int*/ reload_ignore_cache;
	
	/** @field cast=(void (CEF_CALLBACK *)(struct _cef_browser_t* self)) */
	public long /*int*/ stop_load;
	
	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_browser_t* self)) */
	public long /*int*/ get_identifier;
	
	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_browser_t* self, struct _cef_browser_t* that))" */
	public long /*int*/ is_same;
	
	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_browser_t* self)) */
	public long /*int*/ is_popup;
	
	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_browser_t* self)) */
	public long /*int*/ has_document;
	
	/** @field cast=(struct _cef_frame_t* (CEF_CALLBACK *)(struct _cef_browser_t* self)) */
	public long /*int*/ get_main_frame;
	
	/** @field cast=(struct _cef_frame_t* (CEF_CALLBACK *)(struct _cef_browser_t* self)) */
	public long /*int*/ get_focused_frame;
	
	/** @field cast="(struct _cef_frame_t* (CEF_CALLBACK *)(struct _cef_browser_t* self, int64 identifier))" */
	public long /*int*/ get_frame_byident;
	
	/** @field cast="(struct _cef_frame_t* (CEF_CALLBACK *)(struct _cef_browser_t* self, const cef_string_t* name))" */
	public long /*int*/ get_frame;
	
	/** @field cast=(size_t (CEF_CALLBACK *)(struct _cef_browser_t* self)) */
	public long /*int*/ get_frame_count;
	
	/** @field cast="(void (CEF_CALLBACK *)(struct _cef_browser_t* self, size_t* identifiersCount, int64* identifiers))" */
	public long /*int*/ get_frame_identifiers;
	
	/** @field cast="(void (CEF_CALLBACK *)(struct _cef_browser_t* self, cef_string_list_t names))" */
	public long /*int*/ get_frame_names;
	
	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_browser_t* self, enum cef_process_id_t target_process, struct _cef_process_message_t* message))" */
	public long /*int*/ send_process_message;
	
	public static final int sizeof = CEF3.cef_browser_t_sizeof ();
}
