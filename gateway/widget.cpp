#include "widget.h"
#include "ui_widget.h"

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
    this->setWindowFlags(Qt::FramelessWindowHint);
    QRect screenRect = QApplication::desktop()->availableGeometry();
    this->resize(screenRect.width(),screenRect.height());
    MoudleSet* moudleset = new MoudleSet;
    moudleset->InitMoudle();
}

Widget::~Widget()
{
    delete ui;
}
