# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Main code
SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-hatemalamir.git;protocol=ssh;branch=main"
SRCREV = "8cd67ba68aebf9985024e7e276b803c5d2816d51"
# Wifi configs
SRC_URI += " file://wpa_supplicant_template.conf"

PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git/server"
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "aesdsocket-start-stop"
TARGET_LDFLAGS += "-pthread -lrt"

inherit update-rc.d

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ${S}/aesdsocket ${D}${bindir}/
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d/
	install -m 0600 ${WORKDIR}/sources-unpack/wpa_supplicant_template.conf ${D}${sysconfdir}/wpa_supplicant_template.conf
}
FILES:${PN} += "${bindir}/aesdsocket"
FILES:${PN} += "${sysconfdir}/wpa_supplicant_template.conf"
