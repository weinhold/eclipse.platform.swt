/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others. All rights reserved.
 * The contents of this file are made available under the terms
 * of the GNU Lesser General Public License (LGPL) Version 2.1 that
 * accompanies this distribution (lgpl-v21.txt).  The LGPL is also
 * available at http://www.gnu.org/licenses/lgpl.html.  If the version
 * of the LGPL at http://www.gnu.org is different to the version of
 * the LGPL accompanying this distribution and there is any conflict
 * between the two license versions, the terms of the LGPL accompanying
 * this distribution shall govern.
 * 
 * Contributors:
 *     Ingo Weinhold
 *******************************************************************************/


#include "swt.h"

#include <algorithm>

#include <Autolock.h>
#include <View.h>

#include <private/shared/AutoLocker.h>

#include "HaikuDisplay.h"
#include "HaikuJNIContext.h"
#include "HaikuUtils.h"


using namespace swt::haiku;


// #pragma mark - native methods


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuView_addChild(
	JNIEnv* env, jobject object, jlong handle, jlong childHandle)
{
	HaikuJNIContext haikuJniContext(env);

	BView* view = (BView*)(addr_t)handle;
	BView* childView = (BView*)(addr_t)childHandle;
	bool locked = view->LockLooper();
	view->AddChild(childView);
	if (locked)
		view->UnlockLooper();
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuView_removeChild(
	JNIEnv* env, jobject object, jlong handle, jlong childHandle)
{
	HaikuJNIContext haikuJniContext(env);

	BView* view = (BView*)(addr_t)handle;
	BView* childView = (BView*)(addr_t)childHandle;
	bool locked = view->LockLooper();
	view->RemoveChild(childView);
	if (locked)
		view->UnlockLooper();
}


extern "C" jlongArray
Java_org_eclipse_swt_internal_haiku_HaikuView_getChildren(
	JNIEnv* env, jobject object, jlong handle)
{
	HaikuJNIContext haikuJniContext(env);

	BView* view = (BView*)(addr_t)handle;
	
	if (!view->LockLooper())
		return NULL;
	AutoLocker<BLooper> windowLocker(view->Looper(), true);

	int32 count = view->CountChildren();
	if (count == 0)
		return NULL;

	jlongArray handleArray = env->NewLongArray(count);
	if (handleArray == NULL)
		return NULL;

	jlong* handles = (jlong*)env->GetPrimitiveArrayCritical(handleArray, NULL);
	if (handles == NULL)
		return NULL;

	for (int32 i = 0; i < count; i++)
		handles[i] = (jlong)(addr_t)view->ChildAt(i);

	env->ReleasePrimitiveArrayCritical(handleArray, handles, JNI_COMMIT);
	return handleArray;
}


extern "C" jobject
Java_org_eclipse_swt_internal_haiku_HaikuView_getPreferredSize(
	JNIEnv* env, jobject object, jlong handle, jint wHint, jint hHint)
{
	HaikuJNIContext haikuJniContext(env);

	BView* view = (BView*)(addr_t)handle;
	BSize size;
	if (view->LockLooper()) {
		// If hints have been specified, make sure they lie within the
		// min/max range for the view.
		if (wHint >= 0 || hHint >= 0) {
			BSize min = view->MinSize();
			BSize max = view->MaxSize();
			if (wHint >= 0) {
				wHint = std::max(wHint, (jint)min.width + 1);
				wHint = std::min(wHint, (jint)max.width + 1);
			}
			if (hHint >= 0) {
				hHint = std::max(hHint, (jint)min.height + 1);
				hHint = std::min(hHint, (jint)max.height + 1);
			}
		}

		// The Haiku API doesn't support getting the width for a height, so we
		// always get the preferred width, if the width isn't given.
		BSize preferred = view->PreferredSize();
		size.width = wHint >= 0 ? float(wHint - 1) : preferred.width;

		if (view->HasHeightForWidth()) {
			view->GetHeightForWidth(size.width, NULL, NULL, &size.height);
		} else {
			size.height = hHint >= 0
				? hHint - 1 : view->PreferredSize().height;
		}

		view->UnlockLooper();
	}

	return HaikuUtils::CreatePoint(env, size);
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuView_setAndGetFrame(
	JNIEnv* env, jobject object, jlong handle, jintArray _frame,
	jbooleanArray _moveResize)
{
	HaikuJNIContext haikuJniContext(env);

	jint* frame = env->GetIntArrayElements(_frame, NULL);
	if (frame == NULL)
		return;

	jboolean* moveResize = env->GetBooleanArrayElements(_moveResize, NULL);
	if (frame == NULL) {
		env->ReleaseIntArrayElements(_frame, frame, JNI_ABORT);
		return;
	}

	BView* view = (BView*)(addr_t)handle;
	BRect newFrame;
	if (view->LockLooper()) {
		BRect oldFrame = view->Frame();
		if (moveResize[0]) {
			BPoint leftTop(frame[0], frame[1]);
			if (leftTop != oldFrame.LeftTop())
				view->MoveTo(leftTop);
			else
				moveResize[0] = false;
		}

		if (moveResize[1]) {
			BSize size(frame[2] - 1, frame[3] - 1);
			if (size != oldFrame.Size())
				view->ResizeTo(size.width, size.height);
			else
				moveResize[1] = false;
		}

		newFrame = view->Frame();

		view->UnlockLooper();
	} else {
		moveResize[0] = false;
		moveResize[1] = false;
	}

	frame[0] = (jint)newFrame.left;
	frame[1] = (jint)newFrame.top;
	frame[2] = (jint)newFrame.IntegerWidth() + 1;
	frame[3] = (jint)newFrame.IntegerHeight() + 1;

	env->ReleaseIntArrayElements(_frame, frame, JNI_COMMIT);
	env->ReleaseBooleanArrayElements(_moveResize, moveResize, JNI_COMMIT);
}
