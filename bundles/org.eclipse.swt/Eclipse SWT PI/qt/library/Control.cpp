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


#include "Control.h"

#include <QLayout>
#include <QWidget>

#include "JNIContext.h"
#include "Utils.h"


using namespace swt::qt;


namespace swt {
namespace qt {


Control::Control()
{
}


Control::~Control()
{
}


void
Control::swtInit()
{
	// resize the widget, so it doesn't change its size when made visible
	swtResizeTo(swtGetInitialSize());
}


QSize
Control::swtGetInitialSize()
{
	return QSize(1, 1);
}


}	// namespace qt
}	// namespace swt


// native methods


extern "C" jobject
Java_org_eclipse_swt_internal_qt_QtControl_getPreferredSize(
	JNIEnv* env, jclass clazz, jintLong handle, jint wHint, jint hHint)
{
	SWT_QT_JNI_ENTER(env)

	Control* control = Control::get(handle);
	QSize size = control->swtGetPreferredSize(wHint, hHint);

	return Utils::createPoint(env, size);

	SWT_QT_JNI_EXIT(env, NULL)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtControl_relayout(
	JNIEnv* env, jclass clazz, jintLong handle)
{
	SWT_QT_JNI_ENTER(env)

	Control* control = Control::get(handle);
	if (QLayout* layout = control->swtGetQWidget()->layout())
		layout->activate();

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtControl_setAndGetBounds(
	JNIEnv* env, jclass clazz, jintLong handle, jintArray _frame,
	jbooleanArray _moveResize)
{
	SWT_QT_JNI_ENTER(env)

	jint* frame = env->GetIntArrayElements(_frame, NULL);
	if (frame == NULL)
		return;

	jboolean* moveResize = env->GetBooleanArrayElements(_moveResize, NULL);
	if (frame == NULL) {
		env->ReleaseIntArrayElements(_frame, frame, JNI_ABORT);
		return;
	}

	Control* control = Control::get(handle);

	QRect oldRect = control->swtGetGeometry();
	if (moveResize[0]) {
		QPoint leftTop(frame[0], frame[1]);
		if (leftTop != oldRect.topLeft())
			control->swtMoveTo(leftTop);
		else
			moveResize[0] = false;
	}

	if (moveResize[1]) {
		QSize size(frame[2], frame[3]);
		if (size != oldRect.size())
			control->swtResizeTo(size);
		else
			moveResize[1] = false;
	}

	QRect newFrame = control->swtGetGeometry();

	frame[0] = (jint)newFrame.left();
	frame[1] = (jint)newFrame.top();
	frame[2] = (jint)newFrame.width();
	frame[3] = (jint)newFrame.height();

	env->ReleaseIntArrayElements(_frame, frame, JNI_COMMIT);
	env->ReleaseBooleanArrayElements(_moveResize, moveResize, JNI_COMMIT);

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtControl_setEnabled(
	JNIEnv* env, jclass clazz, jintLong handle, jboolean enabled)
{
	SWT_QT_JNI_ENTER(env)

	Control* control = Control::get(handle);
	control->swtSetEnabled(enabled);

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtControl_setStyle(
	JNIEnv* env, jclass clazz, jintLong handle, jint style)
{
	SWT_QT_JNI_ENTER(env)

	Control* control = Control::get(handle);
	control->swtSetStyle(style);

	SWT_QT_JNI_EXIT(env,)
}
