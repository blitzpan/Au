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
	<script src="js/public.js"></script>
	<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script src="bootstrap-3.3.5-dist/js/jquery.bootstrap.min.js"></script>
	<script src="js/echarts3.2.2/echarts.common.3.2.2.min.js"></script>
	<script src="js/echarts3.2.2/theme/shine.js"></script>
	<script src="js/computer.js"></script>
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


</script>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-11" style="padding:0px">
				<ul class="nav nav-tabs">
					<li role="presentation" class="active"><a href="">折线图</a></li>
					<li role="presentation"><a href="au/records.jsp">记录</a></li>
					<li role="presentation"><a href="au/analyse.jsp">分析</a></li>
				</ul>
			</div>
			<div class="col-lg-1" style="padding:0px;">
				<a href="javascript:void(0)" onclick="javascript:logout();" class="btn btn-default" style="float:right" role="button">退出</a>
			</div>
		</div>
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
			    <div id="main" style="height:450px"></div>
			</div>
		</div>
		</div>
	</div>
</body>