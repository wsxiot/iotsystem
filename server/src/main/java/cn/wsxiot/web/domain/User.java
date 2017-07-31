package cn.wsxiot.web.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wsx on 2017-03-25.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer uid;

    @Column(length = 320)
    private String uemail;

    @Column(length = 60)
    private String uname;

    @Column(length = 40)
    private String upasswd;

    @Column(length = 11)
    private String uphone;

    private Date utime;

    public User() {
    }

    public User(String uemail, String uname, String upasswd, String uphone, Date utime) {
        this.uemail = uemail;
        this.uname = uname;
        this.upasswd = upasswd;
        this.uphone = uphone;
        this.utime = utime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpasswd() {
        return upasswd;
    }

    public void setUpasswd(String upasswd) {
        this.upasswd = upasswd;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }
}
