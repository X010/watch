package com.dssmp.watch.util;

import com.dssmp.watch.model.Alarm;
import com.dssmp.watch.model.MetricRecord;
import com.google.common.base.Strings;

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
public class TemplateUtil {

    public final static String METRICNAME = "#{METRICNAME}"; //指标名
    public final static String VALUE = "#{VALUE}"; //值
    public final static String ALARMNAME = "#{ALARMNAME}";//报警规则名称


    /**
     * 替换相关数据
     *
     * @param content
     * @return
     */
    public static String replaceTemplate(String content, MetricRecord metricRecord, Alarm alarm) {
        if (!Strings.isNullOrEmpty(content)) {
            //替换数据
            content = content.replace(METRICNAME, metricRecord.getMetricname());
            content = content.replace(VALUE, String.valueOf(metricRecord.getMvalue()));
            content = content.replace(ALARMNAME, alarm.getName());


            return content;
        }
        return null;
    }
}
