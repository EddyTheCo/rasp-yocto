SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Recipe created by bitbake-layers"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI = "git://github.com/EddyTheCo/DLockersServer.git;protocol=https;branch=use_rpi"

PV = "1.0.0+1.1+git${SRCPV}"
SRCREV = "1ce76f9405090a8a67ae899add9189c0df415526"


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
"
inherit qt6-cmake 

EXTRA_OECMAKE:append = "-G Ninja -DFETCHCONTENT_FULLY_DISCONNECTED=OFF -DBUILD_TESTING=ON -DRPI_SERVER=ON -DBUILD_SHARED_LIBS=ON" 
do_configure[network] =  "1"
do_compile[network] = "1"


#https://docs.yoctoproject.org/dev-manual/prebuilt-libraries.html#non-versioned-libraries
INSANE_SKIP:${PN} = "ldflags"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
SOLIBS = ".so"
FILES_SOLIBSDEV = ""
