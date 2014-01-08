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


#include "GraphicsContext.h"

#include "JNIContext.h"
#include "Utils.h"


using namespace swt::qt;


namespace swt {
namespace qt {


GraphicsContext::GraphicsContext(QPaintDevice* device)
	:
	fPainter(device)
{
}


GraphicsContext::~GraphicsContext()
{
}


}	// namespace qt
}	// namespace swt


// #pragma mark - native methods


extern "C" void
Java_org_eclipse_swt_internal_qt_QtGraphicsContext_delete(
	JNIEnv* env, jclass clazz, jintLong handle)
{
	SWT_QT_JNI_ENTER(env)

	GraphicsContext* graphicsContext = GraphicsContext::get(handle);
	delete graphicsContext;

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtGraphicsContext_drawRectangle(
	JNIEnv* env, jclass clazz, jintLong handle, jint x, jint y, jint width,
	jint height)
{
	SWT_QT_JNI_ENTER(env)

	GraphicsContext* graphicsContext = GraphicsContext::get(handle);
	graphicsContext->getPainter().drawRect(x, y, width, height);

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtGraphicsContext_drawString(
	JNIEnv* env, jclass clazz, jintLong handle, jint x, jint y, jstring string,
	jboolean isTransparent)
{
	SWT_QT_JNI_ENTER(env)

	GraphicsContext* graphicsContext = GraphicsContext::get(handle);
	QPainter& painter = graphicsContext->getPainter();

	Qt::BGMode oldBackgroundMode = painter.backgroundMode();
	painter.setBackgroundMode(isTransparent ? Qt::TransparentMode : Qt::OpaqueMode);

	painter.drawText(x, y + painter.fontMetrics().ascent(),
		Utils::fromJavaString(env, string));

	painter.setBackgroundMode(oldBackgroundMode);

	SWT_QT_JNI_EXIT(env,)
}


extern "C" jobject
Java_org_eclipse_swt_internal_qt_QtGraphicsContext_getForegroundColor(
	JNIEnv* env, jclass clazz, jintLong handle)
{
	SWT_QT_JNI_ENTER(env)

	GraphicsContext* graphicsContext = GraphicsContext::get(handle);
	QColor color = graphicsContext->getPainter().pen().color();
	return Utils::createColor(env, color);

	SWT_QT_JNI_EXIT(env, NULL)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtGraphicsContext_setForegroundColor(
	JNIEnv* env, jclass clazz, jintLong handle, jbyte red, jbyte green,
	jbyte blue)
{
	SWT_QT_JNI_ENTER(env)

	GraphicsContext* graphicsContext = GraphicsContext::get(handle);
	QColor color((quint8)red, (quint8)green, (quint8)blue);
	graphicsContext->getPainter().setPen(color);

	SWT_QT_JNI_EXIT(env,)
}
