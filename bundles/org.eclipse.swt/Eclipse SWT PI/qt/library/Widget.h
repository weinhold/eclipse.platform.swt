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

#ifndef INC_WIDGET_H
#define INC_WIDGET_H


#include "swt.h"


namespace swt {
namespace qt {


class Widget {
public:
								Widget();
	virtual						~Widget();

			jintLong			getHandle() const
									{ return (jintLong)(size_t)this; }
	static	Widget*				get(jintLong handle)
									{ return (Widget*)(size_t)handle; }
	template<typename Target>
	static	Target*				getAs(jintLong handle);

	virtual	void				swtDelete();
};


template<typename Target>
/*static*/ inline Target*
Widget::getAs(jintLong handle)
{
	return static_cast<Target*>(get(handle));
}


}	// namespace qt
}	// namespace swt


#endif /* INC_WIDGET_H */
