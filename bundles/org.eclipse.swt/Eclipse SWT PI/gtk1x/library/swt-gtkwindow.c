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
 * This file also contains all dialog functions (e.g.,
 * gtk_color_selection_*).
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






/*
 *  DIALOGS
 */

/*
 *  Color selection
 */



/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_color_selection_set_color
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1color_1selection_1set_1color
  (JNIEnv *env, jclass that, jint colorsel, jdoubleArray color)
{
	jdouble *color1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_color_selection_set_color");
#endif

	if (color) {
		color1 = (*env)->GetDoubleArrayElements(env, color, NULL);
	}
	gtk_color_selection_set_color((GtkColorSelection*)colorsel, (gdouble*)color1);
	if (color) {
		(*env)->ReleaseDoubleArrayElements(env, color, color1, 0);
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_color_selection_get_color
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1color_1selection_1get_1color
  (JNIEnv *env, jclass that, jint colorsel, jdoubleArray color)
{
	jdouble *color1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_color_selection_get_color");
#endif

	if (color) {
		color1 = (*env)->GetDoubleArrayElements(env, color, NULL);
	}
	gtk_color_selection_get_color((GtkColorSelection*)colorsel, (gdouble*)color1);
	if (color) {
		(*env)->ReleaseDoubleArrayElements(env, color, color1, 0);
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_color_selection_dialog_new
 * Signature:	
 */
JNIEXPORT jint JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1color_1selection_1dialog_1new
  (JNIEnv *env, jclass that, jbyteArray title)
{
	jint rc;
	jbyte *title1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_color_selection_dialog_new");
#endif

	if (title) {
		title1 = (*env)->GetByteArrayElements(env, title, NULL);
	}
	rc = (jint)gtk_color_selection_dialog_new((gchar*)title1);
	if (title) {
		(*env)->ReleaseByteArrayElements(env, title, title1, 0);
	}
	return rc;
}



/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_dialog_new
 * Signature:	
 */
JNIEXPORT jint JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1dialog_1new
  (JNIEnv *env, jclass that)
{
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_dialog_new");
#endif

	return (jint)gtk_dialog_new();
}









/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_file_selection_new
 * Signature:	
 */
JNIEXPORT jint JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1file_1selection_1new
  (JNIEnv *env, jclass that, jbyteArray title)
{
	jint rc;
	jbyte *title1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_file_selection_new");
#endif

	if (title) {
		title1 = (*env)->GetByteArrayElements(env, title, NULL);
	}
	rc = (jint)gtk_file_selection_new((gchar*)title1);
	if (title) {
		(*env)->ReleaseByteArrayElements(env, title, title1, 0);
	}
	return rc;
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_file_selection_set_filename
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1file_1selection_1set_1filename
  (JNIEnv *env, jclass that, jint filesel, jbyteArray filename)
{
	jbyte *filename1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_file_selection_set_filename");
#endif

	if (filename) {
		filename1 = (*env)->GetByteArrayElements(env, filename, NULL);
	}
	gtk_file_selection_set_filename((GtkFileSelection*)filesel, (gchar*)filename1);
	if (filename) {
		(*env)->ReleaseByteArrayElements(env, filename, filename1, 0);
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_file_selection_get_filename
 * Signature:	
 */
JNIEXPORT jint JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1file_1selection_1get_1filename
  (JNIEnv *env, jclass that, jint filesel)
{
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_file_selection_get_filename");
#endif

	return (jint)gtk_file_selection_get_filename((GtkFileSelection*)filesel);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_file_selection_complete
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1file_1selection_1complete
  (JNIEnv *env, jclass that, jint filesel, jbyteArray pattern)
{
	jbyte *pattern1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_file_selection_complete");
#endif

	if (pattern) {
		pattern1 = (*env)->GetByteArrayElements(env, pattern, NULL);
	}
	gtk_file_selection_complete((GtkFileSelection*)filesel, (gchar*)pattern1);
	if (pattern) {
		(*env)->ReleaseByteArrayElements(env, pattern, pattern1, 0);
	}
}








JNIEXPORT jboolean JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1font_1selection_1set_1font_1name
  (JNIEnv *env, jclass that, jint fontsel, jbyteArray fontname)
{
	jboolean rc;
	jbyte *fontname1 = NULL;
	if (fontname) {
		fontname1 = (*env)->GetByteArrayElements(env, fontname, NULL);
	}
	rc = (jboolean)gtk_font_selection_set_font_name((GtkFontSelection*)fontsel, (gchar*)fontname1);
	if (fontname) {
		(*env)->ReleaseByteArrayElements(env, fontname, fontname1, 0);
	}
	return rc;
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_font_selection_dialog_new
 * Signature:	
 */
JNIEXPORT jint JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1font_1selection_1dialog_1new
  (JNIEnv *env, jclass that, jbyteArray title)
{
	jint rc;
	jbyte *title1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_font_selection_dialog_new");
#endif

	if (title) {
		title1 = (*env)->GetByteArrayElements(env, title, NULL);
	}
	rc = (jint)gtk_font_selection_dialog_new((gchar*)title1);
	if (title) {
		(*env)->ReleaseByteArrayElements(env, title, title1, 0);
	}
	return rc;
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_font_selection_dialog_get_font_name
 * Signature:	
 */
JNIEXPORT jint JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1font_1selection_1dialog_1get_1font_1name
  (JNIEnv *env, jclass that, jint fsd)
{
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_font_selection_dialog_get_font_name");
#endif

	return (jint)gtk_font_selection_dialog_get_font_name((GtkFontSelectionDialog*)fsd);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_font_selection_dialog_set_font_name
 * Signature:	
 */
JNIEXPORT jboolean JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_1font_1selection_1dialog_1set_1font_1name
  (JNIEnv *env, jclass that, jint fsd, jbyteArray fontname)
{
	jboolean rc;
	jbyte *fontname1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "gtk_font_selection_dialog_set_font_name");
#endif

	if (fontname) {
		fontname1 = (*env)->GetByteArrayElements(env, fontname, NULL);
	}
	rc = (jboolean)gtk_font_selection_dialog_set_font_name((GtkFontSelectionDialog*)fsd, (gchar*)fontname1);
	if (fontname) {
		(*env)->ReleaseByteArrayElements(env, fontname, fontname1, 0);
	}
	return rc;
}





