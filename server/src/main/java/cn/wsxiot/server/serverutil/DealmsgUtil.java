package cn.wsxiot.server.serverutil;

import cn.wsxiot.server.main.TerminalThread;

import java.io.IOException;
import java.util.List;

import static cn.wsxiot.server.main.InitServer.android_threads;
import static cn.wsxiot.server.main.InitServer.gateway_threads;
import static cn.wsxiot.server.main.InitServer.web_threads;
import static cn.wsxiot.server.serverutil.DatabaseUtil.*;

/**
 * Created by wsx on 2017-07-14.
 */
public class DealmsgUtil {

    /**
     * 处理gateway登录相关
     * @param ttgateway
     * @param msgs
     * @return
     * @throws Exception
     */
    public static boolean dealSIGNINfromGateway(TerminalThread ttgateway, String[] msgs) throws Exception {
        ttgateway.setGid(DatabaseUtil.checkGatewayLogin(msgs));
        if(ttgateway.getGid() > 0){//登陆成功
            ttgateway.getOut().write(0x41);
            ttgateway.getOut().flush();
            gateway_threads.put(ttgateway.getGid(), ttgateway);
            System.out.println("gateway connected!");
            return true;
        }
        ttgateway.getOut().write(0x40);
        ttgateway.getOut().flush();
        return false;
    }

    /**
     * 处理gateway温湿度光照相关
     * @param ttgateway
     * @param msgs
     * @return
     * @throws Exception
     */
    public static boolean dealTEMPHUMILIGHTfromGateway(TerminalThread ttgateway, String[] msgs) throws Exception {
        saveTemphumilight(ttgateway.getGid(), msgs);
        return true;
    }

    /**
     * 处理android登录相关
     * @param ttandroid
     * @param msgs
     * @return
     * @throws Exception
     */
    public static boolean dealSIGNINfromAndroid(TerminalThread ttandroid, String[] msgs) throws Exception {
        ttandroid.setUid(checkAndroidLogin(msgs));
        if(ttandroid.getUid() > 0){//登录成功
            ttandroid.getOut().writeUTF("0/0/0001");
            ttandroid.getOut().flush();
            android_threads.put(ttandroid.getAid(), ttandroid);
            System.out.println("android login successful!");
            return true;
        }
        ttandroid.getOut().writeUTF("0/0/0000");
        ttandroid.getOut().flush();
        System.out.println("android login failed!");
        return false;
    }

    /**
     * 处理android绑定网关信息相关
     * @param ttandroid
     * @param msgs
     * @return
     * @throws Exception
     */
    public static boolean dealBINDGATEWAYfromAndroid(TerminalThread ttandroid, String[] msgs) throws Exception {
        List<Integer> glist = getGidlistfromuid(ttandroid.getUid());
        if(glist == null){
            ttandroid.getOut().writeUTF("0/1/0002");
            ttandroid.getOut().flush();
            return false;
        }
        String ret = "";
        for(int i=0; i<glist.size(); i++){
            if(i == 0)
                ret += String.valueOf(glist.get(i));
            else
                ret += ";"+String.valueOf(glist.get(i));
        }
        ttandroid.getOut().writeUTF("0/1/0002;"+ret);
        ttandroid.getOut().flush();
        return true;
    }

    /**
     * 处理android温湿度光照相关
     * @param ttandroid
     * @param msgs
     * @return
     * @throws Exception
     */
    public static boolean dealTEMPHUMILIGHTfromAndroid(TerminalThread ttandroid, String[] msgs) throws Exception {
        ttandroid.getOut().writeUTF("0/10/0003;"+getlatestTHL(msgs));
        ttandroid.getOut().flush();
        return true;
    }

    /**
     * 处理andoid客厅灯相关
     * @param ttandroid
     * @param msgs
     * @return
     * @throws Exception
     */
    public static boolean dealLIVINGLEDfromAndroid(TerminalThread ttandroid, String[] msgs) throws Exception {
        String[] infos = msgs[2].split(";");
        TerminalThread ttgateway = gateway_threads.get(Integer.parseInt(infos[1]));
        if(ttgateway == null){
            ttandroid.getOut().writeUTF("0/11/0005");
            ttandroid.getOut().flush();
            return false;
        }
        try {
            byte[] b = new byte[2];
            b[0] = 0x09;
            if(infos[0].equals("2003")){
                b[1] = 0x01;
            }else if(infos[0].equals("2004")){
                b[1] = 0x00;
            }
            ttgateway.getOut().write(b);
            ttgateway.getOut().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ttandroid.getOut().writeUTF("0/11/0004");
        ttandroid.getOut().flush();
        return true;
    }

    /**
     * 处理android卧室灯相关
     * @param ttandroid
     * @param msgs
     * @return
     * @throws Exception
     */
    public static boolean dealBEDLEDfromAndroid(TerminalThread ttandroid, String[] msgs) throws Exception {
        String[] infos = msgs[2].split(";");
        TerminalThread ttgateway = gateway_threads.get(Integer.parseInt(infos[1]));
        if(ttgateway == null){
            ttandroid.getOut().writeUTF("0/12/0007");
            ttandroid.getOut().flush();
            return false;
        }
        try {
            byte[] b = new byte[2];
            b[0] = 0x11;
            if(infos[0].equals("2005")){
                b[1] = 0x01;
            }else if(infos[0].equals("2006")){
                b[1] = 0x00;
            }
            ttgateway.getOut().write(b);
            ttgateway.getOut().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ttandroid.getOut().writeUTF("0/12/0006");
        ttandroid.getOut().flush();
        return true;
    }

    /**
     * 处理web登录相关
     * @param ttweb
     * @param msgs
     * @return
     * @throws Exception
     */
    public static boolean dealSIGNINfromWeb(TerminalThread ttweb, String[] msgs) throws Exception {
        String[] infos = msgs[2].split(";");
        if(infos[1].equals("admin") && infos[2].equals("admin123")){
            ttweb.getOut().writeUTF("0/0/0009");
            ttweb.getOut().flush();
            web_threads.put(ttweb.getWid(), ttweb);
            System.out.println("web connected!");
            return true;
        }
        ttweb.getOut().writeUTF("0/0/0008");
        ttweb.getOut().flush();
        System.out.println("web login failed!");
        return false;
    }

    /**
     * 处理web客厅灯相关
     * @param ttweb
     * @param msgs
     * @return
     * @throws Exception
     */
    public static boolean dealLIVINGLEDfromWeb(TerminalThread ttweb, String[] msgs) throws Exception {
        String[] infos = msgs[2].split(";");
        TerminalThread ttgateway = gateway_threads.get(Integer.parseInt(infos[1]));
        if(ttgateway == null){
            ttweb.getOut().writeUTF("0/11/0011");
            ttweb.getOut().flush();
            return false;
        }
        try {
            byte[] b = new byte[2];
            b[0] = 0x09;
            if(infos[0].equals("3001")){
                b[1] = 0x01;
            }else if(infos[0].equals("3002")){
                b[1] = 0x00;
            }
            ttgateway.getOut().write(b);
            ttgateway.getOut().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ttweb.getOut().writeUTF("0/11/0010");
        ttweb.getOut().flush();
        return true;
    }

    /**
     * 处理web卧室灯相关
     * @param ttweb
     * @param msgs
     * @return
     * @throws Exception
     */
    public static boolean dealBEDLEDfromWeb(TerminalThread ttweb, String[] msgs) throws Exception {
        String[] infos = msgs[2].split(";");
        TerminalThread ttgateway = gateway_threads.get(Integer.parseInt(infos[1]));
        if(ttgateway == null){
            ttweb.getOut().writeUTF("0/12/0013");
            ttweb.getOut().flush();
            return false;
        }
        try {
            byte[] b = new byte[2];
            b[0] = 0x11;
            if(infos[0].equals("3003")){
                b[1] = 0x01;
            }else if(infos[0].equals("3004")){
                b[1] = 0x00;
            }
            ttgateway.getOut().write(b);
            ttgateway.getOut().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ttweb.getOut().writeUTF("0/12/0012");
        ttweb.getOut().flush();
        return true;
    }

}
