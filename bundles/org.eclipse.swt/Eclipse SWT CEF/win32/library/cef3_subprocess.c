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
 
 //#include <windows.h>
//#include <stdio.h>
#include "swt.h"
#include "cef3_structs.h"
#include "cef3_stats.h"
#include <cef_app_capi.h>


int main(int argc, char **argv, char **envp) {
 	cef_main_args_t args;
 	args.instance = GetModuleHandle("cef3_subprocess.exe");
// 	MessageBox(0, "in main(void)", 0, MB_OK);
	return cef_execute_process(&args, NULL);
}
