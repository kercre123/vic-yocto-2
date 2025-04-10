require iproute2.inc

SRC_URI = "${KERNELORG_MIRROR}/linux/utils/net/${BPN}/${BPN}-${PV}.tar.xz \
           file://0001-iproute2-de-bash-scripts.patch \
           file://configure-cross.patch \
           file://iproute2_config_to_data.patch \
           file://include-stdint.h-for-UINT16_MAX.patch \
          "

SRC_URI[md5sum] = "6c823b40fdcfa7b8120743349a52ac18"
SRC_URI[sha256sum] = "1f0a8a6c0e872166f75433f5cbf9766f3002b5c2f13501b3bb8c51846a127b79"

# CFLAGS are computed in Makefile and reference CCOPTS
#
EXTRA_OEMAKE:append = " CCOPTS='${CFLAGS}'"
