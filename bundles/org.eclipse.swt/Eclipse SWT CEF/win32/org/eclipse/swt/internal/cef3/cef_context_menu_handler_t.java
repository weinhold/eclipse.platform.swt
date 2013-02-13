package org.eclipse.swt.internal.cef3;

public class cef_context_menu_handler_t extends cef_base_t {
	// Called before a context menu is displayed. |params| provides information
	// about the context menu state. |model| initially contains the default
	// context menu. The |model| can be cleared to show no context menu or
	// modified to show a custom menu. Do not keep references to |params| or
	// |model| outside of this callback.
	//	/** @field cast=(void (CEF_CALLBACK *on_before_context_menu) (struct _cef_context_menu_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_context_menu_params_t* params, struct _cef_menu_model_t* model))
	public long /*int*/ on_before_context_menu;

	// Called to execute a command selected from the context menu. Return true (1)
	// if the command was handled or false (0) for the default implementation. See
	// cef_menu_id_t for the command ids that have default implementations. All
	// user-defined command ids should be between MENU_ID_USER_FIRST and
	// MENU_ID_USER_LAST. |params| will have the same values as what was passed to
	// on_before_context_menu(). Do not keep a reference to |params| outside of
	// this callback.
	//	/** @field cast=(int (CEF_CALLBACK *on_context_menu_command)(struct _cef_context_menu_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_context_menu_params_t* params, int command_id, enum cef_event_flags_t event_flags))
	public long /*int*/ on_context_menu_command;

	// Called when the context menu is dismissed irregardless of whether the menu
	// was NULL or a command was selected.
	//	/** @field cast=(void (CEF_CALLBACK *on_context_menu_dismissed)(struct _cef_context_menu_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame))
	public long /*int*/ on_context_menu_dismissed;
	
	public static final int sizeof = CEF3.cef_context_menu_handler_t_sizeof ();
}
