SUMMARY = "Anki Vector test program"
DESCRIPTION = "Ensures functionality of robot"
SECTION = "examples"
PR = "r1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://vic-test \
	   file://vic-test.service \
	   file://libvector-gobot.so \
	   file://testSound.wav"

S = "${WORKDIR}"
#S = "${WORKDIR}/sources"
#UNPACKDIR = "${S}"


inherit systemd

do_install () {
		#install -m 0644 ${WORKDIR}/wcnss_wlan.service -D ${D}/etc/systemd/system/wcnss_wlan.service
	        #install -d ${D}/etc/systemd/system/multi-user.target.wants/
		#ln -sf /etc/systemd/system/wcnss_wlan.service \
		#${D}/etc/systemd/system/multi-user.target.wants/wcnss_wlan.service
        install -d ${D}/etc/systemd/system/multi-user.target.wants/
	install -d ${D}/bin/
	install -d ${D}/lib/
	install -p -m 755 testSound.wav ${D}/etc/testSound.wav
	install -p -m 755 vic-test ${D}/bin/vic-test
	install -p -m 755 libvector-gobot.so ${D}/lib/libvector-gobot.so
	install -m 644 vic-test.service -D ${D}/etc/systemd/system/vic-test.service
	#install -p -m 755 rampost ${D}/bin/
        ln -sf /etc/systemd/system/vic-test.service ${D}/etc/systemd/system/multi-user.target.wants/vic-test.service
	#some things need this
	#ln -sf /lib/ld-2.35.so ${D}/lib/ld-linux.so.3
}

FILES:${PN} = "/bin/vic-test \
		/bin \
		/lib \
		/lib/vector-gobot.so \
		/etc/testSound.wav \
		/bin/bootctl \
		/etc/systemd/system \
		/etc/systemd/system/vic-test.service \
		/etc/systemd/system/multi-user.target.wants \
		/etc/systemd/system/multi-user.target.wants/vic-test.service"

# yocto doesn't really allow precompiled binaries
INSANE_SKIP:${PN} = "file-rdeps"
INSANE_SKIP:${PN} += " dev-elf"
INSANE_SKIP:${PN} += " ldflags"
INSANE_SKIP:${PN} += " installed-vs-shipped"

INSANE_SKIP:${PN}-dev = "ldflags installed-vs-shipped dev-elf"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
SOLIBS = ".so"
FILES:SOLIBSDEV = ""
FILES:${PN}-dev = ""

# Prevents do_package failures with:
# debugsources.list: No such file or directory:
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

