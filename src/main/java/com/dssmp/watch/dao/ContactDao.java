package com.dssmp.watch.dao;

import com.dssmp.watch.model.Contact;
import com.dssmp.watch.model.ContactGroup;
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
public interface ContactDao {


    /**
     * 添加联系人对象
     *
     * @param contact
     */
    @Insert("insert into watch_contact(name,phone,email)values(#{name},#{phone},#{email})")
    public void insertContact(Contact contact);

    /**
     * 保存联系人组
     *
     * @param contactGroup
     */
    @Select("insert into watch_contact_group(groupname,notic,contacts)values(#{groupname},#{notic},#{contacts})")
    public void insertContactGroup(ContactGroup contactGroup);

    /**
     * 分页读取联系人
     *
     * @param start
     * @param limit
     * @return
     */
    @Select("select * from watch_contact limit #{start},#{limit}")
    public List<Contact> findContactByContact(@Param("start") int start, @Param("limit") int limit);


    /**
     * 获取联系人组列表
     *
     * @return
     */
    @Select("select * from watch_contact_group")
    public List<ContactGroup> findAllContactGroup();

    /**
     * 统计联系人总行数
     *
     * @return
     */
    @Select("select count(1) from watch_contact")
    public int countContactRows();


    /**
     * 获取所有联系人
     *
     * @return
     */
    @Select("select * from watch_contact")
    public List<Contact> findAllContact();
}
