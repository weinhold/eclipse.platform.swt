


/*  ***MEMMOVE***  */





/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GdkColor_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GdkColor src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGdkColorFids(env, src, &PGLOB(GdkColorFc));
		getGdkColorFields(env, src, src1, &PGLOB(GdkColorFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GdkColor_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GdkColor_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GdkColor dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GdkColor_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGdkColorFids(env, dest, &PGLOB(GdkColorFc));
		setGdkColorFields(env, dest, dest1, &PGLOB(GdkColorFc));
	}
}

JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkDialog_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkDialog dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkDialog_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkDialogFids(env, dest, &PGLOB(GtkDialogFc));
		setGtkDialogFields(env, dest, dest1, &PGLOB(GtkDialogFc));
	}
}

JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkStyleClass_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkStyleClass dest_struct, *dest1 = NULL;

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkStyleClassFids(env, dest, &PGLOB(GtkStyleClassFc));
		setGtkStyleClassFields(env, dest, dest1, &PGLOB(GtkStyleClassFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GdkEventKey_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GdkEvent src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGdkEventKeyFids(env, src, &PGLOB(GdkEventKeyFc));
		getGdkEventKeyFields(env, src, src1, &PGLOB(GdkEventKeyFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GdkEventKey_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GdkEventKey_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GdkEvent dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GdkEventKey_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGdkEventKeyFids(env, dest, &PGLOB(GdkEventKeyFc));
		setGdkEventKeyFields(env, dest, dest1, &PGLOB(GdkEventKeyFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GdkEventButton_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GdkEvent src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGdkEventButtonFids(env, src, &PGLOB(GdkEventButtonFc));
		getGdkEventButtonFields(env, src, src1, &PGLOB(GdkEventButtonFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GdkEventButton_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GdkEventButton_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GdkEvent dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GdkEventButton_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGdkEventButtonFids(env, dest, &PGLOB(GdkEventButtonFc));
		setGdkEventButtonFields(env, dest, dest1, &PGLOB(GdkEventButtonFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GdkEventMotion_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GdkEvent src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGdkEventMotionFids(env, src, &PGLOB(GdkEventMotionFc));
		getGdkEventMotionFields(env, src, src1, &PGLOB(GdkEventMotionFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GdkEventMotion_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GdkEventMotion_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GdkEvent dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GdkEventMotion_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGdkEventMotionFids(env, dest, &PGLOB(GdkEventMotionFc));
		setGdkEventMotionFields(env, dest, dest1, &PGLOB(GdkEventMotionFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GdkEventExpose_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GdkEvent src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGdkEventExposeFids(env, src, &PGLOB(GdkEventExposeFc));
		getGdkEventExposeFields(env, src, src1, &PGLOB(GdkEventExposeFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GdkEventExpose_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GdkEventExpose_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GdkEvent dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GdkEventExpose_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGdkEventExposeFids(env, dest, &PGLOB(GdkEventExposeFc));
		setGdkEventExposeFields(env, dest, dest1, &PGLOB(GdkEventExposeFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GdkFont_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GdkFont src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGdkFontFids(env, src, &PGLOB(GdkFontFc));
		getGdkFontFields(env, src, src1, &PGLOB(GdkFontFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GdkFont_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GdkFont_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GdkFont dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GdkFont_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGdkFontFids(env, dest, &PGLOB(GdkFontFc));
		setGdkFontFields(env, dest, dest1, &PGLOB(GdkFontFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GdkGCValues_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GdkGCValues src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGdkGCValuesFids(env, src, &PGLOB(GdkGCValuesFc));
		getGdkGCValuesFields(env, src, src1, &PGLOB(GdkGCValuesFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GdkGCValues_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GdkGCValues_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GdkGCValues dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GdkGCValues_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGdkGCValuesFids(env, dest, &PGLOB(GdkGCValuesFc));
		setGdkGCValuesFields(env, dest, dest1, &PGLOB(GdkGCValuesFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GdkImage_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GdkImage src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGdkImageFids(env, src, &PGLOB(GdkImageFc));
		getGdkImageFields(env, src, src1, &PGLOB(GdkImageFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GdkImage_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GdkImage_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GdkImage dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GdkImage_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGdkImageFids(env, dest, &PGLOB(GdkImageFc));
		setGdkImageFields(env, dest, dest1, &PGLOB(GdkImageFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GdkPoint_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GdkPoint src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGdkPointFids(env, src, &PGLOB(GdkPointFc));
		getGdkPointFields(env, src, src1, &PGLOB(GdkPointFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GdkPoint_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GdkPoint_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GdkPoint dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GdkPoint_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGdkPointFids(env, dest, &PGLOB(GdkPointFc));
		setGdkPointFields(env, dest, dest1, &PGLOB(GdkPointFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GdkRectangle_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GdkRectangle src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGdkRectangleFids(env, src, &PGLOB(GdkRectangleFc));
		getGdkRectangleFields(env, src, src1, &PGLOB(GdkRectangleFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GdkRectangle_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GdkRectangle_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GdkRectangle dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GdkRectangle_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGdkRectangleFids(env, dest, &PGLOB(GdkRectangleFc));
		setGdkRectangleFields(env, dest, dest1, &PGLOB(GdkRectangleFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GdkVisual_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GdkVisual src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGdkVisualFids(env, src, &PGLOB(GdkVisualFc));
		getGdkVisualFields(env, src, src1, &PGLOB(GdkVisualFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GdkVisual_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GdkVisual_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GdkVisual dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GdkVisual_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGdkVisualFids(env, dest, &PGLOB(GdkVisualFc));
		setGdkVisualFields(env, dest, dest1, &PGLOB(GdkVisualFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkAllocation_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkAllocation src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkAllocationFids(env, src, &PGLOB(GtkAllocationFc));
		getGtkAllocationFields(env, src, src1, &PGLOB(GtkAllocationFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkAllocation_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkAllocation_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkAllocation dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkAllocation_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkAllocationFids(env, dest, &PGLOB(GtkAllocationFc));
		setGtkAllocationFields(env, dest, dest1, &PGLOB(GtkAllocationFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkArg_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkArg src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkArgFids(env, src, &PGLOB(GtkArgFc));
		getGtkArgFields(env, src, src1, &PGLOB(GtkArgFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkArg_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkArg_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkArg dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkArg_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkArgFids(env, dest, &PGLOB(GtkArgFc));
		setGtkArgFields(env, dest, dest1, &PGLOB(GtkArgFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkBin_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkBin src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkBinFids(env, src, &PGLOB(GtkBinFc));
		getGtkBinFields(env, src, src1, &PGLOB(GtkBinFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkBin_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkBin_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkBin dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkBin_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkBinFids(env, dest, &PGLOB(GtkBinFc));
		setGtkBinFields(env, dest, dest1, &PGLOB(GtkBinFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkCList_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkCList src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkCListFids(env, src, &PGLOB(GtkCListFc));
		getGtkCListFields(env, src, src1, &PGLOB(GtkCListFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkCList_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkCList_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkCList dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkCList_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkCListFids(env, dest, &PGLOB(GtkCListFc));
		setGtkCListFields(env, dest, dest1, &PGLOB(GtkCListFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkMenu_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkMenu src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkMenuFids(env, src, &PGLOB(GtkMenuFc));
		getGtkMenuFields(env, src, src1, &PGLOB(GtkMenuFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkMenu_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkMenu_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkMenu dest_struct={0}, *dest1 = NULL;

#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkMenu_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkMenuFids(env, dest, &PGLOB(GtkMenuFc));
		setGtkMenuFields(env, dest, dest1, &PGLOB(GtkMenuFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkCTree_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkCTree src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkCTreeFids(env, src, &PGLOB(GtkCTreeFc));
		getGtkCTreeFields(env, src, src1, &PGLOB(GtkCTreeFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkCTree_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkCTree_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkCTree dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkCTree_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkCTreeFids(env, dest, &PGLOB(GtkCTreeFc));
		setGtkCTreeFields(env, dest, dest1, &PGLOB(GtkCTreeFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkColorSelectionDialog_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkColorSelectionDialog src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkColorSelectionDialogFids(env, src, &PGLOB(GtkColorSelectionDialogFc));
		getGtkColorSelectionDialogFields(env, src, src1, &PGLOB(GtkColorSelectionDialogFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkColorSelectionDialog_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkColorSelectionDialog_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkColorSelectionDialog dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkColorSelectionDialog_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkColorSelectionDialogFids(env, dest, &PGLOB(GtkColorSelectionDialogFc));
		setGtkColorSelectionDialogFields(env, dest, dest1, &PGLOB(GtkColorSelectionDialogFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkCombo_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkCombo src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkComboFids(env, src, &PGLOB(GtkComboFc));
		getGtkComboFields(env, src, src1, &PGLOB(GtkComboFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkCombo_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkCombo_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkCombo dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkCombo_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkComboFids(env, dest, &PGLOB(GtkComboFc));
		setGtkComboFields(env, dest, dest1, &PGLOB(GtkComboFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkContainer_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkContainer src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkContainerFids(env, src, &PGLOB(GtkContainerFc));
		getGtkContainerFields(env, src, src1, &PGLOB(GtkContainerFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkContainer_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkContainer_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkContainer dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkContainer_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkContainerFids(env, dest, &PGLOB(GtkContainerFc));
		setGtkContainerFields(env, dest, dest1, &PGLOB(GtkContainerFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkData_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkData src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkDataFids(env, src, &PGLOB(GtkDataFc));
		getGtkDataFields(env, src, src1, &PGLOB(GtkDataFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkData_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkData_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkData dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkData_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkDataFids(env, dest, &PGLOB(GtkDataFc));
		setGtkDataFields(env, dest, dest1, &PGLOB(GtkDataFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkEditable_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkEditable src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkEditableFids(env, src, &PGLOB(GtkEditableFc));
		getGtkEditableFields(env, src, src1, &PGLOB(GtkEditableFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkEditable_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkEditable_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkEditable dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkEditable_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkEditableFids(env, dest, &PGLOB(GtkEditableFc));
		setGtkEditableFields(env, dest, dest1, &PGLOB(GtkEditableFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkText_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkText src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkTextFids(env, src, &PGLOB(GtkTextFc));
		getGtkTextFields(env, src, src1, &PGLOB(GtkTextFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkText_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkText_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkText dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkText_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkTextFids(env, dest, &PGLOB(GtkTextFc));
		setGtkTextFields(env, dest, dest1, &PGLOB(GtkTextFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	gtk_text_inquiry
 * Signature:	
 * Not implemented yet;
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_gtk_text_inquiry__Lorg_eclipse_swt_internal_gtk_GtkText_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{

}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkFileSelection_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkFileSelection src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkFileSelectionFids(env, src, &PGLOB(GtkFileSelectionFc));
		getGtkFileSelectionFields(env, src, src1, &PGLOB(GtkFileSelectionFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkFileSelection_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkFileSelection_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkFileSelection dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkFileSelection_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkFileSelectionFids(env, dest, &PGLOB(GtkFileSelectionFc));
		setGtkFileSelectionFields(env, dest, dest1, &PGLOB(GtkFileSelectionFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkFontSelectionDialog_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkFontSelectionDialog src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkFontSelectionDialogFids(env, src, &PGLOB(GtkFontSelectionDialogFc));
		getGtkFontSelectionDialogFields(env, src, src1, &PGLOB(GtkFontSelectionDialogFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkFontSelectionDialog_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkFontSelectionDialog_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkFontSelectionDialog dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkFontSelectionDialog_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkFontSelectionDialogFids(env, dest, &PGLOB(GtkFontSelectionDialogFc));
		setGtkFontSelectionDialogFields(env, dest, dest1, &PGLOB(GtkFontSelectionDialogFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkObject_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkObject src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkObjectFids(env, src, &PGLOB(GtkObjectFc));
		getGtkObjectFields(env, src, src1, &PGLOB(GtkObjectFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkObject_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkObject_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkObject dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkObject_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkObjectFids(env, dest, &PGLOB(GtkObjectFc));
		setGtkObjectFields(env, dest, dest1, &PGLOB(GtkObjectFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkProgress_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkProgress src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkProgressFids(env, src, &PGLOB(GtkProgressFc));
		getGtkProgressFields(env, src, src1, &PGLOB(GtkProgressFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkProgress_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkProgress_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkProgress dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkProgress_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkProgressFids(env, dest, &PGLOB(GtkProgressFc));
		setGtkProgressFields(env, dest, dest1, &PGLOB(GtkProgressFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkProgressBar_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkProgressBar src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkProgressBarFids(env, src, &PGLOB(GtkProgressBarFc));
		getGtkProgressBarFields(env, src, src1, &PGLOB(GtkProgressBarFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkProgressBar_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkProgressBar_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkProgressBar dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkProgressBar_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkProgressBarFids(env, dest, &PGLOB(GtkProgressBarFc));
		setGtkProgressBarFields(env, dest, dest1, &PGLOB(GtkProgressBarFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkRequisition_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkRequisition src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkRequisitionFids(env, src, &PGLOB(GtkRequisitionFc));
		getGtkRequisitionFields(env, src, src1, &PGLOB(GtkRequisitionFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkRequisition_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkRequisition_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkRequisition dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkRequisition_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkRequisitionFids(env, dest, &PGLOB(GtkRequisitionFc));
		setGtkRequisitionFields(env, dest, dest1, &PGLOB(GtkRequisitionFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkStyle_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkStyle src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkStyleFids(env, src, &PGLOB(GtkStyleFc));
		getGtkStyleFields(env, src, src1, &PGLOB(GtkStyleFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkStyle_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkStyle_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkStyle dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkStyle_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkStyleFids(env, dest, &PGLOB(GtkStyleFc));
		setGtkStyleFields(env, dest, dest1, &PGLOB(GtkStyleFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkWidget_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkWidget src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkWidgetFids(env, src, &PGLOB(GtkWidgetFc));
		getGtkWidgetFields(env, src, src1, &PGLOB(GtkWidgetFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkWidget_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkWidget_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkWidget dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkWidget_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkWidgetFids(env, dest, &PGLOB(GtkWidgetFc));
		setGtkWidgetFields(env, dest, dest1, &PGLOB(GtkWidgetFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkFrame_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkFrame src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkFrameFids(env, src, &PGLOB(GtkFrameFc));
		getGtkFrameFields(env, src, src1, &PGLOB(GtkFrameFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkFrame_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkFrame_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkFrame dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkFrame_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkFrameFids(env, dest, &PGLOB(GtkFrameFc));
		setGtkFrameFields(env, dest, dest1, &PGLOB(GtkFrameFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkWindow_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkWindow src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkWindowFids(env, src, &PGLOB(GtkWindowFc));
		getGtkWindowFields(env, src, src1, &PGLOB(GtkWindowFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkWindow_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkWindow_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkWindow dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkWindow_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkWindowFids(env, dest, &PGLOB(GtkWindowFc));
		setGtkWindowFields(env, dest, dest1, &PGLOB(GtkWindowFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkCheckMenuItem_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkCheckMenuItem src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkCheckMenuItemFids(env, src, &PGLOB(GtkCheckMenuItemFc));
		getGtkCheckMenuItemFields(env, src, src1, &PGLOB(GtkCheckMenuItemFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkCheckMenuItem_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkCheckMenuItem_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkCheckMenuItem dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkCheckMenuItem_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkCheckMenuItemFids(env, dest, &PGLOB(GtkCheckMenuItemFc));
		setGtkCheckMenuItemFields(env, dest, dest1, &PGLOB(GtkCheckMenuItemFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkAdjustment_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkAdjustment src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkAdjustmentFids(env, src, &PGLOB(GtkAdjustmentFc));
		getGtkAdjustmentFields(env, src, src1, &PGLOB(GtkAdjustmentFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkAdjustment_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkAdjustment_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkAdjustment dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkAdjustment_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkAdjustmentFids(env, dest, &PGLOB(GtkAdjustmentFc));
		setGtkAdjustmentFields(env, dest, dest1, &PGLOB(GtkAdjustmentFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkBox_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkBox src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkBoxFids(env, src, &PGLOB(GtkBoxFc));
		getGtkBoxFields(env, src, src1, &PGLOB(GtkBoxFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkBox_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkBox_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkBox dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkBox_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkBoxFids(env, dest, &PGLOB(GtkBoxFc));
		setGtkBoxFields(env, dest, dest1, &PGLOB(GtkBoxFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkHBox_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkHBox src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkHBoxFids(env, src, &PGLOB(GtkHBoxFc));
		getGtkHBoxFields(env, src, src1, &PGLOB(GtkHBoxFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkHBox_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkHBox_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkHBox dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkHBox_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkHBoxFids(env, dest, &PGLOB(GtkHBoxFc));
		setGtkHBoxFields(env, dest, dest1, &PGLOB(GtkHBoxFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkMenuItem_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkMenuItem src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkMenuItemFids(env, src, &PGLOB(GtkMenuItemFc));
		getGtkMenuItemFields(env, src, src1, &PGLOB(GtkMenuItemFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkMenuItem_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkMenuItem_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkMenuItem dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkMenuItem_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkMenuItemFids(env, dest, &PGLOB(GtkMenuItemFc));
		setGtkMenuItemFields(env, dest, dest1, &PGLOB(GtkMenuItemFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkCListRow_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkCListRow src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkCListRowFids(env, src, &PGLOB(GtkCListRowFc));
		getGtkCListRowFields(env, src, src1, &PGLOB(GtkCListRowFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkCListRow_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkCListRow_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkCListRow dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkCListRow_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkCListRowFids(env, dest, &PGLOB(GtkCListRowFc));
		setGtkCListRowFields(env, dest, dest1, &PGLOB(GtkCListRowFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkCListColumn_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkCListColumn src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkCListColumnFids(env, src, &PGLOB(GtkCListColumnFc));
		getGtkCListColumnFields(env, src, src1, &PGLOB(GtkCListColumnFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkCListColumn_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkCListColumn_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkCListColumn dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkCListColumn_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkCListColumnFids(env, dest, &PGLOB(GtkCListColumnFc));
		setGtkCListColumnFields(env, dest, dest1, &PGLOB(GtkCListColumnFc));
	}
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__ILorg_eclipse_swt_internal_gtk_GtkCTreeRow_2I
  (JNIEnv *env, jclass that, jint dest, jobject src, jint count)
{
	DECL_GLOB(pGlob)
	GtkCTreeRow src_struct={0}, *src1 = NULL;
	if (src) {
		src1 = &src_struct;
		cacheGtkCTreeRowFids(env, src, &PGLOB(GtkCTreeRowFc));
		getGtkCTreeRowFields(env, src, src1, &PGLOB(GtkCTreeRowFc));
	}
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__ILorg_eclipse_swt_internal_gtk_GtkCTreeRow_2I\n");
#endif

	memmove((void*)dest, (void*)src1, count);
}

/*
 * Class:	org_eclipse_swt_internal_gtk_OS
 * Method:	memmove
 * Signature:	
 */
JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__Lorg_eclipse_swt_internal_gtk_GtkCTreeRow_2II
  (JNIEnv *env, jclass that, jobject dest, jint src, jint count)
{
	DECL_GLOB(pGlob)
	GtkCTreeRow dest_struct={0}, *dest1 = NULL;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__Lorg_eclipse_swt_internal_gtk_GtkCTreeRow_2II\n");
#endif

	memmove((void*)&dest_struct, (void*)src, count);
	if (dest) {
		dest1 = &dest_struct;
		cacheGtkCTreeRowFids(env, dest, &PGLOB(GtkCTreeRowFc));
		setGtkCTreeRowFields(env, dest, dest1, &PGLOB(GtkCTreeRowFc));
	}
}

JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__I_3BI
  (JNIEnv *env, jclass that, jint dest, jbyteArray src, jint count)
{
	jbyte *src1;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__I_3BI\n");
#endif

	if (src) {
		src1 = (*env)->GetByteArrayElements(env, src, NULL);
		memmove((void*)dest, (void*)src1, count);
		(*env)->ReleaseByteArrayElements(env, src, src1, 0);
	}
}

JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove__I_3II
  (JNIEnv *env, jclass that, jint dest, jintArray src, jint count)
{
	jint *src1;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__I_3II\n");
#endif

	if (src) {
		src1 = (*env)->GetIntArrayElements(env, src, NULL);
		memmove((void*)dest, (void*)src1, count);
		(*env)->ReleaseIntArrayElements(env, src, src1, 0);
	}
}

JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove___3III
  (JNIEnv *env, jclass that, jintArray dest, jint src, jint count)
{
	jint *dest1;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove__III\n");
#endif

	if (dest) {
		dest1 = (*env)->GetIntArrayElements(env, dest, NULL);
		memmove((void*)dest1, (void*)src, count);
		(*env)->ReleaseIntArrayElements(env, dest, dest1, 0);
	}
}

JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove___3BII
  (JNIEnv *env, jclass that, jbyteArray dest, jint src, jint count)
{
	jbyte *dest1;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove___3BII\n");
#endif

	if (dest) {
		dest1 = (*env)->GetByteArrayElements(env, dest, NULL);
		memmove((void*)dest1, (void*)src, count);
		(*env)->ReleaseByteArrayElements(env, dest, dest1, 0);
	}
}

JNIEXPORT void JNICALL Java_org_eclipse_swt_internal_gtk_OS_memmove___3I_3BI
  (JNIEnv *env, jclass that, jintArray dest, jbyteArray src, jint count)
{
	jint *dest1;
	jbyte *src1;
#ifdef DEBUG_CALL_PRINTS
	fprintf(stderr, "memmove___3I_3BI\n");
#endif

	if (src && dest) {
		dest1 = (*env)->GetIntArrayElements(env, dest, NULL);
		src1 = (*env)->GetByteArrayElements(env, dest, NULL);
		memmove((void*)dest1, (void*)src1, count);
		(*env)->ReleaseIntArrayElements(env, dest, dest1, 0);
		(*env)->ReleaseByteArrayElements(env, src, src1, 0);
	}
}


