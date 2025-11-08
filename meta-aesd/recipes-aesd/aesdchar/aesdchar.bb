SUMMARY = "Char device driver. Uses in-memory circular buffer to save random text"
DESCRIPTION = "${SUMMARY}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit module
inherit update-rc.d

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-hatemalamir.git;protocol=ssh;branch=main"
SRCREV = "8cd67ba68aebf9985024e7e276b803c5d2816d51"

PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git/aesd-char-driver"
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "S97aesdchar"

RPROVIDES:${PN} += "kernel-module-aesdchar"

do_install:append() {
	install -d ${D}${sysconfdir}/modules-load.d
	install -m 0644 ${S}/aesdchar.conf ${D}${sysconfdir}/modules-load.d/aesdchar.conf

	install -d ${D}${sbindir}
	install -m 0755 ${S}/aesdchar_load ${D}${sbindir}/
	install -m 0755 ${S}/aesdchar_unload ${D}${sbindir}/

	install -d ${D}${sysconfdir}/init.d
	install -m 0644 ${S}/S97aesdchar ${D}${sysconfdir}/init.d/
}
FILES:${PN} += "${sysconfdir}/modules-load.d/aesdchar.conf"
FILES:${PN} += "${sbindir}/aesdchar_load"
FILES:${PN} += "${sbindir}/aesdchar_unload"
FILES:${PN} += "${sysconfdir}/init.d/S97aesdchar"
