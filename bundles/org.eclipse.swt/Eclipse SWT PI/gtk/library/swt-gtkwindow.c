/*
 * Copyright (c) IBM Corp. 2000, 2002.  All rights reserved.
 *
 * The contents of this file are made available under the terms
 * of the GNU Lesser General Public License (LGPL) Version 2.1 that
 * accompanies this distribution (lgpl-v21.txt).  The LGPL is also
 * available at http://www.gnu.org/licenses/lgpl.html.  If the version
 * of the LGPL at http://www.gnu.org is different to the version of
 * the LGPL accompanying this distribution and there is any conflict
 * between the two license versions, the terms of the LGPL accompanying
 * this distribution shall govern.
 */

/**
 * SWT OS natives implementation: gtk_window_* functions.
 */ 

#include "swt.h"
#include "structs.h"

#include <stdio.h>
#include <assert.h>


/*
 * gtk_window_new
 */
JNIEXPORT jint JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1window_1new
  (JNIEnv *env, jclass that, jint type)
{
	return (jint)gtk_window_new((GtkWindowType)type);
}


/*
 * gtk_window_set_title
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1window_1set_1title
  (JNIEnv *env, jclass that, jint window, jbyteArray title)
{
	jbyte *title1 = NULL;
	if (title) {
		title1 = (*env)->GetByteArrayElements(env, title, NULL);
	}
	gtk_window_set_title((GtkWindow*)window, (gchar*)title1);
	if (title) {
		(*env)->ReleaseByteArrayElements(env, title, title1, 0);
	}
}


/*
 * gtk_window_set_policy
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1window_1set_1policy
  (JNIEnv *env, jclass that, jint window, jint allow_shrink, jint allow_grow, jint auto_shrink)
{
	gtk_window_set_policy((GtkWindow*)window, (gint)allow_shrink, (gint)allow_grow, (gint)auto_shrink);
}


/*
 * gtk_window_set_resizable
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1window_1set_1resizable
  (JNIEnv *env, jclass that, jint window, jboolean resizable)
{
	gtk_window_set_resizable((GtkWindow*)window, (gboolean)resizable);
}


/*
 * gtk_window_set_transient_for
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1window_1set_1transient_1for
  (JNIEnv *env, jclass that, jint window, jint parent)
{
	gtk_window_set_transient_for((GtkWindow*)window, (GtkWindow*)parent);
}


/*
 * gtk_window_set_modal
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1window_1set_1modal
  (JNIEnv *env, jclass that, jint window, jboolean modal)
{
	gtk_window_set_modal((GtkWindow*)window, modal);
}


JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1window_1add_1accel_1group
  (JNIEnv *env, jclass that, jint window, jint accel_group)
{
	gtk_window_add_accel_group((GtkWindow*)window, (GtkAccelGroup*)accel_group);
}

