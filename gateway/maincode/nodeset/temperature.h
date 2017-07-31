#ifndef TEMPERATURE_H
#define TEMPERATURE_H

#include "moudle.h"

class Temperature : public QObject, public Moudle
{
    Q_OBJECT
public:
    explicit Temperature(QObject *parent = 0);
    qint8 GetID();
    void HandleSerialMsg(const QByteArray &msg);
    void HandleSocketMsg(qint8 &);
    QByteArray GetSensorInfo();
    
signals:
    
public slots:

private:
    int temperature_;
    int humidity_;
    float light_;
    int speed;
    
};

#endif // TEMPERATURE_H
