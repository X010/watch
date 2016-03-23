package com.dssmp.watch.service.impl;

import com.dssmp.watch.dao.UserDao;
import com.dssmp.watch.model.User;
import com.dssmp.watch.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void saveUser(User user) {
        Preconditions.checkNotNull(user);
        if (user.getId() > 0) {
            this.userDao.updateUser(user);
        } else {
            this.userDao.insertUser(user);
        }
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        Preconditions.checkNotNull(username);
        Preconditions.checkNotNull(password);
        return this.userDao.findUserByUserNameAndPassword(username, password);
    }

    @Override
    public User getUserById(long id) {
        Preconditions.checkArgument(id > 0);
        return this.userDao.findUserById(id);
    }

    @Override
    public void deleteUserById(long id) {
        Preconditions.checkArgument(id > 0);
        this.userDao.deleteUserById(id);
    }

    @Override
    public List<User> getAllUser() {
        return this.userDao.findAllUser();
    }
}
