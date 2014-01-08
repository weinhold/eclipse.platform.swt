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


#include "Shell.h"

#include "Display.h"
#include "JNIContext.h"


using namespace swt::qt;


namespace swt {
namespace qt {


Shell::Shell()
	:
	SwtQWidgetComposite<QMainWindow>(),
	fCentralWidget(new CentralWidget()),
	fDeleted(false)
{
	setCentralWidget(fCentralWidget);
}


Shell::~Shell()
{
}


void
Shell::closeEvent(QCloseEvent* event)
{
	if (fDeleted || Display::current()->callbackShellCloseRequested(this))
		event->accept();
	else
		event->ignore();
}


void
Shell::swtDelete()
{
	fDeleted = true;
	delete this;
}


QWidget*
Shell::swtGetQWidget()
{
	return fCentralWidget;
}


void
Shell::swtSetStyle(jint style)
{
	fCentralWidget->swtSetStyle(style);
}


void
Shell::swtAddChild(Control* child)
{
	fCentralWidget->swtAddChild(child);
}


void
Shell::swtRemoveChild(Control* child)
{
	fCentralWidget->swtRemoveChild(child);
}


QList<Control*>
Shell::swtGetChildren()
{
	return fCentralWidget->swtGetChildren();
}


}	// namespace qt
}	// namespace swt


// native methods


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtShell_create(
	JNIEnv* env, jclass clazz, jintLong displayHandle)
{
	SWT_QT_JNI_ENTER(env)

	Shell* shell = new Shell;
	return shell->getHandle();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtShell_getCentralWidget(JNIEnv* env,
	jclass clazz, jintLong handle)
{
	SWT_QT_JNI_ENTER(env)

	Shell* shell = Shell::get(handle);
	return shell->swtGetCentralWidget()->getHandle();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtShell_setVisible(
	JNIEnv* env, jclass clazz, jintLong handle, jboolean visible)
{
	SWT_QT_JNI_ENTER(env)

	Shell* shell = Shell::get(handle);
	shell->setVisible(visible);

	SWT_QT_JNI_EXIT(env,)
}
