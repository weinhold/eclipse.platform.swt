/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.internal;

import java.util.*;

public abstract class OSObject {
	static boolean IsSolaris;
	static {
		String osName = System.getProperty ("os.name").toLowerCase (); //$NON-NLS-1$
		IsSolaris = osName.startsWith ("sunos") || osName.startsWith("solaris"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private long /*int*/ handle;

	static protected int FailCode = -1;
	static protected int NotImplementedCode = -1;

	static private final int MAX_ARG_COUNT = 12;
	static private final int MAX_VTABLE_LENGTH = 80;
	static private final int OS_OFFSET = IsSolaris ? 2 : 0;
	static protected Callback[][] Callbacks = new Callback[MAX_VTABLE_LENGTH + OS_OFFSET][MAX_ARG_COUNT];

	static private final Hashtable ObjectMap = new Hashtable ();


public OSObject (int[] argCounts) {
	super ();
	handle = create (argCounts, OS_OFFSET);
	ObjectMap.put (new LONG (handle), this);
}

protected abstract long /*int*/ create (int[] argCounts, int os_offset);
protected abstract void release (long /*int*/ handle);

public void dispose () {
	release (handle);
	ObjectMap.remove (new LONG (handle));	
	handle = 0;
}

static long /*int*/ callback0 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method0 (args);
}
static long /*int*/ callback1 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method1 (args);
}
static long /*int*/ callback10 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method10 (args);
}
static long /*int*/ callback11 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method11 (args);
}
static long /*int*/ callback12 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method12 (args);
}
static long /*int*/ callback13 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method13 (args);
}
static long /*int*/ callback14 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method14 (args);
}
static long /*int*/ callback15 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method15 (args);
}
static long /*int*/ callback16 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method16 (args);
}
static long /*int*/ callback17 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method17 (args);
}
static long /*int*/ callback18 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method18 (args);
}
static long /*int*/ callback19 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method19 (args);
}
static long /*int*/ callback2 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method2 (args);
}
static long /*int*/ callback20 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method20 (args);
}
static long /*int*/ callback21 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method21 (args);
}
static long /*int*/ callback22 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method22 (args);
}
static long /*int*/ callback23 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method23 (args);
}
static long /*int*/ callback24 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method24 (args);
}
static long /*int*/ callback25 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method25 (args);
}
static long /*int*/ callback26 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method26 (args);
}
static long /*int*/ callback27 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method27 (args);
}
static long /*int*/ callback28 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method28 (args);
}
static long /*int*/ callback29 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method29 (args);
}
static long /*int*/ callback3 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method3 (args);
}
static long /*int*/ callback30 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method30 (args);
}
static long /*int*/ callback31 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method31 (args);
}
static long /*int*/ callback32 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method32 (args);
}
static long /*int*/ callback33 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method33 (args);
}
static long /*int*/ callback34 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method34 (args);
}
static long /*int*/ callback35 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method35 (args);
}
static long /*int*/ callback36 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method36 (args);
}
static long /*int*/ callback37 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method37 (args);
}
static long /*int*/ callback38 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method38 (args);
}
static long /*int*/ callback39 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method39 (args);
}
static long /*int*/ callback4 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method4 (args);
}
static long /*int*/ callback40 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method40 (args);
}
static long /*int*/ callback41 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method41 (args);
}
static long /*int*/ callback42 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method42 (args);
}
static long /*int*/ callback43 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method43 (args);
}
static long /*int*/ callback44 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method44 (args);
}
static long /*int*/ callback45 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method45 (args);
}
static long /*int*/ callback46 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method46 (args);
}
static long /*int*/ callback47 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method47 (args);
}
static long /*int*/ callback48 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method48 (args);
}
static long /*int*/ callback49 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method49 (args);
}
static long /*int*/ callback5 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method5 (args);
}
static long /*int*/ callback50 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method50 (args);
}
static long /*int*/ callback51 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method51 (args);
}
static long /*int*/ callback52 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method52 (args);
}
static long /*int*/ callback53 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method53 (args);
}
static long /*int*/ callback54 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method54 (args);
}
static long /*int*/ callback55 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method55 (args);
}
static long /*int*/ callback56 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method56 (args);
}
static long /*int*/ callback57 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method57 (args);
}
static long /*int*/ callback58 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method58 (args);
}
static long /*int*/ callback59 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method59 (args);
}
static long /*int*/ callback6 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method6 (args);
}
static long /*int*/ callback60 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method60 (args);
}
static long /*int*/ callback61 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method61 (args);
}
static long /*int*/ callback62 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method62 (args);
}
static long /*int*/ callback63 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method63 (args);
}
static long /*int*/ callback64 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method64 (args);
}
static long /*int*/ callback65 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method65 (args);
}
static long /*int*/ callback66 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method66 (args);
}
static long /*int*/ callback67 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method67 (args);
}
static long /*int*/ callback68 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method68 (args);
}
static long /*int*/ callback69 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method69 (args);
}
static long /*int*/ callback7 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method7 (args);
}
static long /*int*/ callback70 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method70 (args);
}
static long /*int*/ callback71 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method71 (args);
}
static long /*int*/ callback72 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method72 (args);
}
static long /*int*/ callback73 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method73 (args);
}
static long /*int*/ callback74 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method74 (args);
}
static long /*int*/ callback75 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method75 (args);
}
static long /*int*/ callback76 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method76 (args);
}
static long /*int*/ callback77 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method77 (args);
}
static long /*int*/ callback78 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method78 (args);
}
static long /*int*/ callback79 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method79 (args);
}
static long /*int*/ callback8 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method8 (args);
}
static long /*int*/ callback9 (long /*int*/[] callbackArgs) {
	// find the object on which this call was invoked
	long /*int*/ address = callbackArgs[0];
	Object object = ObjectMap.get (new LONG (address));
	if (object == null) return FailCode;
	long /*int*/[] args = new long /*int*/[callbackArgs.length - 1];
	System.arraycopy (callbackArgs, 1, args, 0, args.length);
	return ((OSObject) object).method9 (args);
}

public long /*int*/ getAddress () {
	return handle;
}

public long /*int*/ method0 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method1 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method10 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method11 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method12 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method13 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method14 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method15 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method16 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method17 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method18 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method19 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method2 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method20 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method21 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method22 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method23 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method24 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method25 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method26 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method27 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method28 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method29 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method3 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method30 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method31 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method32 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method33 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method34 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method35 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method36 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method37 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method38 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method39 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method4 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method40 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method41 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method42 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method43 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method44 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method45 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method46 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method47 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method48 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method49 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method5 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method50 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method51 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method52 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method53 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method54 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method55 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method56 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method57 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method58 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method59 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method6 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method60 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method61 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method62 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method63 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method64 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method65 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method66 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method67 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method68 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method69 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method7 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method70 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method71 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method72 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method73 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method74 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method75 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method76 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method77 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method78 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method79 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method8 (long /*int*/[] args) {
	return NotImplementedCode;
}
public long /*int*/ method9 (long /*int*/[] args) {
	return NotImplementedCode;
}
}

