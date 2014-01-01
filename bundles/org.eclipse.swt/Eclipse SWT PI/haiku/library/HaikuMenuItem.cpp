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


#include "HaikuMenuItem.h"

#include <SeparatorItem.h>

#include <private/shared/AutoLocker.h>

#include "HaikuJNIContext.h"
#include "HaikuUtils.h"


using namespace swt::haiku;


namespace swt {
namespace haiku {


// pragma mark - HaikuMenuItem


HaikuMenuItem::HaikuMenuItem()
	:
	HaikuWidget()
{
}


HaikuMenuItem::~HaikuMenuItem()
{
}


bool
HaikuMenuItem::Lock()
{
	if (BMenu* menu = GetBMenuItem()->Menu())
		return menu->LockLooper();
	return false;
}


void
HaikuMenuItem::Unlock()
{
	if (BMenu* menu = GetBMenuItem()->Menu())
		menu->UnlockLooper();
}


// pragma mark - HaikuViewMenuItem


template<typename ViewClass>
class HaikuViewMenuItem : public ViewClass, public HaikuMenuItem {
public:
	template<typename... Arguments>
	HaikuViewMenuItem(const Arguments... arguments)
		:
		ViewClass(arguments...),
		HaikuMenuItem()
	{
	}

	virtual BMenuItem* GetBMenuItem()
	{
		return this;
	}
};


// pragma mark - HaikuCheckMenuItem


class HaikuCheckMenuItem : public HaikuViewMenuItem<BMenuItem> {
public:
	HaikuCheckMenuItem()
		:
		SuperClass("", (BMessage*)NULL)
	{
	}

private:
	typedef HaikuViewMenuItem<BMenuItem> SuperClass;
};


// pragma mark - HaikuPushMenuItem


class HaikuPushMenuItem : public HaikuViewMenuItem<BMenuItem> {
public:
	HaikuPushMenuItem()
		:
		SuperClass("", (BMessage*)NULL)
	{
	}

private:
	typedef HaikuViewMenuItem<BMenuItem> SuperClass;
};


// pragma mark - HaikuRadioMenuItem


class HaikuRadioMenuItem : public HaikuViewMenuItem<BMenuItem> {
public:
	HaikuRadioMenuItem()
		:
		SuperClass("", (BMessage*)NULL)
	{
	}

private:
	typedef HaikuViewMenuItem<BMenuItem> SuperClass;
};


// pragma mark - HaikuSeparatorMenuItem


typedef HaikuViewMenuItem<BSeparatorItem> HaikuSeparatorMenuItem;


}	// namespace haiku
}	// namespace swt


// #pragma mark - native methods


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuMenuItem_createCheckItem(
	JNIEnv* env, jclass clazz, jintLong displayHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuCheckMenuItem* menuItem = new(std::nothrow) HaikuCheckMenuItem;
	if (menuItem == NULL)
		return 0;
	return menuItem->Handle();
}


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuMenuItem_createPushItem(
	JNIEnv* env, jclass clazz, jintLong displayHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuPushMenuItem* menuItem = new(std::nothrow) HaikuPushMenuItem;
	if (menuItem == NULL)
		return 0;
	return menuItem->Handle();
}


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuMenuItem_createRadioItem(
	JNIEnv* env, jclass clazz, jintLong displayHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuRadioMenuItem* menuItem = new(std::nothrow) HaikuRadioMenuItem;
	if (menuItem == NULL)
		return 0;
	return menuItem->Handle();
}


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuMenuItem_createSeparatorItem(
	JNIEnv* env, jclass clazz, jintLong displayHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuSeparatorMenuItem* menuItem = new(std::nothrow) HaikuSeparatorMenuItem;
	if (menuItem == NULL)
		return 0;
	return menuItem->Handle();
}


extern "C" jboolean
Java_org_eclipse_swt_internal_haiku_HaikuMenuItem_isSelected(
	JNIEnv* env, jclass clazz, jintLong handle)
{
	HAIKU_JNI_ENTER(env);

	HaikuMenuItem* menuItem = HaikuMenuItem::Get(handle);
	AutoLocker<HaikuMenuItem> menuItemLocker(menuItem);
	return menuItem->GetBMenuItem()->IsMarked();
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuMenuItem_setAccelerator(
	JNIEnv* env, jclass clazz, jintLong handle, jint accelerator)
{
	HAIKU_JNI_ENTER(env);

	HaikuMenuItem* menuItem = HaikuMenuItem::Get(handle);
	AutoLocker<HaikuMenuItem> menuItemLocker(menuItem);
// TODO:...
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuMenuItem_setEnabled(
	JNIEnv* env, jclass clazz, jintLong handle, jboolean enabled)
{
	HAIKU_JNI_ENTER(env);

	HaikuMenuItem* menuItem = HaikuMenuItem::Get(handle);
	AutoLocker<HaikuMenuItem> menuItemLocker(menuItem);
	menuItem->GetBMenuItem()->SetEnabled(enabled);
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuMenuItem_setImage(
	JNIEnv* env, jclass clazz, jintLong handle, jintLong imageHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuMenuItem* menuItem = HaikuMenuItem::Get(handle);
	AutoLocker<HaikuMenuItem> menuItemLocker(menuItem);
// TODO:...
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuMenuItem_setSelected(
	JNIEnv* env, jclass clazz, jintLong handle, jboolean selected)
{
	HAIKU_JNI_ENTER(env);

	HaikuMenuItem* menuItem = HaikuMenuItem::Get(handle);
	AutoLocker<HaikuMenuItem> menuItemLocker(menuItem);
	menuItem->GetBMenuItem()->SetMarked(selected);
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuMenuItem_setSubMenu(
	JNIEnv* env, jclass clazz, jintLong handle, jintLong subMenuHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuMenuItem* menuItem = HaikuMenuItem::Get(handle);
	AutoLocker<HaikuMenuItem> menuItemLocker(menuItem);
// TODO:...
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuMenuItem_setText(
	JNIEnv* env, jclass clazz, jintLong handle, jstring text)
{
	HAIKU_JNI_ENTER(env);

	HaikuMenuItem* menuItem = HaikuMenuItem::Get(handle);
	AutoLocker<HaikuMenuItem> menuItemLocker(menuItem);
	menuItem->GetBMenuItem()->SetLabel(HaikuUtils::FromJavaString(env, text));
}
