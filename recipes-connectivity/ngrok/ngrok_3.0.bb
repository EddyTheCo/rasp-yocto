SUMMARY = "Ngrok - Secure introspectable tunnels to localhost"
DESCRIPTION = "Ngrok exposes local servers to the public internet securely."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
COMPATIBLE_MACHINE = "^rpi$"
SRC_URI:arm = "https://bin.equinox.io/c/bNyj1mQVY4c/ngrok-v3-stable-linux-arm.tgz;name=arm"
SRC_URI:aarch64 = "https://bin.equinox.io/c/bNyj1mQVY4c/ngrok-v3-stable-linux-arm64.tgz;name=aarch64"
SRC_URI[aarch64.sha256sum] = "829e979f8f516b6fd4eabef552e730b2f618c2024b355b0b2aec5aee12d4e7f4"
SRC_URI[arm.sha256sum] = "d8c5f0491bbeceabe8122307989025bdc4778219c49e58b7415ee44144e91f22"
SRC_URI:append = " file://ngrok.yml"

# Specify where the sources will be extracted
S = "${WORKDIR}"

# Install ngrok binary and configuration file
do_install() {
    install -d ${D}${bindir}
    install -m 0755 ngrok ${D}${bindir}/ngrok

    # Copy the provided configuration file
    install -d ${D}/etc/ngrok
    install -m 0644 ${WORKDIR}/ngrok.yml ${D}/etc/ngrok/ngrok.yml
}
INSANE_SKIP:${PN} += "already-stripped"
# Specify paths where files will be installed
FILES:${PN} += "${bindir}/ngrok /etc/ngrok/ngrok.yml"

inherit systemd

SRC_URI:append = " file://ngrok.service "
FILES:${PN} += "${systemd_system_unitdir}/ngrok.service"

do_install:append() {
  install -d ${D}${systemd_system_unitdir}
  install -m 0644 ${WORKDIR}/ngrok.service ${D}${systemd_system_unitdir}/ngrok.service
}
SYSTEMD_SERVICE:${PN} = "ngrok.service"
SYSTEMD_AUTO_ENABLE = "enable"
