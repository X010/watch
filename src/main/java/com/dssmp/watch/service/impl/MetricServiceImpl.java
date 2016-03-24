package com.dssmp.watch.service.impl;

import com.dssmp.watch.dao.MetricDao;
import com.dssmp.watch.model.Metric;
import com.dssmp.watch.service.MetricService;
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
public class MetricServiceImpl implements MetricService {

    @Autowired
    private MetricDao metricDao;

    @Override
    public void saveMetric(Metric metric) {
        Preconditions.checkNotNull(metric);
        if (metric.getId() > 0) {

        } else {
            this.metricDao.insertMetric(metric);
        }
    }

    @Override
    public List<Metric> getMetricByPage(int page, int size) {
        Preconditions.checkArgument(page > 0);
        Preconditions.checkArgument(size > 0);
        int start = (page - 1) * size;
        return this.metricDao.findMetricByPage(start, size);
    }

    @Override
    public int getCountPage(int page) {
        int totalRow = this.metricDao.countMetricRow();
        if (totalRow % page == 0) {
            return totalRow / page;
        }
        return totalRow / page + 1;
    }

    @Override
    public List<Metric> getAllMetric() {
        return this.metricDao.findMetric();
    }
}
