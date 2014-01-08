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

#ifndef INC_JNI_CONTEXT_H
#define INC_JNI_CONTEXT_H


#include "swt.h"

#include "Utils.h"


// define to enable tracing for JNI functions
#define TRACE_QT_JNI

#ifdef TRACE_QT_JNI
#	define TRACE_QT_JNI_ONLY(...)	__VA_ARGS__
#else
#	define TRACE_QT_JNI_ONLY(...)
#endif


namespace swt {
namespace qt {


class JNIContext {
public:
								JNIContext(TRACE_QT_JNI_ONLY(const char* functionName));
								~JNIContext();

	static	bool				init(JNIEnv* env);

	static	JNIEnv*				getEnv();

private:
	static	JavaVM*				sJavaVM;

#ifdef TRACE_QT_JNI
			const char*			fFunctionName;
#endif
};


}	// namespace qt
}	// namespace swt


#ifdef TRACE_QT_JNI
#	define SWT_QT_JNI_ENTER(env)						\
		JNIContext qtJniContext(__PRETTY_FUNCTION__);	\
		try {
#else
#	define SWT_QT_JNI_ENTER(env)	\
		JNIContext qtJniContext;	\
		try {
#endif

#define SWT_QT_JNI_EXIT(env, returnValue)	\
	} catch (std::bad_alloc&) {				\
		Utils::throwOutOfMemoryError(env);	\
		return returnValue;					\
	}


#endif /* INC_JNI_CONTEXT_H */
