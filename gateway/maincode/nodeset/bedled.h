#ifndef BEDLED_H
#define BEDLED_H

#include "moudle.h"

class Bedled : public QObject, public Moudle
{
    Q_OBJECT
public:
    explicit Bedled(QObject *parent = 0);
    qint8 GetID();
    void HandleSerialMsg(const QByteArray &msg);
    void HandleSocketMsg(qint8 &cmd);
    QByteArray GetSensorInfo();
    
signals:
    
public slots:

private:
    static QByteArray bedledon;
    static QByteArray bedledoff;
    
};

#endif // BEDLED_H
