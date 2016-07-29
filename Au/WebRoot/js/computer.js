var myChart;
var option;
$(function(){
	initEcharts();
	$("#queryBtn").click(function(){
		query();
	});
	//默认加载
	query();
});
function query(){
	var zdj = [];//最低价
	var zgj = [];//最高价
	var zxj = [];//最新价
	var kpj = [];//开盘价
	var zdf = [];//涨跌幅
    var zsj = [];//昨收价
	$.ajax({
		url:"priceCon/queryPrices.action",
		data:{
			"startTime":$("#startTime").val(),
			"endTime":$("#endTime").val()
		},
		type:"post",
		success:function(data){
			//console.log(data);
			if(data.state!=1){
				alert("查询数据出现异常！");
			}else{
				if(data.res.length==0){
					alert("没有查询到任何数据！");
					return;
				}
				var gxsj;
				$.each(data.res, function(i,item){
					gxsj = new Date(item.gxsj);
					zdj.push([
					        gxsj,
					        item.zdj,
					        item.zdf
					        ]);
					zgj.push([
						        gxsj,
						        item.zgj,
						        item.zdf
						        ]);
					zxj.push([
						        gxsj,
						        item.zxj,
						        item.zdf
						        ]);
					kpj.push([
						        gxsj,
						        item.kpj,
						        item.zdf
						        ]);
					zdf.push([
						        gxsj,
						        item.zdf,
						        item.zdf
						        ]);
                    zsj.push([
						        gxsj,
						        item.zsj,
						        item.zdf
						        ]);
				});
				option.series[0].data=zxj;
				option.series[1].data=zdj;
				option.series[2].data=zgj;
				option.series[3].data=kpj;
                option.series[4].data=zsj;
				myChart.setOption(option, true);
			}
		},
		error:function(request,state,e){
			alert("查询异常！");
		}
	});
}
function initEcharts(){
	// 基于准备好的dom，初始化echarts图表
    myChart = echarts.init(document.getElementById('main'),'shine'); 
    option = {
   	    title : {
   	        text : 'AU9999价格',
   	        subtext : '10min采集一次'
   	    },
   	    tooltip : {
   	        trigger: 'item',
   	        formatter : function (params) {
   	            var date = new Date(params.value[0]);
   	            data = date.getFullYear() + '-'
   	                   + (date.getMonth() + 1) + '-'
   	                   + date.getDate() + ' '
   	                   + date.getHours() + ':'
   	                   + date.getMinutes();
   	            return data + '<br/>'
   	                   + params.value[1] + ',' + params.value[2];
   	        }
   	    },
   	    toolbox: {
   	        show : true,
   	        feature : {
   	            mark : {show: true},
   	            dataView : {show: true, readOnly: false},
   	            restore : {show: true},
   	            saveAsImage : {show: true}
   	        }
   	    },
   	    dataZoom: {
   	        show: true,
   	        start : 1,
   	        end:100,
   	    },
   	    legend : {
   	        data : ['最新价','最低价','最高价','开盘价','昨收价']
   	    },
   	    xAxis : [
   	        {
   	            type : 'time',
   	            splitNumber:10
   	        }
   	    ],
   	    yAxis : [
   	        {
   	            type : 'value',
   	         	scale:true//设置为true代表坐标轴不会强制包含0，就这个功能我找了够2小时，可是坑死了
   	        }
   	    ],
   	    series : [
   	        {
   	            name: '最新价',
   	            type: 'line',
   	            showAllSymbol: false,
   	            symbolSize: 2,
   	         	smooth:true,
   	            data: []
   	        },
   	     	{
	            name: '最低价',
	            type: 'line',
	            showAllSymbol: false,
	            symbolSize: 2,
	            smooth:true,
	            data: []
	        },
       	    {
 	            name: '最高价',
 	            type: 'line',
 	            showAllSymbol: false,
 	            symbolSize: 2,
 	            smooth:true,
 	            data: []
 	        },
       	    {
 	            name: '开盘价',
 	            type: 'line',
 	            showAllSymbol: false,
 	            symbolSize: 2,
 	            smooth:true,
 	            data: []
 	        },
                  {
 	            name: '昨收价',
 	            type: 'line',
 	            showAllSymbol: false,
 	            symbolSize: 2,
 	            smooth:true,
 	            data: []
 	        }
   	    ]
   	};
}