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
	<script language="javascript" type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
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
			dataType:'json',
			success:function(data){
				hideLoading();
				if(data.state==1){
					clearAddForm();
					$.messager.popup("保存成功！");
					query();
				}else{
					$.messager.popup(data.msg);
				}
			},
			error:function(request,status,e){
				hideLoading();
				$.messager.alert("警告", "网络出现异常！");
			}
		});
	});
	//查询
	$("#queryRecord").click(function(){
		query();
	});
});
function clearAddForm(){
	$("#type").val("");
	$("#gram").val("");
	$("#price").val("");
	$("#operTime").val("");
}
function query(page){
	//showLoading();
	$.ajax({
		type:"POST",
		url:"recordCon/getRecords.action",
		data:{
			"begin":$("#startTime").val(),
			"end":$("#endTime").val(),
			"pageNo":page==null?"1":page
		},
		dataType:'json',
		success:function(data){
			hideLoading();
			//console.log(data);
			$("#recordTable").empty();
			$("#recordTable").append("<tr><th>利润</th><th>剩余质量</th><th>买入质量</th><th>买入价格</th>"
				+"<th>买入时间</th><th>买入总额</th><th>卖出质量</th><th>卖出时间</th></tr>");
			$.each(data.res.list,function(i,item){
				$("#recordTable").append("<tr "+(item.sellall==1?"calss='warning'":"")+"><td>"+(item.profit==0?"":item.profit.toFixed(4))+"</td><td>"+(item.gram-item.sellgram).toFixed(4)+"</td>"
					+"<td>"+item.gram.toFixed(4)+"</td><td>"+item.price.toFixed(4)+"</td><td>"+item.time+"</td>"
					+"<td>"+(item.gram*item.price).toFixed(4)+"</td><td>"+(item.sellgram==0?"":item.sellgram.toFixed(4))+"</td><td>"+(item.selltime==null?"":item.selltime)+"</td></tr>");
			});
			createPage(data.res.page);
		},error:function(request,status,e){
			hideLoading();
			$.messager.alert("警告", "网络出现异常！");
		}
	});
}
function createPage(page){
	$("#page").empty();
	var pageHtml = "<nav><ul class='pagination pagination-lg' style='margin:0px auto'>";
	pageHtml += "<li"+(page.hasPrevious?"":" class='disabled'")+"><a href='javascript:void(0)' onclick='query("+(page.pageNo-5)+")' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>";
	
	for(i=page.startPage;i<=page.endPage;i++){
		pageHtml+="<li "+(i==page.pageNo?"class='disabled'":"")+"><a href='javascript:void(0)' onclick='query("+i+")'>"+i+"</a></li>";
	}
	pageHtml += "<li"+ (page.hasNext?"":" class='disabled'")+"><a href='javascript:void(0)' onclick='query("+(page.pageNo+5)+")' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>";
	pageHtml += "</ul></nav>";
	$("#page").append(pageHtml);
}
</script>
<body>
<div class="container">
	<div class="row" style="padding:0px">
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
						<li class="active"><a href="">记录</a></li>
						<li><a href="au/analyse.jsp">分析</a></li>
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
			<form class="form-horizontal" role="form">
				<div class="form-group">
					<div class="col-lg-3">
						<select id="type" class="form-control">
							<option value=""></option>
							<option value="0">买入</option>
							<option value="1">卖出</option>
						</select>
					</div>
					<div class="col-lg-3">
						<input id="gram" type="number" class="form-control" min=0.00000 placeHolder="重量：克"/>
					</div>
					<div class="col-lg-3">
						<input id="price" type="number" class="form-control" min=0.00 placeHolder="单价：元"/>
					</div>
					<div class="col-lg-3">
						<input id="operTime" class="form-control" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="操作时间">
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-3">
						<button id="addRecord" type="button" class="btn btn-primary">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<form class="form-inline">
				<div class="form-group">
					<label for="startTime">开始时间</label>
					<input id="startTime" class="form-control" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="开始时间">
				</div>
				<div class="form-group">
					<label for="endTime">结束时间</label>
					<input id="endTime" class="form-control" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="结束时间">
				</div>
				<div class="form-group">
					<button id="queryRecord" type="button" class="btn btn-primary">查询</button>
				</div>
			</form>
		</div>
	</div>
	<div class="row" style="margin:5px">
		<div class="col-lg-12">
			<table id="recordTable" class="table table-hover">
				<tr>
					<th>利润</th>
					<th>剩余质量</th>
					<th>买入质量</th>
					<th>买入价格</th>
					<th>买入时间</th>
					<th>买入总额</th>
					<th>卖出质量</th>
					<th>卖出价格</th>
					<th>卖出时间</th>
				</tr>
			</table>
			<div id="page"></div>
		</div>
	</div>
</div>
</body>