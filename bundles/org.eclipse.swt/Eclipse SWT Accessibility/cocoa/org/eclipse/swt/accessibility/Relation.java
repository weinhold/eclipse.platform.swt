/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.cocoa.NSMutableArray;
import org.eclipse.swt.internal.cocoa.NSObject;
import org.eclipse.swt.internal.cocoa.NSPoint;
import org.eclipse.swt.internal.cocoa.NSRect;
import org.eclipse.swt.internal.cocoa.NSString;
import org.eclipse.swt.internal.cocoa.NSView;
import org.eclipse.swt.internal.cocoa.OS;
import org.eclipse.swt.internal.cocoa.id;

public class Relation {
	Accessible accessible;
	Accessible[] targets;
	int type;

	Relation(Accessible accessible, int type) {
		this.accessible = accessible;
		this.type = type;
		this.targets = new Accessible[0];
	}

	void addTarget(Accessible target) {
		Accessible[] newTargets = new Accessible[targets.length + 1];
		System.arraycopy(targets, 0, newTargets, 0, targets.length);
		newTargets[targets.length] = target;
		targets = newTargets;
	}

	void removeTarget(Accessible target) {
		Accessible[] newTargets = new Accessible[targets.length - 1];
		int j = 0;
		for (int i = 0; i < targets.length; i++) {
			if (targets[i] != target) {
				newTargets[j++] = targets[i];
			}
		}
		targets = newTargets;
	}

	id getTitleUIElement() {
		id result = null;
		for (int i = 0; i < targets.length; i++) {
			Accessible target = targets[i];			
			NSView view = target.control.view;
			int /*long*/ handle = OS.objc_msgSend(view.id, OS.sel_accessibleHandle);
			result = new id(handle);
			String string = new NSString(OS.objc_msgSend(result.id, OS.sel_className)).getString();
			System.out.println(string);
//			NSRect bounds = view.frame();
//			NSPoint point = new NSPoint();
//			point.x = bounds.width/2 + bounds.x;
//			point.y = bounds.height/2 + bounds.y;
//			NSView hitTest = view.hitTest(point);
//			result = hitTest;
//			result = view;
//			result = view; //focusView()???
			//result = accessibleElement.accessibilityAttributeValue(OS.NSAccessibilityTitleUIElementAttribute, null);
			System.out.println(accessible.control + " title UI element is: " + target.control);
		}
		return result;
	}

	id getServesAsTitleForUIElements() {
		NSMutableArray result = NSMutableArray.arrayWithCapacity(targets.length);
		for (int i = 0; i < targets.length; i++) {
			Accessible target = targets[i];			
			NSObject accessibleElement = target.control.view;
			result.addObject(accessibleElement);
			System.out.println(accessible.control + " serves as title for: " + target.control);
		}
		return result;
	}

	id getLinkedUIElements() {
		NSMutableArray result = NSMutableArray.arrayWithCapacity(targets.length);
		for (int i = 0; i < targets.length; i++) {
			Accessible target = targets[i];			
			NSObject accessibleElement = target.control.view;
			result.addObject(accessibleElement);
			System.out.println(accessible.control + " linked to: " + target.control);
		}
		return result;
	}
}
