package org.eclipse.swt.internal.win32;

	
public class DTTOPTS {
	public static int DTT_TEXTCOLOR = (1 << 0);      // crText has been specified
	public static int DTT_BORDERCOLOR = (1 << 1);      // crBorder has been specified
	public static int DTT_SHADOWCOLOR = (1 << 2);      // crShadow has been specified
	public static int DTT_SHADOWTYPE = (1 << 3);      // iTextShadowType has been specified
	public static int DTT_SHADOWOFFSET = (1 << 4);      // ptShadowOffset has been specified
	public static int DTT_BORDERSIZE = (1 << 5);      // iBorderSize has been specified
	public static int DTT_FONTPROP = (1 << 6);      // iFontPropId has been specified
	public static int DTT_COLORPROP = (1 << 7);      // iColorPropId has been specified
	public static int DTT_STATEID = (1 << 8);      // IStateId has been specified
	public static int DTT_CALCRECT = (1 << 9);      // Use pRect as and in/out parameter
	public static int DTT_APPLYOVERLAY = (1 << 10);     // fApplyOverlay has been specified
	public static int DTT_GLOWSIZE = (1 << 11);     // iGlowSize has been specified
	public static int DTT_CALLBACK = (1 << 12);     // pfnDrawTextCallback has been specified
	public static int DTT_COMPOSITED = (1 << 13);     // Draws text with antialiased alpha (needs a DIB section)
	public static int DTT_VALIDBITS = (DTT_TEXTCOLOR |
	                             DTT_BORDERCOLOR |
	                             DTT_SHADOWCOLOR |
	                             DTT_SHADOWTYPE |
	                             DTT_SHADOWOFFSET |
	                             DTT_BORDERSIZE |
	                             DTT_FONTPROP |
	                             DTT_COLORPROP |
	                             DTT_STATEID |
	                             DTT_CALCRECT |
	                             DTT_APPLYOVERLAY |
	                             DTT_GLOWSIZE |
	                             DTT_COMPOSITED);
	
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
