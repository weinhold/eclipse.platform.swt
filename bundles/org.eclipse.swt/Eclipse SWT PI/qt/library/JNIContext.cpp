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


#include "JNIContext.h"


namespace swt {
namespace qt {


JavaVM* JNIContext::sJavaVM;


JNIContext::JNIContext(TRACE_QT_JNI_ONLY(const char* functionName))
#ifdef TRACE_QT_JNI
	: fFunctionName(functionName)
#endif
{
#ifdef TRACE_QT_JNI
	fprintf(stderr, "XXX Qt: %s\n", fFunctionName);
#endif
}


JNIContext::~JNIContext()
{
#ifdef TRACE_QT_JNI
	fprintf(stderr, "XXX Qt: %s done\n", fFunctionName);
#endif
}


/*static*/ bool
JNIContext::init(JNIEnv* env)
{
	return env->GetJavaVM(&sJavaVM) == 0 && getEnv() != NULL;
}


/*static*/ JNIEnv*
JNIContext::getEnv()
{
	void* env;
	return sJavaVM->GetEnv(&env, JNI_VERSION_1_6) == 0 ? (JNIEnv*)env : NULL;
}


}	// namespace qt
}	// namespace swt
