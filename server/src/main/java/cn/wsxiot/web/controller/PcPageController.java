package cn.wsxiot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by wsx on 2017/1/5.
 */
@Controller
public class PcPageController {
    /**
     * 用户主页面
     * @param httpServletRequest
     * @return
     */
    @GetMapping(value = {"/","/pages/home"})
    public String index(HttpServletRequest httpServletRequest) {
        String uemail = (String) httpServletRequest.getSession().getAttribute("uemail");
        if(uemail == null){
            return "pc/signin";
        }
        return "pc/index";
    }

    /**
     * 用户注册界面
     * @return
     */
    @GetMapping(value = "/pages/signup")
    public String signup(){
        return "pc/signup";
    }

    /**
     * 用户登录界面
     * @return
     */
    @GetMapping(value = "/pages/signin")
    public String signin(){
        return "pc/signin";
    }

}
