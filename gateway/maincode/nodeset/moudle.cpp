#include "moudle.h"

unsigned char Moudle::Varify (unsigned char  *date, unsigned short len )
{
    unsigned char num = 0;
    unsigned short i;
    for (i = 0; i < len; i++)
    {
        num+= date[i];
    }
    return num;
}
