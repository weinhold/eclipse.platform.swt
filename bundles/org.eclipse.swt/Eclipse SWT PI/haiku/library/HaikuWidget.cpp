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


#include "HaikuWidget.h"

#include "HaikuJNIContext.h"


using namespace swt::haiku;


namespace swt {
namespace haiku {


HaikuWidget::HaikuWidget()
{
}


HaikuWidget::~HaikuWidget()
{
}


void
HaikuWidget::Delete()
{
	delete this;
}


}	// namespace haiku
}	// namespace swt


// #pragma mark - native methods


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuWidget_delete(
	JNIEnv* env, jobject object, jintLong handle)
{
	HAIKU_JNI_ENTER(env);

	HaikuWidget* widget = HaikuWidget::Get(handle);
	widget->Delete();
}

