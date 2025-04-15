# vic-yocto-2

Vector's original OS is built with an old version of Yocto/OpenEmbedded. This project aims to upgrade that.

## Vector's Original OS Info

-	Kernel: 3.18.66
-	glibc: 2.22
-	arch: armel
-	yocto: jethro (2.0.3)

## Status

-	Kernel: 3.18.68-scarthgap
	-	Tiny little modifications required for new sysfs
-	glibc: 2.39
-	arch: armel
-	yocto: scarthgap (5.0)

## Build

### no signing

Make sure you have Docker installed, and configured so a regular user can use it.

```
git clone https://github.com/kercre123/vic-yocto-2
cd vic-yocto-2
./build/build.sh -bt <dev/oskr> -bp <boot-passwd> -v <build-increment>
```

### with signing

If you are privileged enough to have the passwords:

```
./build/build.sh -bt <dev/oskr> -s -op <OTA-pw> -bp <boot-passwd> -v <build-increment>
```

### where is my OTA?

`./_build/vicos-3.0.0.#oskr/d.ota`

## What is working

- almost everything!
- we have victor building, all hardware components working, userdata mounting, clear user data working, connman working, avahi-daemon working

## What isn't working

- delta updates