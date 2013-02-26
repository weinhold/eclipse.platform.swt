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

int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow) {
 	cef_main_args_t args;
 	args.instance = hInstance;
	return cef_execute_process(&args, NULL);
}
