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
#include <Windows.h>

	cef_render_process_handler_t renderProcessHandler;
	unsigned int appRefCount = 1, handlerRefCount = 1;
	HANDLE hSharedFile = 0;

	static int RESPONSE_TIMEOUT = 3000;
	static char* SHARED_MEMORY_NAME = "org.eclipse.swt.browser.CEF3.ipc";
	static int SHARED_MEMORY_MAXSIZE = 0x7FFFFFFF;
	static int SLEEP_INTERVAL = 50;

	/* IPC message names */
	static wchar_t* MSG_init_ipc = L"init_ipc";
	static wchar_t* MSG_on_before_navigation = L"on_before_navigation";

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
	int result = 0;
	LPVOID pMemory;
	int total = 0;
	char buffer[1];
	static int RESULT_SIZE = 1;

	/*
	 * If browser process cannot respond to the navigation request then always let it proceed.
	 */
	if (hSharedFile) {
		pMemory = MapViewOfFile(hSharedFile, FILE_MAP_WRITE, 0, 0, RESULT_SIZE);
		if (pMemory) {
			/* clear the portion of the shared memory that will be read */
			memset(buffer, 0, RESULT_SIZE);
			CopyMemory(pMemory, buffer, RESULT_SIZE);

			/* create message to send to browser process */
			memset(&string, 0, sizeof(cef_string_t));
			cef_string_set(MSG_on_before_navigation, wcslen(MSG_on_before_navigation), &string, 1);
			message = cef_process_message_create(&string);
			argsList = message->get_argument_list(message);
			url = request->get_url(request);
			argsList->set_string(argsList, 0, url);
			cef_string_userfree_free(url);

			message->base.add_ref(&message->base);
			if (browser->send_process_message(browser, PID_BROWSER, message)) {
				while (total <= RESPONSE_TIMEOUT) {
					CopyMemory(buffer, pMemory, RESULT_SIZE);
					if (buffer[0] != 0) {
						break;
					}
					Sleep(SLEEP_INTERVAL);
					total += SLEEP_INTERVAL;
				}
				result = buffer[0] == '0' ? 1 : 0;
			} else {
			}
			message->base.release(&message->base);
			UnmapViewOfFile(pMemory);
		}
	}

	browser->base.release(&browser->base);
	frame->base.release(&frame->base);
	request->base.release(&request->base);
	return result;
}

void (CEF_CALLBACK on_browser_created)(struct _cef_render_process_handler_t* self, struct _cef_browser_t* browser) {
	cef_process_message_t* message;
	cef_list_value_t* argsList;
	cef_string_t string;
	char fileName[64];
	wchar_t messageArg[64];

	/* create shared file store */
	sprintf_s(fileName, 64, "%s.%d", SHARED_MEMORY_NAME, GetCurrentProcessId());
	hSharedFile = CreateFileMapping(INVALID_HANDLE_VALUE, 0, PAGE_READWRITE, 0, SHARED_MEMORY_MAXSIZE, fileName);
	if (hSharedFile != 0) {
		/* send message to browser process */
		memset(&string, 0, sizeof(cef_string_t));
		cef_string_set(MSG_init_ipc, wcslen(MSG_init_ipc), &string, 1);
		message = cef_process_message_create(&string);
		argsList = message->get_argument_list(message);
		cef_string_clear(&string);
		swprintf(messageArg, 64, L"%hs", fileName);
		cef_string_set(messageArg, wcslen(messageArg), &string, 1);
		argsList->set_string(argsList, 0, &string);

		message->base.add_ref(&message->base);
		if (!browser->send_process_message(browser, PID_BROWSER, message)) {
			CloseHandle(hSharedFile);
			hSharedFile = 0;
		}
		message->base.release(&message->base);
	}

	browser->base.release(&browser->base);
}

int (CEF_CALLBACK on_process_message_received)(
      struct _cef_render_process_handler_t* self,
      struct _cef_browser_t* browser, enum cef_process_id_t source_process,
      struct _cef_process_message_t* message) {

	printf("on_process_message_received\n");
	browser->base.release(&browser->base);
	message->base.release(&message->base);
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

	// MessageBox (NULL, L"starting", L"", 0);

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

	if (hSharedFile != 0) {
		CloseHandle(hSharedFile);
	}
	return result;
}
