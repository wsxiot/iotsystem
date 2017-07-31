package cn.wsxiot.web.repository;

import cn.wsxiot.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wsx on 2017-04-22.
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    //登录用
    public List<User> findByUemailAndUpasswd(String uemail, String upasswd);

    //检验电子邮箱是否重复
    public List<User> findByUemail(String uemail);
}
