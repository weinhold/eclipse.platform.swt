package org.eclipse.swt.internal.cocoa;

public class CAMediaTimingFunction extends NSObject {

public CAMediaTimingFunction() {
	super();
}

public CAMediaTimingFunction(int id) {
	super(id);
}

public static id functionWithControlPoints(float c1x, float c1y, float c2x, float c2y) {
	int result = OS.objc_msgSend(OS.class_CAMediaTimingFunction, OS.sel_functionWithControlPoints_1_1_1_1, c1x, c1y, c2x, c2y);
	return result != 0 ? new id(result) : null;
}

public static id functionWithName(NSString name) {
	int result = OS.objc_msgSend(OS.class_CAMediaTimingFunction, OS.sel_functionWithName_1, name != null ? name.id : 0);
	return result != 0 ? new id(result) : null;
}

public void getControlPointAtIndex(int idx, int ptr) {
	OS.objc_msgSend(this.id, OS.sel_getControlPointAtIndex_1values_1, idx, ptr);
}

public id initWithControlPoints(float c1x, float c1y, float c2x, float c2y) {
	int result = OS.objc_msgSend(this.id, OS.sel_initWithControlPoints_1_1_1_1, c1x, c1y, c2x, c2y);
	return result != 0 ? new id(result) : null;
}

}
