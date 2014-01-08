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

#ifndef INC_GRAPHICS_CONTEXT_H
#define INC_GRAPHICS_CONTEXT_H


#include <QPainter>

#include "swt.h"


namespace swt {
namespace qt {


class GraphicsContext {
public:
								GraphicsContext(QPaintDevice* device);
								~GraphicsContext();

			jintLong			getHandle() const
									{ return (jintLong)(size_t)this; }
	static	GraphicsContext*	get(jintLong handle)
									{ return (GraphicsContext*)(size_t)handle; }

			QPainter&			getPainter()
									{ return fPainter; }

private:
			QPainter			fPainter;
};


}	// namespace qt
}	// namespace swt


#endif /* INC_GRAPHICS_CONTEXT_H */
