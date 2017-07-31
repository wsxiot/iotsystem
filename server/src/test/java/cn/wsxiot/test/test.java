package cn.wsxiot.test;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by wsx on 2017-07-14.
 */
public class test {

    public static void main(String[] args){
        try {
            Socket socket = new Socket("182.254.130.103", 6789);//182.254.130.103
            //Socket socket = new Socket("localhost", 6789);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("2/0/2000;860223178@qq.com;123456");
            System.out.println(dis.readUTF());
            Thread.sleep(1000);
            dos.writeUTF("2/1/2001");
            System.out.println(dis.readUTF());
            Thread.sleep(1000);
            dos.writeUTF("2/10/2002;1");
            System.out.println(dis.readUTF());
            Thread.sleep(1000);
            dos.writeUTF("2/11/2003;1");
            System.out.println(dis.readUTF());
            Thread.sleep(1000);
            dos.writeUTF("2/12/2005;1");
            System.out.println(dis.readUTF());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
