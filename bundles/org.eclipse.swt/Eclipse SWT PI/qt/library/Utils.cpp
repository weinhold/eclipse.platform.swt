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


#include "Utils.h"

#include <new>

#include "JNIContext.h"


namespace swt {
namespace qt {


jclass Utils::sPointClass = NULL;
jclass Utils::sColorClass = NULL;
jclass Utils::sSWTExceptionClass = NULL;
jclass Utils::sOutOfMemoryErrorClass = NULL;
jmethodID Utils::sPointConstructor = NULL;
jmethodID Utils::sColorConstructor = NULL;
jmethodID Utils::sSWTExceptionConstructor = NULL;
jmethodID Utils::sOutOfMemoryErrorConstructor = NULL;


/*static*/ bool
Utils::init(JNIEnv* env)
{
	#define GET_CLASS(variable, name)					\
	{													\
		jclass clazz = env->FindClass(name);			\
		if (clazz == NULL)								\
			return false;								\
		variable = (jclass)env->NewGlobalRef(clazz);	\
		if (variable == NULL)							\
			return false;								\
	}
	#define GET_CONSTRUCTOR(variable, clazz, signature)				\
		variable = env->GetMethodID(clazz, "<init>", signature);	\
		if (variable == NULL)										\
			return false;
	#define GET_CLASS_AND_CONSTRUCTOR(classVariable, ctorVariable, className, \
			ctorSignature)												\
		GET_CLASS(classVariable, className)								\
		GET_CONSTRUCTOR(ctorVariable, classVariable, ctorSignature)
	

	GET_CLASS_AND_CONSTRUCTOR(sPointClass, sPointConstructor,
		"org/eclipse/swt/graphics/Point", "(II)V")
	GET_CLASS_AND_CONSTRUCTOR(sColorClass, sColorConstructor,
		"org/eclipse/swt/internal/qt/QtColor", "(BBB)V")
	GET_CLASS_AND_CONSTRUCTOR(sSWTExceptionClass, sSWTExceptionConstructor,
		"org/eclipse/swt/SWTException", "(I)V")
	GET_CLASS_AND_CONSTRUCTOR(sOutOfMemoryErrorClass,
		sOutOfMemoryErrorConstructor, "java/lang/OutOfMemoryError", "()V")

	#undef GET_CLASS
	#undef GET_CONSTRUCTOR
	#undef GET_CLASS_AND_CONSTRUCTOR

	return true;
}


/*static*/ void
Utils::cleanup(JNIEnv* env)
{
	if (sPointClass != NULL) {
		env->DeleteGlobalRef(sPointClass);
		sPointClass = NULL;
	}
	sPointConstructor = NULL;
}


/*static*/ QString
Utils::fromJavaString(JNIEnv* env, jstring javaString)
{
	const jchar* utf16 = env->GetStringChars(javaString, NULL);
	jsize utf16Count = env->GetStringLength(javaString);
	if (utf16 == NULL || utf16Count == 0)
		return QString();

	return QString::fromUtf16((const ushort*)utf16, utf16Count);
}


/*static*/ jstring
Utils::toJavaString(JNIEnv* env, const QString& string)
{
	return env->NewString((const jchar*)string.utf16(), string.length());
}


/*static*/ void
Utils::throwSWTException(JNIEnv* env, jint code)
{
	jobject exception = env->NewObject(sSWTExceptionClass, sSWTExceptionConstructor, code);
	if (exception != NULL)
		env->Throw(static_cast<jthrowable>(exception));
}


/*static*/ void
Utils::throwOutOfMemoryError(JNIEnv* env)
{
	jobject exception = env->NewObject(sOutOfMemoryErrorClass, sOutOfMemoryErrorConstructor);
	if (exception != NULL)
		env->Throw(static_cast<jthrowable>(exception));
}


}	// namespace qt
}	// namespace swt

