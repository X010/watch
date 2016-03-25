package com.dssmp.watch.service.impl;

import com.dssmp.watch.dao.MetricRecordDao;
import com.dssmp.watch.model.Metric;
import com.dssmp.watch.model.MetricRecord;
import com.dssmp.watch.model.NameSpace;
import com.dssmp.watch.service.MetricRecordService;
import com.dssmp.watch.service.MetricService;
import com.dssmp.watch.service.NameSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


        //添加到数据库
        this.metricRecordDao.insertMetricRecord(metricRecord);
    }
}
