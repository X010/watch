package com.dssmp.watch.dao;

import com.dssmp.watch.model.Template;
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
public interface TemplateDao {

    /**
     * 添加模板
     *
     * @param template
     */
    @Insert("insert into watch_template(title,content)values(#{title},#{content})")
    public void insertTemplate(Template template);


    /**
     * 更新模板
     *
     * @param template
     */
    @Update("update watch_template set title=#{title},content=#{content} where id=#{id}")
    public void updateTemplate(Template template);


    /**
     * 根据ID获取模板
     *
     * @param id
     * @return
     */
    @Select("select * from watch_template where id=#{id}")
    public Template findTemplateById(@Param("id") long id);

    /**
     * 获取所有的版本号
     *
     * @return
     */
    @Select("select * from watch_template")
    public List<Template> findAllTemplate();

    /**
     * 删除所有模板
     * @param id
     */
    @Delete("delete from watch_template where id=#{id}")
    public void deleteTemplate(@Param("id") long id);
}
