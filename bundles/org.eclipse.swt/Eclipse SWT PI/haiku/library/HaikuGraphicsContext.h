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

#ifndef INC_HAIKU_GRAPHICS_CONTEXT_H
#define INC_HAIKU_GRAPHICS_CONTEXT_H


#include <InterfaceDefs.h>
#include <Rect.h>

#include "swt.h"


class BView;


namespace swt {
namespace haiku {


class HaikuGraphicsContext {
public:
								HaikuGraphicsContext();
	virtual						~HaikuGraphicsContext();

			jintLong			Handle() const
									{ return (jintLong)(addr_t)this; }
	static	HaikuGraphicsContext* Get(jintLong handle);
	template<typename Target>
	static	Target*				GetAs(jintLong handle);

	virtual	bool				Lock() = 0;
	virtual	void				Unlock() = 0;

	virtual	rgb_color			ForegroundColor() = 0;
	virtual	void				SetForegroundColor(const rgb_color& color) = 0;

	virtual	void				DrawRectangle(const BRect& rect) = 0;
	virtual	void				DrawString(const BPoint& point,
									const char* string, bool isTransparent) = 0;
};


/*static*/ inline HaikuGraphicsContext*
HaikuGraphicsContext::Get(jintLong handle)
{
	return (HaikuGraphicsContext*)(addr_t)handle;
}


template<typename Target>
/*static*/ inline Target*
HaikuGraphicsContext::GetAs(jintLong handle)
{
	return static_cast<Target*>(Get(handle));
}


}	// namespace haiku
}	// namespace swt


#endif /* INC_HAIKU_GRAPHICS_CONTEXT_H */
