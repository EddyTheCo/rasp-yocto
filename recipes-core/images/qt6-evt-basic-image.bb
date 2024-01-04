SUMMARY = "Autostart Qt6 demo app"
HOMEPAGE = "https://www.estervtech.com"
LICENSE = "MIT"

# Base this image on rpi-basic-image -> core-image-base
require recipes-core/images/core-image-base.bb
#IMAGE_FEATURES += " package-management"

IMAGE_LINGUAS = "en-us"

COMPATIBLE_MACHINE = "^rpi$"


IMAGE_INSTALL:remove = " packagegroup-core-x11-base connman"

#IMAGE_INSTALL:append = " \
#    qtbase \
#    qttools \
#"

#qtdeclarative 
#qtdeclarative-tools 

# toolbox
#IMAGE_INSTALL:append = " \
#  vim \
#  i2c-tools \
#  usbutils \
#  wget \
#  minicom \
#"


# audio stuff
# IMAGE_INSTALL:append += " \
#   alsa-server \
#   alsa-plugins \
#   alsa-tools \
#   alsa-utils \
#   sox \
# "

# video stuff
# IMAGE_INSTALL:append = " \
#   opencv \
#   ffmpeg \
#   packagegroup-gstreamer1.0 \
# "

# web server stuff
# IMAGE_INSTALL:append = " \
#   lighttpd \
#   lighttpd-module-fastcgi \
#   php \
#   php-cgi \
#   php-cli \
# "

# for WiFi client 
IMAGE_INSTALL:append = " \
   wpa-supplicant \
   dhcpcd \
   ppp \
"

# ssh 
#IMAGE_FEATURES += "ssh-server-dropbear"


# blueetooth stuff
#IMAGE_INSTALL:append = " \
#  bluez5 \
#  bluez5-noinst-tools \
#"

# lttng tracing tools, see http://lttng.org/docs/
#IMAGE_INSTALL:append = " \
#  lttng-tools \
#  lttng-modules \
#  lttng-ust \
#  babeltrace \
#"

# autostart Qt application
# minicom
# ntp  
IMAGE_INSTALL:append = " \
 nftminter \ 
"


export IMAGE_BASENAME = "qt6-evt-basic-image"
