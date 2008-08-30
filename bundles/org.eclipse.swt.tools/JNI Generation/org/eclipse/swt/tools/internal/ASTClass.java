/*******************************************************************************
 * Copyright (c) 2004, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.tools.internal;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class ASTClass extends ReflectItem implements JNIClass {
	String sourcePath;
	MetaData metaData;

	ASTClass superclass;
	ASTField[] fields;
	ASTMethod[] methods;
	String name, simpleName, superclassName;

public ASTClass(String sourcePath, MetaData data) {
	this.sourcePath = sourcePath;
	this.metaData = data;
	
	IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(sourcePath));
	String source = getResourceContent(resource);
	ASTParser parser = ASTParser.newParser(AST.JLS3);
	parser.setSource(source.toCharArray());
	CompilationUnit unit = (CompilationUnit)parser.createAST(null);
	TypeDeclaration type = (TypeDeclaration)unit.types().get(0);
	simpleName = type.getName().getIdentifier();
	String packageName = unit.getPackage().getName().getFullyQualifiedName();
	name = packageName + "." + simpleName;
	superclassName = type.getSuperclassType() != null ? type.getSuperclassType().toString() : null;

	FieldDeclaration[] fields = type.getFields();
	ArrayList fid = new ArrayList();
	for (int i = 0; i < fields.length; i++) {
		FieldDeclaration field = fields[i];
		List fragments = field.fragments();
		for (Iterator iterator = fragments.iterator(); iterator.hasNext();) {
			VariableDeclarationFragment fragment = (VariableDeclarationFragment) iterator.next();
			fid.add(new ASTField(this, source, packageName, field, fragment));
		}
	}
	this.fields = (ASTField[])fid.toArray(new ASTField[fid.size()]);
	MethodDeclaration[] methods = type.getMethods();
	ArrayList mid = new ArrayList();
	for (int i = 0; i < methods.length; i++) {
		if (methods[i].getReturnType2() == null) continue;
		mid.add(new ASTMethod(this, source, packageName, methods[i]));
	}
	this.methods = (ASTMethod[])mid.toArray(new ASTMethod[mid.size()]);
}


private static String getResourceContent(IResource resource) {
	try {
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		InputStream is = ((IFile)resource).getContents();
		byte[] buffer = new byte[4096];
		int read = -1;
		while ((read = is.read(buffer)) != -1) {
			bs.write(buffer, 0, read);
		}
		is.close();
		return new String(bs.toByteArray());
	} catch (IOException e) {
		e.printStackTrace();
	} catch (CoreException e) {
		e.printStackTrace();
	}
	return null;
}

public int hashCode() {
	return getName().hashCode();
}

public boolean equals(Object obj) {
	if (this == obj) return true;
	if (!(obj instanceof ASTClass)) return false;
	return ((ASTClass)obj).getName().equals(getName());
}

public JNIField[] getDeclaredFields() {
	JNIField[] result = new JNIField[fields.length];
	System.arraycopy(fields, 0, result, 0, result.length);
	return result;
}

public JNIMethod[] getDeclaredMethods() {
	JNIMethod[] result = new JNIMethod[methods.length];
	System.arraycopy(methods, 0, result, 0, result.length);
	return result;
}

public String getName() {
	return name;
}

public JNIClass getSuperclass() {
	if (superclassName == null) return new ReflectClass(Object.class);
	if (superclass != null) return superclass;
	//TODO different package
	String path = new File(sourcePath).getParent() + "/" + superclassName + ".java";
	return superclass = new ASTClass(path, metaData);
}

public String getSimpleName() {
	return simpleName;
}

public String getExclude() {
	return (String)getParam("exclude");
}

public String getMetaData() {
	String key = JNIGenerator.toC(getName());
	return metaData.getMetaData(key, "");
}

public void setExclude(String str) { 
	setParam("exclude", str);
}

public void setMetaData(String value) {
	String key = JNIGenerator.toC(getName());
	metaData.setMetaData(key, value);
}

public String toString() {
	return getName();
}

}
