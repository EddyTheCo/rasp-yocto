SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Recipe created by bitbake-layers"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI = "git://github.com/opencv/opencv.git;protocol=https;branch=4.x"

PV = "1.0.0+1.1+git${SRCPV}"
SRCREV = "dad8af6b17f8e60d7b95a1203a1b4d22f56574cf"


S = "${WORKDIR}/git"
DEPENDS = " \
    unzip \
    zlib \
"

inherit cmake 
EXTRA_OECMAKE:append = "-G Ninja \
-DFETCHCONTENT_FULLY_DISCONNECTED=OFF \
-DCMAKE_BUILD_TYPE=Release -DBUILD_SHARED_LIBS=ON \
-DBUILD_opencv_js=OFF \ 
        -DBUILD_JAVA=OFF \ 
        -DBUILD_ANDROID_EXAMPLES=OFF \
        -DBUILD_ANDROID_PROJECTS=OFF \ 
        -DBUILD_opencv_dnn=OFF \
        -DBUILD_opencv_photo=OFF \
        -DBUILD_opencv_objdetect=ON \
        -DBUILD_opencv_video=OFF \
        -DBUILD_opencv_imgproc=ON \
        -DBUILD_opencv_videoio=OFF \ 
        -DBUILD_opencv_highgui=OFF \ 
        -DBUILD_opencv_stitching=OFF \
        -DBUILD_opencv_superres=OFF \ 
        -DBUILD_opencv_videostab=OFF \
        -DBUILD_opencv_shape=OFF \
        -DBUILD_opencv_imgcodecs=OFF \
        -DBUILD_opencv_ml=OFF \
        -DBUILD_opencv_gapi=OFF \
        -DBUILD_opencv_flann=ON \
        -DBUILD_opencv_features2d=ON \
        -DBUILD_opencv_calib3d=ON \
        -DBUILD_opencv_apps=OFF \
        -DBUILD_PERF_TESTS=OFF \ 
        -DBUILD_WASM_INTRIN_TESTS=OFF \
        -DBUILD_DOCS=OFF \
        -DBUILD_TESTS=OFF \
        -DBUILD_PACKAGE=OFF \
        -DBUILD_EXAMPLES=OFF \
        -DBUILD_opencv_python3=OFF \
        -DBUILD_opencv_python2=OFF \
        -DBUILD_opencv_java=OFF \
        -DCV_ENABLE_INTRINSICS=OFF \ 
        -DWITH_PTHREADS_PF=OFF \
        -DWITH_ITT=OFF \
        -DWITH_LAPACK=OFF \
        -DWITH_GPHOTO2=OFF \
        -DWITH_OPENCLAMDBLAS=OFF \
        -DWITH_OPENCLAMDFFT=OFF \
        -DWITH_OPENCL_SVM=OFF \
        -DWITH_OPENCL=OFF \
        -DWITH_V4L=OFF \
        -DWITH_TIFF=OFF \
        -DWITH_TBB=OFF \
        -DWITH_PNG=OFF \
        -DWITH_OPENNI2=OFF \
        -DWITH_OPENNI=OFF \
        -DWITH_OPENVX=OFF \
        -DWITH_OPENGL=OFF \
        -DWITH_OPENEXR=OFF \
        -DWITH_WEBP=OFF \
        -DWITH_JPEG=OFF \
        -DWITH_JASPER=OFF \
        -DWITH_IPP=OFF \
        -DWITH_GTK_2_X=OFF \
        -DWITH_GTK=OFF \
        -DWITH_GSTREAMER=OFF \
        -DWITH_FFMPEG=OFF \
        -DWITH_EIGEN=OFF \
        -DWITH_VTK=OFF \
        -DWITH_ADE=OFF \
        -DWITH_1394=OFF \ 
        -DWITH_CUDA=OFF \
        -DBUILD_ITT=OFF \
        -DBUILD_opencv_ittnotify=OFF \
        -DCV_TRACE=OFF \
        -DWITH_VA_INTEL=OFF \
        -DBUILD_TESTING=OFF \
        -DWITH_ARITH_DEC=OFF \
        -DWITH_ARITH_ENC=OFF \
        -DWITH_CUBLAS=OFF \
        -DWITH_CUFFT=OFF \
        -DWITH_GDAL=OFF \
        -DWITH_HALIDE=OFF \
        -DWITH_NVCUVID=OFF \
        -DWITH_PROTOBUF=OFF \
        -DWITH_QUIRC=ON \
        -DBUILD_OPENJPEG=OFF \
        -DWITH_OPENJPEG=OFF \
        -DBUILD_TESTING=OFF \
 "
do_configure[network] =  "1"
do_compile[network] = "1"

RDEPENDS:${PN}-apps  = "bash"

FILES:${PN} += "${bindir} ${libdir}/* ${datadir}/opencv4 ${datadir}/licenses"

do_install:append() {
    # Move Python files into correct library folder (for multilib build)
    if [ "$libdir" != "/usr/lib" -a -d ${D}/usr/lib ]; then
        mv ${D}/usr/lib/* ${D}/${libdir}/
        rm -rf ${D}/usr/lib
    fi
    # remove build host path to improve reproducibility
    if [ -f ${D}${libdir}/cmake/opencv4/OpenCVModules.cmake ]; then
        sed -e 's@${STAGING_DIR_HOST}@@g' \
            -i ${D}${libdir}/cmake/opencv4/OpenCVModules.cmake
    fi
    # remove setup_vars_opencv4.sh as its content is confusing and useless
    if [ -f ${D}${bindir}/setup_vars_opencv4.sh ]; then
        rm -rf ${D}${bindir}/setup_vars_opencv4.sh
    fi
}
