/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.internal.cef3;


public class cef_frame_t extends cef_base_t {
	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_frame_t* self)) */
	public long /*int*/ is_valid;
	
	/** @field cast=(void (CEF_CALLBACK *)(struct _cef_frame_t* self)) */
	public long /*int*/ undo;
	
	/** @field cast=(void (CEF_CALLBACK *)(struct _cef_frame_t* self)) */
	public long /*int*/ redo;
	
	/** @field cast=(void (CEF_CALLBACK *)(struct _cef_frame_t* self)) */
	public long /*int*/ cut;
	
	/** @field cast=(void (CEF_CALLBACK *)(struct _cef_frame_t* self)) */
	public long /*int*/ copy;
	
	/** @field cast=(void (CEF_CALLBACK *)(struct _cef_frame_t* self)) */
	public long /*int*/ paste;
	
	/** @field cast=(void (CEF_CALLBACK *)(struct _cef_frame_t* self)) */
	public long /*int*/ del;
	
	/** @field cast=(void (CEF_CALLBACK *)(struct _cef_frame_t* self)) */
	public long /*int*/ select_all;
	
	/** @field cast=(void (CEF_CALLBACK *)(struct _cef_frame_t* self)) */
	public long /*int*/ view_source;
	
	/** @field cast="(void (CEF_CALLBACK *)(struct _cef_frame_t* self, struct _cef_string_visitor_t* visitor))" */
	public long /*int*/ get_source;
	
	/** @field cast="(void (CEF_CALLBACK *)(struct _cef_frame_t* self, struct _cef_string_visitor_t* visitor))" */
	public long /*int*/ get_text;
	
	/** @field cast="(void (CEF_CALLBACK *)(struct _cef_frame_t* self, struct _cef_request_t* request))" */
	public long /*int*/ load_request;
	
	/** @field cast="(void (CEF_CALLBACK *)(struct _cef_frame_t* self, const cef_string_t* url))" */
	public long /*int*/ load_url;
	
	/** @field cast="(void (CEF_CALLBACK *)(struct _cef_frame_t* self, const cef_string_t* string_val, const cef_string_t* url))" */
	public long /*int*/ load_string;
	
	/** @field cast="(void (CEF_CALLBACK *)(struct _cef_frame_t* self, const cef_string_t* code, const cef_string_t* script_url, int start_line))" */
	public long /*int*/ execute_java_script;
	
	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_frame_t* self)) */
	public long /*int*/ is_main;
	
	/** @field cast=(int (CEF_CALLBACK *)(struct _cef_frame_t* self)) */
	public long /*int*/ is_focused;
	
	/** @field cast=(cef_string_userfree_t (CEF_CALLBACK *)(struct _cef_frame_t* self)) */
	public long /*int*/ get_name;
	
	/** @field cast=(int64 (CEF_CALLBACK *)(struct _cef_frame_t* self)) */
	public long /*int*/ get_identifier;
	
	/** @field cast=(struct _cef_frame_t* (CEF_CALLBACK *)(struct _cef_frame_t* self)) */
	public long /*int*/ get_parent;
	
	/** @field cast=(cef_string_userfree_t (CEF_CALLBACK *)(struct _cef_frame_t* self)) */
	public long /*int*/ get_url;
	
	/** @field cast=(struct _cef_browser_t* (CEF_CALLBACK *)(struct _cef_frame_t* self)) */
	public long /*int*/ get_browser;
	
	/** @field cast=(struct _cef_v8context_t* (CEF_CALLBACK *)(struct _cef_frame_t* self)) */
	public long /*int*/ get_v8context;
	
	/** @field cast="(void (CEF_CALLBACK *)(struct _cef_frame_t* self, struct _cef_domvisitor_t* visitor))" */
	public long /*int*/ visit_dom;
	
	public static final int sizeof = CEF3.cef_frame_t_sizeof ();
}
