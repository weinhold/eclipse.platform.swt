# Copyright (c) IBM Corp. 2000, 2001.  All rights reserved.
#
# The contents of this file are made available under the terms
# of the GNU Lesser General Public License (LGPL) Version 2.1 that
# accompanies this distribution (lgpl-v21.txt).  The LGPL is also
# available at http://www.gnu.org/licenses/lgpl.html.  If the version
# of the LGPL at http://www.gnu.org is different to the version of
# the LGPL accompanying this distribution and there is any conflict
# between the two license versions, the terms of the LGPL accompanying
# this distribution shall govern.
#
# Makefile for creating SWT libraries on Linux

# User configuration

# Define the installation directories for various products.
# Your system may have these in a different place.

# JAVA_JNI - Depending on your version of JDK, and where
# it is installed, your jni.h may be located differently.
JAVA_JNI = /bluebird/teamswt/swt-builddir/ive/bin/include
#IVE_HOME   = /opt/IBMvame1.4/ive/bin/include

# Whether we want GTK over X or FB
GTKTARGET = gtk+-2.0
#GTKTARGET = gtk+-linux-fb-2.0

CC = gcc

include make_common.mak

SWT_VERSION=$(maj_ver)$(min_ver)

# Define the various DLL (shared) libraries to be made.

SWT_PREFIX   = swt
OS_PREFIX    = linux
SWT_DLL      = lib$(SWT_PREFIX)-$(OS_PREFIX)-$(SWT_VERSION).so
SWTPI_DLL      = lib$(SWT_PREFIX)-pi-$(OS_PREFIX)-$(SWT_VERSION).so

#GNOME_PREFIX = swt-gnome
#GNOME_DLL    = lib$(GNOME_PREFIX)-$(OS_PREFIX)-$(SWT_VERSION).so
#GNOME_LIB    = -x -shared \
#    -L/usr/lib \
#    -lgnome -lglib \
#    -lm -ldl


# Compile and link options from pkg-config
GTKCFLAGS = `pkg-config --cflags $(GTKTARGET)`
GTKLIBS = `pkg-config --libs $(GTKTARGET)`


#
#  Target Rules
#

all: make_swt  # make_gnome

make_swt: $(SWT_DLL) $(SWTPI_DLL)

#make_gnome: $(GNOME_DLL)


# All about Linking

$(SWT_DLL): callback.o
	ld -x -shared \
	    -o $(SWT_DLL) callback.o
	    
$(SWTPI_DLL): swt.o structs.o
	ld -x -shared \
	    $(GTKLIBS) \
	    -o $(SWTPI_DLL) swt.o structs.o

#$(GNOME_DLL): gnome.o
#	ld -o $@ gnome.o $(GNOME_LIB)


# All about Compiling

SWT_C_FLAGS = -c -O -s \
	    -DSWT_VERSION=$(SWT_VERSION) \
	    -DLINUX -DGTK \
	    -fpic \
	    -I$(JAVA_JNI) \
	    $(GTKCFLAGS)

#SWT_GNOME_FLAGS = -c -O -s \
#	    -DSWT_VERSION=$(SWT_VERSION) \
#	    -DLINUX -DGTK \
#	    -fpic \
#	    -I$(JAVA_JNI) \
#	    `gnome-config --cflags gnome`

swt.o: swt.c swt.h
	gcc $(SWT_C_FLAGS) swt.c

swt-gtkwidget.o: swt-gtkwidget.c swt.h
	gcc $(SWT_C_FLAGS) swt-gtkwidget.c

swt-gtkwindow.o: swt-gtkwindow.c swt.h
	gcc $(SWT_C_FLAGS) swt-gtkwindow.c

structs.o: structs.c
	gcc $(SWT_C_FLAGS) structs.c

callback.o: callback.c
	gcc $(SWT_C_FLAGS) callback.c

globals.o: globals.c
	gcc $(SWT_C_FLAGS) globals.c

library.o: library.c
	gcc $(SWT_C_FLAGS) library.c

#gnome.o: gnome.c
#	gcc $(SWT_GNOME_FLAGS) gnome.c

clean:
	rm -f *.o *.so
