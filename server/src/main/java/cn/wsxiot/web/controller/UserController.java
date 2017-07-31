package cn.wsxiot.web.controller;

import cn.wsxiot.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wsx on 2017-05-21.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param uname
     * @param uemail
     * @param uphone
     * @param upasswd
     * @return
     */
    @PostMapping(value = "/users/signup")
    public String signup(String uname, String uemail, String uphone, String upasswd) {
        if(uname != null && uemail != null && uphone != null && upasswd != null){
            return userService.signup(uemail, uname, upasswd, uphone);
        }
        return "false";
    }

    /**
     * 用户登录
     * @param httpServletRequest
     * @param uemail
     * @param upasswd
     * @return
     */
    @PostMapping(value = "/users/signin")
    public String signin(HttpServletRequest httpServletRequest, String uemail, String upasswd){
        if(uemail != null && upasswd != null){
            String ret = userService.signin(uemail, upasswd);
            if("true".equals(ret)){
                httpServletRequest.getSession().setAttribute("uemail",uemail);
                return "true";
            }
            return "false";
        }
        return "false";
    }

    /**
     * 检查邮箱是否唯一，因为登录是由邮箱唯一标识的
     * @param uemail
     * @return
     */
    @PostMapping(value = "/users/checkonly")
    public String checkonly(String uemail){
        if(uemail != null){
            return userService.checkonly(uemail);
        }
        return "false";
    }

    /**
     * 用户退出
     * @param httpServletRequest
     * @return
     */
    @PostMapping(value = "/users/signout")
    public String signout(HttpServletRequest httpServletRequest){
        String ret = (String) httpServletRequest.getSession().getAttribute("uemail");
        if(ret != null){
            httpServletRequest.getSession().removeAttribute("uemail");
        }
        return "true";
    }


}
