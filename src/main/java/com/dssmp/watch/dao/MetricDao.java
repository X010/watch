package com.dssmp.watch.dao;

import com.dssmp.watch.model.Metric;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
public interface MetricDao {

    /**
     * 添加Metric对象
     *
     * @param metric
     */
    @Insert("insert into watch_metric(metricname)values(#{metricname})")
    public void insertMetric(Metric metric);

    /**
     * 删除指标
     *
     * @param id
     */
    @Delete("delete from watch_metric where id=#{id}")
    public void deleteMetric(@Param("id") long id);


    /**
     * 分页读取指标
     *
     * @param start
     * @param limit
     * @return
     */
    @Select("select * from watch_metric limit #{start},#{limit}")
    public List<Metric> findMetricByPage(@Param("start") int start, @Param("limit") int limit);

    /**
     * 根据ID获取指标
     *
     * @param id
     * @return
     */
    @Select("select * from watch_metric where id=#{id}")
    public Metric findMetricById(@Param("id") long id);

    /**
     * 获取总行数
     *
     * @return
     */
    @Select("select count(1) from watch_metric")
    public int countMetricRow();


    /**
     * 获取所有的指标
     *
     * @return
     */
    @Select("select * from watch_metric")
    public List<Metric> findMetric();
}
