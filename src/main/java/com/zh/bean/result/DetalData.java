package com.zh.bean.result;

import java.util.List;

/**
 * Created by zh on 2017/6/18.
 */
public class DetalData {
    private ViewData viewData;
    private List<ListData> listData;

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

    public static class ListData {
        private Integer id;
        private String date;
        private String inTime;
        private String outTime;
        private Integer interTime;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getInTime() {
            return inTime;
        }

        public void setInTime(String inTime) {
            this.inTime = inTime;
        }

        public String getOutTime() {
            return outTime;
        }

        public void setOutTime(String outTime) {
            this.outTime = outTime;
        }

        public Integer getInterTime() {
            return interTime;
        }

        public void setInterTime(Integer interTime) {
            this.interTime = interTime;
        }
    }
}
