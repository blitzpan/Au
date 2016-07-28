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
	$("#analyse").click(function(){
		var price = $("#price").val();
		if(price==''){
			$.messager.alert("单价不能为空！");
			$("#price").focus();
			return;
		}
		$.ajax({
			type:"POST",
			url:"recordCon/analyse.action",
			data:{
				"price":price
			},
			success:function(data){
				//console.log(data);
				$("#dataList").empty();
				$("#dataList").append("<tr><th>单价</th><th>质量</th><th>总计</th><th>利润</th></tr>")
				.append("<tr class='success'><td>"+price+"</td><td>"+data.res.gram+"</td><td>"+data.res.amount.toFixed(4)+"</td><td>"+data.res.profit.toFixed(4)+"</td></tr>");
				$.each(data.res.list,function(i,item){
					$("#dataList").append("<tr><td>"+item.price+"</td><td>"+(item.gram-item.sellgram)+"</td><td>"+((item.gram-item.sellgram)*price).toFixed(4)+"</td><td>"+((item.gram-item.sellgram)*(price-item.price)).toFixed(4)+"</td></tr>");
				});
			},
			error:function(request,status,e){
				$.messager.alert("警告","网络异常！");
			}
		});
	});
});
</script>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-11" style="padding:0px">
				<ul class="nav nav-tabs">
					<li role="presentation"><a href="">折线图</a></li>
					<li role="presentation"><a href="au/records.jsp">记录</a></li>
					<li role="presentation" class="active"><a href="javascript:void(0)">分析</a></li>
				</ul>
			</div>
			<div class="col-lg-1" style="padding:0px;">
				<a href="javascript:void(0)" onclick="javascript:logout();" class="btn btn-default" style="float:right" role="button">退出</a>
			</div>
		</div>
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<div class="col-sm-3">
								<input id="price" type="number" class="form-control" min=0.00000 placeHolder="单价：元"/>
							</div>
							<div class="col-sm-3">
								<button id="analyse" type="button" class="btn btn-primary">分析</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="row">
		<div class="panel panel-default">
			<div class="panel-body">
			<table id="dataList" class="table table-hover">
				<tr>
					<th>单价</th>
					<th>质量</th>
					<th>总计</th>
					<th>利润</th>
				</tr>
			</table>
			</div>
		</div>
		</div>
	</div>
</body>