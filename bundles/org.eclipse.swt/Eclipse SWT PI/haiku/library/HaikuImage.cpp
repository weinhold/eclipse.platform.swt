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


#include "HaikuImage.h"

#include <sys/stat.h>

#include <Bitmap.h>
#include <BitmapStream.h>
#include <File.h>
#include <TranslatorRoster.h>

#include "HaikuJNIContext.h"
#include "HaikuUtils.h"
#include "SWTDefs.h"


using namespace swt::haiku;


namespace swt {
namespace haiku {


HaikuImage::HaikuImage()
	:
	fBitmap(NULL)
{
}


HaikuImage::~HaikuImage()
{
	delete fBitmap;
}


status_t
HaikuImage::Load(BPositionIO& data)
{
	delete fBitmap;
	fBitmap = NULL;

	BBitmapStream bitmapStream;
	status_t error = BTranslatorRoster::Default()->Translate(&data, NULL, NULL,
		&bitmapStream, B_TRANSLATOR_BITMAP);
	if (error != B_OK)
		return error;
	
	return bitmapStream.DetachBitmap(&fBitmap);
}


}	// namespace haiku
}	// namespace swt


// #pragma mark - native methods


extern "C" jlong
Java_org_eclipse_swt_internal_haiku_HaikuImage_load(
	JNIEnv* env, jobject object, jstring fileName)
{
	HAIKU_JNI_ENTER(env);

	BFile file;
	status_t error = file.SetTo(HaikuUtils::FromJavaString(env, fileName),
		B_READ_ONLY);
	if (error != B_OK) {
		HaikuUtils::ThrowSWTException(env, SWT_ERROR_IO);
		return 0;
	}

	HaikuImage* image = new(std::nothrow) HaikuImage;
	if (image == NULL)
		return 0;

	error = image->Load(file);
	if (error != B_OK) {
		delete image;
		// Translate the error to an SWTException code as good as we can.
		jint code;
		switch (error) {
			case B_NO_MEMORY:
				return 0;
			case B_BAD_DATA:
				code = SWT_ERROR_INVALID_IMAGE;
				break;
			case B_IO_ERROR:
			case B_PERMISSION_DENIED:
				code = SWT_ERROR_IO;
				break;
			case B_NO_TRANSLATOR:
			case B_NOT_SUPPORTED:
			default:
				code = SWT_ERROR_UNSUPPORTED_FORMAT;
				break;
		}
		HaikuUtils::ThrowSWTException(env, code);
		return 0;
	}

	return image->Handle();
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuImage_delete(
	JNIEnv* env, jobject object, jlong handle)
{
	HAIKU_JNI_ENTER(env);

	HaikuImage* image = HaikuImage::Get(handle);
	delete image;
}

