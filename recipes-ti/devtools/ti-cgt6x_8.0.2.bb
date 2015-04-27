DESCRIPTION = "TI DSP Code Generation Tools"
HOMEPAGE = "https://www-a.ti.com/downloads/sds_support/TICodegenerationTools/download.htm"
LICENSE = "(TI-TSPA & Thai-Open-Source-Software-Center) & BSD-3-Clause & BSL-1.0 & Hewlett-Packard & AFL-3.0 & MIT & BSD-2-Clause & PD"

LIC_FILES_CHKSUM = "file://ti-cgt-c6000_${PV}/LICENSE.txt;md5=b6311962635a4f15630e36ec2d875eca"
LIC_FILES_CHKSUM_class-target = "file://usr/share/doc/ti/cgt-c6x/LICENSE.txt;md5=b6311962635a4f15630e36ec2d875eca"

PE = "1"

require recipes-ti/includes/ti-unpack.inc

COMPATIBLE_HOST_class-target = "arm.*-linux"

# For now we only have hardfp version for target class
python __anonymous() {
    c = bb.data.getVar("CLASSOVERRIDE", d, 1)

    if c == "class-target":
        tunes = bb.data.getVar("TUNE_FEATURES", d, 1)
        if not tunes:
            return
        pkgn = bb.data.getVar("PN", d, 1)
        pkgv = bb.data.getVar("PV", d, 1)
        if "callconvention-hard" not in tunes:
            bb.warn("%s-%s ONLY supports hardfp mode for now" % (pkgn, pkgv))
            raise bb.parse.SkipPackage("%s-%s ONLY supports hardfp mode for now" % (pkgn, pkgv))
}


BINFILE = "ti_cgt_c6000_${PV}_linux_installer_x86.bin"
BINFILE_NAME = "cgt6x_x86_installer"
TI_BIN_UNPK_ARGS = "--prefix ${S}"
TI_BIN_UNPK_CMDS = ""

BINFILE_class-target = "ti_cgt_c6000_${PV}_armlinuxa8hf_busybox_installer.sh"
BINFILE_NAME_class-target = "cgt6x_arm_installer"

SRC_URI = "http://software-dl.ti.com/codegen/esd/cgt_public_sw/C6000/${PV}/${BINFILE};name=${BINFILE_NAME}"

SRC_URI[cgt6x_x86_installer.md5sum] = "0b190a36a9a299ac6cfa645b104f2150"
SRC_URI[cgt6x_x86_installer.sha256sum] = "677554f420e389f6a5fb911db58b3dd23b5d7ab5b6ed906edb651a0026d57766"

SRC_URI[cgt6x_arm_installer.md5sum] = "4bdce604383e03be1b40499762f72397"
SRC_URI[cgt6x_arm_installer.sha256sum] = "b5c9c363fa7add632977d5ef24fea6b8d5174667ff94fdd448c9bf21aa91cebe"

do_install() {
    install -d ${D}${bindir}
    for binfile in ${S}/ti-cgt-c6000_${PV}/bin/*; do
        install -m 755 ${binfile} ${D}${bindir}
    done

    install -d ${D}${datadir}/ti/cgt-c6x/include
    for includefile in ${S}/ti-cgt-c6000_${PV}/include/*; do
        install -m 644 ${includefile} ${D}${datadir}/ti/cgt-c6x/include
    done

    install -d ${D}${datadir}/ti/cgt-c6x/lib
    for libfile in ${S}/ti-cgt-c6000_${PV}/lib/*.a; do
        install -m 644 ${libfile} ${D}${datadir}/ti/cgt-c6x/lib
    done

    for libfile in ${S}/ti-cgt-c6000_${PV}/lib/*.cmd; do
        install -m 644 ${libfile} ${D}${datadir}/ti/cgt-c6x/lib
    done

    for libfile in ${S}/ti-cgt-c6000_${PV}/lib/*.lib; do
        install -m 644 ${libfile} ${D}${datadir}/ti/cgt-c6x/lib
    done

    install -d ${D}${datadir}/ti/cgt-c6x/lib/src
    for srcfile in ${S}/ti-cgt-c6000_${PV}/lib/src/*; do
        install -m 644 ${srcfile} ${D}${datadir}/ti/cgt-c6x/lib/src
    done

    install -d ${D}${datadir}/man/man1
    for manfile in ${S}/ti-cgt-c6000_${PV}/man/man1/*; do
        install -m 644 ${manfile} ${D}${datadir}/man/man1
    done

    install -d ${D}${datadir}/doc/ti/cgt-c6x
    for docfile in ${S}/ti-cgt-c6000_${PV}/*.txt; do
        install -m 644 ${docfile} ${D}${datadir}/doc/ti/cgt-c6x
    done

    for docfile in ${S}/ti-cgt-c6000_${PV}/*.htm; do
        install -m 644 ${docfile} ${D}${datadir}/doc/ti/cgt-c6x
    done

    for docfile in ${S}/ti-cgt-c6000_${PV}/*.spdx; do
        install -m 644 ${docfile} ${D}${datadir}/doc/ti/cgt-c6x
    done

    for docfile in ${S}/ti-cgt-c6000_${PV}/doc/*; do
        install -m 644 ${docfile} ${D}${datadir}/doc/ti/cgt-c6x
    done

}

do_install_class-target() {
    ${WORKDIR}/${BINFILE} --prefix ${D}
}

FILES_${PN} += "${datadir}/ti/*"

BBCLASSEXTEND = "native nativesdk"
