package org.eclipse.swt.internal.cef3;

public class cef_base_t {
	/** @field cast=(size_t) */
	public long /*int*/ size;

	// Increment the reference count.
//	/** @field cast=(int (CEF_CALLBACK *add_ref)(struct _cef_base_t* self)) */
	public long /*int*/ add_ref;

	// Decrement the reference count.  Delete this object when no references
	// remain.
//	/** @field cast=(int (CEF_CALLBACK *release)(struct _cef_base_t* self)) */
	public long /*int*/ release;

	// Returns the current number of references.
//	/** @field cast=(int (CEF_CALLBACK *get_refct)(struct _cef_base_t* self)) */
	public long /*int*/ get_refct;
}
