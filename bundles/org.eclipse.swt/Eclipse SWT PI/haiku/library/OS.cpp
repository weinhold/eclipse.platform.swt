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

#include <View.h>

#include "HaikuJNIContext.h"
#include "HaikuUtils.h"


using namespace swt::haiku;


static bool
initOSConstants(JNIEnv* env, jclass clazz)
{
	#define SET_STATIC_INT_FIELD(name)									\
	{																	\
		jfieldID fieldID = env->GetStaticFieldID(clazz, #name, "I");	\
		if (fieldID == NULL)											\
			return false;												\
		env->SetStaticIntField(clazz, fieldID, (jint)name);				\
		if (env->ExceptionOccurred() != NULL)							\
			return false;												\
	}

	SET_STATIC_INT_FIELD(B_ALIGN_LEFT)
	SET_STATIC_INT_FIELD(B_ALIGN_RIGHT)
	SET_STATIC_INT_FIELD(B_ALIGN_CENTER)

	#undef SET_STATIC_INT_FIELD

	return true;
}


// #pragma mark - native methods


extern "C" void
Java_org_eclipse_swt_internal_haiku_OS_init(JNIEnv* env, jclass clazz)
{
	if (!HaikuJNIContext::Init())
		return;

	HAIKU_JNI_ENTER(env);

	if (!HaikuUtils::Init(env))
		return;

	if (!initOSConstants(env, clazz))
		return;
}
