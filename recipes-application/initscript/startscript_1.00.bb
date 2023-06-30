LICENSE = "CLOSED"
inherit systemd


SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "qtapp.service"

SRC_URI  += " file://qtapp.service "
FILES:${PN} += "${systemd_unitdir}/system/qtapp.service"

do_install:append() {
  install -d ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/qtapp.service ${D}/${systemd_unitdir}/system
}
