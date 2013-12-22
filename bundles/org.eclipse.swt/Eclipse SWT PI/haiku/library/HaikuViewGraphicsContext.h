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

#ifndef INC_HAIKU_VIEW_GRAPHICS_CONTEXT_H
#define INC_HAIKU_VIEW_GRAPHICS_CONTEXT_H


#include "HaikuGraphicsContext.h"


namespace swt {
namespace haiku {


class HaikuViewGraphicsContext : public HaikuGraphicsContext {
public:
								HaikuViewGraphicsContext(BView* view);
	virtual						~HaikuViewGraphicsContext();

	static	HaikuViewGraphicsContext* Get(jintLong handle);

	virtual	bool				Lock();
	virtual	void				Unlock();

	virtual	rgb_color			ForegroundColor();
	virtual	void				SetForegroundColor(const rgb_color& color);

	virtual	void				DrawRectangle(const BRect& rect);
	virtual	void				DrawString(const BPoint& point,
									const char* string, bool isTransparent);

private:
			BView*				fView;
};


/*static*/ inline HaikuViewGraphicsContext*
HaikuViewGraphicsContext::Get(jintLong handle)
{
	return GetAs<HaikuViewGraphicsContext>(handle);
}


}	// namespace haiku
}	// namespace swt


#endif /* INC_HAIKU_VIEW_GRAPHICS_CONTEXT_H */
