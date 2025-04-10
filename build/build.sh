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


docker build --build-arg UID=$(id -u $USER) --build-arg GID=$(id -g $USER) -t vic-yocto-builder build/

docker run -it \
    -v "$(pwd):/home/build/vic-yocto-2" \
    -v "$(pwd)/anki-deps:/home/build/.anki" \
    vic-yocto-builder bash -c "cd ~/vic-yocto-2/poky && source build/conf/set_bb_env.sh && build-victor-robot-image"
