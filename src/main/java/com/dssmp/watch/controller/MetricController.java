package com.dssmp.watch.controller;

import com.dssmp.watch.model.MetricRecord;
import com.dssmp.watch.model.ResponseMessage;
import com.dssmp.watch.service.MetricRecordService;
import com.dssmp.watch.util.JsonParser;
import com.dssmp.watch.util.RequestUtil;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

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
@RequestMapping(value = "/metric")
public class MetricController {

    @Autowired
    private MetricRecordService metricRecordService;


    /**
     * 获取PUT指标数据
     *
     * @param request
     * @param response
     * @apiParam {String} namespace 命名空间
     * @apiParam {String} metric    指标
     * @apiParam {long} timestamp 时间戳
     * @apiParam {double} value   值
     * @apiParam {String} group  组
     * @apiParam {long} delay   +时间T  timestamp表示时间点,如果是时间范围用timestamp+delay表示即偏差值
     */
    @ResponseBody
    @RequestMapping(value = "put.action")
    public String put(HttpServletRequest request, HttpServletResponse response) {
        ResponseMessage responseMessage = new ResponseMessage();
        String namespace = RequestUtil.getString(request, "namespace", null);
        String metric = RequestUtil.getString(request, "metric", null);
        long timestamp = RequestUtil.getLong(request, "timestamp", 0);
        double value = RequestUtil.getDouble(request, "value", 0);
        String group = RequestUtil.getString(request, "group", null);
        long delay = RequestUtil.getLong(request, "delay", 0);

        //检验参数
        boolean isCheck = true;

        if (Strings.isNullOrEmpty(namespace)) {
            isCheck = false;
            responseMessage.setMessage("parameter namespace is Error");
        }

        if (Strings.isNullOrEmpty(metric)) {
            isCheck = false;
            responseMessage.setMessage("parameter metric is Error");
        }

        if (timestamp < 0) {
            isCheck = false;
            responseMessage.setMessage("parameter timestamp is Error");
        }

        if (Strings.isNullOrEmpty(group)) {
            isCheck = false;
            responseMessage.setMessage("parameter group is Error");
        }


        if (isCheck) {
            //保存MetricRecord
            MetricRecord metricRecord = new MetricRecord();
            metricRecord.setNamespace(namespace);
            metricRecord.setMetricname(metric);
            metricRecord.setStarttime(new Date(timestamp));
            if (delay == 0) {
                metricRecord.setEndtime(new Date(timestamp));
            } else {
                metricRecord.setEndtime(new Date(timestamp + delay * 1000));
            }
            metricRecord.setMvalue(value);
            metricRecord.setMgroup(group);


            try {

                this.metricRecordService.saveMetricRecord(metricRecord);
            } catch (Exception e) {
                responseMessage.setMessage(e.getMessage());
                e.printStackTrace();
            }

        }
        return JsonParser.simpleJson(responseMessage);
    }
}
