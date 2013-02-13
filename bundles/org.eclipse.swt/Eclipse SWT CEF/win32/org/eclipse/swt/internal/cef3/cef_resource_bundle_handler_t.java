package org.eclipse.swt.internal.cef3;

public class cef_resource_bundle_handler_t extends cef_base_t {
	  ///
	  // Called to retrieve a localized translation for the string specified by
	  // |message_id|. To provide the translation set |string| to the translation
	  // string and return true (1). To use the default translation return false
	  // (0). Supported message IDs are listed in cef_pack_strings.h.
	  ///
//	/** @field cast=(int (CEF_CALLBACK *get_localized_string)(struct _cef_resource_bundle_handler_t* self, int message_id, cef_string_t* string)) */
	public long /*int*/ get_localized_string;
	
	  ///
	  // Called to retrieve data for the resource specified by |resource_id|. To
	  // provide the resource data set |data| and |data_size| to the data pointer
	  // and size respectively and return true (1). To use the default resource data
	  // return false (0). The resource data will not be copied and must remain
	  // resident in memory. Supported resource IDs are listed in
	  // cef_pack_resources.h.
	  ///
//	/** @field cast=(int (CEF_CALLBACK *get_data_resource)(struct _cef_resource_bundle_handler_t* self, int resource_id, void** data, size_t* data_size))
	public long /*int*/ get_data_resource;
	
	public static final int sizeof = CEF3.cef_resource_bundle_handler_t_sizeof ();
}
