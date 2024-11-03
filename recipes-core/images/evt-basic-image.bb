SUMMARY = "Autostart Qt6 demo app"
HOMEPAGE = "https://www.estervtech.com"
LICENSE = "MIT"

require recipes-core/images/core-image-base.bb

IMAGE_LINGUAS = "en-us"

COMPATIBLE_MACHINE = "^rpi$"


IMAGE_INSTALL:remove = " packagegroup-core-x11-base connman"

IMAGE_INSTALL:append = " \
   wpa-supplicant \
   dhcpcd \
   ntp \
"

export IMAGE_BASENAME = "evt-basic-image"
