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

#ifndef INC_HAIKU_MESSAGE_H
#define INC_HAIKU_MESSAGE_H


#include <app/Message.h>

#include <private/kernel/util/DoublyLinkedList.h>


namespace swt {
namespace haiku {


class HaikuWindow;


class HaikuMessage : public DoublyLinkedListLinkImpl<HaikuMessage> {
public:
								HaikuMessage(BMessage* message,
									HaikuWindow* window, BHandler* handler);
								~HaikuMessage();

			BMessage*			Message() const
									{ return fMessage; }
			HaikuWindow*		Window() const
									{ return fWindow; }
			BHandler*			Handler() const
									{ return fHandler; }

private:
			BMessage*			fMessage;
			HaikuWindow*		fWindow;
			BHandler*			fHandler;
};


typedef DoublyLinkedList<HaikuMessage> HaikuMessageList;


}	// namespace haiku
}	// namespace swt


#endif /* INC_HAIKU_MESSAGE_H */
