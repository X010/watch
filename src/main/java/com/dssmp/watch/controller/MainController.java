package com.dssmp.watch.controller;

import com.dssmp.watch.model.User;
import com.dssmp.watch.service.UserService;
import com.dssmp.watch.util.CONST;
import com.dssmp.watch.util.RequestUtil;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@Controller
public class MainController {

    @Autowired
    private UserService userService;


    /**
     * 登陆
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "login.action")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String username = RequestUtil.getString(request, "username", null);
            String password = RequestUtil.getString(request, "password", null);
            if (!Strings.isNullOrEmpty(username) && !Strings.isNullOrEmpty(password)) {
                User user = this.userService.getUserByUsernameAndPassword(username, password);
                if (user != null) {
                    request.getSession().setAttribute(CONST.LOGIN_FLAG, user);
                    model.setViewName("redirect:mainframe.action");
                    return model;
                }
            }
        }
        model.setViewName("login");
        return model;
    }

    /**
     * 退出
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "logout.action")
    public ModelAndView loginOut(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        request.getSession().removeAttribute(CONST.LOGIN_FLAG);
        model.setViewName("login");
        return model;
    }


    /**
     * 读取当前用户
     *
     * @param request
     * @return
     */
    private User getCurrentUser(HttpServletRequest request) {
        Object obj = request.getSession().getAttribute(CONST.LOGIN_FLAG);
        return (User) obj;
    }
}
