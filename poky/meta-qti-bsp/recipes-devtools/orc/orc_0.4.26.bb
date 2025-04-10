SUMMARY = "Optimised Inner Loop Runtime Compiler"
HOMEPAGE = "http://code.entropywave.com/projects/orc/"
LICENSE = "BSD-2-Clause & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=1400bd9d09e8af56b9ec982b3d85797e"

SRC_URI = "http://gstreamer.freedesktop.org/src/orc/orc-${PV}.tar.xz;name=orc"
SRC_URI[orc.md5sum] = "8e9bef677bae289d3324d81c337a4507"
SRC_URI[orc.sha256sum] = "7d52fa80ef84988359c3434e1eea302d077a08987abdde6905678ebcad4fa649"

inherit autotools pkgconfig

BBCLASSEXTEND = "native nativesdk"

PACKAGES =+ "orc-examples"
FILES:orc-examples = "${libdir}/orc/*"
FILES:${PN} = "${bindir}/*"

python populate_packages:prepend () {
    libdir = d.expand('${libdir}')
    do_split_packages(d, libdir, '^lib(.*)\.so\.*', 'lib%s', 'ORC %s library', extra_depends='', allow_links=True)
}

do_compile:prepend_class-native () {
    sed -i -e 's#/tmp#.#g' ${S}/orc/orccodemem.c
}
