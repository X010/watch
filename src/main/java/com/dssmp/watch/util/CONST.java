package com.dssmp.watch.util;

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
public class CONST {
    /**
     * GET方式
     */
    public final static String HTTP_METHOD_GET = "GET";

    /**
     * POST
     */
    public final static String HTTP_METHOD_POST = "POST";

    /**
     * 默认每页个数
     */
    public final static int DEFAULT_SIZE = 20;

    /**
     * 默认页数
     */
    public final static int DEFAULT_PAGE = 1;

    /**
     * 登陆标识
     */
    public final static String LOGIN_FLAG = "LOGIN_FLAG";

    /**
     * 组件占位符
     */
    public final static String COMPENT_PH = "#{COMPENT_PH}";

    /**
     * 正则表达式
     */
    public final static String COMPENT_PH_PATTERN = "#\\{.*?\\}";

    //比较关系
    public final static int GT = 1; //大于
    public final static int LT = 2;//小于
    public final static int EQ = 3;//等于
    public final static int LTE = 5;//小于等于
    public final static int GTE = 4;//大于等于
}
