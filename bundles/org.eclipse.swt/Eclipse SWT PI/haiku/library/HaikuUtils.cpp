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


#include "HaikuUtils.h"

#include <new>

#include <ByteOrder.h>
#include <UTF8.h>

#include <private/shared/AutoDeleter.h>

#include "HaikuJNIContext.h"


namespace swt {
namespace haiku {


jclass HaikuUtils::sPointClass = NULL;
jmethodID HaikuUtils::sPointConstructor = NULL;


/*static*/ bool
HaikuUtils::Init(JNIEnv* env)
{
	// get the Point class and constructor
	jclass clazz = env->FindClass("org/eclipse/swt/graphics/Point");
	if (clazz == NULL)
		return false;

	sPointClass = (jclass)env->NewGlobalRef(clazz);
	if (sPointClass == NULL)
		return false;

	sPointConstructor = env->GetMethodID(sPointClass, "<init>", "(II)V");
	if (sPointConstructor == NULL)
		return false;

	return true;
}


/*static*/ void
HaikuUtils::Cleanup(JNIEnv* env)
{
	if (sPointClass != NULL) {
		env->DeleteGlobalRef(sPointClass);
		sPointClass = NULL;
	}
	sPointConstructor = NULL;
}


/*static*/ BString
HaikuUtils::FromJavaString(jstring javaString)
{
	JNIEnv* env = HaikuJNIContext::CurrentEnv();
	const jchar* javaUtf16 = env->GetStringChars(javaString, NULL);
	const jchar* utf16 = javaUtf16;
	jsize utf16Count = env->GetStringLength(javaString);
	if (utf16 == NULL || utf16Count == 0)
		return BString();

	// convert_to_utf8() only supports UTF-16BE, so we need to swap the bytes
	// on little-endian machines.
#if B_HOST_IS_LENDIAN
	jchar* swappedUtf16 = new(std::nothrow) jchar[utf16Count];
	if (swappedUtf16 == NULL)
		return BString();
	ArrayDeleter<jchar> swappedUtf16Deleter;

	for (jsize i = 0; i < utf16Count; i++)
		swappedUtf16[i] = B_SWAP_INT16(utf16[i]);
	utf16 = swappedUtf16;
#endif

	BString result;
	char* buffer = result.LockBuffer(utf16Count * 3);
		// *3 is a worst case estimate

	int32 sourceLength = utf16Count * 2;
	int32 destLength = utf16Count * 3;
	int32 state = 0;
	if (convert_to_utf8(B_UTF16_CONVERSION, (const char*)utf16,
			&sourceLength, buffer, &destLength, &state) != B_OK) {
		return BString();
	}

	env->ReleaseStringChars(javaString, javaUtf16);

	result.UnlockBuffer(destLength);
	return result;
}


/*static*/ jstring
HaikuUtils::ToJavaString(const BString& string)
{
	JNIEnv* env = HaikuJNIContext::CurrentEnv();
	return env->NewStringUTF(string.String());
		// TODO: This isn't modified UTF-8! Use convert_from_utf8() to convert
		// to UTF-16 instead!
}


}	// namespace haiku
}	// namespace swt

