/**
 * 个人js库
 */
/**
 * 获取JSon对象的长度
 * @param jsonData
 * @returns
 */
function getJSonObjLenth(jsonObj){
	var jsonLength=0;
	for(var item in jsonObj){
		jsonLength++;
	}
	return jsonLength;
}
//验证登录
function checkUser() {
	if (window != top) {
		top.location.href = location.href;
	}
	$.ajax({
		type : 'get',
		dataType : "json",
		url : "api/checkUser",
		data : {
			time : new Date()
		},
		success : function(data) {
			var flag = data.success;
			if (flag) {
			} else {
				$(location).attr('href', "http://login.test.com/login.html");
			}
		},
		error : function() {
		}
	});
}
//关闭弹窗  ---点击关闭弹窗
function close(){
	$("#close").click(function(){
		closeDiv();
	})
}
//让弹窗关闭
function closeDiv(){
	$("#MyDiv").css('display','none'); 
	$("#fade").css('display','none'); 
}
//让弹窗打开
function showDiv() {
	// 打开弹窗
	$("#MyDiv").css('display', 'block');
	$("#fade").css('display', 'block');
	$("#fade").css('width', $(window).width());
	$("#fade").css('height', $(window).height());
}

