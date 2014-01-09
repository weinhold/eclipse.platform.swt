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


#include "Image.h"

#include <QFile>

#include "JNIContext.h"
#include "Utils.h"
#include "SWTDefs.h"


using namespace swt::qt;


namespace swt {
namespace qt {


Image::Image(const QString& fileName)
	:
	fPixmap(fileName)
{
}


Image::~Image()
{
}


}	// namespace qt
}	// namespace swt


// native methods


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtImage_load(
	JNIEnv* env, jobject object, jstring fileName)
{
	SWT_QT_JNI_ENTER(env)

	QString qFileName(Utils::fromJavaString(env, fileName));
	if (!QFile(qFileName).exists()) {
		Utils::throwSWTException(env, SWT_ERROR_IO);
		return 0;
	}

	Image* image = new Image(qFileName);
	if (image->getPixmap().isNull()) {
		delete image;
		Utils::throwSWTException(env, SWT_ERROR_UNSUPPORTED_FORMAT);
		return 0;
	}

	return image->getHandle();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtImage_delete(
	JNIEnv* env, jobject object, jintLong handle)
{
	SWT_QT_JNI_ENTER(env)

	Image* image = Image::get(handle);
	delete image;

	SWT_QT_JNI_EXIT(env,)
}

