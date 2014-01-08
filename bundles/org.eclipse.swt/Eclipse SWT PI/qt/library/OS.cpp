/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others. All rights reserved.
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

#include <Qt>

#include "JNIContext.h"
#include "Utils.h"


using namespace swt::qt;


static bool
initOSConstants(JNIEnv* env, jclass clazz)
{
	#define SET_STATIC_INT_FIELD(attributeName, name)							\
	{																			\
		jfieldID fieldID = env->GetStaticFieldID(clazz, #attributeName, "I");	\
		if (fieldID == NULL)													\
			return false;														\
		env->SetStaticIntField(clazz, fieldID, (jint)name);						\
		if (env->ExceptionOccurred() != NULL)									\
			return false;														\
	}

	SET_STATIC_INT_FIELD(Qt_AlignLeft, Qt::AlignLeft)
	SET_STATIC_INT_FIELD(Qt_AlignRight, Qt::AlignRight)
	SET_STATIC_INT_FIELD(Qt_AlignHCenter, Qt::AlignHCenter)

	SET_STATIC_INT_FIELD(Qt_AlignTop, Qt::AlignTop)
	SET_STATIC_INT_FIELD(Qt_AlignBottom, Qt::AlignBottom)
	SET_STATIC_INT_FIELD(Qt_AlignVCenter, Qt::AlignVCenter)

	#undef SET_STATIC_INT_FIELD

	return true;
}


// #pragma mark - native methods


extern "C" void
Java_org_eclipse_swt_internal_qt_OS_init(JNIEnv* env, jclass clazz)
{
	if (!JNIContext::init(env))
		return;

	SWT_QT_JNI_ENTER(env)

	if (!Utils::init(env))
		return;

	if (!initOSConstants(env, clazz))
		return;

	SWT_QT_JNI_EXIT(env,)
}
