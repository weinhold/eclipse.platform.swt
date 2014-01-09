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


#include "ToolItem.h"

#include <QMenu>
#include <QPointer>
#include <QStyleOptionToolButton>
#include <QToolButton>

#include "Display.h"
#include "Image.h"
#include "JNIContext.h"
#include "Utils.h"
#include "SwtQWidgetControl.h"


using namespace swt::qt;


namespace swt {
namespace qt {


// ToolItem


ToolItem::ToolItem()
	:
	fAction(NULL)
{
}


ToolItem::~ToolItem()
{
}

// ToolButton


class ToolButton : public SwtQWidgetControl<QToolButton> {
public:
	ToolButton(bool radioMode)
		:
		SuperClass(),
		fRadioMode(radioMode)
	{
	}

protected:
	virtual void mousePressEvent(QMouseEvent* event) {
		// If this is a button with a drop-down menu arrow, intercept the click
		// on the the arrow, so the menu doesn't open. Emit the clicked() signal
		// with argument "true" instead, which will be passed on and interpreted
		// by the Java ToolItem class accordingly.
		if (event->button() == Qt::LeftButton && (popupMode() == MenuButtonPopup)) {
			QStyleOptionToolButton option;
			initStyleOption(&option);

			QRect rect = style()->subControlRect(QStyle::CC_ToolButton, &option,
				QStyle::SC_ToolButtonMenu, this);
			if (rect.isValid() && rect.contains(event->pos())) {
				emit clicked(true);
				return;
			}
		}

		SuperClass::mousePressEvent(event);
	}

	virtual void nextCheckState()
	{
		if (fRadioMode) {
			// When not in a radio group the button normally reverts to toggle
			// behavior. Prevent that.
			setChecked(true);
		} else
			SuperClass::nextCheckState();
	}

private:
	typedef SwtQWidgetControl<QToolButton> SuperClass;

private:
	bool	fRadioMode;
};


// ToolButtonToolItem


class ToolButtonToolItem : public ToolItem {
public:
	ToolButtonToolItem(bool isCheckable, bool radioMode = false)
		:
		ToolItem(),
		fToolButton(Widget::create<ToolButton>(radioMode)),
		fImage(NULL),
		fDisabledImage(NULL),
		fHotImage(NULL),
		fSelectionCallbackEnabled(true)
	{
		fToolButton->setCheckable(isCheckable);

		if (isCheckable)
			QObject::connect(fToolButton.data(), &QToolButton::toggled, [=](bool checked){ this->_selectionChanged(checked); });
		else
			QObject::connect(fToolButton.data(), &QToolButton::clicked, [=](bool checked){ this->_selectionChanged(checked); });

	}

	~ToolButtonToolItem()
	{
		if (!fToolButton.isNull() && fToolButton->parent() == NULL)
			fToolButton->swtDelete();
	}

	virtual QWidget* swtGetQWidget()
	{
		return fToolButton;
	}

	virtual Control* swtGetControl()
	{
		return fToolButton;
	}

	virtual void swtSetControl(Control* control)
	{
		// not allowed
	}

	virtual bool swtIsSelected()
	{
		return fToolButton->isChecked();
	}

	virtual void swtSetSelected(bool selected)
	{
		fSelectionCallbackEnabled = false;
		fToolButton->setChecked(selected);
		fSelectionCallbackEnabled = true;
	}

	virtual void setSetText(const QString& text)
	{
		fToolButton->setText(text);
		_updateButtonStyle();
	}

	virtual void setSetToolTipText(const QString& text)
	{
		fToolButton->setToolTip(text);
	}

	virtual void setSetImage(Image* image)
	{
		if (image == fImage)
			return;

		fImage = image;

		_updateIcon();
		_updateButtonStyle();
	}

	virtual void setSetHotImage(Image* image)
	{
		if (image == fHotImage)
			return;

		fHotImage = image;

		QIcon icon(fToolButton->icon());
		icon.addPixmap(fHotImage != NULL ? fHotImage->getPixmap() : QPixmap(), QIcon::Active);
		fToolButton->setIcon(icon);
	}

	virtual void setSetDisabledImage(Image* image)
	{
		if (image == fDisabledImage)
			return;

		fDisabledImage = image;

		QIcon icon(fToolButton->icon());
		icon.addPixmap(fDisabledImage != NULL ? fDisabledImage->getPixmap() : QPixmap(), QIcon::Disabled);
		fToolButton->setIcon(icon);
	}

private:
	void _updateIcon()
	{
		QIcon icon(fImage != NULL ? fImage->getPixmap() : QPixmap());

		if (fDisabledImage != NULL)
			icon.addPixmap(fDisabledImage->getPixmap(), QIcon::Disabled);
		if (fHotImage != NULL)
			icon.addPixmap(fHotImage->getPixmap(), QIcon::Active);

		fToolButton->setIcon(icon);
	}

	void _updateButtonStyle()
	{
		Qt::ToolButtonStyle buttonStyle = Qt::ToolButtonIconOnly;
		if (!fToolButton->text().isEmpty()) {
			if (fImage != NULL)
				buttonStyle = Qt::ToolButtonTextBesideIcon;
			else
				buttonStyle = Qt::ToolButtonTextOnly;
		}

		fToolButton->setToolButtonStyle(buttonStyle);
	}

	void _selectionChanged(bool checked)
	{
		if (fSelectionCallbackEnabled)
			Display::current()->callbackWidgetSelected(this, checked);
	}

protected:
	QPointer<ToolButton>	fToolButton;
	Image*					fImage;
	Image*					fDisabledImage;
	Image*					fHotImage;
	bool					fSelectionCallbackEnabled;
};


// CheckToolItem


class CheckToolItem : public ToolButtonToolItem {
public:
	CheckToolItem()
		:
		ToolButtonToolItem(true)
	{
	}
};


// DropDownToolItem


class DropDownToolItem : public ToolButtonToolItem {
public:
	DropDownToolItem()
		:
		ToolButtonToolItem(false)
	{
		fToolButton->setPopupMode(QToolButton::MenuButtonPopup);
		fToolButton->setMenu(new QMenu);
	}
};


// PushToolItem


class PushToolItem : public ToolButtonToolItem {
public:
	PushToolItem()
		:
		ToolButtonToolItem(false)
	{
	}
};


// RadioToolItem


class RadioToolItem : public ToolButtonToolItem {
public:
	RadioToolItem()
		:
		ToolButtonToolItem(true, true)
	{
	}
};


// SeparatorToolItem


class SeparatorToolItem : public ToolItem {
public:
	SeparatorToolItem()
		:
		ToolItem(),
		fControl(NULL)
	{
	}

	~SeparatorToolItem()
	{
	}

	virtual QWidget* swtGetQWidget()
	{
		return fControl != NULL ? fControl->swtGetQWidget() : NULL;
	}

	virtual Control* swtGetControl()
	{
		return fControl;
	}

	virtual void swtSetControl(Control* control)
	{
		fControl = control;
	}

	virtual bool swtIsSelected()
	{
		return false;
	}

	virtual void swtSetSelected(bool selected)
	{
		// not allowed
	}

	virtual void setSetText(const QString& text)
	{
		// not allowed
	}

	virtual void setSetToolTipText(const QString& text)
	{
		// not allowed
	}

	virtual void setSetImage(Image* image)
	{
		// not allowed
	}

	virtual void setSetHotImage(Image* image)
	{
		// not allowed
	}

	virtual void setSetDisabledImage(Image* image)
	{
		// not allowed
	}

private:
	Control*	fControl;
};


}	// namespace qt
}	// namespace swt


// native methods


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtToolItem_createCheckItem(
	JNIEnv* env, jobject object, jintLong displayHandle)
{
	SWT_QT_JNI_ENTER(env)

	return Widget::create<CheckToolItem>()->getHandle();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtToolItem_createDropDownItem(
	JNIEnv* env, jobject object, jintLong displayHandle)
{
	SWT_QT_JNI_ENTER(env)

	return Widget::create<DropDownToolItem>()->getHandle();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtToolItem_createPushItem(
	JNIEnv* env, jobject object, jintLong displayHandle)
{
	SWT_QT_JNI_ENTER(env)

	return Widget::create<PushToolItem>()->getHandle();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtToolItem_createRadioItem(
	JNIEnv* env, jobject object, jintLong displayHandle)
{
	SWT_QT_JNI_ENTER(env)

	return Widget::create<RadioToolItem>()->getHandle();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtToolItem_createSeparatorItem(
	JNIEnv* env, jobject object, jintLong displayHandle)
{
	SWT_QT_JNI_ENTER(env)

	return Widget::create<SeparatorToolItem>()->getHandle();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" jintLong
Java_org_eclipse_swt_internal_qt_QtToolItem_getControl(
	JNIEnv* env, jobject object, jintLong handle)
{
	SWT_QT_JNI_ENTER(env)

	ToolItem* item = ToolItem::get(handle);
	return item->swtGetControl()->getHandle();

	SWT_QT_JNI_EXIT(env, 0)
}


extern "C" jboolean
Java_org_eclipse_swt_internal_qt_QtToolItem_isSelected(
	JNIEnv* env, jobject object, jintLong handle)
{
	SWT_QT_JNI_ENTER(env)

	ToolItem* item = ToolItem::get(handle);
	return item->swtIsSelected();

	SWT_QT_JNI_EXIT(env, false)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtToolItem_setControl(
	JNIEnv* env, jobject object, jintLong handle, jintLong controlHandle)
{
	SWT_QT_JNI_ENTER(env)

	ToolItem* item = ToolItem::get(handle);
	item->swtSetControl(controlHandle != 0 ? Control::get(controlHandle) : NULL);

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtToolItem_setDisabledImage(
	JNIEnv* env, jobject object, jintLong handle, jintLong imageHandle)
{
	SWT_QT_JNI_ENTER(env)

	ToolItem* item = ToolItem::get(handle);
	item->setSetDisabledImage(imageHandle != 0 ? Image::get(imageHandle) : NULL);

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtToolItem_setHotImage(
	JNIEnv* env, jobject object, jintLong handle, jintLong imageHandle)
{
	SWT_QT_JNI_ENTER(env)

	ToolItem* item = ToolItem::get(handle);
	item->setSetHotImage(imageHandle != 0 ? Image::get(imageHandle) : NULL);

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtToolItem_setImage(
	JNIEnv* env, jobject object, jintLong handle, jintLong imageHandle)
{
	SWT_QT_JNI_ENTER(env)

	ToolItem* item = ToolItem::get(handle);
	item->setSetImage(imageHandle != 0 ? Image::get(imageHandle) : NULL);

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtToolItem_setSelected(
	JNIEnv* env, jobject object, jintLong handle, jboolean selected)
{
	SWT_QT_JNI_ENTER(env)

	ToolItem* item = ToolItem::get(handle);
	item->swtSetSelected(selected);

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtToolItem_setText(
	JNIEnv* env, jobject object, jintLong handle, jstring text)
{
	SWT_QT_JNI_ENTER(env)

	ToolItem* item = ToolItem::get(handle);
	item->setSetText(Utils::fromJavaString(env, text));

	SWT_QT_JNI_EXIT(env,)
}


extern "C" void
Java_org_eclipse_swt_internal_qt_QtToolItem_setToolTipText(
	JNIEnv* env, jobject object, jintLong handle, jstring text)
{
	SWT_QT_JNI_ENTER(env)

	ToolItem* item = ToolItem::get(handle);
	item->setSetToolTipText(Utils::fromJavaString(env, text));

	SWT_QT_JNI_EXIT(env,)
}
