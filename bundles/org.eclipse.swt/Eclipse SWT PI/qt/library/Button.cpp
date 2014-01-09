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


#include "Button.h"

#include <QCheckBox>
#include <QPushButton>
#include <QRadioButton>
#include <QStyle>

#include "Image.h"
#include "Utils.h"
#include "SwtQWidgetControl.h"


using namespace swt::qt;


namespace swt {
namespace qt {


// Button


Button::Button()
{
}


Button::~Button()
{
}


// AbstractButton


template<typename WidgetClass>
class AbstractButton : public SwtQWidgetControl<WidgetClass, Button> {
public:
	AbstractButton(bool isCheckable)
		:
		SwtQWidgetControl<WidgetClass, Button>(),
		fSelectionCallbackEnabled(true)
	{
		if (isCheckable)
			QObject::connect(this, &WidgetClass::toggled, [=](bool checked){ this->selectionChanged(checked); });
		else
			QObject::connect(this, &WidgetClass::clicked, [=](bool checked){ this->selectionChanged(checked); });
	}

	virtual QAbstractButton* swtGetAbstractButton()
	{
		return this;
	}

	virtual bool swtIsSelected()
	{
		return this->isChecked();
	}

	virtual void swtSetSelected(bool selected)
	{
		this->setChecked(selected);
	}

	virtual void swtSetSelectionCallbackEnabled(bool enabled)
	{
		fSelectionCallbackEnabled = enabled;
	}

	virtual void swtSetAlignmentStyle(int style)
	{
		// Setting the image/label alignment is not supported by QPushButton,
		// QCheckBox, and QRadioButton.
	}

	virtual void swtSetFlat(bool flat)
	{
		// not supported for most button types
	}

	virtual void swtSetGrayed(bool grayed)
	{
		// not supported for most button types
	}

private:
	void selectionChanged(bool checked)
	{
		if (fSelectionCallbackEnabled)
			Display::current()->callbackWidgetSelected(this, checked);
	}

private:
	bool	fSelectionCallbackEnabled;
};


// CheckButton


class CheckButton : public AbstractButton<QCheckBox> {
public:
	CheckButton()
		:
		AbstractButton<QCheckBox>(true)
	{
	}

	virtual bool swtIsSelected()
	{
		return checkState() != Qt::Unchecked;
	}

	virtual void swtSetSelected(bool selected)
	{
		if (selected == swtIsSelected())
			return;

		if (selected)
			setCheckState(isTristate() ? Qt::PartiallyChecked : Qt::Checked);
		else
			setCheckState(Qt::Unchecked);
	}

	virtual void swtSetGrayed(bool grayed)
	{
		if (grayed == isTristate())
			return;

		setTristate(grayed);

		if (checkState() != Qt::Unchecked)
			setCheckState(isTristate() ? Qt::PartiallyChecked : Qt::Checked);
	}

protected:
	virtual void nextCheckState()
	{
		swtSetSelected(!swtIsSelected());
	}
};


// PushButton


class PushButton : public AbstractButton<QPushButton> {
public:
	PushButton(bool isCheckable = false)
		:
		AbstractButton<QPushButton>(isCheckable)
	{
	}

	virtual void swtSetFlat(bool flat)
	{
		setFlat(flat);
	}
};


// ArrowButton


class ArrowButton : public PushButton {
public:
	ArrowButton()
		:
		PushButton()
	{
		swtSetAlignmentStyle(SWT_UP);
	}

	virtual void swtSetAlignmentStyle(int style)
	{
		QStyle::StandardPixmap pixmap;
		if ((style & SWT_LEFT) != 0)
			pixmap = QStyle::SP_ArrowLeft;
		else if ((style & SWT_RIGHT) != 0)
			pixmap = QStyle::SP_ArrowRight;
		else if ((style & SWT_UP) != 0)
			pixmap = QStyle::SP_ArrowUp;
		else
			pixmap = QStyle::SP_ArrowDown;

		setIcon(this->style()->standardIcon(pixmap, 0, this));
	}
};


/// RadioButton


class RadioButton : public AbstractButton<QRadioButton> {
public:
	RadioButton(bool inRadioGroup)
		:
		AbstractButton<QRadioButton>(true)
	{
		setAutoExclusive(inRadioGroup);
	}

protected:
	virtual void nextCheckState()
	{
		// When not in a radio group the button normally reverts to toggle
		// behavior. Prevent that.
		setChecked(true);
	}
};


// ToggleButton


class ToggleButton : public PushButton {
public:
	ToggleButton()
		:
		PushButton(true)
	{
		setCheckable(true);
	}
};


}	// namespace qt
}	// namespace swt


// native methods


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtButton_createArrowButton(
	JNIEnv* env, jclass clazz, jintLong displayHandle)
{
	SWT_QT_JNI_ENTER(env)

	ArrowButton* button = Widget::create<ArrowButton>();
	return button->getHandle();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtButton_createCheckButton(
	JNIEnv* env, jclass clazz, jintLong displayHandle)
{
	SWT_QT_JNI_ENTER(env)

	CheckButton* button = Widget::create<CheckButton>();
	return button->getHandle();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtButton_createPushButton(
	JNIEnv* env, jclass clazz, jintLong displayHandle)
{
	SWT_QT_JNI_ENTER(env)

	PushButton* button = Widget::create<PushButton>();
	return button->getHandle();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtButton_createRadioButton(
	JNIEnv* env, jclass clazz, jintLong displayHandle, jboolean inRadioGroup)
{
	SWT_QT_JNI_ENTER(env)

	RadioButton* button = Widget::create<RadioButton>(inRadioGroup);
	return button->getHandle();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtButton_createToggleButton(
	JNIEnv* env, jclass clazz, jintLong displayHandle)
{
	SWT_QT_JNI_ENTER(env)

	ToggleButton* button = Widget::create<ToggleButton>();
	return button->getHandle();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" jboolean
Java_org_eclipse_swt_internal_qt_QtButton_isSelected(
	JNIEnv* env, jclass clazz, jintLong handle)
{
	SWT_QT_JNI_ENTER(env)

	Button* button = Button::get(handle);
	return button->swtIsSelected();

	SWT_QT_JNI_EXIT(env, false)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtButton_setAlignmentStyle(
	JNIEnv* env, jclass clazz, jintLong handle, jint style)
{
	SWT_QT_JNI_ENTER(env)

	Button* button = Button::get(handle);
	button->swtSetAlignmentStyle(style);

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtButton_setFlat(
	JNIEnv* env, jclass clazz, jintLong handle, jboolean flat)
{
	SWT_QT_JNI_ENTER(env)

	Button* button = Button::get(handle);
	button->swtSetFlat(flat);

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtButton_setGrayed(
	JNIEnv* env, jclass clazz, jintLong handle, jboolean grayed)
{
	SWT_QT_JNI_ENTER(env)

	Button* button = Button::get(handle);
	button->swtSetGrayed(grayed);

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtButton_setImage(
	JNIEnv* env, jclass clazz, jintLong handle, jintLong imageHandle)
{
	SWT_QT_JNI_ENTER(env)

	Button* button = Button::get(handle);
	Image* image = imageHandle != 0 ? Image::get(imageHandle) : NULL;
	QIcon* icon = image != NULL ? image->getIcon() : NULL;
	button->swtGetAbstractButton()->setIcon(icon != NULL ? *icon : QIcon());

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtButton_setSelected(
	JNIEnv* env, jclass clazz, jintLong handle, jboolean selected)
{
	SWT_QT_JNI_ENTER(env)

	Button* button = Button::get(handle);
	button->swtSetSelectionCallbackEnabled(false);
	button->swtSetSelected(selected);
	button->swtSetSelectionCallbackEnabled(true);

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtButton_setText(
	JNIEnv* env, jclass clazz, jintLong handle, jstring text)
{
	SWT_QT_JNI_ENTER(env)

	Button* button = Button::get(handle);
	button->swtGetAbstractButton()->setText(Utils::fromJavaString(env, text));

	SWT_QT_JNI_EXIT(env,)
}
