package com.design.pattern.interceptor;

import com.design.pattern.annotation.LoginRequired;
import com.mysql.cj.util.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

public class SourceAccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器了");
        // 反射获取方法上的LoginRequred注解
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        LoginRequired loginRequired=handlerMethod.getMethod().getAnnotation(LoginRequired.class);
        if(ObjectUtils.isEmpty(loginRequired)){
            return true;
        }
        // 有LoginRequired注解说明需要登录，提示用户登录
        System.out.println("env:"+loginRequired.env());
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print("你访问的资源需要登录");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
