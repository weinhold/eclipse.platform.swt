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

#ifndef INC_HAIKU_COMPOSITE_H
#define INC_HAIKU_COMPOSITE_H


#include <support/ObjectList.h>

#include "swt.h"

#include "HaikuControl.h"


namespace swt {
namespace haiku {


class HaikuComposite : public HaikuControl {
public:
								HaikuComposite();
	virtual						~HaikuComposite();

	static	HaikuComposite*		Get(jlong handle)
									{ return GetAs<HaikuComposite>(handle); }

	virtual	bool				CompositeAddChild(HaikuControl* child) = 0;
	virtual	bool				CompositeRemoveChild(HaikuControl* child) = 0;
	virtual	void				CompositeGetChildren(
									BObjectList<HaikuControl>& children) = 0;
};


}	// namespace haiku
}	// namespace swt


#endif /* INC_HAIKU_COMPOSITE_H */
