package com.dssmp.watch.dao;

import com.dssmp.watch.model.MetricRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
public interface MetricRecordDao {

    /**
     * 添加记录
     *
     * @param metricRecord
     */
    @Insert("insert into watch_metricrecord(nid,namespace,mid,metricname,starttime,endtime,mvalue,mgroup)" +
            "values(#{nid},#{namespace},#{mid},#{metricname},#{starttime},#{endtime},#{mvalue},#{mgroup})")
    public void insertMetricRecord(MetricRecord metricRecord);


    /**
     * 根据命名空间删除MetricRecord
     *
     * @param nid
     */
    @Select("delete from watch_metricrecord where nid=#{nid}")
    public void deleteMetricRecord(@Param("nid") long nid);

    /**
     * 统计指标MetricRecord个数
     *
     * @param mid
     * @return
     */
    @Select("select count(1) from watch_metricrecord where mid=#{mid}")
    public int countMetricRecord(@Param("mid") long mid);


    /**
     * 统计记录条数
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param mid       指标ID
     * @param nid       命名空间ID
     * @param like      查询条件
     * @return
     */
    @Select("select count(1) from watch_metricrecord where starttime>=#{startTime} and starttime<=#{endTime} and mid=#{mid} and nid=#{nid} and mgroup like #{like}")
    public int countMetricRecordLike(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("mid") long mid, @Param("nid") long nid, @Param("like") String like);

    /**
     * 统计记录条数
     * @param startTime
     * @param endTime
     * @param mid
     * @param nid
     * @return
     */
    @Select("select count(1) from watch_metricrecord where starttime>=#{startTime} and starttime<=#{endTime} and mid=#{mid} and nid=#{nid}")
    public int countMetricRecord(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("mid") long mid, @Param("nid") long nid);
}
