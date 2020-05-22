$().ready(function() {
	
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		//var src = $("#src").val();
		//隐藏每次判断
		//$("span").css("display","none");
		//if(src == null || src == ''){
		//	$("span").css("display","inline");
		//}else{
			save();
		//}
	},
});
function save() {
	$.ajax({
		cache : true,
		type : "post",
		url : "/vrs/logImgs/save",
		data : $('#signupForm').serialize(),// 你的formid
		//dataType : "json",
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
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
			},
			sort : {
				required : true
			},
			src : {
				required : true
			}
		},
		errorPlacement: function(error, element) {
			if("src-error" == error[0].id){
				error.appendTo(element.parent().parent());
				$("#src-error").css("margin-left","205px");
			}else{
				error.appendTo(element.parent());
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			},
			sort : {
				required : icon + "请输入排序"
			},
			src : {
				required : icon + "请选择需要上传的文件"
			}
		}
	})
}