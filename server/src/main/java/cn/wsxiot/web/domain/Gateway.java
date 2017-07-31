package cn.wsxiot.web.domain;

import javax.persistence.Entity;
import javax.persistence.Table;


import javax.persistence.*;

/**
 * Created by Administrator on 2017/7/15.
 */

@Entity
@Table(name = "gateway")
public class Gateway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer gid;

    @Column(length = 40)
    private String gname;

    @Column(length = 40)
    private String  gpasswd;

    @Column(length = 11)
    private Integer gonline;

    public Gateway() {
    }

    public Gateway(String gname, String gpasswd, Integer gonline) {
        this.gname = gname;
        this.gpasswd = gpasswd;
        this.gonline = gonline;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGpasswd() {
        return gpasswd;
    }

    public void setGpasswd(String gpasswd) {
        this.gpasswd = gpasswd;
    }

    public Integer getGonline() {
        return gonline;
    }

    public void setGonline(Integer gonline) {
        this.gonline = gonline;
    }
}
