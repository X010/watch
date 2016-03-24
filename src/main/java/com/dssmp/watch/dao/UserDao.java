package com.dssmp.watch.dao;

import com.dssmp.watch.model.User;
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
public interface UserDao {

    /**
     * 添加用户
     *
     * @param user
     */
    @Insert("insert into watch_user(username,password)values(#{username},#{password})")
    public void insertUser(User user);

    /**
     * 更新用户
     *
     * @param user
     */
    @Update("update watch_user set password=#{password} where id=#{id}")
    public void updateUser(User user);

    /**
     * 根据用户名和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    @Select("select * from watch_user where username=#{username} and password=#{password} limit 1")
    public User findUserByUserNameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Select("select * from watch_user where username=#{username} limit 1")
    public User findUserByUserName(@Param("username")String username);

    /**
     * 根据ID获取用户
     *
     * @param id
     * @return
     */
    @Select("select * from watch_user where id=#{id}")
    public User findUserById(@Param("id") long id);

    /**
     * 根据ID删除ID
     *
     * @param id
     */
    @Delete("delete from watch_user where id=#{id}")
    public void deleteUserById(@Param("id") long id);


    /**
     * 获取所有用户
     *
     * @return
     */
    @Select("select * from watch_user")
    public List<User> findAllUser();
}
