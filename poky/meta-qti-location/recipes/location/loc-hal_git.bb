inherit autotools-brokensep pkgconfig

DESCRIPTION = "GPS Loc HAL"
PR = "r5"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=3775480a712fc46a69647678acb234cb"

FILESPATH =+ "${WORKSPACE}:"
SRC_URI = "file://hardware/qcom/gps/"
S = "${WORKDIR}/hardware/qcom/gps"
DEPENDS = "glib-2.0 gps-utils qmi qmi-framework data loc-pla loc-flp-hdr libcutils"
EXTRA_OECONF = "--with-core-includes=${WORKSPACE}/system/core/include \
                --with-glib"


CPPFLAGS += "-I${WORKSPACE}/base/include"
CFLAGS += "-I${STAGING_INCDIR}/cutils"

PACKAGES = "${PN}"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
FILES:${PN} = "${libdir}/* ${sysconfdir}"
FILES:${PN} += "/usr/include/*"
FILES:${PN} += "/usr/include/loc-hal/*"
# The loc-hal package contains symlinks that trip up insane
INSANE_SKIP:${PN} = "dev-so"

do_install:append() {
   install -m 0644 -D ${S}/etc/gps.conf ${D}${sysconfdir}/gps.conf
}
