#include "serialclass.h"

SerialClass::SerialClass(QObject *parent) :
    QObject(parent),
    my_com_(0),
    com_state_(CLOSE)
{
    CloseCom();
    OpenCom();
    timer = new QTimer();
    timer->start(READTIME);
    connect(this->timer,SIGNAL(timeout()),this,SLOT(ReadFromSerial()));
}

SerialClass* SerialClass::SerialClassSingle = 0;

void SerialClass::ReadFromSerial()
{
    if(my_com_&&OPEN == com_state_)
    {
        QByteArray byte = my_com_->readAll();
        if(byte.length())
            emit SerialMsg(byte);
    }
}

int SerialClass::WriteToSerial(const QByteArray &byte)
{
    if(my_com_&&OPEN == com_state_)
    {
        qDebug()<<"writetoserial: "<<byte.toHex();
        return my_com_->write(byte);
    }
    return -1;
}

bool SerialClass::OpenCom()
{
    if(my_com_)
    {
        CloseCom();
    }
    else
    {
        QString com_name_ = "/dev/ttySAC1";
        my_com_ = new Posix_QextSerialPort(com_name_, QextSerialBase::Polling);
    }
    my_com_->open(QIODevice::ReadWrite);
    if(my_com_->isOpen())
    {
        com_state_ = OPEN;
        my_com_->setBaudRate(BAUD115200);
        my_com_->setDataBits(DATA_8);
        my_com_->setParity(PAR_NONE);
        my_com_->setStopBits(STOP_1);
        my_com_->setFlowControl(FLOW_OFF);
        my_com_->setTimeout(50);
        return true;
    }
    else
    {
        com_state_ = CLOSE;
        return false;
    }
}

bool SerialClass::CloseCom()
{
    if(my_com_&&CLOSE!=com_state_)
    {
        my_com_->close();
    }
    com_state_ = CLOSE;
    return true;
}

void SerialClass::ReleaseSerial()
{
    if(my_com_)
    {
        CloseCom();
        delete my_com_;
        my_com_ = 0;
    }
}

SerialClass* SerialClass::GetInstance()
{
    if(0 == SerialClassSingle)
    {
        SerialClassSingle = new SerialClass;
    }
    return SerialClassSingle;
}
