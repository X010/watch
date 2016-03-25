package com.dssmp.watch.service;

import com.dssmp.watch.model.Metric;

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
public interface MetricService {

    /**
     * 保存Metric对象
     * @param metric
     */
    public void saveMetric(Metric metric);


    /**
     * 分页读取指标
     * @param page
     * @param size
     * @return
     */
    public List<Metric> getMetricByPage(int page,int size);

    /**
     * 获取总页数
     * @param page
     * @return
     */
    public int getCountPage(int page);

    /**
     * 获取所有的指标
     * @return
     */
    public List<Metric> getAllMetric();


    /**
     * 根据名称获取指标
     * @param name
     * @return
     */
    public Metric getMetricByName(String name);
}
