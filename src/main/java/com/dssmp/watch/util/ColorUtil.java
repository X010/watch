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
public class ColorUtil {

    private final static String[] colors = new String[]{
            "#8B8878", "#87CEFA", "#848484", "#836FFF", "#7D26CD", "#71C671", "#8B0A50", "#8B5F65", "#212121"
            , "#00FFFF", "#00FF7F", "#00868B", "#00688B", "#00CDCD", "#388E8E", "#8B1A1A", "#8B8B00", "#B4CDCD"
            , "#CD00CD", "#CD9B1D", "#EE7621", "#F4F4F4", "#FF1493", "#FF4040", "#FFC1C1", "#FFEC8B", "#FFEFDB"
            , "#EE7621", "#EE6363", "#EE3A8C", "#EE00EE", "#DEB887", "#CD7054", "#CD661D", "#CD5B45", "#CD3333"
            , "#CD1076", "#CAFF70", "#C71585", "#C4C4C4", "#C1CDC1", "#BFBFBF", "#BDB76B", "#BBFFFF", "#B8B8B8"
            , "#B4EEB4", "#B3B3B3", "#B0E2FF", "#B03060", "#ADADAD", "#A9A9A9", "#A4D3EE", "#A1A1A1", "#9F79EE"
            , "#9B30FF", "#9A32CD", "#98F5FF", "#949494", "#912CEE", "#9AC0CD", "#9BCD9B", "#A52A2A", "#BFEFFF"
    };

    /**
     * 获取Color
     *
     * @return
     */
    public static String Color(int index) {
        if (index >= colors.length) {
            return colors[0];
        }
        return colors[index];
    }


}
