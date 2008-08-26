package org.eclipse.swt.tools.internal;

public interface JNIType {

public boolean isPrimitive();

public boolean isArray();

public JNIType getComponentType();

public boolean isType(String type);

public int getByteCount();

public String getSimpleName();

public String getTypeSignature();

public String getTypeSignature1();

public String getTypeSignature2();

public String getTypeSignature3();

public String getTypeSignature4();

public String getTypeSignature4(boolean struct);

}
