package com.dssmp.watch.service.impl;

import com.dssmp.watch.dao.MetricRecordDao;
import com.dssmp.watch.model.*;
import com.dssmp.watch.service.AlarmService;
import com.dssmp.watch.service.MetricRecordService;
import com.dssmp.watch.service.MetricService;
import com.dssmp.watch.service.NameSpaceService;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class MetricRecordServiceImpl implements MetricRecordService {

    @Autowired
    private MetricRecordDao metricRecordDao;

    @Autowired
    private MetricService metricService;

    @Autowired
    private NameSpaceService nameSpaceService;

    @Autowired
    private AlarmService alarmService;


    @Override
    public void saveMetricRecord(MetricRecord metricRecord) throws Exception {
        //补充MetricRecord信息
        //补充命名空间
        NameSpace nameSpace = this.nameSpaceService.getNameSpaceByName(metricRecord.getNamespace());

        if (nameSpace == null) {
            throw new Exception("No Found NameSpace");
        }

        metricRecord.setNid(nameSpace.getId());

        //补充指标信息
        Metric metric = this.metricService.getMetricByName(metricRecord.getMetricname());
        if (metric == null) {
            throw new Exception("No Found Metric");
        }

        metricRecord.setMid(metric.getId());


        //发送MetricRecord到监控队列里面去
        this.alarmService.checkMetricRecordAndNoticAlarm(metricRecord);


        //添加到数据库
        this.metricRecordDao.insertMetricRecord(metricRecord);
    }

    @Override
    public ChartData countMetricRecord(MetricCondition metricCondition) throws Exception {
        Preconditions.checkNotNull(metricCondition);
        //根据MetricCondition转换查询条件
        Date endTime = new Date();
        Date startTime = new Date(endTime.getTime() - 60 * 1000);
        switch (metricCondition.getWeek()) {
            case 1:
                startTime = new Date(endTime.getTime() - 60 * 1000);
                break;
            case 2:
                startTime = new Date(endTime.getTime() - 60 * 60 * 1000);
                break;
            case 3:
                startTime = new Date(endTime.getTime() - 24 * 60 * 60 * 1000);
                break;
            case 4:
                startTime = new Date(endTime.getTime() - 7 * 24 * 60 * 60 * 1000);
                break;
            case 5:
                startTime = new Date(endTime.getTime() - 30 * 24 * 60 * 60 * 1000);
                break;
        }

        //读取数据的规则是根据时间的多少.确定Group By 的粒度
        //先统计条.然后做分断式处理
        String condition = null;
        if (!Strings.isNullOrEmpty(metricCondition.getCondition())) {
            condition = "%" + metricCondition.getCondition() + "%";
        }

        //统计记录条数
        int total = 0;
        if (!Strings.isNullOrEmpty(condition)) {
            total = this.metricRecordDao.countMetricRecordLike(startTime, endTime, metricCondition.getMid(), metricCondition.getNid(), condition);
        } else {
            total = this.metricRecordDao.countMetricRecord(startTime, endTime, metricCondition.getMid(), metricCondition.getNid());
        }

        if (total > 20000) {
            throw new Exception("single Record too much");
        }

        //处理当类


        return null;
    }
}
