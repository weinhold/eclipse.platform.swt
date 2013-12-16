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

#include <private/shared/AutoLocker.h>

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


HaikuWindow::RootView*
HaikuWindow::CreateRootView()
{
	BGroupLayout* layout = new(std::nothrow) BGroupLayout(B_HORIZONTAL);
	if (layout == NULL)
		return NULL;
	SetLayout(layout);

	fRootView = new(std::nothrow) RootView((const char*)NULL, B_WILL_DRAW);
	if (fRootView == NULL || layout->AddView(fRootView) == NULL) {
		delete fRootView;
		fRootView = NULL;
		return NULL;
	}

	fRootView->ResizeTo(Bounds().Size());

	return fRootView;
}


void
HaikuWindow::DispatchDelayedMessage(HaikuMessage* message)
{
	fDelayedMessage = message;
	BWindow::DispatchMessage(message->Message(), message->Handler());
		// TODO: Check whether the handler is still valid!
	fDelayedMessage = NULL;
}


void
HaikuWindow::Delete()
{
	AutoLocker<HaikuWindow> locker(this);

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


bool
HaikuWindow::Lock()
{
	return BWindow::Lock();
}


void
HaikuWindow::Unlock()
{
	BWindow::Unlock();
}


BSize
HaikuWindow::ControlPreferredSize(jint wHint, jint hHint)
{
	return fRootView->ControlPreferredSize(wHint, hHint);
}


BRect
HaikuWindow::ControlFrame()
{
	return Frame();
}


void
HaikuWindow::ControlMoveTo(const BPoint& point)
{
	MoveTo(point);
}


void
HaikuWindow::ControlResizeTo(const BSize& size)
{
	ResizeTo(size.width, size.height);
}


bool
HaikuWindow::CompositeAddChild(HaikuControl* child)
{
	return fRootView->CompositeAddChild(child);
}


bool
HaikuWindow::CompositeRemoveChild(HaikuControl* child)
{
	return fRootView->CompositeRemoveChild(child);
}


void
HaikuWindow::CompositeGetChildren(BObjectList<HaikuControl>& children)
{
	fRootView->CompositeGetChildren(children);
}


}	// namespace haiku
}	// namespace swt


// #pragma mark - native methods


extern "C" jlong
Java_org_eclipse_swt_internal_haiku_HaikuWindow_create(
	JNIEnv* env, jobject object, jlong displayHandle)
{
	HAIKU_JNI_ENTER(env);

	HaikuDisplay* display = (HaikuDisplay*)(addr_t)displayHandle;
	HaikuWindow* window = new(std::nothrow) HaikuWindow(display);
	return window != NULL ? window->Handle() : 0;
}


extern "C" jlong
Java_org_eclipse_swt_internal_haiku_HaikuWindow_createRootView(JNIEnv* env,
	jobject object, jlong handle)
{
	HAIKU_JNI_ENTER(env);

	HaikuWindow* window = HaikuWindow::Get(handle);
	HaikuWindow::RootView* view = NULL;
	if (window->Lock()) {
		view = window->CreateRootView();
		window->Unlock();
	}

	return view != NULL ? view->Handle() : 0;
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuWindow_setVisible(
	JNIEnv* env, jobject object, jlong handle, jboolean visible)
{
	HAIKU_JNI_ENTER(env);

	HaikuWindow* window = HaikuWindow::Get(handle);
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
