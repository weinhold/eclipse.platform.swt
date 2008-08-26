package org.eclipse.swt.tools.internal;

public class ReflectType implements JNIType {
	Class clazz, alternateClazz;

public ReflectType(Class clazz) {
	this.clazz = clazz;
}

public ReflectType(Class clazz, Class alternateClass) {
	Class temp = clazz.isArray() ? clazz.getComponentType() : clazz;
	if (temp == double.class || temp == long.class) {
		temp = alternateClass;
		clazz = alternateClass;
		alternateClass = temp;
	}
	this.clazz = clazz;
	this.alternateClazz = alternateClass;
}
	
public int getByteCount() {
	if (clazz == Integer.TYPE) return 4;
	if (clazz == Boolean.TYPE) return 4;
	if (clazz == Long.TYPE) return 8;
	if (clazz == Short.TYPE) return 2;
	if (clazz == Character.TYPE) return 2;
	if (clazz == Byte.TYPE) return 1;
	if (clazz == Float.TYPE) return 4;
	if (clazz == Double.TYPE) return 8;
	return 4;
}


public JNIType getComponentType() {
	return new ReflectType(clazz.getComponentType());
}

public String getSimpleName() {
	String name = clazz.getName();
	int index = name.lastIndexOf('.') + 1;
	return name.substring(index, name.length());
}

public String getTypeSignature() {
	if (clazz == Void.TYPE) return "V";
	if (clazz == Integer.TYPE) return alternateClazz != null ? "SWT_I_SIGNATURE" : "I";
	if (clazz == Boolean.TYPE) return "Z";
	if (clazz == Long.TYPE) return "J";
	if (clazz == Short.TYPE) return "S";
	if (clazz == Character.TYPE) return "C";
	if (clazz == Byte.TYPE) return "B";
	if (clazz == Float.TYPE) return alternateClazz != null ? "SWT_F_SIGNATURE" : "F";
	if (clazz == Double.TYPE) return "D";
	if (clazz == String.class) return "Ljava/lang/String;";
	if (clazz.isArray()) {
		return "[" + getComponentType().getTypeSignature();
	}
	return "L" + clazz.getName().replace('.', '/') + ";";
}

public String getTypeSignature1() {
	if (clazz == Void.TYPE) return "Void";
	if (clazz == Integer.TYPE) return "Int";
	if (clazz == Boolean.TYPE) return "Boolean";
	if (clazz == Long.TYPE) return "Long";
	if (clazz == Short.TYPE) return "Short";
	if (clazz == Character.TYPE) return "Char";
	if (clazz == Byte.TYPE) return "Byte";
	if (clazz == Float.TYPE) return "Float";
	if (clazz == Double.TYPE) return "Double";
	if (clazz == String.class) return "String";
	return "Object";
}

public String getTypeSignature2() {
	if (clazz == Void.TYPE) return "void";
	if (clazz == Integer.TYPE) return "jint";
	if (clazz == Boolean.TYPE) return "jboolean";
	if (clazz == Long.TYPE) return "jlong";
	if (clazz == Short.TYPE) return "jshort";
	if (clazz == Character.TYPE) return "jchar";
	if (clazz == Byte.TYPE) return "jbyte";
	if (clazz == Float.TYPE) return "jfloat";
	if (clazz == Double.TYPE) return "jdouble";
	if (clazz == String.class) return "jstring";
	if (clazz == Class.class) return "jclass";
	if (clazz.isArray()) {
		return getComponentType().getTypeSignature2() + "Array";
	}
	return "jobject";
}

public String getTypeSignature3() {
	if (clazz == Void.TYPE) return "void";
	if (clazz == Integer.TYPE) return "int";
	if (clazz == Boolean.TYPE) return "boolean";
	if (clazz == Long.TYPE) return "long";
	if (clazz == Short.TYPE) return "short";
	if (clazz == Character.TYPE) return "char";
	if (clazz == Byte.TYPE) return "byte";
	if (clazz == Float.TYPE) return "float";
	if (clazz == Double.TYPE) return "double";
	if (clazz == String.class) return "String";
	if (clazz.isArray()) {
		return getComponentType().getTypeSignature3() + "[]";
	}
	return clazz.getName();
}

public String getTypeSignature4() {
	return getTypeSignature4(false);
}

public String getTypeSignature4(boolean struct) {
	if (clazz == Void.TYPE) return "void";
	if (clazz == Integer.TYPE) return "jint";
	if (clazz == Boolean.TYPE) return "jboolean";
	if (clazz == Long.TYPE) return "jlong";
	if (clazz == Short.TYPE) return "jshort";
	if (clazz == Character.TYPE) return "jchar";
	if (clazz == Byte.TYPE) return "jbyte";
	if (clazz == Float.TYPE) return "jfloat";
	if (clazz == Double.TYPE) return "jdouble";
	if (clazz == String.class) return "jstring";
	if (clazz.isArray()) {
		String sig = getComponentType().getTypeSignature4();
		return struct ? sig : sig + " *";
	}
	String sig = getSimpleName(); 
	return struct ? sig : sig + " *";
}

public boolean isArray() {
	return clazz.isArray();
}

public boolean isPrimitive() {
	return clazz.isPrimitive();
}

public boolean isType(String type) {
	return clazz.getName().equals(type);
}

}
