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


#include "swt.h"

#include <CardLayout.h>
#include <ControlLook.h>
#include <GroupLayout.h>

#include <private/shared/AutoLocker.h>

#include "HaikuDisplay.h"
#include "HaikuJNIContext.h"
#include "HaikuToolItem.h"
#include "HaikuUtils.h"
#include "HaikuViewComposite.h"


using namespace swt::haiku;


class HaikuToolBar : public HaikuViewComposite<BView> {
public:
	HaikuToolBar()
		:
		HaikuViewComposite<BView>((const char*)NULL, (uint32)0),
		fItems(),
		fCardLayout(NULL),
		fGroupLayout(NULL)
	{
		SetViewColor(ui_color(B_PANEL_BACKGROUND_COLOR));
	}

	bool Init(enum orientation orientation)
	{
		// To meat our layout requirements, we nest a group layout in a card
		// layout. The group layout lays out the tool bar items. The card layout
		// plus the explicit alignment (instead of using full size) of the group
		// layout make sure that the items won't be resized beyond their maximum
		// size.
		fCardLayout = new(std::nothrow) BCardLayout;
		if (fCardLayout == NULL)
			return false;
		SetLayout(fCardLayout);

		fGroupLayout = new(std::nothrow) BGroupLayout(orientation);
		if (fGroupLayout == NULL || !fCardLayout->AddItem(fGroupLayout)) {
			delete fGroupLayout;
			fGroupLayout = NULL;
			return false;
		}

		float inset = ceilf(be_control_look->DefaultItemSpacing() / 2);
		fGroupLayout->SetInsets(inset, 2, inset, 3);
		fGroupLayout->SetSpacing(inset);
		fGroupLayout->SetExplicitAlignment(
			BAlignment(B_ALIGN_LEFT, B_ALIGN_TOP));

		return true;
	}

	static HaikuToolBar* Get(jintLong handle)
	{
		return GetAs<HaikuToolBar>(handle);
	}

	const BObjectList<HaikuToolItem>& Items() const
	{
		return fItems;
	}

	bool AddItem(HaikuToolItem* item, int32 index)
	{
		if (!fItems.AddItem(item, index))
			return false;

		BView* view = item->ToolItemGetView();
		if (view->Parent() != NULL)
			view->RemoveSelf();

		if (!fGroupLayout->AddView(index, view)) {
			fItems.RemoveItemAt(index);
			return false;
		}

		return true;
	}

	int32 RemoveItem(HaikuToolItem* item)
	{
		int32 index = fItems.IndexOf(item);
		if (index < 0)
			return -1;

		BView* view = item->ToolItemGetView();
		if (view->Parent() == this)
			view->RemoveSelf();

		return index;
	}

private:
	BObjectList<HaikuToolItem>	fItems;
	BCardLayout*				fCardLayout;
	BGroupLayout*				fGroupLayout;
};


// #pragma mark - native methods


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuToolBar_addItem(
	JNIEnv* env, jobject object, jintLong handle, jintLong itemHandle,
	jint index)
{
	HAIKU_JNI_ENTER(env);

	HaikuToolBar* toolBar = HaikuToolBar::Get(handle);
	AutoLocker<HaikuToolBar> toolBarLocker(toolBar);
	if (!toolBar->AddItem(HaikuToolItem::Get(itemHandle), index))
		HaikuUtils::ThrowOutOfMemoryError(env);
}


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuToolBar_create(
	JNIEnv* env, jobject object, jintLong displayHandle, jboolean horizontal)
{
	HAIKU_JNI_ENTER(env);

	HaikuToolBar* toolBar = new(std::nothrow) HaikuToolBar;
	if (toolBar == NULL
		|| !toolBar->Init(horizontal ? B_HORIZONTAL : B_VERTICAL)) {
		delete toolBar;
		return 0;
	}
	return toolBar->Handle();
}


extern "C" jint
Java_org_eclipse_swt_internal_haiku_HaikuToolBar_getItemCount(
	JNIEnv* env, jobject object, jintLong handle)
{
	HAIKU_JNI_ENTER(env);

	HaikuToolBar* toolBar = HaikuToolBar::Get(handle);
	AutoLocker<HaikuToolBar> toolBarLocker(toolBar);
	return toolBar->Items().CountItems();
}


extern "C" jintLongArray
Java_org_eclipse_swt_internal_haiku_HaikuToolBar_getItems(
	JNIEnv* env, jobject object, jintLong handle)
{
	HAIKU_JNI_ENTER(env);

	HaikuToolBar* toolBar = HaikuToolBar::Get(handle);
	AutoLocker<HaikuToolBar> toolBarLocker(toolBar);
	return HaikuUtils::CreateHandleArray(env, toolBar->Items());
}


extern "C" jint
Java_org_eclipse_swt_internal_haiku_HaikuToolBar_removeItem(
	JNIEnv* env, jobject object, jintLong handle, jintLong itemHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuToolBar* toolBar = HaikuToolBar::Get(handle);
	AutoLocker<HaikuToolBar> toolBarLocker(toolBar);
	return toolBar->RemoveItem(HaikuToolItem::Get(itemHandle));
}
