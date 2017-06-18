/**
 * Acestek.com.cn Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.zh.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志表的实体类
 *
 * @author zhanghao
 * @version $Id: EventLog.java,v 0.1 2017年1月2日 下午9:00:12 Exp $
 */
public class EventLog extends BasePage implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 拍照时间
	 */
	private Date startTime;
	/**
	 * 资源路径
	 */
	private String sourceName;

	/**
	 * 查询的开始时间
	 */
	private String queryTimeFrom;
	/**
	 * 查询的结束时间
	 */
	private String queryTimeTo;

	/**
	 * 查询一天的时间
	 */
	private String queryForOneDay;

	/**
	 * 0 表示 时点查询  1表示间隔查询
	 */
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getQueryTimeFrom() {
		return queryTimeFrom;
	}

	public void setQueryTimeFrom(String queryTimeFrom) {
		this.queryTimeFrom = queryTimeFrom;
	}

	public String getQueryTimeTo() {
		return queryTimeTo;
	}

	public void setQueryTimeTo(String queryTimeTo) {
		this.queryTimeTo = queryTimeTo;
	}

	public String getQueryForOneDay() {
		return queryForOneDay;
	}

	public void setQueryForOneDay(String queryForOneDay) {
		this.queryForOneDay = queryForOneDay;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
