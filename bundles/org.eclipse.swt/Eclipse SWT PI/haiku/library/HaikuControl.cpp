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


#include "HaikuControl.h"

#include <View.h>

#include <private/shared/AutoLocker.h>

#include "HaikuDisplay.h"
#include "HaikuJNIContext.h"
#include "HaikuUtils.h"


using namespace swt::haiku;


namespace swt {
namespace haiku {


HaikuControl::HaikuControl()
{
}


HaikuControl::~HaikuControl()
{
}


}	// namespace haiku
}	// namespace swt


// #pragma mark - native methods


extern "C" jobject
Java_org_eclipse_swt_internal_haiku_HaikuControl_getPreferredSize(
	JNIEnv* env, jobject object, jlong handle, jint wHint, jint hHint)
{
	HAIKU_JNI_ENTER(env);

	HaikuControl* control = HaikuControl::Get(handle);
	AutoLocker<HaikuControl> controlLocker(control);
	BSize size = control->ControlPreferredSize(wHint, hHint);
	controlLocker.Unlock();

	return HaikuUtils::CreatePoint(env, size);
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuControl_setAndGetFrame(
	JNIEnv* env, jobject object, jlong handle, jintArray _frame,
	jbooleanArray _moveResize)
{
	HAIKU_JNI_ENTER(env);

	jint* frame = env->GetIntArrayElements(_frame, NULL);
	if (frame == NULL)
		return;

	jboolean* moveResize = env->GetBooleanArrayElements(_moveResize, NULL);
	if (frame == NULL) {
		env->ReleaseIntArrayElements(_frame, frame, JNI_ABORT);
		return;
	}

	HaikuControl* control = HaikuControl::Get(handle);
	AutoLocker<HaikuControl> controlLocker(control);

	BRect oldFrame = control->ControlFrame();
	if (moveResize[0]) {
		BPoint leftTop(frame[0], frame[1]);
		if (leftTop != oldFrame.LeftTop())
			control->ControlMoveTo(leftTop);
		else
			moveResize[0] = false;
	}

	if (moveResize[1]) {
		BSize size(frame[2] - 1, frame[3] - 1);
		if (size != oldFrame.Size())
			control->ControlResizeTo(size);
		else
			moveResize[1] = false;
	}

	BRect newFrame = control->ControlFrame();

	controlLocker.Unlock();

	frame[0] = (jint)newFrame.left;
	frame[1] = (jint)newFrame.top;
	frame[2] = (jint)newFrame.IntegerWidth() + 1;
	frame[3] = (jint)newFrame.IntegerHeight() + 1;

	env->ReleaseIntArrayElements(_frame, frame, JNI_COMMIT);
	env->ReleaseBooleanArrayElements(_moveResize, moveResize, JNI_COMMIT);
}
