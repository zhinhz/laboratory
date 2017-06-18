/**
 * Acestek.com.cn Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.zh.dao;

import com.zh.bean.EventLog;
import com.zh.bean.TramcarCount;

import java.util.List;

/**
 * 
 * 日志取数据库接口
 *
 * @author zhanghao
 * @version $Id: EventLogDao.java,v 0.1 2017年1月8日 下午8:20:55 Exp $
 */
public interface EventLogDao{

	/**
	 *
	 */
	List<TramcarCount> queryEventLogs(EventLog eventLog);
	/**
	 *  查询平均分钟间隔  间隔时间
	 */
	List<TramcarCount> queryDiffInterval(EventLog eventLog);

	/**
	 * 查询分钟间隔  一天
	 */
	List<TramcarCount> queryDiffOneDay(EventLog eventLog);

	/**
	 * 时点查询的总条数
	 */
	Integer queryDayCount(EventLog eventLog);

	/**
	 * 区间查询的总条数
	 */
	Integer queryIntervalCount(EventLog eventLog);
}
