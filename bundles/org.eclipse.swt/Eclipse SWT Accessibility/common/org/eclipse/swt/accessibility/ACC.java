/*******************************************************************************
 * Copyright (c) 2000, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.accessibility;


/**
 * Class ACC contains all the constants used in defining an
 * Accessible object.
 *
 * @see <a href="http://www.eclipse.org/swt/">Sample code and further information</a>
 * 
 * @since 2.0
 */
public class ACC {
	public static final int STATE_NORMAL = 0x00000000;
	public static final int STATE_SELECTED = 0x00000002;
	public static final int STATE_SELECTABLE = 0x00200000;
	public static final int STATE_MULTISELECTABLE = 0x1000000;
	public static final int STATE_FOCUSED = 0x00000004;
	public static final int STATE_FOCUSABLE = 0x00100000;
	public static final int STATE_PRESSED = 0x8;
	public static final int STATE_CHECKED = 0x10;
	public static final int STATE_EXPANDED = 0x200;
	public static final int STATE_COLLAPSED = 0x400;
	public static final int STATE_HOTTRACKED = 0x80;
	public static final int STATE_BUSY = 0x800;
	public static final int STATE_READONLY = 0x40;
	public static final int STATE_INVISIBLE = 0x8000;
	public static final int STATE_OFFSCREEN = 0x10000;
	public static final int STATE_SIZEABLE = 0x20000;
	public static final int STATE_LINKED = 0x400000;
//	public static final int STATE_UNAVAILABLE = 0x1;
//	public static final int STATE_DEFAULT = 0x100;
//	public static final int STATE_FLOATING = 0x1000;
//	public static final int STATE_MARQUEED = 0x2000;
//	public static final int STATE_ANIMATED = 0x4000;
//	public static final int STATE_MOVEABLE = 0x40000;
//	public static final int STATE_SELFVOICING = 0x80000;
//	public static final int STATE_TRAVERSED = 0x800000;
//	public static final int STATE_EXTSELECTABLE = 0x2000000;
//	public static final int STATE_ALERT_LOW = 0x4000000;
//	public static final int STATE_ALERT_MEDIUM = 0x8000000;
//	public static final int STATE_ALERT_HIGH = 0x10000000;
//	public static final int STATE_PROTECTED = 0x20000000;
//	public static final int STATE_HASPOPUP = 0x40000000;

	public static final int ROLE_CLIENT_AREA = 0xa;
	public static final int ROLE_WINDOW = 0x9;
	public static final int ROLE_MENUBAR = 0x2;
	public static final int ROLE_MENU = 0xb;
	public static final int ROLE_MENUITEM = 0xc;
	public static final int ROLE_SEPARATOR = 0x15;
	public static final int ROLE_TOOLTIP = 0xd;
	public static final int ROLE_SCROLLBAR = 0x3;
	public static final int ROLE_DIALOG = 0x12;
	public static final int ROLE_LABEL = 0x29;
	public static final int ROLE_PUSHBUTTON = 0x2b;
	public static final int ROLE_CHECKBUTTON = 0x2c;
	public static final int ROLE_RADIOBUTTON = 0x2d;
	/** @since 3.5 */
	public static final int ROLE_SPLITBUTTON = 0x3e;
	public static final int ROLE_COMBOBOX = 0x2e;
	public static final int ROLE_TEXT = 0x2a;
	public static final int ROLE_TOOLBAR = 0x16;
	public static final int ROLE_LIST = 0x21;
	public static final int ROLE_LISTITEM = 0x22;
	public static final int ROLE_TABLE = 0x18;
	public static final int ROLE_TABLECELL = 0x1d;
	public static final int ROLE_TABLECOLUMNHEADER = 0x19;
	/** @deprecated use ROLE_TABLECOLUMNHEADER */
	public static final int ROLE_TABLECOLUMN = ROLE_TABLECOLUMNHEADER;
	public static final int ROLE_TABLEROWHEADER = 0x1a;
	public static final int ROLE_TREE = 0x23;
	public static final int ROLE_TREEITEM = 0x24;
	public static final int ROLE_TABFOLDER = 0x3c;
	public static final int ROLE_TABITEM = 0x25;
	public static final int ROLE_PROGRESSBAR = 0x30;
	public static final int ROLE_SLIDER = 0x33;
	public static final int ROLE_LINK = 0x1e;
	/** @since 3.6 */
	public static final int ROLE_ALERT = 0x08;
	/** @since 3.6 */
	public static final int ROLE_ANIMATION = 0x36;
	/** @since 3.6 */
	public static final int ROLE_CANVAS = 0;
	/** @since 3.6 */
	public static final int ROLE_DOCUMENT = 0x0f;
	/** @since 3.6 */
	public static final int ROLE_GROUP = 0x14;
	/** @since 3.6 */
	public static final int ROLE_HEADING = 0;
	/** @since 3.6 */
	public static final int ROLE_GRAPHIC = 0x28;
	/** @since 3.6 */
	public static final int ROLE_SPINBUTTON = 0x34;
	/** @since 3.6 */
	public static final int ROLE_STATUSBAR = 0x17;
	/** @since 3.6 */
	public static final int ROLE_CHECK_MENU_ITEM = 0x403; // maybe ??
	/** @since 3.6 */
	public static final int ROLE_RADIO_MENU_ITEM = 0x431; // maybe ??
	/** @since 3.6 */
	public static final int ROLE_CLOCK = 0x3d; // maybe ??
	/** @since 3.6 */
	public static final int ROLE_DATE_EDITOR = 0x405; // maybe ??
	/** @since 3.6 */
	public static final int ROLE_COLOR_CHOOSER = 0x404; // maybe ??
	/** @since 3.6 */
	public static final int ROLE_FILE_CHOOSER = 0x412; // maybe ??
	/** @since 3.6 */
	public static final int ROLE_FONT_CHOOSER = 0x413; // maybe ??

	public static final int CHILDID_SELF = -1;
	public static final int CHILDID_NONE = -2;
	public static final int CHILDID_MULTIPLE = -3;
	
	public static final int TEXT_INSERT = 0;
	public static final int TEXT_DELETE = 1;

	/**
	 * Sent when an object is shown, for example for tool tips,
	 * or child objects within a browser document.
	 * 
	 * @since 3.6
	 */
	static final int EVENT_SHOW = 0x8002;
	
	/**
	 * Sent when an object is hidden, for example for child objects within a browser document.
	 * Note: do not send this for child objects when parent object is hidden.
	 * 
	 * @since 3.6
	 */
	static final int EVENT_HIDE = 0x8003;
	
	/**
	 * Sent when the z-order of objects in a container has changed,
	 * for example for a browser document which has been loaded or refreshed.
	 * 
	 * @since 3.6
	 */
	static final int EVENT_REORDER = 0x8004;
	
	/**
	 * Sent when the item with the selection is moved to a different item within a container.
	 * 
	 * @since 3.6
	 */
	static final int EVENT_SELECTION = 0x8006;
	
	/**
	 * Sent when a new item has been added to the selection within a container.
	 * 
	 *  @since 3.6
	 */
	static final int EVENT_SELECTIONADD = 0x8007;
	
	/**
	 * Sent when an item has been removed from the selection within a container.
	 * 
	 * @since 3.6
	 */
	static final int EVENT_SELECTIONREMOVE = 0x8008;
	
	/**
	 * Sent when an object's text selection has changed.
	 * 
	 * @since 3.6
	 */
	static final int EVENT_TEXTSELECTIONCHANGED = 0x8014;
	
	/**
	 * Sent when an object's state has changed, for example enabled/disabled, pressed/released, or checked/unchecked.
	 * 
	 * @since 3.6
	 */
	static final int EVENT_STATECHANGE = 0x800A;
	
	/**
	 * Sent when an object moves. Note: only send one notification for the topmost object that has changed.
	 * 
	 * @since 3.6
	 */
	static final int EVENT_LOCATIONCHANGE = 0x800B;
	
	/**
	 * Sent when an object's name has changed.
	 * 
	 * @since 3.6
	 */
	static final int EVENT_NAMECHANGE = 0x800C;
	
	/**
	 * Sent when an object's description has changed.
	 * 
	 * @since 3.6
	 */
	static final int EVENT_DESCRIPTIONCHANGE = 0x800D;
	
	/**
	 * Sent when an object's value has changed.
	 * 
	 * @since 3.6
	 */
	static final int EVENT_VALUECHANGE = 0x800E;
	
	/**
	 * Sent when entering into menu mode.
	 * 
	 * @since 3.6
	 */
	static final int EVENT_MENUSTART = 0x0004;
	
	/**
	 * Sent when leaving from menu mode.
	 * 
	 * @since 3.6
	 */
	static final int EVENT_MENUEND = 0x0005;
	
	/**
	 * Sent when a popup menu comes up.
	 * 
	 * @since 3.6
	 */
	static final int EVENT_MENUPOPUPSTART = 0x0006;
	
	/**
	 * Sent just before a popup menu is taken down.
	 * 
	 * @since 3.6
	 */
	static final int EVENT_MENUPOPUPEND = 0x0007;

	/**
	 * Sent when the loading of a document has completed.
	 * 
	 * @since 3.6
	 */
	public static final int EVENT_DOCUMENT_LOAD_COMPLETE = 0x105;

	/**
	 * Sent when the loading of a document was interrupted.
	 * 
	 * @since 3.6
	 */
	public static final int EVENT_DOCUMENT_LOAD_STOPPED = 0x106;

	/**
	 * Sent when the document contents are being reloaded.
	 * 
	 * @since 3.6
	 */
	public static final int EVENT_DOCUMENT_RELOAD = 0x107;

	/**
	 * Sent when a slide changed in a presentation document
	 * or a page boundary was crossed in a word processing document. 
	 * 
	 * @since 3.6
	 */
	public static final int EVENT_PAGE_CHANGED = 0x111;

	/**
	 * Sent when the caret moved from one section to the next.
	 * 
	 * @since 3.6
	 */
	public static final int EVENT_SECTION_CHANGED = 0x112;

	/**
	 * Sent when the caret has moved to a new position.
	 * 
	 * @since 3.6
	 */
	public static final int EVENT_TEXT_CARET_MOVED = 0x11b; 

	/**
	 * Sent when the caret moved from one column to the next.
	 * 
	 * @since 3.6
	 */
	public static final int EVENT_TEXT_COLUMN_CHANGED = 0x11d;

	/**
	 * Some attribute of this object is affected by a target object.
	 * 
	 * @since 3.6
	 */
	public static final int RELATION_CONTROLLED_BY = 1;

	/**
	 * This object is interactive and controls some attribute of a target object.
	 * 
	 * @since 3.6
	 */
	public static final int RELATION_CONTROLLER_FOR = 2;

	/**
	 * This object is described by the target object.
	 * 
	 * @since 3.6
	 */
	public static final int RELATION_DESCRIBED_BY = 3;

	/**
	 * This object is describes the target object.
	 * 
	 * @since 3.6
	 */
	public static final int RELATION_DESCRIPTION_FOR = 4;

	/**
	 * This object is embedded by a target object.
	 * 
	 * @since 3.6
	 */
	public static final int RELATION_EMBEDDED_BY = 5;

	/**
	 * This object embeds a target object. This relation can be used on a
	 * control's accessible to show where the content areas are.
	 * 
	 * @since 3.6
	 */
	public static final int RELATION_EMBEDS = 6;

	/**
	 * Content flows to this object from a target object. 
	 * This relation and RELATION_FLOWS_TO are useful to tie text and non-text
	 * objects together in order to allow assistive technology to follow the
	 * intended reading order.
	 * 
	 * @since 3.6
	 */
	public static final int RELATION_FLOWS_FROM = 7;

	/**
	 * Content flows from this object to a target object.
	 * 
	 * @since 3.6
	 */
	public static final int RELATION_FLOWS_TO = 8;

	/**
	 * This object is label for a target object.
	 * 
	 * @since 3.6
	 */
	public static final int RELATION_LABEL_FOR = 9;

	/**
	 * This object is labelled by a target object.
	 * 
	 * @since 3.6
	 */
	public static final int RELATION_LABELLED_BY = 10;

	/**
	 * This object is a member of a group of one or more objects. When 
	 * there is more than one object in the group each member may have one and the 
	 * same target, e.g. a grouping object.  It is also possible that each member has 
	 * multiple additional targets, e.g. one for every other member in the group.
	 * 
	 * @since 3.6
	 */
	public static final int RELATION_MEMBER_OF = 11;

	/**
	 * This object is a child of a target object.
	 * 
	 * @since 3.6
	 */
	public static final int RELATION_NODE_CHILD_OF = 12;

	/**
	 * This object is a parent window of the target object.
	 * 
	 * @since 3.6
	 */
	public static final int RELATION_PARENT_WINDOW_OF = 13;

	/**
	 * This object is a transient component related to the target object. 
	 * When this object is activated the target object doesn't lose focus.
	 * 
	 * @since 3.6
	 */
	public static final int RELATION_POPUP_FOR = 14;

	/**
	 * This object is a sub window of a target object.
	 * 
	 * @since 3.6
	 */
	public static final int RELATION_SUBWINDOW_OF = 15;
}
