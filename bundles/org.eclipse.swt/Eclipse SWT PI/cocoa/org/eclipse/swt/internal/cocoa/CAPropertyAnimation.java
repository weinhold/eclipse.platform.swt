package org.eclipse.swt.internal.cocoa;

public class CAPropertyAnimation extends CAAnimation {

public CAPropertyAnimation() {
	super();
}

public CAPropertyAnimation(int id) {
	super(id);
}

public static id animationWithKeyPath(NSString path) {
	int result = OS.objc_msgSend(OS.class_CAPropertyAnimation, OS.sel_animationWithKeyPath_1, path != null ? path.id : 0);
	return result != 0 ? new id(result) : null;
}

public boolean isAdditive() {
	return OS.objc_msgSend(this.id, OS.sel_isAdditive) != 0;
}

public boolean isCumulative() {
	return OS.objc_msgSend(this.id, OS.sel_isCumulative) != 0;
}

public void setAdditive(boolean additive) {
	OS.objc_msgSend(this.id, OS.sel_setAdditive_1, additive);
}

public void setCumulative(boolean cumulative) {
	OS.objc_msgSend(this.id, OS.sel_setCumulative_1, cumulative);
}

}
