package com.dssmp.watch.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

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
public class SQLExtend {

    private final static Logger logger = LoggerFactory.getLogger(SQLExtend.class);

    @SuppressWarnings("unchecked")
    public static String getGroupsId(Map<String, Object> params) {
        List<String> idList = (List<String>) params.get("ids");

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM watch_contact_group WHERE id in (");
        for (String id : idList) {
            if (idList.indexOf(id) > 0)
                sql.append(",");

            sql.append("'").append(id).append("'");
        }
        sql.append(")");
        logger.info(sql.toString());
        return sql.toString();
    }

    /**
     * 读取多个联系人信息
     *
     * @param params
     * @return
     */
    public static String getContactsId(Map<String, Object> params) {
        List<String> idList = (List<String>) params.get("ids");

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM watch_contact WHERE id in (");
        for (String id : idList) {
            if (idList.indexOf(id) > 0)
                sql.append(",");

            sql.append("'").append(id).append("'");
        }
        sql.append(")");
        logger.info(sql.toString());
        return sql.toString();
    }

}
