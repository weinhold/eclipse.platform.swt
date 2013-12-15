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

#include <StringView.h>

#include "HaikuDisplay.h"
#include "HaikuJNIContext.h"
#include "HaikuUtils.h"


using namespace swt::haiku;


// #pragma mark - native methods


extern "C" jlong
Java_org_eclipse_swt_internal_haiku_HaikuLabel_create(
	JNIEnv* env, jobject object, jlong displayHandle)
{
	HaikuJNIContext haikuJniContext(env);

	return (jlong)(addr_t)new(std::nothrow) BStringView(NULL, "");
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuLabel_delete(JNIEnv* env,
	jobject object, jlong handle)
{
	HaikuJNIContext haikuJniContext(env);

	BStringView* view = (BStringView*)(addr_t)handle;
	delete view;
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuLabel_setAlignment(
	JNIEnv* env, jobject object, jlong handle, jint alignment)
{
	HaikuJNIContext haikuJniContext(env);

	BStringView* view = (BStringView*)(addr_t)handle;
	bool locked = view->LockLooper();
	view->SetAlignment(
		alignment == 0
			? B_ALIGN_CENTER
			: (alignment < 0 ? B_ALIGN_LEFT : B_ALIGN_RIGHT));
	if (locked)
		view->UnlockLooper();
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuLabel_setText(
	JNIEnv* env, jobject object, jlong handle, jstring text)
{
	HaikuJNIContext haikuJniContext(env);

	BStringView* view = (BStringView*)(addr_t)handle;
	bool locked = view->LockLooper();
	view->SetText(HaikuUtils::FromJavaString(text));
	if (locked)
		view->UnlockLooper();
}
