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

#ifndef INC_SWT_DEFS_H
#define INC_SWT_DEFS_H


#define SWT_NO_BACKGROUND		(1 << 18)
#define SWT_NO_REDRAW_RESIZE	(1 << 20)

#define SWT_LEFT				(1 << 14)
#define SWT_RIGHT				(1 << 17)
#define SWT_UP					(1 << 7)
#define SWT_DOWN				(1 << 10)
#define SWT_CENTER				(1 << 24)

#define	SWT_ERROR_IO					39
#define	SWT_ERROR_INVALID_IMAGE			40
#define	SWT_ERROR_UNSUPPORTED_FORMAT	42

#endif /* INC_SWT_DEFS_H */
