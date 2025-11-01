SUMMARY = "Char device driver. Uses in-memory circular buffer to save random text"
DESCRIPTION = "${SUMMARY}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit module

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-hatemalamir.git;protocol=ssh;branch=main"
SRCREV = "940d6517bbb54e96b57dfa6144d15988db553242"

PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git/aesd-char-driver"
# UNPACKDIR = "${S}"
RPROVIDES:${PN} += "kernel-module-aesdchar"

FILES:${PN} += "${sysconfdir}/modules-load.d/aesdchar.conf"
do_install:append() {
	install -d ${D}${sysconfdir}/modules-load.d
	install -m 0644 ${S}/aesdchar.conf ${D}${sysconfdir}/modules-load.d/aesdchar.conf
}
