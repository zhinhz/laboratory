package com.zh.bean.result;

import java.util.List;

/**
 * Created by zh on 2017/6/18.
 */
public class TimeData {
    private String totalAverTime;
    private String minInterTime;
    private String minInterDate;
    private String maxInterTime;
    private String maxInterDate;
    private String minEveryTime;
    private String minEveryDate;
    private String maxEveryTime;
    private String maxEveryDate;
    private ViewData viewData;
    private List<ListData> listData;

    public String getTotalAverTime() {
        return totalAverTime;
    }

    public void setTotalAverTime(String totalAverTime) {
        this.totalAverTime = totalAverTime;
    }

    public String getMinInterTime() {
        return minInterTime;
    }

    public void setMinInterTime(String minInterTime) {
        this.minInterTime = minInterTime;
    }

    public String getMinInterDate() {
        return minInterDate;
    }

    public void setMinInterDate(String minInterDate) {
        this.minInterDate = minInterDate;
    }

    public String getMaxInterTime() {
        return maxInterTime;
    }

    public void setMaxInterTime(String maxInterTime) {
        this.maxInterTime = maxInterTime;
    }

    public String getMaxInterDate() {
        return maxInterDate;
    }

    public void setMaxInterDate(String maxInterDate) {
        this.maxInterDate = maxInterDate;
    }

    public String getMinEveryTime() {
        return minEveryTime;
    }

    public void setMinEveryTime(String minEveryTime) {
        this.minEveryTime = minEveryTime;
    }

    public String getMinEveryDate() {
        return minEveryDate;
    }

    public void setMinEveryDate(String minEveryDate) {
        this.minEveryDate = minEveryDate;
    }

    public String getMaxEveryTime() {
        return maxEveryTime;
    }

    public void setMaxEveryTime(String maxEveryTime) {
        this.maxEveryTime = maxEveryTime;
    }

    public String getMaxEveryDate() {
        return maxEveryDate;
    }

    public void setMaxEveryDate(String maxEveryDate) {
        this.maxEveryDate = maxEveryDate;
    }

    public ViewData getViewData() {
        return viewData;
    }

    public void setViewData(ViewData viewData) {
        this.viewData = viewData;
    }

    public List<ListData> getListData() {
        return listData;
    }

    public void setListData(List<ListData> listData) {
        this.listData = listData;
    }
}
