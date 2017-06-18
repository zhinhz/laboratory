package com.zh.bean;


import org.springframework.util.StringUtils;

/**
 * 分页实体bean
 *
 */
public class BasePage {

	private Integer page = 1;

	private Integer rows = 10;

	private String sort;

	private String order;

	/**
	 * 分页导航
	 */
	private Pager pager = new Pager();

	public Pager getPager() {
		pager.setPageId(getPage());
		pager.setPageSize(getRows());
		String orderField = "";
		if (!StringUtils.isEmpty(sort)) {
			orderField = sort;
		}
		if (!StringUtils.isEmpty(orderField) && !StringUtils.isEmpty(order)) {
			orderField += " " + order;
		}
		pager.setOrderField(orderField);
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
