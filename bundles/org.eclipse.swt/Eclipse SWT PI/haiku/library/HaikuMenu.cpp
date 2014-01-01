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


#include "HaikuMenu.h"

#include <MenuBar.h>
#include <MenuItem.h>
#include <PopUpMenu.h>

#include <private/shared/AutoLocker.h>

#include "HaikuMenuItem.h"
#include "HaikuUtils.h"
#include "HaikuViewControl.h"


using namespace swt::haiku;


namespace swt {
namespace haiku {


// pragma mark - HaikuMenu


HaikuMenu::HaikuMenu()
	:
	HaikuControl()
{
}


HaikuMenu::~HaikuMenu()
{
}


// pragma mark - HaikuViewMenu


template<typename ViewClass>
class HaikuViewMenu : public HaikuViewControl<ViewClass, HaikuMenu> {
public:
	template<typename... Arguments>
	HaikuViewMenu(const Arguments... arguments)
		:
		SuperClass(arguments...)
	{
	}

	virtual BMenu* GetBMenu()
	{
		return this;
	}

	virtual void MenuSetLocation(const BPoint& location)
	{
		// not allowed in most cases
	}

private:
	typedef HaikuViewControl<ViewClass, HaikuMenu> SuperClass;
};


// pragma mark - HaikuDropDownMenu


class HaikuDropDownMenu : public HaikuViewMenu<BMenu> {
public:
	HaikuDropDownMenu()
		:
		HaikuViewMenu<BMenu>((const char*)NULL)
	{
	}

	virtual void MenuSetVisible(bool visible)
	{
		// not allowed
	}
};


// pragma mark - HaikuMenuBar


class HaikuMenuBar : public HaikuViewMenu<BMenuBar> {
public:
	HaikuMenuBar()
		:
		HaikuViewMenu<BMenuBar>((const char*)NULL)
	{
	}

	virtual void MenuSetVisible(bool visible)
	{
		if (visible == IsHidden()) {
			if (visible)
				Show();
			else
				Hide();
		}
	}
};


// pragma mark - HaikuPopUpMenu


class HaikuPopUpMenu : public HaikuViewMenu<BPopUpMenu> {
public:
	HaikuPopUpMenu()
		:
		HaikuViewMenu<BPopUpMenu>((const char*)NULL, false, false),
		fLocation()
	{
	}

	virtual void MenuSetVisible(bool visible)
	{
		if (visible == IsHidden()) {
			if (visible) {
				BRect openRect(fLocation - BPoint(2, 2), BSize(4, 4));
				Go(fLocation, true, true, openRect, true);
			} else
				Hide();
		}
	}

	virtual void MenuSetLocation(const BPoint& location)
	{
		fLocation = location;
	}

private:
	BPoint	fLocation;
};


// pragma mark - ItemListAdapter


class ItemListAdapter {
public:
	ItemListAdapter(const BMenu* menu)
		:
		fMenu(menu)
	{
	}

	int32 CountItems() const
	{
		return fMenu->CountItems();
	}

	HaikuMenuItem* ItemAt(int32 index) const
	{
		return dynamic_cast<HaikuMenuItem*>(fMenu->ItemAt(index));
	}

private:
	const BMenu*	fMenu;
};


}	// namespace haiku
}	// namespace swt


// #pragma mark - native methods


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuMenu_addItem(
	JNIEnv* env, jobject object, jintLong handle, jintLong itemHandle,
	jint index)
{
	HAIKU_JNI_ENTER(env);

	HaikuMenu* menu = HaikuMenu::Get(handle);
	AutoLocker<HaikuMenu> menuLocker(menu);
	if (!menu->GetBMenu()->AddItem(
			HaikuMenuItem::Get(itemHandle)->GetBMenuItem(), index)) {
		HaikuUtils::ThrowOutOfMemoryError(env);
	}
}


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuMenu_createDropDownMenu(
	JNIEnv* env, jclass clazz, jintLong displayHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuDropDownMenu* menu = new(std::nothrow) HaikuDropDownMenu;
	if (menu == NULL)
		return 0;
	return menu->Handle();
}


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuMenu_createMenuBar(
	JNIEnv* env, jclass clazz, jintLong displayHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuMenuBar* menu = new(std::nothrow) HaikuMenuBar;
	if (menu == NULL)
		return 0;
	return menu->Handle();
}


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuMenu_createPopUpMenu(
	JNIEnv* env, jclass clazz, jintLong displayHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuPopUpMenu* menu = new(std::nothrow) HaikuPopUpMenu;
	if (menu == NULL)
		return 0;
	return menu->Handle();
}


extern "C" jint
Java_org_eclipse_swt_internal_haiku_HaikuMenu_getItemCount(
	JNIEnv* env, jobject object, jintLong handle)
{
	HAIKU_JNI_ENTER(env);

	HaikuMenu* menu = HaikuMenu::Get(handle);
	AutoLocker<HaikuMenu> menuLocker(menu);
	return menu->GetBMenu()->CountItems();
}


extern "C" jintLongArray
Java_org_eclipse_swt_internal_haiku_HaikuMenu_getItems(
	JNIEnv* env, jobject object, jintLong handle)
{
	HAIKU_JNI_ENTER(env);

	HaikuMenu* menu = HaikuMenu::Get(handle);
	AutoLocker<HaikuMenu> menuLocker(menu);
	return HaikuUtils::CreateHandleArray(env,
		ItemListAdapter(menu->GetBMenu()));
}


extern "C" jboolean
Java_org_eclipse_swt_internal_haiku_HaikuMenu_isVisible(
	JNIEnv* env, jclass clazz, jintLong handle)
{
	HAIKU_JNI_ENTER(env);

	HaikuMenu* menu = HaikuMenu::Get(handle);
	AutoLocker<HaikuMenu> menuLocker(menu);
	return !menu->GetBMenu()->IsHidden();
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuMenu_setLocation(
	JNIEnv* env, jclass clazz, jintLong handle, jint x, jint y)
{
	HAIKU_JNI_ENTER(env);

	HaikuMenu* menu = HaikuMenu::Get(handle);
	AutoLocker<HaikuMenu> menuLocker(menu);
	menu->MenuSetLocation(BPoint(x, y));
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuMenu_setVisible(
	JNIEnv* env, jclass clazz, jintLong handle, jboolean visible)
{
	HAIKU_JNI_ENTER(env);

	HaikuMenu* menu = HaikuMenu::Get(handle);
	AutoLocker<HaikuMenu> menuLocker(menu);
	menu->MenuSetVisible(visible);
}
