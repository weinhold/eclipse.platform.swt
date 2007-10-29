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
	int animatorHandle;
	protected double decelerationRatio;
	protected double accelerationRatio;
	
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

	void createDoubleAnimation() {
	}

	void createIntegerAnimation() {
	}
	
	void OnPropertyChanged(int object, int args) {
		try {
			if (paramType == Double.TYPE
					|| paramType == Color.class
					|| paramType == Transform.class) {
				double newValue = OS.DependencyPropertyChangedEventArgs_NewValueDouble(args);
				Object[] methodArg = new Object[1];
				if (paramType == Transform.class) {
					Transform transform = getTransform(newValue);
					methodArg[0] = transform;
				} else if (paramType == Color.class) {
					Color color = getColor(newValue);
					methodArg[0] = color;
				} else {
					methodArg[0] = new Double(newValue);
				}
				method.invoke(target, methodArg);
				if (paramType == Transform.class) {
					((Transform)methodArg[0]).dispose();
				}
			} else if (paramType == Integer.TYPE) {
				int newValue = OS.DependencyPropertyChangedEventArgs_NewValueInt(args);
				method.invoke(target, new Object[] {new Integer(newValue)});
			} else {
				throw new RuntimeException(paramType.getName() + " is not supported yet.");
			}
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
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
		if (animatorHandle != 0) OS.GCHandle_Free(animatorHandle);
		animatorHandle = 0;
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

	void setParamType() {
		paramType = Properties.getParamType(property);
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
		if (method == null) SWT.error(SWT.ERROR_INVALID_ARGUMENT);
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

	int getDependencyProperty() {
		int dp = Properties.getDependencyProperty(target, property);
		if (dp == 0) {
			if (paramType == Double.TYPE
					|| paramType == Color.class
					|| paramType == Transform.class) {
				dp = OS.SWTAnimator_DoubleValueProperty();
			} else if (paramType == Integer.TYPE) {
				dp = OS.SWTAnimator_IntValueProperty();
			} else {
				throw new RuntimeException(paramType.getName() + " is not supported yet.");
			}
		}
		return dp;
	}

	void createAnimation() {
		if (paramType == Double.TYPE
				|| paramType == Color.class
				|| paramType == Transform.class) {
			createDoubleAnimation();
		} else if (paramType == Integer.TYPE) {
			createIntegerAnimation();
		} else {
			throw new RuntimeException(paramType.getName() + " is not supported yet.");
		}
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
	
	long updateDuration(long delay) {
		return delay;
	}
	
	void updateFromToValues() {
	}

	public double getAccelerationRatio() {
		checkAnimation();
		return accelerationRatio;
	}

	public double getDecelerationRatio() {
		checkAnimation();
		return decelerationRatio;
	}

	public void setAccelerationRatio(double ratio) {
		checkAnimation();
		accelerationRatio = ratio;
	}

	public void setDecelerationRatio(double ratio) {
		checkAnimation();
		decelerationRatio = ratio;
	}

	private void updateAccelRatios() {
		OS.Timeline_AccelerationRatio(handle, accelerationRatio);
		OS.Timeline_DecelerationRatio(handle, decelerationRatio);
	}
}
