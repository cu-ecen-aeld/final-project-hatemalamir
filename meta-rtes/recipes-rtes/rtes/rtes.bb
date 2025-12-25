LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/hatemalamir/rtes-assignments.git;protocol=ssh;branch=main"
SRCREV = "c6691a5722b719400434d984701733abb3bbfa5d"
S = "${WORKDIR}/git"

inherit cmake

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE += "-DENABLE_INSTALL=ON"
