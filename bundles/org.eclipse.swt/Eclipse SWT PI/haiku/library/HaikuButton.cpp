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


#include "HaikuButton.h"

#include <Button.h>
#include <CheckBox.h>
#include <ControlLook.h>
#include <RadioButton.h>
#include <ScrollBar.h>
#include <Window.h>

#include <private/shared/AutoLocker.h>

#include "HaikuImage.h"
#include "HaikuUtils.h"
#include "HaikuViewControl.h"


using namespace swt::haiku;


namespace swt {
namespace haiku {


// pragma mark - HaikuButton


HaikuButton::HaikuButton()
{
}


HaikuButton::~HaikuButton()
{
}


// pragma mark - HaikuViewButton


template<typename ViewClass>
class HaikuViewButton : public HaikuViewControl<ViewClass, HaikuButton> {
public:
	template<typename... Arguments>
	HaikuViewButton(const Arguments... arguments)
		:
		SuperClass(arguments...)
	{
	}

	virtual status_t Invoke(BMessage* message = NULL)
	{
		status_t error = ViewClass::Invoke(message);
		ButtonInvokedCallback();
		return error;
	}

	virtual BControl* GetBControl()
	{
		return this;
	}

	virtual void SetGrayed(bool grayed)
	{
		// not supported for most button types
	}

	virtual void SetImage(HaikuImage* image)
	{
		this->SetIcon(image != NULL ? image->Bitmap() : NULL);
	}

protected:
	void ButtonInvokedCallback()
	{
		HaikuJNIContext::CurrentDisplay()->CallbackButtonInvoked(this,
			this->Value() == B_CONTROL_ON);
	}

private:
	typedef HaikuViewControl<ViewClass, HaikuButton> SuperClass;
};


// pragma mark - HaikuArrowButton


class HaikuArrowButton : public HaikuViewButton<BButton> {
public:
	HaikuArrowButton()
		:
		HaikuViewButton<BButton>(""),
		fDirection(B_UP_ARROW)
	{
	}

	virtual void Draw(BRect updateRect)
	{
		BRect rect(Bounds());
		rgb_color background = LowColor();
		rgb_color base = background;
		uint32 flags = be_control_look->Flags(this);
		if (IsDefault())
			flags |= BControlLook::B_DEFAULT_BUTTON;
		be_control_look->DrawButtonFrame(this, rect, updateRect, base,
			background, flags);
		be_control_look->DrawButtonBackground(this, rect, updateRect, base,
			flags);

		rect.InsetBy(kFrameMargin - 1, kFrameMargin - 1);
			// Bug in BControlLook::DrawArrowShape(): Without the -1 the arrow
			// shape doesn't look right (BScrollBar does the same).
		be_control_look->DrawArrowShape(this, rect, updateRect,
			base, fDirection, flags);
	}

	virtual void GetPreferredSize(float* _width, float* _height)
	{
		if (_width != NULL)
			*_width = B_V_SCROLL_BAR_WIDTH + 2 * kFrameMargin;
		if (_height != NULL)
			*_height = B_H_SCROLL_BAR_HEIGHT + 2 * kFrameMargin;
	}

	virtual BSize MinSize()
	{
		return BView::MinSize();
	}

	virtual BSize MaxSize()
	{
		return BView::MaxSize();
	}

	virtual BSize PreferredSize()
	{
		return BView::PreferredSize();
	}

	virtual void SetAlignmentStyle(int style)
	{
		int32 direction;
		if ((style & SWT_LEFT) != 0)
			direction = BControlLook::B_LEFT_ARROW;
		else if ((style & SWT_RIGHT) != 0)
			direction = BControlLook::B_RIGHT_ARROW;
		else if ((style & SWT_UP) != 0)
			direction = BControlLook::B_UP_ARROW;
		else
			direction = BControlLook::B_DOWN_ARROW;

		if (direction != fDirection) {
			fDirection = direction;
			Invalidate();
		}
	}

private:
	static const int32	kFrameMargin = 1;
							// margin between frame and arrow area on each side

	int32				fDirection;
};


// pragma mark - HaikuCheckButton


class HaikuCheckButton : public HaikuViewButton<BCheckBox> {
public:
	HaikuCheckButton()
		:
		HaikuViewButton<BCheckBox>(""),
		fGrayed(false)
	{
		SetPartialStateToOff(true);
	}

	virtual void SetValue(int32 value)
	{
		if (value == Value())
			return;

		if (fGrayed && value == B_CONTROL_ON)
			value = B_CONTROL_PARTIALLY_ON;

		HaikuViewButton<BCheckBox>::SetValue(value);
	}

	virtual void SetAlignmentStyle(int style)
	{
		// TODO: BCheckBox doesn't support any alternative alignment.
	}

	virtual void SetGrayed(bool grayed)
	{
		if (grayed == fGrayed)
			return;

		fGrayed = grayed;

		if (Value() != B_CONTROL_OFF)
			SetValue(fGrayed ? B_CONTROL_PARTIALLY_ON : B_CONTROL_ON);
	}

private:
	bool	fGrayed;
};


// pragma mark - HaikuPushButton


class HaikuPushButton : public HaikuViewButton<BButton> {
public:
	HaikuPushButton()
		:
		HaikuViewButton<BButton>("")
	{
	}

	virtual void SetAlignmentStyle(int style)
	{
		// TODO: BButton doesn't support any alternative alignment.
	}
};


// pragma mark - HaikuRadioButton


class HaikuRadioButton : public HaikuViewButton<BRadioButton> {
public:
	HaikuRadioButton(bool inRadioGroup)
		:
		HaikuViewButton<BRadioButton>("", (BMessage*)NULL),
		fInRadioGroup(inRadioGroup)
	{
	}

	virtual void SetValue(int32 value)
	{
		if (value == Value())
			return;

		if (fInRadioGroup) {
			HaikuViewButton<BRadioButton>::SetValue(value);
		} else {
			// Skip BRadioButton::SetValue() to avoid the radio group behavior.
			// Unfortunately SetValue() invalidates the whole control. Only the
			// knob needs to be invalidated, but we don't know its whereabouts.
			BControl::SetValue(value);
		}

		// Deselecting the button doesn't trigger Invoke(), but SWT is expecting
		// a selection event.
		if (value == B_CONTROL_OFF)
			ButtonInvokedCallback();
	}

	virtual void SetAlignmentStyle(int style)
	{
		// TODO: BRadioButton doesn't support any alternative alignment.
	}

private:
	bool	fInRadioGroup;
};


// pragma mark - HaikuToggleButton


class HaikuToggleButton : public HaikuPushButton {
public:
	HaikuToggleButton()
		:
		HaikuPushButton()
	{
		SetBehavior(BButton::B_TOGGLE_BEHAVIOR);
	}
};


// pragma mark - HaikuButtonFactory



/*static*/ HaikuButton*
HaikuButtonFactory::CreatePushButton()
{
	return new(std::nothrow) HaikuPushButton;
}


/*static*/ HaikuButton*
HaikuButtonFactory::CreateToggleButton()
{
	return new(std::nothrow) HaikuToggleButton;
}


}	// namespace haiku
}	// namespace swt



// #pragma mark - native methods


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuButton_createArrowButton(
	JNIEnv* env, jclass clazz, jintLong displayHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuArrowButton* button = new(std::nothrow) HaikuArrowButton;
	if (button == NULL)
		return 0;
	return button->Handle();
}


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuButton_createCheckButton(
	JNIEnv* env, jclass clazz, jintLong displayHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuCheckButton* button = new(std::nothrow) HaikuCheckButton;
	if (button == NULL)
		return 0;
	return button->Handle();
}


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuButton_createPushButton(
	JNIEnv* env, jclass clazz, jintLong displayHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuPushButton* button = new(std::nothrow) HaikuPushButton;
	if (button == NULL)
		return 0;
	return button->Handle();
}


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuButton_createRadioButton(
	JNIEnv* env, jclass clazz, jintLong displayHandle, jboolean inRadioGroup)
{
	HAIKU_JNI_ENTER(env);

	HaikuRadioButton* button = new(std::nothrow) HaikuRadioButton(inRadioGroup);
	if (button == NULL)
		return 0;
	return button->Handle();
}


extern "C" jintLong
Java_org_eclipse_swt_internal_haiku_HaikuButton_createToggleButton(
	JNIEnv* env, jclass clazz, jintLong displayHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuToggleButton* button = new(std::nothrow) HaikuToggleButton;
	if (button == NULL)
		return 0;
	return button->Handle();
}


extern "C" jboolean
Java_org_eclipse_swt_internal_haiku_HaikuButton_isSelected(
	JNIEnv* env, jclass clazz, jintLong handle, jint alignment)
{
	HAIKU_JNI_ENTER(env);

	HaikuButton* button = HaikuButton::Get(handle);
	AutoLocker<HaikuButton> buttonLocker(button);
	return button->GetBControl()->Value() != B_CONTROL_OFF;
		// We treat B_CONTROL_PARTIALLY_ON as selected, since that's how we map
		// selected + grayed.
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuButton_setAlignmentStyle(
	JNIEnv* env, jclass clazz, jintLong handle, jint style)
{
	HAIKU_JNI_ENTER(env);

	HaikuButton* button = HaikuButton::Get(handle);
	AutoLocker<HaikuButton> buttonLocker(button);
	button->SetAlignmentStyle(style);
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuButton_setGrayed(
	JNIEnv* env, jclass clazz, jintLong handle, jboolean grayed)
{
	HAIKU_JNI_ENTER(env);

	HaikuButton* button = HaikuButton::Get(handle);
	AutoLocker<HaikuButton> buttonLocker(button);
	button->SetGrayed(grayed);
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuButton_setImage(
	JNIEnv* env, jclass clazz, jintLong handle, jintLong imageHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuButton* button = HaikuButton::Get(handle);
	AutoLocker<HaikuButton> buttonLocker(button);
	button->SetImage(imageHandle != 0 ? HaikuImage::Get(imageHandle) : NULL);
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuButton_setSelected(
	JNIEnv* env, jclass clazz, jintLong handle, jboolean selected)
{
	HAIKU_JNI_ENTER(env);

	HaikuButton* button = HaikuButton::Get(handle);
	AutoLocker<HaikuButton> buttonLocker(button);
	button->GetBControl()->SetValue(selected ? B_CONTROL_ON : B_CONTROL_OFF);
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuButton_setText(
	JNIEnv* env, jclass clazz, jintLong handle, jstring text)
{
	HAIKU_JNI_ENTER(env);

	HaikuButton* button = HaikuButton::Get(handle);
	AutoLocker<HaikuButton> buttonLocker(button);
	button->GetBControl()->SetLabel(HaikuUtils::FromJavaString(env, text));
}
