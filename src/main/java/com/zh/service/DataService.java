package com.zh.service;

import com.zh.bean.EventLog;
import com.zh.bean.result.DataResult;

import java.io.OutputStream;

/**
 * Created by zh on 2017/6/18.
 */
public interface DataService {

    DataResult getData(EventLog eventLog);

    /**
     * 生成PDF数据
     */
    void createPDF(OutputStream out, EventLog eventLog);
}
