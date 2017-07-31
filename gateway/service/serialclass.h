#ifndef SERIALCLASS_H
#define SERIALCLASS_H

#include <QObject>
#include <QDebug>
#include <QTimer>
#include "../port/posix_qextserialport.h"

#define READTIME 100

class SerialClass : public QObject
{
    Q_OBJECT
public:
    static SerialClass* GetInstance();
    int WriteToSerial(const QByteArray &byte);

    bool OpenCom();
    bool CloseCom();
    void ReleaseSerial();

signals:
    void SerialMsg(QByteArray byte);

public slots:
    void ReadFromSerial();
private:
    explicit SerialClass(QObject *parent = 0);
    static SerialClass* SerialClassSingle;
    Posix_QextSerialPort *my_com_;
    enum{OPEN, CLOSE};
    int com_state_;
    QTimer* timer;
};

#endif // SERIALCLASS_H
