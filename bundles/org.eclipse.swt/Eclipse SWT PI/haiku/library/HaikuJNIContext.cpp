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


#include "HaikuJNIContext.h"

#include <TLS.h>


namespace swt {
namespace haiku {


int32 HaikuJNIContext::fTLSSlot = -1;


HaikuJNIContext::HaikuJNIContext(JNIEnv* env
	TRACE_HAIKU_JNI_ONLY(,const char* functionName))
	:
	fEnv(env)
#ifdef TRACE_HAIKU_JNI
	, fFunctionName(functionName)
#endif
{
#ifdef TRACE_HAIKU_JNI
	fprintf(stderr, "XXX Haiku: %s\n", fFunctionName);
#endif

	HaikuJNIContext** tls = (HaikuJNIContext**)tls_address(fTLSSlot);
	fPrevious = *tls;
	*tls = this;
}


HaikuJNIContext::~HaikuJNIContext()
{
	tls_set(fTLSSlot, fPrevious);

#ifdef TRACE_HAIKU_JNI
	fprintf(stderr, "XXX Haiku: %s done\n", fFunctionName);
#endif
}


/*static*/ bool
HaikuJNIContext::Init()
{
	if (fTLSSlot < 0)
		fTLSSlot = tls_allocate();
	return fTLSSlot >= 0;
}


/*static*/ HaikuJNIContext*
HaikuJNIContext::Current()
{
	return (HaikuJNIContext*)tls_get(fTLSSlot);
}


}	// namespace haiku
}	// namespace swt
