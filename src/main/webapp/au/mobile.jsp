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
			    <script src="echarts-m-1.0.0/dist/echarts.js"></script>
			    <script type="text/javascript">
					var myChart;
					var option;
			        // 路径配置
			        require.config({
			            paths: {
			                echarts: 'echarts-m-1.0.0/dist'
			            }
			        });
			        
			        // 使用
			        require(
			            [
			                'echarts',
			                'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
			            ],
			            function (ec) {
			                // 基于准备好的dom，初始化echarts图表
			                myChart = ec.init(document.getElementById('main')); 
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
			               	            dataView : {show: true, readOnly: false},
			               	            restore : {show: true},
			               	            saveAsImage : {show: true}
			               	        }
			               	    },
			               	    dataZoom: {
			               	        show: true,
			               	        start : 1,
			               	        end:100
			               	    },
			               	    legend : {
			               	        data : ['最新价','最低价','最高价','开盘价'],
			               	     	x:'left'
			               	    },
			               	    grid: {
			               	        y2: 80
			               	    },
			               	    xAxis : [
			               	        {
			               	            type : 'time',
			               	            splitNumber:10
			               	        }
			               	    ],
			               	    yAxis : [
			               	        {
			               	            type : 'value'
			               	        }
			               	    ],
			               	    series : [
			               	        {
			               	            name: '最新价',
			               	            type: 'line',
			               	            showAllSymbol: true,
			               	            symbolSize: 2,
			               	            data:[]
			               	        },
			               	     	{
			            	            name: '最低价',
			            	            type: 'line',
			            	            showAllSymbol: true,
			            	            symbolSize: 2,
			            	            data:[]
			            	        },
				               	    {
				         	            name: '最高价',
				         	            type: 'line',
				         	            showAllSymbol: true,
				         	            symbolSize: 2,
				         	            data:[]
				         	        },
				               	    {
				         	            name: '开盘价',
				         	            type: 'line',
				         	            showAllSymbol: true,
				         	            symbolSize: 2,
				         	            data:[]
				         	        }
			               	    ]
			               	};
							// 为echarts对象加载数据 
							//myChart.setOption(option); 
							
							window.onresize = function() {
				                myChart.resize();
				            }
							
							
							
							
			            }
			        );
			    </script>
			    <!-- 画图结束 -->
			</div>
		</div>
		</div>
	</div>
</body>