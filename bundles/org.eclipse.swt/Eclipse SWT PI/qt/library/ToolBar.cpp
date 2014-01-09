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


#include <QAction>
#include <QList>
#include <QToolBar>

#include "JNIContext.h"
#include "ToolItem.h"
#include "Utils.h"
#include "SwtQWidgetComposite.h"


using namespace swt::qt;


namespace swt {
namespace qt {


class ToolBar : public SwtQWidgetComposite<QToolBar> {
public:
	ToolBar(bool horizontal)
		:
		SwtQWidgetComposite<QToolBar>(),
		fItems()
	{
		if (!horizontal)
			setOrientation(Qt::Vertical);
	}

	static ToolBar* get(jintLong handle)
	{
		return getAs<ToolBar>(handle);
	}

	const QList<ToolItem*>& getItems() const
	{
		return fItems;
	}

	void addItem(ToolItem* item, int index)
	{
		QWidget* widget = item->swtGetQWidget();
		QAction* nextAction = index < fItems.count() ? fItems.at(index)->getAction() : NULL;

		QAction* action;
		if (widget != NULL)
			action = insertWidget(nextAction, widget);
		else
			action = insertSeparator(nextAction);

		try {
			fItems.insert(index, item);
		} catch (...) {
			removeAction(action);
			throw;
		}

		item->setAction(action);
	}

	int removeItem(ToolItem* item)
	{
		int index = fItems.indexOf(item);
		if (index < 0)
			return -1;

		if (QAction* action = item->getAction()) {
			removeAction(action);
			delete action;
		}

		fItems.removeAt(index);

		return index;
	}

private:
	QList<ToolItem*>	fItems;
};


}	// namespace qt
}	// namespace swt


// native methods


extern "C" void
Java_org_eclipse_swt_internal_qt_QtToolBar_addItem(
	JNIEnv* env, jobject object, jintLong handle, jintLong itemHandle,
	jint index)
{
	SWT_QT_JNI_ENTER(env)

	ToolBar* toolBar = ToolBar::get(handle);
	toolBar->addItem(ToolItem::get(itemHandle), index);

	SWT_QT_JNI_EXIT(env,)
}


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtToolBar_create(
	JNIEnv* env, jobject object, jintLong displayHandle, jboolean horizontal)
{
	SWT_QT_JNI_ENTER(env)

	ToolBar* toolBar = Widget::create<ToolBar>(horizontal);
	return toolBar->getHandle();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" jint
Java_org_eclipse_swt_internal_qt_QtToolBar_getItemCount(
	JNIEnv* env, jobject object, jintLong handle)
{
	SWT_QT_JNI_ENTER(env)

	ToolBar* toolBar = ToolBar::get(handle);
	return toolBar->getItems().count();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" jintLongArray
Java_org_eclipse_swt_internal_qt_QtToolBar_getItems(
	JNIEnv* env, jobject object, jintLong handle)
{
	SWT_QT_JNI_ENTER(env)

	ToolBar* toolBar = ToolBar::get(handle);
	return Utils::createHandleArray(env, toolBar->getItems());

	SWT_QT_JNI_EXIT(env, NULL)
}


extern "C" jint
Java_org_eclipse_swt_internal_qt_QtToolBar_removeItem(
	JNIEnv* env, jobject object, jintLong handle, jintLong itemHandle)
{
	SWT_QT_JNI_ENTER(env)

	ToolBar* toolBar = ToolBar::get(handle);
	return toolBar->removeItem(ToolItem::get(itemHandle));

	SWT_QT_JNI_EXIT(env, 0)
}
