package cn.wsxiot.web.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/15.
 */

@Entity
@Table(name = "temhumlig")
public class Temhumlig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer thlid;

    @Column(length = 11)
    private Integer gid;

    private Float temp;

    private Float humi;

    private Float light;

    private Date thltime;

    public Temhumlig() {
    }

    public Temhumlig(Integer gid, Float temp, Float humi, Float light, Date thltime) {
        this.gid = gid;
        this.temp = temp;
        this.humi = humi;
        this.light = light;
        this.thltime = thltime;
    }

    public Integer getThlid() {
        return thlid;
    }

    public void setThlid(Integer thlid) {
        this.thlid = thlid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

    public Float getHumi() {
        return humi;
    }

    public void setHumi(Float humi) {
        this.humi = humi;
    }

    public Float getLight() {
        return light;
    }

    public void setLight(Float light) {
        this.light = light;
    }

    public Date getThltime() {
        return thltime;
    }

    public void setThltime(Date thltime) {
        this.thltime = thltime;
    }
}
