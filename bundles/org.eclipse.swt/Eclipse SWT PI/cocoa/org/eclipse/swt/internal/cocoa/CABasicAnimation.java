package org.eclipse.swt.internal.cocoa;

public class CABasicAnimation extends CAPropertyAnimation {
	
	public CABasicAnimation(int id) {
		super(id);
	}
	
	public static id animationWithKeyPath(NSString path) {
		int result = OS.objc_msgSend(OS.class_CABasicAnimation, OS.sel_animationWithKeyPath_1, path != null ? path.id : 0);
		return result != 0 ? new id(result) : null;
	}
	
	public id fromValue() {
		int result = OS.objc_msgSend(id, OS.sel_fromValue);
		return result != 0 ? new id(result) : null;
	}
	
	public void setFromValue(id value) {
		OS.objc_msgSend(id, OS.sel_setFromValue_1,  value.id);
	}
	
	public id toValue() {
		int result = OS.objc_msgSend(id, OS.sel_toValue);
		return result != 0 ? new id(result) : null;
	}
	
	public void setToValue(id value) {
		OS.objc_msgSend(id, OS.sel_setToValue_1,  value.id);
	}
	
	public id byValue() {
		int result = OS.objc_msgSend(id, OS.sel_byValue);
		return result != 0 ? new id(result) : null;
	}
	
	public void setByValue(id value) {
		OS.objc_msgSend(id, OS.sel_setByValue_1,  value.id);
	}
}
