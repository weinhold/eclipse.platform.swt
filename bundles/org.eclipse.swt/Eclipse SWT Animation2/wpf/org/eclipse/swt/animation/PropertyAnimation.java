package org.eclipse.swt.animation;

import java.lang.reflect.*;

import org.eclipse.swt.*;
import org.eclipse.swt.effects.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.wpf.*;
import org.eclipse.swt.widgets.*;

public class PropertyAnimation extends Animation {
	double from, to;
	float[] transformTo, transformFrom;
	Color colorTo, colorFrom;
	String name, property;
	Object target;
	Method method;
	Class paramType;
	int animatorHandle, customAnimation;
	double decelerationRatio;
	double accelerationRatio;
	Object nextValue;
	IInterpolator interpolator;
		
	void create() {
		if (target == null || property == null) SWT.error(SWT.ERROR_INVALID_ARGUMENT);
		if (handle == 0) {
			super.create();
			setParamType();
			createAnimation();
			setTargetProperty();
			register(widget);
		}
		updateFromToValues();
		updateAccelRatios();
	}

	void createCustomAnimation() {
		customAnimation = OS.gcnew_SWTAnimation(jniRef);
		int children = OS.TimelineGroup_Children(handle);
		OS.IList_Add(children, customAnimation);
		OS.GCHandle_Free(children);
	}
	
	void createDoubleAnimation() {
	}

	void createIntegerAnimation() {
	}
	
	void OnPropertyChanged(int object, int args) {
		try {
			if (nextValue != null) {
				method.invoke(target, new Object[] {nextValue});
				return;
			}
			if (paramType == Double.TYPE
					|| paramType == Color.class
					|| paramType == Transform.class) {
				double newValue = OS.DependencyPropertyChangedEventArgs_NewValueDouble(args);
				Object methodArg;
				if (paramType == Transform.class) {
					Transform transform = getTransform(newValue);
					methodArg = transform;
				} else if (paramType == Color.class) {
					Color color = getColor(newValue);
					methodArg = color;
				} else {
					methodArg = new Double(newValue);
				}
				method.invoke(target, new Object[] {methodArg});
				if (paramType == Transform.class) {
					((Transform)methodArg).dispose();
				}
				return;
			} 
			if (paramType == Integer.TYPE) {
				int newValue = OS.DependencyPropertyChangedEventArgs_NewValueInt(args);
				method.invoke(target, new Object[] {new Integer(newValue)});
				return;
			} 
			throw new RuntimeException(paramType + " not supported yet");
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public double getAccelerationRatio() {
		checkAnimation();
		return accelerationRatio;
	}
	
	Color getColor(double newValue) {
		RGB start = colorFrom.getRGB();
		RGB end = colorTo.getRGB();
		int red = (int) (((end.red-start.red)*newValue) + start.red);
		int green = (int) (((end.green-start.green)*newValue) + start.green);
		int blue = (int) (((end.blue-start.blue)*newValue) + start.blue);
		RGB current = new RGB(red, green, blue);
		return new Color(colorFrom.getDevice(), current);
	}

	void GetCurrentValueCore(double currentTime) {
		if (interpolator != null) {
			try {
				nextValue = interpolator.getCurrentValue(new Double(from), new Double(to), (long)currentTime, duration);
				return;
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		nextValue = null;
	}
	
	Transform getTransform(double newValue) {
		Control control = (Control)target;
		Transform transform = control.getTransform();
		float[] f = new float[6];
		for (int i = 0; i < 6; i++) {
			f[i] = (float) (((transformTo[i]-transformFrom[i])*newValue) + transformFrom[i]);
		}
		transform.setElements(f[0], f[1], f[2], f[3], f[4], f[5]);
		control.setTransform(transform); 
		return transform;
	}

	void register(Widget widget) {
		if (name == null) {
			int nameScope = OS.NameScope_GetNameScope(widget.handle);
			count ++;
			name = "SWTAnimRef" + count;
			int str = createDotNetString(name);
			int targetHandle = targetHandle();
			OS.NameScope_RegisterName(nameScope, str, targetHandle);
			OS.Storyboard_SetTargetName(handle, str);
			OS.GCHandle_Free(str);
			OS.GCHandle_Free(nameScope);
		}
	}
	
	void release() {
		super.release();
		if (customAnimation != 0) OS.GCHandle_Free(customAnimation);
		customAnimation = 0;
		if (animatorHandle != 0) OS.GCHandle_Free(animatorHandle);
		animatorHandle = 0;
	}

	public void setAccelerationRatio(double ratio) {
		checkAnimation();
		accelerationRatio = ratio;
	}

	public void setDecelerationRatio(double ratio) {
		checkAnimation();
		decelerationRatio = ratio;
	}

	public void setFrom(Color from) {
		checkAnimation();
		colorFrom = from;
		this.to = 1;
		this.from = 0;
	}
	
	public void setFrom(double from) {
		checkAnimation();
		this.from = from;
	}
	
	public void setFrom(Transform from) {
		checkAnimation();
		transformFrom = new float[6];
		from.getElements(transformFrom);
		this.to = 1;
		this.from = 0;
	}

	public void setInterpolator(IInterpolator interpolator) {
		checkAnimation();
		this.interpolator = interpolator;
	}
	
	void setParamType() {
		if (interpolator == null) {
			// if using custom interpolation we cannot use OS to set property.
			paramType = Properties.getParamType(property);
		}
		if (paramType != null) return;
		String mName = "set" + property.substring(0, 1).toUpperCase() + property.substring(1);
		Class clazz = target.getClass();
		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];
			if (m.getName().equals(mName)) {
				Class[] parameterTypes = m.getParameterTypes();
				if (parameterTypes.length == 1) {
					method = m;
					break;
				}
			}
		}
//		if (method == null) error?
		paramType = method.getParameterTypes()[0];
		animatorHandle = OS.gcnew_SWTAnimator(jniRef);
	}
	
	public void setProperty(String property) {
		checkAnimation();
		this.property = property;
	}
	
	public void setTarget(Object target) {
		checkAnimation();
		this.target = target;
	}

	void setTargetProperty() {
		int dp = getDependencyProperty();
		int propertyPath = OS.gcnew_PropertyPath(dp);
		OS.Storyboard_SetTargetProperty(handle, propertyPath);
		OS.GCHandle_Free(propertyPath);
		OS.GCHandle_Free(dp);		
	}

	public double getDecelerationRatio() {
		checkAnimation();
		return decelerationRatio;
	}

	int getDependencyProperty() {
		if (interpolator != null) {
			return OS.SWTAnimator_DoubleValueProperty();
		}
		int dp = Properties.getDependencyProperty(target, property);
		if (dp == 0) {
			if (paramType == null
					|| paramType == Double.TYPE
					|| paramType == Color.class
					|| paramType == Transform.class) {
				dp = OS.SWTAnimator_DoubleValueProperty();
			} else if (paramType == Integer.TYPE) {
				dp = OS.SWTAnimator_IntValueProperty();
			} else {
				throw new RuntimeException(paramType + " is not supported yet.");
			}
		}
		return dp;
	}

	void createAnimation() {
		if (interpolator != null) {
			createCustomAnimation();
			return;
		}
		if (paramType == Double.TYPE
				|| paramType == Color.class
				|| paramType == Transform.class) {
			createDoubleAnimation();
			return;
		} 
		if (paramType == Integer.TYPE) {
			createIntegerAnimation();
			return;
		} 
		throw new RuntimeException(paramType + " is not supported yet.");
	}

	public void setTo(Color to) {
		checkAnimation();
		colorTo = to;
		this.to = 1;
		this.from = 0;
	}
	
	public void setTo(double to) {
		checkAnimation();
		this.to = to;
	}

	public void setTo(Transform to) {
		checkAnimation();
		transformTo = new float[6];
		to.getElements(transformTo);
		this.to = 1;
		this.from = 0;
	}
	
	int targetHandle() {
		if (animatorHandle != 0) return animatorHandle;
		if (target instanceof Widget) {
			return ((Widget)target).handle;
		}
		if (target instanceof Effect) {
			return ((Effect)target).handle;
		}
		return 0;
	}

	private void updateAccelRatios() {
		OS.Timeline_AccelerationRatio(handle, accelerationRatio);
		OS.Timeline_DecelerationRatio(handle, decelerationRatio);
	}
	
	long updateDuration(long delay) {
		if (interpolator == null) return delay;
		int timeSpan = OS.TimeSpan_FromMilliseconds(duration);
		int dur = OS.gcnew_Duration(timeSpan);
		OS.Timeline_Duration(customAnimation, dur);
		OS.GCHandle_Free(dur);
		OS.GCHandle_Free(timeSpan);
		//set begin time
		timeSpan = OS.TimeSpan_FromMilliseconds(delay + beginTime);
		OS.Timeline_BeginTime(customAnimation, timeSpan);
		OS.GCHandle_Free(timeSpan);		
		return delay+beginTime+duration;
	}
	
	void updateFromToValues() {
		OS.DoubleAnimation_From(customAnimation, 0);
		OS.DoubleAnimation_To(customAnimation, 1);
	}
}
