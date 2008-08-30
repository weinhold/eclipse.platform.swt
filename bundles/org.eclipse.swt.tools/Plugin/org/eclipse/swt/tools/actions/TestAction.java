package org.eclipse.swt.tools.actions;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.tools.internal.ASTClass;
import org.eclipse.swt.tools.internal.JNIClass;
import org.eclipse.swt.tools.internal.JNIGeneratorApp;
import org.eclipse.swt.tools.internal.MetaData;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class TestAction implements IWorkbenchWindowActionDelegate {
//	private IWorkbenchWindow window;
	/**
	 * The constructor.
	 */
	public TestAction() {
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		
		new Thread() {
			public void run() {
				long time = System.currentTimeMillis();
				String sourcePath = "org.eclipse.swt/Eclipse SWT PI/win32/org/eclipse/swt/internal/win32/";
				IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(sourcePath));
				Properties prop = new Properties();
				try {
					prop.load(new BufferedInputStream(new FileInputStream("C:\\silenio\\workspace\\org.eclipse.swt.tools\\JNI Generation\\org\\eclipse\\swt\\tools\\internal\\org.eclipse.swt.internal.win32.OS.properties")));
					prop.load(new BufferedInputStream(new FileInputStream("C:\\silenio\\workspace\\org.eclipse.swt.tools\\JNI Generation\\org\\eclipse\\swt\\tools\\internal\\org.eclipse.swt.internal.properties")));
				} catch (Throwable e) {
					e.printStackTrace();
				}
				final String mainPath = "/org.eclipse.swt/Eclipse SWT PI/win32/org/eclipse/swt/internal/win32/OS.java";
				final MetaData metaData = new MetaData(prop);
				final ASTClass mainClass = new ASTClass(mainPath, metaData);
				final ArrayList classes = new ArrayList();
				try {
					resource.accept(new IResourceVisitor() {
						public boolean visit(IResource resource) throws CoreException {
							IPath path = resource.getFullPath();
							String ext = path.getFileExtension();
//							System.out.println(path.toPortableString());
							if ("java".equals(ext)) {
								if (mainPath.equals(path.toPortableString())){
									classes.add(mainClass);
								} else {
									classes.add(new ASTClass(path.toPortableString(), metaData));
								}
								return false;
							}
							return true;
						}
					});
				} catch (CoreException e) {
					e.printStackTrace();
				}
				System.out.println("done1=" + (System.currentTimeMillis() - time));
				JNIGeneratorApp app = new JNIGeneratorApp();
				app.setOutputDir("C:/silenio/runtime-New_configuration(1)/org.eclipse.swt/Eclipse SWT PI/win32/library");
				app.setMetaData(metaData);
				app.setMainClass(mainClass);
				app.setClasses((JNIClass[])classes.toArray(new JNIClass[classes.size()]));
				app.generate();
				System.out.println("done=" + (System.currentTimeMillis() - time));
			}
		}.start();
	}

	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
//		this.window = window;
	}
}