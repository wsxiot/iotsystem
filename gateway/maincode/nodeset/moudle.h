#ifndef MOUDLE_H
#define MOUDLE_H

#include "../../service/serialclass.h"
#include "../../service/socketclass.h"

class Moudle
{
public:
    virtual qint8 GetID() = 0;
    virtual void HandleSerialMsg(const QByteArray &msg)=0;
    virtual void HandleSocketMsg(qint8 &cmd)=0;
    virtual QByteArray GetSensorInfo()=0;
    unsigned char Varify (unsigned char  *date, unsigned short len);

};

#endif // MOUDLE_H
