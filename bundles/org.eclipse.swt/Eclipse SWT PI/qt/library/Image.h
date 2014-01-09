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

#ifndef INC_IMAGE_H
#define INC_IMAGE_H


#include "swt.h"


class QIcon;


namespace swt {
namespace qt {


class Image {
public:
								Image(const QIcon& icon);
	virtual						~Image();

			jintLong			getHandle() const
									{ return (jintLong)(size_t)this; }
	static	Image*				get(jintLong handle)
									{ return (Image*)(size_t)handle; }

			QIcon*				getIcon() const
									{ return fIcon; }

private:
			QIcon*				fIcon;
};


}	// namespace qt
}	// namespace swt


#endif /* INC_IMAGE_H */
