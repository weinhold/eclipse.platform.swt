package org.eclipse.swt.dnd;

/*
 * (c) Copyright IBM Corp. 2000, 2002.
 * All Rights Reserved
 */
 
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.gtk.*;

/**
 *
 * Class <code>DropTarget</code> defines the target object for a drag and drop transfer.
 *
 * IMPORTANT: This class is <em>not</em> intended to be subclassed.
 *
 * <p>This class identifies the <code>Control</code> over which the user must position the cursor
 * in order to drop the data being transferred.  It also specifies what data types can be dropped on 
 * this control and what operations can be performed.  You may have several DropTragets in an 
 * application but there can only be a one to one mapping between a <code>Control</code> and a <code>DropTarget</code>.
 * The DropTarget can receive data from within the same application or from other applications 
 * (such as text dragged from a text editor like Word).</p>
 *
 * <code><pre>
 *	int operations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK;
 *	Transfer[] types = new Transfer[] {TextTransfer.getInstance()};
 *	DropTarget target = new DropTarget(label, operations);
 *	target.setTransfer(types);
 * </code></pre>
 *
 * <p>The application is notified of data being dragged over this control and of when a drop occurs by 
 * implementing the interface <code>DropTargetListener</code> which uses the class 
 * <code>DropTargetEvent</code>.  The application can modify the type of drag being performed 
 * on this Control at any stage of the drag by modifying the <code>event.detail</code> field or the 
 * <code>event.currentDataType</code> field.  When the data is dropped, it is the responsibility of 
 * the application to copy this data for its own purposes.
 *
 * <code><pre>
 *	target.addDropListener (new DropTargetListener() {
 *		public void dragEnter(DropTargetEvent event) {};
 *		public void dragOver(DropTargetEvent event) {};
 *		public void dragLeave(DropTargetEvent event) {};
 *		public void dragOperationChanged(DropTargetEvent event) {};
 *		public void dropAccept(DropTargetEvent event) {}
 *		public void drop(DropTargetEvent event) {
 *			// A drop has occurred, copy over the data
 *			if (event.data == null) { // no data to copy, indicate failure in event.detail
 *				event.detail = DND.DROP_NONE;
 *				return;
 *			}
 *			label.setText ((String) event.data); // data copied to label text
 *		}
 * 	});
 * </pre></code>
 *
 * <dl>
 *	<dt><b>Styles</b> <dd>DND.DROP_NONE, DND.DROP_COPY, DND.DROP_MOVE, DND.DROP_LINK 
 *	<dt><b>Events</b> <dd>DND.DragEnter, DND.DragLeave, DND.DragOver, DND.DragOperationChanged, 
 *                        DND.Drop, DND.DropAccept
 * </dl>
 */
public final class DropTarget extends Widget {
	
/**
 * Creates a new <code>DropTarget</code> to handle dropping on the specified <code>Control</code>.
 * 
 * @param control the <code>Control</code> over which the user positions the cursor to drop data
 *
 * @param style the bitwise OR'ing of allowed operations; this may be a combination of any of 
 *					DND.DROP_NONE, DND.DROP_COPY, DND.DROP_MOVE, DND.DROP_LINK
 *
 */
	Control control;
	boolean isEnter = true;
	Callback dragMotion;
	Callback dragLeave;
	Callback dragDataReceived;

	int dragMotionAddress;
	int dragLeaveAddress;
	int dragDataReceivedAddress;

	Transfer[] transfers;
	TransferData[] transferDatas;
	
	GtkTargetEntry[] targets; //Data reference to be freed
	int targets_list;		  //Data reference to be freed	

	Listener controlListener;
	
	int lastOperation = -1;	 // To control operations changed events
	int lastX, lastY; 		 // To be used in drag leave event (callback do not provided it)	
	
	private DragUnderEffect effect;

public DropTarget(Control control, int style) {
	super(control, checkStyle(style));
	this.control = control;

	// Drag Motion Callback	
	dragMotion = new Callback(this, "dragMotion", 5);
	dragMotionAddress = dragMotion.getAddress();
	byte[] dragMotionB = Converter.wcsToMbcs(null, "drag_motion", true);
	OS.gtk_signal_connect(control.handle, dragMotionB, dragMotionAddress, 0);

	// Drag Leave Callback	
	dragLeave = new Callback(this, "dragLeave", 3);
	dragLeaveAddress = dragLeave.getAddress();
	byte[] dragLeaveB = Converter.wcsToMbcs(null, "drag_leave", true);
	OS.gtk_signal_connect(control.handle, dragLeaveB, dragLeaveAddress, 0);
	
	// Drag Data Received Callback	
	dragDataReceived = new Callback(this, "dragDataReceived", 7);
	dragDataReceivedAddress = dragDataReceived.getAddress();
	byte[] DataReceivedB = Converter.wcsToMbcs(null, "drag_data_received", true);
	OS.gtk_signal_connect(control.handle, DataReceivedB, dragDataReceivedAddress, 0);

	// Dispose listeners	
	controlListener = new Listener(){
		public void handleEvent(Event event){
			DropTarget.this.dispose();
		}
	};
	control.addListener(SWT.Dispose, controlListener);	
	this.addListener(SWT.Dispose, new Listener(){
		public void handleEvent(Event event){
			DropTarget.this.onDispose();
		}	
	});

	// Drag under effect
	if (control instanceof Tree) {
		effect = new TreeDragUnderEffect((Tree)control);
	} else if (control instanceof Table) {
		effect = new TableDragUnderEffect((Table)control);
	} else {
		effect = new NoDragUnderEffect(control);
	}
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
public void addDropListener(DropTargetListener listener) {	
	if (listener == null) DND.error (SWT.ERROR_NULL_ARGUMENT);
	DNDListener typedListener = new DNDListener (listener);
	addListener (DND.DragEnter, typedListener);
	addListener (DND.DragLeave, typedListener);
	addListener (DND.DragOver, typedListener);
	addListener (DND.DragOperationChanged, typedListener);
	addListener (DND.Drop, typedListener);
	addListener (DND.DropAccept, typedListener);	
}

static int checkStyle (int style) {
	if (style == SWT.NONE) return DND.DROP_MOVE;	
	return style;
}	

int dragDataReceived ( int widget, int context, int x, int y, int data, int info, int time){
	if (data == 0) return 0;
	GtkSelectionData gtkSelectionData = new GtkSelectionData( data ); 
	if (context == 0) return 0;
	GdkDragContext dragContext = new GdkDragContext( context );	
	
	if (gtkSelectionData.data == 0) return 0;	

	TransferData tdata = new TransferData ();
	tdata.type = info;
	tdata.length = gtkSelectionData.length;
	tdata.pValue = gtkSelectionData.data;

	Point coordinates = new Point( x, y );
	coordinates = control.toDisplay( coordinates );
 
	int[] act = getCurrentOperation(context);
	
	DNDEvent event = new DNDEvent();
	event.widget = this;
	event.x = coordinates.x;
	event.y = coordinates.y;
	event.time = time;
	event.detail = act[0];
	event.operations = act[1];
	event.dataType = tdata;	
	event.dataTypes	= new TransferData[] { tdata };	//looks bad, but the dataType cann't be changed at this step anyway
	notifyListeners(DND.DropAccept, event);
	
	/* NOTE: GTK do not provided a way to choose the typedata just before the drop */
	 

	if (event.detail == DND.DROP_NONE)	{
		OS.gtk_drag_finish(context, false, false, time); // It's Useless		
//		OS.gdk_drag_abort(context, time); // can not be used in this callback (GP)
		return 0;
	}

	Transfer transfer = null;
	for (int i = 0; i < transfers.length; i++) {
		transfer = transfers[i];
		if (transfer.isSupportedType(tdata)) break;
	}
	if (transfer == null) return 0;

	Object value = transfer.nativeToJava(tdata);	
	
	event = new DNDEvent();
	event.widget = this;
	event.x = coordinates.x;
	event.y = coordinates.y;
	event.detail = act[0];
	event.operations = act[1];
	event.time = time;
	event.dataType = tdata;	
	event.data = value;
	this.notifyListener(DND.Drop, event);
	
	OS.gtk_drag_finish(context, true, dragContext.action == OS.GDK_ACTION_MOVE, time); 			
	return 1;	
}

int dragLeave ( int widget, int context, int time){
	int[] act = getCurrentOperation(context);
	DNDEvent event = new DNDEvent();
	event.widget = this;
	event.time = time;
	event.x = lastX;
	event.y = lastY;
	event.dataType = getTransferData(context);
	event.dataTypes	= new TransferData[] { event.dataType }; //BAD
	event.detail = act[0];
	event.operations = act[1];
	notifyListeners(DND.DragLeave, event);
	isEnter = true;
	return 1;	
}

int dragMotion ( int widget, int context, int x, int y, int time){
	DNDEvent event = new DNDEvent();
	int[] act = getCurrentOperation(context);	
	Point coordinates = control.toDisplay( new Point( x, y) );	
	event.widget = this;
	event.x = coordinates.x;
	event.y = coordinates.y;
	event.dataType = getTransferData(context);
	event.dataTypes	= new TransferData[] { event.dataType };	//BAD
	event.detail = act[0];
	event.operations = act[1];
	event.time = time;
	event.feedback = DND.FEEDBACK_NONE;

	// to be used in dragleave
	lastX = event.x;
	lastY = event.y;
	
	int type = DND.DragOver;
	if(isEnter){
		type = DND.DragEnter;
		isEnter = false;
	} else {
		if ( lastOperation != -1 && event.detail != lastOperation ) {
			type = DND.DragOperationChanged;
		}
	}
	lastOperation = event.detail;
	
	notifyListeners(type, event);

	effect.show( event.feedback, coordinates.x, coordinates.y);

	if (event.detail == DND.DROP_NONE)	{
		// Does not work properly, do not send drag_end signal for the source side
		OS.gdk_drag_abort(context, time);  
		return 0;
	}
	
	return 1;
}

private int getActions(){
	int style = getStyle();
	int operationsDnd = 0;
	if ( (style &  DND.DROP_MOVE) != 0) operationsDnd |= OS.GDK_ACTION_MOVE;
	if ( (style &  DND.DROP_COPY) != 0) operationsDnd |= OS.GDK_ACTION_COPY;
	if ( (style &  DND.DROP_LINK) != 0) operationsDnd |= OS.GDK_ACTION_LINK;		
	return operationsDnd;
}

/**
 * Returns the Control which is registered for this DropTarget.  This is the control over which the 
 * user positions the cursor to drop the data.
 *
 * @return the Control which is registered for this DropTarget
 *
 */
public Control getControl () {
	return control;
}

/**
 *  @return int[] length equals 2, 
 * 		index 0 holds the currentAction
 * 		index 1 holds a bitwise of all possible actions
 * 
 */
private int[] getCurrentOperation(int context){
	if(context == 0) return new int[]{ DND.DROP_MOVE, DND.DROP_MOVE};
	GdkDragContext dragContext = new GdkDragContext(context);
	
	int action = DND.DROP_MOVE;
	if(dragContext.action == OS.GDK_ACTION_COPY) action = DND.DROP_COPY;
	if(dragContext.action == OS.GDK_ACTION_LINK) action = DND.DROP_LINK;
	
	int actions = 0;
	if((dragContext.actions & OS.GDK_ACTION_MOVE) != 0) actions |= DND.DROP_MOVE;
	if((dragContext.actions & OS.GDK_ACTION_COPY) != 0) actions |= DND.DROP_COPY;
	if((dragContext.actions & OS.GDK_ACTION_LINK) != 0) actions |= DND.DROP_LINK;
			
	return 	new int[] {action, actions};
}

public Display getDisplay () {
	if (control == null) DND.error(SWT.ERROR_WIDGET_DISPOSED);
	return control.getDisplay ();
}

/**
 * Returns the list of data types that can be transferred to this DropTarget.
 *
 * @return the list of data types that can be transferred to this DropTarget
 *
 */ 
public Transfer[] getTransfer() { 
	return transfers; 
}

private TransferData getTransferData(int context){
	TransferData tdata = new TransferData();
	if ( context == 0) return tdata;
	GdkDragContext dragContext = new GdkDragContext(context);
	int atom = OS.gtk_drag_dest_find_target(control.handle, context, 0);
	int ptr = OS.gdk_atom_name(atom);
	int len = OS.strlen(ptr);
	byte[] buffer = new byte [ len ];
	OS.memmove(buffer, ptr, len);
	String formatname = new String ( Converter.mbcsToWcs(null, buffer) );
	tdata.type = Transfer.registerType(formatname);  // Lazy way
	return tdata;
}

public void notifyListener (int eventType, Event event) {
	Point coordinates = new Point(event.x, event.y);
	coordinates = control.toControl(coordinates);
	if (this.control instanceof Tree) {
		event.item = ((Tree)control).getItem(coordinates);
	}
	if (this.control instanceof Table) {
		event.item = ((Table)control).getItem(coordinates);
	}
	super.notifyListeners(eventType, event);
}

private void onDispose(){
	if (dragMotion != null ) 
		dragMotion.dispose();
	dragMotion = null;
	
	if (dragDataReceived != null ) 
		dragDataReceived.dispose();
	dragDataReceived = null;
	
	if (dragLeave!= null ) 
		dragLeave.dispose();
	dragLeave = null;

	if (control != null){
		OS.gtk_drag_dest_unset(control.handle);
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
public void removeDropListener(DropTargetListener listener) {
	if (listener == null) DND.error (SWT.ERROR_NULL_ARGUMENT);
	removeListener (DND.DragEnter, listener);
	removeListener (DND.DragLeave, listener);
	removeListener (DND.DragOver, listener);
	removeListener (DND.DragOperationChanged, listener);
	removeListener (DND.Drop, listener);
	removeListener (DND.DropAccept, listener);
}

private void setTargetList(){

	if (transfers == null) return;
	if (control == null) return;
		
	//Release previous allocates data (remember)
	
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
	
	transferDatas = new TransferData[entrys.length];
	for (int i = 0; i < transferDatas.length; i++) {
		TransferData td = new TransferData();
		td.type = entrys[i].target; 
		transferDatas[i] = td;
	}

	if (targets_list != 0){
		OS.gtk_drag_dest_unset(control.handle);
	}

	targets_list = OS.g_malloc(buffer.length);   
	OS.memmove(targets_list, buffer, buffer.length);
	targets = entrys;
	
	OS.gtk_drag_dest_set(control.handle, OS.GTK_DEST_DEFAULT_ALL, targets_list, n_entry, getActions());
	
}

/**
 * Specifies the list of data types that can be transferred to this DropTarget.
 *
 * @param transferAgents a list of Transfer objects which define the types of data that can be
 *						 dropped on this target
 */
public void setTransfer(Transfer[] transferAgents){
	if (transferAgents == null) DND.error(SWT.ERROR_NULL_ARGUMENT);
	this.transfers = transferAgents;
	setTargetList();
}	
}
