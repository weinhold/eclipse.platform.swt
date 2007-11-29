package org.eclipse.swt.animation;

import java.lang.reflect.*;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.cocoa.*;
import org.eclipse.swt.widgets.*;

public class PropertyAnimation extends Animation {
	Object from, to;
	String name, property;
	Object target;
	Method method;
	Class paramType;
	int animatorHandle, customAnimation;
	Object nextValue;
	IInterpolator interpolator;
	
	CABasicAnimation[] animations;
		
	void create() {
		if (target == null || property == null) SWT.error(SWT.ERROR_INVALID_ARGUMENT);
		
		if (animations == null) {		
			createAnimations();
			setTimingFunction();
		}
		updateFromToValues();
	}

	void createAnimations() {
		NSMutableDictionary dict = (NSMutableDictionary) NSMutableDictionary.dictionaryWithCapacity(2);
		if ("bounds".equalsIgnoreCase(property)) {
			animations = new CABasicAnimation[2];
			NSString keyPath = createNSString("frameOrigin");
			animations[0] = new CABasicAnimation(CABasicAnimation.animationWithKeyPath(keyPath).id);
			animations[0].retain();
			dict.setValue(animations[0], keyPath);
			keyPath = createNSString("frameSize");
			animations[1] = new CABasicAnimation(CABasicAnimation.animationWithKeyPath(keyPath).id);
			animations[1].retain();
			dict.setValue(animations[1], keyPath);
		}
		int id = targetHandle();
		if (id != 0) OS.objc_msgSend(id, OS.sel_setAnimations_1, dict.id);
	}

	void createCustomAnimation() {
		/*
		* On WPF this is where we use a subclass of DoubleAnimation and override
		* GetNextValueCore to provide custom interpolation.
		*/
	}
	
	Color getColor(double newValue) {
		if (!(from instanceof Color && to instanceof Color)) return null;
		Color colorFrom = (Color) from;
		Color colorTo = (Color) to;
		RGB start = colorFrom.getRGB();
		RGB end = colorTo.getRGB();
		int red = (int) (((end.red-start.red)*newValue) + start.red);
		int green = (int) (((end.green-start.green)*newValue) + start.green);
		int blue = (int) (((end.blue-start.blue)*newValue) + start.blue);
		RGB current = new RGB(red, green, blue);
		return new Color(colorFrom.getDevice(), current);
	}
	
	//TODO Control #getTransform, #setTransform()
	Transform getTransform(double newValue) {
		if (!(from instanceof Transform && to instanceof Transform)) return null;
//		Control control = (Control)target;
//		Transform transform = control.getTransform();
//		float[] f = new float[6];
//		for (int i = 0; i < 6; i++) {
//			f[i] = (float) (((transformTo[i]-transformFrom[i])*newValue) + transformFrom[i]);
//		}
//		transform.setElements(f[0], f[1], f[2], f[3], f[4], f[5]);
//		control.setTransform(transform); 
//		return transform;
		return null;
	}
	
	void release() {
		super.release();
		if (animations != null) {
			for (int i = 0; i < animations.length; i++) {
				if (animations[i] != null) animations[i].release();
			}
		}
		animations = null;
	}

	public void setFrom(Object from) {
		checkAnimation();
		this.from = from;
	}
	
	public void setInterpolator(IInterpolator interpolator) {
		checkAnimation();
		this.interpolator = interpolator;
	}
	
	void setParamType() {
//		if (interpolator == null) {
//			// if using custom interpolation we cannot use OS to set property.
//			paramType = Properties.getParamType(property);
//		}
//		if (paramType != null) return;
//		String mName = "set" + property.substring(0, 1).toUpperCase() + property.substring(1);
//		Class clazz = target.getClass();
//		Method[] methods = clazz.getMethods();
//		for (int i = 0; i < methods.length; i++) {
//			Method m = methods[i];
//			if (m.getName().equals(mName)) {
//				Class[] parameterTypes = m.getParameterTypes();
//				if (parameterTypes.length == 1) {
//					method = m;
//					break;
//				}
//			}
//		}
////		if (method == null) error?
//		paramType = method.getParameterTypes()[0];
//		animatorHandle = OS.gcnew_SWTAnimator(jniRef);
	}
	
	public void setProperty(String property) {
		checkAnimation();
		this.property = property;
	}
	
	public void setTarget(Object target) {
		checkAnimation();
		this.target = target;
	}

	NSString createNSString(String path) {
		char[] buffer = new char[path.length()];
		path.getChars(0, buffer.length, buffer, 0);
		NSString keyPath = NSString.stringWithCharacters(buffer, buffer.length);
		return keyPath;
	}

	private void setTimingFunction() {
		int func = OS.objc_msgSend(OS.class_CAMediaTimingFunction, OS.sel_functionWithControlPoints_1_1_1_1, 0, 0, 1, 1);
		for (int i = 0; i < animations.length; i++) {
			OS.object_setInstanceVariable(animations[i].id, "timingFunction", func);
		}
	}
	
	public void setTo(Object to) {
		checkAnimation();
		this.to = to;
	}

	public void start(Widget widget) {
		super.start(widget);
		int id = targetHandle();
		int animator = OS.objc_msgSend(id, OS.sel_animator);
		//FIXME
		Rectangle pt = (Rectangle) to;
		NSRect rect = new NSRect();
		rect.height = pt.height;
		rect.width = pt.width;
		rect.x = pt.x;
		rect.y = pt.y;
		OS.objc_msgSend(animator, OS.sel_setFrame_1, rect);
	}
	
	int targetHandle() {
		/*
		 * probably need some framework here to get us the right handle...
		 * int Widget.animationHandle() ???? 
		 */
		if (target instanceof Control) return ((Control) target).view.id;
		//return fake view???
		return 0;
	}
	
	long updateDuration(long delay) {
		double sec = duration/1000d;
		for (int i = 0; i < animations.length; i++) {
			animations[i].setDuration(sec);
		}
		return delay+beginTime+duration;
	}
	
	void updateFromToValues() {
		if (property.equalsIgnoreCase("bounds")) {
			if (!(from instanceof Rectangle && to instanceof Rectangle)) SWT.error(SWT.ERROR_INVALID_ARGUMENT);
			Rectangle rect = (Rectangle) from;
			NSPoint pt = new NSPoint();
			pt.x = rect.x;
			pt.y = rect.y;
			NSValue val = NSValue.valueWithPoint(pt);
			animations[0].setFromValue(val);
			NSSize size = new NSSize();
			size.height = rect.height;
			size.width = rect.width;
			val = NSValue.valueWithSize(size);
			animations[1].setFromValue(val);
			
			rect = (Rectangle) to;
			pt = new NSPoint();
			pt.x = rect.x;
			pt.y = rect.y;
			val = NSValue.valueWithPoint(pt);
			animations[0].setToValue(val);
			size = new NSSize();
			size.height = rect.height;
			size.width = rect.width;
			val = NSValue.valueWithSize(size);
			animations[1].setToValue(val);
		}
	}
}
