SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Recipe created by bitbake-layers"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI = "git://github.com/EddyTheCo/DLockersServer.git;protocol=https;branch=main"

PV = "1.0.0+1.1+git${SRCPV}"
SRCREV = "dc269a2cddfa8728ad9fe1727cf9e9b726ae5503"


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
    qtpositioning \
    qtserialport \
"
inherit qt6-cmake 

EXTRA_OECMAKE:append = "-G Ninja -DFETCHCONTENT_FULLY_DISCONNECTED=OFF -DBUILD_TESTING=ON -DRPI_SERVER=ON -DOpenCV_DOWNLOAD=OFF -DBUILD_SHARED_LIBS=ON" 
do_configure[network] =  "1"
do_compile[network] = "1"


#https://docs.yoctoproject.org/dev-manual/prebuilt-libraries.html#non-versioned-libraries
INSANE_SKIP:${PN} = "ldflags"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
SOLIBS = ".so"
FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/libopencv*"



inherit systemd
SYSTEMD_SERVICE:${PN} = "dlockers.service"

SRC_URI  += " file://dlockers.service "
FILES:${PN} += "${systemd_unitdir}/system/dlockers.service"

do_install:append() {
  install -d ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/dlockers.service ${D}/${systemd_system_unitdir}/
  sed -i -e 's,@LIBDIR@,${libdir},g' \
               ${D}${systemd_system_unitdir}/dlockers.service
}
