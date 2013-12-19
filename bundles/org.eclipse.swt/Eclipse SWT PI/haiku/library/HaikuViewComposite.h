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

#ifndef INC_HAIKU_VIEW_COMPOSITE_H
#define INC_HAIKU_VIEW_COMPOSITE_H


#include "HaikuComposite.h"
#include "HaikuViewControl.h"


namespace swt {
namespace haiku {


template<typename ViewClass, typename CompositeClass = HaikuComposite>
class HaikuViewComposite : public HaikuViewControl<ViewClass, CompositeClass> {
public:
	template<typename... Arguments>
	HaikuViewComposite(const Arguments... arguments)
		:
		HaikuViewControl<ViewClass, CompositeClass>(arguments...)
	{
	}

	static HaikuViewComposite* Get(jlong handle)
	{
		return HaikuWidget::GetAs<HaikuViewComposite>(handle);
	}

	virtual bool CompositeAddChild(HaikuControl* child)
	{
		BView* childView = dynamic_cast<BView*>(child);
		if (childView == NULL)
			return false;

		this->AddChild(childView);
		return true;
	}

	virtual bool CompositeRemoveChild(HaikuControl* child)
	{
		BView* childView = dynamic_cast<BView*>(child);
		if (childView == NULL)
			return false;

		this->RemoveChild(childView);
		return true;
	}

	virtual void CompositeGetChildren(
		BObjectList<HaikuControl>& children)
	{
		int32 count = this->CountChildren();
		for (int32 i = 0; i < count; i++) {
			BView* childView = this->ChildAt(i);
			if (HaikuControl* control = dynamic_cast<HaikuControl*>(childView))
				children.AddItem(control);
		}
	}
};


}	// namespace haiku
}	// namespace swt


#endif /* INC_HAIKU_VIEW_COMPOSITE_H */
