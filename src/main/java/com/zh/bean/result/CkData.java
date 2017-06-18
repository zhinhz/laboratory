package com.zh.bean.result;

import java.util.List;

/**
 * Created by zh on 2017/6/18.
 */
public class CkData {
    private int totalNum;
    private int averNum;
    private ViewData viewData;
    private List<ListData> listData;

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getAverNum() {
        return averNum;
    }

    public void setAverNum(int averNum) {
        this.averNum = averNum;
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
