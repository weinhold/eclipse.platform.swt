package org.eclipse.swt.internal.cef3;

public class cef_focus_handler_t extends cef_base_t {
	// Called when the browser component is about to loose focus. For instance, if
	// focus was on the last HTML element and the user pressed the TAB key. |next|
	// will be true (1) if the browser is giving focus to the next component and
	// false (0) if the browser is giving focus to the previous component.
//	/** @field cast=(void (CEF_CALLBACK *on_take_focus)(struct _cef_focus_handler_t* self, struct _cef_browser_t* browser, int next);
	public long /*int*/ on_take_focus;
	
	// Called when the browser component is requesting focus. |source| indicates
	// where the focus request is originating from. Return false (0) to allow the
	// focus to be set or true (1) to cancel setting the focus.
//		/** @field cast=(int (CEF_CALLBACK *on_set_focus)(struct _cef_focus_handler_t* self, struct _cef_browser_t* browser, enum cef_focus_source_t source);
	public long /*int*/ on_set_focus;
	
	// Called when the browser component has received focus.
//		/** @field cast=(void (CEF_CALLBACK *on_got_focus)(struct _cef_focus_handler_t* self, struct _cef_browser_t* browser);
	public long /*int*/ on_got_focus;
	
	public static final int sizeof = CEF3.cef_focus_handler_t_sizeof ();
}
