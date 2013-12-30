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

#ifndef INC_HAIKU_DISPLAY_H
#define INC_HAIKU_DISPLAY_H


#include <pthread.h>

#include <Point.h>
#include <Size.h>

#include "swt.h"

#include "HaikuMessage.h"


class BLooper;


namespace swt {
namespace haiku {


class HaikuButton;
class HaikuControl;
class HaikuGraphicsContext;
class HaikuWidget;


class HaikuDisplay {
public:
								HaikuDisplay();
								~HaikuDisplay();

	static	HaikuDisplay*		Create(jobject object);

			void				PushMessage(HaikuMessage* message);
			HaikuMessage*		PopMessage(bool wait)
									{ return _GetMessage(true, wait); }
			HaikuMessage*		PeekMessage(bool wait)
									{ return _GetMessage(false, wait); }

			void				RemoveAllMessagesFor(BLooper* target,
									HaikuMessageList& messages);

			bool				HandleNextMessage();

			void				CallbackWidgetFrameMoved(
									HaikuWidget* widget,
									const BPoint& newPosition);
			void				CallbackWidgetFrameResized(
									HaikuWidget* widget, const BSize& newSize);

			void				CallbackControlDraw(
									HaikuControl* control,
									HaikuGraphicsContext* graphicsContext,
									const BRect& updateRect);

			void				CallbackWidgetInvoked(HaikuButton* button,
									bool selected);

			bool				CallbackWindowQuitRequested(
									HaikuWindow* window);

private:
			struct DispatchContext;

private:
			bool				_Init(jobject object);
			HaikuMessage*		_GetMessage(bool pop, bool wait);

private:
			HaikuMessageList	fQueue;
			pthread_mutex_t		fMutex;
			pthread_cond_t		fNotEmptyCondition;
			DispatchContext*	fContext;

			jobject				fObject;
			jmethodID			fWidgetFrameMovedCallback;
			jmethodID			fWidgetFrameResizedCallback;
			jmethodID			fControlDrawCallback;
			jmethodID			fWidgetInvokedCallback;
			jmethodID			fWindowQuitRequestedCallback;
};


}	// namespace haiku
}	// namespace swt


#endif /* INC_HAIKU_DISPLAY_H */
