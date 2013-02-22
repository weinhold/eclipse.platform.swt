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

import org.eclipse.swt.browser.*;


public class cef_settings_t {
	public long /*int*/ size;

	public int single_process;
	public cef_string_t browser_subprocess_path = CEF.STRING_EMPTY;
	public int multi_threaded_message_loop;
	public int command_line_args_disabled;
	public cef_string_t cache_path = CEF.STRING_EMPTY;
	public cef_string_t user_agent = CEF.STRING_EMPTY;
	public cef_string_t product_version = CEF.STRING_EMPTY;
	public cef_string_t locale = CEF.STRING_EMPTY;
	public cef_string_t log_file = CEF.STRING_EMPTY;
	public int log_severity;
	public cef_string_t javascript_flags = CEF.STRING_EMPTY;
	public int auto_detect_proxy_settings_enabled;
	public cef_string_t resources_dir_path = CEF.STRING_EMPTY;
	public cef_string_t locales_dir_path = CEF.STRING_EMPTY;
	public int pack_loading_disabled;
	public int remote_debugging_port;
	  
	public static final int sizeof = CEF3.cef_settings_t_sizeof ();
}
