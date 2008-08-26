package org.eclipse.swt.tools.internal;

public interface JNIParameter extends JNIItem {

	public static final String[] FLAGS = {FLAG_NO_IN, FLAG_NO_OUT, FLAG_CRITICAL, FLAG_INIT, FLAG_STRUCT, FLAG_UNICODE, FLAG_SENTINEL, FLAG_GCOBJECT};

public String getCast();

public JNIMethod getMethod();

public int getParameter();

public JNIClass getParameterType();

public void setCast(String str);
}
