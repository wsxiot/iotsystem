#include "moudleset.h"

MoudleSet::MoudleSet(QObject *parent) :
    QObject(parent)
{
}

void MoudleSet::InitMoudle()
{
    serial_service_ = SerialClass::GetInstance();
    socket_serivce_ = SocketClass::GetInstance();
    connect(this->serial_service_,SIGNAL(SerialMsg(QByteArray)),this,SLOT(ReadSerial(QByteArray)));
    connect(this->socket_serivce_,SIGNAL(SocketMsg(QByteArray)),this,SLOT(ReadSocket(QByteArray)));
    connect(this->socket_serivce_,SIGNAL(Connection()),this,SLOT(loginverify()));

    temp_moudle_   = new Temperature();
    livingled_moudle_ = new Liveingled();
    bedled_moudle_ = new Bedled();

    moudle_hash_.insert(0x02,temp_moudle_);
    moudle_hash_.insert(0x09,livingled_moudle_);
    moudle_hash_.insert(0x11,bedled_moudle_);
}

void MoudleSet::ReadSerial(QByteArray byte)
{
    qint8 node = byte[3];
    qDebug() << "Node ID : 0x"+QString::number(node,16);
    if(node == 0)
        return;
    Moudle* temp = moudle_hash_.value(node,NULL);
    if(NULL != temp)
        temp->HandleSerialMsg(byte);
    else
        qDebug() << "Error Occur on serial: Node is " << node;
}

void MoudleSet::ReadSocket(QByteArray byte)
{
    qDebug() << "Receive Form Server : " << byte.toHex();
    char *b = byte.data();
    qint8 node = b[0];
    if(node == 0x41 || node == 0x40)//signin and signup
    {
        return;
    }
    else
    {
        Moudle* temp = moudle_hash_.value(node,NULL);
        if(NULL != temp)
        {
            qint8 cmd = b[1];
            temp->HandleSocketMsg(cmd);
        }
        else
            qDebug() << "Error Occur on socket: Node is " << node;
    }
}

void MoudleSet::loginverify()
{
    socket_serivce_->WriteToSocket("1/0/1;123");
}

