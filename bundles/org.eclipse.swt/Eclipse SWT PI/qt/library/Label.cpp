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


#include <QLabel>

#include "Display.h"
#include "JNIContext.h"
#include "Utils.h"
#include "SwtQWidgetComposite.h"


using namespace swt::qt;


typedef SwtQWidgetComposite<QLabel> Label;


// native methods


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtLabel_create(
	JNIEnv* env, jobject object, jintLong displayHandle)
{
	SWT_QT_JNI_ENTER(env)

	Label* label = Widget::create<Label>();
	return label->getHandle();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtLabel_setAlignment(
	JNIEnv* env, jobject object, jintLong handle, jint alignment)
{
	SWT_QT_JNI_ENTER(env)

	Label* label = Label::get(handle);
	label->setAlignment((Qt::Alignment)alignment);

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtLabel_setText(
	JNIEnv* env, jobject object, jintLong handle, jstring text)
{
	SWT_QT_JNI_ENTER(env)

	Label* label = Label::get(handle);
	label->setText(Utils::fromJavaString(env, text));

	SWT_QT_JNI_EXIT(env,)
}
