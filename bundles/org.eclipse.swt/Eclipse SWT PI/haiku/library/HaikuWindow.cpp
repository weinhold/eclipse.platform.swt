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


#include "HaikuWindow.h"

#include <GroupLayout.h>
#include <View.h>

#include "swt.h"

#include "HaikuDisplay.h"
#include "HaikuJNIContext.h"


using namespace swt::haiku;


namespace swt {
namespace haiku {


HaikuWindow::HaikuWindow(HaikuDisplay* display)
	:
	BWindow(BRect(20, 20, 119, 119), "SWT", B_TITLED_WINDOW,
		B_ASYNCHRONOUS_CONTROLS),
	fDisplay(display),
	fRootView(NULL),
	fDelayedMessage(NULL),
	fDeleted(false)
{
}


HaikuWindow::~HaikuWindow()
{
}


void
HaikuWindow::DispatchMessage(BMessage* message, BHandler* handler)
{
	// Only redirect the messages to the display message queue as long as not
	// marked for deletion.
	if (fDeleted) {
		BWindow::DispatchMessage(message, handler);
		return;
	}

	if (message == CurrentMessage()) {
		HaikuMessage* haikuMessage = new(std::nothrow) HaikuMessage(message,
			this, handler);
		if (haikuMessage == NULL)
			return;

		DetachCurrentMessage();
		fDisplay->PushMessage(haikuMessage);
		return;
	}

	// Not dispatched in the message loop. That shouldn't happen.
	fprintf(stderr, "HaikuWindow::DispatchMessage(): Unexpected message!\n");
	BWindow::DispatchMessage(message, handler);
}


bool
HaikuWindow::QuitRequested()
{
	return fDeleted ? true : fDisplay->CallbackWindowQuitRequested(this);
}


BView*
HaikuWindow::CreateRootView()
{
	BGroupLayout* layout = new(std::nothrow) BGroupLayout(B_HORIZONTAL);
	if (layout == NULL)
		return NULL;
	SetLayout(layout);

	fRootView = new(std::nothrow) BView(NULL, B_WILL_DRAW);
	if (fRootView == NULL || layout->AddView(fRootView) == NULL) {
		delete fRootView;
		fRootView = NULL;
		return NULL;
	}

	fRootView->ResizeTo(Bounds().Size());

	return fRootView;
}


void
HaikuWindow::Delete()
{
	fDeleted = true;

	// handle all of our messages still in the display message queue
	HaikuMessageList messages;
	fDisplay->RemoveAllMessagesFor(this, messages);
	while (HaikuMessage* message = messages.RemoveHead()) {
		switch (message->Message()->what) {
			case B_QUIT_REQUESTED:
				// skip -- we'll send one ourselves below
				break;
			default:
				DispatchDelayedMessage(message);
				break;
		}
		delete message;
	}

	PostMessage(B_QUIT_REQUESTED);
}


void
HaikuWindow::DispatchDelayedMessage(HaikuMessage* message)
{
	fDelayedMessage = message;
	BWindow::DispatchMessage(message->Message(), message->Handler());
		// TODO: Check whether the handler is still valid!
	fDelayedMessage = NULL;
}


}	// namespace haiku
}	// namespace swt


// #pragma mark - native methods


extern "C" jlong
Java_org_eclipse_swt_internal_haiku_HaikuWindow_create(
	JNIEnv* env, jobject object, jlong displayHandle)
{
	HaikuJNIContext haikuJniContext(env);

	HaikuDisplay* display = (HaikuDisplay*)(addr_t)displayHandle;
	return (jlong)(addr_t)new(std::nothrow) HaikuWindow(display);
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuWindow_delete(JNIEnv* env,
	jobject object, jlong handle)
{
	HaikuJNIContext haikuJniContext(env);

	HaikuWindow* window = (HaikuWindow*)(addr_t)handle;
	if (window->Lock()) {
		window->Delete();
		window->Unlock();
	}
}


extern "C" jlong
Java_org_eclipse_swt_internal_haiku_HaikuWindow_createRootView(JNIEnv* env,
	jobject object, jlong handle)
{
	HaikuJNIContext haikuJniContext(env);

	HaikuWindow* window = (HaikuWindow*)(addr_t)handle;
	BView* view = NULL;
	if (window->Lock()) {
		view = window->CreateRootView();
		window->Unlock();
	}

	return (jlong)(addr_t)view;
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuWindow_setVisible(
	JNIEnv* env, jobject object, jlong handle, jboolean visible)
{
	HaikuJNIContext haikuJniContext(env);

	HaikuWindow* window = (HaikuWindow*)(addr_t)handle;
	if (window->Lock()) {
		bool isVisible = !window->IsHidden();
		if (isVisible != visible) {
			if (isVisible)
				window->Hide();
			else
				window->Show();
		}
		window->Unlock();
	}
}
