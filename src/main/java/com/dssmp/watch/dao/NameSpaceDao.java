package com.dssmp.watch.dao;

import com.dssmp.watch.model.NameSpace;
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
public interface NameSpaceDao {

    /**
     * 添加命名空间
     *
     * @param nameSpace
     */
    @Insert("insert into watch_namespace(createtime,name)values(#{createtime},#{name})")
    public void insertNameSpace(NameSpace nameSpace);

    /**
     * 更新命名空间
     *
     * @param nameSpace
     */
    @Update("update watch_namespace set createtime=#{createtime},name=#{name} where id=#{id}")
    public void updateNameSpace(NameSpace nameSpace);

    /**
     * 删除命名空间
     *
     * @param id
     */
    @Delete("delete from watch_namespace where id=#{id}")
    public void deleteNameSpace(@Param("id") long id);


    /**
     * 根据ID获取命名空间
     *
     * @param id
     * @return
     */
    @Select("select * from watch_namespace where id=#{id}")
    public NameSpace findNameSpaceById(@Param("id") long id);

    /**
     * 根据名称获取NameSpace
     *
     * @param name
     * @return
     */
    @Select("select * from watch_namespace where name=#{name} limit 1")
    public NameSpace findNameSpaceByName(@Param("name") String name);

    /**
     * 获取所有命名空间
     *
     * @return
     */
    @Select("select * from watch_namespace")
    public List<NameSpace> findAllNameSpace();
}
