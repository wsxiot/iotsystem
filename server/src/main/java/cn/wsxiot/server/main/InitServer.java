package cn.wsxiot.server.main;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wsx on 2017-07-12.
 */
public class InitServer {
    //数据库地址
    public final static String DATABASEHOST = "xxx";
    //数据库名称
    public final static String DATABASENAME = "iotsystem";
    //数据库用户名
    public final static String DATABASEUSER = "xxx";
    //数据库密码
    public final static String DATABASEPASSWD = "xxx";
    //数据库端口号
    public final static int DATABASEPORT = 3306;
    //服务器启动端口号
    public final static int SERVERPORT = 000;
    //最大线程数目
    public final static int MAXNUMTHREAD = 50;
    //登录相关
    public final static String SIGNIN = "0";
    //绑定网关信息相关
    public final static String BINDGATEWAY = "1";
    //温湿度光照相关
    public final static String TEMPHUMILIGHT = "10";
    //客厅灯相关
    public final static String LIVINGLED = "11";
    //卧室灯相关
    public final static String BEDLED = "12";
    //服务器标识
    public final static String SERVER = "0";
    //网关标识
    public final static String GATEWAY = "1";
    //android标识
    public final static String ANDROIDPHONE = "2";
    //网站标识
    public final static String WEB = "3";
    //网关线程
    public static Map<Integer, TerminalThread> gateway_threads;
    //android线程
    public static Map<Integer, TerminalThread> android_threads;
    //网站线程
    public static Map<Integer, TerminalThread> web_threads;

    /**
     * 初始化
     */
    public static void init(){
        gateway_threads = new HashMap<Integer, TerminalThread>();

        android_threads = new HashMap<Integer, TerminalThread>();

        web_threads = new HashMap<Integer, TerminalThread>();
    }
}
