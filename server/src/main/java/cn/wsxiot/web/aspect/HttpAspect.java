package cn.wsxiot.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wsx on 2017-04-22.
 */
@Slf4j
@Aspect
@Component
public class HttpAspect {

    @Pointcut("execution(public * cn.wsxiot.web.controller.*.*(..))")
    public void log(){
    }

    /**
     * use for log url
     */
    @Before("log()")
    public void doBeforelog(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        log.info("url :"+httpServletRequest.getRequestURL());
    }

}
