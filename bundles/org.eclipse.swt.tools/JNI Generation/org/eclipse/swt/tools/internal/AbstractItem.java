package org.eclipse.swt.tools.internal;

import java.util.Arrays;
import java.util.HashSet;

public abstract class AbstractItem implements JNIItem {

static String[] split(String str, String separator) {
	return JNIGenerator.split(str, separator);
}

public String[] getFlags() {
	Object flags = getParam("flags");
	if (flags == null) return new String[0];
	if (flags instanceof String[]) return (String[])flags;
	String[] result = split((String)flags, " ");
	setParam("flags", result);
	return result;
}

public boolean getFlag(String flag) {
	String[] flags = getFlags();
	for (int i = 0; i < flags.length; i++) {
		if (flags[i].equals(flag)) return true;
	}
	return false;
}

public abstract Object getParam(String key);

public boolean getGenerate() {
	return !getFlag(FLAG_NO_GEN);
}

public void setFlags(String[] flags) { 
	setParam("flags", flags);
}

public void setFlag(String flag, boolean value) {
	String[] flags = getFlags();
	HashSet set = new HashSet(Arrays.asList(flags));
	if (value) {
		set.add(flag);
	} else {
		set.remove(flag);
	}
	setFlags((String[])set.toArray(new String[set.size()]));
}

public void setGenerate(boolean value) {
	setFlag(FLAG_NO_GEN, !value);
}

public abstract void setParam(String key, Object value);
	
}
