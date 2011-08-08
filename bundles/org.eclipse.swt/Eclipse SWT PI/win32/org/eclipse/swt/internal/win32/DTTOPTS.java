package org.eclipse.swt.internal.win32;

	
public class DTTOPTS {
	public int dwSize;
	public int dwFlags;
	public int /*long*/ crText;
	public int /*long*/ crBorder;
	public int /*long*/ crShadow;
	public int iTextShadowType;
	public POINT ptShadowOffset;
	public int iBorderSize;
	public int iFontPropId;
	public int iColorPropId;
	public int iStateId;
	public boolean fApplyOverlay;
	public int iGlowSize;
	/** @field cast=(DTT_CALLBACK_PROC) */
	public int /*long*/ pfnDrawTextCallback;
	public int /*long*/ lParam;
}
