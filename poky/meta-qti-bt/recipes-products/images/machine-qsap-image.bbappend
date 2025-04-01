# BT OPEN SOURCE PACKAGES
include ${BASEMACHINE}/${BASEMACHINE}-bt-image.inc

IMAGE_INSTALL:append_apq8017 = " lib32-gst-bt-app"
