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

#ifndef INC_COMPOSITE_H
#define INC_COMPOSITE_H


#include <QList>

#include "Control.h"


namespace swt {
namespace qt {


class Composite : public Control {
public:
								Composite();
	virtual						~Composite();

	static	Composite*			get(jintLong handle)
									{ return getAs<Composite>(handle); }

	virtual	void				swtAddChild(Control* child) = 0;
	virtual	void				swtRemoveChild(Control* child) = 0;
	virtual	QList<Control*>		swtGetChildren() = 0;
};


}	// namespace qt
}	// namespace swt


#endif /* INC_COMPOSITE_H */
