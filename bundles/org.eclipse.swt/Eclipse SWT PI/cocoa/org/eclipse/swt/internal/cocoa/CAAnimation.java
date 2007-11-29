package org.eclipse.swt.internal.cocoa;

public class CAAnimation extends NSObject {

public CAAnimation() {
	super();
}

public CAAnimation(int id) {
	super(id);
}

public static id animation() {
	int result = OS.objc_msgSend(OS.class_CAAnimation, OS.sel_animation);
	return result != 0 ? new id(result) : null;
}

public static id defaultValueForKey(NSString key) {
	int result = OS.objc_msgSend(OS.class_CAAnimation, OS.sel_defaultValueForKey_1, key != null ? key.id : 0);
	return result != 0 ? new id(result) : null;
}

public id delegate() {
	int result = OS.objc_msgSend(this.id, OS.sel_delegate);
	return result != 0 ? new id(result) : null;
}

public boolean isRemovedOnCompletion() {
	return OS.objc_msgSend(this.id, OS.sel_isRemovedOnCompletion) != 0;
}

public void setDuration(double duration) {
	OS.objc_msgSend(id, OS.sel_setDuration_1, duration);	
}

public void setDelegate(id delegate) {
	OS.objc_msgSend(this.id, OS.sel_setDelegate_1,  delegate!= null ? delegate.id : 0);
}

public void setRemovedOnCompletion(boolean remove) {
	OS.objc_msgSend(this.id, OS.sel_setRemovedOnCompletion_1, remove);
}

public boolean shouldArchiveValueForKey(NSString key) {
	return OS.objc_msgSend(this.id, OS.sel_shouldArchiveValueForKey_1, key != null ? key.id : 0) != 0;
}

}
