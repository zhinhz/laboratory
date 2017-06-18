package com.zh.bean.result;

/**
 * Created by zh on 2017/6/18.
 */
public class DataResult {
    private CkData ckData;

    private TimeData timeData;

    private DetalData detalData;

    public DetalData getDetalData() {
        return detalData;
    }

    public void setDetalData(DetalData detalData) {
        this.detalData = detalData;
    }

    public TimeData getTimeData() {
        return timeData;
    }

    public void setTimeData(TimeData timeData) {
        this.timeData = timeData;
    }

    public CkData getCkData() {
        return ckData;
    }

    public void setCkData(CkData ckData) {
        this.ckData = ckData;
    }
}
