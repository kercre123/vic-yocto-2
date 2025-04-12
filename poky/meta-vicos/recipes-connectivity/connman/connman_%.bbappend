LICENSE = "MIT"

EXTRA_OECONF += " --localstatedir=/data"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://connman.conf"

do_install:append() {
	install -d ${D}/etc/dbus-1/system.d
	install -m 0644 ${WORKDIR}/connman.conf ${D}/etc/dbus-1/system.d/connman.conf
}

FILES:${PN} += "/etc/dbus-1/system.d"