inherit ti-pdk
require uart-lld.inc

DEPENDS += "edma3-lld-rtos"
XDCPATH_append = ";${EDMA3_LLD_INSTALL_DIR}/packages"
