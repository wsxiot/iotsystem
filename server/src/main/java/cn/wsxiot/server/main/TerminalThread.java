package cn.wsxiot.server.main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;

import static cn.wsxiot.server.main.InitServer.*;
import static cn.wsxiot.server.serverutil.DatabaseUtil.*;
import static cn.wsxiot.server.serverutil.DealmsgUtil.*;

/**
 * Created by wsx on 2017-07-12.
 */
public class TerminalThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private DataOutputStream out;
    private volatile boolean isrun;//跳出线程标志
    private int terminalid;// 终端标志，1为网关线程，2为android线程,0为未分配
    private int gid;//网关唯一性标识
    private int aid;//adnroid唯一性标识
    private int uid;//用户标识
    private int wid;//web唯一标识

    /**
     * construct method
     * @param socket
     */
    public TerminalThread(Socket socket) {
        try {
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            out = new DataOutputStream(socket.getOutputStream());
            isrun = true;
            terminalid = -1;
            gid = -1;
            aid = this.hashCode();
            uid = -1;
            wid = this.hashCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理网关连接操作
     * @param msgs
     * @throws Exception
     */
    public void processMsgFromGateway(String[] msgs) throws Exception {
        if(msgs[1].equals(SIGNIN)){//登录相关
            if(dealSIGNINfromGateway(this, msgs) == false){
                disConnected();
            }
        }else if(msgs[1].equals(TEMPHUMILIGHT)){
            dealTEMPHUMILIGHTfromGateway(this, msgs);
        }
    }

    /**
     * 处理android连接操作
     * @param msgs
     * @throws Exception
     */
    public void processMsgFromAndroid(String[] msgs) throws Exception {
        if(msgs[1].equals(SIGNIN)){
            if(dealSIGNINfromAndroid(this, msgs) == false){
                disConnected();
            }
        }else if(msgs[1].equals(BINDGATEWAY)){
            dealBINDGATEWAYfromAndroid(this, msgs);
        }else if(msgs[1].equals(TEMPHUMILIGHT)){
            dealTEMPHUMILIGHTfromAndroid(this, msgs);
        }else if(msgs[1].equals(LIVINGLED)){
            dealLIVINGLEDfromAndroid(this, msgs);
        }else if(msgs[1].equals(BEDLED)){
            dealBEDLEDfromAndroid(this, msgs);
        }
    }

    /**
     * 处理web连接操作
     * @param msgs
     * @throws Exception
     */
    public void processMsgFromWeb(String[] msgs) throws Exception {
        if(msgs[1].equals(SIGNIN)){
            if(dealSIGNINfromWeb(this, msgs) == false){
                disConnected();
            }
        }else if(msgs[1].equals(LIVINGLED)){
            dealLIVINGLEDfromWeb(this, msgs);
        }else if(msgs[1].equals(BEDLED)){
            dealBEDLEDfromWeb(this, msgs);
        }
    }

    /**
     * 运行线程首先进行构造方法然后再运行run方法
     */
    @Override
    public void run() {
        while (isrun) {
            try {
                char[] b = new char[1024];
                int len = in.read(b);
                String str = new String(b, 0, len).trim();
                String[] msgs = str.split("/");
                System.out.println("---receives:"+Arrays.toString(msgs));
                if (msgs[0].equals(GATEWAY)){ // 网关
                    terminalid = Integer.parseInt(GATEWAY);
                    processMsgFromGateway(msgs);
                }else if(msgs[0].equals(ANDROIDPHONE)){// android
                    terminalid = Integer.parseInt(ANDROIDPHONE);
                    processMsgFromAndroid(msgs);
                }else if(msgs[0].equals(WEB)){
                    terminalid = Integer.parseInt(WEB);
                    processMsgFromWeb(msgs);
                }
            } catch (Exception e) {
                disConnected();
            }
        }
    }

    /**
     * 断开连接，处理善后工作
     */
    public void disConnected(){
        this.isrun = false;
        try {
            if (in != null)
                in.close();
            if (out != null)
                out.close();
            if(socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(terminalid == Integer.parseInt(GATEWAY)){//网关
            gateway_threads.remove(gid);
            updateGatewayol(gid, 0);
            System.out.println("gateway disconnected!");
        }else if(terminalid == Integer.parseInt(ANDROIDPHONE)){
           android_threads.remove(aid);
           System.out.println("android disconnected!");
        }else if(terminalid == Integer.parseInt(WEB)){
            web_threads.remove(wid);
            System.out.println("web disconnected!");
        }
    }

    public DataOutputStream getOut() {
        return out;
    }

    public int getGid() {
        return gid;
    }

    public int getAid() {
        return aid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getWid() {
        return wid;
    }
}
