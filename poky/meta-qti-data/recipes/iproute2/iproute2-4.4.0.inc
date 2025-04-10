SUMMARY = "TCP / IP networking and traffic control utilities"
DESCRIPTION = "Iproute2 is a collection of utilities for controlling \
TCP / IP networking and traffic control in Linux.  Of the utilities ip \
and tc are the most important.  ip controls IPv4 and IPv6 \
configuration and tc stands for traffic control."
HOMEPAGE = "http://www.linuxfoundation.org/collaborate/workgroups/networking/iproute2"
SECTION = "base"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a \
                    file://ip/ip.c;beginline=3;endline=8;md5=689d691d0410a4b64d3899f8d6e31817"

DEPENDS = "flex-native bison-native iptables elfutils libselinux"

inherit update-alternatives

EXTRA_OEMAKE = "CC='${CC}' KERNEL_INCLUDE=${STAGING_INCDIR} DOCDIR=${docdir}/iproute2 SUBDIRS='lib tc ip bridge misc genl' SBINDIR='${base_sbindir}' LIBDIR='${libdir}'"

do_configure:append () {
    sh configure ${STAGING_INCDIR}
    # Explicitly disable ATM support
    sed -i -e '/TC_CONFIG_ATM/d' Config
}

do_install () {
    oe_runmake DESTDIR=${D} install
    mv ${D}${base_sbindir}/ip ${D}${base_sbindir}/ip.iproute2
    install -d ${D}${datadir}
    mv ${D}/share/* ${D}${datadir}/ || true
    rm ${D}/share -rf || true
}

# The .so files in iproute2-tc are modules, not traditional libraries
INSANE_SKIP:${PN}-tc = "dev-so"

PACKAGES =+ "${PN}-tc ${PN}-tc-dbg ${PN}-lnstat ${PN}-ifstat ${PN}-genl ${PN}-rtacct ${PN}-nstat ${PN}-ss"
FILES:${PN}-tc = "${base_sbindir}/tc* \
                  ${libdir}/tc/*.so"
FILES:${PN}-tc-dbg += "${libdir}/tc/.debug"
FILES:${PN}-lnstat = "${base_sbindir}/lnstat ${base_sbindir}/ctstat ${base_sbindir}/rtstat"
FILES:${PN}-ifstat = "${base_sbindir}/ifstat"
FILES:${PN}-genl = "${base_sbindir}/genl"
FILES:${PN}-rtacct = "${base_sbindir}/rtacct"
FILES:${PN}-nstat = "${base_sbindir}/nstat"
FILES:${PN}-ss = "${base_sbindir}/ss"
FILES:${PN} += "${userfsdatadir}/*"

ALTERNATIVE_${PN} = "ip"
ALTERNATIVE_TARGET[ip] = "${base_sbindir}/ip.${BPN}"
ALTERNATIVE_LINK_NAME[ip] = "${base_sbindir}/ip"
ALTERNATIVE_PRIORITY = "100"
