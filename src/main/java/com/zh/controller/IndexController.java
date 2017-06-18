package com.zh.controller;

import com.zh.bean.EventLog;
import com.zh.bean.result.DataResult;
import com.zh.service.DataService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zh on 2017/6/18.
 */
@Controller
@EnableAutoConfiguration
@ComponentScan
public class IndexController {
    @Resource
    private DataService dataService;

    @RequestMapping(value = "/index")
    public String index() {
        return "demo01";
    }

    @ResponseBody
    @RequestMapping(value = "/data")
    public DataResult data(EventLog eventLog) {
        return dataService.getData(eventLog);
    }

//    @RequestMapping("/pdfCreate")
//    public void pdfCreate(EventLog model, HttpServletResponse response) {
//        try {
//            response.setHeader("Content-Disposition", "attachment;filename="
//                    + new String("矿车时点日期查询情况.pdf".getBytes("utf-8"), "iso8859-1"));
//            response.setContentType("application/ynd.ms-excel;charset=UTF-8");
//            eventLogService.createPDF(response.getOutputStream(), model);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
