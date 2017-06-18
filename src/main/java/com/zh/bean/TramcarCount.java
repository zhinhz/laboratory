/**
 * Acestek.com.cn Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.zh.bean;

import java.util.Date;

/**
 * 矿车 页面显示的实体类
 * 
 * @author zhanghao
 * @version $Id: TramcarCount.java,v 0.1 2017年1月5日 上午10:51:13 Exp $
 */
public class TramcarCount {

	/**
	 * 区分来源
	 */
	private String sourceId;


	/**
	 * 小车进入时间
	 */
	private Date startTime;

	/**
	 * 小车离开时间
	 */
	private Date endTime;

	/**
	 *
	 */
	/**
	 * 间隔秒数
	 */
	private Integer diffSecond;

	/**
	 * 日期  年月日
	 */
	private String queryTime;

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	public Integer getDiffSecond() {
		return diffSecond;
	}

	public void setDiffSecond(Integer diffSecond) {
		this.diffSecond = diffSecond;
	}


	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
