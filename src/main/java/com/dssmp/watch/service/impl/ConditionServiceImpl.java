package com.dssmp.watch.service.impl;

import com.dssmp.watch.dao.ConditionDao;
import com.dssmp.watch.dao.MetricDao;
import com.dssmp.watch.dao.NameSpaceDao;
import com.dssmp.watch.model.Metric;
import com.dssmp.watch.model.MetricCondition;
import com.dssmp.watch.model.NameSpace;
import com.dssmp.watch.service.ConditionService;
import com.google.common.base.Preconditions;
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
public class ConditionServiceImpl implements ConditionService {

    @Autowired
    private ConditionDao conditionDao;

    @Autowired
    private MetricDao metricDao;

    @Autowired
    private NameSpaceDao nameSpaceDao;


    @Override
    public List<MetricCondition> getMetricCondition() {
        return this.conditionDao.findAllMetricCondition();
    }

    @Override
    public void saveMetricCondition(MetricCondition metricCondition) {
        Preconditions.checkNotNull(metricCondition);

        Metric metric = this.metricDao.findMetricById(metricCondition.getMid());

        NameSpace nameSpace = this.nameSpaceDao.findNameSpaceById(metricCondition.getNid());
        if (metric != null && nameSpace != null) {
            metricCondition.setNamespace(nameSpace.getName());
            metricCondition.setMetric(metric.getMetricname());
            if (metricCondition.getId() > 0) {
                //修改

            } else {
                //添加
                this.conditionDao.insertMetricCondition(metricCondition);
            }
        }
    }
}
