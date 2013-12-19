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

#include "HaikuApplication.h"

#include <stdio.h>

#include <new>

#include "swt.h"

#include "HaikuJNIContext.h"
#include "HaikuUtils.h"


using namespace swt::haiku;


namespace swt {
namespace haiku {


HaikuApplication::HaikuApplication()
	:
	BApplication("application/x-org.eclipse.swt-application")
{
}


HaikuApplication::~HaikuApplication()
{
}


}	// namespace haiku
}	// namespace swt


static HaikuApplication* sApplication = NULL;


// #pragma mark - native methods


extern "C" jlong
Java_org_eclipse_swt_internal_haiku_HaikuApplication_create(JNIEnv* env,
	jobject object)
{
	HAIKU_JNI_ENTER(env);

	sApplication = new(std::nothrow) HaikuApplication;
	if (sApplication == NULL)
		return 0;

	// unlock, so we can run the message loop in another thread
	sApplication->Unlock();
	return (jlong)(addr_t)sApplication;
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuApplication_delete(JNIEnv* env,
	jobject object)
{
	HAIKU_JNI_ENTER(env);

	if (sApplication->Lock()) {
		thread_id thread = sApplication->Thread();
		sApplication->Quit();
		sApplication->Unlock();
		sApplication = NULL;

		while (wait_for_thread(thread, NULL) == B_INTERRUPTED) {
		}
	}
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuApplication_run(JNIEnv* env,
	jobject object)
{
	HAIKU_JNI_ENTER(env);

	sApplication->Lock();
	sApplication->Run();
}
