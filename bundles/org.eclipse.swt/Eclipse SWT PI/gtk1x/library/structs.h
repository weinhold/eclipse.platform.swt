/*
 * Copyright (c) IBM Corp. 2000, 2001.  All rights reserved.
 *
 * The contents of this file are made available under the terms
 * of the GNU Lesser General Public License (LGPL) Version 2.1 that
 * accompanies this distribution (lgpl-v21.txt).  The LGPL is also
 * available at http://www.gnu.org/licenses/lgpl.html.  If the version
 * of the LGPL at http://www.gnu.org is different to the version of
 * the LGPL accompanying this distribution and there is any conflict
 * between the two license versions, the terms of the LGPL accompanying
 * this distribution shall govern.
 */

/**
 * JNI SWT object field getters and setters declarations for GTK structs.
 */

#ifndef INC_structs_H
#define INC_structs_H

#define GTK_ENABLE_BROKEN 1

#include <gtk/gtk.h>
#include <gdk/gdkx.h>

/* ----------- fid and class caches  ----------- */
/**
 * Used for Java objects passed into JNI that are
 * declared like:
 *
 * 	nativeFunction (Rectangle p1, Rectangle p2, Rectangle p3)
 *
 * and not like this
 *
 * 	nativeFunction (Object p1, Object p2, Object p3)
 *
 *
 */

/* ----------- fid cache structures  ----------- */

/* GDK */

typedef struct GdkColor_FID_CACHE {
	int cached;
	jclass GdkColorClass;
	jfieldID pixel, red, green, blue;
} GdkColor_FID_CACHE;

typedef GdkColor_FID_CACHE *PGdkColor_FID_CACHE;

typedef struct GdkEvent_FID_CACHE {
	int cached;
	jclass GdkEventClass;
	jfieldID type, window, send_event;
} GdkEvent_FID_CACHE;

typedef GdkEvent_FID_CACHE *PGdkEvent_FID_CACHE;

typedef struct GdkEventExpose_FID_CACHE {
	int cached;
	jclass GdkEventExposeClass;
	jfieldID x, y, width, height, region, count;
} GdkEventExpose_FID_CACHE;

typedef GdkEventExpose_FID_CACHE *PGdkEventExpose_FID_CACHE;

/* FIXME */
typedef struct GdkFont_FID_CACHE {
	int cached;
	jclass GdkFontClass;
	jfieldID type, ascent, descent;
} GdkFont_FID_CACHE;

typedef GdkFont_FID_CACHE *PGdkFont_FID_CACHE;

typedef struct GdkGCValues_FID_CACHE {
	int cached;
	jclass GdkGCValuesClass;
	jfieldID foreground_pixel, foreground_red, foreground_green, foreground_blue, background_pixel, background_red, background_green, background_blue, font, function, fill, tile, stipple, clip_mask, subwindow_mode, ts_x_origin, ts_y_origin, clip_x_origin, clip_y_origin, graphics_exposures, line_width, line_style, cap_style, join_style;
} GdkGCValues_FID_CACHE;

typedef GdkGCValues_FID_CACHE *PGdkGCValues_FID_CACHE;

typedef struct GdkRectangle_FID_CACHE {
	int cached;
	jclass GdkRectangleClass;
	jfieldID x, y, width, height;
} GdkRectangle_FID_CACHE;

typedef GdkRectangle_FID_CACHE *PGdkRectangle_FID_CACHE;

typedef struct GdkVisual_FID_CACHE {
	int cached;
	jclass GdkVisualClass;
	jfieldID type, depth, byte_order, colormap_size, bits_per_rgb, red_mask, red_shift, red_prec, green_mask, green_shift, green_prec, blue_mask, blue_shift, blue_prec;
} GdkVisual_FID_CACHE;

typedef GdkVisual_FID_CACHE *PGdkVisual_FID_CACHE;

/* GTK */

typedef struct GtkData_FID_CACHE {
	int cached;
	jclass GtkDataClass;
} GtkData_FID_CACHE;

typedef GtkData_FID_CACHE *PGtkData_FID_CACHE;

typedef struct GtkAdjustment_FID_CACHE {
	int cached;
	jclass GtkAdjustmentClass;
	jfieldID lower, upper, value, step_increment, page_increment, page_size;
} GtkAdjustment_FID_CACHE;

typedef GtkAdjustment_FID_CACHE *PGtkAdjustment_FID_CACHE;

typedef struct GtkAllocation_FID_CACHE {
	int cached;
	jclass GtkAllocationClass;
	jfieldID x, y, width, height;
} GtkAllocation_FID_CACHE;

typedef GtkAllocation_FID_CACHE *PGtkAllocation_FID_CACHE;

typedef struct GtkWidget_FID_CACHE {
	int cached;
	jclass GtkWidgetClass;
	jfieldID alloc_x, alloc_y, alloc_width, alloc_height;
} GtkWidget_FID_CACHE;

typedef GtkWidget_FID_CACHE *PGtkWidget_FID_CACHE;

typedef struct GtkContainer_FID_CACHE {
	int cached;
	jclass GtkContainerClass;
	jfieldID focus_child, border_width, need_resize, resize_mode;
} GtkContainer_FID_CACHE;

typedef GtkContainer_FID_CACHE *PGtkContainer_FID_CACHE;

typedef struct GtkBin_FID_CACHE {
	int cached;
	jclass GtkBinClass;
	jfieldID child;
} GtkBin_FID_CACHE;

typedef GtkBin_FID_CACHE *PGtkBin_FID_CACHE;

typedef struct GtkFrame_FID_CACHE {
	int cached;
	jclass GtkFrameClass;
	jfieldID label, shadow_type, 
	         label_width, label_height,
	         label_xalign, label_yalign;
} GtkFrame_FID_CACHE;

typedef GtkFrame_FID_CACHE *PGtkFrame_FID_CACHE;

typedef struct GtkMenu_FID_CACHE {
	int cached;
	jclass GtkMenuClass;
	jfieldID parent_menu_item, old_active_menu_item, accel_group, position_func, position_func_data, toplevel, tearoff_window, torn_off;
} GtkMenu_FID_CACHE;

typedef GtkMenu_FID_CACHE *PGtkMenu_FID_CACHE;

typedef struct GtkMenuShell_FID_CACHE {
	int cached;
	jclass GtkMenuShellClass;
	jfieldID active;
} GtkMenuShell_FID_CACHE;

typedef GtkMenuShell_FID_CACHE *PGtkMenuShell_FID_CACHE;

typedef struct GtkItem_FID_CACHE {
	int cached;
	jclass GtkItemClass;
} GtkItem_FID_CACHE;

typedef GtkItem_FID_CACHE *PGtkItem_FID_CACHE;

typedef struct GtkMenuItem_FID_CACHE {
	int cached;
	jclass GtkMenuItemClass;
	jfieldID submenu, accelerator_signal, toggle_size, accelerator_width, show_toggle_indicator, show_submenu_indicator, submenu_placement, submenu_direction, right_justify, timer;
} GtkMenuItem_FID_CACHE;

typedef GtkMenuItem_FID_CACHE *PGtkMenuItem_FID_CACHE;

typedef struct GtkCheckMenuItem_FID_CACHE {
	int cached;
	jclass GtkCheckMenuItemClass;
	jfieldID active, always_show_toggle;
} GtkCheckMenuItem_FID_CACHE;

typedef GtkCheckMenuItem_FID_CACHE *PGtkCheckMenuItem_FID_CACHE;

typedef struct GtkBox_FID_CACHE {
	int cached;
	jclass GtkBoxClass;
	jfieldID children, spacing, homogeneous;
} GtkBox_FID_CACHE;

typedef GtkBox_FID_CACHE *PGtkBox_FID_CACHE;

typedef struct GtkHBox_FID_CACHE {
	int cached;
	jclass GtkHBoxClass;
} GtkHBox_FID_CACHE;

typedef GtkHBox_FID_CACHE *PGtkHBox_FID_CACHE;

typedef struct GtkCombo_FID_CACHE {
	int cached;
	jclass GtkComboClass;
	jfieldID entry, button, popup, popwin, list, entry_change_id, list_change_id, value_in_list, ok_if_empty, case_sensitive, use_arrows, use_arrows_always, current_button, activate_id;
} GtkCombo_FID_CACHE;

typedef GtkCombo_FID_CACHE *PGtkCombo_FID_CACHE;

typedef struct GtkCList_FID_CACHE {
	int cached;
	jclass GtkCListClass;
	jfieldID clist_flags, row_mem_chunk, cell_mem_chunk, freeze_count, internal_allocation_x, internal_allocation_y, internal_allocation_width, internal_allocation_height, rows, row_height, row_list, row_list_end, columns, column_title_area_x, column_title_area_y, column_title_area_width, column_title_area_height, title_window, column, clist_window, clist_window_width, clist_window_height, hoffset, voffset, shadow_type, selection_mode, selection, selection_end, undo_selection, undo_unselection, undo_anchor, button_actions0, button_actions1, button_actions2, button_actions3, button_actions4, drag_button, click_cell_row, click_cell_column, hadjustment, vadjustment, xor_gc, fg_gc, bg_gc, cursor_drag, x_drag, focus_row, anchor, anchor_state, drag_pos, htimer, vtimer, sort_type, compare, sort_column;
} GtkCList_FID_CACHE;

typedef GtkCList_FID_CACHE *PGtkCList_FID_CACHE;

typedef struct GtkProgress_FID_CACHE {
	int cached;
	jclass GtkProgressClass;
	jfieldID adjustment, offscreen_pixmap, format, x_align, y_align, show_text, activity_mode;
} GtkProgress_FID_CACHE;

typedef GtkProgress_FID_CACHE *PGtkProgress_FID_CACHE;

typedef struct GtkProgressBar_FID_CACHE {
	int cached;
	jclass GtkProgressBarClass;
	jfieldID bar_style, orientation, blocks, in_block, activity_pos, activity_step, activity_blocks, activity_dir;
} GtkProgressBar_FID_CACHE;

typedef GtkProgressBar_FID_CACHE *PGtkProgressBar_FID_CACHE;

typedef struct GtkArg_FID_CACHE {
	int cached;
	jclass GtkArgClass;
	jfieldID type, name, d0, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11;
} GtkArg_FID_CACHE;

typedef GtkArg_FID_CACHE *PGtkArg_FID_CACHE;

typedef struct GtkRequisition_FID_CACHE {
	int cached;
	jclass GtkRequisitionClass;
	jfieldID width, height;
} GtkRequisition_FID_CACHE;

typedef GtkRequisition_FID_CACHE *PGtkRequisition_FID_CACHE;

typedef struct GtkStyle_FID_CACHE {
	int cached;
	jclass GtkStyleClazz;
	jfieldID klass, 
	    fg0_pixel, fg0_red, fg0_green, fg0_blue,
	    fg1_pixel, fg1_red, fg1_green, fg1_blue,
	    fg2_pixel, fg2_red, fg2_green, fg2_blue,
	    fg3_pixel, fg3_red, fg3_green, fg3_blue,
	    fg4_pixel, fg4_red, fg4_green, fg4_blue,
	    bg0_pixel, bg0_red, bg0_green, bg0_blue,
	    bg1_pixel, bg1_red, bg1_green, bg1_blue,
	    bg2_pixel, bg2_red, bg2_green, bg2_blue,
	    bg3_pixel, bg3_red, bg3_green, bg3_blue,
	    bg4_pixel, bg4_red, bg4_green, bg4_blue,
	    light0_pixel, light0_red, light0_green,
	    light0_blue, light1_pixel, light1_red,
	    light1_green, light1_blue, light2_pixel,
	    light2_red, light2_green, light2_blue,
	    light3_pixel, light3_red, light3_green,
	    light3_blue, light4_pixel, light4_red,
	    light4_green, light4_blue, 
	    dark0_pixel, dark0_red, dark0_green, dark0_blue,
	    dark1_pixel, dark1_red, dark1_green, dark1_blue,
	    dark2_pixel, dark2_red, dark2_green, dark2_blue,
	    dark3_pixel, dark3_red, dark3_green, dark3_blue, 
	    dark4_pixel, dark4_red, dark4_green, dark4_blue,
	    mid0_pixel, mid0_red, mid0_green, mid0_blue, 
	    mid1_pixel, mid1_red, mid1_green, mid1_blue, 
	    mid2_pixel, mid2_red, mid2_green, mid2_blue, 
	    mid3_pixel, mid3_red, mid3_green, mid3_blue, 
	    mid4_pixel, mid4_red, mid4_green, mid4_blue, 
	    text0_pixel, text0_red, text0_green, text0_blue, 
	    text1_pixel, text1_red, text1_green, text1_blue, 
	    text2_pixel, text2_red, text2_green, text2_blue, 
	    text3_pixel, text3_red, text3_green, text3_blue, 
	    text4_pixel, text4_red, text4_green, text4_blue, 
	    base0_pixel, base0_red, base0_green, base0_blue, 
	    base1_pixel, base1_red, base1_green, base1_blue, 
	    base2_pixel, base2_red, base2_green, base2_blue, 
	    base3_pixel, base3_red, base3_green, base3_blue, 
	    base4_pixel, base4_red, base4_green, base4_blue, 
	    
	    black_pixel, black_red, black_green, black_blue, 
	    white_pixel, white_red, white_green, white_blue, 
	    
	    font_desc, 
	    
	    fg_gc0, fg_gc1, fg_gc2, fg_gc3, fg_gc4,
	    bg_gc0, bg_gc1, bg_gc2, bg_gc3, bg_gc4,
	    light_gc0, light_gc1, light_gc2, light_gc3, light_gc4,
	    dark_gc0, dark_gc1, dark_gc2, dark_gc3, dark_gc4,
	    mid_gc0, mid_gc1, mid_gc2, mid_gc3, mid_gc4, 
	    text_gc0, text_gc1, text_gc2, text_gc3, text_gc4, 
	    base_gc0, base_gc1, base_gc2, base_gc3, base_gc4, 
	    
	    black_gc, white_gc, 
	    
	    bg_pixmap0, bg_pixmap1, bg_pixmap2, bg_pixmap3, bg_pixmap4, bg_pixmap5;
} GtkStyle_FID_CACHE;

typedef GtkStyle_FID_CACHE *PGtkStyle_FID_CACHE;

typedef struct GtkStyleClass_FID_CACHE {
	int cached;
	jclass GtkStyleClassClazz;
	jfieldID xthickness, ythickness;
} GtkStyleClass_FID_CACHE;

typedef GtkStyleClass_FID_CACHE *PGtkStyleClass_FID_CACHE;

typedef struct GtkCListRow_FID_CACHE {
	int cached;
	jclass GtkCListRowClass;
	jfieldID cell, state, foreground_red, foreground_green, foreground_blue, foreground_pixel, background_red, background_green, background_blue, background_pixel, style, data, destroy, fg_set, bg_set, selectable;
} GtkCListRow_FID_CACHE;

typedef GtkCListRow_FID_CACHE *PGtkCListRow_FID_CACHE;

typedef struct GtkCListColumn_FID_CACHE {
	int cached;
	jclass GtkCListColumnClass;
	jfieldID title, area_x, area_y, area_width, area_height, button, window, width, min_width, max_width, justification, visible, width_set, resizeable, auto_resize, button_passive;
} GtkCListColumn_FID_CACHE;

typedef GtkCListColumn_FID_CACHE *PGtkCListColumn_FID_CACHE;

typedef struct GtkCTreeRow_FID_CACHE {
	int cached;
	jclass GtkCTreeRowClass;
	jfieldID parent, sibling, children, pixmap_closed, mask_closed, pixmap_opened, mask_opened, level, is_leaf, expanded;
} GtkCTreeRow_FID_CACHE;

typedef GtkCTreeRow_FID_CACHE *PGtkCTreeRow_FID_CACHE;

typedef struct GtkCTree_FID_CACHE {
	int cached;
	jclass GtkCTreeClass;
	jfieldID tree_indent, tree_column;
	
} GtkCTree_FID_CACHE;

typedef GtkCTree_FID_CACHE *PGtkCTree_FID_CACHE;

/* ----------- cache function prototypes  ----------- */

void cacheGdkColorFids(JNIEnv *env, jobject lpGdkColor, PGdkColor_FID_CACHE lpCache);
void cacheGdkEventExposeFids(JNIEnv *env, jobject lpGdkEventExpose, PGdkEventExpose_FID_CACHE lpCache);
void cacheGdkFontFids(JNIEnv *env, jobject lpGdkFont, PGdkFont_FID_CACHE lpCache);
void cacheGdkGCValuesFids(JNIEnv *env, jobject lpGdkGCValues, PGdkGCValues_FID_CACHE lpCache);
void cacheGdkRectangleFids(JNIEnv *env, jobject lpGdkRectangle, PGdkRectangle_FID_CACHE lpCache);
void cacheGdkVisualFids(JNIEnv *env, jobject lpGdkVisual, PGdkVisual_FID_CACHE lpCache);

void cacheGtkDataFids(JNIEnv *env, jobject lpGtkData, PGtkData_FID_CACHE lpCache);
void cacheGtkAdjustmentFids(JNIEnv *env, jobject lpGtkAdjustment, PGtkAdjustment_FID_CACHE lpCache);
void cacheGtkWidgetFids(JNIEnv *env, jobject lpGtkWidget, PGtkWidget_FID_CACHE lpCache);
void cacheGtkContainerFids(JNIEnv *env, jobject lpGtkContainer, PGtkContainer_FID_CACHE lpCache);
void cacheGtkBinFids(JNIEnv *env, jobject lpGtkBin, PGtkBin_FID_CACHE lpCache);
void cacheGtkMenuFids(JNIEnv *env, jobject lpGtkMenu, PGtkMenu_FID_CACHE lpCache);
void cacheGtkMenuShellFids(JNIEnv *env, jobject lpGtkMenuShell, PGtkMenuShell_FID_CACHE lpCache);
void cacheGtkItemFids(JNIEnv *env, jobject lpGtkItem, PGtkItem_FID_CACHE lpCache);
void cacheGtkMenuItemFids(JNIEnv *env, jobject lpGtkMenuItem, PGtkMenuItem_FID_CACHE lpCache);
void cacheGtkCheckMenuItemFids(JNIEnv *env, jobject lpGtkCheckMenuItem, PGtkCheckMenuItem_FID_CACHE lpCache);
void cacheGtkBoxFids(JNIEnv *env, jobject lpGtkBox, PGtkBox_FID_CACHE lpCache);
void cacheGtkHBoxFids(JNIEnv *env, jobject lpGtkHBox, PGtkHBox_FID_CACHE lpCache);
void cacheGtkComboFids(JNIEnv *env, jobject lpGtkCombo, PGtkCombo_FID_CACHE lpCache);
void cacheGtkCListFids(JNIEnv *env, jobject lpGtkCList, PGtkCList_FID_CACHE lpCache);
void cacheGtkProgressFids(JNIEnv *env, jobject lpGtkProgress, PGtkProgress_FID_CACHE lpCache);
void cacheGtkProgressBarFids(JNIEnv *env, jobject lpGtkProgressBar, PGtkProgressBar_FID_CACHE lpCache);

void cacheGtkAllocationFids(JNIEnv *env, jobject lpGtkAllocation, PGtkAllocation_FID_CACHE lpCache);
void cacheGtkArgFids(JNIEnv *env, jobject lpGtkArg, PGtkArg_FID_CACHE lpCache);
void cacheGtkRequisitionFids(JNIEnv *env, jobject lpGtkRequisition, PGtkRequisition_FID_CACHE lpCache);
void cacheGtkStyleFids(JNIEnv *env, jobject lpGtkStyle, PGtkStyle_FID_CACHE lpCache);
void cacheGtkStyleClassFids(JNIEnv *env, jobject lpGtkStyleClass, PGtkStyleClass_FID_CACHE lpCache);
void cacheGtkCListColumnFids(JNIEnv *env, jobject lpGtkCListColumn, PGtkCListColumn_FID_CACHE lpCache);
void cacheGtkCListRowFids(JNIEnv *env, jobject lpGtkCListRow, PGtkCListRow_FID_CACHE lpCache);
void cacheGtkCTreeRowFids(JNIEnv *env, jobject lpGtkCTreeRow, PGtkCTreeRow_FID_CACHE lpCache);
void cacheGtkCTreeFids(JNIEnv *env, jobject lpGtkCTree, PGtkCTree_FID_CACHE lpCache);

/* ----------- swt getter and setter prototypes  ----------- */
/**
 * These functions get or set object field ids assuming that the
 * fids for these objects have already been cached.
 *
 * The header file just contains function prototypes
 */
void getGdkColorFields(JNIEnv *env, jobject lpObject, GdkColor *lpGdkColor, GdkColor_FID_CACHE *lpGdkColorFc);
void setGdkColorFields(JNIEnv *env, jobject lpObject, GdkColor *lpGdkColor, GdkColor_FID_CACHE *lpGdkColorFc);
void getGdkEventExposeFields(JNIEnv *env, jobject lpObject, GdkEvent *lpGdkEvent, GdkEventExpose_FID_CACHE *lpGdkEventExposeFc);
void setGdkEventExposeFields(JNIEnv *env, jobject lpObject, GdkEvent *lpGdkEvent, GdkEventExpose_FID_CACHE *lpGdkEventExposeFc);
void getGdkFontFields(JNIEnv *env, jobject lpObject, GdkFont *lpGdkFont, GdkFont_FID_CACHE *lpGdkFontFc);
void setGdkFontFields(JNIEnv *env, jobject lpObject, GdkFont *lpGdkFont, GdkFont_FID_CACHE *lpGdkFontFc);
void getGdkGCValuesFields(JNIEnv *env, jobject lpObject, GdkGCValues *lpGdkGCValues, GdkGCValues_FID_CACHE *lpGdkGCValuesFc);
void setGdkGCValuesFields(JNIEnv *env, jobject lpObject, GdkGCValues *lpGdkGCValues, GdkGCValues_FID_CACHE *lpGdkGCValuesFc);
void getGdkRectangleFields(JNIEnv *env, jobject lpObject, GdkRectangle *lpGdkRectangle, GdkRectangle_FID_CACHE *lpGdkRectangleFc);
void setGdkRectangleFields(JNIEnv *env, jobject lpObject, GdkRectangle *lpGdkRectangle, GdkRectangle_FID_CACHE *lpGdkRectangleFc);
void getGdkVisualFields(JNIEnv *env, jobject lpObject, GdkVisual *lpGdkVisual, GdkVisual_FID_CACHE *lpGdkVisualFc);
void setGdkVisualFields(JNIEnv *env, jobject lpObject, GdkVisual *lpGdkVisual, GdkVisual_FID_CACHE *lpGdkVisualFc);
void getGtkAllocationFields(JNIEnv *env, jobject lpObject, GtkAllocation *lpGtkAllocation, GtkAllocation_FID_CACHE *lpGtkAllocationFc);
void setGtkAllocationFields(JNIEnv *env, jobject lpObject, GtkAllocation *lpGtkAllocation, GtkAllocation_FID_CACHE *lpGtkAllocationFc);
void getGtkArgFields(JNIEnv *env, jobject lpObject, GtkArg *lpGtkArg, GtkArg_FID_CACHE *lpGtkArgFc);
void setGtkArgFields(JNIEnv *env, jobject lpObject, GtkArg *lpGtkArg, GtkArg_FID_CACHE *lpGtkArgFc);
void getGtkBinFields(JNIEnv *env, jobject lpObject, GtkBin *lpGtkBin, GtkBin_FID_CACHE *lpGtkBinFc);
void setGtkBinFields(JNIEnv *env, jobject lpObject, GtkBin *lpGtkBin, GtkBin_FID_CACHE *lpGtkBinFc);
void getGtkCListFields(JNIEnv *env, jobject lpObject, GtkCList *lpGtkCList, GtkCList_FID_CACHE *lpGtkCListFc);
void setGtkCListFields(JNIEnv *env, jobject lpObject, GtkCList *lpGtkCList, GtkCList_FID_CACHE *lpGtkCListFc);
void getGtkCListRowFields(JNIEnv *env, jobject lpObject, GtkCListRow *lpGtkCListRow, GtkCListRow_FID_CACHE *lpGtkCListRowFc);
void setGtkCListRowFields(JNIEnv *env, jobject lpObject, GtkCListRow *lpGtkCListRow, GtkCListRow_FID_CACHE *lpGtkCListRowFc);
void getGtkCListColumnFields(JNIEnv *env, jobject lpObject, GtkCListColumn *lpGtkCListColumn, GtkCListColumn_FID_CACHE *lpGtkCListColumnFc);
void setGtkCListColumnFields(JNIEnv *env, jobject lpObject, GtkCListColumn *lpGtkCListColumn, GtkCListColumn_FID_CACHE *lpGtkCListColumnFc);
void getGtkCTreeRowFields(JNIEnv *env, jobject lpObject, GtkCTreeRow *lpGtkCTreeRow, GtkCTreeRow_FID_CACHE *lpGtkCTreeRowFc);
void setGtkCTreeRowFields(JNIEnv *env, jobject lpObject, GtkCTreeRow *lpGtkCTreeRow, GtkCTreeRow_FID_CACHE *lpGtkCTreeRowFc);
void getGtkComboFields(JNIEnv *env, jobject lpObject, GtkCombo *lpGtkCombo, GtkCombo_FID_CACHE *lpGtkComboFc);
void setGtkComboFields(JNIEnv *env, jobject lpObject, GtkCombo *lpGtkCombo, GtkCombo_FID_CACHE *lpGtkComboFc);
void getGtkContainerFields(JNIEnv *env, jobject lpObject, GtkContainer *lpGtkContainer, GtkContainer_FID_CACHE *lpGtkContainerFc);
void setGtkContainerFields(JNIEnv *env, jobject lpObject, GtkContainer *lpGtkContainer, GtkContainer_FID_CACHE *lpGtkContainerFc);
void getGtkProgressFields(JNIEnv *env, jobject lpObject, GtkProgress *lpGtkProgress, GtkProgress_FID_CACHE *lpGtkProgressFc);
void setGtkProgressFields(JNIEnv *env, jobject lpObject, GtkProgress *lpGtkProgress, GtkProgress_FID_CACHE *lpGtkProgressFc);
void getGtkProgressBarFields(JNIEnv *env, jobject lpObject, GtkProgressBar *lpGtkProgressBar, GtkProgressBar_FID_CACHE *lpGtkProgressBarFc);
void setGtkProgressBarFields(JNIEnv *env, jobject lpObject, GtkProgressBar *lpGtkProgressBar, GtkProgressBar_FID_CACHE *lpGtkProgressBarFc);
void getGtkRequisitionFields(JNIEnv *env, jobject lpObject, GtkRequisition *lpGtkRequisition, GtkRequisition_FID_CACHE *lpGtkRequisitionFc);
void setGtkRequisitionFields(JNIEnv *env, jobject lpObject, GtkRequisition *lpGtkRequisition, GtkRequisition_FID_CACHE *lpGtkRequisitionFc);
void getGtkStyleFields(JNIEnv *env, jobject lpObject, GtkStyle *lpGtkStyle, GtkStyle_FID_CACHE *lpGtkStyleFc);
void setGtkStyleFields(JNIEnv *env, jobject lpObject, GtkStyle *lpGtkStyle, GtkStyle_FID_CACHE *lpGtkStyleFc);
void getGtkStyleClassFields(JNIEnv *env, jobject lpObject, GtkStyleClass *lpGtkStyleClass, GtkStyleClass_FID_CACHE *lpGtkStyleClassFc);
void setGtkStyleClassFields(JNIEnv *env, jobject lpObject, GtkStyleClass *lpGtkStyleClass, GtkStyleClass_FID_CACHE *lpGtkStyleClassFc);
void getGtkWidgetFields(JNIEnv *env, jobject lpObject, GtkWidget *lpGtkWidget, GtkWidget_FID_CACHE *lpGtkWidgetFc);
void setGtkWidgetFields(JNIEnv *env, jobject lpObject, GtkWidget *lpGtkWidget, GtkWidget_FID_CACHE *lpGtkWidgetFc);
void getGtkCheckMenuItemFields(JNIEnv *env, jobject lpObject, GtkCheckMenuItem *lpGtkCheckMenuItem, GtkCheckMenuItem_FID_CACHE *lpGtkCheckMenuItemFc);
void setGtkCheckMenuItemFields(JNIEnv *env, jobject lpObject, GtkCheckMenuItem *lpGtkCheckMenuItem, GtkCheckMenuItem_FID_CACHE *lpGtkCheckMenuItemFc);
void getGtkAdjustmentFields(JNIEnv *env, jobject lpObject, GtkAdjustment *lpGtkAdjustment, GtkAdjustment_FID_CACHE *lpGtkAdjustmentFc);
void setGtkAdjustmentFields(JNIEnv *env, jobject lpObject, GtkAdjustment *lpGtkAdjustment, GtkAdjustment_FID_CACHE *lpGtkAdjustmentFc);
void getGtkBoxFields(JNIEnv *env, jobject lpObject, GtkBox *lpGtkBox, GtkBox_FID_CACHE *lpGtkBoxFc);
void setGtkBoxFields(JNIEnv *env, jobject lpObject, GtkBox *lpGtkBox, GtkBox_FID_CACHE *lpGtkBoxFc);
void getGtkHBoxFields(JNIEnv *env, jobject lpObject, GtkHBox *lpGtkHBox, GtkHBox_FID_CACHE *lpGtkHBoxFc);
void setGtkHBoxFields(JNIEnv *env, jobject lpObject, GtkHBox *lpGtkHBox, GtkHBox_FID_CACHE *lpGtkHBoxFc);
void getGtkMenuFields(JNIEnv *env, jobject lpObject, GtkMenu *lpGtkMenu, GtkMenu_FID_CACHE *lpGtkMenuFc);
void setGtkMenuFields(JNIEnv *env, jobject lpObject, GtkMenu *lpGtkMenu, GtkMenu_FID_CACHE *lpGtkMenuFc);
void getGtkMenuShellFields(JNIEnv *env, jobject lpObject, GtkMenuShell *lpGtkMenuShell, GtkMenuShell_FID_CACHE *lpGtkMenuShellFc);
void setGtkMenuShellFields(JNIEnv *env, jobject lpObject, GtkMenuShell *lpGtkMenuShell, GtkMenuShell_FID_CACHE *lpGtkMenuShellFc);
void getGtkMenuItemFields(JNIEnv *env, jobject lpObject, GtkMenuItem *lpGtkMenuItem, GtkMenuItem_FID_CACHE *lpGtkMenuItemFc);
void setGtkMenuItemFields(JNIEnv *env, jobject lpObject, GtkMenuItem *lpGtkMenuItem, GtkMenuItem_FID_CACHE *lpGtkMenuItemFc);
void getGtkCTreeFields(JNIEnv *env, jobject lpObject, GtkCTree *lpGtkCTree, GtkCTree_FID_CACHE *lpGtkCTreeFc);
void setGtkCTreeFields(JNIEnv *env, jobject lpObject, GtkCTree *lpGtkCTree, GtkCTree_FID_CACHE *lpGtkCTreeFc);

extern GdkColor_FID_CACHE GdkColorFc;
extern GdkEventExpose_FID_CACHE GdkEventExposeFc;
extern GdkEvent_FID_CACHE GdkEventFc;
extern GdkFont_FID_CACHE GdkFontFc;
extern GdkGCValues_FID_CACHE GdkGCValuesFc;
extern GdkRectangle_FID_CACHE GdkRectangleFc;
extern GdkVisual_FID_CACHE GdkVisualFc;

extern GtkAdjustment_FID_CACHE GtkAdjustmentFc;
extern GtkAllocation_FID_CACHE GtkAllocationFc;
extern GtkBin_FID_CACHE GtkBinFc;
extern GtkCheckMenuItem_FID_CACHE GtkCheckMenuItemFc;
extern GtkContainer_FID_CACHE GtkContainerFc;
extern GtkCListRow_FID_CACHE GtkCListRowFc;
extern GtkCListColumn_FID_CACHE GtkCListColumnFc;
extern GtkCTreeRow_FID_CACHE GtkCTreeRowFc;
extern GtkCTree_FID_CACHE GtkCTreeFc;
extern GtkCList_FID_CACHE GtkCListFc;
extern GtkData_FID_CACHE GtkDataFc;
extern GtkItem_FID_CACHE GtkItemFc;
extern GtkMenu_FID_CACHE GtkMenuFc;
extern GtkMenuShell_FID_CACHE GtkMenuShellFc;
extern GtkMenuItem_FID_CACHE GtkMenuItemFc;
extern GtkWidget_FID_CACHE GtkWidgetFc;
extern GtkCombo_FID_CACHE GtkComboFc;
extern GtkFrame_FID_CACHE GtkFrameFc;
extern GtkProgress_FID_CACHE GtkProgressFc;
extern GtkProgressBar_FID_CACHE GtkProgressBarFc;
extern GtkArg_FID_CACHE GtkArgFc;
extern GtkRequisition_FID_CACHE GtkRequisitionFc;
extern GtkStyle_FID_CACHE GtkStyleFc;
extern GtkStyleClass_FID_CACHE GtkStyleClassFc;

#endif // INC_structs_H
