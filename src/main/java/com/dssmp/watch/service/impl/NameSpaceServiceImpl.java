package com.dssmp.watch.service.impl;

import com.dssmp.watch.dao.AlarmDao;
import com.dssmp.watch.dao.MetricRecordDao;
import com.dssmp.watch.dao.NameSpaceDao;
import com.dssmp.watch.model.NameSpace;
import com.dssmp.watch.service.NameSpaceService;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
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
public class NameSpaceServiceImpl implements NameSpaceService {

    @Autowired
    private NameSpaceDao nameSpaceDao;

    @Autowired
    private MetricRecordDao metricRecordDao;

    @Autowired
    private AlarmDao alarmDao;

    @Override
    public void saveNameSpace(NameSpace nameSpace) {
        Preconditions.checkNotNull(nameSpace);
        //检测名称是否已经存在
        if (!Strings.isNullOrEmpty(nameSpace.getName())) {
            NameSpace oldNameSpace = this.nameSpaceDao.findNameSpaceByName(nameSpace.getName());
            if (oldNameSpace == null) {
                if (nameSpace.getId() > 0) {
                    //修改
                    this.nameSpaceDao.updateNameSpace(nameSpace);
                } else {
                    //新建
                    this.nameSpaceDao.insertNameSpace(nameSpace);
                }
            }
        }
    }

    @Override
    public List<NameSpace> getAllNameSpace() {
        return this.nameSpaceDao.findAllNameSpace();
    }

    @Override
    public NameSpace getNameSpaceByName(String name) {
        Preconditions.checkNotNull(name);
        return this.nameSpaceDao.findNameSpaceByName(name);
    }

    @Override
    public boolean deleteNameSpaceById(long id) {
        Preconditions.checkArgument(id > 0);
        //校验NameSpace是否可以删除
        int nidTotal = this.alarmDao.countAlarmById(id);
        if (nidTotal == 0) {
            //如果为0则可以删除数据
            this.metricRecordDao.deleteMetricRecord(id);
            this.nameSpaceDao.deleteNameSpace(id);
            return true;
        }
        return false;
    }
}
