package com.dssmp.watch.service.impl;

import com.dssmp.watch.dao.ContactDao;
import com.dssmp.watch.model.Contact;
import com.dssmp.watch.model.ContactGroup;
import com.dssmp.watch.service.ContactService;
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
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDao contactDao;

    @Override
    public void saveContact(Contact contact) {
        Preconditions.checkNotNull(contact);
        if (contact.getId() > 0) {

        } else {
            this.contactDao.insertContact(contact);
        }
    }

    @Override
    public void saveContactGroup(ContactGroup contactGroup) {
        Preconditions.checkNotNull(contactGroup);
        if (contactGroup.getId() > 0) {

        } else {
            this.contactDao.insertContactGroup(contactGroup);
        }
    }

    @Override
    public List<ContactGroup> getContactGroup() {
        return this.contactDao.findAllContactGroup();
    }

    @Override
    public List<Contact> getContactByPage(int page, int size) {
        Preconditions.checkArgument(page > 0);
        Preconditions.checkArgument(size > 0);
        int start = (page - 1) * size;
        return this.contactDao.findContactByContact(start, size);
    }

    @Override
    public List<Contact> getAllContact() {
        return this.contactDao.findAllContact();
    }

    @Override
    public int getContactPage(int size) {
        Preconditions.checkArgument(size > 0);
        int totalRows = this.contactDao.countContactRows();
        if (totalRows % size == 0) {
            return totalRows / size;
        }
        return totalRows / size + 1;
    }
}
