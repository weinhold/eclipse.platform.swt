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

#ifndef INC_HAIKU_IMAGE_H
#define INC_HAIKU_IMAGE_H


#include <SupportDefs.h>

#include "swt.h"


class BBitmap;
class BPositionIO;


namespace swt {
namespace haiku {


class HaikuWindow;


class HaikuImage {
public:
								HaikuImage();
	virtual						~HaikuImage();

			jlong				Handle() const
									{ return (jlong)(addr_t)this; }
	static	HaikuImage*			Get(jlong handle)
									{ return (HaikuImage*)(addr_t)handle; }

			BBitmap*			Bitmap() const
									{ return fBitmap; }

			status_t			Load(BPositionIO& data);

private:
			BBitmap*			fBitmap;
};


}	// namespace haiku
}	// namespace swt


#endif /* INC_HAIKU_IMAGE_H */
