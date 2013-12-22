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


#include "HaikuComposite.h"

#include "swt.h"

#include <algorithm>

#include <private/shared/AutoLocker.h>

#include "HaikuDisplay.h"
#include "HaikuJNIContext.h"
#include "HaikuUtils.h"


using namespace swt::haiku;


namespace swt {
namespace haiku {


HaikuComposite::HaikuComposite()
{
}


HaikuComposite::~HaikuComposite()
{
}


}	// namespace haiku
}	// namespace swt


// #pragma mark - native methods


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuComposite_addChild(
	JNIEnv* env, jobject object, jintLong handle, jintLong childHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuComposite* parent = HaikuComposite::Get(handle);
	HaikuControl* child = HaikuControl::Get(childHandle);
	AutoLocker<HaikuComposite> parentLocker(parent);
	parent->CompositeAddChild(child);
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuComposite_removeChild(
	JNIEnv* env, jobject object, jintLong handle, jintLong childHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuComposite* parent = HaikuComposite::Get(handle);
	HaikuControl* child = HaikuControl::Get(childHandle);
	AutoLocker<HaikuComposite> parentLocker(parent);
	parent->CompositeRemoveChild(child);
}


extern "C" jintLongArray
Java_org_eclipse_swt_internal_haiku_HaikuComposite_getChildren(
	JNIEnv* env, jobject object, jintLong handle)
{
	HAIKU_JNI_ENTER(env);

	HaikuComposite* parent = HaikuComposite::Get(handle);
	AutoLocker<HaikuComposite> parentLocker(parent);

	BObjectList<HaikuControl> children;
	parent->CompositeGetChildren(children);
	int32 count = children.CountItems();
	if (count == 0)
		return 0;

	jintLongArray handleArray = env->NewIntLongArray(count);
	if (handleArray == NULL)
		return NULL;

	jintLong* handles = (jintLong*)env->GetPrimitiveArrayCritical(handleArray,
		NULL);
	if (handles == NULL)
		return NULL;

	for (int32 i = 0; i < count; i++)
		handles[i] = children.ItemAt(i)->Handle();

	env->ReleasePrimitiveArrayCritical(handleArray, handles, JNI_COMMIT);
	return handleArray;
}
