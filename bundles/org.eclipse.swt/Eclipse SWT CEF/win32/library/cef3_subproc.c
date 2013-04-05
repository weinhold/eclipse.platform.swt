/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *******************************************************************************/
 
#include "swt.h"
#include "cef3_structs.h"
#include "cef3_stats.h"
#include <cef_app_capi.h>
#include <cef_base_capi.h>
#include <cef_browser_capi.h>
#include <cef_frame_capi.h>
#include <cef_render_process_handler_capi.h>
#include <cef_process_message_capi.h>
#include <cef_request_capi.h>
#include <cef_values_capi.h>
#include <cef_v8_capi.h>
#include <Windows.h>

	cef_render_process_handler_t renderProcessHandler;
	cef_v8context_t* v8Context = NULL;
	unsigned int appRefCount = 1, handlerRefCount = 1;
	int haveNavigated = 0;
	HANDLE hSharedFile = 0;
	wchar_t ipcFileName[64];

	static const int RESPONSE_TIMEOUT = 3000;
	static const int SHARED_MEMORY_MAXSIZE = 0xFFFE;
	static const char* SHARED_MEMORY_NAME = "org.eclipse.swt.browser.CEF3.ipc";
	static const int SLEEP_INTERVAL = 50;

	static const wchar_t* STRING_NULL = L"null";

	/* IPC message names (ingoing) */
	static const wchar_t* MSG_evaluate = L"evaluate";

	/* IPC message names (outgoing) */
	static const wchar_t* MSG_dispose_ipc = L"dispose_ipc";
	static const wchar_t* MSG_init_ipc = L"init_ipc";
	static const wchar_t* MSG_on_before_navigation = L"on_before_navigation";

/* forward declarations */

int sendMessage(cef_browser_t* browser, cef_process_message_t* message, int timeout, wchar_t** response);
int sendResponse(cef_string_t string);

/* cef_base_t function implementations */

int (CEF_CALLBACK add_ref)(struct _cef_base_t* self) {
	if (self == &(renderProcessHandler.base)) {
		return InterlockedIncrement(&handlerRefCount);
	}
	return InterlockedIncrement(&appRefCount);
}

int (CEF_CALLBACK release)(struct _cef_base_t* self) {
	if (self == &(renderProcessHandler.base)) {
		return InterlockedDecrement(&handlerRefCount);
	}
	return InterlockedDecrement(&appRefCount);
}

int (CEF_CALLBACK get_refct)(struct _cef_base_t* self) {
	if (self == &(renderProcessHandler.base)) {
		return handlerRefCount;
	}
	return appRefCount;
}

/* cef_render_process_handler_t function implementations */

int (CEF_CALLBACK on_before_navigation)(
	struct _cef_render_process_handler_t* self,
	struct _cef_browser_t* browser, struct _cef_frame_t* frame,
	struct _cef_request_t* request,
	enum cef_navigation_type_t navigation_type, int is_redirect) {

	cef_process_message_t* message;
	cef_list_value_t* argsList;
	cef_string_t string;
	cef_string_userfree_t url;
	int result = 0; /* default: let the navigation proceed */
	wchar_t* response;

	/*
	 * Always let the navigation proceed if this is the first navigate for this
	 * process (see http://code.google.com/p/chromiumembedded/issues/detail?id=926).
	 */
	if (haveNavigated) {
		/* create message to send to browser process */
		memset(&string, 0, sizeof(cef_string_t));
		cef_string_set(MSG_on_before_navigation, wcslen(MSG_on_before_navigation), &string, 1);
		message = cef_process_message_create(&string);
		argsList = message->get_argument_list(message);
		cef_string_clear(&string);
		cef_string_set(ipcFileName, wcslen(ipcFileName), &string, 1);
		argsList->set_string(argsList, 0, &string);
		url = request->get_url(request);
		argsList->set_string(argsList, 1, url);
		cef_string_userfree_free(url);

		if (sendMessage(browser, message, RESPONSE_TIMEOUT, &response)) {
			result = response[0] == '0';
			free(response);
		}
		message->base.release(&message->base);
	}

	browser->base.release(&browser->base);
	frame->base.release(&frame->base);
	request->base.release(&request->base);
	haveNavigated = 1;
	return result;
}

void (CEF_CALLBACK on_browser_created)(struct _cef_render_process_handler_t* self, struct _cef_browser_t* browser) {
	cef_process_message_t* message;
	cef_list_value_t* argsList;
	cef_string_t string;
	char lpName[64];

	/* create shared file store */
	sprintf_s(lpName, 64, "%s.%d", SHARED_MEMORY_NAME, GetCurrentProcessId());
	hSharedFile = CreateFileMapping(INVALID_HANDLE_VALUE, 0, PAGE_READWRITE, 0, SHARED_MEMORY_MAXSIZE, lpName);
	if (hSharedFile) {
		swprintf(ipcFileName, 64, L"%hs", lpName);

		/* send message to browser process */
		memset(&string, 0, sizeof(cef_string_t));
		cef_string_set(MSG_init_ipc, wcslen(MSG_init_ipc), &string, 1);
		message = cef_process_message_create(&string);
		argsList = message->get_argument_list(message);
		cef_string_clear(&string);
		cef_string_set(ipcFileName, wcslen(ipcFileName), &string, 1);
		argsList->set_string(argsList, 0, &string);
		argsList->set_string(argsList, 1, &string);
		if (!sendMessage(browser, message, 0, NULL)) {
			CloseHandle(hSharedFile);
			hSharedFile = 0;
		}
		message->base.release(&message->base);
	}

	browser->base.release(&browser->base);
}

void (CEF_CALLBACK on_browser_destroyed)(struct _cef_render_process_handler_t* self, struct _cef_browser_t* browser) {
	cef_process_message_t* message;
	cef_list_value_t* argsList;
	cef_string_t string;
	
	/*
	 * Note this this function currently is invoked but does not run to
	 * completion, see http://code.google.com/p/chromiumembedded/issues/detail?id=930.
	 */
	if (hSharedFile) {
		/* send message to browser process */
		memset(&string, 0, sizeof(cef_string_t));
		cef_string_set(MSG_dispose_ipc, wcslen(MSG_dispose_ipc), &string, 1);
		message = cef_process_message_create(&string);
		argsList = message->get_argument_list(message);
		cef_string_clear(&string);
		cef_string_set(ipcFileName, wcslen(ipcFileName), &string, 1);
		argsList->set_string(argsList, 0, &string);

		sendMessage(browser, message, 0, NULL);
		CloseHandle(hSharedFile);
		hSharedFile = 0;
		message->base.release(&message->base);
	}

	browser->base.release(&browser->base);
}

void (CEF_CALLBACK on_context_created)(
      struct _cef_render_process_handler_t* self,
      struct _cef_browser_t* browser, struct _cef_frame_t* frame,
      struct _cef_v8context_t* context) {

	if (v8Context) {
		v8Context->base.release(&v8Context->base);
	}
	v8Context = context;

	browser->base.release(&browser->base);
	frame->base.release(&frame->base);
}

/* message implementations */

void performEvaluate(struct _cef_browser_t* browser, struct _cef_process_message_t* message) {
	cef_v8value_t* pReturnValue = NULL;
	cef_v8exception_t* pException = NULL;
	cef_string_userfree_t strResult;
	cef_list_value_t* pArgs;
	cef_string_userfree_t evalString;
	cef_string_t nullString;

	if (v8Context && v8Context->is_valid(v8Context)) {
		pArgs = message->get_argument_list(message);
		evalString = pArgs->get_string(pArgs, 0);

		if (v8Context->eval(v8Context, evalString, &pReturnValue, &pException)) {
			strResult = pReturnValue->get_string_value(pReturnValue);
		} else {
			strResult = pException->get_message(pException);
		}
		sendResponse(*strResult);
		cef_string_userfree_free(strResult);
		cef_string_userfree_free(evalString);
		return;
	}

	/* failure response */
	memset(&nullString, 0, sizeof(cef_string_t));
	cef_string_set(STRING_NULL, wcslen(STRING_NULL), &nullString, 1);
	sendResponse(nullString);
}

/* messaging */

int (CEF_CALLBACK on_process_message_received)(
      struct _cef_render_process_handler_t* self,
      struct _cef_browser_t* browser, enum cef_process_id_t source_process,
      struct _cef_process_message_t* message) {

	cef_string_userfree_t name;
	
	name = message->get_name(message);
	if (wcscmp(name->str, MSG_evaluate) == 0) {
		performEvaluate(browser, message);
	}

	cef_string_userfree_free(name);
	browser->base.release(&browser->base);
	message->base.release(&message->base);
	return 1;
}

int sendMessage(cef_browser_t* browser, cef_process_message_t* message, int timeout, wchar_t** response) {
	LPVOID pMemory = NULL;
	wchar_t buffer[/* SHARED_MEMORY_MAXSIZE / sizeof(wchar_t) */ 0x7FFF];
	int result = 0, total = 0, size;

	if (response) {
		/* clear the ipc file for the anticipated response */
		if (!hSharedFile) {
			return 0; /* response will not be receivable */
		}

		pMemory = MapViewOfFile(hSharedFile, FILE_MAP_WRITE, 0, 0, SHARED_MEMORY_MAXSIZE);
		if (pMemory == 0) {
			return 0; /* response will not be receivable */
		}

		memset(buffer, 0, SHARED_MEMORY_MAXSIZE);
		CopyMemory(pMemory, buffer, SHARED_MEMORY_MAXSIZE);
	}
		
	/* send the message, and wait for the response if required */

	message->base.add_ref(&message->base);
	if (browser->send_process_message(browser, PID_BROWSER, message)) {
		if (!response) {
			return 1; /* done, no response expected, and no pMemory to unmap */
		}
		while (total <= timeout) {
			CopyMemory(buffer, pMemory, SHARED_MEMORY_MAXSIZE);
			if (buffer[0] != 0) {
				break;
			}
			Sleep(SLEEP_INTERVAL);
			total += SLEEP_INTERVAL;
		}
		if (total <= timeout) {
			size = (wcslen(buffer) + 1) * sizeof(wchar_t);
			*response = (wchar_t*)calloc(size, 1);
			memmove(*response, buffer, size);
			result = 1;
		}
	}

	if (pMemory) {
		UnmapViewOfFile(pMemory);
	}
	return result;
}

int sendResponse(cef_string_t string) {
	LPVOID pMemory;
	size_t length;

	if (!hSharedFile) return 0;

	/* ensure that the response string + \0 will fit in the response file */
	length = string.length * sizeof(wchar_t);
	if (length > (SHARED_MEMORY_MAXSIZE - sizeof(wchar_t))) {
		length = SHARED_MEMORY_MAXSIZE - sizeof(wchar_t);
	}

	pMemory = MapViewOfFile(hSharedFile, FILE_MAP_WRITE, 0, 0, length);
	if (!pMemory) return 0;
	CopyMemory(pMemory, string.str, length);
	UnmapViewOfFile(pMemory);
	return 1;
}

/* cef_app_t function implementations */

struct _cef_render_process_handler_t* (CEF_CALLBACK get_render_process_handler)(struct _cef_app_t* self) {
	cef_base_t base;
	if (renderProcessHandler.base.size == 0) {
	 	base.add_ref = add_ref;
 		base.release = release;
	 	base.get_refct = get_refct;
		base.size = sizeof(cef_render_process_handler_t);
 		renderProcessHandler.base = base;
 		renderProcessHandler.on_before_navigation = on_before_navigation;
		renderProcessHandler.on_browser_created = on_browser_created;
		renderProcessHandler.on_browser_destroyed = on_browser_destroyed;
		renderProcessHandler.on_context_created = on_context_created;
		renderProcessHandler.on_process_message_received = on_process_message_received;
 	}
	renderProcessHandler.base.add_ref(&renderProcessHandler.base);
 	return &renderProcessHandler;
}

//int main() {
int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow) {
 	cef_main_args_t args;
 	cef_app_t app;
	cef_base_t base;
	int result;

	// MessageBox (NULL, "starting", "", 0);

	/* initialize fields */
	memset(&renderProcessHandler, 0, sizeof(cef_render_process_handler_t));

	/* initialize "args" */
 	args.instance = hInstance; // GetModuleHandle(NULL);

	/* initialize app */
 	memset(&app, 0, sizeof(cef_app_t));
 	base.add_ref = add_ref;
 	base.release = release;
 	base.get_refct = get_refct;
	base.size = sizeof(cef_app_t);
 	app.base = base;
 	app.get_render_process_handler = get_render_process_handler;

	app.base.add_ref(&app.base);
	result = cef_execute_process(&args, &app);

	/* note: redundant with on_browser_destroyed() */
	if (hSharedFile != 0) {
		CloseHandle(hSharedFile);
		hSharedFile = 0;
	}
	return result;
}
