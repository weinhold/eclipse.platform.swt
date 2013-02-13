package org.eclipse.swt.internal.cef3;

public class cef_command_line_t extends cef_base_t {
	  ///
	  // Returns true (1) if this object is valid. Do not call any other functions
	  // if this function returns false (0).
	  ///
	  //int (CEF_CALLBACK *is_valid)(struct _cef_command_line_t* self);
	public long /*int*/ is_valid;

	  ///
	  // Returns true (1) if the values of this object are read-only. Some APIs may
	  // expose read-only objects.
	  ///
	  //int (CEF_CALLBACK *is_read_only)(struct _cef_command_line_t* self);
	public long /*int*/ is_read_only;

	  ///
	  // Returns a writable copy of this object.
	  ///
	  //struct _cef_command_line_t* (CEF_CALLBACK *copy)(struct _cef_command_line_t* self);
	public long /*int*/ copy;

	  ///
	  // Initialize the command line with the specified |argc| and |argv| values.
	  // The first argument must be the name of the program. This function is only
	  // supported on non-Windows platforms.
	  /// 
	  //void (CEF_CALLBACK *init_from_argv)(struct _cef_command_line_t* self, int argc, const char* const* argv);
	public long /*int*/ init_from_argv;

	  ///
	  // Initialize the command line with the string returned by calling
	  // GetCommandLineW(). This function is only supported on Windows.
	  ///
	  //void (CEF_CALLBACK *init_from_string)(struct _cef_command_line_t* self, const cef_string_t* command_line);
	public long /*int*/ init_from_string;

	  ///
	  // Reset the command-line switches and arguments but leave the program
	  // component unchanged.
	  ///
	  //void (CEF_CALLBACK *reset)(struct _cef_command_line_t* self);
	public long /*int*/ reset;

	  ///
	  // Constructs and returns the represented command line string. Use this
	  // function cautiously because quoting behavior is unclear.
	  ///
	  // The resulting string must be freed by calling cef_string_userfree_free().
	  //cef_string_userfree_t (CEF_CALLBACK *get_command_line_string)(struct _cef_command_line_t* self);
	public long /*int*/ get_command_line_string;

	  ///
	  // Get the program part of the command line string (the first item).
	  ///
	  // The resulting string must be freed by calling cef_string_userfree_free().
	  //cef_string_userfree_t (CEF_CALLBACK *get_program)(struct _cef_command_line_t* self);
	public long /*int*/ get_program;
	
	  ///
	  // Set the program part of the command line string (the first item).
	  ///
	  //void (CEF_CALLBACK *set_program)(struct _cef_command_line_t* self, const cef_string_t* program);
	public long /*int*/ set_program;

	  ///
	  // Returns true (1) if the command line has switches.
	  ///
	  //int (CEF_CALLBACK *has_switches)(struct _cef_command_line_t* self);

	  ///
	  // Returns true (1) if the command line contains the given switch.
	  ///
	  //int (CEF_CALLBACK *has_switch)(struct _cef_command_line_t* self, const cef_string_t* name);
	public long /*int*/ has_switch;

	  ///
	  // Returns the value associated with the given switch. If the switch has no
	  // value or isn't present this function returns the NULL string.
	  ///
	  // The resulting string must be freed by calling cef_string_userfree_free().
	  //cef_string_userfree_t (CEF_CALLBACK *get_switch_value)(struct _cef_command_line_t* self, const cef_string_t* name);
	public long /*int*/ get_switch_value; 

	  ///
	  // Returns the map of switch names and values. If a switch has no value an
	  // NULL string is returned.
	  ///
	  //void (CEF_CALLBACK *get_switches)(struct _cef_command_line_t* self, cef_string_map_t switches);
	public long /*int*/ get_switches;

	  ///
	  // Add a switch to the end of the command line. If the switch has no value
	  // pass an NULL value string.
	  ///
	  //void (CEF_CALLBACK *append_switch)(struct _cef_command_line_t* self, const cef_string_t* name);
	public long /*int*/ append_switch;

	  ///
	  // Add a switch with the specified value to the end of the command line.
	  ///
	  //void (CEF_CALLBACK *append_switch_with_value)(struct _cef_command_line_t* self, const cef_string_t* name, const cef_string_t* value);
	public long /*int*/ append_switch_with_value;

	  ///
	  // True if there are remaining command line arguments.
	  ///
	  //int (CEF_CALLBACK *has_arguments)(struct _cef_command_line_t* self);
	public long /*int*/ has_arguments;

	  ///
	  // Get the remaining command line arguments.
	  ///
	  //void (CEF_CALLBACK *get_arguments)(struct _cef_command_line_t* self, cef_string_list_t arguments);
	public long /*int*/ get_arguments;

	  ///
	  // Add an argument to the end of the command line.
	  ///
	  //void (CEF_CALLBACK *append_argument)(struct _cef_command_line_t* self, const cef_string_t* argument);
	public long /*int*/ append_argument;

	  ///
	  // Insert a command before the current command. Common for debuggers, like
	  // "valgrind" or "gdb --args".
	  ///
	  //void (CEF_CALLBACK *prepend_wrapper)(struct _cef_command_line_t* self, const cef_string_t* wrapper);
	public long /*int*/ prepend_wrapper;
	
	public static final int sizeof = CEF3.cef_command_line_t_sizeof();
}
