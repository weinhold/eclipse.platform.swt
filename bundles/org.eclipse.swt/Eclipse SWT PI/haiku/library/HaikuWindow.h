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


namespace swt {
namespace haiku {


class HaikuDisplay;
class HaikuMessage;


class HaikuWindow : public BWindow {
public:
								HaikuWindow(HaikuDisplay* display);
								~HaikuWindow();

	virtual	void				DispatchMessage(BMessage* message,
									BHandler* handler);
	virtual	bool				QuitRequested();

			BView*				CreateRootView();

			void				Delete();

			void				DispatchDelayedMessage(HaikuMessage* message);

private:
			HaikuDisplay*		fDisplay;
			BView*				fRootView;
			HaikuMessage*		fDelayedMessage;
			bool				fDeleted;
};


}	// namespace haiku
}	// namespace swt


#endif /* INC_HAIKU_WINDOW_H */
