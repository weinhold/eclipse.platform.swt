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

#ifndef INC_HAIKU_WINDOW_H
#define INC_HAIKU_WINDOW_H


#include <pthread.h>

#include <interface/Window.h>

#include "HaikuViewComposite.h"


namespace swt {
namespace haiku {


class HaikuDisplay;
class HaikuMessage;


class HaikuWindow : public BWindow, public HaikuComposite {
public:
			typedef HaikuViewComposite<BView> RootView;

public:
								HaikuWindow(HaikuDisplay* display);
								~HaikuWindow();

	virtual	void				DispatchMessage(BMessage* message,
									BHandler* handler);
	virtual	bool				QuitRequested();

	virtual	void				FrameMoved(BPoint newPosition);
	virtual	void				FrameResized(float newWidth, float newHeight);

			RootView*			CreateRootView();

			void				DispatchDelayedMessage(HaikuMessage* message);

	static	HaikuWindow*		Get(jlong handle)
									{ return GetAs<HaikuWindow>(handle); }

	// HaikuWidget interface 
	virtual	void				Delete();

	// HaikuControl interface 
	virtual	bool				Lock();
	virtual	void				Unlock();

	virtual	BSize				ControlPreferredSize(jint wHint, jint hHint);
	virtual	BRect				ControlFrame();
	virtual	void				ControlMoveTo(const BPoint& point);
	virtual	void				ControlResizeTo(const BSize& size);

	virtual	void				ControlSetPaintStyle(int32 style);

	// HaikuComposite interface 
	virtual	bool				CompositeAddChild(HaikuControl* child);
	virtual	bool				CompositeRemoveChild(HaikuControl* child);
	virtual	void				CompositeGetChildren(
									BObjectList<HaikuControl>& children);

private:
			HaikuDisplay*		fDisplay;
			RootView*			fRootView;
			HaikuMessage*		fDelayedMessage;
			bool				fDeleted;
};


}	// namespace haiku
}	// namespace swt


#endif /* INC_HAIKU_WINDOW_H */
