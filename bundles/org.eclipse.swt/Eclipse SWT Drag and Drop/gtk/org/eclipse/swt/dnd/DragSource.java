package org.eclipse.swt.dnd;

/*
 * (c) Copyright IBM Corp. 2000, 2002.
 * All Rights Reserved
 */
 

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.gtk.*;

/**
 *
 * Class <code>DragSource</code> defines the source object for a drag and drop transfer.
 *
 * IMPORTANT: This class is <em>not</em> intended to be subclassed.
 *
 * <p>This class defines the following items:<ul>
 *   <li>the <code>Control</code> that the user clicks on to intiate a drag;
 *   <li>the data that will be transferred on a successful drop; 
 *   <li>and the modes (move, copy, link) of transfer that are allowed.
 * </ul></p>
 *
 * <p>You may have several DragSources in an application but you can only have one DragSource 
 * per Control.  Data dragged from this DragSource can be dropped on a site within this application 
 * but it can also be dropped on another application such as an external Text editor.</p>
 * 
 * <p>The application supplies the content of the data being transferred by implementing the interface
 * <code>DragSourceListener</code> which uses the class <code>DragSourceEvent</code>.  
 * The application is required to take the appropriate action to remove the data from the drag source
 * when a successful move operation occurs.</p>
 *
 * <code><pre>
 *	// Enable a label as a Drag Source
 *	Label label = new Label(shell, SWT.NONE);
 *	// This example will allow text to be dragged
 *	Transfer[] types = new Transfer[] {TextTransfer.getInstance()};
 *	// This example will allow the text to be copied or moved to the drop target
 *	int operations = DND.DROP_MOVE | DND.DROP_COPY;
 *	
 *	DragSource source = new DragSource (label, operations);
 *	source.setTransfer(types);
 *	source.addDragListener (new DragSourceListener() {
 *		public void dragStart(DragSourceEvent e) {
 *			// Only start the drag if there is actually text in the
 *			// label - this text will be what is dropped on the target.
 *			if (label.getText().length() == 0) {
 *				event.doit = false;
 *			}
 *		};
 *		public void dragSetData (DragSourceEvent event) {
 *			// A drop has been performed, so provide the data of the 
 *			// requested type.
 *			// (Checking the type of the requested data is only 
 *			// necessary if the drag source supports more than 
 *			// one data type but is shown here as an example).
 *			if (TextTransfer.getInstance().isSupportedType(event.dataType)){
 *				event.data = label.getText();
 *			}
 *		}
 *		public void dragFinished(DragSourceEvent event) {
 *			// A Move operation has been performed so remove the data
 *			// from the source
 *			if (event.detail == DND.DROP_MOVE)
 *				label.setText("");
 *		}
 *	});
 * </pre></code>
 *
 *
 * <dl>
 *	<dt><b>Styles</b> <dd>DND.DROP_NONE, DND.DROP_COPY, DND.DROP_MOVE, DND.DROP_LINK 
 *	<dt><b>Events</b> <dd>DND.DragEnd, DND.DragSetData
 * </dl>
 */
public final class DragSource extends Widget {
	Control control;
	Transfer[] transfers;
	Callback dragBegin;
	Callback dragGetData;
	Callback dragEnd;
	int dragBeginAddress;
	int dragGetDataAddress;
	int dragEndAddress;
	
	GtkTargetEntry[] targets; 	//Data reference to be freed
	int targets_list; 			//Data reference to be freed
	
	final int buttonMask = OS.GDK_BUTTON1_MASK | OS.GDK_BUTTON3_MASK;
	int operations;
	
	Listener controlListener;
	
/**
 * Creates a new <code>DragSource</code> to handle dragging from the specified <code>Control</code>.
 * 
 * @param control the <code>Control</code> that the user clicks on to initiate the drag
 *
 * @param style the bitwise OR'ing of allowed operations; this may be a combination of any of 
 *					DND.DROP_NONE, DND.DROP_COPY, DND.DROP_MOVE, DND.DROP_LINK
 *
 */
public DragSource(Control control, int style) {
	super (control, checkStyle(style));
	this.control = control;
	
	operations = 0;
	if ( (style &  DND.DROP_MOVE) != 0) operations |= OS.GDK_ACTION_MOVE;
	if ( (style &  DND.DROP_COPY) != 0) operations |= OS.GDK_ACTION_COPY;
	if ( (style &  DND.DROP_LINK) != 0) operations |= OS.GDK_ACTION_LINK;		

	// Drag Begin Callback	
	dragBegin = new Callback(this, "dragBegin", 3);
	dragBeginAddress = dragBegin.getAddress();
	byte[] dragBeginB = Converter.wcsToMbcs(null, "drag_begin", true);
	OS.gtk_signal_connect(control.handle, dragBeginB, dragBeginAddress, 0);
	
	// Drag Get Data Callback	
	dragGetData = new Callback(this, "dragGetData", 6);
	dragGetDataAddress = dragGetData.getAddress();
	byte[] dragGetDataB = Converter.wcsToMbcs(null, "drag_data_get", true);
	OS.gtk_signal_connect(control.handle, dragGetDataB, dragGetDataAddress, 0);

	// Drag End Callback	
	dragEnd = new Callback(this, "dragEnd", 3);
	dragEndAddress = dragEnd.getAddress();
	byte[] dragEndB = Converter.wcsToMbcs(null, "drag_end", true);
	OS.gtk_signal_connect(control.handle, dragEndB, dragEndAddress, 0);


	controlListener = new Listener(){
		public void handleEvent(Event event){
			DragSource.this.dispose();
		}
	};
	control.addListener(SWT.Dispose, controlListener);	
	this.addListener(SWT.Dispose, new Listener(){
		public void handleEvent(Event event){
			DragSource.this.onDispose();
		}	
	});
		
}

/**	 
 * Adds the listener to receive events.
 *
 * @param listener the listener
 *
 * @exception SWTError 
 *	<ul><li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
 * 		<li>ERROR_WIDGET_DISPOSED  when the widget has been disposed</li>
 * 		<li>ERROR_NULL_ARGUMENT when listener is null</li></ul>
 */
public void addDragListener(DragSourceListener listener) {
	if (listener == null) DND.error (SWT.ERROR_NULL_ARGUMENT);
	DNDListener typedListener = new DNDListener (listener);
	addListener (DND.DragStart, typedListener);
	addListener (DND.DragEnd, typedListener);
	addListener (DND.DragSetData, typedListener);
}

static int checkStyle (int style) {
	if (style == SWT.NONE) return DND.DROP_MOVE;	
	return style;
}


int dragBegin(int widget, int context, int data){ 

	int time = 0;
	if(context != 0 ) {	
		GdkDragContext gdkDragContext = new GdkDragContext(context);
		time = gdkDragContext.start_time; //BAD (ALWAYS ZERO)
		if (time == 0) time = OS.GDK_CURRENT_TIME(); //BAD (ALWAYS ZERO)
	}	
	DNDEvent event = new DNDEvent();
	event.doit = true;
	event.widget = this;
	event.time = time;	
	notifyListeners(DND.DragStart, event);
	
	if ( ! event.doit) {
		OS.gdk_drag_abort(context, event.time ); //BAD (NOT WORKING)
	}	
	
	return 1;
}

int dragEnd(int widget, int context, int data){ 
	int op = DND.DROP_NONE;
	boolean doit = true;
	if (context != 0) {
		GdkDragContext dragContext = new GdkDragContext (context);
		switch (dragContext.action) {
			case OS.GDK_ACTION_MOVE:
				op = DND.DROP_MOVE;						
				break;
			case OS.GDK_ACTION_COPY:
				op = DND.DROP_COPY;						
				break;
			case OS.GDK_ACTION_LINK:
				op = DND.DROP_LINK;						
				break;
			case 0:		//Drag was cancel
				doit = false;
				break;
		}
	}	
	
	DNDEvent event = new DNDEvent();
	event.widget = this;
	event.doit = doit; 
	event.detail = op; 
	notifyListeners(DND.DragEnd, event);
	return 1;	
}	

int dragGetData(int widget, int context, int selection_data,  int info, int time, int data){
	DNDEvent event = new DNDEvent();
	event.widget = this;
	event.time = time; 
	TransferData tdata = new TransferData ();
	tdata.type = info;
	event.dataType = tdata; 

	notifyListeners(DND.DragSetData, event);

	if (event.data == null) return 0;
		
	Transfer transfer = null;
	for (int i = 0; i < transfers.length; i++) {
		transfer = transfers[i];
		if (transfer.isSupportedType(tdata)) break;
	}
	if (transfer == null) return 0;

	if (selection_data == 0) return 0;	
	GtkSelectionData gtkSelectionData = new GtkSelectionData(selection_data);
	if (gtkSelectionData.target == 0) return 0;
	
	transfer.javaToNative(event.data, tdata);

	OS.gtk_selection_data_set(selection_data, gtkSelectionData.target, transfer.format, tdata.pValue , tdata.length);
	
	return 1;
	
}

public Display getDisplay () {
	if (control == null) DND.error(SWT.ERROR_WIDGET_DISPOSED);
	return control.getDisplay ();
}

/**
 * Returns the list of data types that can be transferred by this DragSource.
 *
 * @return the list of data types that can be transferred by this DragSource
 */
public Transfer[] getTransfer(){
	return transfers;
}

private void onDispose(){
	if (dragBegin != null ) 
		dragBegin.dispose();
	dragBegin = null;
	
	if (dragGetData != null ) 
		dragGetData.dispose();
	dragGetData = null;
	
	if (dragEnd != null ) 
		dragEnd.dispose();
	dragEnd = null;
	

	if (control != null){
		OS.gtk_drag_source_unset(control.handle);
		if (controlListener != null)
			control.removeListener(SWT.Dispose, controlListener);
	}

	releaseTargets();
	control = null;
	controlListener = null;
}


private void releaseTargets(){
	
	if ( targets != null ) {
		for (int i = 0; i < targets.length; i++) {
			GtkTargetEntry entry = targets[i];
			if( entry.target != 0) OS.g_free(entry.target);
		}
	}
	
	if (targets_list != 0) {
		OS.g_free(targets_list);
	}
	
	targets_list = 0;
	targets = null;
}

/**	 
 * Removes the listener.
 *
 * @param listener the listener
 *
 * @exception SWTError
 *	<ul><li>ERROR_THREAD_INVALID_ACCESS	when called from the wrong thread</li>
 * 		<li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
 * 		<li>ERROR_NULL_ARGUMENT when listener is null</li></ul>
 */
public void removeDragListener(DragSourceListener listener) {
	if (listener == null) DND.error (SWT.ERROR_NULL_ARGUMENT);
	removeListener (DND.DragStart, listener);
	removeListener (DND.DragEnd, listener);
	removeListener (DND.DragSetData, listener);
}

private void setTargetList(){

	if (transfers == null) return;
	if (control == null) return;
		
	releaseTargets();
	
	int n_entry = 0;	
	GtkTargetEntry[] entrys = new  GtkTargetEntry [n_entry];

	
	Transfer[] transferAgents = transfers;
	for (int i = 0; i < transferAgents.length; i++) {
		Transfer transfer = transferAgents[i];
		int[] types = transfer.getTypeIds();
		for (int j = 0; j < types.length; j++) {
			int type = types[j];
			String typename = transfer.getTypeNames()[j];
			byte[] buffer = Converter.wcsToMbcs(null, typename, true);
			int ptr = OS.g_malloc(buffer.length);
			OS.memmove(ptr, buffer, buffer.length);

			GtkTargetEntry	entry = new GtkTargetEntry();						
			entry.target = ptr;
			entry.info = type;
			
			GtkTargetEntry[] tmp = new  GtkTargetEntry [n_entry + 1];
			System.arraycopy(entrys, 0, tmp, 0, n_entry);
			tmp[ n_entry ] = entry;
			entrys = tmp;
			n_entry++;				
		}	
	}
	
	byte[] buffer = new byte[ GtkTargetEntry.sizeof * n_entry ];
	byte[] tmp = new byte[ GtkTargetEntry.sizeof ];
	for (int i = 0; i < n_entry; i++) {
		OS.memmove(tmp, entrys[i], GtkTargetEntry.sizeof);
		System.arraycopy(tmp, 0, buffer, i * GtkTargetEntry.sizeof, tmp.length);
	}
	
	int ptr = OS.g_malloc(buffer.length);   
	OS.memmove(ptr, buffer, buffer.length);

	if (targets_list != 0){
		OS.gtk_drag_source_unset(control.handle);
	}
	
	targets_list = ptr;
	targets = entrys;

	OS.gtk_drag_source_set(control.handle, buttonMask , targets_list, n_entry, operations );
	
}

/**
 * Specifies the list of data types that can be transferred by this DragSource.
 * The application must be able to provide data to match each of these types when
 * a successful drop has occurred.
 */
public void setTransfer(Transfer[] transferAgents){
	if (transferAgents == null) DND.error(SWT.ERROR_NULL_ARGUMENT);
	this.transfers = transferAgents;
	setTargetList();
}

}
