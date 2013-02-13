package org.eclipse.swt.internal.cef3;

public class cef_proxy_handler_t extends cef_base_t {
	  ///
	  // Called to retrieve proxy information for the specified |url|.
	  ///
//	/** @field cast=(void (CEF_CALLBACK *get_proxy_for_url)(struct _cef_proxy_handler_t* self,const cef_string_t* url, struct _cef_proxy_info_t* proxy_info);
	public long /*int*/ get_proxy_for_url;
	
	public static final int sizeof = CEF3.cef_proxy_handler_t_sizeof();	  
}
