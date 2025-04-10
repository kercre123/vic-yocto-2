DESCRIPTION = "Anki OTA Engine"
LICENSE = "Anki-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-qti-bsp/files/anki-licenses/\
Anki-Inc.-Proprietary;md5=4b03b8ffef1b70b13d869dbce43e8f09"

DEPENDS = "libcutils zlib libbsd"
RDEPENDS:${PN} = "zlib liblog libbsd"

SRC_URI = " \
      file://bootctl.cpp \
      file://bootctl.h \
      file://gpt-utils.cpp \
      file://gpt-utils.h \
      file://main.cpp \
      file://boot-successful.service \
      file://boot-successful.sh \
      "

#do_compile () {
#  ${CC} ${WORKDIR}/gpt-utils.cpp ${WORKDIR}/bootctl.cpp ${WORKDIR}/main.cpp -lstdc++ -lz -llog -lbsd -o ${WORKDIR}/bootctl
#}

do_compile () {
  ${CXX} ${WORKDIR}/gpt-utils.cpp ${WORKDIR}/bootctl.cpp ${WORKDIR}/main.cpp \
    ${CXXFLAGS} ${LDFLAGS} \
    -I${STAGING_INCDIR} \
    -L${STAGING_LIBDIR} -lstdc++ -lz -llog -lbsd -o ${WORKDIR}/bootctl
}


do_install() {
  install -d ${D}/usr/bin
  install -m 0700 ${WORKDIR}/bootctl ${D}/usr/bin/bootctl-anki

  install -d ${D}${sysconfdir}/initscripts
  install -m 0755 ${WORKDIR}/boot-successful.sh ${D}${sysconfdir}/initscripts/boot-successful
  install -d ${D}${sysconfdir}/systemd/system/
  install -m 0644 ${WORKDIR}/boot-successful.service \
    -D ${D}${sysconfdir}/systemd/system/boot-successful.service
  install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
  ln -sf /etc/systemd/system/boot-successful.service \
    ${D}${sysconfdir}/systemd/system/multi-user.target.wants/boot-successful.service
}

FILES:${PN} += "/usr/bin"
FILES:${PN} += "/etc/systemd/system"
FILES:${PN} += "/etc/initscripts"
