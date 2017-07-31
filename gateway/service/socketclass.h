#ifndef SOCKETCLASS_H
#define SOCKETCLASS_H

#include <QObject>
#include <QDebug>
#include <QTcpSocket>
#include <QTimer>

class SocketClass : public QObject
{
    Q_OBJECT
public:
    static SocketClass* GetInstance();
    void WriteToSocket(const QByteArray &byte);

signals:
    void SocketMsg(QByteArray byte);
    void Disconnection();
    void Connection();

public slots:
    void ReadFromSocket();
    void KeepConnection();

private:
    explicit SocketClass(QObject *parent = 0);
    static SocketClass* SocketClassSingle;
    QTcpSocket* my_socket;//use for communication
    QTimer *timer;
    bool Conn_status;
    QString host;
    quint16 port;

};

#endif // SOCKETCLASS_H
