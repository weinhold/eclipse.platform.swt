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

#ifndef INC_SWT_QWIDGET_COMPOSITE_H
#define INC_SWT_QWIDGET_COMPOSITE_H


#include "Composite.h"
#include "SwtQWidgetControl.h"


namespace swt {
namespace qt {


template<typename WidgetClass, typename CompositeClass = Composite>
class SwtQWidgetComposite : public SwtQWidgetControl<WidgetClass, CompositeClass> {
public:
	template<typename... Arguments>
	SwtQWidgetComposite(const Arguments... arguments)
		:
		SwtQWidgetControl<WidgetClass, CompositeClass>(arguments...)
	{
	}

	static SwtQWidgetComposite* get(jintLong handle)
	{
		return Widget::getAs<SwtQWidgetComposite>(handle);
	}

	virtual void swtAddChild(Control* child)
	{
		child->swtGetQWidget()->setParent(this);
	}

	virtual void swtRemoveChild(Control* child)
	{
		QWidget* childWidget = child->swtGetQWidget();
		if (childWidget->parent() == this)
			childWidget->setParent(NULL);
	}

	virtual QList<Control*> swtGetChildren()
	{
		QList<Control*> children;
		foreach (QObject* child, this->children()) {
			if (Control* control = dynamic_cast<Control*>(child))
				children.append(control);
		}
		return children;
	}
};


}	// namespace qt
}	// namespace swt


#endif /* INC_SWT_QWIDGET_COMPOSITE_H */
