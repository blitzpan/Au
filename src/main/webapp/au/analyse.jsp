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
	$.ajax({
		url:"priceCon/getLatestPrice.action",
		type:"post",
		success:function(data){
			if(data.state==1){
				$("#zxj").html(data.res.zxj);
				$("#zgj").html(data.res.zgj);
				$("#zdj").html(data.res.zdj);
				$("#zsj").html(data.res.zsj);
				$("#price").val(data.res.zxj);
			}
		},
		error:function(request,status,e){
			alert("网络异常，请刷新重试！");
		}
	});
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
		<div class="col-lg-12">
			<nav class="navbar navbar-default">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Au99.99</a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="au/computer.jsp">折线图</a></li>
						<li><a href="au/records.jsp">记录</a></li>
						<li class="active"><a href="">分析</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="javascript:void(0)" onclick="javascript:logout();">退出</a></li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<table class="table table-striped">
				<tr><th>最新价</th><th>最高价</th><th>最低价</th><th>昨收价</th></tr>
				<tr><td id="zxj"></td><td id="zgj">最高价</td><td id="zdj">最低价</td><td id="zsj">昨收价</td></tr>
			</table>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<form class="form-horizontal" role="form">
				<div class="form-group">
					<div class="col-lg-3">
						<input id="price" type="number" class="form-control" min=0.00000 placeHolder="单价：元"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-1">
						<button id="analyse" type="button" class="btn btn-primary">分析</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
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
</body>