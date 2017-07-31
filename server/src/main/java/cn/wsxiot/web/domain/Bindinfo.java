package cn.wsxiot.web.domain;

import javax.persistence.Entity;
import javax.persistence.Table;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/14.
 */
@Entity
@Table(name = "bindinfo")
public class Bindinfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer bid;

    @Column(length = 11)
    private Integer uid;

    @Column(length = 11)
    private Integer gid;

    private Date btime;

    public Bindinfo() {
    }

    public Bindinfo(Integer uid, Integer gid, Date btime) {
        this.uid = uid;
        this.gid = gid;
        this.btime = btime;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Date getBtime() {
        return btime;
    }

    public void setBtime(Date btime) {
        this.btime = btime;
    }
}
