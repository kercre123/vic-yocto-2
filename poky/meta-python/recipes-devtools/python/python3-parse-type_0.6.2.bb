SUMMARY = "Simplifies building parse types based on the parse module"
HOMEPAGE = "https://github.com/jenisys/parse_type"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2e469278ace89c246d52505acc39c3da"

SRC_URI[sha256sum] = "79b1f2497060d0928bc46016793f1fca1057c4aacdf15ef876aa48d75a73a355"

PYPI_PACKAGE = "parse_type"
inherit pypi ptest setuptools3

RDEPENDS:${PN} += "python3-parse"

SRC_URI += " \
	file://run-ptest \
"

RDEPENDS:${PN}-ptest += " \
    python3-pytest \
    python3-unittest-automake-output \
"

do_install_ptest() {
	install -d ${D}${PTEST_PATH}/tests
	cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}
