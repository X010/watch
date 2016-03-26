package com.dssmp.watch.service;

import com.dssmp.watch.model.Template;

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
public interface TemplateService {

    /**
     * 保存Template
     *
     * @param template
     */
    public void saveTemplate(Template template);


    /**
     * 根据ID获取模板
     *
     * @param id
     * @return
     */
    public Template getTemplateById(long id);


    /**
     * 获取所有的模板
     *
     * @return
     */
    public List<Template> getAllTemplate();


    /**
     * 根据ID删除模板对象
     *
     * @param id
     */
    public boolean deleteTemplate(long id);
}
