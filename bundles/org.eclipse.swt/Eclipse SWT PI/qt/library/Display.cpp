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


#include "Display.h"

#include <QApplication>

//#include "Button.h"
#include "JNIContext.h"
#include "Shell.h"


using namespace swt::qt;


namespace swt {
namespace qt {


Display* Display::sCurrentDisplay = NULL;


Display::Display()
	:
	fObject(NULL),
	fWidgetMovedCallback(NULL),
	fWidgetResizedCallback(NULL),
	fControlPaintCallback(NULL),
	fWidgetInvokedCallback(NULL),
	fShellCloseRequestedCallback(NULL)
{
}


Display::~Display()
{
	if (fObject != NULL) {
		JNIEnv* env = JNIContext::getEnv();
		env->DeleteGlobalRef(fObject);
	}

	sCurrentDisplay = NULL;
}


/*static*/ Display*
Display::create(jobject object)
{
	Display* display = new Display;
	if (!display->_init(object)) {
		delete display;
		return NULL;
	}

	sCurrentDisplay = display;
	return display;
}


void
Display::callbackWidgetMoved(Widget* widget, const QPoint& newPosition)
{
	JNIContext::getEnv()->CallVoidMethod(fObject,
		fWidgetMovedCallback, widget->getHandle(), (jint)newPosition.x(),
		(jint)newPosition.y());
}


void
Display::callbackWidgetResized(Widget* widget, const QSize& newSize)
{
	JNIContext::getEnv()->CallVoidMethod(fObject,
		fWidgetResizedCallback, widget->getHandle(), (jint)newSize.width(),
		(jint)newSize.height());
}


#if 0
void
Display::callbackControlPaint(Control* control,GraphicsContext* graphicsContext,
	const QRect& updateRect)
{
	JNIContext::getEnv()->CallVoidMethod(fObject,
		fControlPaintCallback, control->getHandle(), graphicsContext->getHandle(),
		(jint)updateRect.left(), (jint)updateRect.top(),
		(jint)updateRect.width(), (jint)updateRect.height());
}


void
Display::callbackWidgetInvoked(Button* button, bool selected,
	int what)
{
	JNIContext::getEnv()->CallVoidMethod(fObject,
		fWidgetInvokedCallback, button->getHandle(), selected, (jint)what);
}
#endif


bool
Display::callbackShellCloseRequested(Shell* shell)
{
	return JNIContext::getEnv()->CallBooleanMethod(fObject,
		fShellCloseRequestedCallback, shell->getHandle());
}


bool
Display::_init(jobject object)
{
	JNIEnv* env = JNIContext::getEnv();

	// get a global reference
	fObject = env->NewGlobalRef(object);

	// get the ID of the methods we may call
	jclass clazz = env->FindClass("org/eclipse/swt/widgets/Display");
	if (clazz == NULL)
		return false;

	#define GET_METHOD_ID(variable, name, signature)			\
		variable = env->GetMethodID(clazz, name, signature);	\
		if (variable == NULL)									\
			return false;

	GET_METHOD_ID(fWidgetMovedCallback, "qtWidgetMoved", "(" I_J "II)V");
	GET_METHOD_ID(fWidgetResizedCallback, "qtWidgetResized", "(" I_J "II)V");
#if 0
	GET_METHOD_ID(fControlPaintCallback, "qtControlPaintCallback",
		"(" I_J I_J "IIII)V");
	GET_METHOD_ID(fWidgetInvokedCallback, "qtWidgetInvokedCallback",
		"(" I_J "ZI)V");
#endif
	GET_METHOD_ID(fShellCloseRequestedCallback, "qtShellCloseRequested", "(" I_J ")Z");

	#undef GET_METHOD_ID

	return true;
}


}	// namespace qt
}	// namespace swt


// native methods


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtDisplay_create(
	JNIEnv* env, jclass clazz, jobject displayObject)
{
	SWT_QT_JNI_ENTER(env)

	Display* display = Display::create(displayObject);
	return display->getHandle();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtDisplay_delete(
	JNIEnv* env, jclass clazz, jintLong handle)
{
	SWT_QT_JNI_ENTER(env)

	Display* display = Display::get(handle);
	delete display;

	SWT_QT_JNI_EXIT(env,)
}


extern "C" jboolean
Java_org_eclipse_swt_internal_qt_QtDisplay_checkPendingEvents(
	JNIEnv* env, jclass clazz, jintLong handle, jboolean wait)
{
	SWT_QT_JNI_ENTER(env)

	if (!wait)
		return QCoreApplication::hasPendingEvents();

	for (;;) {
		if (QCoreApplication::hasPendingEvents())
			return true;

		// Alternatively we could use QCoreApplication::processEvents(). It
		// wouldn't delay events up to 1 ms, but it would automatically deliver
		// them already.
		Utils::msleep(1);
	}

	SWT_QT_JNI_EXIT(env, false)
}


extern "C" jboolean
Java_org_eclipse_swt_internal_qt_QtDisplay_handleNextEvent(
	JNIEnv* env, jclass clazz, jintLong handle)
{
	SWT_QT_JNI_ENTER(env)

	if (!QCoreApplication::hasPendingEvents())
		return false;

	QCoreApplication::processEvents();
	QCoreApplication::sendPostedEvents();
	return true;

	SWT_QT_JNI_EXIT(env, false)
}
