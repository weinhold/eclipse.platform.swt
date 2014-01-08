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

#include <new>

#include <QApplication>

#include "swt.h"

#include "JNIContext.h"


using namespace swt::qt;


namespace swt {
namespace qt {


class Application : public QApplication {
public:
	Application()
		:
		QApplication(fArgc, _initArgv())
	{
	}

private:
	char** _initArgv()
	{
		strcpy(fDummyArg0, "SWT");
		fArgv[0] = fDummyArg0;
		fArgv[1] = NULL;
		fArgc = 1;
		return fArgv;
	}

private:
	int		fArgc;
	char*	fArgv[2];
	char	fDummyArg0[8];
};


static Application* sApplication = NULL;


}	// namespace qt
}	// namespace swt


// native methods


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtApplication_create(JNIEnv* env,
	jclass clazz)
{
	SWT_QT_JNI_ENTER(env)

	sApplication = new Application;
	return (jintLong)(size_t)sApplication;

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtApplication_delete(JNIEnv* env,
	jclass clazz)
{
	SWT_QT_JNI_ENTER(env)

	delete sApplication;
	sApplication = NULL;

	SWT_QT_JNI_EXIT(env,)
}
