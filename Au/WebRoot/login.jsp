<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<!DOCTYPE html>
<head>
	<title>登陆</title>
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
</head>
<script>
$(function(){
	$("#login_btn").click(function(){
		var name = $.trim($("#name").val());
		var pw = $.trim($("#password").val());
		if(name=='' || pw==''){
			$("#tip").html("用户名和密码不能为空！").show();
			return;
		}else{
			$("#tip").hide();
		}
		showLoading("登录中，请稍后...");
		$.ajax({
			url:'loginCon/login.action',
			data:{
				name:name,
				pw:pw
			},
			type:'post',
			success:function(data){
				hideLoading();
				if(data.state==1){
					window.location.href="<%=basePath %>";
				}else{
					$("#tip").html(data.info).show();
				}
			},
			error:function(request,status,e){
				hideLoading();
				$("#tip").html("网络异常，请刷新后重新登陆！").show();
			}
		});
	});
	$("#password").keydown(function() {
		if (event.keyCode == "13") {//keyCode=13是回车键
			$('#login_btn').click();
		}
	});
});
</script>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-2"></div>
		<div class="col-lg-10">
			<div style="height:50px"><h2>登陆</h2></div>
		</div>
	</div>
	<div class="row" style="background:#39A6EC;height:100%-200px">
		<div class="col-lg-6"></div>
		<div class="col-lg-1"></div>
		<div class="col-lg-3">
			<div style="margin:80px auto;padding:50px 30px;background:#E1F2FC">
			<form>
				<div class="form-group" style="font-size:1.25em">
					账户登录
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="name" placeholder="QQ号"/>
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="password" placeholder="密码"/>
				</div>
				<div id="tip" style="display:none" class="alert alert-danger" role="alert"></div>
				<a id="login_btn" class="btn btn-primary btn-block" href="javascript:void(0)" role="button">登陆</a>
			</form>
			</div>
		</div>
		<div class="col-lg-2"></div>
	</div>
	<div class="row">
		<div class="col-lg-12"></div>
	</div>
</div>
</body>