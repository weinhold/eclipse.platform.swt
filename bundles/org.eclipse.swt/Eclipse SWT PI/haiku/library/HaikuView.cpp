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

#include <Autolock.h>
#include <View.h>

#include "HaikuDisplay.h"
#include "HaikuJNIContext.h"


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
