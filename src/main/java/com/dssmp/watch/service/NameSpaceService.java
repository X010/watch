package com.dssmp.watch.service;

import com.dssmp.watch.model.NameSpace;

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
public interface NameSpaceService {


    /**
     * 保存命名空间
     *
     * @param nameSpace
     */
    public void saveNameSpace(NameSpace nameSpace);


    /**
     * 获取所有命名空间
     *
     * @return
     */
    public List<NameSpace> getAllNameSpace();


    /**
     * 根据名称获取命名空间
     *
     * @param name
     * @return
     */
    public NameSpace getNameSpaceByName(String name);

    /**
     * 删除命名空间
     *
     * @param id
     */
    public boolean deleteNameSpaceById(long id);
}
