window.onload = function(){
	view1 = echarts.init(document.getElementById('ck_canvas'));
	view2 = echarts.init(document.getElementById('timeinte_canvas'));
	view3 = echarts.init(document.getElementById('details_canvas'));
};

var drawQueryData = function(data){
	drawCKNum(data.ckData);
	drawTimeTotal(data.timeData);
	drawQueryDetail(data.detalData);
};

var drawCKNum = function(data){
	var totalNum = data.totalNum;
	var averNum = data.averNum;
	$("#total_num").text("总工作次数：" + totalNum);
	$("#aver_num").text("平均每天工作次数：" + averNum);
	
	var viewData = data.viewData;
	var option = {
		tooltip: {
			trigger: 'axis',
			position: function(pt) {
				return [pt[0], '10%'];
			}
		},
		title: {
			left: 'center',
			text: '出矿次数图表',
			top: 15,
			textStyle: {
				color: '#000',
				fontStyle: 'normal',
				fontWeight: '100',
				fontFamily: "宋体",
				fontSize: 18,
			}
		},
		toolbox: {
			top: 15,
			right: 50,
			feature: {
				saveAsImage: {}
			}
		},
		xAxis: {
			type: 'category',
			boundaryGap: false,
			data: viewData.date
		},
		yAxis: {
			type: 'value',
			boundaryGap: [0, '100%']
		},
		dataZoom: [{
			type: 'inside',
			start: 0,
			end: 100
		}, {
			start: 0,
			end: 10,
			handleSize: '80%',
			handleStyle: {
				color: '#fff',
				shadowBlur: 3,
				shadowColor: 'rgba(0, 0, 0, 0.6)',
				shadowOffsetX: 2,
				shadowOffsetY: 2
			}
		}],
		series: [{
			name: '出矿次数',
			type: 'line',
			smooth: true,
			symbol: 'none',
			sampling: 'average',
			itemStyle: {
				normal: {
					color: 'rgb(255, 70, 131)'
				}
			},
			areaStyle: {
				normal: {
					color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						offset: 0,
						color: 'rgb(255, 158, 68)'
					}, {
						offset: 1,
						color: 'rgb(255, 70, 131)'
						}])
					}
				},
				data: viewData.data
			}]
		};
	view1.setOption(option);
	
	var listData = data.listData;
	$("#cklist_table").html("<table id=\"cknum_table\"></table>");
	$('#cknum_table').bootstrapTable({
	    height: 300,
	    striped: true,
	    columns: [{
	        field: 'id',
	        title: '序号',
	        align: 'center'
	    }, {
	        field: 'date',
	        title: '日期',
	        align: 'center'
	    }, {
	        field: 'number',
	        title: '工作次数',
	        align: 'center'
	    }],
	    data: listData
	});
};

var drawTimeTotal = function(data){
	var totalAverTime = data.totalAverTime;
	var minInterTime = data.minInterTime;
	var minInterDate = data.minInterDate;
	var maxInterTime = data.maxInterTime;
	var maxInterDate = data.maxInterDate;
	var minEveryTime = data.minEveryTime;
	var minEveryDate = data.minEveryDate;
	var maxEveryTime = data.maxEveryTime;
	var maxEveryDate = data.maxEveryDate;
	$("#time_max_inter").text(maxInterTime);
	$("#time_min_inter").text(minInterTime);
	$("#time_every_max_inter").text(maxEveryTime);
	$("#time_every_min_inter").text(minEveryTime);
	$("#time_total_aver_inter").text(totalAverTime);
	$("#date_max_inter").text(maxInterDate);
	$("#date_min_inter").text(minInterDate);
	$("#date_every_max_inter").text(maxEveryDate);
	$("#date_every_min_inter").text(minEveryDate);
	
	var viewData = data.viewData;
	var option = {
		tooltip: {
			trigger: 'axis',
			position: function(pt) {
				return [pt[0], '10%'];
			}
		},
		title: {
			left: 'center',
			text: '平均时间间隔图表',
			top: 15,
			textStyle: {
				color: '#000',
				fontStyle: 'normal',
				fontWeight: '100',
				fontFamily: "宋体",
				fontSize: 18,
			}
		},
		toolbox: {
			top: 15,
			right: 50,
			feature: {
				saveAsImage: {}
			}
		},
		xAxis: {
			type: 'category',
			boundaryGap: false,
			data: viewData.date
		},
		yAxis: {
			type: 'value',
			boundaryGap: [0, '100%']
		},
		dataZoom: [{
			type: 'inside',
			start: 0,
			end: 100
		}, {
			start: 0,
			end: 10,
			handleSize: '80%',
			handleStyle: {
				color: '#fff',
				shadowBlur: 3,
				shadowColor: 'rgba(0, 0, 0, 0.6)',
				shadowOffsetX: 2,
				shadowOffsetY: 2
			}
		}],
		series: [{
			name: '平均时间间隔',
			type: 'line',
			smooth: true,
			symbol: 'none',
			sampling: 'average',
			itemStyle: {
				normal: {
					color: 'rgb(255, 70, 131)'
				}
			},
			areaStyle: {
				normal: {
					color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						offset: 0,
						color: 'rgb(255, 158, 68)'
					}, {
						offset: 1,
						color: 'rgb(255, 70, 131)'
					}])
				}
			},
			data: viewData.data
		}]
	};
	view2.setOption(option);
	
	var listData = data.listData;
	$("#time_list_table").html("<table id=\"time_table\"></table>");
	$('#time_table').bootstrapTable({
	    height: 300,
	    striped: true,
	    columns: [{
	        field: 'id',
	        title: '序号',
	        align: 'center'
	    }, {
	        field: 'date',
	        title: '日期',
	        align: 'center'
	    }, {
	        field: 'number',
	        title: '平均时间间隔',
	        align: 'center'
	    }],
	    data: listData
    });
};

var drawQueryDetail = function(data){
	var viewData = data.viewData;
	var option = {
		tooltip: {
			trigger: 'axis',
			position: function(pt) {
				return [pt[0], '10%'];
			}
		},
		title: {
			left: 'center',
			text: '查询结果详细图表',
			top: 15,
			textStyle: {
				color: '#000',
				fontStyle: 'normal',
				fontWeight: '100',
				fontFamily: "宋体",
				fontSize: 18,
			}
		},
		toolbox: {
			top: 15,
			right: 50,
			feature: {
				saveAsImage: {}
			}
		},
		xAxis: {
			type: 'category',
			boundaryGap: false,
			data: viewData.date
		},
		yAxis: {
			type: 'value',
			boundaryGap: [0, '100%']
		},
		dataZoom: [{
			type: 'inside',
			start: 0,
			end: 100
		}, {
			start: 0,
			end: 10,
			handleSize: '80%',
			handleStyle: {
				color: '#fff',
				shadowBlur: 3,
				shadowColor: 'rgba(0, 0, 0, 0.6)',
				shadowOffsetX: 2,
				shadowOffsetY: 2
			}
		}],
		series: [{
			name: '查询结果详细',
			type: 'line',
			smooth: true,
			symbol: 'none',
			sampling: 'average',
			itemStyle: {
				normal: {
					color: 'rgb(255, 70, 131)'
				}
			},
			areaStyle: {
				normal: {
					color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						offset: 0,
						color: 'rgb(255, 158, 68)'
					}, {
						offset: 1,
						color: 'rgb(255, 70, 131)'
					}])
				}
			},
			data: viewData.data
		}]
	};
	view3.setOption(option);
	
	var listData = data.listData;
	$("#detail_list_table").html("<table id=\"details_table\"></table>");
	$('#details_table').bootstrapTable({
	    height: 300,
	    striped: true,
	    columns: [{
	        field: 'id',
	        title: '序号',
	        align: 'center'
	    }, {
	        field: 'date',
	        title: '日期',
	        align: 'center'
	    }, {
	        field: 'inTime',
	        title: '进入时间',
	        align: 'center'
	    }, {
	        field: 'outTime',
	        title: '离开时间',
	        align: 'center'
	    }, {
	        field: 'interTime',
	        title: '间隔时间',
	        align: 'center'
	    }],
	    data: listData
	})
};

var exportPDF = function(){
	
};