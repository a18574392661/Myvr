$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	//日期必须大于当期日期


		var dq=$("#sm").val();
		var daydate=$("#daydate").val();
		if(daydate<=dq){
			layer.msg("排课日期必须大于今天!");
			return;
		}


/*	var startdate=$("#startdate").val();
	if(startdate<=dq){
		layer.msg("课程开始时间必须大于当期日期!");
		return;
	}*/
	/*var enddate=$("#enddate").val();
	if(enddate<=enddate){
		layer.msg("课程节数日期必须大于开始日期!");
		return;
	}
*/





	$.ajax({
		cache : true,
		type : "POST",
		url : "/ts/currlog/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.load();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}