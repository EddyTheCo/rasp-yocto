FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://provider \
	    file://ppp@provider.service \	 
	   "

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN}:append = " ppp@provider.service "

do_install:append () {
	install -m 0644 ${WORKDIR}/ppp@provider.service ${D}${systemd_system_unitdir}
	sed -i -e 's,@SBINDIR@,${sbindir},g' \
	       ${D}${systemd_system_unitdir}/ppp@provider.service
}

