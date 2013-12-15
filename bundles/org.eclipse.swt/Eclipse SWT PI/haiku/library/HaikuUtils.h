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

#ifndef INC_HAIKU_UTILS_H
#define INC_HAIKU_UTILS_H


#include <Point.h>
#include <Size.h>
#include <String.h>

#include "swt.h"


namespace swt {
namespace haiku {


class HaikuUtils {
public:
	static	bool				Init(JNIEnv* env);
	static	void				Cleanup(JNIEnv* env);

	static	BString				FromJavaString(jstring javaString);
	static	jstring				ToJavaString(const BString& string);

	static	jobject				CreatePoint(JNIEnv *env, jint x, jint y);
	static	jobject				CreatePoint(JNIEnv *env, const BPoint& point);
	static	jobject				CreatePoint(JNIEnv *env, const BSize& size);

private:
	static	jclass				sPointClass;
	static	jmethodID			sPointConstructor;
};


/*static*/ inline jobject
HaikuUtils::CreatePoint(JNIEnv *env, jint x, jint y)
{
	return env->NewObject(sPointClass, sPointConstructor, x, y);
}


/*static*/ inline jobject
HaikuUtils::CreatePoint(JNIEnv *env, const BPoint& point)
{
	return CreatePoint(env, (jint)point.x, (jint)point.y);
}


/*static*/ inline jobject
HaikuUtils::CreatePoint(JNIEnv *env, const BSize& size)
{
	return CreatePoint(env, (jint)size.width + 1, (jint)size.height + 1);
}


}	// namespace haiku
}	// namespace swt


#endif /* INC_HAIKU_UTILS_H */
