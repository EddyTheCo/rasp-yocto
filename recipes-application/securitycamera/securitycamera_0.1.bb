SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Recipe created by bitbake-layers"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/EddyTheCo/SecurityCamera.git;protocol=ssh;branch=server_yocto"

PV = "1.0.0+1.1+git${SRCPV}"
SRCREV = "${AUTOREV}"


S = "${WORKDIR}/git"
DEPENDS = " \
    opencv \
    boost \
"
PACKAGECONFIG:append:opencv = "gstreamer ffmpeg videoio core imgproc objdetect imgcodecs"
PACKAGECONFIG:remove:boost = " graph_parallel locale mpi python "
inherit cmake
EXTRA_OECMAKE:append = "-G Ninja -DFETCHCONTENT_FULLY_DISCONNECTED=OFF -DCMAKE_BUILD_TYPE=Release -DBUILD_CLIENT=OFF "

do_configure[network] =  "1"
do_compile[network] = "1"


#https://docs.yoctoproject.org/dev-manual/prebuilt-libraries.html#non-versioned-libraries
INSANE_SKIP:${PN} = "ldflags"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"


inherit systemd
SYSTEMD_SERVICE:${PN} = "securitycamera.service"

SRC_URI  += " file://securitycamera.service "
FILES:${PN} += "${systemd_unitdir}/system/securitycamera.service"

do_install:append() {
  install -d ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/securitycamera.service ${D}/${systemd_system_unitdir}/
}
