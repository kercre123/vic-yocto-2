# vic-yocto

Vector's original OS is built with an old version of Yocto/OpenEmbedded. This project aims to upgrade that.

## Vector's Original OS Info

-	Kernel: 3.18.66
-	glibc: 2.22
-	arch: armel
-	yocto: jethro (2.0.3)

## Status

-	Kernel: 3.18.66
-	glibc: 2.35
-	arch: armhf
-	yocto: kirkstone (4.0)

## Notes

-	All of the meta-qti-* stuff is open-source, but there is no publicly available documentation for which branches go with which yocto version.
	-	The branches I have chosen are in ./BRANCHES.txt
-	WLAN is implemented and will connect on bootup. /data/misc/wifi/wpa_supplicant.conf must be modified to include your WiFi credentials (install.sh does automatically).
-	A test program will launch at startup. Use one of the wheels and the button to navigate it.

## Build

Make sure you have Docker installed, and configured so a regular user can use it.

```
git clone https://github.com/kercre123/vic-yocto-2
cd vic-yocto-2
./build/build.sh
```

Result will be in poky/build/tmp-glibc/deploy/images/

## Install

An install script is included. This patches the image to include the kernel modules corresponding to the boot partition of the current slot in your bot, and adds WiFi credentials for automatic connection.

It is recommended to have WireOS installed.

```
# should not be run in a docker container
cd ../install-image
# replace vectorip with vector's ip address, /path/to/sshkey with the path to his ssh key, ssid with your network name, password with your network password
sudo ./install.sh vectorip /path/to/sshkey "ssid" "password"
```

He should eventually boot up to a screen showing "booted!" and SSH should be available (with [this key](http://wire.my.to:81/ssh_root_key)).

## Resources

-	Qualcomm LE (linux embedded) repo: https://git.codelinaro.org/clo/le
-	Migration guide for thud: https://docs.yoctoproject.org/migration-guides/migration-2.6.html
-	Migration guide for sumo (includes new function definitions): https://docs.yoctoproject.org/migration-guides/migration-2.5.html
-	Original Vector tarball: https://web.archive.org/web/20221102004123if_/https://anki-vic-pubfiles.anki.com/license/prod/1.0.0/licences/OStarball.v160.tgz
-	Original Vector tarball (fixed so it builds): https://keriganc.com/opensource.tar.gz
