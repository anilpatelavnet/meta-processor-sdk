DESCRIPTION = "TI Starterware"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://Makefile;beginline=1;endline=35;md5=286cbb5dce3e348294b6b025cff969b3"

require recipes-ti/includes/ti-paths-append.inc
require recipes-ti/includes/ti-staging.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "ti33x|ti43x"

DEPENDS = "ti-ccsv6-native"

BRANCH = "master"
SRC_URI = "git://gitorious.design.ti.com/starterware/starterware-src.git;protocol=git;branch=${BRANCH}"

SRCREV = "2b12ac9c7697d704020ecc8701cc28c2b74006c6"
PR = "r2"

S = "${WORKDIR}/git"

PARTNO = ""
PARTNO_ti33x = "am335x"
PARTNO_ti43x = "am437x"

export TOOLCHAIN_PATH_A8 = "${A15_TOOLCHAIN_INSTALL_DIR}"
export TOOLCHAIN_PATH_A9 = "${A15_TOOLCHAIN_INSTALL_DIR}"
export FPULIB_PATH = "${A15_TOOLCHAIN_INSTALL_DIR}/lib/gcc/arm-none-eabi/4.8.4/fpu/"

do_compile() {
    cd build
    ./release_${PARTNO}.sh
}

do_install() {
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/starterware
    find -name "*.tar" -exec tar xf {} -C ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/starterware \;
}

FILES_${PN} += "${PDK_INSTALL_DIR_RECIPE}/packages"

INSANE_SKIP_${PN} = "arch staticdev"