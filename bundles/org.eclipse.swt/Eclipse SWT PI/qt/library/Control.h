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

#ifndef INC_CONTROL_H
#define INC_CONTROL_H


#include <QRect>
#include <QSize>

#include "Widget.h"


class QWidget;


namespace swt {
namespace qt {


class Control : public Widget {
public:
								Control();
	virtual						~Control();

	static	Control*			get(jintLong handle)
									{ return getAs<Control>(handle); }

	virtual	void				swtInit();

	virtual	QSize				swtGetInitialSize();

	virtual	QWidget*			swtGetQWidget() = 0;

	virtual	QSize				swtGetPreferredSize(jint wHint, jint hHint) = 0;
	virtual	QRect				swtGetGeometry() = 0;
	virtual	void				swtMoveTo(const QPoint& point) = 0;
	virtual	void				swtResizeTo(const QSize& size) = 0;

	virtual	void				swtSetEnabled(bool enabled) = 0;
	virtual	void				swtSetStyle(jint style) = 0;
};


}	// namespace qt
}	// namespace swt


#endif /* INC_CONTROL_H */
