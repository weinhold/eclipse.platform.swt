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

#include <private/shared/AutoLocker.h>

#include "HaikuDisplay.h"
#include "HaikuJNIContext.h"
#include "HaikuUtils.h"
#include "HaikuViewComposite.h"


using namespace swt::haiku;


typedef HaikuViewComposite<BStringView> HaikuLabel;


// #pragma mark - native methods


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuLabel_create(
	JNIEnv* env, jobject object, jintLong displayHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuLabel* label = new(std::nothrow) HaikuLabel((const char*)NULL, "");
	if (label == NULL)
		return 0;
	return label->Handle();
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuLabel_setAlignment(
	JNIEnv* env, jobject object, jintLong handle, jint alignment)
{
	HAIKU_JNI_ENTER(env);

	HaikuLabel* label = HaikuLabel::Get(handle);
	AutoLocker<HaikuLabel> labelLocker(label);
	label->SetAlignment((enum alignment)alignment);
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuLabel_setText(
	JNIEnv* env, jobject object, jintLong handle, jstring text)
{
	HAIKU_JNI_ENTER(env);

	HaikuLabel* label = HaikuLabel::Get(handle);
	AutoLocker<HaikuLabel> labelLocker(label);
	label->SetText(HaikuUtils::FromJavaString(env, text));
}
