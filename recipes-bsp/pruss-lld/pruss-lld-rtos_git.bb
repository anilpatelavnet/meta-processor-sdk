inherit ti-pdk
require pruss-lld.inc

DEPENDS += "osal-rtos"
DEPENDS_append_ti33x = " starterware"
DEPENDS_append_ti43x = " starterware"

XDCARGS = ""
XDCARGS_ti33x = "am335x"
XDCARGS_ti43x = "am437x"
XDCARGS_omap-a15 = "am571x am572x"

PARALLEL_XDC = "--jobs=${BB_NUMBER_THREADS}"
