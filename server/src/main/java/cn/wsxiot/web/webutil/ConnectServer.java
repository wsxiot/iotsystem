package cn.wsxiot.web.webutil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by wsx on 2017-07-18.
 */
public class ConnectServer {
    public final static String HOST = "localhost";
    public final static int PORT = 6789;

    /**
     * 向服务器发送命令
     * @param cmd 命令 2/0/2000;123456;123456
     * @return
     */
    synchronized public static String transCmd(String cmd){
        String ret = "";
        try {
            Socket socket = new Socket(HOST, PORT);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("3/0/3000;admin;admin123");
            System.out.println(dis.readUTF());
            dos.writeUTF(cmd);
            ret = dis.readUTF();
            System.out.println(ret);
            socket.close();
            dis.close();
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
