#!/bin/bash

if [[ ! -d bootable ]]; then
	if [[ -d ../bootable ]]; then
		cd ..
	else
		echo "run this in the correct directory..........."
		exit 1
	fi
fi

./build/deps.sh

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
    vic-yocto-builder-2 bash -c "cd $(pwd)/poky && source build/conf/set_bb_env.sh && MACHINE=apq8009-robot VARIANT=debug $@"

#cd ota
#rm -rf ../_build/*.img ../_build/*.stats ../_build/*.ini ../_build/*.env
#${MAKECOMMAND}
#make
