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


#include "HaikuViewGraphicsContext.h"

#include <Region.h>
#include <View.h>


using namespace swt::haiku;


namespace swt {
namespace haiku {


HaikuViewGraphicsContext::HaikuViewGraphicsContext(BView* view)
	:
	fView(view)
{
}


HaikuViewGraphicsContext::~HaikuViewGraphicsContext()
{
}


bool
HaikuViewGraphicsContext::Lock()
{
	return fView->LockLooper();
}


void
HaikuViewGraphicsContext::Unlock()
{
	fView->UnlockLooper();
}


rgb_color
HaikuViewGraphicsContext::ForegroundColor()
{
	return fView->HighColor();
}


void
HaikuViewGraphicsContext::SetForegroundColor(const rgb_color& color)
{
	fView->SetHighColor(color);
}


void
HaikuViewGraphicsContext::DrawRectangle(const BRect& rect)
{
	fView->StrokeRect(rect);
}


void
HaikuViewGraphicsContext::DrawString(const BPoint& point, const char* string,
	bool isTransparent)
{
	fView->PushState();

	font_height fontHeight;
	fView->GetFontHeight(&fontHeight);

	// isTransparent implies the drawing mode
	fView->SetDrawingMode(isTransparent ? B_OP_OVER : B_OP_COPY);

	// BView::DrawString() draws on the base line (as documented actually one
	// pixel above it), while SWT expects to have passed the top-left corner of
	// text to be drawn.
	float y = point.y + ceilf(fontHeight.ascent) + 1;
	fView->DrawString(string, BPoint(point.x, y));

	fView->PopState();
}


}	// namespace haiku
}	// namespace swt
