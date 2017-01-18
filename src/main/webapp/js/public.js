function initLoading(){
    $("body").append("<!-- loading -->" +
            "<div class='modal fade' id='loading' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' data-backdrop='static'>" +
            "<div class='modal-dialog' role='document'>" +
            "<div class='modal-content'>" +
            "<div class='modal-header'>" +
            "<h4 class='modal-title' id='myModalLabel'>提示</h4>" +
            "</div>" +
            "<div id='loadingText' class='modal-body'>" +
            "<span class='glyphicon glyphicon-refresh' aria-hidden='true'></span>" +
            "处理中，请稍候。。。" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>"
    );
}
function showLoading(text){
	if($("#loading").length<=0){
		initLoading();
	}
    $("#loadingText").html(text);
    $("#loading").modal("show");
}
function hideLoading(){
    $("#loading").modal("hide");
}
function logout(){
	showLoading("正在退出...");
	$.ajax({
		url:"loginCon/logout.action",
		success:function(data){
			if(data.state==1){
				window.location.href = window.location.href;
			}else{
				alert(data.msg);
			}
		},
		error:function(request,status,e){
			alert("退出发生异常，请刷新重试！");
		}
	});
}