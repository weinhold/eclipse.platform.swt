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


#include "HaikuGraphicsContext.h"

#include <private/shared/AutoLocker.h>

#include "HaikuJNIContext.h"
#include "HaikuUtils.h"


using namespace swt::haiku;


namespace swt {
namespace haiku {


HaikuGraphicsContext::HaikuGraphicsContext()
{
}


HaikuGraphicsContext::~HaikuGraphicsContext()
{
}


}	// namespace haiku
}	// namespace swt


// #pragma mark - native methods


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuGraphicsContext_delete(
	JNIEnv* env, jclass clazz, jlong handle)
{
	HAIKU_JNI_ENTER(env);

	HaikuGraphicsContext* graphicsContext = HaikuGraphicsContext::Get(handle);
	delete graphicsContext;
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuGraphicsContext_drawRectangle(
	JNIEnv* env, jclass clazz, jlong handle, jint x, jint y, jint width,
	jint height)
{
	HAIKU_JNI_ENTER(env);

	HaikuGraphicsContext* graphicsContext = HaikuGraphicsContext::Get(handle);
	AutoLocker<HaikuGraphicsContext> graphicsContextLocker(graphicsContext);
	graphicsContext->DrawRectangle(BRect(x, y, x + width - 1, y + height - 1));
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuGraphicsContext_drawString(
	JNIEnv* env, jclass clazz, jlong handle, jint x, jint y, jstring string,
	jboolean isTransparent)
{
	HAIKU_JNI_ENTER(env);

	HaikuGraphicsContext* graphicsContext = HaikuGraphicsContext::Get(handle);
	AutoLocker<HaikuGraphicsContext> graphicsContextLocker(graphicsContext);
	graphicsContext->DrawString(BPoint(x, y),
		HaikuUtils::FromJavaString(string), isTransparent);
}


extern "C" jobject
Java_org_eclipse_swt_internal_haiku_HaikuGraphicsContext_getForegroundColor(
	JNIEnv* env, jclass clazz, jlong handle)
{
	HAIKU_JNI_ENTER(env);

	HaikuGraphicsContext* graphicsContext = HaikuGraphicsContext::Get(handle);
	AutoLocker<HaikuGraphicsContext> graphicsContextLocker(graphicsContext);
	rgb_color color = graphicsContext->ForegroundColor();
	graphicsContextLocker.Unlock();

	return HaikuUtils::CreateColor(env, color);
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuGraphicsContext_setForegroundColor(
	JNIEnv* env, jclass clazz, jlong handle, jbyte red, jbyte green, jbyte blue)
{
	HAIKU_JNI_ENTER(env);

	HaikuGraphicsContext* graphicsContext = HaikuGraphicsContext::Get(handle);
	AutoLocker<HaikuGraphicsContext> graphicsContextLocker(graphicsContext);
	rgb_color color = graphicsContext->ForegroundColor();
	color.red = red;
	color.green = green;
	color.blue = blue;
	graphicsContext->SetForegroundColor(color);
	graphicsContextLocker.Unlock();
}
