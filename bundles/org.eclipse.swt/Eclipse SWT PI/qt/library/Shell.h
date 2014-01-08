/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others. All rights reserved.
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

#ifndef INC_SHELL_H
#define INC_SHELL_H


#include <QMainWindow>

#include "SwtQWidgetComposite.h"


namespace swt {
namespace qt {


class Shell : public SwtQWidgetComposite<QMainWindow> {
public:
			typedef SwtQWidgetComposite<QWidget> CentralWidget;

public:
								Shell();
								~Shell();

	virtual	void				closeEvent(QCloseEvent* event);

	static	Shell*				get(jintLong handle)
									{ return getAs<Shell>(handle); }

			CentralWidget*		swtGetCentralWidget() const
									{ return fCentralWidget; }

	// Widget interface
	virtual	void				swtDelete();

	// Control interface
	virtual	QWidget*			swtGetQWidget();

	virtual	void				swtSetStyle(jint style);

	// Composite interface
	virtual	void				swtAddChild(Control* child);
	virtual	void				swtRemoveChild(Control* child);
	virtual	QList<Control*>		swtGetChildren();

private:
			CentralWidget*		fCentralWidget;
			bool				fDeleted;
};


}	// namespace qt
}	// namespace swt


#endif /* INC_SHELL_H */
