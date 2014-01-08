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

#ifndef INC_DISPLAY_H
#define INC_DISPLAY_H


#include <pthread.h>

#include <QPoint>
#include <QSize>

#include "swt.h"


namespace swt {
namespace qt {


class Button;
class Control;
class GraphicsContext;
class Shell;
class Widget;


class Display {
public:
								Display();
								~Display();

	static	Display*			create(jobject object);
	static	Display*			current()
									{ return sCurrentDisplay; }

	static	Display*			get(jintLong handle)
									{ return (Display*)(size_t)handle; }
			jintLong			getHandle() const
									{ return (jintLong)(size_t)this; }

			void				callbackWidgetMoved(Widget* widget,
									const QPoint& newPosition);
			void				callbackWidgetResized(Widget* widget,
									const QSize& newSize);

//			void				callbackControlPaint(Control* control,
//									GraphicsContext* graphicsContext,
//									const QRect& updateRect);
//
//			void				callbackWidgetInvoked(Button* button,
//									bool selected, int what);

			bool				callbackShellCloseRequested(Shell* shell);

private:
			struct DispatchContext;

private:
			bool				_init(jobject object);

private:
	static	Display*			sCurrentDisplay;

			jobject				fObject;
			jmethodID			fWidgetMovedCallback;
			jmethodID			fWidgetResizedCallback;
			jmethodID			fControlPaintCallback;
			jmethodID			fWidgetInvokedCallback;
			jmethodID			fShellCloseRequestedCallback;
};


}	// namespace qt
}	// namespace swt


#endif /* INC_DISPLAY_H */
