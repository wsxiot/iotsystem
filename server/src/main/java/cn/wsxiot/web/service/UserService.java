package cn.wsxiot.web.service;

import cn.wsxiot.web.domain.User;
import cn.wsxiot.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wsx on 2017-05-22.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String signup(String uemail, String uname, String upasswd, String uphone){
        User user = userRepository.save(new User(uemail,uname,upasswd,uphone,new Date()));
        if(user != null){
            return "true";
        }else{
            return "false";
        }
    }

    public String signin(String uemail, String upasswd){
        List<User> list = userRepository.findByUemailAndUpasswd(uemail, upasswd);
        if(list.size() > 0){
            return "true";
        }else{
            return "false";
        }
    }

    public String checkonly(String uemail){
        List<User> list = userRepository.findByUemail(uemail);
        if(list.size() > 0){
            return "false";
        }else{
            return "true";
        }
    }
}
