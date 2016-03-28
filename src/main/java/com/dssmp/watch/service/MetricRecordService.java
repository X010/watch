package com.dssmp.watch.service;

import com.dssmp.watch.model.ChartData;
import com.dssmp.watch.model.MetricCondition;
import com.dssmp.watch.model.MetricRecord;

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
public interface MetricRecordService {

    /**
     * 保存指标
     *
     * @param metricRecord
     */
    public void saveMetricRecord(MetricRecord metricRecord) throws Exception;

    /**
     * 根据条件信息统计数据
     *
     * @param metricCondition
     * @return
     */
    public ChartData countMetricRecord(MetricCondition metricCondition) throws Exception;
}
