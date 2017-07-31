#include "bedled.h"

Bedled::Bedled(QObject *parent) :
    QObject(parent)
{
}

QByteArray Bedled::bedledoff("\x40\x06\x01\x11\x00\x58", 6);

QByteArray Bedled::bedledon("\x40\x06\x01\x11\x06\x5E", 6);

qint8 Bedled::GetID()
{
    return 0x09;
}

void Bedled::HandleSerialMsg(const QByteArray &msg)
{

}

void Bedled::HandleSocketMsg(qint8 &cmd)
{
    if(cmd == 0x00)
    {
        SerialClass::GetInstance()->WriteToSerial(bedledoff);
    }
    else
    {
        SerialClass::GetInstance()->WriteToSerial(bedledon);
    }
}

QByteArray Bedled::GetSensorInfo()
{

}
