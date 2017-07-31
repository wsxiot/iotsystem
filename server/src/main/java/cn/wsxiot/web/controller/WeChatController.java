package cn.wsxiot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wsx on 2017-07-21.
 */
@Controller
@RequestMapping("/m")
public class WeChatController {

    /**
     * home page for wechat
     * @param httpServletRequest
     * @return
     */
    @GetMapping(value = {"","/","/pages/home"})
    public String home(HttpServletRequest httpServletRequest){
        String uemail = (String) httpServletRequest.getSession().getAttribute("uemail");
        if(uemail == null){
            return "phone/signin";
        }
        return "phone/index";
    }

    /**
     * wechat signin page for signining
     * @return
     */
    @GetMapping(value = "/pages/signin")
    public String signin(){
        return "phone/signin";
    }
}
