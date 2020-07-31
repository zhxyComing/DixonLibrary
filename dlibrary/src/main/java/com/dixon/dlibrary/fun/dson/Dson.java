package com.dixon.dlibrary.fun.dson;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by: dixon.xu
 * Create on: 2020.06.15
 * Functional desc: 自定义dson格式数据
 * <p>
 * 示例dson数据
 * <p>
 * 示例1
 * type:music|title:Find Music|titleChinese:搜音乐|bg:#00BCD4|msg:提供本地音乐查询播放功能。
 * type:normal|title:Find Movie|titleChinese:搜电影|bg:#FCD62D|msg:提供电影、电视剧下载资源，数据源较稳定，爬自网络。|openPage:com.app.dixon.resourceparser.func.movie.recommend.view.MovieOutlineActivity
 * type:normal|title:Find Magnet|titleChinese:搜磁力|bg:#CDDC39|msg:提供各类资源如电影、音乐、游戏的下载资源，数据源不保证稳定性，爬自网络。|openPage:com.app.dixon.resourceparser.func.torr.view.TorrActivity
 * type:normal|title:Find Message|titleChinese:信息|bg:#FFB74D|msg:App介绍及版本更新信息。|openPage:com.app.dixon.resourceparser.func.set.EditActivity
 * <p>
 * 示例2
 * secret:c3VwZXI=
 */
public class Dson {

    /**
     * 返回DsonData集合（以行为DsonData，参数不限）
     */
    public static List<DsonData> parse(String dson) throws IOException {
        List<DsonData> dataList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(dson.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8));
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.trim().equals("")) {
                dataList.add(parseLine(line));
            }
        }
        return dataList;
    }

    private static DsonData parseLine(String line) throws IOException {
        DsonData dd = new DsonData();
        String[] params = line.split("\\|");
        for (String param : params) {
            String[] kv = param.split(":");
            if (kv.length != 2) {
                throw new IOException("dson format is error");
            }
            dd.put(kv[0], kv[1]);
        }
        return dd;
    }
}
