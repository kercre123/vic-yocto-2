inherit autotools qcommon

DESCRIPTION = "display Library"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=3775480a712fc46a69647678acb234cb"

PR = "r8"

PACKAGES = "${PN}"

SRC_DIR = "${WORKSPACE}/display/display-hal/"
S = "${WORKDIR}/display/display-hal/"

DEPENDS += "system-core"
DEPENDS += "libhardware"
DEPENDS += "native-frameworks"
DEPENDS += "drm"
DEPENDS += "libdrm"
DEPENDS:apq8098 += "native-frameworks"
DEPENDS:apq8098 += "adreno"

EXTRA_OECONF = " --with-core-includes=${WORKSPACE}/system/core/include"
EXTRA_OECONF += " --with-sanitized-headers=${STAGING_KERNEL_BUILDDIR}/usr/include"

EXTRA_OECONF:append_apq8098 = " --enable-sdmhaldrm"

LDFLAGS += "-llog -lhardware -lutils -lcutils"

CPPFLAGS:append_apq8098 = "-DCOMPILE_DRM"
CPPFLAGS += "-DTARGET_HEADLESS"
CPPFLAGS += "-DVENUS_COLOR_FORMAT"
CPPFLAGS += "-DPAGE_SIZE=4096"
CPPFLAGS:append_apq8098 = "-I${WORKSPACE}/display/display-hal/libdrmutils"
CPPFLAGS += "-I${WORKSPACE}/display/display-hal/libqdutils"
CPPFLAGS += "-I${WORKSPACE}/display/display-hal/libqservice"
CPPFLAGS += "-I${WORKSPACE}/display/display-hal/sdm/include"
CPPFLAGS += "-I${WORKSPACE}/display/display-hal/include"
CPPFLAGS += "-I${WORKSPACE}/display/display-hal/libgralloc"
CPPFLAGS += "-I${WORKSPACE}/system/core/include"
CPPFLAGS:append_apq8098 = "-I${STAGING_INCDIR}/libdrm"

do_install:append () {
    # libhardware expects to find /usr/lib/hw/gralloc.*.so
    install -d ${D}${libdir}/hw
    ln -s ${libdir}/libgralloc.so ${D}${libdir}/hw/gralloc.default.so
    cp -fR ${WORKSPACE}/display/display-hal/include/* ${STAGING_INCDIR}/
}

FILES:${PN} = "${libdir}/*.so"
FILES:${PN} += "${libdir}/hw/gralloc.default.so"
INSANE_SKIP:${PN} = "dev-so"
