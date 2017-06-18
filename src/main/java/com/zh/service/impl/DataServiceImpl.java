package com.zh.service.impl;

import com.zh.bean.EventLog;
import com.zh.bean.TramcarCount;
import com.zh.bean.result.*;
import com.zh.dao.EventLogDao;
import com.zh.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zh on 2017/6/18.
 */
@Service
public class DataServiceImpl implements DataService {

    private static final Logger logger = LoggerFactory.getLogger(DataService.class);
    @Resource
    private EventLogDao eventLogDao;
    private final static String IN_SOURCE_ID = "0f22dd47e675424aad18d265c6de4cd9";
    private final static String OUT_SOURCE_ID = "ccadc750540945d88930a3088b85da96";


    @Override
    public DataResult getData(EventLog eventLog) {
        DataResult dataResult = new DataResult();
        List<TramcarCount> allDataInfo = getAllDataInfo(eventLog);
        CkData ckData = getCkData(allDataInfo);
        TimeData timeData = getTimeData(allDataInfo);
        DetalData detalData = getDetalData(allDataInfo);
        dataResult.setCkData(ckData);
        dataResult.setTimeData(timeData);
        dataResult.setDetalData(detalData);
        return dataResult;
    }



    private DetalData getDetalData(List<TramcarCount> dataList) {
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
        DetalData detalData = new DetalData();
        ViewData viewData = new ViewData();
        String[] date = new String[dataList.size()];
        Integer[] data = new Integer[dataList.size()];
        int i = 0;
        int count = 1;
        List<DetalData.ListData> listDatas = new ArrayList<>();
        for (TramcarCount tramcarCount : dataList) {
            DetalData.ListData listData = new DetalData.ListData();
            data[i] = tramcarCount.getDiffSecond();
            date[i] = tramcarCount.getQueryTime() + " " + sf.format(tramcarCount.getStartTime());
            listData.setId(count);
            listData.setDate(tramcarCount.getQueryTime());
            listData.setInterTime(tramcarCount.getDiffSecond());
            listData.setInTime(sf.format(tramcarCount.getStartTime()));
            listData.setOutTime(sf.format(tramcarCount.getEndTime()));
            i++;
            count++;
            listDatas.add(listData);
        }
        viewData.setDate(date);
        viewData.setData(data);
        detalData.setViewData(viewData);
        detalData.setListData(listDatas);
        return detalData;
    }

    private TimeData getTimeData(List<TramcarCount> dataList) {
        TimeData timeData = new TimeData();
        all(timeData, dataList);
        avg(timeData, dataList);
        return timeData;

    }

    /**
     * 总的最长和最短
     */
    private void all(TimeData timeData, List<TramcarCount> dataList) {
        int maxDiff = 0;
        int minDiff = 0;
        String maxTime = "";
        String minTime = "";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int total = 0;
        for (TramcarCount tramcarCount : dataList) {
            Integer diffSecond = tramcarCount.getDiffSecond();
            if (diffSecond > maxDiff) {
                maxDiff = diffSecond;
                maxTime = sf.format(tramcarCount.getStartTime());
            }
            if (diffSecond < minDiff || minDiff == 0) {
                minDiff = diffSecond;
                minTime = sf.format(tramcarCount.getStartTime());
            }
            total += diffSecond;
        }
        timeData.setMaxInterTime(String.valueOf(maxDiff));
        timeData.setMaxInterDate(maxTime);
        timeData.setMinInterTime(String.valueOf(minDiff));
        timeData.setMinInterDate(minTime);
        if (dataList.size() != 0) {
            timeData.setTotalAverTime(String.valueOf(total / dataList.size()));
        } else {
            timeData.setTotalAverTime("0");
        }
    }

    /**
     * 平均最长和最短
     *
     * @param timeData
     * @param dataList
     */
    private void avg(TimeData timeData, List<TramcarCount> dataList) {
        Map<String, Integer> dayCount = getDayCount(dataList);
        Map<String, Integer> avgCount = getAvgCount(dataList);
        int avgMaxDiff = 0;
        int avgMinDiff = 0;
        String maxTime = "";
        String minTime = "";
        ViewData viewData = new ViewData();
        String[] date = new String[dayCount.entrySet().size()];
        Integer[] data = new Integer[dayCount.entrySet().size()];
        int i = 0;
        List<ListData> listDatas = new ArrayList<>();
        int count = 1;
        for (Map.Entry<String, Integer> dayMap : dayCount.entrySet()) {
            ListData listData = new ListData();
            for (Map.Entry<String, Integer> stringIntegerEntry : avgCount.entrySet()) {
                if (dayMap.getKey().equals(stringIntegerEntry.getKey())) {
                    Integer avgDiff = dayMap.getValue() / stringIntegerEntry.getValue();
                    date[i] = dayMap.getKey();
                    data[i] = avgDiff;
                    listData.setDate(dayMap.getKey());
                    listData.setNumber(avgDiff);
                    listData.setId(count);
                    if (avgDiff > avgMaxDiff) {
                        avgMaxDiff = avgDiff;
                        maxTime = dayMap.getKey();
                    }
                    if (avgDiff < avgMinDiff || avgMinDiff == 0) {
                        avgMinDiff = avgDiff;
                        minTime = dayMap.getKey();
                    }
                }
            }
            i++;
            count++;
            listDatas.add(listData);
        }
        viewData.setDate(date);
        viewData.setData(data);
        timeData.setViewData(viewData);
        timeData.setListData(listDatas);
        timeData.setMaxEveryTime(String.valueOf(avgMaxDiff));
        timeData.setMaxEveryDate(maxTime);
        timeData.setMinEveryTime(String.valueOf(avgMinDiff));
        timeData.setMinEveryDate(minTime);
    }

    private CkData getCkData(List<TramcarCount> dataList) {
        CkData ckData = new CkData();
        int totalCount = dataList.size();
        ckData.setTotalNum(totalCount);
        Map<String, Integer> avgCount = getAvgCount(dataList);
        int dayCount = avgCount.keySet().size();
        if (dayCount != 0) {
            ckData.setAverNum(totalCount / dayCount);
        } else {
            ckData.setAverNum(0);
        }
        ViewData viewData = getViewData(avgCount);
        ckData.setViewData(viewData);
        Set<Map.Entry<String, Integer>> entries = avgCount.entrySet();
        int i = 1;
        List<ListData> listDatas = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : entries) {
            ListData listData = new ListData();
            listData.setId(i);
            listData.setDate(entry.getKey());
            listData.setNumber(entry.getValue());
            listDatas.add(listData);
            i++;
        }
        ckData.setListData(listDatas);
        return ckData;
    }

    private ViewData getViewData(Map<String, Integer> avgCount) {
        Set<String> strings = avgCount.keySet();
        Collection<Integer> values = avgCount.values();
        String[] date = new String[strings.size()];
        Integer[] data = new Integer[values.size()];
        ViewData viewData = new ViewData();
        int i = 0;
        for (String string : strings) {
            date[i] = string;
            i++;
        }
        i = 0;
        for (Integer value : values) {
            data[i] = value;
            i++;
        }
        viewData.setData(data);
        viewData.setDate(date);
        return viewData;
    }

    /**
     * 每天的工作次数
     *
     * @param dataList
     * @return
     */
    private Map<String, Integer> getAvgCount(List<TramcarCount> dataList) {
        Map<String, Integer> dataMap = new HashMap<>();
        for (TramcarCount tramcarCount : dataList) {
            String queryDay = tramcarCount.getQueryTime().split(" ")[0];
            Integer count = dataMap.get(queryDay);
            if (count == null) {
                dataMap.put(queryDay, 1);
            } else {
                dataMap.put(queryDay, count + 1);
            }
        }
        return dataMap;
    }

    /**
     * 每天的工作时长
     *
     * @param dataList
     * @return
     */
    private Map<String, Integer> getDayCount(List<TramcarCount> dataList) {
        Map<String, Integer> dataMap = new HashMap<>();
        for (TramcarCount tramcarCount : dataList) {
            String queryDay = tramcarCount.getQueryTime().split(" ")[0];
            Integer count = dataMap.get(queryDay);
            if (count == null) {
                dataMap.put(queryDay, tramcarCount.getDiffSecond());
            } else {
                dataMap.put(queryDay, count + tramcarCount.getDiffSecond());
            }
        }
        return dataMap;
    }

    private List<TramcarCount> getAllDataInfo(EventLog eventLog) {
        List<TramcarCount> tramcarCounts = eventLogDao.queryEventLogs(eventLog);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = null;
        Date endTime = null;
        List<TramcarCount> dataList = new ArrayList<>();
        for (TramcarCount tramcarCount : tramcarCounts) {
            String startSourceId = tramcarCount.getSourceId();
            Date beginTime = tramcarCount.getStartTime();
            if (endTime != null && IN_SOURCE_ID.equals(startSourceId)) {
                //有结束时间并且又是新的一次进来
                TramcarCount data = new TramcarCount();
                Long i = (endTime.getTime() - startTime.getTime()) / 1000;
                data.setDiffSecond(Integer.valueOf(i.toString()));
                data.setQueryTime(sf.format(startTime));
                data.setStartTime(startTime);
                data.setEndTime(endTime);
                startTime = null;
                endTime = null;
                dataList.add(data);
            }

            if (IN_SOURCE_ID.equals(startSourceId)) {
                startTime = beginTime;
            }
            if (OUT_SOURCE_ID.equals(startSourceId)) {
                endTime = beginTime;
            }
        }
        if (startTime != null && endTime != null) {
            TramcarCount data = new TramcarCount();
            Long i = (endTime.getTime() - startTime.getTime()) / 1000;
            data.setDiffSecond(Integer.valueOf(i.toString()));
            data.setQueryTime(sf.format(startTime));
            data.setStartTime(startTime);
            data.setEndTime(endTime);
            dataList.add(data);
        }
        return dataList;
    }
}
