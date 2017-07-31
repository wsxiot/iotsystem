#ifndef MOUDLESET_H
#define MOUDLESET_H

#include <QObject>
#include <QTimer>
#include <QHash>
#include "../service/serialclass.h"
#include "../service/socketclass.h"
#include "nodeset/moudle.h"
#include "nodeset/temperature.h"
#include "nodeset/liveingled.h"
#include "nodeset/bedled.h"

class MoudleSet : public QObject
{
    Q_OBJECT
public:
    explicit MoudleSet(QObject *parent = 0);
    void InitMoudle();
    
signals:
    
public slots:
    void ReadSerial(QByteArray byte);
    void ReadSocket(QByteArray byte);
    void loginverify();

private:
    SerialClass *serial_service_;
    SocketClass *socket_serivce_;
    Moudle* temp_moudle_;
    Moudle* livingled_moudle_;
    Moudle* bedled_moudle_;
    QHash<qint8, Moudle*> moudle_hash_;
    
};

#endif // MOUDLESET_H
