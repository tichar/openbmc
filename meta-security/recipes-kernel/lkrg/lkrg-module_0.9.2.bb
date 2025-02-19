SUMMARY = "Linux Kernel Runtime Guard"
DESCRIPTION="LKRG performs runtime integrity checking of the Linux \
kernel and detection of security vulnerability exploits against the kernel."
SECTION = "security"
HOMEPAGE = "https://www.openwall.com/lkrg/"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://LICENSE;md5=5105ead24b08a32954f34cbaa7112432"

DEPENDS = "virtual/kernel elfutils"

SRC_URI = "https://download.openwall.net/pub/projects/lkrg/lkrg-${PV}.tar.gz \
           file://makefile_cleanup.patch "

SRC_URI[sha256sum] = "c2b501c47089cce3ec3114cef6520b73aa3a098836183186b9bb5e097c99ac27"

S = "${WORKDIR}/lkrg-${PV}"

inherit module kernel-module-split

MAKE_TARGETS = "modules"

MODULE_NAME = "p_lkrg"

module_do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/${MODULE_NAME}
    install -m 0644 ${MODULE_NAME}.ko \
    ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/${MODULE_NAME}/${MODULE_NAME}.ko
}

RPROVIDES:${PN} += "kernel-module-lkrg"

COMPATIBLE_HOST = "(i.86|x86_64|arm|aarch64).*-linux"
