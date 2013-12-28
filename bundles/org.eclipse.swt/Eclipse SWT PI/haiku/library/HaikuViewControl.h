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

#ifndef INC_HAIKU_VIEW_CONTROL_H
#define INC_HAIKU_VIEW_CONTROL_H


#include <algorithm>

#include <interface/View.h>

#include "HaikuControl.h"
#include "HaikuDisplay.h"
#include "HaikuJNIContext.h"
#include "HaikuViewGraphicsContext.h"
#include "SWTDefs.h"


namespace swt {
namespace haiku {


template<typename ViewClass, typename ControlClass = HaikuControl>
class HaikuViewControl : public ViewClass, public ControlClass {
public:
	template<typename... Arguments>
	HaikuViewControl(const Arguments... arguments)
		:
		ViewClass(arguments...),
		ControlClass()
	{
	}

	static HaikuViewControl* Get(jintLong handle)
	{
		return HaikuWidget::GetAs<HaikuViewControl>(handle);
	}

	virtual void Draw(BRect updateRect)
	{
		ViewClass::Draw(updateRect);
		HaikuViewGraphicsContext graphicsContext(this);
		HaikuJNIContext::CurrentDisplay()->CallbackControlDraw(this,
			&graphicsContext, updateRect);
	}

	virtual void FrameMoved(BPoint newPosition)
	{
		HaikuJNIContext::CurrentDisplay()->CallbackWidgetFrameMoved(this,
			newPosition);
	}

	virtual void FrameResized(float newWidth, float newHeight)
	{
		HaikuJNIContext::CurrentDisplay()->CallbackWidgetFrameResized(this,
			BSize(newWidth, newHeight));
	}

	virtual bool Lock()
	{
		return this->LockLooper();
	}

	virtual void Unlock()
	{
		return this->UnlockLooper();
	}

	virtual BView* ControlGetView()
	{
		return this;
	}

	virtual BSize ControlPreferredSize(jint wHint, jint hHint)
	{
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

	virtual void ControlSetPaintStyle(int32 style)
	{
		uint32 viewFlags = this->Flags();
		if ((style & SWT_NO_REDRAW_RESIZE) != 0)
			viewFlags &= B_FULL_UPDATE_ON_RESIZE;
		else
			viewFlags |= B_FULL_UPDATE_ON_RESIZE;

		this->SetFlags(viewFlags);

		if ((style & SWT_NO_BACKGROUND) != 0)
			this->SetViewColor(B_TRANSPARENT_32_BIT);
	}
};


}	// namespace haiku
}	// namespace swt


#endif /* INC_HAIKU_VIEW_CONTROL_H */
