#!/bin/sh

cd `dirname $0`


if [ -z "$JAVA_HOME" ]; then
	export JAVA_HOME="`java-config -H`"
fi

if [ -z "$AWT_LIB_PATH" ]; then
	export AWT_LIB_PATH="$JAVA_HOME/jre/lib/i386"
fi


# Announce our target
echo "Building SWT/Qt for $SWT_OS $SWT_ARCH"
if [ "x${OUTPUT_DIR}" = "x" ]; then
	OUTPUT_DIR=../../../org.eclipse.swt.qt.${SWT_OS}.${SWT_ARCH}
	export OUTPUT_DIR
fi


if [ "x${1}" = "xclean" ]; then
	make clean
else
	make all "$@"
fi
