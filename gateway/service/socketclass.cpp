#include "socketclass.h"

SocketClass::SocketClass(QObject *parent) : QObject(parent)
{
    host = "182.254.130.103";
    port = 6789;
    Conn_status = false;
    my_socket = new QTcpSocket();
    connect(my_socket,SIGNAL(readyRead()),this,SLOT(ReadFromSocket()));
    timer = new QTimer(this);
    connect(timer,SIGNAL(timeout()),this,SLOT(KeepConnection()));
    timer->start(3000);
    qDebug() << "socketclass initial successful";
}

SocketClass* SocketClass::SocketClassSingle = 0;

SocketClass* SocketClass::GetInstance()
{
    if(0 == SocketClassSingle)
    {
        SocketClassSingle = new SocketClass;
    }
    return SocketClassSingle;
}

void SocketClass::ReadFromSocket()
{
    if(my_socket && Conn_status)
    {
        emit SocketMsg(my_socket->readAll());
    }
}

void SocketClass::WriteToSocket(const QByteArray &byte)
{
    if(my_socket && Conn_status)
    {
        my_socket->write(byte);
        qDebug()<<"writetosocket: "<<byte.toLower();
    }
}

void SocketClass::KeepConnection()
{
    if(!(my_socket->waitForConnected(1000)))
    {
        if(Conn_status)
        {
            qDebug() << "disconnect with server,try to connect!!";
            Conn_status = false;
            emit Disconnection();
        }
        qDebug() << "unconntion heart beats";
        my_socket->connectToHost(host,port);
    }
    else
    {
        if(!Conn_status)
        {
            qDebug()<<"Socket connect success";
            Conn_status = true;
            emit Connection();
        }
        qDebug() << "conntion heart beats";
    }
}
