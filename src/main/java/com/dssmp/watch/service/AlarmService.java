package com.dssmp.watch.service;

import com.dssmp.watch.model.Alarm;
import com.dssmp.watch.model.MetricRecord;

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
public interface AlarmService {

    /**
     * 保存报警配置
     *
     * @param name
     * @param threshold
     * @param template
     * @param namespace
     * @param metric
     */
    public void saveAlarm(String name, double threshold, long template, long namespace, long metric, int complare, String groups);


    /**
     * 分页读报警设置
     *
     * @param page
     * @param size
     * @return
     */
    public List<Alarm> getAlarmByPage(int page, int size);


    /**
     * 获取报警记录页面
     *
     * @return
     */
    public int countAlarm(int size);


    /**
     * 检测并通知
     *
     * @param metricRecord
     */
    public void checkMetricRecordAndNoticAlarm(MetricRecord metricRecord);

    /**
     * 消费MetricRecord
     *
     * @param metricRecord
     */
    public void consumerMetricRecord(MetricRecord metricRecord);
}
