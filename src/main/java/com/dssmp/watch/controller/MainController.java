package com.dssmp.watch.controller;

import com.dssmp.watch.model.*;
import com.dssmp.watch.service.*;
import com.dssmp.watch.util.CONST;
import com.dssmp.watch.util.RequestUtil;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private NameSpaceService nameSpaceService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private MetricService metricService;

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private ContactService contactService;

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
     * 主界面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "mainframe.action")
    public ModelAndView mainFrame(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();

        model.setViewName("mainframe");
        return model;
    }

    /**
     * 模板管理
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "template_m.action")
    public ModelAndView template_m(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String title = RequestUtil.getString(request, "title", null);
            String content = RequestUtil.getString(request, "content", null);

            if (!Strings.isNullOrEmpty(title) && !Strings.isNullOrEmpty(content)) {
                Template template = new Template();
                template.setTitle(title);
                template.setContent(content);
                this.templateService.saveTemplate(template);
            }
        }
        List<Template> templates = this.templateService.getAllTemplate();
        if (templates != null) {
            model.addObject("templates", templates);
        }
        model.setViewName("template_m");
        return model;
    }

    /**
     * 报警管理
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "alarm_m.action")
    public ModelAndView alarm_m(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String name = RequestUtil.getString(request, "name", null);
            double threshold = RequestUtil.getDouble(request, "threshold", 0);
            long template = RequestUtil.getLong(request, "template", 0);
            long namespace = RequestUtil.getLong(request, "namespace", 0);
            long metric = RequestUtil.getLong(request, "metric", 0);
            int complare = RequestUtil.getInt(request, "complare", 1);
            String[] groups = request.getParameterValues("groups");
            String groupStr = Joiner.on(",").skipNulls().join(groups);
            if (!Strings.isNullOrEmpty(name) && template > 0 && namespace > 0 && metric > 0 && !Strings.isNullOrEmpty(groupStr)) {
                this.alarmService.saveAlarm(name, threshold, template, namespace, metric, complare, groupStr);
            }
        }
        //读取模板列表
        List<Template> templates = this.templateService.getAllTemplate();
        if (templates != null) {
            model.addObject("templates", templates);
        }

        //读取命名空间列表
        List<NameSpace> nameSpaces = this.nameSpaceService.getAllNameSpace();
        if (nameSpaces != null) {
            model.addObject("nameSpaces", nameSpaces);
        }

        //读取指标列表
        List<Metric> metrics = this.metricService.getAllMetric();
        if (metrics != null) {
            model.addObject("metrics", metrics);
        }

        //读取通知组列表
        List<ContactGroup> contactGroups = this.contactService.getContactGroup();
        if (contactGroups != null) {
            model.addObject("contactGroups", contactGroups);
        }

        //读取报警规则
        int size = RequestUtil.getInt(request, "size", CONST.DEFAULT_SIZE);
        int page = RequestUtil.getInt(request, "page", CONST.DEFAULT_PAGE);
        List<Alarm> alarms = this.alarmService.getAlarmByPage(page, size);
        if (alarms != null) {
            model.addObject("alarms", alarms);
        }

        //分页数
        int pageNum = this.alarmService.countAlarm(CONST.DEFAULT_SIZE);
        model.addObject("pageNum", pageNum);

        model.setViewName("alarm_m");
        return model;
    }

    /**
     * 命名空间管理
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "namespace_m.action")
    public ModelAndView namespace_m(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String name = RequestUtil.getString(request, "name", null);
            if (!Strings.isNullOrEmpty(name)) {
                NameSpace nameSpace = new NameSpace();
                nameSpace.setCreatetime(new Date());
                nameSpace.setName(name);
                this.nameSpaceService.saveNameSpace(nameSpace);
            }
        }
        List<NameSpace> nameSpaceList = this.nameSpaceService.getAllNameSpace();
        if (nameSpaceList != null) {
            model.addObject("namespaces", nameSpaceList);
        }
        model.setViewName("namespace_m");
        return model;
    }

    /**
     * 用户管理
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "user_m.action")
    public ModelAndView user_m(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String username = RequestUtil.getString(request, "username", null);
            String password = RequestUtil.getString(request, "password", null);
            if (!Strings.isNullOrEmpty(username) && !Strings.isNullOrEmpty(password)) {
                User user = new User();
                user.setPassword(password);
                user.setUsername(username);
                this.userService.saveUser(user);
            }
        }
        List<User> users = this.userService.getAllUser();
        if (users != null) {
            model.addObject("users", users);
        }
        model.setViewName("user_m");
        return model;
    }

    /**
     * 指标管理
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "metric_m.action")
    public ModelAndView metric_m(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String metricname = RequestUtil.getString(request, "metricname", null);
            if (!Strings.isNullOrEmpty(metricname)) {
                Metric metric = new Metric();
                metric.setMetricname(metricname);

                this.metricService.saveMetric(metric);
            }
        }
        int size = RequestUtil.getInt(request, "size", CONST.DEFAULT_SIZE);
        int page = RequestUtil.getInt(request, "page", CONST.DEFAULT_PAGE);
        List<Metric> metrics = this.metricService.getMetricByPage(page, size);
        int pageNum = this.metricService.getCountPage(size);
        if (metrics != null) {
            model.addObject("metrics", metrics);
        }
        model.addObject("pageNum", pageNum);
        model.setViewName("metric_m");
        return model;
    }

    /**
     * 指标查询
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "metric_s.action")
    public ModelAndView metric_s(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();

        model.setViewName("metric_s");
        return model;
    }


    /**
     * 联系人管理
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "contact_m.action")
    public ModelAndView contact_m(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String name = RequestUtil.getString(request, "name", null);
            String phone = RequestUtil.getString(request, "phone", null);
            String email = RequestUtil.getString(request, "email", null);
            if (!Strings.isNullOrEmpty(name) && !Strings.isNullOrEmpty(phone) && !Strings.isNullOrEmpty(email)) {
                Contact contact = new Contact();
                contact.setName(name);
                contact.setPhone(phone);
                contact.setEmail(email);
                this.contactService.saveContact(contact);
            }
        }

        int size = RequestUtil.getInt(request, "size", CONST.DEFAULT_SIZE);
        int page = RequestUtil.getInt(request, "page", CONST.DEFAULT_PAGE);

        List<Contact> contacts = this.contactService.getContactByPage(page, size);
        if (contacts != null) {
            int pageNum = this.contactService.getContactPage(size);
            model.addObject("contacts", contacts);
            model.addObject("pageNum", pageNum);
        }

        model.setViewName("contact_m");
        return model;
    }

    /**
     * 联系人组管理
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "contact_group_m.action")
    public ModelAndView contact_group_m(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String name = RequestUtil.getString(request, "name", null);
            String[] contacts = request.getParameterValues("contact");
            String contactStr = Joiner.on(",").skipNulls().join(contacts);
            int notic = RequestUtil.getInt(request, "notic", 1);
            if (!Strings.isNullOrEmpty(name) && !Strings.isNullOrEmpty(contactStr)) {
                ContactGroup contactGroup = new ContactGroup();
                contactGroup.setContacts(contactStr);
                contactGroup.setGroupname(name);
                contactGroup.setNotic(notic);
                this.contactService.saveContactGroup(contactGroup);
            }
        }

        //获取所有的联系人
        List<Contact> contacts = this.contactService.getAllContact();
        if (contacts != null) {
            model.addObject("conacts", contacts);
        }

        //联系人组列表
        List<ContactGroup> contactGroups = this.contactService.getContactGroup();
        if (contactGroups != null) {
            model.addObject("contactGroups", contactGroups);
        }

        model.setViewName("contact_group_m");
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
