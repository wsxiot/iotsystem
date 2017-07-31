#include "temperature.h"

Temperature::Temperature(QObject *parent) :
    QObject(parent)
{
    temperature_ = 0;
    light_ = 0;
    humidity_ = 0;
    speed=0;
}

qint8 Temperature::GetID()
{
    return 0x02;
}

void Temperature::HandleSerialMsg(const QByteArray &msg)
{
    if(0x01 == msg[4])
    {
        unsigned char adc_value[2];
        temperature_ = (msg[5] << 8) + msg[6];//计算温度值公式
        humidity_ = (msg[7] << 8) + msg[8];//计算湿度值公式
        adc_value[0] = msg[10];
        adc_value[1] = msg[9];
        adc_value[0] = adc_value[0] >> 2;
        light_ = (adc_value[1]*256 + adc_value[0]) * 3.3 / 8192;
        light_ = light_ / 4;
        light_ = light_ * 913;
        if(speed == 2)
        {
            SocketClass::GetInstance()->WriteToSocket(GetSensorInfo());
            speed = 0;
        }
        else
        {
            speed++;
        }
    }
}

void Temperature::HandleSocketMsg(qint8 &)
{

}

QByteArray Temperature::GetSensorInfo()
{
    QString info;
    info+="1/10/";
    info+=QString::number(temperature_)+";";
    info+=QString::number(humidity_)+";";
    info+=QString::number(light_,'f',1);
    return info.toAscii();
}
