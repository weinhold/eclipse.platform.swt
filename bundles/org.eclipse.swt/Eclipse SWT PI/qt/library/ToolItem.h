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

#ifndef INC_TOOL_ITEM_H
#define INC_TOOL_ITEM_H


#include "Widget.h"


class QAction;
class QString;
class QWidget;


namespace swt {
namespace qt {


class Control;
class Image;


class ToolItem : public Widget {
public:
								ToolItem();
	virtual						~ToolItem();

	static	ToolItem*			get(jintLong handle)
									{ return getAs<ToolItem>(handle); }

			QAction*			getAction() const
									{ return fAction; }
			void				setAction(QAction* action)
									{ fAction = action; }

	virtual	QWidget*			swtGetQWidget() = 0;

	virtual	Control*			swtGetControl() = 0;
	virtual	void				swtSetControl(Control* control) = 0;

	virtual	bool				swtIsSelected() = 0;
	virtual	void				swtSetSelected(bool selected) = 0;

	virtual	void				setSetText(const QString& text) = 0;

	virtual	void				setSetToolTipText(const QString& text) = 0;

	virtual	void				setSetImage(Image* image) = 0;
	virtual	void				setSetHotImage(Image* image) = 0;
	virtual	void				setSetDisabledImage(Image* image) = 0;

private:
			QAction*			fAction;
};


}	// namespace qt
}	// namespace swt


#endif /* INC_TOOL_ITEM_H */
