package org.eclipse.swt.internal.cef3;

public class cef_download_item_t extends cef_base_t {

	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_download_item_t* self))" */
	public long /*int*/ is_valid;

	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_download_item_t* self))" */
	public long /*int*/ is_in_progress;

	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_download_item_t* self))" */
	public long /*int*/ is_complete;

	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_download_item_t* self))" */
	public long /*int*/ is_canceled;

	/** @field cast="(int64 (CEF_CALLBACK *)(struct _cef_download_item_t* self))" */
	public long /*int*/ get_current_speed;

	/** @field cast="(int (CEF_CALLBACK *)(struct _cef_download_item_t* self))" */
	public long /*int*/ get_percent_complete;

	/** @field cast="(int64 (CEF_CALLBACK *)(struct _cef_download_item_t* self))" */
	public long /*int*/ get_total_bytes;

	/** @field cast="(int64 (CEF_CALLBACK *)(struct _cef_download_item_t* self))" */
	public long /*int*/ get_received_bytes;

	/** @field cast="(cef_time_t (CEF_CALLBACK *)(struct _cef_download_item_t* self))" */
	public long /*int*/ get_start_time;

	/** @field cast="(cef_time_t (CEF_CALLBACK *)(struct _cef_download_item_t* self))" */
	public long /*int*/ get_end_time;

	/** @field cast="(cef_string_userfree_t (CEF_CALLBACK *)(struct _cef_download_item_t* self))" */
	public long /*int*/ get_full_path;

	/** @field cast="(int32 (CEF_CALLBACK *)(struct _cef_download_item_t* self))" */
	public long /*int*/ get_id;

	/** @field cast="(cef_string_userfree_t (CEF_CALLBACK *)(struct _cef_download_item_t* self))" */
	public long /*int*/ get_url;

	/** @field cast="(cef_string_userfree_t (CEF_CALLBACK *)(struct _cef_download_item_t* self))" */
	public long /*int*/ get_suggested_file_name;

	/** @field cast="(cef_string_userfree_t (CEF_CALLBACK *)(struct _cef_download_item_t* self))" */
	public long /*int*/ get_content_disposition;

	/** @field cast="(cef_string_userfree_t (CEF_CALLBACK *)(struct _cef_download_item_t* self))" */
	public long /*int*/ get_mime_type;

	public static final int sizeof = CEF3.cef_download_item_t_sizeof();
}
