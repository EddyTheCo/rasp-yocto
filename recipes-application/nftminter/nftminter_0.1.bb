SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Recipe created by bitbake-layers"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI = "git://github.com/EddyTheCo/NftMinter.git;protocol=https;branch=main"

PV = "1.0.0+1.1+git${SRCPV}"
SRCREV = "184275be5d2dfa924e1167738807557fbae0cf37"


S = "${WORKDIR}/git"
DEPENDS = " \
    qtbase \
    qtdeclarative \
    qtdeclarative-native \
    qtquick3d \
    qtsvg \
    qtwebsockets \
    qtmqtt \
    qtshadertools \
    qtmultimedia \
    opencv \
    qvault \
"
PACKAGECONFIG:append:opencv = " objdetect imgproc flann features2d calib3d quirc"

inherit qt6-cmake
EXTRA_OECMAKE:append = "-G Ninja -DFETCHCONTENT_FULLY_DISCONNECTED=OFF -DCMAKE_BUILD_TYPE=Release -DBUILD_SHARED_LIBS=ON -DQTDEPLOY=OFF "

do_configure[network] =  "1"
do_compile[network] = "1"


#https://docs.yoctoproject.org/dev-manual/prebuilt-libraries.html#non-versioned-libraries
INSANE_SKIP:${PN} = "ldflags"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
SOLIBS = ".so"
FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/Esterv/*"


inherit systemd
SYSTEMD_SERVICE:${PN} = "nftminter.service"

SRC_URI  += " file://nftminter.service "
FILES:${PN} += "${systemd_unitdir}/system/nftminter.service"

do_install:append() {
  install -d ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/nftminter.service ${D}/${systemd_system_unitdir}/
  sed -i -e 's,@LIBDIR@,${libdir},g' \
               ${D}${systemd_system_unitdir}/nftminter.service
}
