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

#ifndef INC_HAIKU_JNI_CONTEXT_H
#define INC_HAIKU_JNI_CONTEXT_H


#include <SupportDefs.h>

#include "swt.h"


// define to enable tracing for JNI function
#define TRACE_HAIKU_JNI

#ifdef TRACE_HAIKU_JNI
#	define TRACE_HAIKU_JNI_ONLY(...)	__VA_ARGS__
#else
#	define TRACE_HAIKU_JNI_ONLY(...)
#endif


namespace swt {
namespace haiku {


class HaikuDisplay;


class HaikuJNIContext {
public:
								HaikuJNIContext(JNIEnv* env
									TRACE_HAIKU_JNI_ONLY(
										, const char* functionName));
								~HaikuJNIContext();

	static	bool				Init();

	static	HaikuJNIContext*	Current();
	static	JNIEnv*				CurrentEnv()
									{ return Current()->Env(); }
	static	HaikuDisplay*		CurrentDisplay();

			JNIEnv*				Env() const
									{ return fEnv; }
			HaikuDisplay*		Display() const
									{ return fDisplay; }
			void				SetDisplay(HaikuDisplay* display)
									{ fDisplay = display; }

private:

private:
	static	int32				fTLSSlot;

			HaikuJNIContext*	fPrevious;
			JNIEnv*				fEnv;
			HaikuDisplay*		fDisplay;
#ifdef TRACE_HAIKU_JNI
			const char*			fFunctionName;
#endif
};


}	// namespace haiku
}	// namespace swt


#ifdef TRACE_HAIKU_JNI
#	define HAIKU_JNI_ENTER(env)	\
		HaikuJNIContext haikuJniContext(env, __PRETTY_FUNCTION__)
#else
#	define HAIKU_JNI_ENTER(env)	\
		HaikuJNIContext haikuJniContext(env)
#endif


#endif /* INC_HAIKU_JNI_CONTEXT_H */
