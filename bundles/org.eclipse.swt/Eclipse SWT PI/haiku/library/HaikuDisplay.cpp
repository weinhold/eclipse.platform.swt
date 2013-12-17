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


#include "HaikuDisplay.h"

#include "HaikuJNIContext.h"
#include "HaikuWindow.h"


using namespace swt::haiku;


namespace swt {
namespace haiku {


struct HaikuDisplay::DispatchContext {
	DispatchContext*	fNextContext;
	HaikuMessage*		fMessage;

	DispatchContext(DispatchContext* nextContext)
		:
		fNextContext(nextContext),
		fMessage(NULL)
	{
	}
};


HaikuDisplay::HaikuDisplay()
	:
	fQueue(),
	fContext(NULL),
	fObject(NULL),
	fWidgetFrameMovedCallback(NULL),
	fWidgetFrameResizedCallback(NULL),
	fWindowQuitRequestedCallback(NULL)
{
	pthread_mutex_init(&fMutex, NULL);
	pthread_cond_init(&fNotEmptyCondition, NULL);
}


HaikuDisplay::~HaikuDisplay()
{
	while (HaikuMessage* message = PopMessage(false))
		delete message;

	if (fObject != NULL) {
		JNIEnv* env = HaikuJNIContext::CurrentEnv();
		env->DeleteGlobalRef(fObject);
	}

	pthread_cond_destroy(&fNotEmptyCondition);
	pthread_mutex_destroy(&fMutex);
}


/*static*/ HaikuDisplay*
HaikuDisplay::Create(jobject object)
{
	HaikuDisplay* display = new(std::nothrow) HaikuDisplay;
	if (display == NULL || !display->_Init(object)) {
		delete display;
		return NULL;
	}

	return display;
}


void
HaikuDisplay::PushMessage(HaikuMessage* message)
{
	pthread_mutex_lock(&fMutex);

	bool signal = fQueue.IsEmpty();
	fQueue.Add(message);
	if (signal)
		pthread_cond_signal(&fNotEmptyCondition);

	pthread_mutex_unlock(&fMutex);
}


void
HaikuDisplay::RemoveAllMessagesFor(BLooper* target,
	HaikuMessageList& messages)
{
	pthread_mutex_lock(&fMutex);

	for (HaikuMessageList::Iterator it = fQueue.GetIterator();
			HaikuMessage* message = it.Next();) {
		if (message->Window() == target) {
			it.Remove();
			messages.Insert(message);
		}
	}

	pthread_mutex_unlock(&fMutex);
}


bool
HaikuDisplay::HandleNextMessage()
{
	HaikuMessage* message = PopMessage(false);
	if (message == NULL)
		return false;

	HaikuWindow* window = message->Window();
	if (window != NULL) {
		DispatchContext context(fContext);
		fContext = &context;

		if (window->Lock()) {
			window->DispatchDelayedMessage(message);
			window->Unlock();
		}

		fContext = context.fNextContext;
	}

	delete message;
	return true;
}


void
HaikuDisplay::CallbackWidgetFrameMoved(HaikuWidget* widget,
	const BPoint& newPosition)
{
	HaikuJNIContext::CurrentEnv()->CallVoidMethod(fObject,
		fWidgetFrameMovedCallback, widget->Handle(), (jint)newPosition.x,
		(jint)newPosition.y);
}


void
HaikuDisplay::CallbackWidgetFrameResized(HaikuWidget* widget,
	const BSize& newSize)
{
	HaikuJNIContext::CurrentEnv()->CallVoidMethod(fObject,
		fWidgetFrameResizedCallback, widget->Handle(), (jint)newSize.width + 1,
		(jint)newSize.height + 1);
}


bool
HaikuDisplay::CallbackWindowQuitRequested(HaikuWindow* window)
{
	return HaikuJNIContext::CurrentEnv()->CallBooleanMethod(fObject,
		fWindowQuitRequestedCallback, window->Handle());
}


bool
HaikuDisplay::_Init(jobject object)
{
	JNIEnv* env = HaikuJNIContext::CurrentEnv();

	// get a global reference
	fObject = env->NewGlobalRef(object);

	// get the ID of the methods we may call
	jclass clazz = env->FindClass("org/eclipse/swt/widgets/Display");
	if (clazz == NULL)
		return false;

	#define GET_METHOD_ID(variable, name, signature)			\
		variable = env->GetMethodID(clazz, name, signature);	\
		if (variable == NULL)									\
			return false;

	GET_METHOD_ID(fWidgetFrameMovedCallback, "haikuWidgetFrameMoved",
		"(JII)V");
	GET_METHOD_ID(fWidgetFrameResizedCallback, "haikuWidgetFrameResized",
		"(JII)V");
	GET_METHOD_ID(fWindowQuitRequestedCallback, "haikuWindowQuitRequested",
		"(J)Z");

	#undef GET_METHOD_ID

	return true;
}


HaikuMessage*
HaikuDisplay::_GetMessage(bool pop, bool wait)
{
	pthread_mutex_lock(&fMutex);

	HaikuMessage* message = fQueue.Head();
	if (wait) {
		while (message == NULL) {
			pthread_cond_wait(&fNotEmptyCondition, &fMutex);
			message = fQueue.Head();
		}
	}

	if (message != NULL && pop)
		fQueue.Remove(message);

	pthread_mutex_unlock(&fMutex);

	return message;
}


}	// namespace haiku
}	// namespace swt


// #pragma mark - native methods


extern "C" jlong
Java_org_eclipse_swt_internal_haiku_HaikuDisplay_create(
	JNIEnv* env, jobject object, jlong applicationHandle, jobject displayObject)
{
	HAIKU_JNI_ENTER(env);

	HaikuDisplay* display = HaikuDisplay::Create(displayObject);
	haikuJniContext.SetDisplay(display);

	return (jlong)(addr_t)display;
}


extern "C" void
Java_org_eclipse_swt_internal_haiku_HaikuDisplay_delete(
	JNIEnv* env, jobject object, jlong handle)
{
	HAIKU_JNI_ENTER(env);

	haikuJniContext.SetDisplay(NULL);

	HaikuDisplay* display = (HaikuDisplay*)(addr_t)handle;
	delete display;
}


extern "C" jboolean
Java_org_eclipse_swt_internal_haiku_HaikuDisplay_checkPendingEvents(
	JNIEnv* env, jobject object, jlong handle, jboolean wait)
{
	HAIKU_JNI_ENTER(env);

	HaikuDisplay* display = (HaikuDisplay*)(addr_t)handle;
	return display->PeekMessage(wait) != NULL;
}


extern "C" jboolean
Java_org_eclipse_swt_internal_haiku_HaikuDisplay_handleNextEvent(
	JNIEnv* env, jobject object, jlong handle)
{
	HAIKU_JNI_ENTER(env);

	HaikuDisplay* display = (HaikuDisplay*)(addr_t)handle;
	return display->HandleNextMessage();
}
