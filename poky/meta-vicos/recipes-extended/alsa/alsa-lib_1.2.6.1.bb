SUMMARY = "ALSA sound library"
DESCRIPTION = "(Occasionally a.k.a. libasound) is a userspace library that \
provides a level of abstraction over the /dev interfaces provided by the kernel modules."
HOMEPAGE = "http://www.alsa-project.org"
BUGTRACKER = "http://alsa-project.org/main/index.php/Bug_Tracking"
SECTION = "libs/multimedia"
LICENSE = "LGPL-2.1-only & GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=a916467b91076e631dd8edb7424769c7 \
                    file://src/socket.c;md5=285675b45e83f571c6a957fe4ab79c93;beginline=9;endline=24 \
                    "

SRC_URI = "https://www.alsa-project.org/files/pub/lib/${BP}.tar.bz2"
SRC_URI[sha256sum] = "ad582993d52cdb5fb159a0beab60a6ac57eab0cc1bdf85dc4db6d6197f02333f"

inherit autotools pkgconfig

# @kercre123
# the kernel and ALSA have to compiled with the same compiler
# in Kirkstone (GCC 10) it worked without issue, but some change was made in GCC 13
# (what Scarthgap uses) which makes it not work with our GCC-4.9-compiled kernel

CC = "${WORKSPACE}/old-toolchain/arm/bin/arm-linux-gnueabi-gcc"
CXX = "${WORKSPACE}/old-toolchain/arm/bin/arm-linux-gnueabi-g++"
LD = "${WORKSPACE}/old-toolchain/arm/bin/arm-linux-gnueabi-ld"

do_configure:prepend () {
    export LDFLAGS="$(echo $LDFLAGS | sed 's/-fcanon-prefix-map=[^ ]*//g' | sed 's/-fcanon-prefix-map//g' | sed 's/-fmacro-prefix-map=[^ ]*//g' | sed 's/-fdebug-prefix-map=[^ ]*//g')"
}

EXTRA_OECONF += " \
    --with-softfloat \
    --disable-python \
"

PACKAGES =+ "alsa-server alsa-conf libatopology"

FILES:alsa-server = "${bindir}/*"
FILES:alsa-conf = "${datadir}/alsa/"
FILES:libatopology = "${libdir}/libatopology.so.*"

RDEPENDS:${PN}:class-target = "alsa-conf alsa-ucm-conf"
RDEPENDS:libatopology:class-target = "alsa-topology-conf"

# upgrade path
RPROVIDES:${PN} = "libasound"
RREPLACES:${PN} = "libasound"
RCONFLICTS:${PN} = "libasound"

RPROVIDES:${PN}-dev = "alsa-dev"
RREPLACES:${PN}-dev = "alsa-dev"
RCONFLICTS:${PN}-dev = "alsa-dev"

RPROVIDES:alsa-conf = "alsa-conf-base"
RREPLACES:alsa-conf = "alsa-conf-base"
RCONFLICTS:alsa-conf = "alsa-conf-base"

BBCLASSEXTEND = "native nativesdk"
