PR_append = ".tisdk0"

IMAGE_INSTALL_append_am64xx += "\
    dropbear \
    linuxptp \
    iproute2 \
    tensorflow-lite \
"

IMAGE_INSTALL_append_am62xx += "\
    packagegroup-dl \
"

IMAGE_INSTALL_append_am62xx-lp-evm += "\
    packagegroup-dl \
"

WIC_CREATE_EXTRA_ARGS += " --no-fstab-update"
# Extra boot files for WIC images
do_image_wic_append_am64xx-evm[depends] += " wifi-oob:do_deploy"
IMAGE_BOOT_FILES_append_am64xx-evm += " wificfg"
do_image_wic_append_am62xx-evm[depends] += " wifi-oob:do_deploy"
IMAGE_BOOT_FILES_append_am62xx-evm += " wificfg"
do_image_wic_append_am62xx-lp-evm[depends] += " wifi-oob:do_deploy"
IMAGE_BOOT_FILES_append_am62xx-lp-evm += " wificfg"
