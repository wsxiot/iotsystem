#ifndef LIVEINGLED_H
#define LIVEINGLED_H

#include "moudle.h"

class Liveingled : public QObject, public Moudle
{
    Q_OBJECT
public:
    explicit Liveingled(QObject *parent = 0);
    qint8 GetID();
    void HandleSerialMsg(const QByteArray &msg);
    void HandleSocketMsg(qint8 &cmd);
    QByteArray GetSensorInfo();
    
signals:
    
public slots:

private:
    static QByteArray livingledon;
    static QByteArray livingledoff;
};

#endif // LIVEINGLED_H
