package com.dssmp.watch.service.impl;

import com.dssmp.watch.dao.AlarmDao;
import com.dssmp.watch.dao.MetricDao;
import com.dssmp.watch.dao.NameSpaceDao;
import com.dssmp.watch.dao.TemplateDao;
import com.dssmp.watch.model.*;
import com.dssmp.watch.service.AlarmService;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    private AlarmDao alarmDao;

    @Autowired
    private TemplateDao templateDao;

    @Autowired
    private NameSpaceDao nameSpaceDao;

    @Autowired
    private MetricDao metricDao;

    @Override
    public void saveAlarm(String name, double threshold, long template, long namespace, long metric, int complare, String groups) {
        Preconditions.checkNotNull(name);
        Preconditions.checkArgument(template > 0);
        Preconditions.checkArgument(namespace > 0);
        Preconditions.checkArgument(metric > 0);

        Alarm alarm = new Alarm();
        alarm.setName(name);
        alarm.setComplare(complare);
        if (!Strings.isNullOrEmpty(groups)) {
            alarm.setGroups(groups);
        } else {
            return;
        }

        //判断模板是否存在
        Template templateObj = this.templateDao.findTemplateById(template);
        if (templateObj != null) {
            alarm.setTid(templateObj.getId());
            alarm.setTemplate(templateObj.getTitle());
        } else {
            return;
        }

        //判断命名空间是否在存
        NameSpace nameSpaceObj = this.nameSpaceDao.findNameSpaceById(namespace);
        if (nameSpaceObj != null) {
            alarm.setNid(nameSpaceObj.getId());
            alarm.setNamespace(nameSpaceObj.getName());
        } else {
            return;
        }

        //判断指标是否存在
        Metric metricObj = this.metricDao.findMetricById(metric);
        if (metricObj != null) {
            alarm.setMid(metricObj.getId());
            alarm.setMetric(metricObj.getMetricname());
        } else {
            return;
        }

        this.alarmDao.insertAlarm(alarm);
    }

    @Override
    public List<Alarm> getAlarmByPage(int page, int size) {
        Preconditions.checkArgument(page > 0);
        Preconditions.checkArgument(size > 0);
        int start = (page - 1) * size;
        return this.alarmDao.findAlarmByPage(start, size);
    }

    @Override
    public int countAlarm(int size) {
        int totalRow = this.alarmDao.countAlarmRows();
        if (totalRow % size == 0) {
            return totalRow / size;
        }
        return totalRow / size + 1;
    }

    @Override
    public void checkMetricRecordAndNoticAlarm(MetricRecord metricRecord) {
        Preconditions.checkNotNull(metricRecord);


    }
}
