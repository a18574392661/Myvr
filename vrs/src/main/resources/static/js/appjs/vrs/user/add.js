$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {


	var isvip=$("#isVip").val();

	if(isvip==1){
		var dq=$("#sm").val();
		var vipDate=$("#vipDate").val();
		if(vipDate<=dq){
			layer.msg("会员结束时间不能小于当前时间!");
			return;
		}

	}

	$.ajax({
		cache : true,
		type : "POST",
		url : "/userallot/user/save",
		data : $('#signupForm').serialize(),// 你的formid
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
			username : {
				required : true
			},
			password : {
				required : true,
				minlength : 6
			},
			mobile : {
				required : true,
				minlength : 11,
				maxlength : 11
			},
			email : {
				required : true,
				email: true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			},
			username : {
				required : icon + "请输入用户名"
			},
			password : {
				required : icon + "密码需要大于6位数"
			},
			mobile : {
				required : icon + "请输入正确的手机号码"
			},
			email : {
				required : icon + "请输入正确的邮箱号码"
			}
		}
	})
}