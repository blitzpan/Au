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
	<script src="bootstrap-3.3.5-dist/js/jquery.bootstrap.min.js"></script>
	<script src="js/public.js"></script>
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
	$("#addRecord").click(function(){
		var gram = $("#gram").val();
		var price = $("#price").val();
		var time = $("#operTime").val();
		if($("#type")==''){
			$.messager.alert("请选择类型！");
			$("#type").focus();
			return;
		}
		if(gram==''){
			$.messager.alert("质量不能为空！");
			$("#gram").focus();
			return;
		}
		if(price==''){
			$.messager.alert("单价不能为空！");
			$("#price").focus();
			return;
		}
		if(time==''){
			$.messager.alert("时间不能为空！");
			$("#operTime").focus();
			return;
		}
		showLoading();
		$.ajax({
			type:"POST",
			url:"recordCon/addRecord.action",
			data:{
				"gram":gram,
				"price":price,
				"time":time,
				"type":$("#type").val()
			},
			success:function(data){
				hideLoading();
				$.messager.popup("保存成功！");
				clearAddForm();
			},
			error:function(request,status,e){
				hideLoading();
				$.messager.alert("警告", "网络出现异常！");
			}
		});
	});
	initLoading();
});
function clearAddForm(){
	$("#type").val("");
	$("#gram").val("");
	$("#price").val("");
	$("#operTime").val("");
}
</script>
<body>
	<div class="container">
		<div><br/></div>
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<div class="col-sm-3">
								<select id="type" class="form-control">
									<option value=""></option>
									<option value="0">买入</option>
									<option value="1">卖出</option>
								</select>
							</div>
							<div class="col-sm-3">
								<input id="gram" type="number" class="form-control" min=0.00000 placeHolder="重量：克"/>
							</div>
							<div class="col-sm-3">
								<input id="price" type="number" class="form-control" min=0.00 placeHolder="单价：元"/>
							</div>
							<div class="col-sm-3">
								<input id="operTime" class="form-control" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="操作时间">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-3">
								<button id="addRecord" type="button" class="btn btn-primary">保存</button>
							</div>
						</div>
					</form>
				</div>
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
				<button id="queryRecord" type="button" class="btn btn-primary">查询</button>
			</form>
			</div>
		</div>
		</div>
		<div class="row">
		<div class="panel panel-default">
			<div class="panel-body">
			</div>
		</div>
		</div>
	</div>
</body>