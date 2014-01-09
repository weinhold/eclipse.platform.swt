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

#ifndef INC_BUTTON_H
#define INC_BUTTON_H


#include "Composite.h"


class QAbstractButton;


namespace swt {
namespace qt {


class Image;


class Button : public Control {
public:
								Button();
	virtual						~Button();

	static	Button*				get(jintLong handle)
									{ return getAs<Button>(handle); }

	virtual	QAbstractButton*	swtGetAbstractButton() = 0;

	virtual	bool				swtIsSelected() = 0;
	virtual	void				swtSetSelected(bool selected) = 0;
	virtual	void				swtSetSelectionCallbackEnabled(bool enabled) = 0;

	virtual	void				swtSetAlignmentStyle(int style) = 0;
	virtual	void				swtSetFlat(bool flat) = 0;
	virtual	void				swtSetGrayed(bool grayed) = 0;
};


}	// namespace qt
}	// namespace swt


#endif /* INC_BUTTON_H */
