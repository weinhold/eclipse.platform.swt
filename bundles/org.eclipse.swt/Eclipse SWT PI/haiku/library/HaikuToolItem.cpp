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


#include "HaikuToolItem.h"

#include "swt.h"

#include <Control.h>
#include <SeparatorView.h>

#include <private/shared/AutoLocker.h>

#include "HaikuButton.h"
#include "HaikuDisplay.h"
#include "HaikuImage.h"
#include "HaikuJNIContext.h"
#include "HaikuUtils.h"
#include "HaikuViewComposite.h"


using namespace swt::haiku;


namespace swt {
namespace haiku {


// #pragma mark - HaikuToolItem


HaikuToolItem::HaikuToolItem()
{
}


HaikuToolItem::~HaikuToolItem()
{
}


bool
HaikuToolItem::Lock()
{
	return ToolItemGetControl()->Lock();
}


void
HaikuToolItem::Unlock()
{
	ToolItemGetControl()->Unlock();
}


// #pragma mark - HaikuAbstractToolItem


class HaikuAbstractToolItem : public HaikuToolItem {
public:
	HaikuAbstractToolItem()
		:
		HaikuToolItem()
	{
	}

	virtual BView* ToolItemGetView()
	{
		return ToolItemGetControl()->ControlGetView();
	}

	virtual void ToolItemSetToolTipText(const char* text)
	{
		ToolItemGetView()->SetToolTip(text);
	}

	virtual bool IsValid() const = 0;
};


// #pragma mark - HaikuButtonToolItem


class HaikuButtonToolItem : public HaikuAbstractToolItem {
public:
	HaikuButtonToolItem(HaikuButton* button)
		:
		HaikuAbstractToolItem(),
		fButton(button)
	{
		fButton->SetFlat(true);
	}

	~HaikuButtonToolItem()
	{
		if (fButton != NULL && fButton->ControlGetView()->Parent() == NULL)
			delete fButton;
	}

	virtual HaikuControl* ToolItemGetControl()
	{
		return fButton;
	}

	virtual void ToolItemSetControl(HaikuControl* control)
	{
		// not allowed
	}

	virtual bool ToolItemIsSelected()
	{
		return fButton->GetBControl()->Value() != B_CONTROL_OFF;
	}

	virtual void ToolItemSetSelected(bool selected)
	{
		fButton->GetBControl()->SetValue(B_CONTROL_ON);
	}

	virtual void ToolItemSetText(const char* text)
	{
		fButton->GetBControl()->SetLabel(text);
	}

	virtual void ToolItemSetImage(HaikuImage* image)
	{
		fButton->SetImage(image);
	}

	virtual void ToolItemSetHotImage(HaikuImage* image)
	{
		// TODO:...
	}

	virtual void ToolItemSetDisabledImage(HaikuImage* image)
	{
		// TODO:...
	}

	virtual bool IsValid() const
	{
		return fButton != NULL;
	}

private:
	HaikuButton*	fButton;
};


// #pragma mark - HaikuCheckToolItem


class HaikuCheckToolItem : public HaikuButtonToolItem {
public:
	HaikuCheckToolItem()
		:
		HaikuButtonToolItem(HaikuButtonFactory::CreateToggleButton())
	{
	}
};


// #pragma mark - HaikuDropDownToolItem


class HaikuDropDownToolItem : public HaikuButtonToolItem {
public:
	HaikuDropDownToolItem()
		:
		HaikuButtonToolItem(HaikuButtonFactory::CreatePushButton())
	{
	}
};


// #pragma mark - HaikuPushToolItem


class HaikuPushToolItem : public HaikuButtonToolItem {
public:
	HaikuPushToolItem()
		:
		HaikuButtonToolItem(HaikuButtonFactory::CreatePushButton())
	{
	}
};


// #pragma mark - HaikuRadioToolItem


class HaikuRadioToolItem : public HaikuButtonToolItem {
public:
	HaikuRadioToolItem()
		:
		HaikuButtonToolItem(HaikuButtonFactory::CreateToggleButton())
	{
	}
};


// #pragma mark - HaikuSeparatorToolItem


class HaikuSeparatorToolItem : public HaikuAbstractToolItem {
public:
	HaikuSeparatorToolItem(bool horizontal)
		:
		HaikuAbstractToolItem(),
		fSeparator(new(std::nothrow) SeparatorControl(
			horizontal ? B_VERTICAL : B_HORIZONTAL)),
			// Note: horizontal specifies whether the tool bar is horizontal,
			// i.e. the separator needs to be orthogonal.
		fControl(fSeparator)
	{
	}

	~HaikuSeparatorToolItem()
	{
		if (fControl != NULL && fControl->ControlGetView()->Parent() == NULL)
			delete fControl;
		if (fControl != fSeparator)
			delete fSeparator;
	}

	virtual HaikuControl* ToolItemGetControl()
	{
		return fControl;
	}

	virtual void ToolItemSetControl(HaikuControl* control)
	{
		fControl = control != NULL ? control : fSeparator;
	}

	virtual bool ToolItemIsSelected()
	{
		// not allowed
		return false;
	}

	virtual void ToolItemSetSelected(bool selected)
	{
		// not allowed
	}

	virtual void ToolItemSetText(const char* text)
	{
		// not allowed
	}

	virtual void ToolItemSetImage(HaikuImage* image)
	{
		// not allowed
	}

	virtual void ToolItemSetHotImage(HaikuImage* image)
	{
		// not allowed
	}

	virtual void ToolItemSetDisabledImage(HaikuImage* image)
	{
		// not allowed
	}

	virtual bool IsValid() const
	{
		return fSeparator != NULL;
	}

private:
	typedef HaikuViewControl<BSeparatorView> SeparatorControl;

private:
	SeparatorControl*	fSeparator;
	HaikuControl*		fControl;
};


}	// namespace haiku
}	// namespace swt


// #pragma mark - helper functions


template<typename ItemType, typename... Arguments>
static inline jintLong create_item(const Arguments... arguments)
{
	ItemType* item = new(std::nothrow) ItemType(arguments...);
	if (item == NULL || !item->IsValid()) {
		delete item;
		return 0;
	}
	return item->Handle();
}


// #pragma mark - native methods


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuToolItem_createCheckItem(
	JNIEnv* env, jobject object, jintLong displayHandle)
{
	HAIKU_JNI_ENTER(env);

	return create_item<HaikuCheckToolItem>();
}


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuToolItem_createDropDownItem(
	JNIEnv* env, jobject object, jintLong displayHandle)
{
	HAIKU_JNI_ENTER(env);

	return create_item<HaikuDropDownToolItem>();
}


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuToolItem_createPushItem(
	JNIEnv* env, jobject object, jintLong displayHandle)
{
	HAIKU_JNI_ENTER(env);

	return create_item<HaikuPushToolItem>();
}


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuToolItem_createRadioItem(
	JNIEnv* env, jobject object, jintLong displayHandle)
{
	HAIKU_JNI_ENTER(env);

	return create_item<HaikuRadioToolItem>();
}


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuToolItem_createSeparatorItem(
	JNIEnv* env, jobject object, jintLong displayHandle, jboolean horizontal)
{
	HAIKU_JNI_ENTER(env);

	return create_item<HaikuSeparatorToolItem>(horizontal);
}


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuToolItem_getControl(
	JNIEnv* env, jobject object, jintLong handle)
{
	HAIKU_JNI_ENTER(env);

	HaikuToolItem* item = HaikuToolItem::Get(handle);
	AutoLocker<HaikuToolItem> itemLocker(item);
	return item->ToolItemGetControl()->Handle();
}


extern "C" jboolean
Java_org_eclipse_swt_internal_haiku_HaikuToolItem_isSelected(
	JNIEnv* env, jobject object, jintLong handle)
{
	HAIKU_JNI_ENTER(env);

	HaikuToolItem* item = HaikuToolItem::Get(handle);
	AutoLocker<HaikuToolItem> itemLocker(item);
	return item->ToolItemIsSelected();
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuToolItem_setControl(
	JNIEnv* env, jobject object, jintLong handle, jintLong controlHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuToolItem* item = HaikuToolItem::Get(handle);
	AutoLocker<HaikuToolItem> itemLocker(item);
	item->ToolItemSetControl(
		controlHandle != 0 ? HaikuControl::Get(controlHandle) : NULL);
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuToolItem_setDisabledImage(
	JNIEnv* env, jobject object, jintLong handle, jintLong imageHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuToolItem* item = HaikuToolItem::Get(handle);
	AutoLocker<HaikuToolItem> itemLocker(item);
	item->ToolItemSetDisabledImage(
		imageHandle != 0 ? HaikuImage::Get(imageHandle) : NULL);
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuToolItem_setHotImage(
	JNIEnv* env, jobject object, jintLong handle, jintLong imageHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuToolItem* item = HaikuToolItem::Get(handle);
	AutoLocker<HaikuToolItem> itemLocker(item);
	item->ToolItemSetHotImage(
		imageHandle != 0 ? HaikuImage::Get(imageHandle) : NULL);
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuToolItem_setImage(
	JNIEnv* env, jobject object, jintLong handle, jintLong imageHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuToolItem* item = HaikuToolItem::Get(handle);
	AutoLocker<HaikuToolItem> itemLocker(item);
	item->ToolItemSetImage(
		imageHandle != 0 ? HaikuImage::Get(imageHandle) : NULL);
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuToolItem_setSelected(
	JNIEnv* env, jobject object, jintLong handle, jboolean selected)
{
	HAIKU_JNI_ENTER(env);

	HaikuToolItem* item = HaikuToolItem::Get(handle);
	AutoLocker<HaikuToolItem> itemLocker(item);
	item->ToolItemSetSelected(selected);
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuToolItem_setText(
	JNIEnv* env, jobject object, jintLong handle, jstring text)
{
	HAIKU_JNI_ENTER(env);

	HaikuToolItem* item = HaikuToolItem::Get(handle);
	AutoLocker<HaikuToolItem> itemLocker(item);
	item->ToolItemSetText(HaikuUtils::FromJavaString(env, text));
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuToolItem_setToolTipText(
	JNIEnv* env, jobject object, jintLong handle, jstring text)
{
	HAIKU_JNI_ENTER(env);

	HaikuToolItem* item = HaikuToolItem::Get(handle);
	AutoLocker<HaikuToolItem> itemLocker(item);
	item->ToolItemSetToolTipText(HaikuUtils::FromJavaString(env, text));
}
