#!/bin/bash

set -e

if [[ ! -d bootable ]]; then
	if [[ -d ../bootable ]]; then
		cd ..
	else
		echo "run this in the correct directory..........."
		exit 1
	fi
fi

mkdir -p build/cache
./build/deps.sh

rm -rf poky/build/tmp-glibc/deploy/images/apq8009-robot/apq8009-robot-sysfs.ext4

DIRPATH="$(pwd)"

if [[ $1 == "oskr" ]]; then
	echo "building OSKRKR!!!!!"
	OSKRQ="oskr-"
	MAKECOMMAND="export BOOT_IMAGE_SIGNING_PASSWORD=\"annul-burl-zq-flew-hack-owe-phil-triton-pk\" && make oskrsign"
else
	MAKECOMMAND="make devsign"
	echo "building DEV!!!!!"
fi

docker build --build-arg DIR_PATH="${DIRPATH}" --build-arg USER_NAME=$(whoami) --build-arg UID=$(id -u $USER) --build-arg GID=$(id -g $USER) -t vic-yocto-builder-2 build/

docker run -it \
    -v $(pwd)/anki-deps:${HOME}/.anki \
    -v $(pwd):$(pwd) \
    -v $(pwd)/build/cache:${HOME}/.ccache \
    vic-yocto-builder-2 bash -c "cd $(pwd)/poky && source build/conf/set_bb_env.sh && MACHINE=apq8009-robot VARIANT=debug bitbake -c cleanall anki-version victor machine-robot-image && ANKI_BUILD_VERSION=1 BB_ENV_PASSTHROUGH_ADDITIONS=\"${BB_ENV_PASSTHROUGH_ADDITIONS} ANKI_BUILD_VERSION\" build-victor-robot-${OSKRQ}image"

cd ota
rm -rf ../_build/*.img ../_build/*.stats ../_build/*.ini ../_build/*.env
${MAKECOMMAND}
make
