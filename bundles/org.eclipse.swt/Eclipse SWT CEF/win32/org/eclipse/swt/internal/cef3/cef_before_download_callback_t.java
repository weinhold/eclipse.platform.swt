package org.eclipse.swt.internal.cef3;

public class cef_before_download_callback_t extends cef_base_t {
	
	/** @field cast="(void (CEF_CALLBACK *)(struct _cef_before_download_callback_t* self, const cef_string_t* download_path, int show_dialog))" */
	public long /*int*/ cont;
	
	public static final int sizeof = CEF3.cef_before_download_callback_t_sizeof ();
}
