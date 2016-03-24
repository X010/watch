package com.dssmp.watch.dao;

import com.dssmp.watch.model.Alarm;
import org.apache.ibatis.annotations.*;

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
public interface AlarmDao {

    /**
     * 添加报警规则
     *
     * @param alarm
     */
    @Insert("insert into watch_alarm(name,mid,nid,threshold,tid,complare,namespace,template,metric,groups)" +
            "values(#{name},#{mid},#{nid},#{threshold},#{tid},#{complare},#{namespace},#{template},#{metric},#{groups})")
    public void insertAlarm(Alarm alarm);


    /**
     * 更新报警规则
     *
     * @param alarm
     */
    @Update("update watch_alarm set threshold=#{threshold},tid=#{tid},complare=#{complare} where id=#{id}")
    public void updateAlarm(Alarm alarm);


    /**
     * 删除报警规则
     *
     * @param id
     */
    @Delete("delete from watch_alarm where id=#{id}")
    public void deleteAlarm(@Param("id") long id);

    /**
     * 根据ID获取报警规则
     *
     * @param id
     * @return
     */
    @Select("select * from watch_alarm where id=#{id}")
    public Alarm findAlarmById(@Param("id") long id);


    /**
     * 分页获取报警规则
     *
     * @param start
     * @param limit
     * @return
     */
    @Select("select * from watch_alarm limit #{start},#{limit}")
    public List<Alarm> findAlarmByPage(@Param("start") int start, @Param("limit") int limit);

    /**
     * 统计行数
     * @return
     */
    @Select("select count(1) from watch_alarm")
    public int countAlarmRows();
}
