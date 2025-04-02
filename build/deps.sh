#!/bin/bash

if [[ ! -d poky ]]; then
	if [[ -d ../poky ]]; then
		cd ..
	else
		echo "run this in the correct directory"
	fi
fi

if [[ ! -d old-toolchain/arm ]]; then
	mkdir -p old-toolchain
	cd old-toolchain
	wget https://github.com/kercre123/vic-yocto-2/releases/download/0.0.1/arm-4.9.3.tar.gz
	tar -zxf arm-4.9.3.tar.gz
	rm arm-4.9.3.tar.gz
fi
