#include "liveingled.h"

Liveingled::Liveingled(QObject *parent) :
    QObject(parent)
{    
}

QByteArray Liveingled::livingledoff("\x40\x06\x01\x09\x00\x50", 6);

QByteArray Liveingled::livingledon("\x40\x06\x01\x09\x06\x56", 6);

qint8 Liveingled::GetID()
{
    return 0x11;
}

void Liveingled::HandleSerialMsg(const QByteArray &msg)
{

}

void Liveingled::HandleSocketMsg(qint8 &cmd)
{
    if(cmd == 0x00)
    {
        SerialClass::GetInstance()->WriteToSerial(livingledoff);
    }
    else
    {
        SerialClass::GetInstance()->WriteToSerial(livingledon);
    }
}

QByteArray Liveingled::GetSensorInfo()
{

}
