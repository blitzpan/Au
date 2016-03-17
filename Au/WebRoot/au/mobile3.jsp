<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<!DOCTYPE html>
<head>
	<title></title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <base href="<%=basePath%>">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<script src="js/jquery-2.0.0.min.js"></script>
	<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<!-- 
	<script src="bootstrap-3.3.5-dist/js/jquery.bootstrap.min.js"></script>
	 -->
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<style>
	</style>
	<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
</head>
<script>
$(function(){
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
				$.each(data.res, function(i,item){
					zdj.push([
					        new Date(item.gxsj),
					        item.zdj,
					        item.zdf
					        ]);
					zgj.push([
					        new Date(item.gxsj),
					        item.zgj,
					        item.zdf
					        ]);
					zxj.push([
					        new Date(item.gxsj),
					        item.zxj,
					        item.zdf
					        ]);
					kpj.push([
					        new Date(item.gxsj),
					        item.kpj,
					        item.zdf
					        ]);
					zdf.push([
					        new Date(item.gxsj),
					        item.zdf,
					        item.zdf
					        ]);
				});
				option.series[0].data=zxj;
				option.series[1].data=zdj;
				option.series[2].data=zgj;
				option.series[3].data=kpj;
				myChart.setOption(option, true);
			}
		},
		error:function(request,state,e){
			alert("查询异常！");
		}
	});
}
</script>
<body>
	<div class="container">
		<div><br/></div>
		<div class="row">
		<div class="panel panel-default">
			<div class="panel-body">
			<form class="form-inline">
				<div class="form-group">
					<label for="startTime">开始时间</label>
					<input id="startTime" class="form-control" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="开始时间">
				</div>
				<div class="form-group">
					<label for="endTime">结束时间</label>
					<input id="endTime" class="form-control" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="结束时间">
				</div>
				<button id="queryBtn" type="button" class="btn btn-primary">查询</button>
			</form>
			</div>
		</div>
		</div>
		<div class="row">
		<div class="panel panel-default">
			<div class="panel-body">
				<!-- 画图开始。为ECharts准备一个具备大小（宽高）的Dom -->
			    <div id="main" style="height:400px"></div>
			    <!-- ECharts单文件引入 -->
			    <script src="echarts-3.1.3/echarts.common.min.js"></script>
			    <script type="text/javascript">
					var myChart;
					var option;
			                // 基于准备好的dom，初始化echarts图表
			                myChart = echarts.init(document.getElementById('main')); 
			                option = {
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
			               	            restore : {show: true},
			               	            saveAsImage : {show: true}
			               	        }
			               	    },
			               	    dataZoom: [
		               	            {
		               	                type: 'slider',
		               	                realtime: true,
		               	                start: 1,
		               	                end: 99,
		               	            }
		               	        ],
			               	    legend : {
			               	        data : ['最新价','最低价','最高价','开盘价'],
			               	        x:'center'
			               	    },
			               	    grid: {
			               	    	bottom: 80
			               	    },
			               	    xAxis : [
			               	        {
			               	        	type : 'time',
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
			               	        	symbolSize: 4,
			               	            data: []
			               	        },
			               	     	{
			            	            name: '最低价',
			            	            type: 'line',
			            	            symbolSize: 4,
			            	            data: []
			            	        },
				               	    {
				         	            name: '最高价',
				         	            type: 'line',
				         	           	symbolSize: 4,
				         	            data: []
				         	        },
				               	    {
				         	            name: '开盘价',
				         	            type: 'line',
				         	           	symbolSize: 4,
				         	            data: []
				         	        }
			               	    ]
			               	};
							window.onresize = function() {
				                myChart.resize();
				            }
			    </script>
			    <!-- 画图结束 -->
			</div>
		</div>
		</div>
	</div>
</body>