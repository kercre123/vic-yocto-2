FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${PV}:"
DEPENDS = "base-passwd"

SRC_URI:append = "file://fstab"

dirs755:append = " /media/cf /media/net /media/ram \
            /media/union /media/realroot /media/hdd /media/mmc1"

# userdata mount point is present by default in all machines.
# TODO: Add this path to MACHINE_MNT_POINTS in machine conf.
dirs755:append = " ${userfsdatadir}"

dirs755:append = " ${MACHINE_MNT_POINTS}"

# /systemrw partition is needed only when system is RO.
# Otherwise files can be directly written to / itself.
dirs755:append = " ${@bb.utils.contains('DISTRO_FEATURES','ro-rootfs','/systemrw','',d)}"
dirs755:append_apq8009 = "/firmware /persist /factory"

# Explicitly remove sepolicy entries from fstab when selinux is not present.
fix_sepolicies () {
    #For /run
    sed -i "s#,rootcontext=system_u:object_r:var_run_t:s0##g" ${WORKDIR}/fstab
    # For /var/volatile
    sed -i "s#,rootcontext=system_u:object_r:var_t:s0##g" ${WORKDIR}/fstab
}
do_install[prefuncs] += " ${@bb.utils.contains('DISTRO_FEATURES', 'selinux', '', 'fix_sepolicies', d)}"

do_install:append(){
    #if [ ${BASEMACHINE} == "mdm9650" ]; then
    #  ln -s /etc/resolvconf/run/resolv.conf ${D}/etc/resolv.conf
    #else
    #  ln -s /var/run/resolv.conf ${D}/etc/resolv.conf
    #fi

}

# Don't install fstab for systemd targets
do_install:append() {
#    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
#        rm ${D}${sysconfdir}/fstab
#    else
        install -m 0644 ${WORKDIR}/fstab ${D}${sysconfdir}/fstab
#    fi
# kercre123 - install custom profile
     install -m 0755 ${WORKDIR}/profile ${D}${sysconfdir}/profile
}

do_install:append_sdm845 () {
    install -m 755 -o diag -g diag -d ${D}/mnt/usbstorage0
    install -m 755 -o diag -g diag -d ${D}/mnt/usbstorage1
    install -m 755 -o diag -g diag -d ${D}/mnt/usbstorage2
}
