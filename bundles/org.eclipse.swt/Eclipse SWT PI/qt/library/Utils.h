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

#ifndef INC_UTILS_H
#define INC_UTILS_H


#include <QColor>
#include <QPoint>
#include <QRect>
#include <QSize>
#include <QString>
#include <QThread>

#include "swt.h"


namespace swt {
namespace qt {


class Utils : private QThread {
public:
	static	bool				init(JNIEnv* env);
	static	void				cleanup(JNIEnv* env);

	static	QString				fromJavaString(JNIEnv* env, jstring javaString);
	static	jstring				toJavaString(JNIEnv* env, const QString& string);

	static	jobject				createPoint(JNIEnv* env, jint x, jint y);
	static	jobject				createPoint(JNIEnv* env, const QPoint& point);
	static	jobject				createPoint(JNIEnv* env, const QSize& size);

	static	jobject				createColor(JNIEnv* env, jbyte red, jbyte green, jbyte blue);
	static	jobject				createColor(JNIEnv* env, const QColor& color);

	static	void				throwSWTException(JNIEnv* env, jint code);
	static	void				throwOutOfMemoryError(JNIEnv* env);

	template<typename ListType>
	static	jintLongArray		createHandleArray(JNIEnv* env,
									const ListType& objects);

    static	void				usleep(unsigned long usecs)
    								{ QThread::usleep(usecs); }
    static	void				msleep(unsigned long usecs)
    								{ QThread::msleep(usecs); }
    static	void				sleep(unsigned long usecs)
    								{ QThread::sleep(usecs); }

//private:
	static	jclass				sPointClass;
	static	jclass				sColorClass;
	static	jclass				sSWTExceptionClass;
	static	jclass				sOutOfMemoryErrorClass;
	static	jmethodID			sPointConstructor;
	static	jmethodID			sColorConstructor;
	static	jmethodID			sSWTExceptionConstructor;
	static	jmethodID			sOutOfMemoryErrorConstructor;
};


/*static*/ inline jobject
Utils::createPoint(JNIEnv* env, jint x, jint y)
{
	return env->NewObject(sPointClass, sPointConstructor, x, y);
}


/*static*/ inline jobject
Utils::createPoint(JNIEnv* env, const QPoint& point)
{
	return createPoint(env, (jint)point.x(), (jint)point.y());
}


/*static*/ inline jobject
Utils::createPoint(JNIEnv* env, const QSize& size)
{
	return createPoint(env, (jint)size.width(), (jint)size.height());
}


/*static*/ inline jobject
Utils::createColor(JNIEnv* env, jbyte red, jbyte green, jbyte blue)
{
	return env->NewObject(sColorClass, sColorConstructor, red, green, blue);
}


/*static*/ inline jobject
Utils::createColor(JNIEnv* env, const QColor& color)
{
	return createColor(env, color.red(), color.green(), color.blue());
}


template<typename ListType>
/*static*/ jintLongArray
Utils::createHandleArray(JNIEnv* env, const ListType& objects)
{
	int count = objects.count();
	if (count == 0)
		return 0;

	jintLongArray handleArray = env->NewIntLongArray(count);
	if (handleArray == NULL)
		return 0;

	jintLong* handles = (jintLong*)env->GetPrimitiveArrayCritical(handleArray, NULL);
	if (handles == NULL)
		return 0;

	for (int i = 0; i < count; i++)
		handles[i] = objects.at(i)->getHandle();

	env->ReleasePrimitiveArrayCritical(handleArray, handles, JNI_COMMIT);
	return handleArray;
}


}	// namespace qt
}	// namespace swt


#endif /* INC_UTILS_H */
