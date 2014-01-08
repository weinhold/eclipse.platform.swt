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

#ifndef INC_SWT_QWIDGET_CONTROL_H
#define INC_SWT_QWIDGET_CONTROL_H


#include <algorithm>

#include <QMoveEvent>
#include <QResizeEvent>

#include "Control.h"
#include "Display.h"
#include "JNIContext.h"
#include "SWTDefs.h"


namespace swt {
namespace qt {


template<typename WidgetClass, typename ControlClass = Control>
class SwtQWidgetControl : public WidgetClass, public ControlClass {
public:
	template<typename... Arguments>
	SwtQWidgetControl(const Arguments... arguments)
		:
		WidgetClass(arguments...),
		ControlClass()
	{
	}

	static SwtQWidgetControl* get(jintLong handle)
	{
		return Widget::getAs<SwtQWidgetControl>(handle);
	}

	virtual void moveEvent(QMoveEvent* event)
	{
		Display::current()->callbackWidgetMoved(this, event->pos());
	}

	virtual	void resizeEvent(QResizeEvent* event)
	{
		Display::current()->callbackWidgetResized(this, event->size());
	}

	virtual QWidget* swtGetQWidget()
	{
		return this;
	}

	virtual QSize swtGetPreferredSize(jint wHint, jint hHint)
	{
		// The Qt API doesn't support getting the width for a height, so we
		// always get the preferred width, if the width isn't given.
		QSize preferred = this->sizeHint();
		QSize size;
		size.setWidth(wHint >= 0 ? wHint : preferred.width());

		if (this->hasHeightForWidth())
			size.setHeight(this->heightForWidth(size.width()));
		else
			size.setHeight(hHint >= 0 ? hHint : preferred.height());

		return size;
	}

	virtual QRect swtGetGeometry()
	{
		return this->geometry();
	}

	virtual void swtMoveTo(const QPoint& point)
	{
		this->move(point);
	}

	virtual void swtResizeTo(const QSize& size)
	{
		this->resize(size);
	}

	virtual void swtSetEnabled(bool enabled)
	{
		this->setEnabled(enabled);
	}

	virtual void swtSetStyle(jint style)
	{
		// TODO: Handle SWT_NO_REDRAW_RESIZE and SWT_NO_BACKGROUND.
	}
};


}	// namespace qt
}	// namespace swt


#endif /* INC_SWT_QWIDGET_CONTROL_H */
