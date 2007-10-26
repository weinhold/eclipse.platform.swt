package org.eclipse.swt.animation;

import org.eclipse.swt.effects.*;
import org.eclipse.swt.internal.wpf.*;
import org.eclipse.swt.widgets.*;

class Properties {

	static Class getParamType(String property) {
		if (property.equalsIgnoreCase("width")
				|| property.equalsIgnoreCase("height")
				|| property.equalsIgnoreCase("x")
				|| property.equalsIgnoreCase("y")
				|| property.equalsIgnoreCase("Size")) {
			return Double.TYPE;
		}
		return null;
	}
	
	static int getDependencyProperty(Object target, String property) {
		if (property.equalsIgnoreCase("x")) return OS.Canvas_LeftProperty();
		if (property.equalsIgnoreCase("y")) return OS.Canvas_TopProperty();
		if (property.equalsIgnoreCase("Width")) {
			if (target instanceof Composite) {
				if (!(target instanceof Group 
						|| target instanceof Spinner 
						|| target instanceof TabFolder
						|| target instanceof Table
						|| target instanceof Tree)) {
					return OS.Panel_WidthProperty();
				}
			}
			return OS.FrameworkElement_WidthProperty();
		}
		if (property.equalsIgnoreCase("Height")) {
			if (target instanceof Composite) {
				if (!(target instanceof Group 
						|| target instanceof Spinner 
						|| target instanceof TabFolder
						|| target instanceof Table
						|| target instanceof Tree)) {
					return OS.Panel_HeightProperty();
				}
			}
			return OS.FrameworkElement_HeightProperty();
		}
		if (property.equalsIgnoreCase("Size")) {
			if (target instanceof GlowEffect) return OS.OuterGlowBitmapEffect_GlowSizeProperty();
		}
		return 0;
	}
}
