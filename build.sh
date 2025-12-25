#!/bin/bash
# Script to build image for qemu.
# Author: Siddhant Jajoo.
# Edited: Hatem Alamir

git submodule init
git submodule sync
git submodule update

# local.conf won't exist until this step on first execution
source poky/oe-init-build-env

function add_configs {
	local configs=("$@")
	for confline in "${configs[@]}"; do
		cat conf/local.conf | grep "${confline}" > /dev/null
		local_conf_info=$?
		if [ $local_conf_info -ne 0 ];then
			echo "Append ${confline} in the local.conf file"
			echo "${confline}" >> conf/local.conf
		else
			echo "${confline} already exists in the local.conf file"
		fi
	done
}

# CONFLINE="MACHINE = \"qemuarm64\""
configs=(\
	"MACHINE ?= \"raspberrypi4\""\  # Target hardware
	"IMAGE_FSTYPES = \"wic.bz2\""\  # Image type rpi-sdimg
	"GPU_MEM = \"16\""\  # Minimum GPU memory
	"DISTRO_FEATURES:append = \"wifi\""\  # WiFi support
	"IMAGE_INSTALL:append = \"v4l-utils python3 ntp wpa-supplicant\""\
	"LICENSE_FLAGS_ACCEPTED = \"commercial synaptics-killswitch\""\  # License
	"IMAGE_FEATURES += \"ssh-server-openssh\""\  # SSH
	"MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += \"kernel-modules\""\  # kernel modules
	"MACHINE_EXTRA_RDEPENDS += \"kernel-module-aesdchar\""\  # Our kernel module
)
add_configs "${configs[@]}"

function add_layers {
	local base=$1
	shift 1
	local layers=("$@")
	for layer in "${layers[@]}"; do
		bitbake-layers show-layers | grep "${layer}" > /dev/null
		layer_info=$?
		if [ $layer_info -ne 0 ];then
			echo "Adding ${layer} layer"
			bitbake-layers add-layer "${base}/${layer}"
		else
			echo "${layer} layer already exists"
		fi
	done
}

layers=("meta-oe" "meta-python" "meta-networking")
add_layers "../meta-openembedded" "${layers[@]}"

layers=("meta-raspberrypi" "meta-aesd" "meta-rtes")
add_layers ".." "${layers[@]}"

set -e
bitbake core-image-aesd
