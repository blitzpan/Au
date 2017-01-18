<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AU9999</title>
</head>
<body>
<script type="text/javascript">
//平台、设备和操作系统
var system ={
    win : false,
    mac : false,
    xll : false
};
//检测平台
var p = navigator.platform;
//alert(p);
system.win = p.indexOf("Win") == 0;
system.mac = p.indexOf("Mac") == 0;
system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);

window.location.href="./au/analyse.jsp";
//跳转语句
/*
if(system.win||system.mac||system.xll){//转向后台登陆页面
    window.location.href="./au/computer.jsp";
}else{
    window.location.href="./au/mobile.jsp";
}
*/
</script>
</body>
</html>