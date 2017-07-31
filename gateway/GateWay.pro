#-------------------------------------------------
#
# Project created by QtCreator 2017-05-18T22:16:57
#
#-------------------------------------------------

QT       += core gui network sql

TARGET = GateWay
TEMPLATE = app


SOURCES += main.cpp\
        widget.cpp \
    port/posix_qextserialport.cpp \
    port/qextserialbase.cpp \
    port/qextserialport.cpp \
    service/serialclass.cpp \
    service/socketclass.cpp \
    maincode/nodeset/moudle.cpp \
    maincode/nodeset/temperature.cpp \
    maincode/moudleset.cpp \
    maincode/nodeset/liveingled.cpp \
    maincode/nodeset/bedled.cpp

HEADERS  += widget.h \
    port/posix_qextserialport.h \
    port/qextserialbase.h \
    port/qextserialport.h \
    service/serialclass.h \
    service/socketclass.h \
    maincode/nodeset/moudle.h \
    maincode/nodeset/temperature.h \
    maincode/moudleset.h \
    maincode/nodeset/liveingled.h \
    maincode/nodeset/bedled.h

FORMS    += widget.ui

unix:DEFINES           += _TTY_POSIX_
