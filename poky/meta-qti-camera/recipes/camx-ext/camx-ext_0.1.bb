inherit cmake pkgconfig

DESCRIPTION = "Recipe to provide Camera external library"
HOMEPAGE = "http://developer.android.com/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/:"
SRC_URI =+ "file://camx-ext"

include ../env.inc

S = "${WORKDIR}/camx-ext"

FILES:${PN}        += "${libdir}/*.so"
FILES:${PN}-dbg    += "${libdir}/.debug/"
FILES:SOLIBSDEV     = ""
INSANE_SKIP:${PN}   = "dev-so"