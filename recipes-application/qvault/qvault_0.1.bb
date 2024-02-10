SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Recipe created by bitbake-layers"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI = "git://github.com/EddyTheCo/QVault.git;protocol=https;branch=main"

PV = "1.0.0+1.1+git${SRCPV}"
SRCREV = "6fa29e08b2b38566147e4fd57e2eeea05346a828"


S = "${WORKDIR}/git"
DEPENDS = " \
    qtbase \
    qtdeclarative \
    qtdeclarative-native \
    openssl \
"
SYSROOT_DIRS += " ${bindir} "
inherit qt6-cmake 
EXTRA_OECMAKE:append = "-G Ninja -DFETCHCONTENT_FULLY_DISCONNECTED=OFF -DCMAKE_BUILD_TYPE=Release -DBUILD_SHARED_LIBS=ON -DUSE_QML=OFF "

do_configure[network] =  "1"
do_compile[network] = "1"


#https://docs.yoctoproject.org/dev-manual/prebuilt-libraries.html#non-versioned-libraries
INSANE_SKIP:${PN} = "ldflags"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
SOLIBS = ".so"
FILES_SOLIBSDEV = ""


