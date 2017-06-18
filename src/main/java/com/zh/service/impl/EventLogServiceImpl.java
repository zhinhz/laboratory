/**
 * Acestek.com.cn Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.zh.service.impl;


import com.zh.bean.EventLog;
import com.zh.bean.TramcarCount;
import com.zh.dao.EventLogDao;
import com.zh.service.EventLogService;
import com.zh.util.pdf.PDFCreateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zh on 2017/4/29.
 */
@Service
public class EventLogServiceImpl implements EventLogService {

    private static final Logger logger = LoggerFactory.getLogger(EventLogService.class);
    @Autowired
    private EventLogDao eventLogDao;
    /**
     * 查询类型
     */
    public final static String DAY_TYPE = "0";
    public final static String INTERVAL_TYPE = "1";

    private final static String IN_SOURCE_ID = "0f22dd47e675424aad18d265c6de4cd9";
    private final static String OUT_SOURCE_ID = "ccadc750540945d88930a3088b85da96";

    @Override
    public List<TramcarCount> getCountInfo(EventLog eventLog) {
return null;

    }

    /**
     * 平均工作次数
     * @param eventLog
     * @return
     */
    @Override
    public Map<String,Integer> getAvgCount(EventLog eventLog) {
        Map<String,Integer> dataMap=new HashMap<>();
        for (TramcarCount tramcarCount : getCountInfo(eventLog)) {
            try{
            String queryDay = tramcarCount.getQueryTime().split(" ")[0];
            Integer count = dataMap.get(queryDay);
            if(count==null){
                dataMap.put(queryDay,1);
            }else {
                dataMap.put(queryDay,count+1);
            }}catch (Exception e){
                logger.error("查询出错",e);
            }
        }
        return dataMap;
    }


    /**
     * 匹配生成页面数据
     */
    @Override
    public List<TramcarCount> getPageCount(EventLog eventLog) {
        List<TramcarCount> data = new ArrayList<>();
        String type = eventLog.getType();
        if (!StringUtils.isEmpty(type)) {
            if (DAY_TYPE.equals(type)) {
                Integer rowCount = eventLogDao.queryDayCount(eventLog);
                eventLog.getPager().setRowCount(rowCount);
                data = eventLogDao.queryDiffOneDay(eventLog);
            } else if (INTERVAL_TYPE.equals(type)) {
                Integer rowCount = eventLogDao.queryIntervalCount(eventLog);
                eventLog.getPager().setRowCount(rowCount);
                data = eventLogDao.queryDiffInterval(eventLog);
            }
        }
        return data;
    }

    /**
     * 生成报表
     */
    @Override
    public void createPDF(OutputStream out, EventLog eventLog) {
        String type = eventLog.getType();
        eventLog.getPager().setPageFlag(false);
        List<TramcarCount> dataList = getPageCount(eventLog);
        Map<String, Object> map = createAllData(eventLog);
        int count = (Integer) map.get("count");
        String avgDiff = (String) map.get("avgDiff");
        try {
            new PDFCreateUtil(out)
                    .generatePDF(dateFormat(eventLog), count, avgDiff, dataList, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * pdf文档的查询时间显示
     */
    private String dateFormat(EventLog eventLog) {
        String day = eventLog.getQueryForOneDay();
        String from = eventLog.getQueryTimeFrom();
        String to = eventLog.getQueryTimeTo();
        if (!StringUtils.isEmpty(day)) {
            return day;
        }
        if (!StringUtils.isEmpty(from) && !StringUtils.isEmpty(to)) {
            return from + " 至 " + to;
        }
        if (!StringUtils.isEmpty(from)) {
            return from + " 之后";
        }
        if (!StringUtils.isEmpty(to)) {
            return to + " 之前";
        }
        return "";
    }

    /**
     * 生成图表数据
     */
    @Override
    public Map<String, Object> chartsMsg(EventLog eventLog) {
        eventLog.getPager().setPageFlag(false);
        List<TramcarCount> pageCount = getPageCount(eventLog);
        String[] axis = {"5", "10", "15", "20", "25", "30", "30以上"};
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("legend", new String[]{"次数"});
        result.put("xAxisName", "时间间隔");
        result.put("yAxisName", "次数");
        result.put("axis", axis);

        Map<String, Object> series1 = new HashMap<String, Object>();
        series1.put("name", "次数");
        series1.put("type", "bar");
        series1.put("data", creatData(pageCount));
        List<Map<String, Object>> series = new ArrayList<Map<String, Object>>();
        series.add(series1);
        result.put("series", series);
        return result;
    }

    ;

    //生成数据
    private double[] creatData(List<TramcarCount> pageList) {
        double[] data = new double[7];
        for (TramcarCount t : pageList) {
            double diff = t.getDiffSecond();
            if (diff <= 5) {
                data[0] += 1;
            } else if (diff > 5 && diff <= 10) {
                data[1] += 1;
            } else if (diff > 10 && diff <= 15) {
                data[2] += 1;
            } else if (diff > 15 && diff <= 20) {
                data[3] += 1;
            } else if (diff > 20 && diff <= 25) {
                data[4] += 1;
            } else if (diff > 25 && diff <= 30) {
                data[5] += 1;
            } else if (diff > 30) {
                data[6] += 1;
            }
        }
        return data;
    }

    //矿车工作次数 平均分钟间隔
    @Override
    public Map<String, Object> countData(EventLog eventLog) {
        Map<String, Object> result = createAllData(eventLog);
        return result;
    }

    //计算 矿车工作次数 平均分钟间隔
    private Map<String, Object> createAllData(EventLog eventLog) {
        eventLog.getPager().setPageFlag(false);
        eventLog.setType(DAY_TYPE);
        List<TramcarCount> pageCount = getPageCount(eventLog);
        //矿车工作次数
        int count = 0;
        //矿车总时间
        double sumTime = 0;
        for (TramcarCount tramcarCount : pageCount) {
            count++;
            sumTime = sumTime + tramcarCount.getDiffSecond();
        }
        double avgDiff = 0;
        if (count != 0) {
            avgDiff = ((double) sumTime / count);
        }
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("count", count);
        result.put("avgDiff", df.format(avgDiff));
        if (CollectionUtils.isEmpty(pageCount)) {
            result.put("min", "");
            result.put("max", "");
        } else {
            result.put("min", pageCount.get(0).getQueryTime());
            result.put("max", pageCount.get(pageCount.size() - 1).getQueryTime());
        }
        return result;
    }
}
