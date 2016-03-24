package com.dssmp.watch.service.impl;

import com.dssmp.watch.dao.TemplateDao;
import com.dssmp.watch.model.Template;
import com.dssmp.watch.service.TemplateService;
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
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateDao templateDao;


    @Override
    public void saveTemplate(Template template) {
        Preconditions.checkNotNull(template);
        if (template.getId() > 0) {
            //修改
            this.templateDao.updateTemplate(template);
        } else {
            //添加
            this.templateDao.insertTemplate(template);
        }
    }

    @Override
    public Template getTemplateById(long id) {
        Preconditions.checkArgument(id > 0);
        return null;
    }

    @Override
    public List<Template> getAllTemplate() {
        return this.templateDao.findAllTemplate();
    }

    @Override
    public void deleteTemplate(long id) {
        Preconditions.checkArgument(id > 0);

    }
}
