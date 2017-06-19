//package com.zh.controller;
//
//import com.zh.bean.EventLog;
//import com.zh.bean.TramcarCount;
//import com.zh.service.EventLogService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by zh on 2017/4/29.
// */
//@RestController
//@RequestMapping("/eventLog")
//public class EventLogController {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    private EventLogService eventLogService;
//
//    /**
//     * 查询列表
//     */
//    @RequestMapping(value = "/intervalList", method = RequestMethod.GET)
//    public ModelAndView intervalList() {
//        return new ModelAndView("logInterval");
//    }
//
//    /**
//     * 查询列表
//     */
//    @RequestMapping(value = "/dayList", method = RequestMethod.GET)
//    public ModelAndView dayList() {
//        return new ModelAndView("logForDay");
//    }
//
//    /**
//     * 列表数据获取
//     */
//    @RequestMapping("/dataList")
//    public List<TramcarCount> getList(EventLog model) {
//        List<TramcarCount> dataList = eventLogService.getPageCount(model);
//        return dataList;
//    }
//
//
//
//    /**
//     * 图表数据
//     */
//    @RequestMapping("/echartData")
//    public Map<String, Object> echartData(EventLog model) {
//        Map<String, Object> result = eventLogService.chartsMsg(model);
//        return result;
//    }
//
//    /**
//     * 数据统计
//     */
//    @RequestMapping("/statMsg")
//    public Map<String, Object> statMsg(EventLog model) {
//        Map<String, Object> result = eventLogService.countData(model);
//        return result;
//    }
//}