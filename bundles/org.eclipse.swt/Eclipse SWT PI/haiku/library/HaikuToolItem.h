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

#ifndef INC_HAIKU_TOOL_ITEM_H
#define INC_HAIKU_TOOL_ITEM_H


#include "HaikuWidget.h"


class BView;


namespace swt {
namespace haiku {


class HaikuControl;
class HaikuImage;


class HaikuToolItem : public HaikuWidget {
public:
								HaikuToolItem();
	virtual						~HaikuToolItem();

	static	HaikuToolItem*		Get(jintLong handle)
									{ return GetAs<HaikuToolItem>(handle); }

			bool				Lock();
			void				Unlock();

	virtual	BView*				ToolItemGetView() = 0;

	virtual	HaikuControl*		ToolItemGetControl() = 0;
	virtual	void				ToolItemSetControl(HaikuControl* control) = 0;

	virtual	bool				ToolItemIsSelected() = 0;
	virtual	void				ToolItemSetSelected(bool selected) = 0;

	virtual	void				ToolItemSetText(const char* text) = 0;

	virtual	void				ToolItemSetToolTipText(const char* text) = 0;

	virtual	void				ToolItemSetImage(HaikuImage* image) = 0;
	virtual	void				ToolItemSetHotImage(HaikuImage* image) = 0;
	virtual	void				ToolItemSetDisabledImage(HaikuImage* image) = 0;
};


}	// namespace haiku
}	// namespace swt


#endif /* INC_HAIKU_TOOL_ITEM_H */
