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

import org.eclipse.swt.internal.*;


public class CEF3 extends C {

/**
 * @param client cast=(struct _cef_client_t*)
 * @param url cast=(const cef_string_t*)
 */
public static final native int cef_browser_host_create_browser(cef_window_info_t windowInfo, long /*int*/ client, long /*int*/ url, cef_browser_settings_t settings);

public static final native int cef_build_revision();

/** @param application cast=(struct _cef_app_t*) */
public static final native int cef_execute_process(cef_main_args_t args, long /*int*/ application);

/** @param application cast=(struct _cef_app_t*) */
public static final native int cef_initialize(cef_main_args_t args, cef_settings_t settings, long /*int*/ application);

/** @param str cast=(cef_string_t*) */
public static final native void cef_string_clear(long /*int*/ str);

/**
 * @param src cast=(void*)
 * @param output cast=(cef_string_t*)
 */
public static final native int cef_string_set(char[] src, long /*int*/ src_len, long /*int*/ output, int copy);

public static final native long /*int*/ cef_string_userfree_alloc();

/** @param str cast=(cef_string_userfree_t) */
public static final native void cef_string_userfree_free(long /*int*/ str);

/**
 * @param dest cast=(void *),flags=no_in critical
 * @param src cast=(const void *)
 * @param size cast=(size_t)
 */
public static final native void memmove(cef_base_t dest, long /*int*/ src, long /*int*/ size);

/**
 * @param dest cast=(void *),flags=no_in critical
 * @param src cast=(const void *)
 * @param size cast=(size_t)
 */
public static final native void memmove(cef_browser_t dest, long /*int*/ src, long /*int*/ size);

/**
 * @param dest cast=(void *),flags=no_in critical
 * @param src cast=(const void *)
 * @param size cast=(size_t)
 */
public static final native void memmove(cef_browser_host_t dest, long /*int*/ src, long /*int*/ size);

/**
 * @param dest cast=(void *),flags=no_in critical
 * @param src cast=(const void *)
 * @param size cast=(size_t)
 */
public static final native void memmove(cef_frame_t dest, long /*int*/ src, long /*int*/ size);

/**
 * @param dest cast=(void *),flags=no_in critical
 * @param src cast=(const void *)
 * @param size cast=(size_t)
 */
public static final native void memmove(cef_string_t dest, long /*int*/ src, long /*int*/ size);


/* function ptr invocations */

/** @method convention=CEF_CALLBACK */
public static final native int callFuncInt(long /*int*/ address, long /*int*/ arg0);
/** @method convention=CEF_CALLBACK */
public static final native void callFuncVoid(long /*int*/ address, long /*int*/ arg0);
/** @method convention=CEF_CALLBACK */
public static final native long /*int*/ callFuncPtr(long /*int*/ address, long /*int*/ arg0);
/** @method convention=CEF_CALLBACK */
public static final native void callFuncVoid(long /*int*/ address, long /*int*/ arg0, long /*int*/ arg1);
/** @method convention=CEF_CALLBACK */
public static final native void callFuncVoid(long /*int*/ address, long /*int*/ arg0, long /*int*/ arg1, long /*int*/ arg2);
/** @method convention=CEF_CALLBACK */
public static final native void callFuncVoid(long /*int*/ address, long /*int*/ arg0, long /*int*/ arg1, long /*int*/ arg2, int arg3);


/* 64 bit */

public static final native int cef_base_t_sizeof();
public static final native int cef_browser_t_sizeof();
public static final native int cef_browser_host_t_sizeof();
public static final native int cef_browser_settings_t_sizeof();
public static final native int cef_frame_t_sizeof();
public static final native int cef_settings_t_sizeof();
public static final native int cef_string_t_sizeof();

}
