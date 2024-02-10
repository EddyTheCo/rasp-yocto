SUMMARY = "Autostart Qt6 demo app"
HOMEPAGE = "https://www.estervtech.com"
LICENSE = "MIT"

# Base this image on rpi-basic-image -> core-image-base
require recipes-core/images/core-image-base.bb
#IMAGE_FEATURES += " package-management"

IMAGE_LINGUAS = "en-us"

COMPATIBLE_MACHINE = "^rpi$"


IMAGE_INSTALL:remove = " packagegroup-core-x11-base connman"

IMAGE_INSTALL:append = " \
   wpa-supplicant \
   dhcpcd \
   ppp \
"

export IMAGE_BASENAME = "qt6-evt-basic-image"
