package cn.wsxiot.server.serverutil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static cn.wsxiot.server.main.InitServer.*;

/**
 * Created by wsx on 2017-07-12.
 */
public class DatabaseUtil {
    /**
     * 获取mysql数据库的连接
     * @return
     */
    private static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
                return DriverManager.getConnection("jdbc:mysql://"+ DATABASEHOST
                        +":"+DATABASEPORT+"/"+DATABASENAME, DATABASEUSER, DATABASEPASSWD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证网关登录口令
     * @param msgs
     * @return  口令匹配返回gid  否则返回0
     */
    public static int checkGatewayLogin(String[] msgs) {
        String[] loginwords = msgs[2].split(";");
        int ret = 0;
        try {
            Connection conn = getConnection();
            if(conn != null){
                String sql = "select count(*) from gateway where gid = ? and gpasswd = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(loginwords[0]));
                ps.setString(2, loginwords[1]);
                ResultSet rss = ps.executeQuery();
                rss.next();
                if(rss.getInt(1) > 0){
                    ret = Integer.parseInt(loginwords[0]);
                }
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateGatewayol(Integer.parseInt(loginwords[0]), 1);
        return ret;
    }

    /**
     * 获取网关是否在线
     * @param gid
     * @param isol
     */
    public static void updateGatewayol(int gid, int isol){
        try {
            Connection conn = getConnection();
            if(conn != null){
                String sql = "update gateway set gonline = ? where gid = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps = conn.prepareStatement(sql);
                ps.setInt(1, isol);
                ps.setInt(2, gid);
                ps.executeUpdate();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检验客户端的登录
     * @param msgs
     * @return
     */
    public static int checkAndroidLogin(String[] msgs) {
        String[] infos = msgs[2].split(";");
        int uid = 0;
        try {
            Connection conn = getConnection();
            if(conn != null){
                String sql = "select uid from user where uemail = ? and upasswd = SHA1(?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, infos[1]);
                ps.setString(2, infos[2]);
                ResultSet rss = ps.executeQuery();
                if(rss.next()){
                    uid = rss.getInt(1);
                }
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uid;
    }

    /**
     * 保存温湿度光照信息
     * @param gid
     * @param msgs
     * @return
     */
    public static boolean saveTemphumilight(int gid, String[] msgs){
        String[] infos = msgs[2].split(";");
        try {
            Connection conn = getConnection();
            if(conn != null){
                String sql = "insert into temhumlig(gid, temp, humi, light, thltime) values(?, ?, ?, ?, now())";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps = conn.prepareStatement(sql);
                ps.setInt(1, gid);
                ps.setFloat(2, Float.parseFloat(infos[0]));
                ps.setFloat(3, Float.parseFloat(infos[1]));
                ps.setFloat(4, Float.parseFloat(infos[2]));
                ps.executeUpdate();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 由uid获取gid列表
     * @param uid 用户id
     * @return 该用户绑定的所有网关
     */
    public static List<Integer> getGidlistfromuid(int uid){
        if(uid == 0)
            return null;
        List<Integer> glist = new ArrayList<Integer>();
        try {
            Connection conn = getConnection();
            if(conn != null){
                String sql = "select gid from bindinfo where uid = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1,uid);
                ResultSet rss = ps.executeQuery();
                while(rss.next()){
                    glist.add(rss.getInt(1));
                }
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return glist;
    }

    /**
     * 获取最先的温湿度信息
     * @param msgs from andoirdphone
     * @return
     */
    public static String getlatestTHL(String[] msgs){
        String[] infos = msgs[2].split(";");
        String THL = "";
        try {
            Connection conn  =getConnection();
            if(conn != null){
                String sql = "select temp, humi, light from temhumlig where gid = ? order by thlid desc limit 1";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(infos[1]));
                ResultSet rss = ps.executeQuery();
                while(rss.next()){
                    THL += rss.getFloat(1)+";"+rss.getFloat(2)+";"+rss.getFloat(3);
                }
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return THL;
    }
}
