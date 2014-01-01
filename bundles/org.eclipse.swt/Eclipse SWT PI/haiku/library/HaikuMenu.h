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

#ifndef INC_HAIKU_MENU_H
#define INC_HAIKU_MENU_H


#include "HaikuControl.h"


class BMenu;


namespace swt {
namespace haiku {


class HaikuMenu : public HaikuControl {
public:
								HaikuMenu();
	virtual						~HaikuMenu();

	static	HaikuMenu*			Get(jintLong handle)
									{ return GetAs<HaikuMenu>(handle); }

	virtual	BMenu*				GetBMenu() = 0;

	virtual	void				MenuSetLocation(const BPoint& location) = 0;
	virtual	void				MenuSetVisible(bool visible) = 0;
};


}	// namespace haiku
}	// namespace swt


#endif /* INC_HAIKU_MENU_H */
