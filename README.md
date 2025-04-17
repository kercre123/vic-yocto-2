# vic-yocto-2

Vector's original OS is built with an old version of Yocto/OpenEmbedded. I upgraded it to `walnascar`, which is the latest release of Yocto as of 2025.

It may or may not build on your machine. Yocto is weird. Who knows.

## Vector's Original OS Info

-	Kernel: 3.18.66
-	glibc: 2.22
-	arch: armel
-	yocto: jethro (2.0.3)

## Status

-	Kernel: 3.18.68-scarthgap
-	glibc: 2.41
-	arch: armel
-	yocto: walnascar (5.2)

## Build

### no signing

Make sure you have Docker installed, and configured so a regular user can use it.

```
git clone https://github.com/kercre123/vic-yocto-2 --recurse-submodules --shallow-submodules --depth=1
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
