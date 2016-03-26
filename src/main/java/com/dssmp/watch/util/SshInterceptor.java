package com.dssmp.watch.util;

import com.dssmp.watch.model.User;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jeffrey on 11/9/15.
 */
public class SshInterceptor extends HandlerInterceptorAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(SshInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (!Strings.isNullOrEmpty(uri)) {
            if (uri.indexOf("index") >= 0 || uri.indexOf("login") >= 0 || uri.indexOf("/api") >= 0||uri.indexOf("/metric") >= 0) {
                return true;
            } else {
                Object user = request.getSession().getAttribute(CONST.LOGIN_FLAG);
                if (user != null && user instanceof User) {
                    User loginUser = (User) user;
                    return true;
                }
                response.sendRedirect("login.action");
            }
        }
        return false;
    }
}
