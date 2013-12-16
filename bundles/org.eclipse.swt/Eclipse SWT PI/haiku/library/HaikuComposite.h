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


#include <algorithm>

#include <interface/View.h>
#include <support/ObjectList.h>

#include "swt.h"

#include "HaikuControl.h"
#include "HaikuDisplay.h"
#include "HaikuJNIContext.h"


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


template<typename ViewClass>
class HaikuViewComposite : public ViewClass, public HaikuComposite {
public:
	template<typename... Arguments>
	HaikuViewComposite(const Arguments... arguments)
		:
		ViewClass(arguments...),
		HaikuComposite()
	{
	}

	static HaikuViewComposite* Get(jlong handle)
	{
		return GetAs<HaikuViewComposite>(handle);
	}

	virtual void FrameMoved(BPoint newPosition)
	{
		HaikuJNIContext::CurrentDisplay()->CallbackControlFrameMoved(this);
	}

	virtual void FrameResized(float newWidth, float newHeight)
	{
		HaikuJNIContext::CurrentDisplay()->CallbackControlFrameResized(this);
	}

	virtual bool Lock()
	{
		return this->LockLooper();
	}

	virtual void Unlock()
	{
		return this->UnlockLooper();
	}

	virtual BSize ControlPreferredSize(jint wHint, jint hHint)
	{
		// If hints have been specified, make sure they lie within the
		// min/max range for the view.
		if (wHint >= 0 || hHint >= 0) {
			BSize min = this->MinSize();
			BSize max = this->MaxSize();
			if (wHint >= 0) {
				wHint = std::max(wHint, (jint)min.width + 1);
				wHint = std::min(wHint, (jint)max.width + 1);
			}
			if (hHint >= 0) {
				hHint = std::max(hHint, (jint)min.height + 1);
				hHint = std::min(hHint, (jint)max.height + 1);
			}
		}

		// The Haiku API doesn't support getting the width for a height, so we
		// always get the preferred width, if the width isn't given.
		BSize preferred = this->PreferredSize();
		BSize size;
		size.width = wHint >= 0 ? float(wHint - 1) : preferred.width;

		if (this->HasHeightForWidth())
			this->GetHeightForWidth(size.width, NULL, NULL, &size.height);
		else
			size.height = hHint >= 0 ? hHint - 1 : preferred.height;

		return size;
	}

	virtual BRect ControlFrame()
	{
		return this->Frame();
	}

	virtual void ControlMoveTo(const BPoint& point)
	{
		return this->MoveTo(point);
	}

	virtual void ControlResizeTo(const BSize& size)
	{
		return this->ResizeTo(size);
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


#endif /* INC_HAIKU_COMPOSITE_H */
