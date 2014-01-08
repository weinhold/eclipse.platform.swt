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


#include "Composite.h"

#include <algorithm>

#include "JNIContext.h"
#include "Utils.h"


using namespace swt::qt;


namespace swt {
namespace qt {


Composite::Composite()
{
}


Composite::~Composite()
{
}


}	// namespace qt
}	// namespace swt


// native methods


extern "C" void
Java_org_eclipse_swt_internal_qt_QtComposite_addChild(
	JNIEnv* env, jclass clazz, jintLong handle, jintLong childHandle)
{
	SWT_QT_JNI_ENTER(env)

	Composite* parent = Composite::get(handle);
	Control* child = Control::get(childHandle);
	parent->swtAddChild(child);

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtComposite_removeChild(
	JNIEnv* env, jclass clazz, jintLong handle, jintLong childHandle)
{
	SWT_QT_JNI_ENTER(env)

	Composite* parent = Composite::get(handle);
	Control* child = Control::get(childHandle);
	parent->swtRemoveChild(child);

	SWT_QT_JNI_EXIT(env,)
}


extern "C" jintLongArray
Java_org_eclipse_swt_internal_qt_QtComposite_getChildren(
	JNIEnv* env, jclass clazz, jintLong handle)
{
	SWT_QT_JNI_ENTER(env)

	Composite* parent = Composite::get(handle);

	QList<Control*> children = parent->swtGetChildren();
	return Utils::createHandleArray(env, children);

	SWT_QT_JNI_EXIT(env, NULL)
}
