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

#ifndef INC_HAIKU_CONTROL_H
#define INC_HAIKU_CONTROL_H


#include <Rect.h>
#include <Size.h>

#include "HaikuWidget.h"


class BView;


namespace swt {
namespace haiku {


class HaikuControl : public HaikuWidget {
public:
								HaikuControl();
	virtual						~HaikuControl();

	static	HaikuControl*		Get(jintLong handle)
									{ return GetAs<HaikuControl>(handle); }

	virtual	bool				Lock() = 0;
	virtual	void				Unlock() = 0;

	virtual	BView*				ControlGetView() = 0;

	virtual	BSize				ControlPreferredSize(jint wHint, jint hHint)
									= 0;
	virtual	BRect				ControlFrame() = 0;
	virtual	void				ControlMoveTo(const BPoint& point) = 0;
	virtual	void				ControlResizeTo(const BSize& size) = 0;

	virtual	void				ControlSetPaintStyle(int32 style) = 0;
};


}	// namespace haiku
}	// namespace swt


#endif /* INC_HAIKU_CONTROL_H */
