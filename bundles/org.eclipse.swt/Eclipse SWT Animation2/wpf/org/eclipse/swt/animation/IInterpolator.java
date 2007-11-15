package org.eclipse.swt.animation;

public interface IInterpolator {
	public Object getCurrentValue(Object from, Object to, long currentTime, long duration);
}
