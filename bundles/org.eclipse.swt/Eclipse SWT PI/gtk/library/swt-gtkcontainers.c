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

/*
 * This file is logically part of swt.c, and will be eventually merged back.
 */

/**
 * SWT natives for GTK container widgets.
 */ 

#include "swt.h"
#include "structs.h"

#include <stdio.h>
#include <assert.h>


/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_box_pack_start
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1box_1pack_1start
  (JNIEnv *env, jclass that, jint box, jint child, jboolean expand, jboolean fill, jint padding)
{
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_box_pack_start");
#endif

	gtk_box_pack_start((GtkBox*)box, (GtkWidget*)child, (gboolean)expand, (gboolean)fill, padding);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_box_pack_end
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1box_1pack_1end
  (JNIEnv *env, jclass that, jint box, jint child, jboolean expand, jboolean fill, jint padding)
{
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_box_pack_end");
#endif

	gtk_box_pack_end((GtkBox*)box, (GtkWidget*)child, (gboolean)expand, (gboolean)fill, padding);
}






/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_container_add
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1container_1add
  (JNIEnv *env, jclass that, jint container, jint widget)
{
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_container_add");
#endif

	gtk_container_add((GtkContainer*)container, (GtkWidget*)widget);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_container_remove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1container_1remove
  (JNIEnv *env, jclass that, jint container, jint widget)
{
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_container_remove");
#endif

	gtk_container_remove((GtkContainer*)container, (GtkWidget*)widget);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_container_children
 * Signature:	
 */
JNIEXPORT jint JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1container_1children
  (JNIEnv *env, jclass that, jint container)
{
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_container_children");
#endif

	return (jint)gtk_container_children((GtkContainer*)container);
}


JNIEXPORT jint JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1event_1box_1new
  (JNIEnv *env, jclass that)
{
	return (jint)gtk_event_box_new();
}


JNIEXPORT jint JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1fixed_1new
  (JNIEnv *env, jclass that)
{
	return (jint)gtk_fixed_new();
}

JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1fixed_1put
  (JNIEnv *env, jclass that, jint fixed, jint widget, jshort x, jshort y)
{
	gtk_fixed_put((GtkFixed*)fixed, (GtkWidget*)widget, (gint16)x, (gint16)y);
}

JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1fixed_1move
  (JNIEnv *env, jclass that, jint fixed, jint widget, jshort x, jshort y)
{
	gtk_fixed_move((GtkFixed*)fixed, (GtkWidget*)widget, (gint16)x, (gint16)y);
}






/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_frame_new
 * Signature:	
 */
JNIEXPORT jint JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1frame_1new
  (JNIEnv *env, jclass that, jbyteArray label)
{
	jint rc;
	jbyte *label1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_frame_new");
#endif

	if (label) {
		label1 = (*env)->GetByteArrayElements(env, label, NULL);
	}
	rc = (jint)gtk_frame_new((gchar*)label1);
	if (label) {
		(*env)->ReleaseByteArrayElements(env, label, label1, 0);
	}
	return rc;
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_frame_set_label
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1frame_1set_1label
  (JNIEnv *env, jclass that, jint frame, jbyteArray label)
{
	jbyte *label1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_frame_set_label");
#endif

	if (label) {
		label1 = (*env)->GetByteArrayElements(env, label, NULL);
	}
	gtk_frame_set_label((GtkFrame*)frame, (gchar*)label1);
	if (label) {
		(*env)->ReleaseByteArrayElements(env, label, label1, 0);
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_frame_set_shadow_type
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1frame_1set_1shadow_1type
  (JNIEnv *env, jclass that, jint frame, jint type)
{
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_frame_set_shadow_type");
#endif

	gtk_frame_set_shadow_type((GtkFrame*)frame, (GtkShadowType)type);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_hbox_new
 * Signature:	
 */
JNIEXPORT jint JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1hbox_1new
  (JNIEnv *env, jclass that, jboolean homogeneous, jint spacing)
{
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_hbox_new");
#endif

	return (jint)gtk_hbox_new((gboolean)homogeneous, (gint)spacing);
}





