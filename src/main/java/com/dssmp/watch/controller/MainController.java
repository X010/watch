package com.dssmp.watch.controller;

import com.dssmp.watch.model.User;
import com.dssmp.watch.service.UserService;
import com.dssmp.watch.until.CONST;
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

        }
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
