package com.dssmp.watch.service.impl;

import com.dssmp.watch.dao.ContactDao;
import com.dssmp.watch.model.Contact;
import com.dssmp.watch.model.ContactGroup;
import com.dssmp.watch.service.NoticService;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
public class NoticServiceImpl implements NoticService {

    @Autowired
    private ContactDao contactDao;


    private final static Logger LOGGER = LoggerFactory.getLogger(NoticServiceImpl.class);


    @Override
    public void noticContact(String content, String contacts) {
        Preconditions.checkNotNull(content);
        Preconditions.checkNotNull(contacts);

        String[] gourps = contacts.split("\\,");

        if (gourps != null && gourps.length > 0) {
            //根据组读取联系人
            List<ContactGroup> contactGroupList = this.contactDao.findContactGroupByIds(Arrays.asList(gourps));

            if (contactGroupList != null && contactGroupList.size() > 0) {
                //读取联系人
                final StringBuilder contactBuilder = new StringBuilder();
                contactGroupList.stream().forEach(contactGroup -> {
                    contactBuilder.append(contactGroup.getContacts());
                    contactBuilder.append(",");
                });
                String contactStr = contactBuilder.toString();
                if (!Strings.isNullOrEmpty(contactStr)) {
                    //去掉最后一个逗号然后拆分
                    contactStr = contactStr.substring(0, contactStr.length() - 1);

                    //读取联系人列表
                    String[] conactArray = contactStr.split("\\,");

                    List<Contact> contactList = this.contactDao.findContactByIds(Arrays.asList(conactArray));

                    if (contactList != null && contactList.size() > 0) {
                        //发送信息
                        LOGGER.info("Send Notic Message To Contact Size " + contactList.size());


                    }
                }
            }
        }
    }
}
