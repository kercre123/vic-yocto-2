inherit autotools pkgconfig

DESCRIPTION = "Recipe to provide Camera Metadata library"
HOMEPAGE = "http://developer.android.com/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

FILESPATH =+ "${WORKSPACE}/frameworks/:"
SRC_URI =+ "file://camera_metadata"

S = "${WORKDIR}/camera_metadata"

DEPENDS += "libcutils"

FILES:${PN}-dbg    = "${libdir}/.debug/lib*.*"
FILES:${PN}        = "${libdir}/lib*.so.* ${libdir}/pkgconfig/*"
FILES:${PN}-dev    = "${libdir}/lib*.so ${libdir}/lib*.la ${includedir}"

